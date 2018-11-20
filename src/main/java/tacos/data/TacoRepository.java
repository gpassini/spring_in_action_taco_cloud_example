package tacos.data;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import tacos.Taco;

public interface TacoRepository extends CrudRepository<Taco, Long> {

  Page<Taco> findAll(Pageable pageable);

}
