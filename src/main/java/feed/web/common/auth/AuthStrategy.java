package feed.web.common.auth;

import feed.web.model.UserInfoSession;

/**
 * 验证授权的策略
 * @author Boxbox
 *
 */
public interface AuthStrategy {
	
	
	/**
	 * 检查Session是否是合法的状态
	 * @param session
	 * @return
	 */
	boolean check(UserInfoSession session);
	
}
