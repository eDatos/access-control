Cuando se cree la RELEASE, añadir estos pasos al manual de instalación:

1. Parar Tomcat

2. Cambios en Base de datos
	- No enviar el script de updates-in-release porque falta migrar inserts posiblemente no tengan datos. Mandar BBDD completa. Si se quejan se manda la actualización. 

3. Cambios en el DATA
    - Eliminada propiedad "metamac.ws.statistical.operations.internal.endpoint" ([DATA]/conf/static/resources.xml)
    - Añadida propiedad "metamac.access.control.clients.statistical.operations.rest.internal" ([DATA]/conf/static/resources.xml)

99. Reiniciar Tomcat