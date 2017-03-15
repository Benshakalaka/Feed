package feed.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import feed.web.dao.UserInfoDao;
import feed.web.model.po.UserInfoPo;
import feed.web.model.vo.UserInfoVo;
import feed.web.service.UserInfoService;

@Service
public class UserInfoServiceImpl implements UserInfoService{
	
	@Autowired
	private UserInfoDao userInfoDao;
	
	@Override
	public void add(UserInfoVo userInfo) {
		UserInfoPo userPo = userInfo.toPo();
		userInfoDao.add(userPo);
	}

	@Override
	public UserInfoVo get(String userName) {
		UserInfoPo userPo = userInfoDao.get(userName);
		return userPo.toVo();
	}

	@Override
	public void update(int userId, UserInfoVo userInfo) {
		UserInfoPo userPo = userInfo.toPo();
		userInfoDao.update(userId, userPo);
	}

}
