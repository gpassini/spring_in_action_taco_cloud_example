package tacos.data;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import tacos.Ingredient;

public interface IngredientsRepository extends ReactiveCrudRepository<Ingredient, String> {

}
