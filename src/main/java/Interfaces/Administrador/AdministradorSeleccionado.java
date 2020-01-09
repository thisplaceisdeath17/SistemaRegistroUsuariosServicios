package Interfaces.Administrador;

import Interfaces.Inicio.VentanaPrincipal;
import MySQL.Administrador;
import MySQL.CarreraArea;
import MySQL.TipoAdministrador;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class AdministradorSeleccionado extends JInternalFrame {
	private JPanel pp = new JPanel();
	private JPanel pca = new JPanel();
	private JPanel pta = new JPanel();
	private JSeparator sp = new JSeparator(SwingConstants.VERTICAL);
	private JLabel lblLogin = new JLabel("Nombre de Usuario");
	private JLabel lblNombre = new JLabel("Nombre Completo");
	private JLabel lblPassword = new JLabel("Contraseña");
	private JLabel lblNuevaPassword = new JLabel("Nueva Contraseña");
	private JLabel lblConfPassword = new JLabel("Confirmar Contraseña");
	private JTextField txtLogin = new JTextField();
	private JTextField txtNombre = new JTextField();
	private JPasswordField txtPassword = new JPasswordField();
	private JPasswordField txtNuevaPassword = new JPasswordField();
	private JPasswordField txtConfPassword = new JPasswordField();
	private JComboBox<String> cboCarrera = new JComboBox<>();
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

	//Variables y objetos
	private String login = null;
	private Administrador a = new Administrador();
	private TipoAdministrador ta = new TipoAdministrador();
	private CarreraArea ca = new CarreraArea();
	private String[] usuario;
	private Object[][] datosCA;
	private Object[][] datosTA;
	private Object[] datosAdmin;

	public AdministradorSeleccionado(String[] usuario, String login) {
		initComponents();
		deshabilitar();
		this.usuario = usuario;
		this.login = login;
		datosCA = ca.carreraAreaConsultar("Todos");
		txtLogin.setText(login);
		datosAdmin = a.administradorSeleccionar(login);
		int indiceCA = 0;
		for (int ca = 0; ca < datosCA.length; ca++) {
			cboCarrera.addItem(datosCA[ca][1].toString());
			if (datosCA[ca][0].toString().equals(datosAdmin[3].toString())) {
				indiceCA = ca;
			}
		}
		int indiceTA = 0;
		datosTA = ta.tipoAdministradorConsultar();
		for (int ta = 0; ta < datosTA.length; ta++) {
			cboTipo.addItem(datosTA[ta][1].toString());
			if (datosTA[ta][0].toString().equals(datosAdmin[4].toString())) {
				indiceTA = ta;
			}
		}
		txtNombre.setText(datosAdmin[1].toString());
	}

	private void initComponents() {
		//Propiedades de los jlabel
		lblLogin.setForeground(Color.BLACK);
		lblLogin.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
		lblLogin.setBounds(15, 15, 150, 30);
		pp.add(lblLogin);
		lblNombre.setForeground(Color.BLACK);
		lblNombre.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
		lblNombre.setBounds(15, 85, 150, 30);
		pp.add(lblNombre);
		lblPassword.setForeground(Color.BLACK);
		lblPassword.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
		lblPassword.setBounds(15, 155, 150, 30);
		pp.add(lblPassword);
		lblNuevaPassword.setForeground(Color.BLACK);
		lblNuevaPassword.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
		lblNuevaPassword.setBounds(15, 225, 150, 30);
		pp.add(lblNuevaPassword);
		lblConfPassword.setForeground(Color.BLACK);
		lblConfPassword.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
		lblConfPassword.setBounds(15, 295, 150, 30);
		pp.add(lblConfPassword);

		//Propiedades de las cajas de texto
		txtLogin.setForeground(Color.BLACK);
		txtLogin.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
		txtLogin.setHorizontalAlignment(SwingConstants.CENTER);
		txtLogin.setBounds(15, 50, 250, 30);
		pp.add(txtLogin);
		txtNombre.setForeground(Color.BLACK);
		txtNombre.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
		txtNombre.setHorizontalAlignment(SwingConstants.CENTER);
		txtNombre.setBounds(15, 120, 250, 30);
		pp.add(txtNombre);
		txtPassword.setForeground(Color.BLACK);
		txtPassword.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
		txtPassword.setHorizontalAlignment(SwingConstants.CENTER);
		txtPassword.setBounds(15, 190, 250, 30);
		pp.add(txtPassword);
		txtNuevaPassword.setForeground(Color.BLACK);
		txtNuevaPassword.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
		txtNuevaPassword.setHorizontalAlignment(SwingConstants.CENTER);
		txtNuevaPassword.setBounds(15, 260, 250, 30);
		pp.add(txtNuevaPassword);
		txtConfPassword.setForeground(Color.BLACK);
		txtConfPassword.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
		txtConfPassword.setHorizontalAlignment(SwingConstants.CENTER);
		txtConfPassword.setBounds(15, 330, 250, 30);
		pp.add(txtConfPassword);

		//Panel de carrera
		pca.setLayout(null);
		pca.setBackground(new Color(242, 245, 242));
		pca.setBorder(BorderFactory.createTitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Seleccione Carrera o �rea", TitledBorder.LEFT, TitledBorder.ABOVE_TOP, new Font("Segoe UI Symbol", Font.BOLD, 12), Color.BLACK));
		pca.setBounds(310, 40, 275, 90);
		pp.add(pca);

		//Combo box carrera
		cboCarrera.setForeground(Color.BLACK);
		cboCarrera.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
		cboCarrera.setBounds(15, 37, 250, 30);
		pca.add(cboCarrera);

		//Panel de tipo
		pta.setLayout(null);
		pta.setBackground(new Color(242, 245, 242));
		pta.setBorder(BorderFactory.createTitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Seleccione Tipo Administrador", TitledBorder.LEFT, TitledBorder.ABOVE_TOP, new Font("Segoe UI Symbol", Font.BOLD, 12), Color.BLACK));
		pta.setBounds(310, 150, 275, 90);
		pp.add(pta);

		//Combo box tipo
		cboTipo.setForeground(Color.BLACK);
		cboTipo.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
		cboTipo.setBounds(15, 37, 250, 30);
		pta.add(cboTipo);

		//Separador
		sp.setBounds(280, 15, 1, 344);
		pp.add(sp);

		//Botones
		btnEditar.setForeground(Color.BLACK);
		btnEditar.setFont(new Font("Segoe UI Symbol", Font.BOLD, 12));
		btnEditar.setText("Editar");
		btnEditar.setBorder(BorderFactory.createLineBorder(new Color(171, 235, 198), 2, Boolean.FALSE));
		btnEditar.setIcon(new ImageIcon("src/main/resources/Iconos/editar.png"));
		btnEditar.setBounds(15, 380, 150, 50);
		pp.add(btnEditar);
		btnEliminar.setForeground(Color.BLACK);
		btnEliminar.setFont(new Font("Segoe UI Symbol", Font.BOLD, 12));
		btnEliminar.setText("Eliminar");
		btnEliminar.setBorder(BorderFactory.createLineBorder(new Color(174, 214, 241), 2, Boolean.FALSE));
		btnEliminar.setIcon(new ImageIcon("src/main/resources/Iconos/eliminar.png"));
		btnEliminar.setBounds(225, 380, 150, 50);
		pp.add(btnEliminar);
		btnVolver.setForeground(Color.BLACK);
		btnVolver.setFont(new Font("Segoe UI Symbol", Font.BOLD, 12));
		btnVolver.setText("Volver");
		btnVolver.setBorder(BorderFactory.createLineBorder(new Color(245, 183, 177), 2, Boolean.FALSE));
		btnVolver.setIcon(new ImageIcon("src/main/resources/Iconos/volver.png"));
		btnVolver.setBounds(433, 380, 150, 50);
		pp.add(btnVolver);

		//Propiedades del pp
		pp.setLayout(null);
		pp.setBackground(new Color(242, 245, 242));
		pp.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5, Boolean.FALSE));
		pp.setBounds(0, 0, 598, 455);
		this.add(pp);

		//Propiedades del internal frame
		this.setLayout(null);
		this.setSize(600, 480);
		this.setIconifiable(Boolean.TRUE);
		this.setTitle(".: Administrador Seleccionado. - SiRiUS :.");
		this.setFrameIcon(new ImageIcon("src/main/resources/Iconos/administrador_16.png"));

		//Eventos de los componentes.
		txtLogin.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				//----------------------------
			}

			@Override
			public void keyPressed(KeyEvent e) {
				txtLoginKeyPressed(e);
			}

			@Override
			public void keyReleased(KeyEvent e) {
				//-------------------------------
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
				//-------------------------------
			}
		});
		txtPassword.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				//----------------------------
			}

			@Override
			public void keyPressed(KeyEvent e) {
				txtPasswordKeyPressed(e);
			}

			@Override
			public void keyReleased(KeyEvent e) {
				//-------------------------------
			}
		});
		txtNuevaPassword.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				//-------------------------------
			}

			@Override
			public void keyPressed(KeyEvent e) {
				txtNuevaPasswordKeyPressed(e);
			}

			@Override
			public void keyReleased(KeyEvent e) {
				//-------------------------------
			}
		});
		txtConfPassword.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				//----------------------------
			}

			@Override
			public void keyPressed(KeyEvent e) {
				txtConfPasswordKeyPressed(e);
			}

			@Override
			public void keyReleased(KeyEvent e) {
				txtConfPasswordKeyReleased(e);
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
	}

	private void deshabilitar() {
		txtLogin.setEnabled(Boolean.FALSE);
		txtNombre.setEnabled(Boolean.FALSE);
		txtPassword.setEnabled(Boolean.FALSE);
		txtNuevaPassword.setEnabled(Boolean.FALSE);
		txtConfPassword.setEnabled(Boolean.FALSE);
		cboCarrera.setEnabled(Boolean.FALSE);
		cboTipo.setEnabled(Boolean.FALSE);
	}

	private void habilitar() {
		txtLogin.setEnabled(Boolean.FALSE);
		txtNombre.setEnabled(Boolean.TRUE);
		txtPassword.setEnabled(Boolean.TRUE);
		txtNuevaPassword.setEnabled(Boolean.TRUE);
		txtConfPassword.setEnabled(Boolean.TRUE);
		cboCarrera.setEnabled(Boolean.TRUE);
		cboTipo.setEnabled(Boolean.TRUE);
	}

	private void txtLoginKeyPressed(KeyEvent evt) {
		if (txtLogin.getText().length() == 15) {
			JOptionPane.showInternalMessageDialog(AdministradorSeleccionado.this, "Has alcanzado el límite de caracteres permitido.", "Límite Alcanzado. - SiRiUS.", JOptionPane.WARNING_MESSAGE, warning);
			txtLogin.requestFocus();
			evt.consume();
		}
	}

	private void txtNombreKeyPressed(KeyEvent evt) {
		if (txtNombre.getText().length() == 60) {
			JOptionPane.showInternalMessageDialog(AdministradorSeleccionado.this, "Has alcanzado el límite de caracteres permitido.", "Límite Alcanzado. - SiRiUS.", JOptionPane.WARNING_MESSAGE, warning);
			txtNombre.requestFocus();
			evt.consume();
		}
	}

	private void txtPasswordKeyPressed(KeyEvent evt) {
		if (txtPassword.getText().length() == 15) {
			JOptionPane.showInternalMessageDialog(AdministradorSeleccionado.this, "Has alcanzado el límite de caracteres permitido.", "Límite Alcanzado. - SiRiUS.", JOptionPane.WARNING_MESSAGE, warning);
			txtPassword.requestFocus();
			evt.consume();
		}
	}

	private void txtNuevaPasswordKeyPressed(KeyEvent evt) {
		if (txtNuevaPassword.getText().length() == 15) {
			JOptionPane.showInternalMessageDialog(AdministradorSeleccionado.this, "Has alcanzado el límite de caracteres permitido.", "Límite Alcanzado. - SiRiUS.", JOptionPane.WARNING_MESSAGE, warning);
			txtNuevaPassword.requestFocus();
			evt.consume();
		}
	}

	private void txtConfPasswordKeyPressed(KeyEvent evt) {
		if (txtConfPassword.getText().length() == 15) {
			JOptionPane.showInternalMessageDialog(AdministradorSeleccionado.this, "Has alcanzado el límite de caracteres permitido.", "Límite Alcanzado. - SiRiUS.", JOptionPane.WARNING_MESSAGE, warning);
			txtConfPassword.requestFocus();
			evt.consume();
		}
	}

	private void txtConfPasswordKeyReleased(KeyEvent evt) {
		String nuevaPassword = txtNuevaPassword.getText();
		String confPassword = txtConfPassword.getText();
		if (nuevaPassword.equals(confPassword)) {
			txtNuevaPassword.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2, Boolean.FALSE));
			txtConfPassword.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2, Boolean.FALSE));
		} else {
			txtNuevaPassword.setBorder(BorderFactory.createLineBorder(Color.RED, 2, Boolean.FALSE));
			txtConfPassword.setBorder(BorderFactory.createLineBorder(Color.RED, 2, Boolean.FALSE));
		}
	}

	private void btnEditarActionPerformed(ActionEvent evt) {
		if (btnEditar.getText().equals("Editar")) {
			habilitar();
			btnEditar.setText("Guardar");
			btnEditar.setIcon(new ImageIcon("src/main/resources/Iconos/guardar.png"));
			btnEliminar.setEnabled(Boolean.FALSE);
		} else {
			String nombre = txtNombre.getText();
			String password = txtPassword.getText();
			String nuevaPassword = txtNuevaPassword.getText();
			String confPassword = txtConfPassword.getText();
			String carrera = datosCA[cboCarrera.getSelectedIndex()][0].toString();
			String tipo = datosTA[cboTipo.getSelectedIndex()][0].toString();
			String admin = String.valueOf(usuario[0]);
			datosAdmin = a.administradorValidar(login, password);
			if (nombre.isEmpty()) {
				JOptionPane.showInternalMessageDialog(AdministradorSeleccionado.this, "El campo nombre completo no puede quedar vacío.", "Campo vacío. - SiRiUS.", JOptionPane.WARNING_MESSAGE, warning);
				txtNombre.requestFocus();
			} else if (password.isEmpty()) {
				JOptionPane.showInternalMessageDialog(AdministradorSeleccionado.this, "El campo contraseña no puede quedar vacío.", "Campo vacío. - SiRiUS.", JOptionPane.WARNING_MESSAGE, warning);
				txtPassword.requestFocus();
			} else if (nuevaPassword.isEmpty()) {
				JOptionPane.showInternalMessageDialog(AdministradorSeleccionado.this, "El campo nueva contraseña no puede quedar vacío.", "Campo vacío. - SiRiUS.", JOptionPane.WARNING_MESSAGE, warning);
				txtNuevaPassword.requestFocus();
			} else if (confPassword.isEmpty()) {
				JOptionPane.showInternalMessageDialog(AdministradorSeleccionado.this, "El campo confirmar contraseña no puede quedar vacío.", "Campo vacío. - SiRiUS.", JOptionPane.WARNING_MESSAGE, warning);
				txtConfPassword.requestFocus();
			} else if (datosAdmin[0] == null) {
				JOptionPane.showInternalMessageDialog(AdministradorSeleccionado.this, "La contraseña no coincide con la registrada.", "Contraseñas diferentes. - SiRiUS.", JOptionPane.WARNING_MESSAGE, warning);
				txtPassword.requestFocus();
				txtPassword.setText("");
			} else if (!confPassword.equals(nuevaPassword)) {
				JOptionPane.showInternalMessageDialog(AdministradorSeleccionado.this, "La nueva contraseña no coincide con la confirmación.", "Contraseñas diferentes. - SiRiUS.", JOptionPane.WARNING_MESSAGE, warning);
				txtNuevaPassword.requestFocus();
				txtConfPassword.setText("");
			} else if (a.administradorModificar(login, nombre, nuevaPassword, carrera, tipo)) {
				JOptionPane.showInternalMessageDialog(AdministradorSeleccionado.this, "La modificación se ejecutó correctamente.", "Modificación correcta. - SiRiUS.", JOptionPane.INFORMATION_MESSAGE, exito);
				if (admin.equals(login)) {
					JOptionPane.showInternalMessageDialog(AdministradorSeleccionado.this, "Para que la modificación surta efecto, se cerrará el sistema y deberá iniciar una nueva sesión.", "Cierre de sesión. - SiRiUS.", JOptionPane.INFORMATION_MESSAGE, informacion);
					System.exit(0);
				}
			} else {
				JOptionPane.showInternalMessageDialog(AdministradorSeleccionado.this, "La modificación no se ejecutó, debido a un error de comunicación con la base de datos. Esta ventana se cerrará.", "Error de comunicación. - SiRiUS.", JOptionPane.ERROR_MESSAGE, error);
				this.dispose();
			}
		}
	}

	private void btnEliminarActionPerformed(ActionEvent evt) {
		int res = JOptionPane.showInternalConfirmDialog(AdministradorSeleccionado.this, "¿Estas seguro de eliminar este registro?", "Confirmar eliminación. - SiRiUS.", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (res == JOptionPane.YES_OPTION) {
			if (a.administradorEliminar(login)) {
				JOptionPane.showInternalMessageDialog(AdministradorSeleccionado.this, "La eliminación se ejecutó correctamente.", "Eliminación correcta. - SiRiUS.", JOptionPane.INFORMATION_MESSAGE, exito);
				abrirListado();
			} else {
				JOptionPane.showInternalMessageDialog(AdministradorSeleccionado.this, "La eliminación no se ejecutó, debido a un error de comunicación con la base de datos. Esta ventana se cerrará.", "Error de comunicación. - SiRiUS.", JOptionPane.ERROR_MESSAGE, error);
				this.dispose();
			}
		}
	}

	private void btnVolverActionPerformed(ActionEvent evt) {
		abrirListado();
	}

	private void abrirListado() {
		ListadoAdministrador la = new ListadoAdministrador(usuario);
		int x = (VentanaPrincipal.escritorio.getWidth() / 2) - la.getWidth() / 2;
		int y = (VentanaPrincipal.escritorio.getHeight() / 2) - la.getHeight() / 2;
		if (la.isShowing()) {
			la.setLocation(x, y);
		} else {
			VentanaPrincipal.escritorio.add(la);
			la.setLocation(x, y);
			la.show();
		}
		this.dispose();
	}
}
