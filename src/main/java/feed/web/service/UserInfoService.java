package feed.web.service;

import org.springframework.stereotype.Service;

import feed.web.model.vo.UserInfoVo;

@Service
public interface UserInfoService {
	void addUser(UserInfoVo userInfo);
}
