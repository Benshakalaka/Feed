package feed.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import feed.web.model.po.UserRelationPo;

/**
 * 用户关系Dao
 * @author Boxbox
 *
 */
@Repository
public interface UserRelationDao {
	
	/**
	 * 添加用户关系
	 * @param userRelation
	 */
	void add(UserRelationPo userRelation);
	
	/**
	 * 删除用户关系
	 * @param userRelation
	 */
	void delete(UserRelationPo userRelation);
	
	/**
	 * 获取粉丝列表
	 * @param userId
	 * @return
	 */
	List<Integer> getFansList(@Param(value="userId") int userId);
	
	/**
	 * 获取关注列表
	 * @param userId
	 * @return
	 */
	List<Integer> getFollowList(@Param(value="userId") int userId);
}
