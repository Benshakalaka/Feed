package feed.web.dao;

public interface BaseDao<T>{
	
	long add(T t);
	
	long delete(T t);
	
	long update(T t);
	
	long get(T t);
}
