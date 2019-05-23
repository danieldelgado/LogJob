package belatrix.test.log.solucion.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.text.DateFormat;
import java.util.Date;

import org.junit.Test;

import belatrix.test.log.solucion.util.LogLevel;
import belatrix.test.log.solucion.util.LogUtil;

public class LogUtilTest {

	@Test(expected = NullPointerException.class)
	public void logUtil_isNullAndTrim_error_null() {
		LogUtil.isNullAndTrim(null);
	}

	@Test(expected = NullPointerException.class)
	public void logUtil_isNullAndTrim_error_space() {
		LogUtil.isNullAndTrim("");
	}

	@Test
	public void logUtil_isNullAndTrim_ok() {
		String h = LogUtil.isNullAndTrim("hola ");
		assertEquals(h, "hola");
	}

	@Test
	public void logUtil_getValEnv_null_ok() {
		String systemdata = LogUtil.getValEnv("systemdata");
		assertNull(systemdata);
	}

	@Test
	public void logUtil_getValEnv_ok() {
		System.setProperty("systemdata", "hola");
		String systemdata = LogUtil.getValEnv("systemdata");
		assertEquals(systemdata, "hola");
	}

	@Test
	public void logUtil_getMessage_message_ok() {
		String message = LogUtil.getMessage(LogLevel.MESSAGE, "hola");
		assertEquals(message, "message " + DateFormat.getDateInstance(DateFormat.LONG).format(new Date()) + " hola");
	}

	@Test
	public void logUtil_getMessage_war_ok() {
		String message = LogUtil.getMessage(LogLevel.WAR, "hola");
		assertEquals(message, "warning " + DateFormat.getDateInstance(DateFormat.LONG).format(new Date()) + " hola");
	}

	@Test
	public void logUtil_getMessage_error_ok() {
		String message = LogUtil.getMessage(LogLevel.ERROR, "hola");
		assertEquals(message, "error " + DateFormat.getDateInstance(DateFormat.LONG).format(new Date()) + " hola");
	}

}
