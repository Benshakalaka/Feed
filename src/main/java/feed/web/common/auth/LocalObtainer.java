package feed.web.common.auth;

import feed.web.model.UserInfoSession;
import io.jsonwebtoken.impl.crypto.MacProvider;

import java.security.Key;

/**
 * 项目启动时维护的数据
 * @author Boxbox
 *
 */
public class LocalObtainer {
	
	/**
	 * 用于JWT使用的key
	 */
	private Key key = MacProvider.generateKey();
	
	/**
	 * 保存UserSession的ThreadLocal变量，生命周期为一次访问
	 */
	private ThreadLocal<UserInfoSession> localSession = new ThreadLocal<>();
	
	private LocalObtainer(){}
	
	private static class LocalObtainerHolder{
		private final static LocalObtainer instance = new LocalObtainer();
	}
	
	public static LocalObtainer getInstance(){
		return LocalObtainerHolder.instance;
	}
	
	public Key getKey(){
		return key;
	}
	
	/**
	 * 存储Session
	 * @param session
	 */
	public void putSession(UserInfoSession session){
		localSession.set(session);
	}
	
	/**
	 * 获取Session
	 * @return
	 */
	public UserInfoSession getSession(){
		return localSession.get();
	}
	
	/**
	 * 清除Session
	 */
	public void clearSession(){
		localSession.remove();
	}
}
