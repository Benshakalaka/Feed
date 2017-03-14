package feed.web.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import feed.web.common.ResponseEntity;
import feed.web.model.vo.UserInfoVo;

@RestController
@RequestMapping(value="/user")
public class UserInfoController {
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Integer> create(@RequestBody UserInfoVo user){
		return null;
	}
}
