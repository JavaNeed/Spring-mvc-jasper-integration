package org.krams.tutorial.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;

import org.krams.tutorial.service.DownloadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/main")
public class MainController {
	protected static Logger logger = LoggerFactory.getLogger(MainController.class);
	
	@Resource(name="downloadService")
	private DownloadService downloadService;
	
    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public String getDownloadPage() {
    	logger.debug("Received request to show download page");
    
    	return "downloadpage";
	}
 

    @RequestMapping(value = "/download/xls", method = RequestMethod.GET)
    public void doSalesReportXLS(HttpServletResponse response) throws ServletException, IOException,
		ClassNotFoundException, SQLException, JRException {
		logger.debug("Received request to download Excel report");
		
		downloadService.downloadXLS(response);
	}
}
