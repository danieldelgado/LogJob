package belatrix.test.log.solucion;

import java.util.List;

import belatrix.test.log.solucion.util.LogUtil;

class LogMultiType implements Log {

	private List<Log> log;

	LogMultiType(List<Log> log) {
		if (log == null || log.size() == 0) {
			throw new IllegalArgumentException("La lista de algoritmos log no puede ser null");
		}
		this.log = log;
	}

	public void info(String message) {
		LogUtil.isNullAndTrim(message);
		log.parallelStream().forEach(l -> {
			l.info(message);
		});
	}

	public void warr(String message) {
		LogUtil.isNullAndTrim(message);
		log.parallelStream().forEach(l -> {
			l.warr(message);
		});
	}

	public void error(String message) {
		LogUtil.isNullAndTrim(message);
		log.parallelStream().forEach(l -> {
			l.error(message);
		});
	}

}
