package Interfaces.CarreraArea;

import Interfaces.Inicio.VentanaPrincipal;
import MySQL.CarreraArea;
import TextPrompt.TextPrompt;
import com.jgoodies.binding.adapter.ComboBoxAdapter;
import com.jgoodies.binding.value.ValueHolder;
import com.jgoodies.binding.value.ValueModel;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class CarreraAreaSeleccionado extends JInternalFrame {
	private JPanel pp = new JPanel();
	private JPanel pt = new JPanel();
	private JLabel lblClave = new JLabel("Clave");
	private JLabel lblNombre = new JLabel("Nombre Carrera/Área");
	private JLabel lblDescripcion = new JLabel("Descripción");
	private JLabel lblJefe = new JLabel("Nombre del Jéfe o Director");
	private JTextField txtClave = new JTextField();
	private JTextField txtNombre = new JTextField();
	private JTextField txtJefe = new JTextField();
	private JTextArea txtDescripcion = new JTextArea();
	private JScrollPane scrollPane = new JScrollPane(txtDescripcion);
	private JComboBox<String> cboTipo = new JComboBox<>();
	private JButton btnEditar = new JButton();
	private JButton btnEliminar = new JButton();
	private JButton btnVolver = new JButton();

	//Iconos para JOptionPanel
	private ImageIcon warning = new ImageIcon("src/main/resources/Iconos/warning.png");
	private ImageIcon error = new ImageIcon("src/main/resources/Iconos/error.png");
	private ImageIcon exito = new ImageIcon("src/main/resources/Iconos/exito.png");
	private ImageIcon pregunta = new ImageIcon("src/main/resources/Iconos/pregunta.png");
	private ImageIcon informacion = new ImageIcon("src/main/resources/Iconos/info.png");

	//Variables y objetos.
	private String[] usuario;
	private String clave = null;
	private CarreraArea ca = new CarreraArea();
	private Object[] datoCA;

	public CarreraAreaSeleccionado(String[] usuario, String clave) {
		initComponents();
		deshabilitar();
		this.usuario = usuario;
		this.clave = clave;
		txtClave.setText(clave);
		datoCA = ca.carreraAreaSeleccionar(clave);
		txtNombre.setText(datoCA[1].toString());
		txtDescripcion.setText(datoCA[2].toString());
		txtJefe.setText(datoCA[3].toString());
		cboTipo.setSelectedItem(datoCA[2].toString());
	}

	private void initComponents() {
		//propiedades del pp
		pp.setLayout(null);
		pp.setBackground(new Color(242, 245, 242));
		pp.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5, Boolean.FALSE));
		pp.setBounds(0, 0, 568, 375);
		this.add(pp);


		//propiedades de los jlabels
		lblClave.setForeground(Color.BLACK);
		lblClave.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
		lblClave.setBounds(15, 15, 150, 30);
		pp.add(lblClave);
		lblNombre.setForeground(Color.BLACK);
		lblNombre.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
		lblNombre.setBounds(15, 85, 150, 30);
		pp.add(lblNombre);
		lblDescripcion.setForeground(Color.BLACK);
		lblDescripcion.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
		lblDescripcion.setBounds(15, 155, 150, 30);
		pp.add(lblDescripcion);
		lblJefe.setForeground(Color.BLACK);
		lblJefe.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
		lblJefe.setBounds(300, 15, 200, 30);
		pp.add(lblJefe);

		//propiedades de las cajas de texto.
		txtClave.setForeground(Color.BLACK);
		txtClave.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
		txtClave.setHorizontalAlignment(SwingConstants.CENTER);
		txtClave.setBounds(15, 50, 250, 30);
		pp.add(txtClave);
		txtNombre.setForeground(Color.BLACK);
		txtNombre.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
		txtNombre.setHorizontalAlignment(SwingConstants.CENTER);
		txtNombre.setBounds(15, 120, 250, 30);
		pp.add(txtNombre);
		TextPrompt placeholder = new TextPrompt("Escriba una descripción amplia de la carrera o del área.", txtDescripcion);
		placeholder.changeAlpha(0.75f);
		placeholder.setHorizontalAlignment(SwingConstants.LEADING);
		placeholder.setVerticalAlignment(SwingConstants.TOP);
		placeholder.setFont(new Font("Segoe UI Symbol", Font.BOLD + Font.ITALIC, 14));
		scrollPane.setViewportView(txtDescripcion);
		txtDescripcion.setForeground(Color.BLACK);
		txtDescripcion.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
		txtDescripcion.setWrapStyleWord(Boolean.TRUE);
		txtDescripcion.setLineWrap(Boolean.TRUE);
		scrollPane.setBounds(15, 190, 535, 90);
		pp.add(scrollPane);
		txtJefe.setForeground(Color.BLACK);
		txtJefe.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
		txtJefe.setHorizontalAlignment(SwingConstants.CENTER);
		txtJefe.setBounds(300, 50, 250, 30);
		pp.add(txtJefe);

		//Propiedades del combobox
		cboTipo.setForeground(Color.BLACK);
		cboTipo.setFont(new Font("Segoe UI Symbol", Font.BOLD + Font.ITALIC, 14));
		ArrayList opciones = new ArrayList();
		opciones.add("Carrera");
		opciones.add("Área");
		ValueModel opcionesCombo = new ValueHolder("Seleccione una opción");
		ComboBoxAdapter cboTipoAdapter = new ComboBoxAdapter(opciones, opcionesCombo);
		cboTipo.setModel(cboTipoAdapter);
		cboTipo.setBounds(15, 15, 217, 30);
		pt.add(cboTipo);

		//Propiedades del los botones
		btnEditar.setForeground(Color.BLACK);
		btnEditar.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
		btnEditar.setText("Editar");
		btnEditar.setIcon(new ImageIcon("src/main/resources/Iconos/editar.png"));
		btnEditar.setBorder(BorderFactory.createLineBorder(new Color(171, 235, 198), 2, Boolean.FALSE));
		btnEditar.setBounds(15, 300, 150, 50);
		pp.add(btnEditar);
		btnEliminar.setForeground(Color.BLACK);
		btnEliminar.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
		btnEliminar.setText("Eliminar");
		btnEliminar.setIcon(new ImageIcon("src/main/resources/Iconos/eliminar.png"));
		btnEliminar.setBorder(BorderFactory.createLineBorder(new Color(174, 214, 241), 2, Boolean.FALSE));
		btnEliminar.setBounds(208, 300, 150, 50);
		pp.add(btnEliminar);
		btnVolver.setForeground(Color.BLACK);
		btnVolver.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
		btnVolver.setText("Volver");
		btnVolver.setIcon(new ImageIcon("src/main/resources/Iconos/volver.png"));
		btnVolver.setBorder(BorderFactory.createLineBorder(new Color(245, 183, 177), 2, Boolean.FALSE));
		btnVolver.setBounds(400, 300, 150, 50);
		pp.add(btnVolver);

		//Propiedades del pt
		pt.setLayout(null);
		pt.setBackground(new Color(242, 245, 242));
		pt.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
		pt.setBounds(300, 100, 250, 63);
		pp.add(pt);

		this.setLayout(null);
		this.setSize(570, 400);
		this.setTitle(".: Agregar Carrera o Área. :.");
		this.setFrameIcon(new ImageIcon("src/main/resources/Iconos/iconitotese_16.png"));

		//Eventos
		txtClave.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				//----------------------------
			}

			@Override
			public void keyPressed(KeyEvent e) {
				txtClaveKeyPressed(e);
			}

			@Override
			public void keyReleased(KeyEvent e) {
				//----------------------------
			}
		});
		txtNombre.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				//----------------------------
			}

			@Override
			public void keyPressed(KeyEvent e) {
				txtNombreKeyPressed(e);
			}

			@Override
			public void keyReleased(KeyEvent e) {
				//----------------------------
			}
		});
		txtJefe.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				//----------------------------
			}

			@Override
			public void keyPressed(KeyEvent e) {
				txtJefeKeyPressed(e);
			}

			@Override
			public void keyReleased(KeyEvent e) {
				//----------------------------
			}
		});
		btnEditar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnEditarActionPerformed(e);
			}
		});
		btnVolver.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnVolverActionPerformed(e);
			}
		});
		btnEliminar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnEliminarActionPerformed(e);
			}
		});
	}

	private void deshabilitar() {
		txtClave.setEnabled(Boolean.FALSE);
		txtNombre.setEnabled(Boolean.FALSE);
		txtDescripcion.setEnabled(Boolean.FALSE);
		txtJefe.setEnabled(Boolean.FALSE);
		cboTipo.setEnabled(Boolean.FALSE);
	}

	private void habilitar() {
		txtClave.setEnabled(Boolean.FALSE);
		txtNombre.setEnabled(Boolean.TRUE);
		txtDescripcion.setEnabled(Boolean.TRUE);
		txtJefe.setEnabled(Boolean.TRUE);
		cboTipo.setEnabled(Boolean.TRUE);
	}

	private void txtClaveKeyPressed(KeyEvent evt) {
		if (txtClave.getText().length() == 8) {
			JOptionPane.showInternalMessageDialog(CarreraAreaSeleccionado.this, "Has alcanzado el límite de caracteres permitido.", "Límite Alcanzado. - SiRiUS.", JOptionPane.WARNING_MESSAGE, warning);
			txtClave.requestFocus();
		}
	}

	private void txtNombreKeyPressed(KeyEvent evt) {
		if (txtNombre.getText().length() == 60) {
			JOptionPane.showInternalMessageDialog(CarreraAreaSeleccionado.this, "Has alcanzado el límite de caracteres permitido.", "Límite Alcanzado. - SiRiUS.", JOptionPane.WARNING_MESSAGE, warning);
			txtNombre.requestFocus();
		}
	}

	private void txtJefeKeyPressed(KeyEvent evt) {
		if (txtJefe.getText().length() == 60) {
			JOptionPane.showInternalMessageDialog(CarreraAreaSeleccionado.this, "Has alcanzado el límite de caracteres permitido.", "Límite Alcanzado. - SiRiUS.", JOptionPane.WARNING_MESSAGE, warning);
			txtJefe.requestFocus();
		}
	}

	private void btnEditarActionPerformed(ActionEvent evt) {
		if (btnEditar.getText().equals("Editar")) {
			habilitar();
			btnEliminar.setEnabled(Boolean.FALSE);
			btnEditar.setText("Guardar");
			btnEditar.setIcon(new ImageIcon("src/main/resources/Iconos/guardar.png"));
		} else {
			String nombre = txtNombre.getText();
			String descripcion = txtDescripcion.getText();
			String jefe = txtJefe.getText();
			String tipo = cboTipo.getSelectedItem().toString();
			if (clave.isEmpty()) {
				JOptionPane.showInternalMessageDialog(CarreraAreaSeleccionado.this, "El campo clave no puede quedar vacío.", "Campo vacío.", JOptionPane.WARNING_MESSAGE, warning);
				txtClave.requestFocus();
			} else if (nombre.isEmpty()) {
				JOptionPane.showInternalMessageDialog(CarreraAreaSeleccionado.this, "El campo nombre completo no puede quedar vacío.", "Campo vacío.", JOptionPane.WARNING_MESSAGE, warning);
				txtNombre.requestFocus();
			} else if (descripcion.isEmpty()) {
				JOptionPane.showInternalMessageDialog(CarreraAreaSeleccionado.this, "Debes escribir una descripción.", "Campo vacío.", JOptionPane.WARNING_MESSAGE, warning);
				txtDescripcion.requestFocus();
			} else if (jefe.isEmpty()) {
				JOptionPane.showInternalMessageDialog(CarreraAreaSeleccionado.this, "El campo nombre del jéfe o director no puede quedar vacío.", "Campo vacío.", JOptionPane.WARNING_MESSAGE, warning);
				txtJefe.requestFocus();
			} else if (tipo.equals("Seleccione una opción")) {
				JOptionPane.showInternalMessageDialog(CarreraAreaSeleccionado.this, "Debes seleccionar una opción de la lista desplegable.", "Selección de opción.", JOptionPane.WARNING_MESSAGE, warning);
				cboTipo.requestFocus();
			} else if (ca.carreraAreaModificar(clave, nombre, descripcion, jefe, tipo)) {
				JOptionPane.showInternalMessageDialog(CarreraAreaSeleccionado.this, "La modificación se ejecutó correctamente.", "Modificación correcta. - SiRiUS.", JOptionPane.INFORMATION_MESSAGE, exito);
				abrirListadoCA(usuario);
				dispose();
			} else {
				JOptionPane.showInternalMessageDialog(CarreraAreaSeleccionado.this, "La modificación no se ejecutó, debido a un error de comunicación con la base de datos. Esta ventana se cerrará.", "Error de comunicación. - SiRiUS.", JOptionPane.ERROR_MESSAGE, error);
				this.dispose();
			}
		}
	}

	private void btnEliminarActionPerformed(ActionEvent evt) {
		int res = JOptionPane.showInternalConfirmDialog(CarreraAreaSeleccionado.this, "¿Realmente deseas eliminar este registro?", "Confirmar elimincación. - SiRiUS.", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, pregunta);
		if (res == JOptionPane.YES_OPTION) {
			if (ca.carreraAreaEliminado(clave)) {
				JOptionPane.showInternalMessageDialog(CarreraAreaSeleccionado.this, "La eliminación se ejecutó correctamente.", "Eliminación correcta. - SiRiUS.", JOptionPane.INFORMATION_MESSAGE, exito);
				abrirListadoCA(usuario);
				dispose();
			}
		}
	}

	private void btnVolverActionPerformed(ActionEvent evt) {
		abrirListadoCA(usuario);
		this.dispose();
	}

	private String[] abrirListadoCA(String[] usuario) {
		ListadoCarreraArea lca = new ListadoCarreraArea(usuario);
		int x = (VentanaPrincipal.escritorio.getWidth() / 2) - lca.getWidth() / 2;
		int y = (VentanaPrincipal.escritorio.getHeight() / 2) - lca.getHeight() / 2;
		if (lca.isShowing()) {
			lca.setLocation(x, y);
		} else {
			VentanaPrincipal.escritorio.add(lca);
			lca.setLocation(x, y);
			lca.show();
		}
		return usuario;
	}
}
