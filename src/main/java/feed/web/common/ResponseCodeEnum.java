package feed.web.common;

/**
 * 结果编码
 * @author Boxbox
 *
 */
public class ResponseCodeEnum {
	// 成功
	public final static ResponseCode SUCCESS = new ResponseCode(0, "success");
	// 数据库异常
	public final static ResponseCode DBFAILED = new ResponseCode(-1, "dbfailed");
	// 业务异常
	public final static ResponseCode LOGICFAILED = new ResponseCode(-2, "logicfailed");
	// 未知异常
	public final static ResponseCode UNCATCHED = new ResponseCode(-3, "uncatched");
}
