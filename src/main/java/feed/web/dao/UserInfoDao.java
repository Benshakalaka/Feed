package feed.web.dao;

import org.mybatis.spring.annotation.MapperScan;

import feed.web.model.po.UserInfoPo;

@MapperScan
public interface UserInfoDao extends BaseDao<UserInfoPo>{
	
}
