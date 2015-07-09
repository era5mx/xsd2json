# xsd2json
---
## Proyecto Gradle que hace uso de tecnologia JAXB y Jackson para Generar Schemas JSON a partir de XSD

- La generacion del modelo de clases se realiza ejecutando el comando: <strong>gradle xjc</strong>

- La generacion de los xsd se realiza ejecutando la clase SchemaGeneration

- En la interfaz Constantes se define el Path del archivo config.properties asi como las claves para recuperar los valores definidos en este.

- El ejercicio se realizó con el xsd público disponible en el url: <a href="http://www.sat.gob.mx/sitio_internet/cfd/3/cfdv32.xsd" target="_blank"> http://www.sat.gob.mx/sitio_internet/cfd/3/cfdv32.xsd</a>

...pero, debería trabajar apropiadamente con cualquier xsd valido.


---
Los acentos han sido omitidos intencionalmente en este documento