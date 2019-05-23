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
		logger.log(Level.INFO, LogUtil.getMessage(LogLevel.MESSAGE, message));
	}

	public void warr(String message) {
		logger.log(Level.WARNING, LogUtil.getMessage(LogLevel.WAR, message));
	}

	public void error(String message) {
		logger.log(Level.INFO, LogUtil.getMessage(LogLevel.ERROR, message));
	}

}