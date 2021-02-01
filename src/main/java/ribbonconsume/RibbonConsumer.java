package ribbonconsume;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;

import ribbonconsume.configuraiton.AvoidScan;
import ribbonconsume.configuraiton.TestConfiguration;
import org.springframework.context.annotation.FilterType;

@EnableDiscoveryClient
// 开启断路器功能
@EnableCircuitBreaker
// 该标签包含@SpringBootApplication,@EnableDiscoveryClient,@EnableCircuitBreaker
// 即SpringCloud服务默认是包含服务发现，断路器，springBoot三个选项的。
// @SpringCloudApplication
// @RibbonClient(name="HELLO-SERVICE",configuration=TestConfiguration.class)
// @ComponentScan(excludeFilters=
// {@ComponentScan.Filter(type=FilterType.ANNOTATION,value= {AvoidScan.class})})
@SpringBootApplication
public class RibbonConsumer {
	@Bean
	@LoadBalanced
	RestTemplate restTemplate() {
		return new RestTemplate();
	};

	@Bean
	public IRule ribbonRule() {
		return new RandomRule();// 这里配置策略，和配置文件对应
	}

	public static void main(String[] args) {
		SpringApplication.run(RibbonConsumer.class, args);
	}

}
