package com.bo.acredito.util.reportgenerator;

/**
 * Created by Alvaro on 4/17/2015.
 */
import com.vaadin.server.StreamResource;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.query.JRJpaQueryExecuterFactory;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.persistence.EntityManager;
import java.io.*;
import java.util.HashMap;

/**
 * This class makes the work, loads the template, compile, fill and export the report.
 */
public class ReportGenerator {
    private Log log= LogFactory.getLog(this.getClass());

    public ReportGenerator() {
    }

    public void executeReport(String templatePath, EntityManager em) throws JRException {

        JasperDesign jasperDesign=loadTemplate(templatePath);
        setTempDirectory(templatePath);
        JasperReport jasperReport=compileReport(jasperDesign);
        JasperPrint jasperPrint=fillReport(jasperReport, em);
        exportReportToPdf(jasperPrint);
    }

    /**
     * Load the template (defined by templatePath) and return a JasperDesign object representing the template
     * @return JasperDesign
     */
    private JasperDesign loadTemplate(String templatePath){
        JasperDesign jasperDesign=null;
        File templateFile=new File(templatePath);
        System.out.println("ABSOLUTE PATH: "+templateFile.getAbsolutePath());
        if(templateFile.exists()){
            try {
                jasperDesign= JRXmlLoader.load(templateFile);
            } catch (JRException e) {
                log.error("Error in loading the template... "+e.getMessage());
            }
        }
        else
            log.error("Error, the file dont exists");
        return(jasperDesign);
    }

    /**
     * Compile the report and generates a binary version of it
     * @param jasperDesign The report design
     * @return JasperReport
     */
    private JasperReport compileReport(JasperDesign jasperDesign){
        JasperReport jasperReport=null;
        try {
            jasperReport= JasperCompileManager.compileReport(jasperDesign);
        } catch (JRException e) {
            log.error("Error in compiling the report.... "+e.getMessage());
        }
        return(jasperReport);
    }

    /**
     * Fill the report and generates a binary version of it
     * @param jasperReport The Compiled report design
     * @return JasperPrint
     */
    private JasperPrint fillReport(JasperReport jasperReport, EntityManager em){
        JasperPrint jasperPrint=null;
        HashMap fillParameters=new HashMap();
        fillParameters.put(JRJpaQueryExecuterFactory.PARAMETER_JPA_ENTITY_MANAGER, em);
        try {
            jasperPrint =JasperFillManager.fillReport(
                    jasperReport,
                    fillParameters);
        } catch (JRException e) {
            log.error("Error in filling the report..... "+e.getMessage());
        }
        return(jasperPrint);
    }

    /**
     * Prepare a JRExporter for the filled report (to HTML)
     * @param jasperPrint The jasperPrint
     * @return The HTML text
     */
    private void exportReportToPdf(JasperPrint jasperPrint) throws JRException {
        JRPdfExporter exporter = new JRPdfExporter();

        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput("d:\\temp\\reporte.pdf"));
        exporter.exportReport();
    }
    /**
     * Set the temp directory for report generation
     */
    private void setTempDirectory(String templatePath){
        File templateFile=new File(templatePath);
        if(templateFile.exists()){
            log.info("Setting parentDirectory: "+templateFile.getParent());
            System.setProperty("jasper.reports.compile.temp", templateFile.getParent());
        }
    }
}
