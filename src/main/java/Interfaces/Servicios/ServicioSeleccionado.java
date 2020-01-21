/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces.Servicios;

import Interfaces.Inicio.VentanaPrincipal;
import Interfaces.Metodo.Metodo;
import Log.Log;
import MySQL.CarreraArea;
import MySQL.Servicios;
import TextPrompt.TextPrompt;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * @author roule
 */
public class ServicioSeleccionado extends JFrame {

	private JPanel pp = new JPanel();
	private JPanel pca = new JPanel();
	private JPanel pf = new JPanel();
	private JLabel lblNombre = new JLabel("Nombre del Servicio");
	private JLabel lblCosto = new JLabel("Costo del Servicio (MXN)");
	private JLabel lblDescripcion = new JLabel("DescripciÃ³n");
	private JLabel lblFechaActual = new JLabel("Fecha Actual");
	private JLabel lblFechaEntrega = new JLabel("Selecciona fecha ");
	private JLabel lblFotografia = new JLabel();
	private JTextField txtNombre = new JTextField();
	private JTextField txtCosto = new JTextField();
	private JTextField txtDias = new JTextField();
	private JTextArea txtDescripcion = new JTextArea();
	private JScrollPane scrollPane = new JScrollPane(txtDescripcion);
	private JComboBox<String> cboCarreras = new JComboBox<>();
	private JDateChooser dcFechaActual = new JDateChooser();
	private JDateChooser dcFechaEntrega = new JDateChooser();
	private JButton btnEditar = new JButton();
	private JButton btnEliminar = new JButton();
	private JButton btnVolver = new JButton();
	private JButton btnMetodo = new JButton();

	//Iconos para JOptionPanel
	private ImageIcon warning = new ImageIcon("src/main/resources/Iconos/warning.png");
	private ImageIcon error = new ImageIcon("src/main/resources/Iconos/error.png");
	private ImageIcon exito = new ImageIcon("src/main/resources/Iconos/exito.png");
	private ImageIcon pregunta = new ImageIcon("src/main/resources/Iconos/pregunta.png");
	private ImageIcon informacion = new ImageIcon("src/main/resources/Iconos/info.png");

	//Variables y objetos.
	private String[] usuario;
	private String id = null;
	private Servicios s = new Servicios();
	private CarreraArea ca = new CarreraArea();
	private File origen = null;
	private Object[][] datosCA;
	private Object[] datosSer;
	private ImageIcon imagen, imagenScaled;
	private String path = "src/main/java/Log/Log.txt";

	public ServicioSeleccionado(String id, String[] usuario) {
		initComponents();
		this.deshabilitar();
		this.usuario = usuario;
		this.id = id;
		datosCA = ca.carreraAreaConsultar("Todos");
		for (int ca = 0; ca < datosCA.length; ca++) {
			cboCarreras.addItem(datosCA[ca][1].toString());
		}
		datosSer = s.servicioSeleccionar(id);
		txtNombre.setText(datosSer[1].toString());
		txtCosto.setText(datosSer[3].toString());
		txtDescripcion.setText(datosSer[4].toString());
		txtDias.setText(datosSer[5].toString());
		cboCarreras.setSelectedItem(datosCA[2].toString());
		origen = new File(new File(".").getAbsolutePath() + "/src/main/resources/Fotografias/Servicios/" + datosSer[6].toString());
		imagen = new ImageIcon("src/main/resources/Fotografias/Servicios/" + datosSer[6].toString());
		imagenScaled = new ImageIcon(imagen.getImage().getScaledInstance(lblFotografia.getWidth(), lblFotografia.getHeight(), Image.SCALE_SMOOTH));
		lblFotografia.setIcon(imagenScaled);
		Calendar fechaActual = new GregorianCalendar();
		dcFechaActual.setCalendar(fechaActual);
		dcFechaEntrega.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				try {
					if (dcFechaActual.getDate() != null || dcFechaEntrega.getDate() != null) {
						Calendar actual = dcFechaActual.getCalendar();
						Calendar entrega = dcFechaEntrega.getCalendar();
						int dias = -1;
						while (actual.before(entrega) || actual.equals(entrega)) {
							dias++;
							actual.add(Calendar.DATE, 1);
						}
						txtDias.setText("" + dias);
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(ServicioSeleccionado.this, "Error en el calculo de las fechas. Para mÃ¡s informaciÃ³n, consulte el archivo log.", "Error. - SiRiUS.", JOptionPane.ERROR_MESSAGE, error);
					Log.writeLog(path, "Error en el calculo de las fechas." + ex);
				}
			}
		});
	}

	private void initComponents() {
		//labels
		lblNombre.setForeground(Color.BLACK);
		lblNombre.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
		lblNombre.setBounds(15, 15, 150, 30);
		pp.add(lblNombre);
		lblCosto.setForeground(Color.BLACK);
		lblCosto.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
		lblCosto.setBounds(15, 85, 170, 30);
		pp.add(lblCosto);
		lblDescripcion.setForeground(Color.BLACK);
		lblDescripcion.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
		lblDescripcion.setBounds(15, 155, 150, 30);
		pp.add(lblDescripcion);
		lblFechaActual.setForeground(Color.BLACK);
		lblFechaActual.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
		lblFechaActual.setBounds(15, 400, 180, 30);
		pp.add(lblFechaActual);
		lblFechaEntrega.setForeground(Color.BLACK);
		lblFechaEntrega.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
		lblFechaEntrega.setBounds(15, 470, 180, 30);
		pp.add(lblFechaEntrega);
		lblFotografia.setBounds(50, 35, 640, 480);
		lblFotografia.setHorizontalAlignment(SwingConstants.CENTER);
		lblFotografia.setBorder(BorderFactory.createLineBorder(new Color(237, 187, 153), 2, Boolean.FALSE));
		pf.add(lblFotografia);

		//cajas de texto
		txtNombre.setForeground(Color.BLACK);
		txtNombre.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
		txtNombre.setHorizontalAlignment(SwingConstants.CENTER);
		txtNombre.setBounds(15, 50, 300, 30);
		pp.add(txtNombre);
		TextPrompt prompt = new TextPrompt("Escriba el costo en MXN.", txtCosto);
		prompt.setForeground(Color.BLACK);
		prompt.changeAlpha(0.5f);
		prompt.changeStyle(Font.BOLD + Font.ITALIC);
		prompt.setIcon(new ImageIcon("src/main/resources/Iconos/money.png"));
		txtCosto.setForeground(Color.BLACK);
		txtCosto.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
		txtCosto.setHorizontalAlignment(SwingConstants.CENTER);
		txtCosto.setBounds(15, 120, 300, 30);
		pp.add(txtCosto);
		TextPrompt placeholder = new TextPrompt("Escriba una descripciÃ³n.", txtDescripcion);
		placeholder.changeAlpha(0.75f);
		placeholder.setHorizontalAlignment(SwingConstants.LEADING);
		placeholder.setVerticalAlignment(SwingConstants.TOP);
		placeholder.setFont(new Font("Segoe UI Symbol", Font.BOLD + Font.ITALIC, 14));
		scrollPane.setViewportView(txtDescripcion);
		txtDescripcion.setForeground(Color.BLACK);
		txtDescripcion.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
		txtDescripcion.setWrapStyleWord(Boolean.TRUE);
		txtDescripcion.setLineWrap(Boolean.TRUE);
		scrollPane.setBounds(15, 190, 300, 120);
		pp.add(scrollPane);
		txtDias.setForeground(Color.BLACK);
		txtDias.setEditable(Boolean.FALSE);
		txtDias.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
		txtDias.setHorizontalAlignment(SwingConstants.CENTER);
		txtDias.setBounds(270, 470, 30, 30);
		pp.add(txtDias);

		//Jdatechooser
		dcFechaActual.setForeground(Color.BLACK);
		dcFechaActual.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
		dcFechaActual.setEnabled(Boolean.FALSE);
		dcFechaActual.setDateFormatString("EEE dd 'de' MMMM 'del' YYYY");
		dcFechaActual.setBounds(15, 435, 250, 30);
		pp.add(dcFechaActual);
		dcFechaEntrega.setForeground(Color.BLACK);
		dcFechaEntrega.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
		dcFechaEntrega.setDateFormatString("EEE dd 'de' MMMM 'del' YYYY");
		dcFechaEntrega.setBounds(15, 505, 250, 30);
		pp.add(dcFechaEntrega);

		//combobox
		cboCarreras.setForeground(Color.BLACK);
		cboCarreras.setFont(new Font("Segoe UI Symbol", Font.BOLD + Font.ITALIC, 14));
		cboCarreras.setBounds(15, 25, 267, 30);
		pca.add(cboCarreras);

		//pca
		pca.setLayout(null);
		pca.setBackground(new Color(242, 245, 242));
		pca.setBorder(BorderFactory.createTitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Seleccione Carrera/ï¿½rea", TitledBorder.ABOVE_TOP, TitledBorder.LEFT, new Font("Segoe UI Symbol", Font.BOLD, 12), Color.BLACK));
		pca.setBounds(15, 330, 300, 70);
		pp.add(pca);
		pf.setLayout(null);
		pf.setBackground(new Color(242, 245, 242));
		pf.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.BLACK, 5, Boolean.FALSE), "Seleccione una fotografï¿½a o ï¿½magen.", TitledBorder.ABOVE_TOP, TitledBorder.LEFT, new Font("Segoe UI Symbol", Font.BOLD, 14), Color.BLACK));
		pf.setBounds(350, 15, 737, 600);
		pp.add(pf);

		//botones
		btnEditar.setForeground(Color.BLACK);
		btnEditar.setFont(new Font("Segoe UI Symbol", Font.BOLD, 12));
		btnEditar.setText("Editar");
		btnEditar.setIcon(new ImageIcon("src/main/resources/Iconos/editar.png"));
		btnEditar.setBorder(BorderFactory.createLineBorder(new Color(171, 235, 198), 2, Boolean.FALSE));
		btnEditar.setBounds(15, 550, 150, 50);
		pp.add(btnEditar);
		btnEliminar.setForeground(Color.BLACK);
		btnEliminar.setFont(new Font("Segoe UI Symbol", Font.BOLD, 12));
		btnEliminar.setText("Eliminar");
		btnEliminar.setIcon(new ImageIcon("src/main/resources/Iconos/eliminar.png"));
		btnEliminar.setBorder(BorderFactory.createLineBorder(new Color(174, 214, 241), 2, Boolean.FALSE));
		btnEliminar.setBounds(180, 550, 150, 50);
		pp.add(btnEliminar);
		btnVolver.setForeground(Color.BLACK);
		btnVolver.setFont(new Font("Segoe UI Symbol", Font.BOLD, 12));
		btnVolver.setText("Volver");
		btnVolver.setIcon(new ImageIcon("src/main/resources/Iconos/volver.png"));
		btnVolver.setBorder(BorderFactory.createLineBorder(new Color(245, 183, 177), 2, Boolean.FALSE));
		btnVolver.setBounds(97, 610, 150, 50);
		pp.add(btnVolver);
		btnMetodo.setForeground(Color.BLACK);
		btnMetodo.setFont(new Font("Segoe UI Symbol", Font.BOLD, 12));
		btnMetodo.setText("Opciones");
		btnMetodo.setIcon(new ImageIcon("src/main/resources/Iconos/opciones.png"));
		btnMetodo.setToolTipText("Clic para ver la opciones de cï¿½ptura.");
		btnMetodo.setBorder(BorderFactory.createLineBorder(new Color(245, 183, 177), 2, Boolean.FALSE));
		btnMetodo.setBounds(300, 530, 150, 50);
		pf.add(btnMetodo);

		//propiedades del pp
		pp.setLayout(null);
		pp.setBackground(new Color(242, 245, 242));
		pp.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5, Boolean.FALSE));
		pp.setBounds(0, 0, 1100, 677);
		this.add(pp);

		this.setLayout(null);
		this.setSize(1100, 677);
		this.setLocationRelativeTo(null);
		this.setResizable(Boolean.FALSE);
		this.setTitle(".: Modificar o Eliminar Servicios. - SiRiUS. :.");
		this.setIconImage(new ImageIcon("src/main/resources/Iconos/servicios_16.png").getImage());

		txtNombre.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				//----------------------------
			}

			@Override
			public void keyPressed(KeyEvent e) {
				//----------------------------
			}

			@Override
			public void keyReleased(KeyEvent e) {
				txtNombreKeyReleased(e);
			}
		});
		txtCosto.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				txtCostoKeyTyped(e);
			}

			@Override
			public void keyPressed(KeyEvent e) {
				//------------------------------
			}

			@Override
			public void keyReleased(KeyEvent e) {
				//-------------------------------
			}
		});
		btnEditar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnEditarActionPerformed(e);
			}
		});
		btnEliminar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnEliminarActionPerformed(e);
			}
		});
		btnVolver.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnVolverActionPerformed(e);
			}
		});
		btnMetodo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnMetodoActionPerformed(e);
			}
		});
	}

	private void txtNombreKeyReleased(KeyEvent evt) {
		if (txtNombre.getText().length() == 60) {
			JOptionPane.showMessageDialog(ServicioSeleccionado.this, "Has alcanzado el lÃ­mite de caracteres permitidos.", "LÃ­mite Alcanzado. - SiriUS.", JOptionPane.WARNING_MESSAGE, warning);
			txtNombre.requestFocus();
		}
	}

	public File getOrigen() {
		return origen;
	}

	public void setOrigen(File origen) {
		this.origen = origen;
	}

	private void deshabilitar() {
		txtNombre.setEnabled(Boolean.FALSE);
		txtCosto.setEnabled(Boolean.FALSE);
		txtDescripcion.setEnabled(Boolean.FALSE);
		txtDias.setEnabled(Boolean.FALSE);
		cboCarreras.setEnabled(Boolean.FALSE);
		dcFechaEntrega.setEnabled(Boolean.FALSE);
		btnMetodo.setEnabled(Boolean.FALSE);
	}

	private void habilitar() {
		txtNombre.setEnabled(Boolean.TRUE);
		txtCosto.setEnabled(Boolean.TRUE);
		txtDescripcion.setEnabled(Boolean.TRUE);
		txtDias.setEnabled(Boolean.TRUE);
		cboCarreras.setEnabled(Boolean.TRUE);
		dcFechaEntrega.setEnabled(Boolean.TRUE);
		btnMetodo.setEnabled(Boolean.TRUE);
	}

	private void txtCostoKeyTyped(KeyEvent evt) {
		char key = evt.getKeyChar();
		if ((key < '0' || key > '9') && (key != KeyEvent.VK_BACK_SPACE) && (key != '.' || txtCosto.getText().contains(".")) && key != '\b') {
			JOptionPane.showMessageDialog(ServicioSeleccionado.this, "Este campo solo aceptar nÃºmeros.", "Caracteres Incorrectos. - SiRiUS.", JOptionPane.WARNING_MESSAGE, warning);
			evt.consume();
		}
	}

	private void btnEditarActionPerformed(ActionEvent evt) {
		if (btnEditar.getText().equals("Editar")) {
			this.habilitar();
			btnEliminar.setEnabled(Boolean.FALSE);
			btnEditar.setText("Guardar");
			btnEditar.setIcon(new ImageIcon("src/main/resources/Iconos/guardar.png"));
		} else {
			String nombre = txtNombre.getText();
			String costo = txtCosto.getText();
			String descripcion = txtDescripcion.getText();
			String dias = txtDias.getText();
			String carrera = datosCA[cboCarreras.getSelectedIndex()][0].toString();
			if (nombre.isEmpty()) {
				JOptionPane.showMessageDialog(ServicioSeleccionado.this, "El campo nombre del servicio no puede quedar vacÃ­o.", "Campo vacÃ­o. - SiRiUS.", JOptionPane.WARNING_MESSAGE, warning);
				txtNombre.requestFocus();
			} else if (costo.isEmpty()) {
				JOptionPane.showMessageDialog(ServicioSeleccionado.this, "El campo costo del servicio no puede quedar vacÃ­o.", "Campo vacÃ­o. - SiRiUS.", JOptionPane.WARNING_MESSAGE, warning);
				txtCosto.requestFocus();
			} else if (descripcion.isEmpty()) {
				JOptionPane.showMessageDialog(ServicioSeleccionado.this, "El campo descripciÃ³n no puede quedar vacÃ­o.", "Campo vacÃ­o. - SiRiUS.", JOptionPane.WARNING_MESSAGE, warning);
				txtDescripcion.requestFocus();
			} else if (dias.isEmpty()) {
				JOptionPane.showMessageDialog(ServicioSeleccionado.this, "El campo dÃ­as de entrega no puede quedar vacÃ­o.", "Campo vacÃ­o. - SiRiUS.", JOptionPane.WARNING_MESSAGE, warning);
				txtDias.requestFocus();
			} else if (origen == null) {
				JOptionPane.showMessageDialog(ServicioSeleccionado.this, "Debes seleccionar una imagen o tomar una fotografÃ­a.", "SelecciÃ³n de imagen o fotografÃ­a. - SiRiUS.", JOptionPane.WARNING_MESSAGE, warning);
				btnMetodo.requestFocus();
			} else if (s.servicioModificar(id, nombre, carrera, Double.parseDouble(costo), descripcion, dias, origen)) {
				JOptionPane.showMessageDialog(ServicioSeleccionado.this, "La modificiaciÃ³n se ejecutÃ³ correctamente.", "ModificaciÃ³n correcta. - SiRiUS.", JOptionPane.INFORMATION_MESSAGE, exito);
				this.abrirListado(usuario);
				this.dispose();
			} else {
				JOptionPane.showMessageDialog(ServicioSeleccionado.this, "La modificaciÃ³n no se ejecutÃ³, debido a un error de comunicaciÃ³n con la base de datos. Esta ventana se cerrarÃ¡.", "Error de comunicaciÃ³n. - SiRiUS.", JOptionPane.ERROR_MESSAGE, error);
				this.dispose();
			}
		}
	}

	private void btnEliminarActionPerformed(ActionEvent evt) {
		int res = JOptionPane.showConfirmDialog(ServicioSeleccionado.this, "Â¿Realmente deseas eliminar este servicio?", "Confirmar eliminaciÃ³n. - SiRiUS.", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, pregunta);
		if (res == JOptionPane.YES_OPTION) {
			if (s.servicioEliminar(id)) {
				JOptionPane.showMessageDialog(ServicioSeleccionado.this, "La eliminaciÃ³n se ejecutÃ³ correctamente.", "EliminaciÃ³n correcta. - SiRiUS.", JOptionPane.INFORMATION_MESSAGE, exito);
				this.abrirListado(usuario);
				this.dispose();
			} else {
				JOptionPane.showMessageDialog(ServicioSeleccionado.this, "La eliminaciÃ³n no se ejecutÃ³ debido a un error de comunicaciÃ³n con la base de datos. Esta ventana se cerrarÃ¡.", "Error de comunicaciÃ³n. - SiRiUS.", JOptionPane.ERROR_MESSAGE, error);
				this.dispose();
			}
		}
	}

	private void btnVolverActionPerformed(ActionEvent evt) {
		this.abrirListado(usuario);
		this.dispose();
	}

	private void btnMetodoActionPerformed(ActionEvent evt) {
		Metodo m = new Metodo(this, rootPaneCheckingEnabled, "ServicioSeleccionado");
		m.setVisible(Boolean.TRUE);
		if (origen != null) {
			imagen = new ImageIcon(origen.getPath());
			imagenScaled = new ImageIcon(imagen.getImage().getScaledInstance(lblFotografia.getWidth(), lblFotografia.getHeight(), Image.SCALE_SMOOTH));
			lblFotografia.setIcon(imagenScaled);
		}
	}

	private void abrirListado(String[] usuario) {
		ListadoServicios ls = new ListadoServicios(usuario);
		int x = (VentanaPrincipal.escritorio.getWidth() / 2) - ls.getWidth() / 2;
		int y = (VentanaPrincipal.escritorio.getHeight() / 2) - ls.getHeight() / 2;
		if (ls.isShowing()) {
			ls.setLocation(x, y);
		} else {
			VentanaPrincipal.escritorio.add(ls);
			ls.setLocation(x, y);
			ls.show();
		}
	}
}
