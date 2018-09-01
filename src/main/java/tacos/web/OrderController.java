package tacos.web;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import tacos.Order;

@Slf4j
@Controller
@RequestMapping("/orders")
public class OrderController {
	
	private static final String TEMPLATE = "orderForm";

	@GetMapping("/current")
	public String orderForm(Model model) {
		log.info("Showing current order");
		model.addAttribute("order", new Order());
		return TEMPLATE;
	}
	
	@PostMapping
	public String processOrder(@Valid Order order, Errors errors) {
		if (errors.hasErrors()) {
			return TEMPLATE;
		}
		
		log.info("Order submitted: {}", order);
		
		return "redirect:/";
	}
}
