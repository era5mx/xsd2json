/**
 * Clase para generar los schema.json de las clases del modelo generado por JAXB
 */
package net.puenteg.extend;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.factories.SchemaFactoryWrapper;

/**
 * @author alexander
 *
 */
public class SchemaGeneration implements Constantes {

	private static Logger logger = LogManager.getLogger(SchemaGeneration.class);



	/**
	 * @param args
	 */
	public static void main(String[] args) {

		ObjectMapper mapper = new ObjectMapper();
        SchemaFactoryWrapper schemaFactoryWrapper = new SchemaFactoryWrapper();

        // Se escanea el directorio para obtener las clases
        List<String> listFiles = ScanDatos.scanDir(UtilProperties.getProperty(DATOS_DIR));
        if(!listFiles.isEmpty()){

        	// Se itera la lista de clases para generar el JsonSchema
        	for (Iterator<String> iterator = listFiles.iterator(); iterator.hasNext();) {
				String className = (String) iterator.next();
				if(logger.isDebugEnabled()){
					logger.debug("El nombre calificado de la clase [" + className + "]");
				}
				FileWriter fw = null;
				BufferedWriter bw = null;
	        	try {
	        		// Se obtiene el Class
					Class<?> clase = Class.forName(className);
					if(logger.isDebugEnabled()){
						logger.debug("Se obtuvo el Class [" + clase.getSimpleName() +"]");
					}

					// Se crea el File
					File schema = new File (UtilProperties.getProperty(JSON_DIR).concat(clase.getSimpleName()).concat(".json"));
					// Se crea el FileWriter
					fw = new FileWriter(schema);
					// Se crea el BufferedWriter
					bw = new BufferedWriter(fw);

					// Se genera el schema
					mapper.acceptJsonFormatVisitor(clase, schemaFactoryWrapper);
					JsonSchema jsonSchema = schemaFactoryWrapper.finalSchema();
					String jsonSchemaStr = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonSchema);
//					if(logger.isDebugEnabled()){
//						logger.debug(jsonSchemaStr);
//					}

					// Se escribe el schema en el archivo
	    			bw.write(jsonSchemaStr);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (JsonMappingException e) {
					e.printStackTrace();
	    		} catch (JsonProcessingException e) {
	    			e.printStackTrace();
	    		} catch (IOException e) {
					e.printStackTrace();
				} finally {
					try {
						if(null!=bw) { bw.close(); }
						if(null!=fw) { fw.close(); }
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

			}

        }

	}

}
