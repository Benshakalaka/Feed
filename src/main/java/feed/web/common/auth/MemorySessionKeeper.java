package feed.web.common.auth;

import java.util.concurrent.ConcurrentHashMap;

public class MemorySessionKeeper<K, V> implements SessionKeeper<K, V> {
	
	private ConcurrentHashMap<K, V> cacheMap;
	
	/**
	 * 	初始化大小
	 * @param initCapacity
	 */
	public MemorySessionKeeper(int initCapacity){
		cacheMap = new ConcurrentHashMap<>(initCapacity);
	}
	
	@Override
	public V get(K k) {
		return cacheMap.get(k);
	}

	@Override
	public void set(K k, V v) {
		cacheMap.put(k, v);
	}

	@Override
	public void remove(K k) {
		cacheMap.remove(k);
	}

}
