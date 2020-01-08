/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces.Administrador;

import MySQL.Administrador;
import MySQL.CarreraArea;
import MySQL.TipoAdministrador;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

/**
 *
 * @author David Esquivel Mendoza
 * @version 2.0
 */
public class AgregarAdministrador extends JInternalFrame {

    private JPanel pp = new JPanel();
    private JPanel pca = new JPanel();
    private JPanel pta = new JPanel();
    private JSeparator sp = new JSeparator(SwingConstants.VERTICAL);
    private JLabel lblLogin = new JLabel("Nombre de Usuario");
    private JLabel lblNombre = new JLabel("Nombre Completo");
    private JLabel lblPassword = new JLabel("Contraseña");
    private JLabel lblConfPassword = new JLabel("Confirmar Contraseña");
    private JTextField txtLogin = new JTextField();
    private JTextField txtNombre = new JTextField();
    private JPasswordField txtPassword = new JPasswordField();
    private JPasswordField txtConfPassword = new JPasswordField();
    private JComboBox<String> cboCarrera = new JComboBox<>();
    private JComboBox<String> cboTipo = new JComboBox<>();
    private JButton btnGuardar = new JButton();
    private JButton btnVolver = new JButton();

    //Iconos para JOptionPanel
    private ImageIcon warning = new ImageIcon("src/main/resources/Iconos/warning.png");
    private ImageIcon error = new ImageIcon("src/main/resources/Iconos/error.png");
    private ImageIcon exito = new ImageIcon("src/main/resources/Iconos/exito.png");
    private ImageIcon pregunta = new ImageIcon("src/main/resources/Iconos/pregunta.png");

    //Variables y objetos
    private Administrador a = new Administrador();
    private TipoAdministrador ta = new TipoAdministrador();
    private CarreraArea ca = new CarreraArea();
    private String[] usuario;
    private Object[][] datosCA;
    private Object[][] datosTA;

    public AgregarAdministrador(String[] usuario) {
        initComponents();
        this.usuario = usuario;
        datosCA = ca.carreraAreaConsultar("Todos");
        for (int ca = 0; ca < datosCA.length; ca++) {
            cboCarrera.addItem(datosCA[ca][1].toString());
        }
        datosTA = ta.tipoAdministradorConsultar();
        for (int ta = 0; ta < datosTA.length; ta++) {
            cboTipo.addItem(datosTA[ta][1].toString());
        }
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
        lblConfPassword.setForeground(Color.BLACK);
        lblConfPassword.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
        lblConfPassword.setBounds(15, 225, 150, 30);
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
        txtConfPassword.setForeground(Color.BLACK);
        txtConfPassword.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
        txtConfPassword.setHorizontalAlignment(SwingConstants.CENTER);
        txtConfPassword.setBounds(15, 260, 250, 30);
        pp.add(txtConfPassword);

        //Panel de carrera
        pca.setLayout(null);
        pca.setBackground(new Color(242, 245, 242));
        pca.setBorder(BorderFactory.createTitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Seleccione Carrera o Área", TitledBorder.LEFT, TitledBorder.ABOVE_TOP, new Font("Segoe UI Symbol", Font.BOLD, 12), Color.BLACK));
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
        sp.setBounds(280, 15, 1, 274);
        pp.add(sp);

        //Botones
        btnGuardar.setForeground(Color.BLACK);
        btnGuardar.setFont(new Font("Segoe UI Symbol", Font.BOLD, 12));
        btnGuardar.setText("Guardar");
        btnGuardar.setBorder(BorderFactory.createLineBorder(new Color(171, 235, 198), 2, Boolean.FALSE));
        btnGuardar.setIcon(new ImageIcon("src/main/resources/Iconos/guardar.png"));
        btnGuardar.setBounds(100, 310, 150, 50);
        pp.add(btnGuardar);
        btnVolver.setForeground(Color.BLACK);
        btnVolver.setFont(new Font("Segoe UI Symbol", Font.BOLD, 12));
        btnVolver.setText("Volver");
        btnVolver.setBorder(BorderFactory.createLineBorder(new Color(171, 235, 198), 2, Boolean.FALSE));
        btnVolver.setIcon(new ImageIcon("src/main/resources/Iconos/volver.png"));
        btnVolver.setBounds(350, 310, 150, 50);
        pp.add(btnVolver);

        //Propiedades del pp
        pp.setLayout(null);
        pp.setBackground(new Color(242, 245, 242));
        pp.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5, Boolean.FALSE));
        pp.setBounds(0, 0, 598, 375);
        this.add(pp);

        //Propiedades del internal frame
        this.setLayout(null);
        this.setSize(600, 400);
        this.setIconifiable(Boolean.TRUE);
        this.setTitle(".: Agregar Administrador. - SiRiUS :.");
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

    private void txtLoginKeyPressed(KeyEvent evt) {
        if (txtLogin.getText().length() == 15) {
            JOptionPane.showInternalMessageDialog(AgregarAdministrador.this, "Has alcanzado el límite de caracteres permitido.", "Límite Alcanzado. - SiRiUS.", JOptionPane.WARNING_MESSAGE, warning);
            txtLogin.requestFocus();
            evt.consume();
        }
    }

    private void txtNombreKeyPressed(KeyEvent evt) {
        if (txtNombre.getText().length() == 60) {
            JOptionPane.showInternalMessageDialog(AgregarAdministrador.this, "Has alcanzado el límite de caracteres permitido.", "Límite Alcanzado. - SiRiUS.", JOptionPane.WARNING_MESSAGE, warning);
            txtNombre.requestFocus();
            evt.consume();
        }
    }

    private void txtPasswordKeyPressed(KeyEvent evt) {
        if (txtPassword.getText().length() == 15) {
            JOptionPane.showInternalMessageDialog(AgregarAdministrador.this, "Has alcanzado el límite de caracteres permitido.", "Límite Alcanzado. - SiRiUS.", JOptionPane.WARNING_MESSAGE, warning);
            txtPassword.requestFocus();
            evt.consume();
        }
    }

    private void txtConfPasswordKeyPressed(KeyEvent evt) {
        if (txtConfPassword.getText().length() == 15) {
            JOptionPane.showInternalMessageDialog(AgregarAdministrador.this, "Has alcanzado el límite de caracteres permitido.", "Límite Alcanzado. - SiRiUS.", JOptionPane.WARNING_MESSAGE, warning);
            txtConfPassword.requestFocus();
            evt.consume();
        }
    }

    private void txtConfPasswordKeyReleased(KeyEvent evt) {
        String password = txtPassword.getText();
        String confPassword = txtConfPassword.getText();
        if (password.equals(confPassword)) {
            txtPassword.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2, Boolean.FALSE));
            txtConfPassword.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2, Boolean.FALSE));
        } else {
            txtPassword.setBorder(BorderFactory.createLineBorder(Color.RED, 2, Boolean.FALSE));
            txtConfPassword.setBorder(BorderFactory.createLineBorder(Color.RED, 2, Boolean.FALSE));
        }
    }

    private void btnGuardarActionPerformed(ActionEvent evt) {
        String login = txtLogin.getText();
        String nombre = txtNombre.getText();
        String password = txtPassword.getText();
        String confPassword = txtConfPassword.getText();
        String carrera = datosCA[cboCarrera.getSelectedIndex()][0].toString();
        String tipo = datosTA[cboTipo.getSelectedIndex()][0].toString();
        if (login.isEmpty()) {
            JOptionPane.showInternalMessageDialog(AgregarAdministrador.this, "El campo nombre de usuario no puede quedar vacío.", "Campo vacío. - SiRiUS.", JOptionPane.WARNING_MESSAGE, warning);
            txtLogin.requestFocus();
        } else if (nombre.isEmpty()) {
            JOptionPane.showInternalMessageDialog(AgregarAdministrador.this, "El campo nombre completo no puede quedar vacío.", "Campo vacío. - SiRiUS.", JOptionPane.WARNING_MESSAGE, warning);
            txtNombre.requestFocus();
        } else if (password.isEmpty()) {
            JOptionPane.showInternalMessageDialog(AgregarAdministrador.this, "El campo contraseña no puede quedar vacío.", "Campo vacío. - SiRiUS.", JOptionPane.WARNING_MESSAGE, warning);
            txtPassword.requestFocus();
        } else if (confPassword.isEmpty()) {
            JOptionPane.showInternalMessageDialog(AgregarAdministrador.this, "El campo confirmar contraseña no puede quedar vacío.", "Campo vacío. - SiRiUS.", JOptionPane.WARNING_MESSAGE, warning);
            txtConfPassword.requestFocus();
        } else if (!password.equals(confPassword)) {
            JOptionPane.showInternalMessageDialog(AgregarAdministrador.this, "Las contraseñas no coinciden, revise de nuevo.", "Contraseñas incorrectas. - SiRiUS.", JOptionPane.WARNING_MESSAGE, warning);
            txtPassword.requestFocus();
            txtConfPassword.setText("");
        } else if (a.administradorExiste(login)) {
            JOptionPane.showInternalMessageDialog(AgregarAdministrador.this, "Ya existe un registro con el mismo nombre de usuario.", "Nombre de usuario duplicado. - SiRiUS.", JOptionPane.WARNING_MESSAGE, warning);
            txtLogin.setText("");
            txtLogin.requestFocus();
        } else if (a.administradorInsertar(login, nombre, password, carrera, tipo)) {
            JOptionPane.showInternalMessageDialog(AgregarAdministrador.this, nombre + " se ha registro correctamente.", "Registro correcto. - SiRiUS.", JOptionPane.INFORMATION_MESSAGE, exito);
            txtLogin.setText("");
            txtNombre.setText("");
            txtPassword.setText("");
            txtConfPassword.repaint();
            txtPassword.repaint();
            txtConfPassword.setText("");
            cboCarrera.setSelectedIndex(0);
            cboTipo.setSelectedIndex(0);
            txtLogin.requestFocus();
        } else {
            JOptionPane.showInternalMessageDialog(AgregarAdministrador.this, "El registro no se llevo a cabo, debido a un error de comunicación con la base de datos. Esta ventana se cerrará.", "Error de comunicación. - SiRiUS.", JOptionPane.ERROR_MESSAGE, error);
            this.dispose();
        }
    }
    private void btnVolverActionPerformed(ActionEvent evt) {
        //Abrir listado
        this.dispose();
    }
}
