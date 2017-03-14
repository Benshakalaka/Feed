package feed.web.common.exception;

/**
 * feed系统数据库异常
 * 
 * @author Boxbox
 * 
 */
public class FeedDaoException extends RuntimeException {

	private static final long serialVersionUID = -5324408538502217532L;

	private int errorCode;

	private String errorMsg;

	public FeedDaoException(int errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}

	public int getCode() {
		return errorCode;
	}
	
	public String getMsg(){
		return errorMsg;
	}

}
