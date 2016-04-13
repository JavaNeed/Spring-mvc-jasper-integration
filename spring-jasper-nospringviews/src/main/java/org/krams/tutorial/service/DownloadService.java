package org.krams.tutorial.service;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import org.krams.tutorial.dao.SalesDAO;
import org.krams.tutorial.jasper.Exporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("downloadService")
@Transactional
public class DownloadService {
	private static Logger LOGGER = LoggerFactory.getLogger(DownloadService.class);

	public void downloadXLS(HttpServletResponse response) throws ClassNotFoundException, JRException {
		LOGGER.debug("Downloading Excel report");
		
		SalesDAO datasource = new SalesDAO();
		JRDataSource ds = datasource.getDataSource();

		// params is used for passing extra parameters 
		HashMap<String, Object> params = new HashMap<>(); 
		params.put("Title", "Sales Report");
		
		// Retrieve our report template
		InputStream reportStream = this.getClass().getResourceAsStream("/tree-template.jrxml"); 

		// Create a JasperDesign object from the JRXMl file
		JasperDesign jd = JRXmlLoader.load(reportStream);
		
		// You can also load the template by directly adding the actual path, i.e. 
		//JasperDesign jd = JRXmlLoader.load("c:/krams/jasper/reports/tree-template.jrxml");
		
		// You can also let Spring inject the template
		// See http://stackoverflow.com/questions/734671/read-file-in-classpath
		
		// Compile our report layout
		JasperReport jr = JasperCompileManager.compileReport(jd);
		JasperPrint jp = JasperFillManager.fillReport(jr, params, ds);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		Exporter exporter = new Exporter();
		exporter.export(jp, baos);

		String fileName = "SalesReport.xls";
		response.setHeader("Content-Disposition", "inline; filename="+ fileName);

		response.setContentType("application/vnd.ms-excel");
		response.setContentLength(baos.size());

		// Write to reponse stream
		writeReportToResponseStream(response, baos);
	}
	

	private void writeReportToResponseStream(HttpServletResponse response,ByteArrayOutputStream baos) {
		LOGGER.debug("Writing report to the stream");
		
		try {
			// Retrieve the output stream
			ServletOutputStream outputStream = response.getOutputStream();
			baos.writeTo(outputStream);
			outputStream.flush();
		} catch (Exception e) {
			LOGGER.error("Unable to write report to the output stream");
		}
	}

}
