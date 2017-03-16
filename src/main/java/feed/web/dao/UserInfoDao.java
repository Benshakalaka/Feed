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
	
	
	/**
	 * 粉丝数累加
	 * @param userId
	 */
	void fansIncrement(@Param(value="userId") int userId);
	
	/**
	 * 粉丝数递减
	 */
	void fansDecrement(@Param(value="userId") int userId);
	
	/**
	 * 关注数递增
	 * @param userId
	 */
	void followIncrement(@Param(value="userId") int userId);
	
	/**
	 * 关注数递减
	 * @param userId
	 */
	void followDecrement(@Param(value="userId") int userId);
	
}
