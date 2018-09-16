import com.wu.store.bean.User;
import com.wu.store.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class test
{
	@Autowired
	UserService userService;

	@Test
	public void test1() throws Exception
	{
		User user = new User();
		user.setUid(UUID.randomUUID().toString());
		user.setName("wawa");
		userService.addUser(user);
	}
}
