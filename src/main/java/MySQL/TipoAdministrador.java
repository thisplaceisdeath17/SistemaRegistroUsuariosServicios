/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MySQL;

import Conexion.*;
import java.sql.*;

/**
 *
 * @author roule
 */
public class TipoAdministrador extends Conexion {
    private int campos = 3;
    private ResultSet res;
    private CallableStatement procedimiento;
    
    public boolean tipoAdministradorExiste(String id) throws ClassNotFoundException {
        boolean existe = Boolean.FALSE;
        try {
            if (conectar()) {
                procedimiento = getConn().prepareCall("{CALL tipoAdministradorExiste(?)}");
                procedimiento.setString("paramID", id);
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
            System.out.println("Error en la función tipoAdministradorExiste: " + ex);
        }
        return existe;
    }
    public int tipoAdministradorContar() {
        int cantidad = 0;
        try {
            if (conectar()) {
                procedimiento = getConn().prepareCall("{CALL tipoAdministradorContar()}");
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
            System.out.println("Error en la función tipoAdministradorContar: " + ex);
        }
        return cantidad;
    }
    public Object[][] tipoAdministradorConsultar() {
        Object[][] datos = new Object[tipoAdministradorContar()][campos];
        try {
            if (conectar()) {
                procedimiento = getConn().prepareCall("{CALL tipoAdministradorConsultar()}");
                res = procedimiento.executeQuery();
                int registro = 0;
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
            System.out.println("Error en la función tipoAdministradorConsultar: " + ex);
        }
        return datos;
    }
    public int tipoAdministradorContarBuscado(String dato) throws ClassNotFoundException {
        int cantidad = 0;
        try {
            if (conectar()) {
                procedimiento = getConn().prepareCall("{CALL tipoAdministradorContarBuscado(?)}");
                procedimiento.setString("paramDato", dato);
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
            System.out.println("Error en la función tipoAdministradorContarBuscado: " + ex);
        }
        return cantidad;
    }
    public Object[][] tipoAdministradorBuscar(String dato) throws ClassNotFoundException {
        Object[][] datos = new Object[tipoAdministradorContarBuscado(dato)][campos];
        try {
            if (conectar()) {
                procedimiento = getConn().prepareCall("{CALL tipoAdministradorBuscar(?)}");
                procedimiento.setString("paramDato", dato);
                res = procedimiento.executeQuery();
                int registro = 0;
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
            System.out.println("Error en la función tipoAdministradorBuscar: " + ex);
        }
        return datos;
    }
    public Object[] tipoAdministradorSeleccionar(int id) throws ClassNotFoundException {
        Object[] dato = new Object[campos + 1];
        try {
            if (conectar()) {
                procedimiento = getConn().prepareCall("{CALL tipoAdministradorSeleccionar(?)}");
                procedimiento.setInt("paramID", id);
                res = procedimiento.executeQuery();
                if (res.next()) {
                    for (int i = 0; i < campos; i++) {
                        dato[i] = res.getObject(i + 1);
                    }
                }
                res.close();
                procedimiento.close();
                desconectar();
            }
        } catch (SQLException ex) {
            System.out.println("Error en la función tipoAdministradorSeleccionar: " + ex);
        }
        return dato;
    }
    public boolean tipoAdministradorInsertar(String descripcion) throws ClassNotFoundException {
        boolean insertado = Boolean.FALSE;
        try {
            if (conectar()) {
                procedimiento = getConn().prepareCall("{CALL tipoAdministradorInsertar(?)}");
                procedimiento.setString("paramDescripcion", descripcion);
                procedimiento.executeUpdate();
                insertado = Boolean.TRUE;
                System.out.println(insertado);
                procedimiento.close();
                desconectar();
            }
        } catch (SQLException ex) {
            System.out.println("Error en la función tipoAdministradorInsertar: " + ex);
        }
        return insertado;
    }
    public boolean tipoAdministradorEliminar(int id) throws ClassNotFoundException {
        boolean eliminado = Boolean.FALSE;
        try {
            if (conectar()) {
                procedimiento = getConn().prepareCall("{CALL tipoAdministradorEliminar(?)}");
                procedimiento.setInt("paramID", id);
                procedimiento.executeUpdate();
                eliminado = Boolean.TRUE;
                System.out.println(eliminado);
                procedimiento.close();
                desconectar();
            }
        } catch (SQLException ex) {
            System.out.println("Error en la función tipoAdministradorEliminar: " + ex);
        }
        return eliminado;
    }
    public boolean tipoAdministradorModificar(String descripcion) throws ClassNotFoundException {
        boolean modificado = Boolean.FALSE;
        try {
            if (conectar()) {
                procedimiento = getConn().prepareCall("{CALL tipoAdministradorModificar(?)}");
                procedimiento.setString("paramDescripcion", descripcion);
                procedimiento.executeUpdate();
                modificado = Boolean.TRUE;
                System.out.println(modificado);
                procedimiento.close();
                desconectar();
            }
        } catch (SQLException ex) {
            System.out.println("Error en la función tipoAdministradorModificar: " + ex);
        }
        return modificado;
    }
}
