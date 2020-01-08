package Main;

import Conexion.Conexion;
import Interfaces.Inicio.InicioSesion;
import com.jgoodies.looks.plastic.Plastic3DLookAndFeel;
import com.jgoodies.looks.plastic.PlasticLookAndFeel;
import com.jgoodies.looks.plastic.theme.ExperienceRoyale;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        PlasticLookAndFeel laf = new Plastic3DLookAndFeel();
        Plastic3DLookAndFeel.setCurrentTheme(new ExperienceRoyale());
        try {
            UIManager.setLookAndFeel(laf);
        } catch (UnsupportedLookAndFeelException ex) {
            System.out.println("Error al mostrar Look and Feel: " + ex);
        }
        InicioSesion is = new InicioSesion();
        is.setVisible(true);
    }
}
