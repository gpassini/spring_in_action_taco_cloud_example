package tacos.data;

import java.util.UUID;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import tacos.Taco;

public interface TacoRepository extends ReactiveCassandraRepository<Taco, UUID> {

}
