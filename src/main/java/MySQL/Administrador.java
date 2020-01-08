/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MySQL;

import Conexion.Conexion;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author David Esquivel Mendoza
 * @version 2.0
 */
public class Administrador extends Conexion {

    private final int campos = 6;
    private ResultSet res;
    private CallableStatement proc;
    private String passwordX;

    /**
     * @param login
     * @param password
     * @return
     * @throws java.lang.ClassNotFoundException
     */
    public String[] administradorValidar(String login, String password) {
        String[] datos = new String[3];
        try {
            if (conectar()) {
                proc = getConn().prepareCall("{CALL administradorValidar(?, ?)}");
                passwordX = DigestUtils.sha256Hex(password);
                proc.setString("paramLogin", login);
                proc.setString("paramPassword", passwordX);
                res = proc.executeQuery();
                if (res.next()) {
                    datos[0] = res.getString("login");
                    datos[1] = res.getString("nombre_administrador");
                    datos[2] = res.getString("descripcion");
                    System.out.println("Login: " + datos[0] + " Nombre: " + datos[1] + " Tipo: " + datos[2]);
                }
                res.close();
                proc.close();
                desconectar();
            }
        } catch (SQLException ex) {
            System.out.println("Error en la función administradorValidar: " + ex);
        }
        return datos;
    }

    /**
     * @param login
     * @return
     * @throws java.lang.ClassNotFoundException
     */
    public boolean administradorExiste(String login) {
        boolean existe = false;
        try {
            if (conectar()) {
                proc = getConn().prepareCall("{CALL administradorExiste(?)}");
                proc.setString("paramLogin", login);
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
            System.out.println("Error en la función administradorExiste: " + ex);
        }
        return existe;
    }

	/**
	 * @return
	 *
	 * @throws java.lang.ClassNotFoundException
	 */
	public int administradorContar() {
		int cantidad = 0;
		try {
			if (conectar()) {
				proc = getConn().prepareCall("{CALL administradorContar()}");
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
            System.out.println("Error en la función administradorContar: " + ex);
        }
        return cantidad;
	}

	/**
	 * @return
	 *
	 * @throws java.lang.ClassNotFoundException
	 */
	public Object[][] administradorConsultar() {
		Object[][] datos = new Object[administradorContar()][campos - 2];
		try {
			if (conectar()) {
				proc = getConn().prepareCall("{CALL administradorConsultar()}");
				res = proc.executeQuery();
				int registro = 0;
				while (res.next()) {
					for (int i = 0; i < campos - 2; i++) {
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
            System.out.println("Error en la función administradorConsultar: " + ex);
        }
        return datos;
	}

	/**
	 * @param dato
	 *
	 * @return
	 *
	 * @throws java.lang.ClassNotFoundException
	 */
	public int administradorContarBuscado(String dato) {
		int cantidad = 0;
		try {
			if (conectar()) {
				proc = getConn().prepareCall("{CALL administradorContarBuscado(?)}");
				proc.setString("paramDato", dato);
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
            System.out.println("Error en la función administradorContarBuscado " + ex);
        }
		return cantidad;
	}

	/**
	 * @param dato
	 *
	 * @return
	 *
	 * @throws java.lang.ClassNotFoundException
	 */
	public Object[][] administradorBuscar(String dato) {
		Object[][] datos = new Object[administradorContarBuscado(dato)][campos - 2];
		try {
			if (conectar()) {
				proc = getConn().prepareCall("{CALL administradorBuscar(?)}");
				proc.setString("paramDato", dato);
				res = proc.executeQuery();
				int registro = 0;
				while (res.next()) {
					for (int i = 0; i < campos - 2; i++) {
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
            System.out.println("Error en la función administradorBuscar " + ex);
		}
		return datos;
	}

	/**
	 * @param login
	 *
	 * @return
	 *
	 * @throws java.lang.ClassNotFoundException
	 */
	public Object[] administradorSeleccionar(String login) {
		Object[] dato = new Object[campos + 1];
		try {
			if (conectar()) {
				proc = getConn().prepareCall("{CALL administradorSeleccionar(?)}");
				proc.setString("paramLogin", login);
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
            System.out.println("Error en la función administradorSeleccionar " + ex);
        }
        return dato;
    }

    /**
     * @param login
     * @param nombre
     * @param password
     * @param carrera
     * @param tipo
     * @return
     * @throws java.lang.ClassNotFoundException
     */
    public boolean administradorInsertar(String login, String nombre, String password, String carrera, String tipo) {
        boolean insertado = false;
        try {
            if (conectar()) {
                proc = getConn().prepareCall("{CALL administradorInsertar(?, ?, ?, ?, ?)}");
                passwordX = DigestUtils.sha256Hex(password);
                proc.setString("paramLogin", login);
                proc.setString("paramNombre", nombre);
                proc.setString("paramPassword", passwordX);
                proc.setString("paramCarrera", carrera);
                proc.setString("paramTipo", tipo);
                proc.executeUpdate();
                insertado = true;
                System.out.println(insertado);
            }
            proc.close();
            desconectar();
        } catch (SQLException ex) {
            System.out.println("Error en la función administradorInsertar " + ex);
        }
	    return insertado;
    }

	/**
	 * @param login
	 *
	 * @return
	 *
	 * @throws java.lang.ClassNotFoundException
	 */
	public boolean administradorEliminar(String login) {
		boolean eliminado = false;
		try {
			if (conectar()) {
				proc = getConn().prepareCall("{CALL administradorEliminar(?)}");
				proc.setString("paramLogin", login);
				proc.executeUpdate();
				eliminado = true;
				System.out.println(eliminado);
				proc.close();
                desconectar();
            }
        } catch (SQLException ex) {
            System.out.println("Error en la función administradorEliminar " + ex);
		}
		return eliminado;
	}

	/**
	 * @param login
	 * @param nombre
	 * @param password
	 * @param carrera
	 * @param tipo
	 *
	 * @return
	 *
	 * @throws java.lang.ClassNotFoundException
	 */
	public boolean administradorModificar(String login, String nombre, String password, String carrera, String tipo) {
		boolean modificado = true;
		try {
			if (conectar()) {
				proc = getConn().prepareCall("{CALL administradorModificar(?, ?, ?, ?, ?)}");
				passwordX = DigestUtils.sha256Hex(password);
				proc.setString("paramLogin", login);
				proc.setString("paramNombre", nombre);
				proc.setString("paramPassword", passwordX);
				proc.setString("paramCarrera", carrera);
                proc.setString("paramTipo", tipo);
                proc.executeUpdate();
                modificado = true;
                System.out.println(modificado);
                proc.close();
                desconectar();
            }
        } catch (SQLException ex) {
            System.out.println("Error en la función administradorModificar: " + ex);
        }
        return modificado;
    }
}
