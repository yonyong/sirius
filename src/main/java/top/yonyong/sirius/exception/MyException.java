package top.yonyong.sirius.exception;

/**
 * @author yonyong
 */
public class MyException extends RuntimeException {
	private static final long serialVersionUID = -5026044920703857009L;
	/**
	 * 返回结果
	 */
	private Object result;

	public MyException(Object result) {
		this.result = result;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

}