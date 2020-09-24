package springWebFlux.controller;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import dev.miku.r2dbc.mysql.MySqlConnectionConfiguration;
import dev.miku.r2dbc.mysql.MySqlConnectionFactory;
import io.r2dbc.pool.ConnectionPool;
import io.r2dbc.pool.ConnectionPoolConfiguration;
import io.r2dbc.spi.Connection;
import io.r2dbc.spi.ConnectionFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import springWebFlux.bean.Person;
import springWebFlux.service.PersonService;

@Controller
@RequestMapping("/jdbcReactive")
public class JdbcReactiveController {
//	MySqlConnectionConfiguration configuration = MySqlConnectionConfiguration.builder().host("localhost").port(3306)
//			.database("testdb").username("root").password("12345678").build();
//	ConnectionFactory connectionFactory = MySqlConnectionFactory.from(configuration);
//	ConnectionPoolConfiguration poolConfiguration = ConnectionPoolConfiguration.builder(connectionFactory)
//			   .maxIdleTime(Duration.ofMillis(1000))
//			   .maxSize(120)
//			   .build();
//	ConnectionPool pool = new ConnectionPool(poolConfiguration);
			 
	 
	@RequestMapping(path="findAll",method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Flux<Person>> findAll() {
//		// Creating a Mono using Project Reactor
//		Mono<Connection> connectionMono = Mono.from(connectionFactory.create());
//		Flux<String> e = connectionMono
//				.flatMapMany(
//						connection ->
//						 connection.createStatement("SELECT col1 FROM table1 order by col1 asc").execute()
//						 )
//				.flatMap(result -> result.map((row, rowMetadata) -> row.get("col1", String.class)))
//				;
//		connectionMono.subscribe().dispose();
		Flux<Person> e = personServiceImpl.findAll();
		HttpStatus status = e != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
		return new ResponseEntity<Flux<Person>>(e, status);
	}
	
	@Autowired private PersonService personServiceImpl; 
}