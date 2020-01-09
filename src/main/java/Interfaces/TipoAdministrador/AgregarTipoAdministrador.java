package Interfaces.TipoAdministrador;

import Interfaces.Inicio.VentanaPrincipal;
import MySQL.TipoAdministrador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class AgregarTipoAdministrador extends JInternalFrame {
	private JPanel pp = new JPanel();
	private JLabel lblTipo = new JLabel("Descripción");
	private JTextField txtDescripcion = new JTextField();
	private JButton btnGuardar = new JButton();
	private JButton btnVolver = new JButton();

	//Iconos para JOptionPanel
	private ImageIcon warning = new ImageIcon("src/main/resources/Iconos/warning.png");
	private ImageIcon error = new ImageIcon("src/main/resources/Iconos/error.png");
	private ImageIcon exito = new ImageIcon("src/main/resources/Iconos/exito.png");
	private ImageIcon pregunta = new ImageIcon("src/main/resources/Iconos/pregunta.png");

	//Variables y objetos.
	private TipoAdministrador ta = new TipoAdministrador();
	private String[] usuario;
	private Object[][] datosTA;

	public AgregarTipoAdministrador(String[] usuario) {
		initComponent();
		this.usuario = usuario;
	}

	private void initComponent() {
		//jlabel descripcion
		lblTipo.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
		lblTipo.setForeground(Color.BLACK);
		lblTipo.setBounds(15, 15, 150, 30);
		pp.add(lblTipo);

		//jtextfield descripcion
		txtDescripcion.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
		txtDescripcion.setForeground(Color.BLACK);
		txtDescripcion.setHorizontalAlignment(SwingConstants.CENTER);
		txtDescripcion.setBounds(15, 50, 318, 30);
		pp.add(txtDescripcion);

		//jbutton guardar
		btnGuardar.setFont(new Font("Segoe UI Symbol", Font.BOLD, 12));
		btnGuardar.setForeground(Color.BLACK);
		btnGuardar.setText("Guardar");
		btnGuardar.setIcon(new ImageIcon("src/main/resources/Iconos/guardar.png"));
		btnGuardar.setBorder(BorderFactory.createLineBorder(new Color(171, 235, 198), 2, Boolean.FALSE));
		btnGuardar.setBounds(15, 100, 150, 50);
		pp.add(btnGuardar);
		//jbutton volver
		btnVolver.setFont(new Font("Segoe UI Symbol", Font.BOLD, 12));
		btnVolver.setForeground(Color.BLACK);
		btnVolver.setText("Volver");
		btnVolver.setIcon(new ImageIcon("src/main/resources/Iconos/volver.png"));
		btnVolver.setBorder(BorderFactory.createLineBorder(new Color(171, 235, 198), 2, Boolean.FALSE));
		btnVolver.setBounds(183, 100, 150, 50);
		pp.add(btnVolver);

		//Propiedades del pp
		pp.setLayout(null);
		pp.setBackground(new Color(242, 245, 242));
		pp.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5, Boolean.FALSE));
		pp.setBounds(0, 0, 348, 165);
		this.add(pp);

		//Propiedades del internal frame
		this.setLayout(null);
		this.setSize(350, 190);
		this.setTitle(".: Agregar Tipo Administrador . - SiRiUS. :.");
		this.setFrameIcon(new ImageIcon("src/main/resources/Iconos/administrador_16.png"));

		txtDescripcion.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				txtDescripcionKeyTyped(e);
			}

			@Override
			public void keyPressed(KeyEvent e) {
				txtDescripcionKeyPressed(e);
			}

			@Override
			public void keyReleased(KeyEvent e) {
				//-------------------------------
			}
		});
		btnGuardar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnGuardarActionPerformed(e);
			}
		});
		btnVolver.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnVolverActionPerformed(e);
			}
		});
	}

	private void txtDescripcionKeyTyped(KeyEvent evt) {
		char key = evt.getKeyChar();
		if (key == KeyEvent.VK_ENTER) {
			btnGuardarActionPerformed(null);
		}
	}

	private void txtDescripcionKeyPressed(KeyEvent evt) {
		if (txtDescripcion.getText().length() == 20) {
			JOptionPane.showInternalMessageDialog(AgregarTipoAdministrador.this, "Has alcanzado el límite de caracteres permitido.", "Límite Alcanzado. - SiRiUS.", JOptionPane.WARNING_MESSAGE, warning);
			txtDescripcion.requestFocus();
			evt.consume();
		}
	}

	private void btnGuardarActionPerformed(ActionEvent evt) {
		String descripcion = txtDescripcion.getText();
		if (descripcion.isEmpty()) {
			JOptionPane.showInternalMessageDialog(AgregarTipoAdministrador.this, "El campo descripción no puede quedar vacío.", "Campo vacío. - SiRiUS.", JOptionPane.WARNING_MESSAGE, warning);
			txtDescripcion.requestFocus();
		} else if (ta.tipoAdministradorExiste(descripcion)) {
			JOptionPane.showInternalMessageDialog(AgregarTipoAdministrador.this, "La descripción que desea ingresar, ya está registrada.", "Campo duplicado. - SiRiUS.", JOptionPane.WARNING_MESSAGE, warning);
			txtDescripcion.setText("");
			txtDescripcion.requestFocus();
		} else if (ta.tipoAdministradorInsertar(descripcion)) {
			JOptionPane.showInternalMessageDialog(AgregarTipoAdministrador.this, "El registro del la descripción se ha ejecutado correctamente.", "Registro correcto. - SiRiUS.", JOptionPane.INFORMATION_MESSAGE, exito);
			txtDescripcion.setText("");
			txtDescripcion.requestFocus();
		} else {
			JOptionPane.showInternalMessageDialog(AgregarTipoAdministrador.this, "El registro no se ejecutó, debido a un error de comunicación con la base de datos. Esta ventana se cerrará.", "Error de comunicación. - SiRiUS.", JOptionPane.ERROR_MESSAGE, error);
			this.dispose();
		}
	}

	private void btnVolverActionPerformed(ActionEvent evt) {
		ListadoTipoAdministrador lta = new ListadoTipoAdministrador(usuario);
		int x = (VentanaPrincipal.escritorio.getWidth() / 2) - lta.getWidth() / 2;
		int y = (VentanaPrincipal.escritorio.getHeight() / 2) - lta.getHeight() / 2;
		if (lta.isShowing()) {
			lta.setLocation(x, y);
		} else {
			VentanaPrincipal.escritorio.add(lta);
			lta.setLocation(x, y);
			lta.show();
		}
		this.dispose();
	}
}
