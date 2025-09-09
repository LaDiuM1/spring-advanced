package study.spring_advanced.proxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import study.spring_advanced.proxy.config.AppV0Config;
import study.spring_advanced.proxy.config.AppV1Config;
import study.spring_advanced.proxy.config.AppV2Config;

@Import({ AppV0Config.class, AppV1Config.class, AppV2Config.class })
@SpringBootApplication(scanBasePackages = {
		"study.spring_advanced.proxy.trace"
})
public class ProxyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProxyApplication.class, args);
	}

}
