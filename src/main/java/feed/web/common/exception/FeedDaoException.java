package feed.web.common.exception;

public class FeedDaoException extends FeedBaseException {

	private static final long serialVersionUID = 4280312206188105054L;

	public FeedDaoException(int errorCode, String errorMsg) {
		super(errorCode, errorMsg);
	}

}
