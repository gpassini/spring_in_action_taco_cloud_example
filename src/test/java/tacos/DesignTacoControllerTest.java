package tacos;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import tacos.data.TacoRepository;
import tacos.reactive.DesignTacoController;

public class DesignTacoControllerTest {

  @Test
  public void shoudlReturnRecentTacos() {
    Taco[] tacos = {testTaco(1L), testTaco(2L), testTaco(3L), testTaco(4L), testTaco(5L),
        testTaco(6L), testTaco(7L), testTaco(8L), testTaco(9L), testTaco(10L), testTaco(11L),
        testTaco(12L), testTaco(13L), testTaco(14L), testTaco(15L), testTaco(16L)};
    Flux<Taco> tacoFlux = Flux.just(tacos);

    TacoRepository tacoRepo = mock(TacoRepository.class);
    when(tacoRepo.findAll()).thenReturn(tacoFlux);

    WebTestClient testClient =
        WebTestClient.bindToController(new DesignTacoController(tacoRepo)).build();

    testClient
        .get()
        .uri("/design/recent")
        .exchange()
        .expectStatus()
        .isOk()
        .expectBodyList(Taco.class)
        .contains(Arrays.copyOf(tacos, 12));
  }

  private Taco testTaco(Long number) {
    Taco taco = new Taco();
    taco.setId(number);
    taco.setName("Taco " + number);
    List<Ingredient> ingredients = new ArrayList<>();
    ingredients.add(new Ingredient("INGA", "Ingredient A", Ingredient.Type.WRAP));
    ingredients.add(new Ingredient("INGB", "Ingredient B", Ingredient.Type.PROTEIN));
    taco.setIngredients(ingredients);
    return taco;
  }
}
