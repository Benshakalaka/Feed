package feed.web.model.po;

/**
 * 
 * @author Boxbox
 *
 */
public class MsgRelationPo {
	
	/**
	 * 引用消息用户编号
	 */
	private Integer referenceId;
	
	/**
	 * 引用消息编号
	 */
	private Integer referenceMsgId;
	
	/**
	 * 消息发布者编号
	 */
	private Integer referencedId;
	
	/**
	 * 被引用消息编号
	 */
	private Integer referenecedMsgId;
	
	/**
	 * 消息类型 1评论 2转发
	 */
	private Integer type;
	
	/**
	 * 发布时间
	 */
	private Integer timeStamp;
	
	/**
	 * 转发或评论页码
	 */
	private Integer pageIndex;
	
	public MsgRelationPo(){}

	public Integer getReferenceId() {
		return referenceId;
	}

	public void setReferenceId(Integer referenceId) {
		this.referenceId = referenceId;
	}

	public Integer getReferenceMsgId() {
		return referenceMsgId;
	}

	public void setReferenceMsgId(Integer referenceMsgId) {
		this.referenceMsgId = referenceMsgId;
	}

	public Integer getReferencedId() {
		return referencedId;
	}

	public void setReferencedId(Integer referencedId) {
		this.referencedId = referencedId;
	}

	public Integer getReferenecedMsgId() {
		return referenecedMsgId;
	}

	public void setReferenecedMsgId(Integer referenecedMsgId) {
		this.referenecedMsgId = referenecedMsgId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Integer timeStamp) {
		this.timeStamp = timeStamp;
	}

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}
	
}
