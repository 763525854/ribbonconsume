package ribbonconsume.command.test;

import com.netflix.hystrix.contrib.javanica.command.AsyncResult;

import io.netty.util.concurrent.Future;
import ribbonconsume.command.UserCommand;
import ribbonconsume.model.User;

public class UserCommandImpl {
	// 同步方法调用
	public void sync() {
		User user = new UserCommand(null, null, 1L).execute();
	}

	// 异步方法调用
	public void notsync() {
		Future<User> futureUser = (Future<User>) new UserCommand(null, null, 1L).queue();
	}

	public AsyncResult<User> async() {
		return new AsyncResult<User>() {
			@Override
			public User invoke() {
				return null;
			}

		};
	}
}
