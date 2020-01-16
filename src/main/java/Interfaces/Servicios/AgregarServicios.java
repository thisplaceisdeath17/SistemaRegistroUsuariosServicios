package Interfaces.Servicios;

import Interfaces.Inicio.VentanaPrincipal;
import Interfaces.Metodo.Metodo;
import Log.Log;
import MySQL.CarreraArea;
import MySQL.Servicios;
import TextPrompt.TextPrompt;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class AgregarServicios extends JFrame {

    private JPanel pp = new JPanel();
    private JPanel pca = new JPanel();
    private JPanel pf = new JPanel();
    private JLabel lblNombre = new JLabel("Nombre del Servicio");
    private JLabel lblCosto = new JLabel("Costo del Servicio (MXN)");
    private JLabel lblDescripcion = new JLabel("Descripción");
    private JLabel lblFechaActual = new JLabel("Fecha Actual");
    private JLabel lblFechaEntrega = new JLabel("Selecciona fecha ");
    private JLabel lblFotografia = new JLabel();
    private JTextField txtNombre = new JTextField();
    private JTextField txtCosto = new JTextField();
    private JTextField txtDias = new JTextField();
    private JTextArea txtDescripcion = new JTextArea();
    private JScrollPane scrollPane = new JScrollPane(txtDescripcion);
    private JComboBox<String> cboCarreras = new JComboBox<>();
    private JDateChooser dcFechaActual = new JDateChooser();
    private JDateChooser dcFechaEntrega = new JDateChooser();
    private JButton btnGuardar = new JButton();
    private JButton btnVolver = new JButton();
    private JButton btnMetodo = new JButton();

    //Iconos para JOptionPanel
    private ImageIcon warning = new ImageIcon("src/main/resources/Iconos/warning.png");
    private ImageIcon error = new ImageIcon("src/main/resources/Iconos/error.png");
    private ImageIcon exito = new ImageIcon("src/main/resources/Iconos/exito.png");
    private ImageIcon pregunta = new ImageIcon("src/main/resources/Iconos/pregunta.png");
    private ImageIcon informacion = new ImageIcon("src/main/resources/Iconos/info.png");

    //Variable y objetos
    private Servicios s = new Servicios();
    private CarreraArea ca = new CarreraArea();
    private String[] usuario;
    private File origen = null;
    private Object[][] datosCA;
    private String path = "src/main/java/Log/Log.txt";

    public AgregarServicios(String[] usuario) {
        initComponents();
        this.usuario = usuario;
        datosCA = ca.carreraAreaConsultar("Todos");
        for (int ca = 0; ca < datosCA.length; ca++) {
            cboCarreras.addItem(datosCA[ca][1].toString());
        }
        Calendar diasEntrega = new GregorianCalendar();
        dcFechaActual.setCalendar(diasEntrega);
        ImageIcon no_image = new ImageIcon("src/main/resources/Iconos/no_image.png");
        ImageIcon imagen = new ImageIcon(no_image.getImage().getScaledInstance(lblFotografia.getWidth(), lblFotografia.getHeight(), Image.SCALE_SMOOTH));
        lblFotografia.setIcon(imagen);
        dcFechaEntrega.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                try {
                    if (dcFechaActual.getDate() != null || dcFechaEntrega.getDate() != null) {
                        Calendar actual = dcFechaActual.getCalendar();
                        Calendar entrega = dcFechaEntrega.getCalendar();
                        int dias = -1;
                        while (actual.before(entrega) || actual.equals(entrega)) {
                            dias++;
                            actual.add(Calendar.DATE, 1);
                        }
                        txtDias.setText("" + dias);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(AgregarServicios.this, "Error en el calculo de las fechas. Para más información, consulte el archivo log.", "Error. - SiRiUS.", JOptionPane.ERROR_MESSAGE, error);
                    Log.writeLog(path, "Error en el calculo de las fechas." + ex);
                }
            }
        });
    }

    private void initComponents() {
        //labels
        lblNombre.setForeground(Color.BLACK);
        lblNombre.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
        lblNombre.setBounds(15, 15, 150, 30);
        pp.add(lblNombre);
        lblCosto.setForeground(Color.BLACK);
        lblCosto.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
        lblCosto.setBounds(15, 85, 170, 30);
        pp.add(lblCosto);
        lblDescripcion.setForeground(Color.BLACK);
        lblDescripcion.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
        lblDescripcion.setBounds(15, 155, 150, 30);
        pp.add(lblDescripcion);
        lblFechaActual.setForeground(Color.BLACK);
        lblFechaActual.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
        lblFechaActual.setBounds(15, 400, 180, 30);
        pp.add(lblFechaActual);
        lblFechaEntrega.setForeground(Color.BLACK);
        lblFechaEntrega.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
        lblFechaEntrega.setBounds(15, 470, 180, 30);
        pp.add(lblFechaEntrega);
        lblFotografia.setBounds(50, 35, 640, 480);
        lblFotografia.setHorizontalAlignment(SwingConstants.CENTER);
        lblFotografia.setBorder(BorderFactory.createLineBorder(new Color(237, 187, 153), 2, Boolean.FALSE));
        pf.add(lblFotografia);

        //cajas de texto
        txtNombre.setForeground(Color.BLACK);
        txtNombre.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
        txtNombre.setHorizontalAlignment(SwingConstants.CENTER);
        txtNombre.setBounds(15, 50, 300, 30);
        pp.add(txtNombre);
        TextPrompt prompt = new TextPrompt("Escriba el costo en MXN.", txtCosto);
        prompt.setForeground(Color.BLACK);
        prompt.changeAlpha(0.5f);
        prompt.changeStyle(Font.BOLD + Font.ITALIC);
        prompt.setIcon(new ImageIcon("src/main/resources/Iconos/money.png"));
        txtCosto.setForeground(Color.BLACK);
        txtCosto.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
        txtCosto.setHorizontalAlignment(SwingConstants.CENTER);
        txtCosto.setBounds(15, 120, 300, 30);
        pp.add(txtCosto);
        TextPrompt placeholder = new TextPrompt("Escriba una descripción.", txtDescripcion);
        placeholder.changeAlpha(0.75f);
        placeholder.setHorizontalAlignment(SwingConstants.LEADING);
        placeholder.setVerticalAlignment(SwingConstants.TOP);
        placeholder.setFont(new Font("Segoe UI Symbol", Font.BOLD + Font.ITALIC, 14));
        scrollPane.setViewportView(txtDescripcion);
        txtDescripcion.setForeground(Color.BLACK);
        txtDescripcion.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
        txtDescripcion.setWrapStyleWord(Boolean.TRUE);
        txtDescripcion.setLineWrap(Boolean.TRUE);
        scrollPane.setBounds(15, 190, 300, 120);
        pp.add(scrollPane);
        txtDias.setForeground(Color.BLACK);
        txtDias.setEditable(Boolean.FALSE);
        txtDias.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
        txtDias.setHorizontalAlignment(SwingConstants.CENTER);
        txtDias.setBounds(270, 470, 30, 30);
        pp.add(txtDias);

        //Jdatechooser
        dcFechaActual.setForeground(Color.BLACK);
        dcFechaActual.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
        dcFechaActual.setEnabled(Boolean.FALSE);
        dcFechaActual.setDateFormatString("EEE dd 'de' MMMM 'del' YYYY");
        dcFechaActual.setBounds(15, 435, 250, 30);
        pp.add(dcFechaActual);
        dcFechaEntrega.setForeground(Color.BLACK);
        dcFechaEntrega.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
        dcFechaEntrega.setDateFormatString("EEE dd 'de' MMMM 'del' YYYY");
        dcFechaEntrega.setBounds(15, 505, 250, 30);
        pp.add(dcFechaEntrega);

        //combobox
        cboCarreras.setForeground(Color.BLACK);
        cboCarreras.setFont(new Font("Segoe UI Symbol", Font.BOLD + Font.ITALIC, 14));
        cboCarreras.setBounds(15, 25, 267, 30);
        pca.add(cboCarreras);

        //pca
        pca.setLayout(null);
        pca.setBackground(new Color(242, 245, 242));
        pca.setBorder(BorderFactory.createTitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Seleccione Carrera/Área", TitledBorder.ABOVE_TOP, TitledBorder.LEFT, new Font("Segoe UI Symbol", Font.BOLD, 12), Color.BLACK));
        pca.setBounds(15, 330, 300, 70);
        pp.add(pca);
        pf.setLayout(null);
        pf.setBackground(new Color(242, 245, 242));
        pf.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.BLACK, 5, Boolean.FALSE), "Seleccione una fotografía o Imagen.", TitledBorder.ABOVE_TOP, TitledBorder.LEFT, new Font("Segoe UI Symbol", Font.BOLD, 14), Color.BLACK));
        pf.setBounds(350, 15, 737, 600);
        pp.add(pf);

        //botones
        btnGuardar.setForeground(Color.BLACK);
        btnGuardar.setFont(new Font("Segoe UI Symbol", Font.BOLD, 12));
        btnGuardar.setText("Guardar");
        btnGuardar.setIcon(new ImageIcon("src/main/resources/Iconos/guardar.png"));
        btnGuardar.setBorder(BorderFactory.createLineBorder(new Color(171, 235, 198), 2, Boolean.FALSE));
        btnGuardar.setBounds(15, 550, 150, 50);
        pp.add(btnGuardar);
        btnVolver.setForeground(Color.BLACK);
        btnVolver.setFont(new Font("Segoe UI Symbol", Font.BOLD, 12));
        btnVolver.setText("Volver");
        btnVolver.setIcon(new ImageIcon("src/main/resources/Iconos/volver.png"));
        btnVolver.setBorder(BorderFactory.createLineBorder(new Color(245, 183, 177), 2, Boolean.FALSE));
        btnVolver.setBounds(180, 550, 150, 50);
        pp.add(btnVolver);
        btnMetodo.setForeground(Color.BLACK);
        btnMetodo.setFont(new Font("Segoe UI Symbol", Font.BOLD, 12));
        btnMetodo.setText("Opciones");
        btnMetodo.setIcon(new ImageIcon("src/main/resources/Iconos/opciones.png"));
        btnMetodo.setToolTipText("Clic para ver la opciones de captura.");
        btnMetodo.setBorder(BorderFactory.createLineBorder(new Color(245, 183, 177), 2, Boolean.FALSE));
        btnMetodo.setBounds(300, 530, 150, 50);
        pf.add(btnMetodo);

        //propiedades del pp
        pp.setLayout(null);
        pp.setBackground(new Color(242, 245, 242));
        pp.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5, Boolean.FALSE));
        pp.setBounds(0, 0, 1098, 638);
        this.add(pp);

        this.setLayout(null);
        this.setSize(1114, 677);
        this.setLocationRelativeTo(null);
        this.setResizable(Boolean.FALSE);
        this.setTitle(".: Agregar Servicios. - SiRiUS. :.");
        this.setIconImage(new ImageIcon("src/main/resources/Iconos/servicios_16.png").getImage());

        txtNombre.addKeyListener(new KeyListener() {
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
                txtNombreKeyReleased(e);
            }
        });
        txtCosto.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                txtCostoKeyTyped(e);
            }

            @Override
            public void keyPressed(KeyEvent e) {
                //------------------------------
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

    private void txtNombreKeyReleased(KeyEvent evt) {
        if (txtNombre.getText().length() == 60) {
            JOptionPane.showMessageDialog(AgregarServicios.this, "Has alcanzado el límite de caracteres permitidos.", "Límite Alcanzado. - SiriUS.", JOptionPane.WARNING_MESSAGE, warning);
            txtNombre.requestFocus();
        }
    }

    private void txtCostoKeyTyped(KeyEvent evt) {
        char key = evt.getKeyChar();
        if ((key < '0' || key > '9') && (key != KeyEvent.VK_BACK_SPACE) && (key != '.' || txtCosto.getText().contains(".")) && key != '\b') {
            JOptionPane.showMessageDialog(AgregarServicios.this, "Este campo solo acepta números.", "Caracteres Incorrectos. - SiRiUS.", JOptionPane.WARNING_MESSAGE, warning);
            evt.consume();
        }
    }

    private void btnGuardarActionPerformed(ActionEvent evt) {
        String nombre = txtNombre.getText();
        String costo = txtCosto.getText();
        String descripcion = txtDescripcion.getText();
        String dias = txtDias.getText();
        String carrera = datosCA[cboCarreras.getSelectedIndex()][0].toString();
        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(AgregarServicios.this, "El campo nombre del servicio no puede quedar vacío.", "Campo vacío. - SiRiUS.", JOptionPane.WARNING_MESSAGE, warning);
            txtNombre.requestFocus();
        } else if (costo.isEmpty()) {
            JOptionPane.showMessageDialog(AgregarServicios.this, "El campo costo del servicio no puede quedar vacío.", "Campo vacío. - SiRiUS.", JOptionPane.WARNING_MESSAGE, warning);
            txtCosto.requestFocus();
        } else if (descripcion.isEmpty()) {
            JOptionPane.showMessageDialog(AgregarServicios.this, "El campo descripción no puede quedar vacío.", "Campo vacío. - SiRiUS.", JOptionPane.WARNING_MESSAGE, warning);
            txtDescripcion.requestFocus();
        } else if (txtDias.getText().equals("-1")) {
            JOptionPane.showMessageDialog(AgregarServicios.this, "Debes seleccionar una fecha para calcular el número de días.", "Calculo de días. - SiRiUS.", JOptionPane.WARNING_MESSAGE, warning);
            dcFechaEntrega.requestFocus();
        } else if (origen == null) {
            JOptionPane.showMessageDialog(AgregarServicios.this, "Debes seleccionar una imagen o tomar una fotografía.", "Selección de imagen o fotografía. - SiRiUS.", JOptionPane.WARNING_MESSAGE, warning);
            lblFotografia.requestFocus();
        } else if (s.servicioInsertar(nombre, carrera, costo, descripcion, dias, origen)) {
            JOptionPane.showMessageDialog(AgregarServicios.this, "El registro se ejecutó correctamente.", "Registro correcto. - SiRiUS.", JOptionPane.INFORMATION_MESSAGE, exito);
            txtNombre.requestFocus();
            txtNombre.setText("");
            txtCosto.setText("");
            txtDescripcion.setText("");
            txtDias.setText("-1");
            cboCarreras.setSelectedIndex(0);
            origen = null;
            ImageIcon no_image = new ImageIcon("src/main/resources/Iconos/no_image.png");
            ImageIcon imagen = new ImageIcon(no_image.getImage().getScaledInstance(lblFotografia.getWidth(), lblFotografia.getHeight(), Image.SCALE_SMOOTH));
            lblFotografia.setIcon(imagen);
        } else {
            JOptionPane.showMessageDialog(AgregarServicios.this, "El registro no se ejecutó debido a un error de comunicación con la base de datos.", "Error de comunicación. - SiRiUS.", JOptionPane.ERROR_MESSAGE, error);
            this.dispose();
        }
    }

    private void btnMetodoActionPerformed(ActionEvent evt) {
        Metodo m = new Metodo(this, rootPaneCheckingEnabled, "AgregarServicios");
        m.setVisible(Boolean.TRUE);
        if (origen != null) {
            ImageIcon imagen = new ImageIcon(origen.getPath());
            ImageIcon imagenScaled = new ImageIcon(imagen.getImage().getScaledInstance(lblFotografia.getWidth(), lblFotografia.getHeight(), Image.SCALE_SMOOTH));
            lblFotografia.setIcon(imagenScaled);
        }
    }

    private void btnVolverActionPerformed(ActionEvent evt) {
        ListadoServicios ls = new ListadoServicios(usuario);
        int x = (VentanaPrincipal.escritorio.getWidth() / 2) - ls.getWidth() / 2;
        int y = (VentanaPrincipal.escritorio.getHeight() / 2) - ls.getHeight() / 2;
        if (ls.isShowing()) {
            ls.setLocation(x, y);
        } else {
            VentanaPrincipal.escritorio.add(ls);
            ls.setLocation(x, y);
            ls.show();
        }
        this.dispose();
    }
}
