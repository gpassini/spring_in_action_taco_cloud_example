package tacos.web;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import lombok.extern.slf4j.Slf4j;
import tacos.Order;
import tacos.User;
import tacos.data.OrderRepository;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
@ConfigurationProperties(prefix = "taco.orders")
public class OrderController {

  private static final String TEMPLATE = "orderForm";

  private final OrderRepository orderRepository;

  private OrderProps props;

  @Autowired
  public OrderController(OrderRepository orderRepository, OrderProps props) {
    this.orderRepository = orderRepository;
    this.props = props;
  }

  @GetMapping("/current")
  public String orderForm(Model model) {
    log.info("Showing current order");
    model.addAttribute("order", new Order());
    return TEMPLATE;
  }

  @PostMapping
  public String processOrder(@Valid Order order, Errors errors, SessionStatus sessionStatus,
      @AuthenticationPrincipal User user) {
    if (errors.hasErrors()) {
      return TEMPLATE;
    }

    order.setUser(user);

    log.info("Order submitted: {}", order);
    orderRepository.save(order);
    sessionStatus.setComplete();

    return "redirect:/";
  }

  @GetMapping
  public String ordersForUser(@AuthenticationPrincipal User user, Model model) {
    Pageable pageable = PageRequest.of(0, props.getPageSize());
    model.addAttribute("orders", orderRepository.findByUserOrderByPlacedAtDesc(user, pageable));
    return "orderList";
  }

}
