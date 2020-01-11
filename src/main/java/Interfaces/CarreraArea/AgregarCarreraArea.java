package Interfaces.CarreraArea;

import TextPrompt.TextPrompt;

import javax.swing.*;
import java.awt.*;

public class AgregarCarreraArea extends JInternalFrame {
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

	public AgregarCarreraArea(String[] usuario) {
		initComponents();
	}

	private void initComponents() {
		//propiedades del pp
		pp.setLayout(null);
		pp.setBackground(new Color(242, 245, 242));
		pp.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5, Boolean.FALSE));
		pp.setBounds(0, 0, 598, 375);
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
		lblJefe.setBounds(15, 285, 200, 30);
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
		TextPrompt placeholder = new TextPrompt("Escriba una descripción amplia del carrera o el área.", txtDescripcion);
		placeholder.changeAlpha(0.75f);
		placeholder.setHorizontalAlignment(SwingConstants.LEADING);
		placeholder.setVerticalAlignment(SwingConstants.TOP);
		placeholder.setFont(new Font("Segoe UI Symbol", Font.BOLD + Font.ITALIC, 14));
		scrollPane.setViewportView(txtDescripcion);
		txtDescripcion.setForeground(Color.BLACK);
		txtDescripcion.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
		txtDescripcion.setWrapStyleWord(Boolean.TRUE);
		txtDescripcion.setLineWrap(Boolean.TRUE);
		scrollPane.setBounds(15, 190, 567, 90);
		pp.add(scrollPane);
		txtJefe.setForeground(Color.BLACK);
		txtJefe.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
		txtJefe.setHorizontalAlignment(SwingConstants.CENTER);
		txtJefe.setBounds(15, 320, 250, 30);
		pp.add(txtJefe);

		this.setLayout(null);
		this.setSize(600, 400);
		this.setTitle(".: Agregar Carrera o Área. :.");
		this.setFrameIcon(new ImageIcon("src/main/resources/Iconos/iconitotese_16.png"));
	}
}
