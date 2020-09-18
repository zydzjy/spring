package springWebFlux.reactiveJdbc;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Observable;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dev.miku.r2dbc.mysql.MySqlConnectionConfiguration;
import dev.miku.r2dbc.mysql.MySqlConnectionFactory;
import io.r2dbc.spi.Connection;
import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import reactor.core.Disposable;
import reactor.core.publisher.Mono;

class QueryTest {
	ConnectionFactory connectionFactory;

	@BeforeEach
	void setUp() throws Exception {
		// Notice: the query string must be URL encoded
		MySqlConnectionConfiguration configuration = MySqlConnectionConfiguration.builder()
				.host("localhost")
				.port(3306)
				.database("testdb")
				.username("root")
				.password("12345678")
				.build();
		connectionFactory = MySqlConnectionFactory.from(configuration);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() {
		// Creating a Mono using Project Reactor
		Mono.from(connectionFactory.create())
				.flatMapMany(connection -> connection.createStatement("SELECT col1 FROM table1 WHERE col2 > 1")
						.execute())
				.flatMap(result -> result.map((row, rowMetadata) -> row.get("col1", String.class)))
				.doOnNext(col1 -> System.out.println("col1:"+col1))
				.blockFirst();
	}
}