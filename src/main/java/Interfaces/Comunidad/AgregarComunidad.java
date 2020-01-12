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
import java.io.File;
import java.util.ArrayList;

public class AgregarComunidad extends JFrame {
	private JPanel pp = new JPanel();
	private JPanel pf = new JPanel();
	private JPanel pca = new JPanel();
	private JPanel pta = new JPanel();
	private JLabel lblMatricula = new JLabel("Matricula");
	private JLabel lblNombre = new JLabel("Nombre Completo");
	private JLabel lblRFID = new JLabel("Número RFID");
	private JLabel lblFotografia = new JLabel();
	private JTextField txtMatricula = new JTextField();
	private JTextField txtNombre = new JTextField();
	private JTextField txtRFID = new JTextField();
	private JComboBox<String> cboCarreras = new JComboBox<>();
	private JComboBox<String> cboTipo = new JComboBox<>();
	private JButton btnGuardar = new JButton();
	private JButton btnVolver = new JButton();
	private JButton btnMetodo = new JButton();

	//Iconos para JOptionPanel
	private ImageIcon warning = new ImageIcon("src/main/resources/Iconos/warning.png");
	private ImageIcon error = new ImageIcon("src/main/resources/Iconos/error.png");
	private ImageIcon exito = new ImageIcon("src/main/resources/Iconos/exito.png");
	private ImageIcon pregunta = new ImageIcon("src/main/resources/Iconos/pregunta.png");
	private ImageIcon informacion = new ImageIcon("src/main/resources/Iconos/info.png");

	//Variables y objetos
	private String[] usuario;
	private File origen = null;
	private Comunidad c = new Comunidad();
	private CarreraArea ca = new CarreraArea();
	private Object[][] datosCA;
	private ImageIcon imagenScaled;

	public AgregarComunidad(String[] usuario) {
		initComponents();
		this.usuario = usuario;
		datosCA = ca.carreraAreaConsultar("Todos");
		for (int ca = 0; ca < datosCA.length; ca++) {
			cboCarreras.addItem(datosCA[ca][1].toString());
		}
		ImageIcon no_image = new ImageIcon("src/main/resources/Iconos/no_image.png");
		ImageIcon imagen = new ImageIcon(no_image.getImage().getScaledInstance(lblFotografia.getWidth(), lblFotografia.getHeight(), Image.SCALE_SMOOTH));
		lblFotografia.setIcon(imagen);
	}

	private void initComponents() {
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
		lblFotografia.setBounds(15, 35, 480, 360);
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
		pf.setBounds(350, 15, 637, 573);
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
		btnGuardar.setForeground(Color.BLACK);
		btnGuardar.setFont(new Font("Segoe UI Symbol", Font.BOLD, 12));
		btnGuardar.setText("Guardar");
		btnGuardar.setIcon(new ImageIcon("src/main/resources/Iconos/guardar.png"));
		btnGuardar.setBorder(BorderFactory.createLineBorder(new Color(171, 235, 198), 2, Boolean.FALSE));
		btnGuardar.setBounds(15, 500, 150, 50);
		pp.add(btnGuardar);
		btnVolver.setForeground(Color.BLACK);
		btnVolver.setFont(new Font("Segoe UI Symbol", Font.BOLD, 12));
		btnVolver.setText("Volver");
		btnVolver.setIcon(new ImageIcon("src/main/resources/Iconos/volver.png"));
		btnVolver.setBorder(BorderFactory.createLineBorder(new Color(245, 183, 177), 2, Boolean.FALSE));
		btnVolver.setBounds(180, 500, 150, 50);
		pp.add(btnVolver);
		btnMetodo.setForeground(Color.BLACK);
		btnMetodo.setFont(new Font("Segoe UI Symbol", Font.BOLD, 12));
		btnMetodo.setText("Opciones");
		btnMetodo.setIcon(new ImageIcon("src/main/resources/Iconos/opciones.png"));
		btnMetodo.setToolTipText("Clic para ver la opciones de cáptura.");
		btnMetodo.setBorder(BorderFactory.createLineBorder(new Color(245, 183, 177), 2, Boolean.FALSE));
		btnMetodo.setBounds(250, 483, 150, 50);
		pf.add(btnMetodo);

		//propiedades del pp
		pp.setLayout(null);
		pp.setBackground(new Color(242, 245, 242));
		pp.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5, Boolean.FALSE));
		pp.setBounds(0, 0, 1000, 600);
		this.add(pp);

		this.setLayout(null);
		this.setSize(1000, 600);
		this.setLocationRelativeTo(null);
		this.setUndecorated(Boolean.TRUE);

		//Eventos
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

	private void btnMetodoActionPerformed(ActionEvent evt) {
		Metodo m = new Metodo("AgregarComunidad");
		m.setVisible(Boolean.TRUE);
	}
}
