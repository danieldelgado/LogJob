package belatrix.test.log.solucion.util;

import java.text.DateFormat;
import java.util.Date;

import org.h2.util.StringUtils;

public class LogUtil {

	public static String isNullAndTrim(String data) {
		if (StringUtils.isNullOrEmpty(data)) {
			throw new NullPointerException("data no puede ser null");
		}
		return data.trim();
	}

	public static String getValEnv(String key) {
		String variable_entorno = System.getenv(key);
		if (StringUtils.isNullOrEmpty(variable_entorno))
			variable_entorno = System.getProperty(key);
		return variable_entorno;
	}

	public static String getMessage(LogLevel type, String message) {
		StringBuilder sb = new StringBuilder();
		switch (type) {
		case MESSAGE:
			sb.append("message ");
			break;
		case WAR:
			sb.append("warning ");
			break;
		case ERROR:
			sb.append("error ");
			break;
		}
		sb.append(DateFormat.getDateInstance(DateFormat.LONG).format(new Date()));
		sb.append(" ");
		sb.append(message);
		return sb.toString();
	}
}
