package tacos.web;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import lombok.extern.slf4j.Slf4j;
import tacos.Ingredient;
import tacos.Ingredient.Type;
import tacos.Order;
import tacos.Taco;
import tacos.data.IngredientsRepository;
import tacos.data.TacoRepository;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

  private static final String TEMPLATE = "design";

  private final IngredientsRepository ingredientsRepository;

  private final TacoRepository tacoRepository;

  @Autowired
  public DesignTacoController(IngredientsRepository ingredientsRepository,
      TacoRepository tacoRepository) {
    this.ingredientsRepository = ingredientsRepository;
    this.tacoRepository = tacoRepository;
  }

  @ModelAttribute(name = "order")
  public Order order() {
    return new Order();
  }

  @ModelAttribute(name = "taco")
  public Taco taco() {
    return new Taco();
  }

  @GetMapping
  public String showDesignForm(Model model) {
    log.info("Opening design page");

    List<Ingredient> ingredients = new ArrayList<>();
    ingredientsRepository.findAll().forEach(ingredients::add);

    Stream.of(Type.values()).forEach(
        type -> model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type)));

    if (!model.containsAttribute("taco")) {
      model.addAttribute("taco", new Taco());
    }

    return TEMPLATE;
  }

  @PostMapping
  public String processDesign(Model model, @Valid Taco taco, Errors errors,
      @ModelAttribute Order order) {

    if (errors.hasErrors()) {
      return showDesignForm(model);
    }

    log.info("Processing design: {}", taco);
    Taco saved = tacoRepository.save(taco);
    order.addDesign(saved);

    return "redirect:/orders/current";
  }

  private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
    return ingredients.stream().filter(ingredient -> type.equals(ingredient.getType()))
        .collect(Collectors.toList());
  }
}
