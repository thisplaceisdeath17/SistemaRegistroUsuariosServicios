package Archivos;

import Log.Log;

import javax.swing.*;
import java.io.*;

/**
 * The type Archivos.
 *
 * @author David Esquivel Mendoza
 * @version 1.0
 */
public class Archivos {

    private String path = "src/main/java/Log/Log.txt"; //Ruta del archivo LOG.

    /**
     * Borrar temporales.
     */
    public void borrarTemporales() {
        File file = new File(new File("").getAbsolutePath() + "src/main/java/Temporales/");
        String direccion = file.getAbsolutePath();
        File directorio = new File(direccion);
        File f;
        if (directorio.isDirectory()) {
            String[] files = directorio.list();
            if (files.length > 0) {
                for (String archivo : files) {
                    System.out.println(archivo);
                    f = new File(direccion + File.separator + archivo);
                    f.delete();
                    f.deleteOnExit();
                }
            }
        }
    }

    /**
     * Copiar archivos boolean.
     *
     * @param origen the origen
     * @param destino the destino
     * @return the boolean
     */
    public boolean copiarArchivos(String origen, String destino) {
        boolean copiado = false;
        File archivoOrigen = new File(origen);
        File archivoDestino = new File(destino);
        try {
            InputStream in = new FileInputStream(archivoOrigen);
            OutputStream out = new FileOutputStream(archivoDestino);
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            in.close();
            out.close();
            copiado = true;
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error en la función copiarArchivos. Revise el archivo Log para más información.", "Error de Ejecución de la función copiarArchivos.", JOptionPane.ERROR_MESSAGE);
            Log.writeLog(path, "Error en la función copiarArchivos: " + ex);
        }
        return copiado;
    }
}
