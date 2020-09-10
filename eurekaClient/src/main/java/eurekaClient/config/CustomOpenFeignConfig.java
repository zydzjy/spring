package eurekaClient.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages="eurekaClient.controller.feign")
public class CustomOpenFeignConfig {
	 //使用Feign自己的注解，使用springmvc的注解就会报错
	/*
	 * @Bean public Contract feignContract() { return new feign.Contract.Default();
	 * }
	 */
}