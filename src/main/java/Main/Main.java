package Main;

import Interfaces.Inicio.InicioSesion;
import com.jgoodies.looks.plastic.PlasticLookAndFeel;
import com.jgoodies.looks.plastic.PlasticXPLookAndFeel;
import com.jgoodies.looks.plastic.theme.DesertBlue;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        PlasticLookAndFeel laf = new PlasticXPLookAndFeel();
        PlasticXPLookAndFeel.setCurrentTheme(new DesertBlue());
        try {
            UIManager.setLookAndFeel(laf);
        } catch (UnsupportedLookAndFeelException ex) {
            System.out.println("Error al mostrar Look and Feel: " + ex);
        }
        InicioSesion is = new InicioSesion();
        is.setVisible(true);
    }
}
