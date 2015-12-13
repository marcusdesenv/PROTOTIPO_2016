///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//package util;
//
//import java.io.OutputStream;
//import java.sql.Connection;
//import java.util.*;
//import javax.faces.context.FacesContext;
//import javax.servlet.ServletContext;
//import javax.servlet.http.HttpServletResponse;
//import net.sf.jasperreports.engine.JasperExportManager;
//import net.sf.jasperreports.engine.JasperFillManager;
//import net.sf.jasperreports.engine.JasperPrint;
//import net.sf.jasperreports.engine.JasperReport;
//import net.sf.jasperreports.engine.export.JExcelApiExporter;
//import net.sf.jasperreports.engine.export.JExcelApiExporterParameter;
//import net.sf.jasperreports.engine.util.JRLoader;
//import org.hibernate.Session;
//import seguranca.dao.HibernateUtil;
//
///**
// *
//// * @author ra21004438
// */
//public class Report {
//     protected Connection cn;
//
//    public Report() {
//        Session hib = HibernateUtil.getSessionFactory().openSession();
//        cn = hib.connection();
//    }
//
//    public boolean impRelPDFJasper(String rel, HashMap params, FacesContext facesContext) {
//        try {
//            facesContext.responseComplete();
//            ServletContext context = (ServletContext) facesContext.getExternalContext().getContext();
//            String path = context.getRealPath("/WEB-INF/reports");
//            HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
//            response.reset();
//            response.setContentType("application/pdf");
//            OutputStream out = response.getOutputStream();
//
//            JasperReport jr = (JasperReport) JRLoader.loadObject(path + "\\" + rel);
//            JasperPrint jp = JasperFillManager.fillReport(jr, params, cn);
//            JasperExportManager.exportReportToPdfStream(jp, out);
//
//            return true;
//        } catch (Exception ex) {
//            System.out.println("Exce��o: " + ex.getMessage());
//            return false;
//        }
//
//    }
//
//    public boolean impRelExcelJasper(String rel, HashMap params, FacesContext facesContext) {
//        try {
//            facesContext.responseComplete();
//            ServletContext context = (ServletContext) facesContext.getExternalContext().getContext();
//            String path = context.getRealPath("/WEB-INF/reports");
//            HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
//            response.reset();
//            response.setContentType("excel");
//            OutputStream out = response.getOutputStream();
//
//            JasperReport jr = (JasperReport) JRLoader.loadObject(path + "\\" + rel);
//            JasperPrint jp = JasperFillManager.fillReport(jr, params, cn);
//
//            JExcelApiExporter exporter = new JExcelApiExporter();
//            exporter.setParameter(JExcelApiExporterParameter.JASPER_PRINT, jp);
//            exporter.setParameter(JExcelApiExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
//            exporter.setParameter(JExcelApiExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS, Boolean.TRUE);
//            exporter.setParameter(JExcelApiExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
//            exporter.setParameter(JExcelApiExporterParameter.IS_FONT_SIZE_FIX_ENABLED, Boolean.TRUE);
//            exporter.setParameter(JExcelApiExporterParameter.IS_COLLAPSE_ROW_SPAN, Boolean.TRUE);
//            exporter.setParameter(JExcelApiExporterParameter.OUTPUT_STREAM, out);
//            exporter.exportReport();
//
//            return true;
//        } catch (Exception ex) {
//            System.out.println("Exce��o: " + ex.getMessage());
//            return false;
//        }
//
//    }
//    
//}