/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MySQL;

import Archivos.Archivos;
import Conexion.Conexion;
import Log.Log;
import java.io.File;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author David Esquivel Mendoza.
 * @version 3.0
 */
public class Servicios extends Conexion{
    private int campos = 7;
    private ResultSet res;
    private CallableStatement procedimiento;
    private String path = "src/main/java/Logger/Log.txt"; //Ruta del archivo LOG.
    private Archivos archivos = new Archivos();
    
    public boolean servicioExiste(int id) throws ClassNotFoundException, SecurityException, IOException {
        boolean existe = Boolean.FALSE;
        try {
            if (conectar()) {
                procedimiento = getConn().prepareCall("{CALL servicioExiste(?)}");
                procedimiento.setInt("paramID", id);
                res = procedimiento.executeQuery();
                if (res.next()) {
                    existe = Boolean.TRUE;
                    System.out.println(existe);
                }
                res.close();
                procedimiento.close();
                desconectar();
            }
        } catch (SQLException ex) {
            System.out.println("Error en la función servicioExiste: " + ex);
            Log.writeLog(path, "Hubo un error en la función servicioExiste de la clase Servicios en el paquete MySQL. El error es el siguiente: " + ex);
        }
        return existe;
    }
    public int servicioContar() throws ClassNotFoundException, SecurityException, IOException {
        int cantidad = 0;
        try {
            if (conectar()) {
                procedimiento = getConn().prepareCall("{CALL servicioContar()}");
                res = procedimiento.executeQuery();
                if (res.next()) {
                    cantidad = res.getInt("cantidad");
                    System.out.println(cantidad);
                }
                res.close();
                procedimiento.close();
                desconectar();
            }
        } catch (SQLException ex) {
            System.out.println("Error en la función servicioContar: " + ex);
            Log.writeLog(path, "Hubo un error en la función servicioContar de la clase Servicios en el paquete MySQL. El error es el siguiente: " + ex);
        }
        return cantidad;
    }
    public Object[][] servicioConsultar() throws ClassNotFoundException, SecurityException, IOException {
        Object[][] datos = new Object[servicioContar()][campos - 1];
        int registro = 0;
        try {
            if (conectar()) {
                procedimiento = getConn().prepareCall("{CALL servicioConsultar()}");
                res = procedimiento.executeQuery();
                while (res.next()) {                    
                    for (int i = 0; i < campos - 1; i++) {
                        datos[registro][i] = res.getObject(i + 1);
                        System.out.println(datos[registro][i]);
                    }
                    registro++;
                }
                res.close();
                procedimiento.close();
                desconectar();
            }
        } catch (SQLException ex) {
            System.out.println("Error en la función servicioConsultar: " + ex);
            Log.writeLog(path, "Hubo un error en la función servicioConsultar de la clase Servicios en el paquete MySQL. El error es el siguiente: " + ex);
        }
        return datos;
    }
    public int servicioContarBuscado(String dato) throws ClassNotFoundException, SecurityException, IOException {
        int cantidad = 0;
        try {
            if (conectar()) {
                procedimiento = getConn().prepareCall("{CALL servicioContarBuscado(?)}");
                procedimiento.setString("paramDato", dato);
                res = procedimiento.executeQuery();
                if (res.next()) {
                    cantidad = res.getInt("cantidad");
                }
                res.close();
                procedimiento.close();
                desconectar();
            }
        } catch (SQLException ex) {
            System.out.println("Error en la función servicioContarBuscado: " + ex);
            Log.writeLog(path, "Hubo un error en la función servicioContarBuscado de la clase Servicios en el paquete MySQL. El error es el siguiente: " + ex);
        }
        return cantidad;
    }
    public Object[][] servicioBuscar(String dato) throws ClassNotFoundException, SecurityException, IOException {
        Object[][] datos = new Object[servicioContarBuscado(dato)][campos - 1];
        int registro = 0;
        try {
            if (conectar()) {
                procedimiento = getConn().prepareCall("{CALL servicioBuscar(?)}");
                procedimiento.setString("paramDato", dato);
                res = procedimiento.executeQuery();
                while (res.next()) {                    
                    for (int i = 0; i < campos - 1; i++) {
                        datos[registro][i] = res.getObject(i + 1);
                        System.out.println(datos[registro][i]);
                    }
                    registro++;
                }
                res.close();
                procedimiento.close();
                desconectar();
            }
        } catch (SQLException ex) {
            System.out.println("Error en la función servicioBuscar: " + ex);
            Log.writeLog(path, "Hubo un error en la función servicioBuscar de la clase Servicios en el paquete MySQL. El error es el siguiente: " + ex);
        }
        return datos;
    }
    public Object[] servicioSeleccionar(int id) throws ClassNotFoundException, SecurityException, IOException {
        Object[] dato = new Object[campos + 1];
        try {
            if (conectar()) {
                procedimiento = getConn().prepareCall("{CALL servicioSeleccionar(?)}");
                procedimiento.setInt("paramID", id);
                res = procedimiento.executeQuery();
                if (res.next()) {
                    for (int i = 0; i < campos + 1; i++) {
                        dato[i] = res.getObject(i + 1);
                        System.out.println(dato[i]);
                    }
                }
                res.close();
                procedimiento.close();
                desconectar();
            }
        } catch (SQLException ex) {
            System.out.println("Error en la función servicioSeleccionar: " + ex);
            Log.writeLog(path, "Hubo un error en la función servicioSeleccionar de la clase Servicios en el paquete MySQL. El error es el siguiente: " + ex);
        }
        return dato;
    }
    public String servicioConsecutivo() throws ClassNotFoundException, SecurityException, IOException {
        String consecutivo = "0";
        try {
            if (conectar()) {
                procedimiento = getConn().prepareCall("{CALL servicioConsecutivo()}");
                res = procedimiento.executeQuery();
                if (res.next()) {
                    consecutivo = String.valueOf(Integer.parseInt(res.getString("ultimo")) + 1);
                    System.out.println(consecutivo);
                }
                res.close();
                procedimiento.close();
                desconectar();
            }
        } catch (NumberFormatException | SQLException ex) {
            System.out.println("Error en la función servicioConsecutivo: " + ex);
            Log.writeLog(path, "Hubo un error en la función servicioConsecutivo de la clase Servicios en el paquete MySQL. El error es el siguiente: " + ex);
        }
        return consecutivo;
    }
    public boolean servicioInsertar(int id, String nombre, String carrera, double costo, String descripcion, String fecha, File imagen) throws ClassNotFoundException, SecurityException, IOException {
        boolean insertado = Boolean.FALSE;
        try {
            if (conectar()) {
                procedimiento = getConn().prepareCall("{CALL servicioInsertar(?, ?, ?, ?, ?, ?, ?)}");
                procedimiento.setInt("paramID", id);
                procedimiento.setString("paramNombre", nombre);
                procedimiento.setString("paramCarrera", carrera);
                procedimiento.setDouble("paramCosto", costo);
                procedimiento.setString("paramDescripcion", descripcion);
                procedimiento.setString("paramFecha", fecha);
                procedimiento.setString("paramImagen", id + "." + imagen.getName().substring(imagen.getName().length() - 3, imagen.getName().length()));
                procedimiento.execute();
                if (archivos.copiarArchivos(imagen.getPath(), System.getProperty("user.dir") + "src/main/java/Fotografia/Servicios/" + id + "." + imagen.getName().substring(imagen.getName().length() - 3, imagen.getName().length()))) {
                    insertado = Boolean.TRUE;
                    System.out.println(insertado);
                }
                procedimiento.close();
                desconectar();
            }
        } catch (SQLException ex) {
            System.out.println("Error en la función servicioInsertado: " + ex);
            Log.writeLog(path, "Hubo un error en la función servicioInsertado de la clase Servicios en el paquete MySQL. El error es el siguiente: " + ex);
        }
        return insertado;
    }
    public boolean servicioEliminar(int id) throws ClassNotFoundException, SecurityException, IOException {
        boolean eliminado = Boolean.FALSE;
        try {
            if (conectar()) {
                procedimiento = getConn().prepareCall("{CALL servicioEliminar(?)}");
                procedimiento.setInt("paramID", id);
                procedimiento.executeUpdate();
                eliminado = Boolean.TRUE;
                System.out.println(eliminado);
                procedimiento.close();
                desconectar();
            }
        } catch (SQLException ex) {
            System.out.println("Error en la función servicioEliminar: " + ex);
            Log.writeLog(path, "Hubo un error en la función servicioEliminar de la clase Servicios en el paquete MySQL. El error es el siguiente: " + ex);
        }
        return eliminado;
    }
    public boolean servicioModificar(int id, String nombre, String carrera, double costo, String descripcion, String tiempo, File imagen) throws ClassNotFoundException, SecurityException, IOException {
        boolean modificado = Boolean.FALSE;
        try {
            if (conectar()) {
                procedimiento = getConn().prepareCall("{CALL servicioModificar(?, ?, ?, ?, ?, ?, ?)}");
                procedimiento.setInt("paramID", id);
                procedimiento.setString("paramNombre", nombre);
                procedimiento.setString("paramCarrera", carrera);
                procedimiento.setDouble("paramCosto", costo);
                procedimiento.setString("paramDescripcion", descripcion);
                procedimiento.setString("paramTiempo", tiempo);
                procedimiento.setString("paramImagen", id + "." + imagen.getName().substring(imagen.getName().length() - 3, imagen.getName().length()));
                procedimiento.executeUpdate();
                if (archivos.copiarArchivos(imagen.getName(), System.getProperty("user.dir") + "src/main/java/Fotografias/Servicios/" + id + "." + imagen.getName().substring(imagen.getName().length() - 3, imagen.getName().length()))) {
                    modificado = Boolean.TRUE;
                }
                procedimiento.close();
                desconectar();
            }
        } catch (SQLException ex) {
            System.out.println("Error en la función servicioModificar: " + ex);
            Log.writeLog(path, "Hubo un error en la función servicioModificar de la clase Servicios en el paquete MySQL. El error es el siguiente: " + ex);
        }
        return modificado;
    }
}
