package ribbonconsume;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableDiscoveryClient
// 开启断路器功能
@EnableCircuitBreaker
// 该标签包含@SpringBootApplication,@EnableDiscoveryClient,@EnableCircuitBreaker
//即SpringCloud服务默认是包含服务发现，断路器，springBoot三个选项的。
// @SpringCloudApplication
@SpringBootApplication(scanBasePackages = "ribbonconsume")
public class RibbonConsumer {
	@Bean
	@LoadBalanced
	RestTemplate restTemplate() {
		return new RestTemplate();
	};

	public static void main(String[] args) {
		SpringApplication.run(RibbonConsumer.class, args);
	}
}
