/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MySQL;

import Conexion.Conexion;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author David Esquivel Mendoza
 * @version 2.0
 */
public class CarreraArea extends Conexion {

    private int campos = 5;
    private ResultSet res;
    private CallableStatement proc;

    /**
     * @param clave
     *
     * @return
     *
     * @throws ClassNotFoundException
     */
    public boolean carreraAreaExiste(String clave) {
        boolean existe = false;
        try {
            if (conectar()) {
                proc = getConn().prepareCall("{CALL carreraAreaExiste(?)}");
                proc.setString("paramClave", clave);
                res = proc.executeQuery();
                if (res.next()) {
                    existe = true;
                    System.out.println(existe);
                }
                res.close();
                proc.close();
                desconectar();
            }
        } catch (SQLException ex) {
            System.out.println("Error en la función carreraAreaExiste: " + ex);
        }
        return existe;
    }

    /**
     *
     * @param filtro
     * @return
     * @throws ClassNotFoundException
     */
    public int carreraAreaContar(String filtro) {
        int cantidad = 0;
        try {
            if (conectar()) {
                if (filtro.equals("Todos")) {
                    proc = getConn().prepareCall("{CALL carreraAreaContar()}");
                } else {
                    proc = getConn().prepareCall("{CALL carreraAreaContarFiltro(?)}");
                    proc.setString("paramFiltro", filtro);
                }
                res = proc.executeQuery();
                if (res.next()) {
                    cantidad = res.getInt("cantidad");
                    System.out.println(cantidad);
                }
                res.close();
                proc.close();
                desconectar();
            }
        } catch (SQLException ex) {
            System.out.println("Error en la función carreraAreaContar: " + ex);
        }
        return cantidad;
    }

    /**
     *
     * @param filtro
     * @return
     * @throws ClassNotFoundException
     */
    public Object[][] carreraAreaConsultar(String filtro) {
        Object[][] datos = new Object[carreraAreaContar(filtro)][campos];
        try {
            if (conectar()) {
                if (filtro.equals("Todos")) {
                    proc = getConn().prepareCall("{CALL carreraAreaConsultar()}");
                } else {
                    proc = getConn().prepareCall("{CALL carreraAreaConsultarFiltro(?)}");
                    proc.setString("paramFiltro", filtro);
                }
                res = proc.executeQuery();
                int registro = 0;
                while (res.next()) {
                    for (int i = 0; i < campos - 1; i++) {
                        datos[registro][i] = res.getObject(i + 1);
                        System.out.println(datos[registro][i]);
                    }
                    registro++;
                }
                res.close();
                proc.close();
                desconectar();
            }
        } catch (SQLException ex) {
            System.out.println("Error en la función carreraAreaConsultar: " + ex);
        }
        return datos;
    }

    /**
     * @param dato
     * @param filtro
     *
     * @return
     *
     * @throws ClassNotFoundException
     */
    public int carreraAreaContarBuscado(String dato, String filtro) {
        int cantidad = 0;
        try {
            if (conectar()) {
                if (filtro.equals("Todos")) {
                    proc = getConn().prepareCall("{CALL carreraAreaContarBuscado(?)}");
                    proc.setString("paramDato", dato);
                } else {
                    proc = getConn().prepareCall("{CALL carreraAreaContarBuscadoFiltro(?, ?)}");
                    proc.setString("paramDato", dato);
                    proc.setString("paramFiltro", filtro);
                }
                res = proc.executeQuery();
                if (res.next()) {
                    cantidad = res.getInt("cantidad");
                    System.out.println(cantidad);
                }
                res.close();
                proc.close();
                desconectar();
            }
        } catch (SQLException ex) {
            System.out.println("Error en la función carreraAreaContarBuscado: " + ex);
        }
        return cantidad;
    }

    /**
     * @param dato
     * @param filtro
     *
     * @return
     *
     * @throws ClassNotFoundException
     */
    public Object[][] carreraAreaBuscar(String dato, String filtro) {
        Object[][] datos = new Object[carreraAreaContarBuscado(dato, filtro)][campos];
        try {
            if (conectar()) {
                if (filtro.equals("Todos")) {
                    proc = getConn().prepareCall("{CALL carreraAreaBuscar(?)}");
                    proc.setString("paramDato", dato);
                } else {
                    proc = getConn().prepareCall("CALL carreraAreaBuscarFiltro(?, ?)");
                    proc.setString("paramDato", dato);
                    proc.setString("paramFiltro", filtro);
                }
                res = proc.executeQuery();
                int registro = 0;
                while (res.next()) {                    
                    for (int i = 0; i < campos - 1; i++) {
                        datos[registro][i] = res.getObject(i + 1);
                        System.out.println(datos[registro][i]);
                    }
                    registro++;
                }
                res.close();
                proc.close();
                desconectar();
            }
        } catch (SQLException ex) {
            System.out.println("Error en la función carreraAreaBuscar: " + ex);
        }
        return datos;
    }

    /**
     * @param clave
     *
     * @return
     *
     * @throws ClassNotFoundException
     */
    public Object[] carreraAreaSeleccionar(String clave) {
        Object[] dato = new Object[campos + 1];
        try {
            if (conectar()) {
                proc = getConn().prepareCall("{CALL carreraAreaSeleccionar(?)}");
                proc.setString("paramClave", clave);
                res = proc.executeQuery();
                if (res.next()) {
                    for (int i = 0; i < campos; i++) {
                        dato[i] = res.getObject(i + 1);
                        System.out.println(dato[i]);
                    }
                }
                res.close();
                proc.close();
                desconectar();
            }
        } catch (SQLException ex) {
            System.out.println("Error en la función carreraAreaSeleccionar: " + ex);
        }
        return dato;
    }

    /**
     * @param clave
     * @param nombre
     * @param descripcion
     * @param jefe
     * @param tipo
     *
     * @return
     *
     * @throws ClassNotFoundException
     */
    public boolean carreraAreaInsertar(String clave, String nombre, String descripcion, String jefe, String tipo) {
        boolean insertado = Boolean.TRUE;
        try {
            if (conectar()) {
                proc = getConn().prepareCall("{CALL carreraAreaInsertar(?, ?, ?, ?, ?)}");
                proc.setString("paramClave", clave);
                proc.setString("paramNombre", nombre);
                proc.setString("paramDescripcion", descripcion);
                proc.setString("paramJefe", jefe);
                proc.setString("paramTipo", tipo);
                proc.executeUpdate();
                insertado = Boolean.TRUE;
                System.out.println(insertado);
                proc.close();
                desconectar();
            }
        } catch (SQLException ex) {
            System.out.println("Error en la función carreraAreaInsertar: " + ex);
        }
        return insertado;
    }

    /**
     * @param clave
     *
     * @return
     *
     * @throws ClassNotFoundException
     */
    public boolean carreraAreaEliminado(String clave) {
        boolean eliminado = Boolean.FALSE;
        try {
            if (conectar()) {
                proc = getConn().prepareCall("{CALL carreraAreaEliminar(?)}");
                proc.setString("paramClave", clave);
                proc.executeUpdate();
                eliminado = Boolean.TRUE;
                System.out.println(eliminado);
                proc.close();
                desconectar();
            }
        } catch (SQLException ex) {
            System.out.println("Error en la función carreraAreaEliminado: " + ex);
        }
        return eliminado;
    }

    public boolean carreraAreaModificar(String clave, String nombre, String descripcion, String jefe, String tipo) {
        boolean modificado = Boolean.FALSE;
        try {
            if (conectar()) {
                proc = getConn().prepareCall("{CALL carreraAreaModificar(?, ?, ?, ?, ?)}");
                proc.setString("paramClave", clave);
                proc.setString("paramNombre", nombre);
                proc.setString("paramDescripcion", descripcion);
                proc.setString("paramJefe", jefe);
                proc.setString("paramTipo", tipo);
                proc.executeUpdate();
                modificado = Boolean.TRUE;
                System.out.println(modificado);
                proc.close();
                desconectar();
            }
        } catch (SQLException ex) {
            System.out.println("Error en la función carreraAreaModificar: " + ex);
        }
        return modificado;
    }
}
