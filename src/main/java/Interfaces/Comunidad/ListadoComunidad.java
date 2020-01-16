package Interfaces.Comunidad;

import MySQL.Comunidad;
import TextPrompt.TextPrompt;
import com.jgoodies.binding.adapter.ComboBoxAdapter;
import com.jgoodies.binding.value.ValueHolder;
import com.jgoodies.binding.value.ValueModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ListadoComunidad extends JFrame {
	private JPanel pp = new JPanel();
	private JTextField txtBusqueda = new JTextField();
	private JLabel lblNumero = new JLabel();
	private JTable table = new JTable();
	private JScrollPane scrollPane = new JScrollPane(table);
	private JComboBox<String> cboFiltro = new JComboBox<>();

	//Iconos para JOptionPanel
	private ImageIcon warning = new ImageIcon("src/main/resources/Iconos/warning.png");
	private ImageIcon error = new ImageIcon("src/main/resources/Iconos/error.png");
	private ImageIcon exito = new ImageIcon("src/main/resources/Iconos/exito.png");
	private ImageIcon pregunta = new ImageIcon("src/main/resources/Iconos/pregunta.png");

	//Variables y objetos
	private String[] usuario;
	private String[] encabezado = {"Matricula", "Nombre Completo", "Carrera/Área", "Fecha de Registro", "Tipo", "Saldo Total"};
	private Comunidad c = new Comunidad();
	private Object[][] datosCom = null;
	private DefaultTableModel modelo = null;
	private int cantidad;

	public ListadoComunidad(String[] usuario) {
		initComponents();
		this.usuario = usuario;
		datosCom = c.comunidadConsultar("Todos");
		this.llenarTabla();
	}

	private void llenarTabla() {
		modelo = new DefaultTableModel(datosCom, encabezado) {
			@Override
			public boolean isCellEditable(int i, int j) {
				return Boolean.FALSE;
			}
		};
		table.setModel(modelo);
		TableColumnModel columnModel = table.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(130);
		columnModel.getColumn(1).setPreferredWidth(250);
		columnModel.getColumn(2).setPreferredWidth(180);
		columnModel.getColumn(3).setPreferredWidth(150);
		columnModel.getColumn(4).setPreferredWidth(100);
		columnModel.getColumn(5).setPreferredWidth(60);
		JTableHeader header = table.getTableHeader();
		header.setForeground(Color.BLACK);
		header.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
	}

	private void initComponents() {
		//Propiedades del lbl
		cantidad = c.comunidadContar("Todos");
		lblNumero.setText("# de Registros: " + cantidad);
		lblNumero.setForeground(Color.BLACK);
		lblNumero.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
		lblNumero.setBounds(320, 520, 150, 30);
		pp.add(lblNumero);

		//Propiedades del la caja de texto
		TextPrompt placeholder = new TextPrompt("Búsqueda", txtBusqueda);
		placeholder.changeAlpha(0.75f);
		placeholder.setIcon(new ImageIcon("src/main/resources/Iconos/busqueda.png"));
		placeholder.setHorizontalAlignment(SwingConstants.CENTER);
		placeholder.setFont(new Font("Segoe UI Symbol", Font.BOLD + Font.ITALIC, 14));
		txtBusqueda.setForeground(Color.BLACK);
		txtBusqueda.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
		txtBusqueda.setHorizontalAlignment(SwingConstants.CENTER);
		txtBusqueda.setBounds(10, 520, 300, 30);
		pp.add(txtBusqueda);

		//Propiedades de la table
		scrollPane.setViewportView(table);
		scrollPane.setBounds(10, 10, 779, 500);
		pp.add(scrollPane, BorderLayout.CENTER);

		//Propiedades del combobox
		cboFiltro.setForeground(Color.BLACK);
		cboFiltro.setFont(new Font("Segoe UI Symbol", Font.BOLD + Font.ITALIC, 14));
		ArrayList opciones = new ArrayList();
		opciones.add("Todos");
		opciones.add("Alumno");
		opciones.add("Docente");
		opciones.add("Administrativo");
		ValueModel opcionesTipo = new ValueHolder("Seleccione una opción");
		ComboBoxAdapter cboTipoAdapter = new ComboBoxAdapter(opciones, opcionesTipo);
		cboFiltro.setModel(cboTipoAdapter);
		cboFiltro.setBounds(538, 520, 250, 30);
		pp.add(cboFiltro);

		//Propiedades del pp
		pp.setLayout(null);
		pp.setBackground(new Color(242, 245, 242));
		pp.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5, Boolean.FALSE));
		pp.setBounds(0, 0, 800, 575);

		//Propiedades del internal frame
		this.setLayout(null);
		this.setUndecorated(Boolean.TRUE);
		this.setSize(800, 576);
		this.setTitle(".: Listado de Carrera y Áreas. - SiRiUS. :.");
		this.setIconImage(new ImageIcon("src/main/resources/Iconos/logo.png").getImage());
		this.add(pp);
		this.setLocationRelativeTo(null);

		//Eventos de componentes
		txtBusqueda.addKeyListener(new KeyListener() {
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
				txtBusquedaKeyReleased(e);
			}
		});
		table.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tableMouseClicked(e);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				//----------------------------
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				//----------------------------
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				//----------------------------
			}

			@Override
			public void mouseExited(MouseEvent e) {
				//----------------------------
			}
		});
		cboFiltro.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cboFiltroActionPerformed(e);
			}
		});
	}

	private void cboFiltroActionPerformed(ActionEvent evt) {
		String filtro = null;
		if (cboFiltro.getSelectedItem().equals("Alumno")) {
			filtro = cboFiltro.getSelectedItem().toString();
			datosCom = c.comunidadConsultar(filtro);
			cantidad = c.comunidadContar(filtro);
			lblNumero.setText("# de Registros: " + cantidad);
			this.llenarTabla();
		} else if (cboFiltro.getSelectedItem().equals("Docente")) {
			filtro = cboFiltro.getSelectedItem().toString();
			datosCom = c.comunidadConsultar(filtro);
			cantidad = c.comunidadContar(filtro);
			lblNumero.setText("# de Registros: " + cantidad);
			this.llenarTabla();
		} else if (cboFiltro.getSelectedItem().equals("Administrativo")) {
			filtro = cboFiltro.getSelectedItem().toString();
			datosCom = c.comunidadConsultar(filtro);
			cantidad = c.comunidadContar(filtro);
			lblNumero.setText("# de Registros: " + cantidad);
			this.llenarTabla();
		} else if (cboFiltro.getSelectedItem().equals("Todos")) {
			filtro = cboFiltro.getSelectedItem().toString();
			datosCom = c.comunidadConsultar(filtro);
			cantidad = c.comunidadContar(filtro);
			lblNumero.setText("# de Registros: " + cantidad);
			this.llenarTabla();
		}
	}

	private void txtBusquedaKeyReleased(KeyEvent evt) {
		String busqueda = txtBusqueda.getText();
		String filtro = null;
		if (cboFiltro.getSelectedItem().equals("Seleccione una opción")) {
			JOptionPane.showMessageDialog(ListadoComunidad.this, "Debe seleccionar una opción de la lista desplagable antes de realizar una busqueda.", "Seleccion de opción. - SiRiUS", JOptionPane.WARNING_MESSAGE, warning);
			cboFiltro.requestFocus();
		} else {
			if (cboFiltro.getSelectedItem().equals("Todos")) {
				filtro = cboFiltro.getSelectedItem().toString();
			} else if (cboFiltro.getSelectedItem().equals("Carrera")) {
				filtro = cboFiltro.getSelectedItem().toString();
			} else if (cboFiltro.getSelectedItem().equals("Área")) {
				filtro = cboFiltro.getSelectedItem().toString();
			}
			if (busqueda.isEmpty()) {
				datosCom = c.comunidadConsultar(filtro);
			} else {
				datosCom = c.comunidadBuscar(busqueda, filtro);
			}
			this.llenarTabla();
		}
	}

	private void tableMouseClicked(MouseEvent evt) {
		if (usuario[2].equals("Administrador")) {
			JOptionPane.showMessageDialog(ListadoComunidad.this, "Debes ser Super Administrador para seleccionar un registro.", "Selección denegada. - SiRiUS.", JOptionPane.WARNING_MESSAGE, warning);
		} else {
			if (evt.getClickCount() == 2) {
				String matricula = table.getValueAt(table.getSelectedRow(), 0).toString();
				ComunidadSeleccionado cs = new ComunidadSeleccionado(matricula, usuario);
				cs.setVisible(Boolean.TRUE);
				datosCom = c.comunidadConsultar("Todos");
				this.dispose();
				this.llenarTabla();
			}
		}
	}
}
