package tacos.web.api;

import org.springframework.hateoas.ResourceSupport;
import lombok.Getter;
import tacos.Ingredient;
import tacos.Ingredient.Type;

@Getter
public class IngredientResource extends ResourceSupport {

  private Type type;
  private String name;

  public IngredientResource(Ingredient ingredient) {
    this.name = ingredient.getName();
    this.type = ingredient.getType();
  }
}
