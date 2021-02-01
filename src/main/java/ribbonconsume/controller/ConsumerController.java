package ribbonconsume.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import ribbonconsume.service.HelloService;

@RestController
public class ConsumerController {
	@Autowired
	HelloService helloService;
	private LoadBalancerClient loadBalancer;
	@Autowired
	RestTemplate restTemplate;

	@RequestMapping(value = "/ribbon-consumer", method = RequestMethod.GET)
	public String helloConsumer() {
		helloService.helloService();
		return restTemplate.getForEntity("http://HELLO-SERVICE/hello", String.class).getBody();
	}

	@RequestMapping(value = "ribbon")
	public String testRibbon() {
		ServiceInstance instance = loadBalancer.choose("rhyme");
		return instance.getHost() + ":" + instance.getPort();
	}

}
