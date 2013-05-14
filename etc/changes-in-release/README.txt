Cuando se cree la RELEASE, añadir estos pasos al manual de instalación:

1. Parar Tomcat

NOTA: Los siguientes cambios ya se han añadido al manual de instalación, pero no se ha hecho la instalación en el ISTAC.
	|	2. Base de datos:
	|		- Ejecutar el script 03-updates-in-release/01-update-columns-length.sql, tanto para Oracle como MSSql (Cambiado tamaño de Enums y otra columna de ExternalItem)
	|		- Ejecutar el script 03-updates-in-release/02-update-localised-string.sql, tanto para Oracle como MSSql
	|	
	|	3. DATA:
	|		- Eliminada propiedad del DATA 'metamac.access.control.clients.statistical.operations.rest.internal' (ya se establece en el DATA común)	

99. Reiniciar Tomcat