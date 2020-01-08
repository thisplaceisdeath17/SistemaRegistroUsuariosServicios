package Log;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Clase que permite la impresión en un archivo de texto, que permite al
 * administrador observar los eventos y errores ocurridos durante la ejecución
 * del sistema, con el fin de apoyar y resolver dicho error.
 *
 * @author David Esquivel Mendoza
 * @version 1.0
 */
public class Log {

    /**
     * Write log.
     *
     * @param path the path
     * @param message the message
     * @throws SecurityException
     */
    public static void writeLog(String path, String message) {
        Logger logger = Logger.getLogger("LOGGER");
        FileHandler fh;

        try {
            fh = new FileHandler(path, true);
            logger.addHandler(fh);

            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);

            logger.info(message);
        } catch (SecurityException | IOException ex) {
            ex.printStackTrace();
        }
    }
}
