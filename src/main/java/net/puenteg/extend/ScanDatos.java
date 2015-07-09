/**
 * Clase para realizar el scan de un directorio
 */
package net.puenteg.extend;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * Lee el directorio indicado y obtiene la lista de archivos java
 * excluyendo package-info.java y ObjectFactory.java
 *
 * @author <a href=mailto:david@rengifo.mx>David Rengifo</a>
 */
public class ScanDatos {

	/** package-info.java */
	private static final String PACKAGE_INFO = "package-info.java";

	/** ObjectFactory.java */
	private static final String OBJECT_FACTORY = "ObjectFactory.java";

	/** Constante para cargar el valor de PACKAGE configurado en el config.properties */
	private static final String PACKAGE = "PACKAGE";

	/**
	 * Constructor
	 */
	public ScanDatos() {
	}

	/**
	 * Escanea un directorio y devuelve una lista con los nombres calificados de las clases que contiene
	 * @param args
	 */
	public static List<String> scanDir(String path) {

		List<String> listaArchivos = new ArrayList<String>();

		if(StringUtils.isNotBlank(path)) {
			if(path.endsWith("//")) {
				path.substring(0, path.length()-2);
			}
			File dir = new File(path);
			String[] ficheros = dir.list();

			long ignoreFiles = 0;
			if (ficheros == null)
				System.out.println("No hay ficheros en el directorio especificado");
			else {
				System.out.println("Hay " + ficheros.length + " archivos en el directorio Datos.");
				for (int i = 0; i < ficheros.length; i++) {
					String nameFile = ficheros[i];
					if(nameFile.equalsIgnoreCase(PACKAGE_INFO) || nameFile.equalsIgnoreCase(OBJECT_FACTORY)){
						ignoreFiles++;
					}
					else {
						if (nameFile.endsWith("java") || nameFile.endsWith("JAVA")) {
							listaArchivos.add(UtilProperties.getProperty(PACKAGE).concat(nameFile.substring(0,nameFile.lastIndexOf("."))));
						} else {
							ignoreFiles++;
						}
					}
				}

				if (ignoreFiles > 0) {
					System.out.println("Se ignoraron " + ignoreFiles
							+ " archivos del directorio Datos.\n");
				}
			}
		}

		return listaArchivos;
	}

}