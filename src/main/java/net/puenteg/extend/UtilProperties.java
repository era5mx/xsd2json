/**
 *
 */
package net.puenteg.extend;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * @author alexander
 *
 */
public class UtilProperties implements Constantes {

	/**
	 * Constructor
	 */
	public UtilProperties() {
	}

	public static String getProperty(String nameProperty) {

		/**Creamos un Objeto de tipo Properties*/
	    Properties propiedades = new Properties();

	    /**Cargamos el archivo desde la ruta especificada*/
	    try {
			propiedades.load(new FileInputStream(CONFIG_PROPERTIES));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	    /**Obtenemos los parametros definidos en el archivo*/
	    String property = propiedades.getProperty(nameProperty);

	    return property!=null?property:"";

	}

}
