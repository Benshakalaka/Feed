package feed.web.service;

import org.springframework.stereotype.Service;

import feed.web.model.vo.UserInfoVo;

/**
 * 用户信息Service
 * @author Boxbox
 *
 */
@Service
public interface UserInfoService {
	
	/**
	 * 添加用户
	 * @param userInfo
	 */
	void add(UserInfoVo userInfo);
	
	/**
	 * 获取用户信息
	 * @param userName
	 */
	UserInfoVo get(String userName);
	
	
	/**
	 * 更新用户属性
	 * @param userId
	 * @param userInfo
	 */
	void update(int userId, UserInfoVo userInfo);
}
