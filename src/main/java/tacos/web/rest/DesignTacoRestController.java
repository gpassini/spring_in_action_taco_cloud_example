package tacos.web.rest;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import tacos.Taco;
import tacos.data.TacoRepository;

@RestController
@RequestMapping(path = "/design", produces = "application/json")
@CrossOrigin(origins = "*")
public class DesignTacoRestController {

  private TacoRepository tacoRepository;

  @Autowired
  public DesignTacoRestController(TacoRepository tacoRepository) {
    this.tacoRepository = tacoRepository;
  }

  @GetMapping("/recent")
  public Iterable<Taco> recentTacos() {
    // PageRequest page = PageRequest.of(0, 12, Sort.by("createdAt").descending());
    return tacoRepository.findAll();
  }

  @GetMapping("/{id}")
  public Taco tacoById(@PathVariable("id") Long id) {
    Optional<Taco> optTaco = tacoRepository.findById(id);
    if (optTaco.isPresent()) {
      return optTaco.get();
    }
    return null;
  }

  @PostMapping(consumes = "application/json")
  @ResponseStatus(HttpStatus.CREATED)
  public Taco postTaco(@RequestBody Taco taco) {
    return tacoRepository.save(taco);
  }
}
