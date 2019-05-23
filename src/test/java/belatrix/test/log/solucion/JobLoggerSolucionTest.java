package belatrix.test.log.solucion;

import org.junit.Test;

public class JobLoggerSolucionTest {

	@Test()
	public void createJobLogger_ok_write_console() {
		Log log = LogContextFactory.getLog(new LogMultiType(new LogConsole(), new LogFile()));
		log.info("test");

		System.out.println(System.getenv("SENDGRID_USERNAME"));//funciona
				
		
//		List<Log> strategies = Arrays.asList(new LogConsole(), new LogFile());
//		for (Log stg : strategies) {
//			stg.info("test");
//		}

//		for (Strategy stg : strategies) {
//			stg.performTask();
//		}
//		Log lg = Log.getInstate("sss", LogType.CONSOLE, LogType.FILE, LogType.BD);
//		lg.info("intento de message");
//		lg.warr("intento de war");
//		lg.error("intento de error");
	}

}
