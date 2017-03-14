package feed.web.model.vo;

/**
 * 用户消息索引
 * @author Boxbox
 *
 */
public class UserMsgIndexVo {
	
	/**
	 * 用户Id
	 */
	private Integer userId;
	
	/**
	 * 原作者Id
	 */
	private Integer authId;
	
	/**
	 * 消息Id 原作者自增长
	 */
	private Integer msgId;
	
	/**
	 * 发布时间  元消息数据发布时间
	 */
	private Integer timeStamp;
	
	public UserMsgIndexVo(){}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getAuthId() {
		return authId;
	}

	public void setAuthId(Integer authId) {
		this.authId = authId;
	}

	public Integer getMsgId() {
		return msgId;
	}

	public void setMsgId(Integer msgId) {
		this.msgId = msgId;
	}

	public Integer getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Integer timeStamp) {
		this.timeStamp = timeStamp;
	}

	
}
