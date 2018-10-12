package tacos.data;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import tacos.Taco;

@RepositoryRestResource(collectionResourceRel = "tacos", path = "tacos")
public interface TacoRepository extends CrudRepository<Taco, Long> {

  Page<Taco> findAll(Pageable page);

}
