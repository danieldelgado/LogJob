package belatrix.test.log.solucion;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import belatrix.test.log.solucion.util.LogLevel;
import belatrix.test.log.solucion.util.LogUtil;

class LogConsole implements Log {
	private static Logger logger;

	LogConsole(String name) {
		logger = Logger.getLogger(name);
		logger.setUseParentHandlers(false);
		ConsoleHandler ch = new ConsoleHandler();
		logger.addHandler(ch);
	}

	public void info(String message) {
		execute(message, LogLevel.MESSAGE);
	}

	public void warr(String message) {
		execute(message, LogLevel.WAR);
	}

	public void error(String message) {
		execute(message, LogLevel.ERROR);
	}

	private void execute(String message, LogLevel type) {
		logger.log(Level.INFO, LogUtil.getMessage(type, message));
	}
}