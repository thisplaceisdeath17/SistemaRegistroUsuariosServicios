package Interfaces.Metodo;

import Interfaces.Comunidad.AgregarComunidad;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Metodo extends JFrame {
	private JPanel pp = new JPanel();
	private JLabel lblFotografia = new JLabel();
	private JButton btnArchivos = new JButton();
	private JButton btnCamara = new JButton();
	private JButton btnVolver = new JButton();

	//Iconos para JOptionPanel
	private ImageIcon warning = new ImageIcon("src/main/resources/Iconos/warning.png");
	private ImageIcon error = new ImageIcon("src/main/resources/Iconos/error.png");
	private ImageIcon exito = new ImageIcon("src/main/resources/Iconos/exito.png");
	private ImageIcon pregunta = new ImageIcon("src/main/resources/Iconos/pregunta.png");
	private ImageIcon informacion = new ImageIcon("src/main/resources/Iconos/info.png");

	//Variables y objetos
	private File origen = null;
	private ImageIcon imagen, imagenScaled;
	private String operacion;
	private Dimension dimension = WebcamResolution.VGA.getSize();
	private Webcam webcam = Webcam.getDefault();
	private WebcamPanel webcamPanel;

	public Metodo(String operacion) {
		initComponents();
		this.operacion = operacion;
		try {
			webcamPanel = new WebcamPanel(webcam, dimension, Boolean.FALSE);
		} catch (Exception ex) {
			btnCamara.setEnabled(Boolean.FALSE);
			JOptionPane.showMessageDialog(this, "La cámara web no está diposnible. Favor de revisar el fallo.", "Error de cámara web. - SiRiUS", JOptionPane.ERROR_MESSAGE, error);
		}
	}

	private void initComponents() {
		//pf
		lblFotografia.setBorder(BorderFactory.createLineBorder(new Color(237, 187, 153), 2, Boolean.FALSE));
		lblFotografia.setBounds(15, 15, 670, 500);
		pp.add(lblFotografia);
		//pp
		pp.setLayout(null);
		pp.setBackground(new Color(242, 245, 242));
		pp.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5, Boolean.FALSE));
		pp.setBounds(0, 0, 700, 600);
		this.add(pp);

		//Botones
		btnArchivos.setForeground(Color.BLACK);
		btnArchivos.setFont(new Font("Segoe UI Symbol", Font.BOLD, 12));
		btnArchivos.setText("Archivos");
		btnArchivos.setIcon(new ImageIcon("src/main/resources/Iconos/archivo.png"));
		btnArchivos.setBounds(15, 530, 150, 50);
		btnArchivos.setBorder(BorderFactory.createLineBorder(new Color(171, 235, 198), 2, Boolean.FALSE));
		pp.add(btnArchivos);
		btnCamara.setForeground(Color.BLACK);
		btnCamara.setFont(new Font("Segoe UI Symbol", Font.BOLD, 12));
		btnCamara.setText("Cámara");
		btnCamara.setIcon(new ImageIcon("src/main/resources/Iconos/camara.png"));
		btnCamara.setBounds(275, 530, 150, 50);
		btnCamara.setBorder(BorderFactory.createLineBorder(new Color(171, 214, 241), 2, Boolean.FALSE));
		pp.add(btnCamara);
		btnVolver.setForeground(Color.BLACK);
		btnVolver.setFont(new Font("Segoe UI Symbol", Font.BOLD, 12));
		btnVolver.setText("Volver");
		btnVolver.setIcon(new ImageIcon("src/main/resources/Iconos/volver.png"));
		btnVolver.setBounds(535, 530, 150, 50);
		btnVolver.setBorder(BorderFactory.createLineBorder(new Color(245, 183, 177), 2, Boolean.FALSE));
		pp.add(btnVolver);

		this.setLayout(null);
		this.setSize(700, 600);
		this.setLocationRelativeTo(null);
		this.setUndecorated(Boolean.TRUE);

		//Eventos
		btnArchivos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnArchivosActionPerformed(e);
			}
		});
	}

	private void btnArchivosActionPerformed(ActionEvent evt) {
		if (btnArchivos.getText().equals("Archivos")) {
			btnCamara.setEnabled(Boolean.FALSE);
			btnArchivos.setIcon(new ImageIcon("src/main/resources/Iconos/guardar.png"));
			btnArchivos.setText("Guardar");
			JFileChooser chooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG PNG JPEG GIF", "jpg", "png", "jpeg", "gif");
			chooser.setFileFilter(filter);
			chooser.showOpenDialog(this);
			origen = chooser.getSelectedFile();
			if (operacion.equals("AgregarComunidad")) {
				((AgregarComunidad) getOwner()).setOrigen(origen);
				imagen = new ImageIcon(origen.getPath());
				imagenScaled = new ImageIcon(imagen.getImage().getScaledInstance(lblFotografia.getWidth(), lblFotografia.getHeight(), Image.SCALE_SMOOTH));
				lblFotografia.setIcon(imagen);
			}
		}
	}
}
