package tacos.data;

import java.util.UUID;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import tacos.Order;

public interface OrderRepository extends ReactiveCassandraRepository<Order, UUID> {

}
