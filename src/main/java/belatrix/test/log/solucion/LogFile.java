package belatrix.test.log.solucion;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import belatrix.test.log.solucion.util.ConstantesLog;
import belatrix.test.log.solucion.util.LogLevel;
import belatrix.test.log.solucion.util.LogUtil;

class LogFile implements Log {
	private static Logger logger;

	LogFile(String name) {
		String log_folder = LogUtil.getValEnv(ConstantesLog.FOLDER_FILE_LOG);
		try {
			FileHandler fh = new FileHandler(log_folder + "/logFile.txt");
			logger = Logger.getLogger(name);
			logger.setUseParentHandlers(false);
			logger.addHandler(fh);
		} catch (SecurityException | IOException e) {
			throw new IllegalArgumentException("Ruta no es correctamente establecida : " + log_folder);
		}
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