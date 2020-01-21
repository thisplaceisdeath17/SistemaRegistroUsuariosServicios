package Reportes;

import Conexion.Conexion;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class GenerarReportes {

	Conexion conn = new Conexion();
	boolean conexion = conn.conectar();

	public void generarReporte(int matricula) {
		try {
			JasperReport report = (JasperReport) JRLoader.loadObject(new File(new File("").getAbsolutePath() + "/src/main/resources/Reportes/ReporteComunidad/ReporteComunidad.jasper"));
			Map param = new HashMap();
			param.put("matricula", matricula);
			JasperPrint print = JasperFillManager.fillReport(report, param, conn.getConn());
			JasperViewer viewer = new JasperViewer(print, Boolean.FALSE);
			viewer.setTitle("Reporte de Comunidad. - SiRiUS.");
			viewer.setVisible(Boolean.TRUE);
			System.out.println(matricula);
		} catch (JRException ex) {
			System.out.println("Error al mostrar reporte: " + ex.getMessage());
			ex.printStackTrace();
		}
	}
}
