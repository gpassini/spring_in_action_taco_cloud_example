package tacos.web.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tacos.Taco;
import tacos.data.TacoRepository;

@RestController
@RequestMapping(path = "/design", produces = "application/json")
@CrossOrigin(origins = "*")
public class DesignTacoController {

	private TacoRepository tacoRepository;

	@Autowired
	public DesignTacoController(TacoRepository tacoRepository) {
		this.tacoRepository = tacoRepository;
	}

	@GetMapping("/recent")
	public List<Taco> recentTacos() {
		PageRequest page = PageRequest.of(0, 12, Sort.by("createdAt").descending());
		return tacoRepository.findAll(page);
	}

	@GetMapping("/{id}")
	public Taco tacoById(@PathVariable("id") Long id) {
		Optional<Taco> optTaco = tacoRepository.findById(id);
		if (optTaco.isPresent()) {
			return optTaco.get();
		}
		return null;
	}
}
