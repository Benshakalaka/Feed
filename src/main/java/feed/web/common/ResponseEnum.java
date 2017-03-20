package feed.web.common;

import com.alibaba.fastjson.JSON;

/**
 * 结果编码
 * @author Boxbox
 *
 */
public class ResponseEnum {
	// 成功
	public final static ResponseCode SUCCESS = new ResponseCode(0, "success");
	// 数据库异常
	public final static ResponseCode DBFAILED = new ResponseCode(-1, "dbfailed");
	// 业务异常
	public final static ResponseCode LOGICFAILED = new ResponseCode(-2, "logicfailed");
	// 未知异常
	public final static ResponseCode UNCATCHED = new ResponseCode(-3, "uncatched");
	// 授权异常
	public final static ResponseCode AUTHFAILED = new ResponseCode(-4, "authfailed");
	
	public final static ResponseEntity<Void> VOID_SUCCESS = new ResponseEntity<Void>(null, SUCCESS);
	public final static ResponseEntity<Void> VOID_DBFAILED = new ResponseEntity<Void>(null, DBFAILED);
	public final static ResponseEntity<Void> VOID_LOGICFAILED = new ResponseEntity<Void>(null, LOGICFAILED);
	public final static ResponseEntity<Void> VOID_UNCATCHED = new ResponseEntity<Void>(null, UNCATCHED);
	public final static ResponseEntity<Void> VOID_AUTHFAILED = new ResponseEntity<Void>(null, AUTHFAILED);
	
	public final static String VOID_AUTHFAILED_JSON = JSON.toJSONString(VOID_AUTHFAILED);
}
