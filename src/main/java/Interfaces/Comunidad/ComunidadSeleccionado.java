package Interfaces.Comunidad;

import Interfaces.Metodo.Metodo;
import MySQL.CarreraArea;
import MySQL.Comunidad;
import TextPrompt.TextPrompt;
import com.jgoodies.binding.adapter.ComboBoxAdapter;
import com.jgoodies.binding.value.ValueHolder;
import com.jgoodies.binding.value.ValueModel;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.ArrayList;

public class ComunidadSeleccionado extends JFrame {
	private JPanel pp = new JPanel();
	private JPanel pf = new JPanel();
	private JPanel pca = new JPanel();
	private JPanel pta = new JPanel();
	private JLabel lblMatricula = new JLabel("Matricula");
	private JLabel lblNombre = new JLabel("Nombre Completo");
	private JLabel lblRFID = new JLabel("Número RFID");
	private JLabel lblSaldo = new JLabel("Saldo ($MXN)");
	private JLabel lblFotografia = new JLabel();
	private JTextField txtMatricula = new JTextField();
	private JTextField txtNombre = new JTextField();
	private JTextField txtRFID = new JTextField();
	private JTextField txtSaldo = new JTextField();
	private JComboBox<String> cboCarreras = new JComboBox<>();
	private JComboBox<String> cboTipo = new JComboBox<>();
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
	private String matricula = null;
	private Comunidad c = new Comunidad();
	private File origen = null;
	private ImageIcon imagen, imagenScaled;
	private Object[] datosCom;
	private CarreraArea ca = new CarreraArea();
	private Object[][] datosCA;
	private String[] usuario;

	public ComunidadSeleccionado(String matricula, String[] usuario) {
		initComponent();
		deshabilitar();
		this.matricula = matricula;
		this.usuario = usuario;
		datosCA = ca.carreraAreaConsultar("Todos");
		for (int ca = 0; ca < datosCA.length; ca++) {
			cboCarreras.addItem(datosCA[ca][1].toString());
		}
		txtMatricula.setText(matricula);
		txtMatricula.setEnabled(Boolean.FALSE);
		datosCom = c.comunidadSeleccionar(matricula);
		txtNombre.setText(datosCom[1].toString());
		txtRFID.setText(datosCom[2].toString());
		cboCarreras.setSelectedItem(datosCom[9].toString());
		cboTipo.setSelectedItem(datosCom[7].toString());
		txtSaldo.setText(String.valueOf(datosCom[6].toString()));
		origen = new File(new File(".").getAbsolutePath() + "/src/main/resources/Fotografias/Comunidad/" + datosCom[4].toString());
		imagen = new ImageIcon("src/main/resources/Fotografias/Comunidad/" + datosCom[4].toString());
		imagenScaled = new ImageIcon(imagen.getImage().getScaledInstance(lblFotografia.getWidth(), lblFotografia.getHeight(), Image.SCALE_SMOOTH));
		lblFotografia.setIcon(imagenScaled);
		this.setIconImage(new ImageIcon("src/main/resources/Iconos/logo.png").getImage());
	}

	private void initComponent() {
		//jlabels
		lblMatricula.setForeground(Color.BLACK);
		lblMatricula.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
		lblMatricula.setBounds(15, 15, 150, 30);
		pp.add(lblMatricula);
		lblNombre.setForeground(Color.BLACK);
		lblNombre.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
		lblNombre.setBounds(15, 85, 150, 30);
		pp.add(lblNombre);
		lblRFID.setForeground(Color.BLACK);
		lblRFID.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
		lblRFID.setBounds(15, 155, 150, 30);
		pp.add(lblRFID);
		lblSaldo.setForeground(Color.BLACK);
		lblSaldo.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
		lblSaldo.setBounds(15, 380, 150, 30);
		pp.add(lblSaldo);
		lblFotografia.setBounds(50, 35, 640, 480);
		lblFotografia.setHorizontalAlignment(SwingConstants.CENTER);
		lblFotografia.setBorder(BorderFactory.createLineBorder(new Color(237, 187, 153), 2, Boolean.FALSE));
		pf.add(lblFotografia);

		//textfields
		txtMatricula.setForeground(Color.BLACK);
		txtMatricula.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
		txtMatricula.setHorizontalAlignment(SwingConstants.CENTER);
		txtMatricula.setBounds(15, 50, 300, 30);
		pp.add(txtMatricula);
		txtNombre.setForeground(Color.BLACK);
		txtNombre.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
		txtNombre.setHorizontalAlignment(SwingConstants.CENTER);
		txtNombre.setBounds(15, 120, 300, 30);
		pp.add(txtNombre);
		TextPrompt prompt = new TextPrompt("Pasa la tarjeta RFID para registrar.", txtRFID);
		prompt.setForeground(Color.BLACK);
		prompt.changeAlpha(0.5f);
		prompt.changeStyle(Font.BOLD + Font.ITALIC);
		prompt.setIcon(new ImageIcon("src/main/resources/Iconos/sugerencia.png"));
		txtRFID.setForeground(Color.BLACK);
		txtRFID.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
		txtRFID.setHorizontalAlignment(SwingConstants.CENTER);
		txtRFID.setBounds(15, 190, 300, 30);
		pp.add(txtRFID);
		txtSaldo.setForeground(Color.BLACK);
		txtSaldo.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
		txtSaldo.setHorizontalAlignment(SwingConstants.CENTER);
		txtSaldo.setBounds(15, 415, 300, 30);
		pp.add(txtSaldo);

		//paneles
		pca.setLayout(null);
		pca.setBackground(new Color(242, 245, 242));
		pca.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
		pca.setBounds(15, 240, 300, 63);
		pp.add(pca);
		pta.setLayout(null);
		pta.setBackground(new Color(242, 245, 242));
		pta.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
		pta.setBounds(15, 320, 300, 63);
		pp.add(pta);
		pf.setLayout(null);
		pf.setBackground(new Color(242, 245, 242));
		pf.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.BLACK, 5, Boolean.FALSE), "Seleccione una fotografía o ímagen.", TitledBorder.ABOVE_TOP, TitledBorder.LEFT, new Font("Segoe UI Symbol", Font.BOLD, 14), Color.BLACK));
		pf.setBounds(350, 15, 737, 600);
		pp.add(pf);

		//comboboxes
		cboCarreras.setForeground(Color.BLACK);
		cboCarreras.setFont(new Font("Segoe UI Symbol", Font.BOLD + Font.ITALIC, 14));
		cboCarreras.setBounds(15, 15, 267, 30);
		pca.add(cboCarreras);
		ArrayList opciones = new ArrayList();
		opciones.add("Alumno");
		opciones.add("Docente");
		opciones.add("Administrativo");
		ValueModel modeloTipo = new ValueHolder("Seleccione una opción");
		ComboBoxAdapter cboTipoAdapter = new ComboBoxAdapter(opciones, modeloTipo);
		cboTipo.setModel(cboTipoAdapter);
		cboTipo.setForeground(Color.BLACK);
		cboTipo.setFont(new Font("Segoe UI Symbol", Font.BOLD + Font.ITALIC, 14));
		cboTipo.setBounds(15, 15, 267, 30);
		pta.add(cboTipo);

		//Botones
		btnEditar.setForeground(Color.BLACK);
		btnEditar.setFont(new Font("Segoe UI Symbol", Font.BOLD, 12));
		btnEditar.setText("Editar");
		btnEditar.setIcon(new ImageIcon("src/main/resources/Iconos/editar.png"));
		btnEditar.setBorder(BorderFactory.createLineBorder(new Color(171, 235, 198), 2, Boolean.FALSE));
		btnEditar.setBounds(15, 500, 150, 50);
		pp.add(btnEditar);
		btnEliminar.setForeground(Color.BLACK);
		btnEliminar.setFont(new Font("Segoe UI Symbol", Font.BOLD, 12));
		btnEliminar.setText("Eliminar");
		btnEliminar.setIcon(new ImageIcon("src/main/resources/Iconos/eliminar.png"));
		btnEliminar.setBorder(BorderFactory.createLineBorder(new Color(174, 214, 241), 2, Boolean.FALSE));
		btnEliminar.setBounds(180, 500, 150, 50);
		pp.add(btnEliminar);
		btnVolver.setForeground(Color.BLACK);
		btnVolver.setFont(new Font("Segoe UI Symbol", Font.BOLD, 12));
		btnVolver.setText("Volver");
		btnVolver.setIcon(new ImageIcon("src/main/resources/Iconos/volver.png"));
		btnVolver.setBorder(BorderFactory.createLineBorder(new Color(245, 183, 177), 2, Boolean.FALSE));
		btnVolver.setBounds(95, 560, 150, 50);
		pp.add(btnVolver);
		btnMetodo.setForeground(Color.BLACK);
		btnMetodo.setFont(new Font("Segoe UI Symbol", Font.BOLD, 12));
		btnMetodo.setText("Opciones");
		btnMetodo.setIcon(new ImageIcon("src/main/resources/Iconos/opciones.png"));
		btnMetodo.setToolTipText("Clic para ver la opciones de cáptura.");
		btnMetodo.setBorder(BorderFactory.createLineBorder(new Color(245, 183, 177), 2, Boolean.FALSE));
		btnMetodo.setBounds(300, 530, 150, 50);
		pf.add(btnMetodo);

		//propiedades del pp
		pp.setLayout(null);
		pp.setBackground(new Color(242, 245, 242));
		pp.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5, Boolean.FALSE));
		pp.setBounds(0, 0, 1100, 627);
		this.add(pp);

		this.setLayout(null);
		this.setSize(1100, 627);
		this.setLocationRelativeTo(null);
		this.setUndecorated(Boolean.TRUE);

		//Eventos
		txtMatricula.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				txtMatriculaKeyTyped(e);
			}

			@Override
			public void keyPressed(KeyEvent e) {
				//------------------------------
			}

			@Override
			public void keyReleased(KeyEvent e) {
				txtMatriculaKeyReleased(e);
			}
		});
		txtNombre.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				//------------------------------
			}

			@Override
			public void keyPressed(KeyEvent e) {
				//------------------------------
			}

			@Override
			public void keyReleased(KeyEvent e) {
				txtNombreKeyReleased(e);
			}
		});
		txtRFID.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				txtRFIDKeyTyped(e);
			}

			@Override
			public void keyPressed(KeyEvent e) {
				//------------------------------
			}

			@Override
			public void keyReleased(KeyEvent e) {
				txtRFIDKeyReleased(e);
			}
		});
		txtSaldo.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				txtSaldoKeyTyped(e);
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

	public File getOrigen() {
		return origen;
	}

	public void setOrigen(File origen) {
		this.origen = origen;
	}

	private void deshabilitar() {
		btnMetodo.setEnabled(Boolean.FALSE);
		txtMatricula.setEnabled(Boolean.FALSE);
		txtNombre.setEnabled(Boolean.FALSE);
		txtRFID.setEnabled(Boolean.FALSE);
		txtSaldo.setEnabled(Boolean.FALSE);
		cboTipo.setEnabled(Boolean.FALSE);
		cboCarreras.setEnabled(Boolean.FALSE);
	}

	private void habilitar() {
		txtMatricula.setEnabled(Boolean.FALSE);
		txtNombre.setEnabled(Boolean.TRUE);
		txtRFID.setEnabled(Boolean.TRUE);
		txtSaldo.setEnabled(Boolean.TRUE);
		cboTipo.setEnabled(Boolean.TRUE);
		cboCarreras.setEnabled(Boolean.TRUE);
	}

	private void txtMatriculaKeyTyped(KeyEvent evt) {
		char key = evt.getKeyChar();
		if ((key < '0' || key > '9') && key != '\b') {
			JOptionPane.showMessageDialog(ComunidadSeleccionado.this, "Este campo solo aceptar números.", "Caracteres Incorrectos. - SiRiUS.", JOptionPane.WARNING_MESSAGE, warning);
			evt.consume();
		}
	}

	private void txtMatriculaKeyReleased(KeyEvent evt) {
		if (txtMatricula.getText().length() == 11) {
			JOptionPane.showMessageDialog(ComunidadSeleccionado.this, "Has alcanzado el límite de caracteres permitido.", "Límite Alcanzado. - SiRiUS.", JOptionPane.WARNING_MESSAGE, warning);
			txtMatricula.requestFocus();
			evt.consume();
		}
	}

	private void txtNombreKeyReleased(KeyEvent evt) {
		if (txtNombre.getText().length() == 60) {
			JOptionPane.showMessageDialog(ComunidadSeleccionado.this, "Has alcanzado el límite de caracteres permitido.", "Límite Alcanzado. - SiRiUS.", JOptionPane.WARNING_MESSAGE, warning);
			txtNombre.requestFocus();
			evt.consume();
		}
	}

	private void txtRFIDKeyTyped(KeyEvent evt) {
		char key = evt.getKeyChar();
		if ((key < '0' || key > '9') && key != '\b') {
			JOptionPane.showMessageDialog(ComunidadSeleccionado.this, "Este campo solo aceptar números.", "Caracteres Incorrectos. - SiRiUS.", JOptionPane.WARNING_MESSAGE, warning);
			evt.consume();
		}
	}

	private void txtRFIDKeyReleased(KeyEvent evt) {
		if (txtRFID.getText().length() == 11) {
			JOptionPane.showMessageDialog(ComunidadSeleccionado.this, "Has alcanzado el límite de caracteres permitido.", "Límite Alcanzado. - SiRiUS.", JOptionPane.WARNING_MESSAGE, warning);
			txtRFID.requestFocus();
			evt.consume();
		}
	}

	private void txtSaldoKeyTyped(KeyEvent evt) {
		char key = evt.getKeyChar();
		if ((key < '0' || key > '9') && key != '\b') {
			JOptionPane.showMessageDialog(ComunidadSeleccionado.this, "Este campo solo aceptar números.", "Caracteres Incorrectos. - SiRiUS.", JOptionPane.WARNING_MESSAGE, warning);
			evt.consume();
		}
	}

	private void btnEditarActionPerformed(ActionEvent evt) {
		if (btnEditar.getText().equals("Editar")) {
			habilitar();
			btnEliminar.setEnabled(Boolean.FALSE);
			btnMetodo.setEnabled(Boolean.TRUE);
			btnEditar.setText("Guardar");
			btnEditar.setIcon(new ImageIcon("src/main/resources/Iconos/guardar.png"));
		} else {
			String nombre = txtNombre.getText();
			String rfid = txtRFID.getText();
			String carrera = datosCA[cboCarreras.getSelectedIndex()][0].toString();
			String tipo = cboTipo.getSelectedItem().toString();
			double saldo = Double.parseDouble(txtSaldo.getText());
			if (nombre.isEmpty()) {
				JOptionPane.showMessageDialog(ComunidadSeleccionado.this, "El campo nombre completo no puede quedar vacío.", "Campo vacío. - SiRiUS.", JOptionPane.WARNING_MESSAGE, warning);
				txtNombre.requestFocus();
			} else if (rfid.isEmpty()) {
				JOptionPane.showMessageDialog(ComunidadSeleccionado.this, "El campo número rfid no puede quedar vacío.", "Campo vacío. - SiRiUS.", JOptionPane.WARNING_MESSAGE, warning);
				txtRFID.requestFocus();
			} else if (cboTipo.getSelectedItem().equals("Seleccione una opción")) {
				JOptionPane.showMessageDialog(ComunidadSeleccionado.this, "Debes seleccionar una opción de la lista desplegable.", "Seleccione una opción. - SiRiUS.", JOptionPane.WARNING_MESSAGE, warning);
				cboTipo.requestFocus();
			} else if (txtSaldo.getText().isEmpty()) {
				JOptionPane.showMessageDialog(ComunidadSeleccionado.this, "El campo saldo no puede quedar vacío.", "Campo vacío. - SiRiUS.", JOptionPane.WARNING_MESSAGE, warning);
				txtSaldo.requestFocus();
			} else if (origen == null) {
				JOptionPane.showMessageDialog(ComunidadSeleccionado.this, "Debes seleccionar una imagen o tomar una fotografía.", "Selección de imagen. - SiRiUS.", JOptionPane.INFORMATION_MESSAGE, warning);
				lblFotografia.requestFocus();
			} else if (c.comunidadModificar(matricula, nombre, rfid, carrera, origen, saldo, tipo)) {
				JOptionPane.showMessageDialog(ComunidadSeleccionado.this, "La modificación se ejecutó correctamente.", "Modificación correcta. - SiRiUS.", JOptionPane.INFORMATION_MESSAGE, exito);
				ListadoComunidad lc = new ListadoComunidad(usuario);
				lc.setVisible(Boolean.TRUE);
				this.dispose();
			} else {
				JOptionPane.showMessageDialog(ComunidadSeleccionado.this, "La modificación no se ejecutó debido a un error de comunicación con la base de datos. Esta ventana se cerrará.", "Error de modificación. - SiRiUS.", JOptionPane.ERROR_MESSAGE, error);
				this.dispose();
			}
		}
	}

	private void btnEliminarActionPerformed(ActionEvent evt) {
		int res = JOptionPane.showConfirmDialog(ComunidadSeleccionado.this, "¿Realmente deseas eliminar este registro?", "Confirmar eliminación. - SiRiUS.", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, pregunta);
		if (res == JOptionPane.YES_OPTION) {
			if (c.comunidadEliminar(matricula)) {
				JOptionPane.showMessageDialog(ComunidadSeleccionado.this, "La eliminación se ejecutó correctamente.", "Eliminación correcta. - SiRiUS.", JOptionPane.INFORMATION_MESSAGE, exito);
				ListadoComunidad lc = new ListadoComunidad(usuario);
				lc.setVisible(Boolean.TRUE);
				this.dispose();
			} else {
				JOptionPane.showMessageDialog(ComunidadSeleccionado.this, "La eliminación no se ejecutó debido a un error de comunicación con la base de datos. Esta ventana se cerrará.", "Error de eliminación. - SiRiUS.", JOptionPane.ERROR_MESSAGE, error);
				this.dispose();
			}
		}
	}

	private void btnVolverActionPerformed(ActionEvent evt) {
		ListadoComunidad lc = new ListadoComunidad(usuario);
		lc.setVisible(Boolean.TRUE);
		this.dispose();
	}

	private void btnMetodoActionPerformed(ActionEvent evt) {
		Metodo m = new Metodo(this, rootPaneCheckingEnabled, "ComunidadSeleccionado");
		m.setVisible(Boolean.TRUE);
		if (origen != null) {
			ImageIcon imagen = new ImageIcon(origen.getPath());
			ImageIcon imagenScaled = new ImageIcon(imagen.getImage().getScaledInstance(lblFotografia.getWidth(), lblFotografia.getHeight(), Image.SCALE_SMOOTH));
			lblFotografia.setIcon(imagenScaled);
		}
	}
}
