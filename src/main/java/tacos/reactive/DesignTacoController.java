package tacos.reactive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tacos.Taco;
import tacos.data.TacoRepository;

@RestController
@RequestMapping(path = "/design", produces = "application/json")
@CrossOrigin(origins = "*")
public class DesignTacoController {

  private final TacoRepository tacoRepo;

  @Autowired
  public DesignTacoController(TacoRepository tacoRepo) {
    this.tacoRepo = tacoRepo;
  }

  @GetMapping("/{id}")
  public Mono<Taco> tacoById(@PathVariable("id") Long id) {
    return this.tacoRepo.findById(id).map(Mono::just).orElseGet(Mono::empty);
  }

  @PostMapping(consumes = "application/json")
  @ResponseStatus(HttpStatus.CREATED)
  public Mono<Taco> postTaco(@RequestBody Mono<Taco> tacoMono) {
    return tacoMono.map(this.tacoRepo::save);
  }

  @GetMapping("/recent")
  public Flux<Taco> recentTacos() {
    return Flux.fromIterable(this.tacoRepo.findAll(PageRequest.of(0, 12)));
  }
}
