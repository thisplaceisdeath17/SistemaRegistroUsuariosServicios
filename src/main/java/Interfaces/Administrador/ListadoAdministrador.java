package Interfaces.Administrador;

import Interfaces.Inicio.VentanaPrincipal;
import MySQL.Administrador;
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

public class ListadoAdministrador extends JInternalFrame {

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

    //Variables y objetos
    private String[] encabezado = {"Nombre de Usuario", "Nombre Completo", "Carrera/Área", "Tipo Administrador"};
    private DefaultTableModel modelo = null;
    private Administrador a = new Administrador();
    private Object[][] datosAdmin;
    private String[] usuario;
    private int cantidad;

    public ListadoAdministrador(String[] usuario) {
        initComponents();
        this.usuario = usuario;
        datosAdmin = a.administradorConsultar();
        this.llenarTabla();
    }

    private void initComponents() {
        //Propiedades del lbl
        cantidad = a.administradorContar();
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

        //Propiedades del pp
        pp.setLayout(null);
        pp.setBackground(new Color(242, 245, 242));
        pp.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5, Boolean.FALSE));
        pp.setBounds(0, 0, 798, 575);

        //Propiedades del internal frame
        this.setLayout(null);
        this.setSize(800, 600);
        this.setTitle(".: Listado de Administradores. - SiRiUS. :.");
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
        modelo = new DefaultTableModel(datosAdmin, encabezado) {
            @Override
            public boolean isCellEditable(int i, int j) {
                return false;
            }
        };
        table.setModel(modelo);
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(1).setPreferredWidth(200);
        columnModel.getColumn(2).setPreferredWidth(200);
        columnModel.getColumn(3).setPreferredWidth(200);
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
        header.setForeground(Color.BLACK);
    }

    private void txtBusquedaKeyReleased(KeyEvent evt) {
        String busqueda = txtBusqueda.getText();
        if (busqueda.isEmpty()) {
            datosAdmin = a.administradorConsultar();
        } else {
            datosAdmin = a.administradorBuscar(busqueda);
        }
        this.llenarTabla();
    }

    private void tableMouseClicked(MouseEvent evt) {
        if (usuario[2].equals("Administrador")) {
            JOptionPane.showInternalMessageDialog(ListadoAdministrador.this, "Debes ser Super Administrador para seleccionar un registro.", "Selección denegada. - SiRiUS", JOptionPane.WARNING_MESSAGE, warning);
        } else {
            if (evt.getClickCount() == 2) {
                String login = table.getValueAt(table.getSelectedRow(), 0).toString();
                AdministradorSeleccionado as = new AdministradorSeleccionado(usuario, login);
                int x = (VentanaPrincipal.escritorio.getWidth() / 2) - as.getWidth() / 2;
                int y = (VentanaPrincipal.escritorio.getHeight() / 2) - as.getHeight() / 2;
                if (as.isShowing()) {
                    as.setLocation(x, y);
                } else {
                    VentanaPrincipal.escritorio.add(as);
                    as.setLocation(x, y);
                    as.show();
                }
                this.dispose();
                this.llenarTabla();
            }
        }
    }
}
