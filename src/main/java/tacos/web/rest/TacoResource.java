package tacos.web.rest;

import java.util.Date;
import java.util.List;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;
import lombok.Getter;
import tacos.Taco;

@Getter
@Relation(value = "taco", collectionRelation = "tacos")
public class TacoResource extends ResourceSupport {

  private static final IngredientResourceAssembler INGREDIENT_RESOURCE_ASSEMBLER =
      new IngredientResourceAssembler();

  private final String name;

  private final Date createdAt;

  private final List<IngredientResource> ingredients;

  public TacoResource(Taco taco) {
    this.name = taco.getName();
    this.createdAt = taco.getCreatedAt();
    this.ingredients = INGREDIENT_RESOURCE_ASSEMBLER.toResources(taco.getIngredients());
  }
}
