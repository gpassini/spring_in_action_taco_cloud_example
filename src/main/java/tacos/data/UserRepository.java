package tacos.data;

import java.util.UUID;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import reactor.core.publisher.Mono;
import tacos.User;

public interface UserRepository extends ReactiveCassandraRepository<User, UUID> {

  @AllowFiltering
  Mono<User> findByUsername(String username);
}
