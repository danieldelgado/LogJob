package belatrix.test.log;

import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class JobLoggerTest {

	@Test(expected = NullPointerException.class)
	public void createJobLogger_error_nullpointer() throws Exception {
		boolean logToFileParam = false;
		boolean logToConsoleParam = false;
		boolean logToDatabaseParam = false;
		boolean logMessageParam = false;
		boolean logWarningParam = false;
		boolean logErrorParam = false;
		Map<String, String> dbParamsMap = null;
		new JobLogger(logToFileParam, logToConsoleParam, logToDatabaseParam, logMessageParam, logWarningParam, logErrorParam, dbParamsMap);
		boolean message = false;
		boolean warning = false;
		boolean error = false;
		JobLogger.LogMessage(null, message, warning, error);
	}

	@Test()
	public void createJobLogger_ok_write_console() {
		boolean logToFileParam = false;
		boolean logToConsoleParam = true;
		boolean logToDatabaseParam = false;
		
		boolean logMessageParam = true;
		boolean logWarningParam = true;
		boolean logErrorParam = true;
		
		Map<String, String> dbParamsMap = new HashMap<String, String>();
		dbParamsMap.put("userName", "sa");
		dbParamsMap.put("password", "s$cret");
		dbParamsMap.put("URI_CONNECTION", "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
		dbParamsMap.put("logFileFolder", "C:\\data\\batch\\log");
		new JobLogger(logToFileParam, logToConsoleParam, logToDatabaseParam, logMessageParam, logWarningParam, logErrorParam, dbParamsMap);
		boolean message = true;
		boolean warning = false;
		boolean error = false;
		try {
			JobLogger.LogMessage("mensaje test", message, warning, error);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Error en log message:".concat(e.getMessage()));
		}
	}

}
