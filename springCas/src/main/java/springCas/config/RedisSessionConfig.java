package springCas.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds=1800,
	redisNamespace="spring:httpSession")
public class RedisSessionConfig {
	
	@Bean
	@Primary
	public LettuceConnectionFactory springSessionRedisConnectionFactory() {
		RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
		config.setDatabase(0);
		config.setHostName("localhost");
		config.setPort(6379);
		LettuceConnectionFactory factory = new LettuceConnectionFactory(config);
		return factory;
	}	
}