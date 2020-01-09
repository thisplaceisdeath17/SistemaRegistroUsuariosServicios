package Interfaces.TipoAdministrador;

import Interfaces.Inicio.VentanaPrincipal;
import MySQL.TipoAdministrador;
import TextPrompt.TextPrompt;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ListadoTipoAdministrador extends JInternalFrame {
	private JPanel pp = new JPanel();
	private JTextField txtBusqueda = new JTextField();
	private JLabel lblNumero = new JLabel();
	private JTable table = new JTable();
	private JScrollPane scrollPane = new JScrollPane(table);

	//Iconos para JOptionPanel
	private ImageIcon warning = new ImageIcon("src/main/resources/Iconos/warning.png");
	private ImageIcon error = new ImageIcon("src/main/resources/Iconos/error.png");
	private ImageIcon exito = new ImageIcon("src/main/resources/Iconos/exito.png");
	private ImageIcon pregunta = new ImageIcon("src/main/resources/Iconos/pregunta.png");

	//Variables y objetos.
	private String[] encabezado = {"ID", "Descripcion"};
	private TipoAdministrador ta = new TipoAdministrador();
	private Object[][] datosTA = null;
	private DefaultTableModel modelo = null;
	private String[] usuario;

	public ListadoTipoAdministrador(String[] usuario) {
		initComponents();
		this.usuario = usuario;
		datosTA = ta.tipoAdministradorConsultar();
		this.llenarTabla();
		System.out.println("*******************" + usuario[2]);
	}

	private void initComponents() {
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

		//Propiedades del pp
		pp.setLayout(null);
		pp.setBackground(new Color(242, 245, 242));
		pp.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5, Boolean.FALSE));
		pp.setBounds(0, 0, 798, 575);

		//Propiedades del internal frame
		this.setLayout(null);
		this.setSize(800, 600);
		this.setTitle(".: Listado de Tipos de Administradores. - SiRiUS. :.");
		this.setFrameIcon(new ImageIcon("src/main/resources/Iconos/listado_16.png"));
		this.setClosable(Boolean.TRUE);
		this.add(pp);

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
	}

	private void llenarTabla() {
		modelo = new DefaultTableModel(datosTA, encabezado) {
			@Override
			public boolean isCellEditable(int i, int j) {
				return Boolean.FALSE;
			}
		};
		table.setModel(modelo);
		TableColumnModel columnModel = table.getColumnModel();
		JTableHeader header = table.getTableHeader();
		header.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
		header.setForeground(Color.BLACK);
	}

	private void txtBusquedaKeyReleased(KeyEvent evt) {
		String busqueda = txtBusqueda.getText();
		if (busqueda.isEmpty()) {
			datosTA = ta.tipoAdministradorConsultar();
		} else {
			datosTA = ta.tipoAdministradorBuscar(busqueda);
		}
		this.llenarTabla();
	}

	private void tableMouseClicked(MouseEvent evt) {
		if (evt.getClickCount() == 2) {
			if (usuario[2].equals("Administrador")) {
				JOptionPane.showInternalMessageDialog(ListadoTipoAdministrador.this, "Debes ser Super Administrador para seleccionar un dato de la tabla.", "Selección denegada. - SiRiUS", JOptionPane.WARNING_MESSAGE, warning);
			} else {
				String id = table.getValueAt(table.getSelectedRow(), 0).toString();
				String descripcion = table.getValueAt(table.getSelectedRow(), 1).toString();
				TipoAdministradorSeleccionado tas = new TipoAdministradorSeleccionado(usuario, id, descripcion);
				int x = (VentanaPrincipal.escritorio.getWidth() / 2) - tas.getWidth() / 2;
				int y = (VentanaPrincipal.escritorio.getHeight() / 2) - tas.getHeight() / 2;
				if (tas.isShowing()) {
					tas.setLocation(x, y);
				} else {
					VentanaPrincipal.escritorio.add(tas);
					tas.setLocation(x, y);
					tas.show();
				}
				datosTA = ta.tipoAdministradorConsultar();
				this.llenarTabla();
				this.dispose();
			}
		}
	}
}
