package belatrix.test.log;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Properties;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JobLogger {
	// cuando se instancie un objeto de clase tendra valores estaticos que pueden
	// cambiar durante el tiempo de ejecucion
	private static boolean logToFile;
	private static boolean logToConsole;
	private static boolean logMessage;
	private static boolean logWarning;
	private static boolean logError;
	private static boolean logToDatabase;
	private boolean initialized; // variable sin utilizar
	private static Map dbParams; // no cuenta con la definicion de tipos de datos
	private static Logger logger;

	public JobLogger(boolean logToFileParam, boolean logToConsoleParam, boolean logToDatabaseParam, boolean logMessageParam, boolean logWarningParam, boolean logErrorParam, Map dbParamsMap) {
		logger = Logger.getLogger("MyLog");
		logError = logErrorParam;
		logMessage = logMessageParam;
		logWarning = logWarningParam;
		logToDatabase = logToDatabaseParam;
		logToFile = logToFileParam;
		logToConsole = logToConsoleParam;
		dbParams = dbParamsMap;
	}

	// los metodos no pueden empezar con letra mayuscula o seguir el lineamiento de
	// camel case en el nombrado de metodos y/o variables
	public static void LogMessage(String messageText, boolean message, boolean warning, boolean error) throws Exception {// como log no deberia arrojar exception checked. Deberia arrojar unchecked
		messageText.trim(); // ERROR DE VARIALE PUEDE SER NULL
		if (messageText == null/* NO ENTRARA POR LA LINEA ANTERIOR */ || messageText.length() == 0) {
			return;
		}
		if (!logToConsole && !logToFile && !logToDatabase) {
			throw new Exception("Invalid configuration");
		}
		if ((!logError && !logMessage && !logWarning) || (!message && !warning && !error)) {
			throw new Exception("Error or Warning or Message must be specified");
		}

		Connection connection = null;
		Properties connectionProps = new Properties();
		connectionProps.put("user", dbParams.get("userName")); // ERROR DE VARIALE PUEDE SER NULL
		connectionProps.put("password", dbParams.get("password"));

		// no contaba con la carga de driver de conexion
		// la conexion debe estar en otra instancia unica y un pool de conexiones. No se
		// debe generar la conexion cada vez que se desea insertar.
		connection = DriverManager.getConnection("jdbc:" + dbParams.get("dbms") + "://" + dbParams.get("serverName") + ":" + dbParams.get("portNumber") + "/testdb;AUTO_SERVER=TRUE", connectionProps);

		int t = 0;

		// A
		if (message && logMessage) {
			t = 1;
		}

		if (error && logError) {
			t = 2;
		}

		if (warning && logWarning) {
			t = 3;
		}
		// A
		Statement stmt = connection.createStatement();

		String l = null;// esta variable no es utilizada
		File logFile = new File(dbParams.get("logFileFolder") + "/logFile.txt");
		if (!logFile.exists()) {
			logFile.createNewFile();
		}

		FileHandler fh = new FileHandler(dbParams.get("logFileFolder") + "/logFile.txt");
		ConsoleHandler ch = new ConsoleHandler();
		// B
		if (error && logError) {
			l = l + "error " + DateFormat.getDateInstance(DateFormat.LONG).format(new Date()) + messageText; // se debe utilizar concat para los strings
		}

		if (warning && logWarning) {
			l = l + "warning " + DateFormat.getDateInstance(DateFormat.LONG).format(new Date()) + messageText;
		}

		if (message && logMessage) {
			l = l + "message " + DateFormat.getDateInstance(DateFormat.LONG).format(new Date()) + messageText;
		}
		// la variable l no es utilizada lueego su asignacion entre las condicionalesF
		// B
		// C
		if (logToFile) {
			logger.addHandler(fh);
			logger.log(Level.INFO, messageText);
		}

		if (logToConsole) {
			logger.addHandler(ch);
			logger.log(Level.INFO, messageText);
		}

		if (logToDatabase) {
			// no se cuenta con la creacion de la tabla, en caso exista la tabala la sentencia fallara por no contar con la estructura
			stmt.executeUpdate("insert into Log_Values('" + message + "', " + String.valueOf(t) + ")");
		}
		// C
		// Las secciones A, B y C contiene 3 condiciones lo que aumenta la complejidad
		// ciclomatica y segun el libro de clean code se debe disminuir las
		// condicionales con ayuda de patrones de diseño.
	}
}
