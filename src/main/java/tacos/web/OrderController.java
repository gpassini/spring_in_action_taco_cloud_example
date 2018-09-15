package tacos.web;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import lombok.extern.slf4j.Slf4j;
import tacos.Order;
import tacos.data.OrderRepository;

@Slf4j
@Controller
@RequestMapping("/orders")
public class OrderController {

  private static final String TEMPLATE = "orderForm";

  private final OrderRepository orderRepository;

  @Autowired
  public OrderController(OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  @GetMapping("/current")
  public String orderForm(Model model) {
    log.info("Showing current order");
    model.addAttribute("order", new Order());
    return TEMPLATE;
  }

  @PostMapping
  public String processOrder(@Valid Order order, Errors errors, SessionStatus sessionStatus) {
    if (errors.hasErrors()) {
      return TEMPLATE;
    }

    log.info("Order submitted: {}", order);
    orderRepository.save(order);
    sessionStatus.setComplete();

    return "redirect:/";
  }
}
