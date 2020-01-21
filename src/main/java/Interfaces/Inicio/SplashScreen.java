package Interfaces.Inicio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SplashScreen extends JFrame {
	private JLabel lblIcono = new JLabel();

	public SplashScreen() {
		initComponents();
		tiempo.start();
		ImageIcon icono = new ImageIcon("src/main/resources/Iconos/logo.png");
		ImageIcon iconoScaled = new ImageIcon(icono.getImage().getScaledInstance(lblIcono.getWidth(), lblIcono.getHeight(), Image.SCALE_SMOOTH));
		lblIcono.setIcon(iconoScaled);
	}

	private void initComponents() {
		lblIcono.setBounds(0, 0, 500, 500);
		this.add(lblIcono);

		this.setLayout(null);
		this.setSize(500, 500);
		this.setLocationRelativeTo(null);
		this.setUndecorated(Boolean.TRUE);
		this.setAlwaysOnTop(Boolean.TRUE);
	}

	private Timer tiempo = new Timer(5000, new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			InicioSesion is = new InicioSesion();
			is.setVisible(Boolean.TRUE);
			dispose();
			tiempo.stop();
		}
	});
}
