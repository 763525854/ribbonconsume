package ribbonconsume.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.ObservableExecutionMode;

@Service
public class HelloService {
	private final Logger logger = Logger.getLogger(getClass());
	@Autowired
	RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod = "helloFallBack")
	public String helloService() {
		//在调用超时的时候，先执行断路器内的代码。此时，调用可能会返回结果。
		long start = System.currentTimeMillis();
		String result = restTemplate.getForEntity("http://HELLO-SERVICE/hello/hello", String.class).getBody();
		long end = System.currentTimeMillis();
		logger.info("Speed Time:" + (end - start));
		System.out.println("[**********]"+result);
		return result.toString();
	}

	public String helloFallBack() {
		System.out.println("runing in helloFallBack");
		return "你好，你所访问的服务不存在，请稍后再访问";
	}
}
