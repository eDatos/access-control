Cuando se cree la RELEASE, añadir estos pasos al manual de instalación:

1. Parar Tomcat

2. Cambios en el data común:
	- Eliminado el directorio de configuración del ISTAC [DATA_ISTAC]
	- Añadida propiedad "metamac.navbar.url" en el fichero [DATA]/common/conf/static/resources.xml
	- Añadida propiedad "metamac.organisation" en el fichero [DATA]/common/conf/static/resources.xml

3. Cambios en el data de access-control:
	- Añadido fichero [DATA]/access-control/docs/Gestor_accesos-Manual_usuario.pdf
	- Añadido fichero [DATA]/access-control/conf/static/resources.xml con la propiedad "metamac.access.control.user.guide.file.name"

99. Reiniciar Tomcat