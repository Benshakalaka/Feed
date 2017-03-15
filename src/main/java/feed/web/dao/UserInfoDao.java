package feed.web.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import feed.web.model.po.UserInfoPo;

@Repository
public interface UserInfoDao {
	
	/**
	 * 添加用户
	 * @param po
	 */
	void add(UserInfoPo userInfo);
	
	/**
	 * 根据用户姓名查看用户信息
	 * @param userName
	 * @return 
	 */
	UserInfoPo get(@Param(value="userName") String userName);
	
	/**
	 * 根据用户Id修改用户信息
	 * @param userId
	 * @param po
	 */
	void update(@Param(value="_userId") int userId,@Param(value="userInfo") UserInfoPo userInfo);
}
