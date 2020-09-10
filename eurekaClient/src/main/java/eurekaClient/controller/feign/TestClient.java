package eurekaClient.controller.feign;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name="eurekaClient")
public interface TestClient {
	@RequestMapping(value="/test2")
    public Map<String,Object> test2();
}