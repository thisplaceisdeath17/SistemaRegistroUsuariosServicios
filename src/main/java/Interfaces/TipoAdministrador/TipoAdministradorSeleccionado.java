package Interfaces.TipoAdministrador;

import Interfaces.Inicio.VentanaPrincipal;
import MySQL.TipoAdministrador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TipoAdministradorSeleccionado extends JInternalFrame {
	private JPanel pp = new JPanel();
	private JLabel lblTipo = new JLabel("Descripción");
	private JTextField txtDescripcion = new JTextField();
	private JButton btnEditar = new JButton();
	private JButton btnEliminar = new JButton();
	private JButton btnVolver = new JButton();

	//Iconos para JOptionPanel
	private ImageIcon warning = new ImageIcon("src/main/resources/Iconos/warning.png");
	private ImageIcon error = new ImageIcon("src/main/resources/Iconos/error.png");
	private ImageIcon exito = new ImageIcon("src/main/resources/Iconos/exito.png");
	private ImageIcon pregunta = new ImageIcon("src/main/resources/Iconos/pregunta.png");

	//Variables y objetos.
	private String id = null;
	private String descripcion = null;
	private TipoAdministrador ta = new TipoAdministrador();
	private Object[] datosTA;
	private String[] usuario;

	public TipoAdministradorSeleccionado(String[] usuario, String id, String descripcion) {
		initComponents();
		datosTA = ta.tipoAdministradorSeleccionar(id, descripcion);
		txtDescripcion.setText(datosTA[1].toString());
		this.usuario = usuario;
		this.id = id;
		this.descripcion = descripcion;
		txtDescripcion.setEnabled(Boolean.FALSE);
		System.out.println("+++++++++++++++" + descripcion);
	}

	private void initComponents() {
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

		//jbutton edita
		btnEditar.setFont(new Font("Segoe UI Symbol", Font.BOLD, 12));
		btnEditar.setForeground(Color.BLACK);
		btnEditar.setText("Editar");
		btnEditar.setIcon(new ImageIcon("src/main/resources/Iconos/editar.png"));
		btnEditar.setBorder(BorderFactory.createLineBorder(new Color(171, 235, 198), 2, Boolean.FALSE));
		btnEditar.setBounds(15, 100, 150, 50);
		pp.add(btnEditar);
		//jbutton eliminar
		btnEliminar.setFont(new Font("Segoe UI Symbol", Font.BOLD, 12));
		btnEliminar.setForeground(Color.BLACK);
		btnEliminar.setText("Eliminar");
		btnEliminar.setIcon(new ImageIcon("src/main/resources/Iconos/eliminar.png"));
		btnEliminar.setBorder(BorderFactory.createLineBorder(new Color(174, 214, 241), 2, Boolean.FALSE));
		btnEliminar.setBounds(183, 100, 150, 50);
		pp.add(btnEliminar);
		//jbutton volver
		btnVolver.setFont(new Font("Segoe UI Symbol", Font.BOLD, 12));
		btnVolver.setForeground(Color.BLACK);
		btnVolver.setText("Volver");
		btnVolver.setIcon(new ImageIcon("src/main/resources/Iconos/volver.png"));
		btnVolver.setBorder(BorderFactory.createLineBorder(new Color(245, 183, 177), 2, Boolean.FALSE));
		btnVolver.setBounds(100, 160, 150, 50);
		pp.add(btnVolver);

		//Propiedades del pp
		pp.setLayout(null);
		pp.setBackground(new Color(242, 245, 242));
		pp.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5, Boolean.FALSE));
		pp.setBounds(0, 0, 348, 225);
		this.add(pp);

		//Propiedades del internal frame
		this.setLayout(null);
		this.setSize(350, 250);
		this.setTitle(".: Tipo Administrador Seleccionado. - SiRiUS. :.");
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

	private void txtDescripcionKeyTyped(KeyEvent evt) {
		char key = evt.getKeyChar();
		if (key == KeyEvent.VK_ENTER) {
			btnEditarActionPerformed(null);
			evt.consume();
		}
	}

	private void txtDescripcionKeyPressed(KeyEvent evt) {
		if (txtDescripcion.getText().length() == 20) {
			JOptionPane.showInternalMessageDialog(TipoAdministradorSeleccionado.this, "Has alcanzado el límite de caracteres permitido.", "Límite alcanzado. - SiRiUS.", JOptionPane.WARNING_MESSAGE, warning);
			txtDescripcion.requestFocus();
			evt.consume();
		}
	}

	private void btnEditarActionPerformed(ActionEvent evt) {
		if (btnEditar.getText().equals("Editar")) {
			txtDescripcion.setEnabled(Boolean.TRUE);
			btnEliminar.setEnabled(Boolean.FALSE);
			btnEditar.setText("Guardar");
			btnEditar.setIcon(new ImageIcon("src/main/resources/Iconos/guardar.png"));
		} else {
			String descripcion = txtDescripcion.getText();
			if (descripcion.isEmpty()) {
				JOptionPane.showInternalMessageDialog(TipoAdministradorSeleccionado.this, "El campo descripción no puede quedar vacío.", "Campo vacío. - SiRiUS.", JOptionPane.WARNING_MESSAGE, warning);
				txtDescripcion.requestFocus();
			} else if (ta.tipoAdministradorExiste(descripcion)) {
				JOptionPane.showInternalMessageDialog(TipoAdministradorSeleccionado.this, descripcion + " ya está registrado en la base de datos.", "Dato duplicado. - SiRiUS.", JOptionPane.WARNING_MESSAGE, warning);
				txtDescripcion.setText("");
				txtDescripcion.requestFocus();
			} else if (ta.tipoAdministradorModificar(id, descripcion)) {
				JOptionPane.showInternalMessageDialog(TipoAdministradorSeleccionado.this, "La modificación se ejecutó correctamente.", "Modificación correcta. - SiRiUS.", JOptionPane.INFORMATION_MESSAGE, exito);
				abrirListado();
				this.dispose();
			} else {
				JOptionPane.showInternalMessageDialog(TipoAdministradorSeleccionado.this, "La modificación no se ejecutó, debido a un error de comunicación con la base de datos. Esta ventana se cerrará.", "Error de comunicación. - SiRiUS.", JOptionPane.ERROR_MESSAGE, error);
				this.dispose();
			}
		}
	}

	private void btnEliminarActionPerformed(ActionEvent evt) {
		int res = JOptionPane.showInternalConfirmDialog(TipoAdministradorSeleccionado.this, "¿Estas seguro de eliminar este registro?", "Confirmar elimincación. - SiRiUS.", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, pregunta);
		if (res == JOptionPane.YES_OPTION) {
			if (usuario[2].equals(descripcion)) {
				JOptionPane.showInternalMessageDialog(TipoAdministradorSeleccionado.this, "Este tipo de administrador no puede ser eliminado, ya que, se encuentra actualmente en uso.", "Error de eliminación. - SiRiUS.", JOptionPane.ERROR_MESSAGE, error);
			} else if (ta.tipoAdministradorEliminar(descripcion)) {
				JOptionPane.showInternalMessageDialog(TipoAdministradorSeleccionado.this, "La eliminación se ejecutó correctamente.", "Eliminación correcta. - SiRiUS.", JOptionPane.INFORMATION_MESSAGE, exito);
				abrirListado();
				this.dispose();
			} else {
				JOptionPane.showInternalMessageDialog(TipoAdministradorSeleccionado.this, "La eliminación no se ejecutó, debido a un error de comunicación con la base de datos. Esta ventana se cerrará.", "Error de comunicación. - SiRiUS.", JOptionPane.ERROR_MESSAGE, error);
				this.dispose();
			}
		}
	}

	private void btnVolverActionPerformed(ActionEvent evt) {
		abrirListado();
		this.dispose();
	}

	private void abrirListado() {
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
	}
}
