package Interfaces.Inicio;

import Interfaces.Administrador.AgregarAdministrador;
import Interfaces.Administrador.ListadoAdministrador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author David Esquivel Mendoza.
 * @version 2.0
 *
 */
public class VentanaPrincipal extends JFrame implements Runnable{

	public static JDesktopPane escritorio = new JDesktopPane();
	private JMenuBar menuBar = new JMenuBar();
    private JMenu menuSistema = new JMenu("Sistema");
	private JMenu menuAdministradores = new JMenu("Administradores");
	private JMenu menuCarreras = new JMenu("Carreras y Áreas");
	private JMenu menuComunidad = new JMenu("Comunidad");
	private JMenu menuServicios = new JMenu("Servicios");
	private JMenu menuInfo = new JMenu("Información");
	private JMenuItem itemCerrar = new JMenuItem();
    private JMenuItem itemSalir = new JMenuItem();
    private JMenuItem itemAgregarAdmin = new JMenuItem();
    private JMenuItem itemAgregarTA = new JMenuItem();
    private JMenuItem itemAgregarCarrera = new JMenuItem();
    private JMenuItem itemAgregarComunidad = new JMenuItem();
    private JMenuItem itemAgregarServicios = new JMenuItem();
    private JMenuItem itemListadoAdmin = new JMenuItem();
    private JMenuItem itemListadoTA = new JMenuItem();
    private JMenuItem itemListadoCarrera = new JMenuItem();
    private JMenuItem itemListadoComunidad = new JMenuItem();
    private JMenuItem itemListadoServicios = new JMenuItem();
    private JMenuItem itemManual = new JMenuItem();
    private JSeparator sp = new JSeparator(SwingConstants.HORIZONTAL);
    private JLabel lblNombre = new JLabel();
    private JLabel lblTipo = new JLabel();
    private JLabel lblFecha = new JLabel();
    private JLabel lblTiempo = new JLabel();
    //Iconos para JOptionPanel
    private ImageIcon warning = new ImageIcon("src/main/resources/Iconos/warning.png");
    private ImageIcon error = new ImageIcon("src/main/resources/Iconos/error.png");
    private ImageIcon exito = new ImageIcon("src/main/resources/Iconos/exito.png");
    private ImageIcon pregunta = new ImageIcon("src/main/resources/Iconos/pregunta.png");

    //Variables y objetos.
    private String[] administrador;
    private String login;
    private String hora, minuto, segundo;
    private Thread hilo;
    

    public VentanaPrincipal(String login, String[] administrador) {
        initComponents();
        this.administrador = administrador;
        this.login = login;
        hilo = new Thread(this);
        hilo.start();
        //Propiedades del lblnombre y tipo
        lblNombre.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
        lblNombre.setForeground(Color.BLACK);
        lblNombre.setBounds(15, 600, 250, 30);
        lblNombre.setText("Administrador: " + this.administrador[1]);
        escritorio.add(lblNombre);
        lblTipo.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
        lblTipo.setForeground(Color.BLACK);
        lblTipo.setBounds(15, 640, 250, 30);
        lblTipo.setText("Tipo: " + this.administrador[2]);
        escritorio.add(lblTipo);
        if (this.administrador[2].equals("Administrador")) {
            itemAgregarAdmin.setEnabled(Boolean.FALSE);
            itemAgregarTA.setEnabled(Boolean.FALSE);
            itemAgregarCarrera.setEnabled(Boolean.FALSE);
            itemAgregarComunidad.setEnabled(Boolean.FALSE);
            itemAgregarServicios.setEnabled(Boolean.FALSE);
        }
    }

    private void initComponents() {
        //Propiedades del panelPrincipal
        escritorio.setLayout(null);
        escritorio.setBounds(0, 0, this.getWidth(), this.getHeight());
        escritorio.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5, Boolean.FALSE));
        escritorio.setBackground(new Color(242, 245, 242));

        //Propiedades de los item para el menu sistema
        itemCerrar.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
        itemCerrar.setForeground(Color.BLACK);
        itemCerrar.setText("Cerrar Sesi?n");
        itemCerrar.setIcon(new ImageIcon("src/main/resources/Iconos/logout.png"));
        itemCerrar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK));
        menuSistema.add(itemCerrar);
        itemSalir.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
        itemSalir.setForeground(Color.BLACK);
        itemSalir.setText("Salir del Sistema");
        itemSalir.setIcon(new ImageIcon("src/main/resources/Iconos/shutdown.png"));
        itemSalir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK));
        menuSistema.add(itemSalir);
        
        //propiedades de los items para el menu administradores
        itemAgregarAdmin.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
        itemAgregarAdmin.setForeground(Color.BLACK);
        itemAgregarAdmin.setText("Agregar Administrador");
        itemAgregarAdmin.setIcon(new ImageIcon("src/main/resources/Iconos/agregar.png"));
        itemAgregarAdmin.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK));
        menuAdministradores.add(itemAgregarAdmin);
        itemListadoAdmin.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
        itemListadoAdmin.setForeground(Color.BLACK);
        itemListadoAdmin.setText("Listado de Administradores");
        itemListadoAdmin.setIcon(new ImageIcon("src/main/resources/Iconos/listado.png"));
        itemListadoAdmin.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
        menuAdministradores.add(itemListadoAdmin);
        menuAdministradores.add(sp);
        itemAgregarTA.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
        itemAgregarTA.setForeground(Color.BLACK);
        itemAgregarTA.setText("Agregar Tipo Administrador");
        itemAgregarTA.setIcon(new ImageIcon("src/main/resources/Iconos/agregar.png"));
        itemAgregarTA.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_DOWN_MASK));
        menuAdministradores.add(itemAgregarTA);
        itemListadoTA.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
        itemListadoTA.setForeground(Color.BLACK);
        itemListadoTA.setText("Listado Tipo Administrador");
        itemListadoTA.setIcon(new ImageIcon("src/main/resources/Iconos/listado.png"));
        itemListadoTA.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_K, InputEvent.CTRL_DOWN_MASK));
        menuAdministradores.add(itemListadoTA);
        
        //Propiedades de los item para el menu carrera
        itemAgregarCarrera.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
        itemAgregarCarrera.setForeground(Color.BLACK);
        itemAgregarCarrera.setText("Agregar Carrera o ?rea");
        itemAgregarCarrera.setIcon(new ImageIcon("src/main/resources/Iconos/agregar.png"));
        itemAgregarCarrera.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_DOWN_MASK));
        menuCarreras.add(itemAgregarCarrera);
        itemListadoCarrera.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
        itemListadoCarrera.setForeground(Color.BLACK);
        itemListadoCarrera.setText("Listado de Carreras y ?reas");
        itemListadoCarrera.setIcon(new ImageIcon("src/main/resources/Iconos/listado.png"));
        itemListadoCarrera.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, InputEvent.CTRL_DOWN_MASK));
        menuCarreras.add(itemListadoCarrera);
        
        //Propiedades de los item para el menu comunidad
        itemAgregarComunidad.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
        itemAgregarComunidad.setForeground(Color.BLACK);
        itemAgregarComunidad.setText("Agregar Comunidad");
        itemAgregarComunidad.setIcon(new ImageIcon("src/main/resources/Iconos/agregar.png"));
        itemAgregarComunidad.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_DOWN_MASK));
        menuComunidad.add(itemAgregarComunidad);
        itemListadoComunidad.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
        itemListadoComunidad.setForeground(Color.BLACK);
        itemListadoComunidad.setText("Listado de Comunidad");
        itemListadoComunidad.setIcon(new ImageIcon("src/main/resources/Iconos/listado.png"));
        itemListadoComunidad.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK));
        menuComunidad.add(itemListadoComunidad);
        
        //Propiedades de los item para el menu servicios
        itemAgregarServicios.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
        itemAgregarServicios.setForeground(Color.BLACK);
        itemAgregarServicios.setText("Agregar Servicios");
        itemAgregarServicios.setIcon(new ImageIcon("src/main/resources/Iconos/agregar.png"));
        itemAgregarServicios.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, InputEvent.CTRL_DOWN_MASK));
        menuServicios.add(itemAgregarServicios);
        itemListadoServicios.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
        itemListadoServicios.setForeground(Color.BLACK);
        itemListadoServicios.setText("Listado de Servicios");
        itemListadoServicios.setIcon(new ImageIcon("src/main/resources/Iconos/listado.png"));
        itemListadoServicios.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, InputEvent.CTRL_DOWN_MASK));
        menuServicios.add(itemListadoServicios);
        
        //Propiedades del itemManual
        itemManual.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
        itemManual.setForeground(Color.BLACK);
        itemManual.setText("Manual de Usuario");
        itemManual.setIcon(new ImageIcon("src/main/resources/Iconos/manual.png"));
        itemManual.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, InputEvent.CTRL_DOWN_MASK));
        menuInfo.add(itemManual);

        //Propiedades del menuSistema
        menuSistema.setForeground(Color.BLACK);
        menuSistema.setFont(new Font("Segoe UI Symbol", Font.BOLD, 16));
        menuSistema.setIcon(new ImageIcon("src/main/resources/Iconos/sistema.png"));
        menuBar.add(menuSistema);
        
        //Propiedades del menuAdministradores
        menuAdministradores.setForeground(Color.BLACK);
        menuAdministradores.setFont(new Font("Segoe UI Symbol", Font.BOLD, 16));
        menuAdministradores.setIcon(new ImageIcon("src/main/resources/Iconos/administrador.png"));
        menuBar.add(menuAdministradores);
        
        //Propiedades del menuCarreras
        menuCarreras.setForeground(Color.BLACK);
        menuCarreras.setFont(new Font("Segoe UI Symbol", Font.BOLD, 16));
        menuCarreras.setIcon(new ImageIcon("src/main/resources/Iconos/iconitotese.png"));
        menuBar.add(menuCarreras);
        
        //Propiedades del menuComunidad
        menuComunidad.setForeground(Color.BLACK);
        menuComunidad.setFont(new Font("Segoe UI Symbol", Font.BOLD, 16));
        menuComunidad.setIcon(new ImageIcon("src/main/resources/Iconos/comunidad.png"));
        menuBar.add(menuComunidad);
        
        //Propiedades del menuServicios
        menuServicios.setForeground(Color.BLACK);
        menuServicios.setFont(new Font("Segoe UI Symbol", Font.BOLD, 16));
        menuServicios.setIcon(new ImageIcon("src/main/resources/Iconos/servicios.png"));
        menuBar.add(menuServicios);
        
        //Propiedades del menuServicios
        menuInfo.setForeground(Color.BLACK);
        menuInfo.setFont(new Font("Segoe UI Symbol", Font.BOLD, 16));
        menuInfo.setIcon(new ImageIcon("src/main/resources/Iconos/informacion.png"));
        menuBar.add(menuInfo);
        
        //Propiedades del lblFecha y tiempo
        lblTiempo.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
        lblTiempo.setForeground(Color.BLACK);
        lblTiempo.setBounds(1300, 20, 150, 32);
        escritorio.add(lblTiempo);
        lblFecha.setText(fecha());
        lblFecha.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
        lblFecha.setForeground(Color.BLACK);
        lblFecha.setBounds(1190, 60, 200, 32);
        escritorio.add(lblFecha);      
        
        //Propiedades del menuBar
        this.add(menuBar);

        //Propiedades del jframe
        this.setJMenuBar(menuBar);
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setResizable(Boolean.FALSE);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setTitle(".: Sistema de Registro de Usuarios y Servicios para el Tecnológico de Estudios Superiores de Ecatepec :.");
        this.setIconImage(new ImageIcon("src/main/resources/Iconos/logo.png").getImage());
        this.add(escritorio);

        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
                //--------------------------------
            }

            @Override
            public void windowClosing(WindowEvent e) {
                confirmarCierre();
            }

            @Override
            public void windowClosed(WindowEvent e) {
                //--------------------------------
            }

            @Override
            public void windowIconified(WindowEvent e) {
                //--------------------------------
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
                //--------------------------------
            }

            @Override
            public void windowActivated(WindowEvent e) {
                //--------------------------------
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
                //--------------------------------
            }
        });
        
        //Items del menu sistema
        itemCerrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                itemCerrarActionPerformed(e);
            }
        });
        itemSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
	            itemSalirActionPerformed(e);
            }
        });
	    itemAgregarAdmin.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
			    itemAgregarAdminActionPerformed(e);
		    }
	    });
	    itemListadoAdmin.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
			    itemListadoAdminActionPerformed(e);
		    }
	    });
    }
    private void confirmarCierre(){
	    int res = JOptionPane.showInternalConfirmDialog(escritorio, "¿Estas seguro de salir del sistema?", "Confirmar cierre del sistema. - SiRiUS.", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, pregunta);
        if (res == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
    
    //-------------------------------------------------------------------------------------------
    private void itemCerrarActionPerformed(ActionEvent evt) {
	    int res = JOptionPane.showInternalConfirmDialog(escritorio, "¿Estas seguro de cerrar la sesión actual?", "Confirmar cierre de sesión. - SiRiUS.", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, pregunta);
        if (res == JOptionPane.YES_OPTION) {
            InicioSesion is = new InicioSesion();
            is.setVisible(Boolean.TRUE);
            this.dispose();
        }
    }
    private void itemSalirActionPerformed(ActionEvent evt) {
        int res = JOptionPane.showInternalConfirmDialog(escritorio, "?Estas seguro de salir del sistema?", "Confirmar cierre del sistema. - SiRiUS.", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, pregunta);
        if (res == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
    private void itemAgregarAdminActionPerformed(ActionEvent evt) {
        AgregarAdministrador aa = new AgregarAdministrador(administrador);
	    int x = (escritorio.getWidth() / 2) - aa.getWidth() / 2;
	    int y = (escritorio.getHeight() / 2) - aa.getHeight() / 2;
	    if (aa.isShowing()) {
		    aa.setLocation(x, y);
	    } else {
		    escritorio.add(aa);
		    aa.setLocation(x, y);
		    aa.show();
	    }
    }

	private void itemListadoAdminActionPerformed(ActionEvent evt) {
		ListadoAdministrador la = new ListadoAdministrador(administrador);
		int x = (escritorio.getWidth() / 2) - la.getWidth() / 2;
		int y = (escritorio.getHeight() / 2) - la.getHeight() / 2;
		if (la.isShowing()) {
			la.setLocation(x, y);
		} else {
			escritorio.add(la);
			la.setLocation(x, y);
			la.show();
		}
	}

	//-------------------------------------------------------------------------------------------
	public static String fecha() {
		Date fecha = new Date();
		SimpleDateFormat format = new SimpleDateFormat("EEE dd 'de' MMMM 'del' YYYY");
		return format.format(fecha);
	}

	public void hora() {
		Calendar calendar = new GregorianCalendar();
		Date horaActual = new Date();
        calendar.setTime(horaActual);
        hora = calendar.get(Calendar.HOUR_OF_DAY) > 9 ? "" + calendar.get(Calendar.HOUR_OF_DAY) : "0" + calendar.get(Calendar.HOUR_OF_DAY);
        minuto = calendar.get(Calendar.MINUTE) > 9 ? "" + calendar.get(Calendar.MINUTE) : "0" + calendar.get(Calendar.MINUTE);
        segundo = calendar.get(Calendar.SECOND) > 9 ? "" + calendar.get(Calendar.SECOND) : "0" + calendar.get(Calendar.SECOND);
    }
    
    @Override
    public void run() {
        Thread current = Thread.currentThread();
        while (current == hilo) {            
            hora();
            lblTiempo.setText(hora + ":" + minuto + "." + segundo);
        }
    }
}
