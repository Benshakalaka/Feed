package feed.web.common.util;

import feed.web.common.ResponseEnum;
import feed.web.common.exception.FeedServiceException;

/**
 * Feed断言类
 * @author Boxbox
 *
 */
public class FeedAssert {
	
	public static void notNullOrEmpty(String ... strArray){
		for(String str : strArray){
			if(str==null || str.isEmpty()){
				throw new FeedServiceException(ResponseEnum.LOGICFAILED.getCode(), "string is null");
			}
		}
	}
}
