package com.bo.acredito.ui.util;

import com.bo.acredito.util.reportgenerator.ReportGenerator;
import com.vaadin.server.FileDownloader;
import com.vaadin.server.StreamResource;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.Button;
import net.sf.jasperreports.engine.JRException;

import javax.persistence.EntityManager;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Calendar;

/**
 * Created by alvaro on 22/4/15.
 */
public class ReportsUtil {
    private String baseReportsPath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath()+"/WEB-INF";

    public void prepareForPdfReport(EntityManager entityManager, String reportTemplate,
                                 String reportOutputFilename, Button buttonToExtend){


        reportOutputFilename+=("_"+getDateAsString()+".pdf");
        StreamResource myResource = new ReportsUtil()
                .createPdfResource(entityManager,
                        reportTemplate,reportOutputFilename);
        FileDownloader fileDownloader = new FileDownloader(myResource);
        fileDownloader.extend(buttonToExtend);

    }

    private StreamResource createPdfResource(final EntityManager em, final String templatePath, String reportFileName) {
        return new StreamResource(new StreamResource.StreamSource() {
            @Override
            public InputStream getStream () {
                ByteArrayOutputStream pdfBuffer = new ByteArrayOutputStream();
                ReportGenerator reportGenerator=new ReportGenerator();

                try {
                    reportGenerator.executeReport(baseReportsPath+templatePath, em, pdfBuffer);
                } catch (JRException e) {
                    e.printStackTrace();
                }
                // Return a stream from the buffer.
                return new ByteArrayInputStream(
                        pdfBuffer.toByteArray());
            }
        }, reportFileName);
    }
    private String getDateAsString(){
        return(String.valueOf(Calendar.getInstance().get(Calendar.YEAR))+
                String.valueOf(Calendar.getInstance().get(Calendar.MONTH)+1)+
                String.valueOf(Calendar.getInstance().get(Calendar.DAY_OF_MONTH))+
                String.valueOf(Calendar.getInstance().get(Calendar.HOUR_OF_DAY))+
                String.valueOf(Calendar.getInstance().get(Calendar.MINUTE))+
                String.valueOf(Calendar.getInstance().get(Calendar.SECOND)));
    }
}
