// package tacos.reactive;
//
// import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
// import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
// import static org.springframework.web.reactive.function.server.RouterFunctions.route;
// import java.net.URI;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.web.reactive.function.server.RouterFunction;
// import org.springframework.web.reactive.function.server.ServerRequest;
// import org.springframework.web.reactive.function.server.ServerResponse;
// import reactor.core.publisher.Mono;
// import tacos.Taco;
// import tacos.data.TacoRepository;
//
// @Configuration
// public class RouterFunctionConfig {
//
// @Autowired
// private TacoRepository tacoRepo;
//
// @Bean
// public RouterFunction<?> routerFunction() {
// return route(GET("/design/recent"), this::recentTacos)
// .andRoute(GET("/design/{id}"), this::tacoById)
// .andRoute(POST("/design"), this::postTaco);
// }
//
// private Mono<ServerResponse> recentTacos(ServerRequest request) {
// return ServerResponse.ok().body(tacoRepo.findAll().take(12), Taco.class);
// }
//
// private Mono<ServerResponse> tacoById(ServerRequest request) {
// Long tacoId = Long.valueOf(request.pathVariable("id"));
// return ServerResponse.ok().body(tacoRepo.findById(tacoId), Taco.class);
// }
//
// private Mono<ServerResponse> postTaco(ServerRequest request) {
// Mono<Taco> taco = request.bodyToMono(Taco.class);
// Mono<Taco> savedTaco = tacoRepo.saveAll(taco).next();
// return ServerResponse
// .created(URI.create("http://localhost:8080/design/" + savedTaco.map(Taco::getId)))
// .body(savedTaco, Taco.class);
// }
// }
