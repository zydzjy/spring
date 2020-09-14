package springSecurity.service.security.impl;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;
import springSecurity.service.security.TestRedisService;

@Service("testRedisServiceImpl")
public class TestRedisServiceImpl implements TestRedisService {
	@Autowired
	private ReactiveStringRedisTemplate commonStringRedisTemplate;
	
	@Override
	public Object testPutStringWithTTL(String key, String value) throws Exception {
		
		Boolean result = commonStringRedisTemplate
				.opsForValue()
			.set(key, value, Duration.ofMillis(160000)).block();
		
		return result.toString();
	}
}