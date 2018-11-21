package tacos.data;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import tacos.Order;

public interface OrderRepository extends ReactiveMongoRepository<Order, String> {

}
