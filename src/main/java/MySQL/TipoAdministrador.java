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
 * @author roule
 */
public class TipoAdministrador extends Conexion {
    private int campos = 3;
    private ResultSet res;
    private CallableStatement procedimiento;

	public boolean tipoAdministradorExiste(String descripcion) {
		boolean existe = Boolean.FALSE;
		try {
			if (conectar()) {
				procedimiento = getConn().prepareCall("{CALL tipoAdministradorExiste(?)}");
				procedimiento.setString("paramDescripcion", descripcion);
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

	public int tipoAdministradorContarBuscado(String dato) {
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

	public Object[][] tipoAdministradorBuscar(String dato) {
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

	public Object[] tipoAdministradorSeleccionar(String id, String descripcion) {
		Object[] dato = new Object[campos + 1];
		try {
			if (conectar()) {
				procedimiento = getConn().prepareCall("{CALL tipoAdministradorSeleccionar(?, ?)}");
				procedimiento.setString("paramID", id);
				procedimiento.setString("paramDescripcion", descripcion);
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

	public boolean tipoAdministradorInsertar(String descripcion) {
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

	public boolean tipoAdministradorEliminar(String descripcion) {
		boolean eliminado = Boolean.FALSE;
		try {
			if (conectar()) {
				procedimiento = getConn().prepareCall("{CALL tipoAdministradorEliminar(?)}");
				procedimiento.setString("paramDescripcion", descripcion);
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

	public boolean tipoAdministradorModificar(String id, String descripcion) {
		boolean modificado = Boolean.FALSE;
		try {
			if (conectar()) {
				procedimiento = getConn().prepareCall("{CALL tipoAdministradorModificar(?, ?)}");
				procedimiento.setString("paramID", id);
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
