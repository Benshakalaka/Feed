package feed.web.common.auth;

/**
 * Session存储介质
 * @author Boxbox
 *
 * @param <K>
 * @param <V>
 */
public interface SessionKeeper<K,V>{
	
	/**
	 * 获取Session
	 * @param k
	 * @return
	 */
	V get(K k);
	
	/**
	 * 存储Session
	 * @param k
	 * @param v
	 */
	void set(K k, V v);
	
	/**
	 * 移除Session
	 * @param k
	 */
	void remove(K k);
}
