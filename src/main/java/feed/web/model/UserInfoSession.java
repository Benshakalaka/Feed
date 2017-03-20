package feed.web.model;

/**
 * 用户信息Session
 * 在用户登录后存储在Session中
 * @author Boxbox
 *
 */
public class UserInfoSession {
	/**
	 * 用户Id
	 */
	private int userId;
	
	/**
	 * 时间戳
	 */
	private long timeStamp;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}
	
}
