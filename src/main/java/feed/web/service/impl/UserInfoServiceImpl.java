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
	public void addUser(UserInfoVo userInfo) {
		UserInfoPo po = userInfo.toPo();
		userInfoDao.add(po);
	}

}
