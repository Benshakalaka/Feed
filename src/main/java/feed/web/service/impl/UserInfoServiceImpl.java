package feed.web.service.impl;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import feed.web.common.auth.LocalObtainer;
import feed.web.dao.UserInfoDao;
import feed.web.model.UserInfoSession;
import feed.web.model.po.UserInfoPo;
import feed.web.model.vo.UserInfoVo;
import feed.web.service.UserInfoService;

@Service
public class UserInfoServiceImpl implements UserInfoService {

	@Autowired
	private UserInfoDao userInfoDao;

	private LocalObtainer obtainer = LocalObtainer.getInstance();

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

	@Override
	public String login(int userId, String password) {
		// 暂时不做任何业务逻辑判断 直接将userId生成
		String loginToken = null;
		UserInfoSession session = new UserInfoSession();
		session.setUserId(userId);
		session.setTimeStamp(System.nanoTime());
		
		// 生成loginToken
		loginToken = Jwts.builder().setSubject(JSON.toJSONString(session))
				.signWith(SignatureAlgorithm.HS256, obtainer.getKey())
				.compact();
		return loginToken;
	}

}
