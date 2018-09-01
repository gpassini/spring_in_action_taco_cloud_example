package tacos.web;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import tacos.Ingredient;
import tacos.Ingredient.Type;
import tacos.Taco;

@Slf4j
@Controller
@RequestMapping("/design")
public class DesignTacoController {
	
	private static final String TEMPLATE = "design";

	@GetMapping
	public String showDesignForm(Model model) {
		log.info("Opening design page");
		List<Ingredient> ingredients = Arrays.asList(
			new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
			new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
			new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
			new Ingredient("CARN", "Carnitas", Type.PROTEIN),
			new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
			new Ingredient("LETC", "Lettuce", Type.VEGGIES),
			new Ingredient("CHED", "Cheddar", Type.CHEESE),
			new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
			new Ingredient("SLSA", "Salsa", Type.SAUCE),
			new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
		);
		Stream.of(Type.values())
				.forEach(type -> model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type)));

		if (!model.containsAttribute("taco")) {
			model.addAttribute("taco", new Taco());	
		}

		return TEMPLATE;
	}
	
	@PostMapping
	public String processDesign(Model model, @Valid Taco taco, Errors errors) {
		if (errors.hasErrors()) {
			return showDesignForm(model);
		}
		
		log.info("Processing design: {}", taco);
		
		return "redirect:/orders/current";
	}

	private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
		return ingredients.stream().filter(ingredient -> type.equals(ingredient.getType()))
				.collect(Collectors.toList());
	}
}
