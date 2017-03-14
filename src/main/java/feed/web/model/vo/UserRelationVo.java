package feed.web.model.vo;

/**
 * 用户关系PO
 * @author Boxbox
 *
 */
public class UserRelationVo {
	
	/**
	 * 用户Id
	 */
	private Integer userId;
	
	/**
	 * 被关系者Id
	 */
	private Integer followId;
	
	/**
	 * 关系类型 0粉丝 1关注
	 * example: A关注B
	 * 语义：拥有关系
	 * A - B 1
	 * B - A 0
	 */
	private Integer type;
	
	public UserRelationVo(){}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getFollowId() {
		return followId;
	}

	public void setFollowId(Integer followId) {
		this.followId = followId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
}
