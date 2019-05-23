package belatrix.test.log.solucion.util;

import org.h2.util.StringUtils;

class LogUtil {

	public static String isNullAndTrim(String message) {
		if(StringUtils.isNullOrEmpty(message)) {
			throw new NullPointerException("mensaje no puede ser null");
		}	
		return message.trim();
	}

}
