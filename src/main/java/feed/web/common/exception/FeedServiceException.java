package feed.web.common.exception;

public class FeedServiceException extends FeedBaseException {

	private static final long serialVersionUID = 3163205642383140894L;

	public FeedServiceException(int errorCode, String errorMsg) {
		super(errorCode, errorMsg);
	}

}
