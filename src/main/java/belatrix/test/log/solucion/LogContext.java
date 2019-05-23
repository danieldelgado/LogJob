package belatrix.test.log.solucion;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import belatrix.test.log.solucion.util.ConstantesLog;
import belatrix.test.log.solucion.util.LogUtil;

public class LogContext {

	// Se utliza el patron singleton a demanda 
	private static LogMultiType LOGMULTITYPE_INSTANCE = null;
	
	private LogContext() {
		throw new IllegalArgumentException("No debe ser una instancia");
	}
	
	// Con context podemos utilizar patron strategy para definir distintos algoritmos con la misma interfaz
	public static Log getLog(String name) {
		if(null == LOGMULTITYPE_INSTANCE) {
			String types_configuration = LogUtil.getValEnv(ConstantesLog.LOG_TYPE_RUN);
			types_configuration = LogUtil.isNullAndTrim(types_configuration);
			String[] types = types_configuration.split(",");
			List<Log> listLog = Arrays.stream(types).map(t -> {
				Log l = null;
				switch (t) {
				case ConstantesLog.LOG_TYPE_CONSOLE:
					l = new LogConsole(name);
					break;
				case ConstantesLog.LOG_TYPE_FILE:
					l = new LogFile(name);
					break;
				case ConstantesLog.LOG_TYPE_DATABASE:
					l = new LogDatabase();
					break;
				}
				return l;
			}).collect(Collectors.toList());
			LOGMULTITYPE_INSTANCE = new LogMultiType(listLog);
		}
		return LOGMULTITYPE_INSTANCE;
	}

}
