package feed.web.common.auth;

import feed.web.model.UserInfoSession;

/**
 * 这里需要判断时间的策略
 * @author Boxbox
 *
 */
public class TimeAuthStrategy implements AuthStrategy {
	// 过期时间为一天
	public final static long EXPIRETIME = 86400000L;
	
	@Override
	public boolean check(UserInfoSession session) {
		long timeStamp = session.getTimeStamp();
		return timeStamp > 0 && (System.currentTimeMillis() - timeStamp) < EXPIRETIME;
	}

}
