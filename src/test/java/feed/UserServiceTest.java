package feed;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import feed.web.model.vo.UserInfoVo;
import feed.web.service.UserInfoService;

public class UserServiceTest extends SpringTestBase {
	
	@Autowired
	private UserInfoService userInfoService;
	
	@Test
	@Rollback(true)
	public void addUser(){
		UserInfoVo user = new UserInfoVo();
		user.setUserName("yi");
		user.setUserId(2);
		userInfoService.add(user);
	}
}
