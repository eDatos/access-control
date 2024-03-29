# UPGRADE - Proceso de actualización entre versiones

*Para actualizar de una versión a otra es suficiente con actualizar el WAR a la última versión. 
El siguiente listado presenta aquellos cambios de versión en los que no es suficiente con actualizar 
y que requieren por parte del instalador tener más cosas en cuenta. Si el cambio de versión engloba 
varios cambios de versión del listado, estos han de ejecutarse en orden de más antiguo a más reciente.*

*De esta forma, si tuviéramos una instalación en una versión **A.B.C** y quisieramos actualizar a una 
versión posterior **X.Y.Z** para la cual existan versiones anteriores que incluyan cambios listados 
en este documento, se deberá realizar la actualización pasando por todas estas versiones antes de 
poder llegar a la versión deseada.*

*EJEMPLO: Queremos actualizar desde la versión 1.0.0 a la 3.0.0 y existe un cambio en la base de 
datos en la actualización de la versión 1.0.0 a la 2.0.0.*

*Se deberá realizar primero la actualización de la versión 1.0.0 a la 2.0.0 y luego desde la 2.0.0 a la 3.0.0*

## 2.5.1 a 2.6.0

- Se han realizado cambios a la base de datos, por ello se proveen una serie de scripts SQL para adaptarse a la nueva versión. 
  Ejecutar los scripts de la siguiente ruta en el esquema correspondiente por orden de fecha: 
  [etc/changes-from-release/2.5.1/db/access-control/<BD>/](etc/changes-from-release/2.5.1/db/access-control/<BD>), donde `<BD>`se corresponde 
  con el DBMS empleado.
  
## 0.0.0 a 2.5.1

El proceso de actualizaciones entre versiones para versiones anteriores a la 2.5.1 está definido en 
"Metamac - Manual de instalación.doc".
