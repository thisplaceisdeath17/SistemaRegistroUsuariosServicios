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

	public int servicioContar() {
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

	public Object[][] servicioConsultar() {
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

	public int servicioContarBuscado(String dato) {
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

	public Object[][] servicioBuscar(String dato) {
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

	public Object[] servicioSeleccionar(String id) {
		Object[] dato = new Object[campos + 1];
		try {
			if (conectar()) {
				procedimiento = getConn().prepareCall("{CALL servicioSeleccionar(?)}");
				procedimiento.setString("paramID", id);
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

	public String servicioConsecutivo() {
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

	public boolean servicioInsertar(String nombre, String carrera, String costo, String descripcion, String fecha, File imagen) {
		boolean insertado = Boolean.FALSE;
		try {
			String idservicio = this.servicioConsecutivo();
			if (conectar()) {
				procedimiento = getConn().prepareCall("{CALL servicioInsertar(?, ?, ?, ?, ?, ?)}");
				procedimiento.setString("paramNombre", nombre);
				procedimiento.setString("paramCarrera", carrera);
				procedimiento.setString("paramCosto", costo);
				procedimiento.setString("paramDescripcion", descripcion);
				procedimiento.setString("paramFecha", fecha);
				procedimiento.setString("paramImagen", idservicio + "." + imagen.getName().substring(imagen.getName().length() - 3));
				procedimiento.execute();
				if (archivos.copiarArchivos(imagen.getPath(), System.getProperty("user.dir") + "/src/main/resources/Fotografias/Servicios/" + idservicio + "." + imagen.getName().substring(imagen.getName().length() - 3))) {
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

	public boolean servicioEliminar(String id) {
		boolean eliminado = Boolean.FALSE;
		try {
			if (conectar()) {
				procedimiento = getConn().prepareCall("{CALL servicioEliminar(?)}");
				procedimiento.setString("paramID", id);
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

	public boolean servicioModificar(String id, String nombre, String carrera, double costo, String descripcion, String fecha, File imagen) {
		boolean modificado = Boolean.FALSE;
		try {
			if (conectar()) {
				procedimiento = getConn().prepareCall("{CALL servicioModificar(?, ?, ?, ?, ?, ?, ?)}");
				procedimiento.setString("paramNombre", nombre);
				procedimiento.setString("paramCarrera", carrera);
				procedimiento.setDouble("paramCosto", costo);
				procedimiento.setString("paramDescripcion", descripcion);
				procedimiento.setString("paramFecha", fecha);
				procedimiento.setString("paramImagen", id + "." + imagen.getName().substring(imagen.getName().length() - 3));
				procedimiento.setString("paramID", id);
				procedimiento.executeUpdate();
				if (archivos.copiarArchivos(imagen.getPath(), System.getProperty("user.dir") + "/src/main/resources/Fotografias/Servicios/" + id + "." + imagen.getName().substring(imagen.getName().length() - 3))) {
					modificado = Boolean.TRUE;
				}
				archivos.borrarTemporales();
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
