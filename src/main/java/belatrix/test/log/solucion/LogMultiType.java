package belatrix.test.log.solucion;

import java.util.Arrays;
import java.util.List;

public class LogMultiType implements Log {

	private List<Log> log;

	public LogMultiType(Log... log) {
		if (log == null || log.length == 0) {
			throw new IllegalArgumentException("Algorithms collection cann't be null!");
		}
		this.log = Arrays.asList(log);
	}

	public void info(String message) {
		log.forEach(l -> {
			l.info(message);
		});
	}

	public void warr(String message) {

	}

	public void error(String message) {

	}

}
