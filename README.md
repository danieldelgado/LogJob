# Log Job Configuration
Se debe asignar las variables de entorno:
	-DLOG_TYPE_RUN=CONSOLE,FILE,DATABASE 
	-DFOLDER_FILE_LOG=C:/data/ 
	-DURL_DATABASE=jdbc:h2:mem:test;DB_CLOSE_DELAY=-1 
	-DCONNECT_DRIVER=org.h2.Driver 
	-DCONNECT_USER=sa 	
	-DCONNECT_PASSWORD=s$cret
	

### LOG_TYPE_RUN
Puede asignar cualquier valor de CONSOLE,FILE,DATABASE o la combinaciones que se requiera
	
### FOLDER_FILE_LOG
Ruta donde se colocara el log
	
### URL_DATABASE
Url de conexion
	
### CONNECT_DRIVER
Driver de la implmentacion de base de datos
	
### CONNECT_USER y CONNECT_PASSWORD
Credenciales de conexion

## MVN test
AL ejecutar el comando "mvn test" no requiere la configuracion anterior, ya que cuenta en el pom la configuracion requerida para funciona.



