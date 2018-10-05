package tacos.data;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.CrudRepository;

import tacos.Taco;

public interface TacoRepository extends CrudRepository<Taco, Long> {

	List<Taco> findAll(PageRequest page);

}
