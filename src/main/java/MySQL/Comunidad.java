/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MySQL;

import Archivos.Archivos;
import Conexion.Conexion;

import java.io.File;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author David Esquivel Mendoza.
 * @version 3.0
 */
public class Comunidad extends Conexion {

	private int campos = 9;
	private ResultSet res;
	private CallableStatement procedimiento;
	private String path = "/src/main/java/Logger/Log.txt"; //Ruta del archivo LOG.
	private Archivos archivos = new Archivos();

	/**
	 *
	 * @param matricula
     * @param rfid
     * @return
     * @throws ClassNotFoundException
     */
    public String comunidadValidar(int matricula, int rfid) throws ClassNotFoundException {
        String nombre = "";
        try {
            if (conectar()) {
                procedimiento = getConn().prepareCall("{CALL comunidadValidar(?, ?)}");
                procedimiento.setInt("paramMatricula", matricula);
                procedimiento.setInt("paramRFID", rfid);
                res = procedimiento.executeQuery();
                if (res.next()) {
                    nombre = res.getString("nombre_comunidad");
                    System.out.println(nombre);
                }
                res.close();
                procedimiento.close();
                desconectar();
            }
        } catch (SQLException ex) {
            System.out.println("Error en la función comunidadValidar: " + ex);
        }
        return nombre;
    }

    /**
     * @param matricula
     *
     * @return
     *
     * @throws ClassNotFoundException
     */
    public boolean comunidadExiste(String matricula) {
	    boolean existe = Boolean.FALSE;
	    try {
		    if (conectar()) {
			    procedimiento = getConn().prepareCall("{CALL comunidadExiste(?)}");
			    procedimiento.setString("paramMatricula", matricula);
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
		    System.out.println("Error en la función comunidadExiste: " + ex);
	    }
	    return existe;
    }

	/**
	 *
	 * @param filtro
	 * @return
	 */
    public int comunidadContar(String filtro) {
        int cantidad = 0;
        try {
            if (conectar()) {
                if (filtro.equals("Todos")) {
                    procedimiento = getConn().prepareCall("{CALL comunidadContar()}");
                } else {
                    procedimiento = getConn().prepareCall("{CALL comunidadContarFiltro(?)}");
                    procedimiento.setString("paramFiltro", filtro);
                }
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
	        System.out.println("Error en la función comunidadContar: " + ex);
        }
	    return cantidad;
    }

	/**
	 * @param filtro
	 *
	 * @return
	 *
	 * @throws ClassNotFoundException
	 */
	public Object[][] comunidadConsultar(String filtro) {
		Object[][] datos = new Object[comunidadContar(filtro)][campos - 3];
		int registro = 0;
		try {
			if (conectar()) {
				if (filtro.equals("Todos")) {
					procedimiento = getConn().prepareCall("{CALL comunidadConsultar()}");
				} else {
					procedimiento = getConn().prepareCall("{CALL comunidadConsultarFiltro(?)}");
					procedimiento.setString("paramFiltro", filtro);
				}
				res = procedimiento.executeQuery();
				while (res.next()) {
					for (int i = 0; i < campos - 3; i++) {
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
			System.out.println("Error en la función comunidadConsultar: " + ex);
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
	public int comunidadContarBuscado(String dato, String filtro) {
		int cantidad = 0;
		try {
			if (conectar()) {
				if (filtro.equals("Todos")) {
					procedimiento = getConn().prepareCall("{CALL comunidadContarBuscado(?)}");
					procedimiento.setString("paramDato", dato);
				} else {
					procedimiento = getConn().prepareCall("{CALL comunidadContarBuscadoFiltro(?, ?)}");
					procedimiento.setString("paramDato", dato);
					procedimiento.setString("paramFiltro", filtro);
				}
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
			System.out.println("Error en la función comunidadContarBuscado: " + ex);
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
	public Object[][] comunidadBuscar(String dato, String filtro) {
		Object[][] datos = new Object[comunidadContarBuscado(dato, filtro)][campos - 3];
		int registro = 0;
		try {
			if (conectar()) {
				if (filtro.equals("Todos")) {
					procedimiento = getConn().prepareCall("{CALL comunidadBuscar(?)}");
					procedimiento.setString("paramDato", dato);
				} else {
					procedimiento = getConn().prepareCall("{CALL comunidadBuscarFiltro(?, ?)}");
					procedimiento.setString("paramDato", dato);
					procedimiento.setString("paramFiltro", filtro);
				}
				res = procedimiento.executeQuery();
				while (res.next()) {
					for (int i = 0; i < campos - 3; i++) {
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
			System.out.println("Error en la función comunidadBuscar: " + ex);
		}
		return datos;
	}

	/**
	 * @param matricula
	 *
	 * @return
	 *
	 * @throws ClassNotFoundException
	 */
	public Object[] comunidadSeleccionar(String matricula) {
		Object[] dato = new Object[campos + 1];
		try {
			if (conectar()) {
				procedimiento = getConn().prepareCall("{CALL comunidadSeleccionar(?)}");
				procedimiento.setString("paramMatricula", matricula);
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
			System.out.println("Error en la función comunidadSeleccionar: " + ex);
		}
		return dato;
	}

	/**
	 * @param matricula
	 * @param nombre
	 * @param rfid
	 * @param carrera
	 * @param fotografia
	 * @param tipo
	 *
	 * @return
	 *
	 * @throws ClassNotFoundException
	 */
	public boolean comunidadInsertar(String matricula, String nombre, String rfid, String carrera, File fotografia, String tipo) {
		boolean insertado = Boolean.FALSE;
		try {
			if (conectar()) {
				procedimiento = getConn().prepareCall("{CALL comunidadInsertar(?, ?, ?, ?, ?, ?)}");
				procedimiento.setString("paramMatricula", matricula);
				procedimiento.setString("paramNombre", nombre);
				procedimiento.setString("paramRFID", rfid);
				procedimiento.setString("paramCarrera", carrera);
				procedimiento.setString("paramFotografia", matricula + "." + fotografia.getName().substring(fotografia.getName().length() - 3));
				procedimiento.setString("paramTipo", tipo);
			}
			procedimiento.executeUpdate();
			if (archivos.copiarArchivos(fotografia.getPath(), System.getProperty("user.dir") + "/src/main/resources/Fotografias/Comunidad/" + matricula + "." + fotografia.getName().substring(fotografia.getName().length() - 3))) {
				insertado = Boolean.TRUE;
			}
			archivos.borrarTemporales();
			procedimiento.close();
			desconectar();
		} catch (SQLException ex) {
			System.out.println("Error en la función comunidadInsertar: " + ex);
		}
		return insertado;
	}

	/**
	 * @param matricula
	 *
	 * @return
	 *
	 * @throws ClassNotFoundException
	 */
	public boolean comunidadEliminar(String matricula) {
		boolean eliminado = Boolean.FALSE;
		try {
			if (conectar()) {
				procedimiento = getConn().prepareCall("{CALL comunidadEliminar(?)}");
				procedimiento.setString("paramMatricula", matricula);
				procedimiento.executeUpdate();
				eliminado = Boolean.TRUE;
				System.out.println(eliminado);
				procedimiento.close();
				desconectar();
			}
		} catch (SQLException ex) {
			System.out.println("Error en la función comunidadEliminar: " + ex);
		}
		return eliminado;
	}

	/**
	 * @param matricula
	 * @param nombre
	 * @param rfid
	 * @param carrera
	 * @param fotografia
	 * @param saldo
	 * @param tipo
	 *
	 * @return
	 *
	 * @throws ClassNotFoundException
	 */
	public boolean comunidadModificar(String matricula, String nombre, String rfid, String carrera, File fotografia, double saldo, String tipo) {
		boolean modificado = Boolean.FALSE;
		try {
			if (conectar()) {
				procedimiento = getConn().prepareCall("{CALL comunidadModificar(?, ?, ?, ?, ?, ?, ?)}");
				procedimiento.setString("paramMatricula", matricula);
				procedimiento.setString("paramNombre", nombre);
				procedimiento.setString("paramRFID", rfid);
				procedimiento.setString("paramCarrera", carrera);
				procedimiento.setString("paramFotografia", matricula + "." + fotografia.getName().substring(fotografia.getName().length() - 3));
				procedimiento.setDouble("paramSaldo", saldo);
				procedimiento.setString("paramTipo", tipo);
				procedimiento.executeUpdate();
				if (archivos.copiarArchivos(fotografia.getPath(), System.getProperty("user.dir") + "/src/main/resources/Fotografias/Comunidad/" + matricula + "." + fotografia.getName().substring(fotografia.getName().length() - 3))) {
					modificado = Boolean.TRUE;
					System.out.println(modificado);
				}
				archivos.borrarTemporales();
				procedimiento.close();
				desconectar();
			}
		} catch (SQLException ex) {
            System.out.println("Error en la funcion comunidadModificar: " + ex);
        }
        return modificado;
    }
}
