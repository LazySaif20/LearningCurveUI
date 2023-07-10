package UIException;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;

public class UIException extends RuntimeException {

	private static final long serialVersionUID = -1216501584809953956L;

	public UIException(Exception e, Class<?> clazz) {
		super(e);
		if (!(e instanceof UIException)) {
			LogManager.getLogger(clazz.getSimpleName()).error(ExceptionUtils.getMessage(e));
		}
	}

	public UIException(String errorMsg, Class<?> clazz) {
		super(errorMsg);
		LogManager.getLogger(clazz.getSimpleName()).error(errorMsg);
	}

}
