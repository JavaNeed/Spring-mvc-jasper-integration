package org.krams.tutorial.controller;

import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;

import org.krams.tutorial.dao.SalesDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


/**
 * Handles and retrieves download request
 */
@Controller
@RequestMapping("/main")
public class MainController {
	private static Logger logger = LoggerFactory.getLogger(MainController.class);

	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public String getDownloadPage() {
		logger.debug("Received request to show download page");
		return "downloadpage";
	}

	/**
	 * Retrieves the download file in XLS format
	 */
	@RequestMapping(value = "/download/xls", method = RequestMethod.GET)
	public ModelAndView doSalesReportXLS(ModelAndView modelAndView){
		logger.debug("Received request to download Excel report");

		SalesDAO dataprovider = new SalesDAO();

		JRDataSource datasource  = dataprovider.getDataSource();

		Map<String,Object> parameterMap = new HashMap<String,Object>();
		parameterMap.put("datasource", datasource);

		modelAndView = new ModelAndView("xlsReport", parameterMap);
		return modelAndView;
	}

	/**
	 * Retrieves the download file in XLS format
	 */
	@RequestMapping(value = "/download/pdf", method = RequestMethod.GET)
	public ModelAndView doSalesReportPDF(ModelAndView modelAndView){
		logger.debug("Received request to download PDF report");

		SalesDAO dataprovider = new SalesDAO();

		JRDataSource datasource  = dataprovider.getDataSource();

		Map<String,Object> parameterMap = new HashMap<String,Object>();
		parameterMap.put("datasource", datasource);

		modelAndView = new ModelAndView("pdfReport", parameterMap);

		return modelAndView;
	}

	// download HTML file
	@RequestMapping(value = "/download/html", method = RequestMethod.GET)
	public ModelAndView doSalesReporthtmlReport(ModelAndView modelAndView){
		logger.debug("Received request to download HTML report");

		SalesDAO dataprovider = new SalesDAO();

		JRDataSource datasource  = dataprovider.getDataSource();

		Map<String,Object> parameterMap = new HashMap<String,Object>();
		parameterMap.put("datasource", datasource);

		modelAndView = new ModelAndView("htmlReport", parameterMap);

		return modelAndView;
	}

	
	@RequestMapping(value = "/download/csv", method = RequestMethod.GET)
	public ModelAndView doSalesReportCSVReport(ModelAndView modelAndView){
		logger.debug("Received request to download CSV report");

		SalesDAO dataprovider = new SalesDAO();

		JRDataSource datasource  = dataprovider.getDataSource();

		Map<String,Object> parameterMap = new HashMap<String,Object>();
		parameterMap.put("datasource", datasource);

		modelAndView = new ModelAndView("csvReport", parameterMap);

		return modelAndView;
	}
}
