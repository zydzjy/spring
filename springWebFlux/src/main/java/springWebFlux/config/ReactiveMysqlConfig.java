package springWebFlux.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;

@Configuration
@EnableTransactionManagement
@EnableR2dbcRepositories(basePackages 
		= "springWebFlux.dao")
public class ReactiveMysqlConfig extends AbstractR2dbcConfiguration {
	
	@Bean
	@Override
	public ConnectionFactory connectionFactory() {
		ConnectionFactory connectionFactory = ConnectionFactories.get(
			    "r2dbc:pool:mysql://root:12345678@127.0.0.1:3306/testdb");
		return connectionFactory;
	}
}
