package my.likeaglow.fakedc.common.exceptions;

public abstract class BaseException extends Exception {

  /**
   * 
   */
  private static final long serialVersionUID = -7290303885006480673L;

  public BaseException() {
    // TODO Auto-generated constructor stub
  }

  public BaseException(String message) {
    super(message);
    // TODO Auto-generated constructor stub
  }

  public BaseException(Throwable cause) {
    super(cause);
    // TODO Auto-generated constructor stub
  }

  public BaseException(String message, Throwable cause) {
    super(message, cause);
    // TODO Auto-generated constructor stub
  }

  public BaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
    // TODO Auto-generated constructor stub
  }

}
