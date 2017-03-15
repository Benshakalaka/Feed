package feed.web.common;

public class ResponseEntity<T> {
	
    private T data;
	
	private ResponseCode code;
	
	public ResponseEntity(){}
	
	public ResponseEntity(T data, ResponseCode code){
		this.data = data;
		this.code = code;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public ResponseCode getCode() {
		return code;
	}

	public void setCode(ResponseCode code) {
		this.code = code;
	}

}
