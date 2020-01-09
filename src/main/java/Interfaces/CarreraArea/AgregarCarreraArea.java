package Interfaces.CarreraArea;

import javax.swing.*;

public class AgregarCarreraArea extends JInternalFrame {
	private JPanel pp = new JPanel();

	public AgregarCarreraArea(String[] usuario) {
		initComponents();
	}

	private void initComponents() {
		this.setLayout(null);
		this.setSize(600, 400);
		this.setTitle(".: Agregar Carrera o Área. :.");
		this.setFrameIcon(new ImageIcon("src/main/resources/Iconos/iconitotese_16.png"));
	}
}
