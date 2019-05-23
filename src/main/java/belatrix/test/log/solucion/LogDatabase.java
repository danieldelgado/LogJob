package belatrix.test.log.solucion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import belatrix.test.log.solucion.util.ConstantesLog;
import belatrix.test.log.solucion.util.LogLevel;
import belatrix.test.log.solucion.util.LogUtil;

class LogDatabase implements Log {

	private final static String SQL_INSERT = "insert into Log_Values ( mensage, type ) values ('%s', '%s')";
	private static Statement stmt;

	static {
		String URL_CONNECT = LogUtil.getValEnv(ConstantesLog.URL_DATABASE);
		String CONNECT_USER = LogUtil.getValEnv(ConstantesLog.CONNECT_USER);
		String CONNECT_PASSWORD = LogUtil.getValEnv(ConstantesLog.CONNECT_PASSWORD);
		String CONNECT_DRIVER = LogUtil.getValEnv(ConstantesLog.CONNECT_DRIVER);
		try {
			Class.forName(CONNECT_DRIVER);
			Properties connectionProps = new Properties();
			connectionProps.put("user", CONNECT_USER);
			connectionProps.put("password", CONNECT_PASSWORD);
			Connection connection = DriverManager.getConnection(URL_CONNECT, connectionProps);
			stmt = connection.createStatement();
			stmt.executeUpdate("create table Log_Values (mensage VARCHAR(255), type VARCHAR(255))");
		} catch (ClassNotFoundException | SQLException e) {
			System.err.println("Error en conexion con base de datos:" + e.getMessage());
		}
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
		try {
			stmt.executeUpdate(String.format(SQL_INSERT, message, type.name()));
		} catch (SQLException e) {
			System.err.println("Error registrar en base de datos :" + e.getMessage());
		}
	}

}