package belatrix.test.log.solucion.test;

import org.junit.Test;

import belatrix.test.log.solucion.Log;
import belatrix.test.log.solucion.LogContext;

public class JobLoggerSolucionTest {

	@Test()
	public void createJobLogger_ok_write_console() {
		System.setProperty("LOG_TYPE_RUN", "CONSOLE");
		Log lg = LogContext.getLog("test");
		lg.info("intento de message");
		lg.warr("intento de war");
		lg.error("intento de error");
	}

	@Test()
	public void createJobLogger_ok_write_file() {
		System.setProperty("LOG_TYPE_RUN", "FILE");
		System.setProperty("FOLDER_FILE_LOG", "C:/data/");
		Log lg = LogContext.getLog("test");
		lg.info("intento de message");
		lg.warr("intento de war");
		lg.error("intento de error");
	}

	@Test()
	public void createJobLogger_ok_write_database() {
		System.setProperty("LOG_TYPE_RUN", "DATABASE");
		System.setProperty("URL_DATABASE", "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
		System.setProperty("CONNECT_DRIVER", "org.h2.Driver");
		System.setProperty("CONNECT_USER", "sa");
		System.setProperty("CONNECT_PASSWORD", "s$cret");
		Log lg = LogContext.getLog("test");
		lg.info("intento de message");
		lg.warr("intento de war");
		lg.error("intento de error");
	}

	@Test()
	public void createJobLogger_ok_write_all() {
		System.setProperty("LOG_TYPE_RUN", "FILE,CONSOLE,DATABASE");
		System.setProperty("FOLDER_FILE_LOG", "C:/data/");
		System.setProperty("URL_DATABASE", "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
		System.setProperty("CONNECT_DRIVER", "org.h2.Driver");
		System.setProperty("CONNECT_USER", "sa");
		System.setProperty("CONNECT_PASSWORD", "s$cret");
		Log lg = LogContext.getLog("test");
		lg.info("intento de message");
		lg.warr("intento de war");
		lg.error("intento de error");
	}

}