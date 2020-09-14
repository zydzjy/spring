package springSecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;

@Configuration
public class RedisConfig {
	
	@Bean
	public LettuceConnectionFactory commonRedisConnectionFactory() {
		RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
		config.setDatabase(1);
		config.setHostName("localhost");
		config.setPort(6379);
		LettuceConnectionFactory factory = new LettuceConnectionFactory(config);
		return factory;
	}
	
	@Bean
	public ReactiveStringRedisTemplate commonStringRedisTemplate() {
		return new ReactiveStringRedisTemplate(commonRedisConnectionFactory());
	}
}