package tacos.reactive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import tacos.Ingredient;
import tacos.data.IngredientsRepository;

@RestController
@RequestMapping(path = "/ingredient", produces = "application/json")
@CrossOrigin(origins = "*")
public class IngredientController {

  private final IngredientsRepository ingredientRepository;

  @Autowired
  public IngredientController(IngredientsRepository ingredientRepository) {
    this.ingredientRepository = ingredientRepository;
  }

  @GetMapping
  public Flux<Ingredient> allIngredients() {
    return ingredientRepository.findAll();
  }
}
