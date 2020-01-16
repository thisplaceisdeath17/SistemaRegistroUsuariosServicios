package Interfaces.Inicio;

import MySQL.Administrador;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @author David Esquivel Mendoza
 * @version 2.0
 *
 */
public class InicioSesion extends JFrame {

    private JPanel pp = new JPanel();
    private JPanel pt = new JPanel();
    private JPanel po = new JPanel();
    private JLabel lblTiempo = new JLabel("60");
    private JLabel lblOportunidades = new JLabel("2");
    private JLabel lblPassword = new JLabel("Contraseña");
    private JLabel lblLogin = new JLabel("Nombre de Usuario");
    private JLabel lblLogo = new JLabel();
    private JButton btnIngresar = new JButton();
    private JButton btnSalir = new JButton();
    private JToggleButton btnVer = new JToggleButton();
    private JPasswordField txtPassword = new JPasswordField();
    private JTextField txtLogin = new JTextField();

    //Iconos para JOptionPanel
    private ImageIcon warning = new ImageIcon("src/main/resources/Iconos/warning.png");
    private ImageIcon error = new ImageIcon("src/main/resources/Iconos/error.png");
    private ImageIcon exito = new ImageIcon("src/main/resources/Iconos/exito.png");
    private ImageIcon pregunta = new ImageIcon("src/main/resources/Iconos/pregunta.png");

    //Variables y objetos.
    private Administrador a = new Administrador();
    private int oportunidades = 3;
    private int tiempo = 60;

    public InicioSesion() {
        initComponents();
    }

    private Timer temporizador = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            tiempo--;
            lblTiempo.setText(String.valueOf(tiempo));
            if (tiempo == 0) {
                JOptionPane.showMessageDialog(InicioSesion.this, "El tiempo se ha agotado. Esta ventana se cerrará.", "Tiempo Agotado. - SiRiUS.", JOptionPane.ERROR_MESSAGE, error);
                temporizador.stop();
                System.exit(0);
            }
        }
    });

    public void initComponents() {
        //Propiedades del lblTiempo
        lblTiempo.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
        lblTiempo.setForeground(Color.BLACK);
        lblTiempo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTiempo.setBounds(0, 25, 150, 30);

        //Propiedades del lblTiempo
        lblOportunidades.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
        lblOportunidades.setForeground(Color.BLACK);
        lblOportunidades.setHorizontalAlignment(SwingConstants.CENTER);
        lblOportunidades.setBounds(0, 25, 150, 30);

        //Propiedades pt
        pt.setLayout(null);
        pt.setBounds(15, 500, 150, 60);
        pt.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.BLACK, 2, Boolean.FALSE), "Tiempo", TitledBorder.ABOVE_TOP, TitledBorder.LEFT, new Font("Segoe UI Symbol", Font.BOLD, 14), Color.BLACK));
        pt.setBackground(new Color(242, 245, 242));
        pt.add(lblTiempo);

        //Propiedades po
        po.setLayout(null);
        po.setBounds(180, 500, 150, 60);
        po.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.BLACK, 2, Boolean.FALSE), "Oportunidades", TitledBorder.ABOVE_TOP, TitledBorder.LEFT, new Font("Segoe UI Symbol", Font.BOLD, 14), Color.BLACK));
        po.setBackground(new Color(242, 245, 242));
        po.add(lblOportunidades);

        //Propiedades del btnIngresar
        btnIngresar.setBackground(new Color(242, 245, 242));
        btnIngresar.setForeground(Color.BLACK);
        btnIngresar.setText("Ingresar");
        btnIngresar.setHorizontalAlignment(SwingConstants.CENTER);
        btnIngresar.setIcon(new ImageIcon("src/main/resources/Iconos/login.png"));
        btnIngresar.setBounds(15, 430, 150, 50);
        btnIngresar.setBorder(BorderFactory.createLineBorder(new Color(171, 235, 198), 2, Boolean.FALSE));

        //Propiedades del btnSalir
        btnSalir.setBackground(new Color(242, 245, 242));
        btnSalir.setForeground(Color.BLACK);
        btnSalir.setText("Salir");
        btnSalir.setHorizontalAlignment(SwingConstants.CENTER);
        btnSalir.setIcon(new ImageIcon("src/main/resources/Iconos/out.png"));
        btnSalir.setBounds(180, 430, 150, 50);
        btnSalir.setBorder(BorderFactory.createLineBorder(new Color(245, 183, 177), 2, Boolean.FALSE));

        //Propiedades del txtPassword.
        txtPassword.setBounds(15, 370, 316, 30);
        txtPassword.setBackground(Color.WHITE);
        txtPassword.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
        txtPassword.setForeground(Color.BLACK);
        txtPassword.setHorizontalAlignment(SwingConstants.CENTER);

        //Propiedades del txtLogin.
        txtLogin.setBounds(15, 300, 316, 30);
        txtLogin.setBackground(Color.WHITE);
        txtLogin.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
        txtLogin.setForeground(Color.BLACK);
        txtLogin.setHorizontalAlignment(SwingConstants.CENTER);

        //Propiedades del lblPassword.
        lblPassword.setForeground(Color.BLACK);
        lblPassword.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
        lblPassword.setBounds(15, 340, 150, 30);
        //Propiedades del lblPassword.
        lblLogin.setForeground(Color.BLACK);
        lblLogin.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
        lblLogin.setBounds(15, 270, 150, 30);

        //Propiedades del btnVer
        btnVer.setBounds(340, 370, 30, 30);
        btnVer.setBackground(new Color(242, 245, 242));
        btnVer.setIcon(new ImageIcon("src/main/resources/Iconos/no_eyes.png"));
        btnVer.setToolTipText("Clic para ver contraseña.");

        //Propiedades del lblLogo.
        ImageIcon logo = new ImageIcon("src/main/resources/Iconos/logo.png");
        lblLogo.setBounds(90, 30, 200, 200);
        ImageIcon logoScaled = new ImageIcon(logo.getImage().getScaledInstance(lblLogo.getWidth(), lblLogo.getHeight(), Image.SCALE_SMOOTH));
        lblLogo.setIcon(logoScaled);

        //Propiedades del panel principal.
        pp.setLayout(null);
        pp.setBounds(0, 0, 380, 600);
        pp.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5, Boolean.FALSE));
        pp.setBackground(new Color(242, 245, 242));
        pp.add(pt);
        pp.add(po);
        pp.add(btnIngresar);
        pp.add(btnSalir);
        pp.add(txtPassword);
        pp.add(lblPassword);
        pp.add(txtLogin);
        pp.add(lblLogin);
        pp.add(btnVer);
        pp.add(lblLogo);

        //Propiedades del JFrame
        this.add(pp);
        this.setLayout(null);
        this.setSize(380, 600);
        this.setLocationRelativeTo(null);
        this.setUndecorated(true);
        this.setIconImage(new ImageIcon("src/main/resources/Iconos/logo.png").getImage());

        //Eventos de botones.
        btnIngresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnIngresarActionPerformed(e);
            }
        });
        btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnSalirActionPerformed(e);
            }
        });
        txtLogin.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                //Temporalmente sin uso.
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
        txtPassword.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                txtPasswordKeyTyped(e);
            }

            @Override
            public void keyPressed(KeyEvent e) {
                txtPasswordKeyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                //---------------------------------
            }
        });
        btnVer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnVerActionPerformed(e);
            }
        });
    }

    private void txtLoginKeyPressed(KeyEvent evt) {
        if (txtLogin.getText().length() == 15) {
            JOptionPane.showMessageDialog(InicioSesion.this, "Has alcanzado el límite de caracteres permitidos.", "Límite Alcanzado. - SiRiUS.", JOptionPane.WARNING_MESSAGE, warning);
            txtLogin.requestFocus();
            evt.consume();
        }
    }

    private void txtPasswordKeyPressed(KeyEvent evt) {
        if (txtPassword.getText().length() == 15) {
            JOptionPane.showMessageDialog(InicioSesion.this, "Has alcanzado el límite de caracteres permitidos.", "Límite Alcanzado. - SiRiUS.", JOptionPane.WARNING_MESSAGE, warning);
            txtPassword.requestFocus();
            evt.consume();
        }
    }

    private void txtPasswordKeyTyped(KeyEvent evt) {
        char key = evt.getKeyChar();
        if (key == KeyEvent.VK_ENTER) {
            btnIngresarActionPerformed(null);
            evt.consume();
        }
    }
    private void btnIngresarActionPerformed(ActionEvent evt) {
        String login = txtLogin.getText();
        String password = txtPassword.getText();
        String[] usuario = a.administradorValidar(login, password);
        if (usuario[0] != null) {
            if (!usuario[0].isEmpty()) {
                temporizador.stop();
                JOptionPane.showMessageDialog(InicioSesion.this, "Bienvenido a SiRiUS " + usuario[1] + "!", "Acceso Concedido. - SiRiUS.", JOptionPane.INFORMATION_MESSAGE, exito);
                VentanaPrincipal vp = new VentanaPrincipal(login, usuario);
                vp.setVisible(Boolean.TRUE);
                this.dispose();
            }
        } else {
            temporizador.start();
            JOptionPane.showMessageDialog(InicioSesion.this, "Nombre de usuario o contraseña incorrectos, revise de nuevo.", "Acceso Denegado. - SiRiUS.", JOptionPane.WARNING_MESSAGE, warning);
            txtLogin.requestFocus();
            txtLogin.setText("");
            txtPassword.setText("");
            lblOportunidades.setText(String.valueOf(oportunidades));
            oportunidades--;
            if (oportunidades == 0) {
                JOptionPane.showMessageDialog(InicioSesion.this, "Las oportunidades se han agotado. El sistema se cerrará.", "Oportunidades agotadas. - SiRiUS.", JOptionPane.ERROR_MESSAGE, error);
                System.exit(0);
            }
        }
    }

    private void btnSalirActionPerformed(ActionEvent evt) {
        int res = JOptionPane.showConfirmDialog(InicioSesion.this, "¿Estas seguro de cerrar el sistema?", "Confirmar cierre. - SiRiUS.", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, pregunta);
        if (res == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    private void btnVerActionPerformed(ActionEvent evt) {
        if (btnVer.isSelected()) {
            txtPassword.setEchoChar((char) 0);
            btnVer.setIcon(new ImageIcon("src/main/resources/Iconos/eyes.png"));
        } else {
            txtPassword.setEchoChar('\u25cf');
            btnVer.setIcon(new ImageIcon("src/main/resources/Iconos/no_eyes.png"));
        }
    }
}
