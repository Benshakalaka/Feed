package feed.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import feed.web.common.ResponseEntity;
import feed.web.model.vo.UserInfoVo;
import feed.web.service.UserInfoService;

@RestController
@RequestMapping(value="/user")
public class UserInfoController {
	
	@Autowired
	private UserInfoService userService;
	
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Integer> create(@RequestBody UserInfoVo user){
		userService.addUser(user);
		return new ResponseEntity<Integer>(0, 0, "createSuccess");
	}
}
