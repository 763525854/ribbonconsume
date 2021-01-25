package ribbonconsume.command;

import org.springframework.web.client.RestTemplate;
import com.netflix.hystrix.HystrixCommand;
import ribbonconsume.model.User;

public class UserCommand extends HystrixCommand<User> {
	private RestTemplate restTemplate;
	private long id;

	public UserCommand(Setter setter, RestTemplate restTemplate, Long id) {
		super(setter);
		this.restTemplate = restTemplate;
		this.id = id;
	}

	@Override
	protected User run() throws Exception {
		return restTemplate.getForObject("http://USER-SERVICE/user/{1}", User.class, id);
	}
	
}
