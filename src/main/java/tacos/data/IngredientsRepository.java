package tacos.data;

import org.springframework.data.repository.CrudRepository;
import tacos.Ingredient;

public interface IngredientsRepository extends CrudRepository<Ingredient, String> {

}
