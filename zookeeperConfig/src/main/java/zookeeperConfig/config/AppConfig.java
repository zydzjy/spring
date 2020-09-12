package zookeeperConfig.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@RefreshScope
@Component
public class AppConfig {
	
	@Value("${someProp}")
	private String testPropFromZK;

	public String getTestPropFromZK() {
		return testPropFromZK;
	}

	public void setTestPropFromZK(String testPropFromZK) {
		this.testPropFromZK = testPropFromZK;
	}
}
