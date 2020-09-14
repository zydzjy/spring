package springSecurity.service.security;

public interface TestRedisService {
	
	public Object testPutStringWithTTL(String key,String value)
		throws Exception;
}
