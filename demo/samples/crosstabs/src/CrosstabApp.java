/*
 * JasperReports - Free Java Reporting Library.
 * Copyright (C) 2001 - 2025 Cloud Software Group, Inc. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of JasperReports.
 *
 * JasperReports is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JasperReports is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with JasperReports. If not, see <http://www.gnu.org/licenses/>.
 */
import java.io.File;
import java.util.Calendar;
import java.util.Date;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRCsvMetadataExporter;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.export.oasis.JROdsExporter;
import net.sf.jasperreports.engine.export.oasis.JROdtExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRPptxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.export.ooxml.XlsxMetadataExporter;
import net.sf.jasperreports.engine.util.AbstractSampleApp;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOdsReportConfiguration;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleWriterExporterOutput;
import net.sf.jasperreports.export.SimpleXlsMetadataReportConfiguration;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;
import net.sf.jasperreports.export.SimpleXlsxMetadataReportConfiguration;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
import net.sf.jasperreports.json.export.JsonMetadataExporter;
import net.sf.jasperreports.poi.export.JRXlsExporter;
import net.sf.jasperreports.poi.export.JRXlsMetadataExporter;


/**
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 */
public class CrosstabApp extends AbstractSampleApp
{


	/**
	 *
	 */
	public static void main(String[] args)
	{
		main(new CrosstabApp(), args);
	}
	
	
	@Override
	public void test() throws JRException
	{
		compile();
		fill();
		pdf();
		html();
		rtf();
		csv();
		csvMetadata();
		jsonMetadata();
		xls();
		xlsMetadata();
		odt();
		ods();
		docx();
		xlsx();
		xlsxMetadata();
		pptx();
		xmlEmbed();
		xml();
	}


	/**
	 *
	 */
	public void fill() throws JRException
	{
		File[] files = getFiles(new File("target/reports"), "jasper");
		for(int i = 0; i < files.length; i++)
		{
			File reportFile = files[i];
			long start = System.currentTimeMillis();
			JasperFillManager.fillReportToFile(
				reportFile.getAbsolutePath(), 
				null, 
				getDemoHsqldbConnection()
				);
			System.err.println("Report : " + reportFile + ". Filling time : " + (System.currentTimeMillis() - start));
		}
	}
	
	
	/**
	 *
	 */
	public void print() throws JRException
	{
		File[] files = getFiles(new File("target/reports"), "jrprint");
		for(int i = 0; i < files.length; i++)
		{
			File reportFile = files[i];
			long start = System.currentTimeMillis();
			JasperPrintManager.printReport(
				reportFile.getAbsolutePath(), 
				true
				);
			System.err.println("Report : " + reportFile + ". Printing time : " + (System.currentTimeMillis() - start));
		}
	}
	
	
	/**
	 *
	 */
	public void pdf() throws JRException
	{
		File[] files = getFiles(new File("target/reports"), "jrprint");
		for(int i = 0; i < files.length; i++)
		{
			File reportFile = files[i];
			long start = System.currentTimeMillis();
			JasperExportManager.exportReportToPdfFile(
				reportFile.getAbsolutePath()
				);
			System.err.println("Report : " + reportFile + ". PDF creation time : " + (System.currentTimeMillis() - start));
		}
	}
	
	
	/**
	 *
	 */
	public void xml() throws JRException
	{
		File[] files = getFiles(new File("target/reports"), "jrprint");
		for(int i = 0; i < files.length; i++)
		{
			File reportFile = files[i];
			long start = System.currentTimeMillis();
			JasperExportManager.exportReportToXmlFile(
				reportFile.getAbsolutePath(),
				false
				);
			System.err.println("Report : " + reportFile + ". XML creation time : " + (System.currentTimeMillis() - start));
		}
	}
	
	
	/**
	 *
	 */
	public void xmlEmbed() throws JRException
	{
		File[] files = getFiles(new File("target/reports"), "jrprint");
		for(int i = 0; i < files.length; i++)
		{
			File reportFile = files[i];
			long start = System.currentTimeMillis();
			JasperExportManager.exportReportToXmlFile(
				reportFile.getAbsolutePath(), 
				true
				);
			System.err.println("Report : " + reportFile + ". XML creation time : " + (System.currentTimeMillis() - start));
		}
	}
	
	
	/**
	 *
	 */
	public void html() throws JRException
	{
		File[] files = getFiles(new File("target/reports"), "jrprint");
		for(int i = 0; i < files.length; i++)
		{
			File reportFile = files[i];
			long start = System.currentTimeMillis();
			JasperExportManager.exportReportToHtmlFile(
				reportFile.getAbsolutePath()
				);
			System.err.println("Report : " + reportFile + ". HTML creation time : " + (System.currentTimeMillis() - start));
		}
	}
	
	
	/**
	 *
	 */
	public void rtf() throws JRException
	{
		File[] files = getFiles(new File("target/reports"), "jrprint");
		for(int i = 0; i < files.length; i++)
		{
			long start = System.currentTimeMillis();
			File sourceFile = files[i];

			JasperPrint jasperPrint = (JasperPrint)JRLoader.loadObject(sourceFile);

			File destFile = new File(sourceFile.getParent(), jasperPrint.getName() + ".rtf");
		
			JRRtfExporter exporter = new JRRtfExporter();
		
			exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			exporter.setExporterOutput(new SimpleWriterExporterOutput(destFile));
		
			exporter.exportReport();

			System.err.println("Report : " + sourceFile + ". RTF creation time : " + (System.currentTimeMillis() - start));
		}
	}
	
	
	/**
	 *
	 */
	public void xls() throws JRException
	{
		File[] files = getFiles(new File("target/reports"), "jrprint");
		for(int i = 0; i < files.length; i++)
		{
			long start = System.currentTimeMillis();
			File sourceFile = files[i];

			JasperPrint jasperPrint = (JasperPrint)JRLoader.loadObject(sourceFile);

			File destFile = new File(sourceFile.getParent(), jasperPrint.getName() + ".xls");
		
			JRXlsExporter exporter = new JRXlsExporter();
		
			exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(destFile));
			SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
			configuration.setOnePagePerSheet(true);
			exporter.setConfiguration(configuration);
		
			exporter.exportReport();

			System.err.println("Report : " + sourceFile + ". XLS creation time : " + (System.currentTimeMillis() - start));
		}
	}
	
	
	/**
	 *
	 */
	public void xlsMetadata() throws JRException
	{
		File[] files = getFiles(new File("target/reports"), "jrprint");
		for(int i = 0; i < files.length; i++)
		{
			long start = System.currentTimeMillis();
			File sourceFile = files[i];

			JasperPrint jasperPrint = (JasperPrint)JRLoader.loadObject(sourceFile);

			File destFile = new File(sourceFile.getParent(), jasperPrint.getName() + ".metadata.xls");
		
			JRXlsMetadataExporter exporter = new JRXlsMetadataExporter();
		
			exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(destFile));
			SimpleXlsMetadataReportConfiguration configuration = new SimpleXlsMetadataReportConfiguration();
			configuration.setOnePagePerSheet(true);
			exporter.setConfiguration(configuration);
		
			exporter.exportReport();

			System.err.println("Report : " + sourceFile + ". Metadata XLS creation time : " + (System.currentTimeMillis() - start));
		}
	}
	
	
	/**
	 *
	 */
	public void csv() throws JRException
	{
		File[] files = getFiles(new File("target/reports"), "jrprint");
		for(int i = 0; i < files.length; i++)
		{
			long start = System.currentTimeMillis();
			File sourceFile = files[i];

			JasperPrint jasperPrint = (JasperPrint)JRLoader.loadObject(sourceFile);

			File destFile = new File(sourceFile.getParent(), jasperPrint.getName() + ".csv");
		
			JRCsvExporter exporter = new JRCsvExporter();
		
			exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			exporter.setExporterOutput(new SimpleWriterExporterOutput(destFile));
		
			exporter.exportReport();

			System.err.println("Report : " + sourceFile + ". CSV creation time : " + (System.currentTimeMillis() - start));
		}
	}
	
	
	/**
	 *
	 */
	public void csvMetadata() throws JRException
	{
		File[] files = getFiles(new File("target/reports"), "jrprint");
		for(int i = 0; i < files.length; i++)
		{
			long start = System.currentTimeMillis();
			File sourceFile = files[i];

			JasperPrint jasperPrint = (JasperPrint)JRLoader.loadObject(sourceFile);

			File destFile = new File(sourceFile.getParent(), jasperPrint.getName() + ".metadata.csv");
		
			JRCsvMetadataExporter exporter = new JRCsvMetadataExporter();
		
			exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			exporter.setExporterOutput(new SimpleWriterExporterOutput(destFile));
		
			exporter.exportReport();

			System.err.println("Report : " + sourceFile + ". Metadata CSV creation time : " + (System.currentTimeMillis() - start));
		}
	}
	
	
	/**
	 *
	 */
	public void jsonMetadata() throws JRException
	{
		File[] files = getFiles(new File("target/reports"), "jrprint");
		for(int i = 0; i < files.length; i++)
		{
			long start = System.currentTimeMillis();
			File sourceFile = files[i];

			JasperPrint jasperPrint = (JasperPrint)JRLoader.loadObject(sourceFile);

			File destFile = new File(sourceFile.getParent(), jasperPrint.getName() + ".json");
		
			JsonMetadataExporter exporter = new JsonMetadataExporter();
		
			exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			exporter.setExporterOutput(new SimpleWriterExporterOutput(destFile));
		
			exporter.exportReport();

			System.err.println("Report : " + sourceFile + ". Metadata JSON creation time : " + (System.currentTimeMillis() - start));
		}
	}
	
	
	/**
	 *
	 */
	public void odt() throws JRException
	{
		File[] files = getFiles(new File("target/reports"), "jrprint");
		for(int i = 0; i < files.length; i++)
		{
			long start = System.currentTimeMillis();
			File sourceFile = files[i];

			JasperPrint jasperPrint = (JasperPrint)JRLoader.loadObject(sourceFile);

			File destFile = new File(sourceFile.getParent(), jasperPrint.getName() + ".odt");
		
			JROdtExporter exporter = new JROdtExporter();
		
			exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(destFile));
		
			exporter.exportReport();

			System.err.println("Report : " + sourceFile + ". ODT creation time : " + (System.currentTimeMillis() - start));
		}
	}
	
	
	/**
	 *
	 */
	public void ods() throws JRException
	{
		File[] files = getFiles(new File("target/reports"), "jrprint");
		for(int i = 0; i < files.length; i++)
		{
			long start = System.currentTimeMillis();
			File sourceFile = files[i];

			JasperPrint jasperPrint = (JasperPrint)JRLoader.loadObject(sourceFile);

			File destFile = new File(sourceFile.getParent(), jasperPrint.getName() + ".ods");
		
			JROdsExporter exporter = new JROdsExporter();
		
			exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(destFile));
			SimpleOdsReportConfiguration configuration = new SimpleOdsReportConfiguration();
			configuration.setOnePagePerSheet(true);
			exporter.setConfiguration(configuration);
		
			exporter.exportReport();

			System.err.println("Report : " + sourceFile + ". ODS creation time : " + (System.currentTimeMillis() - start));
		}
	}
	
	
	/**
	 *
	 */
	public void docx() throws JRException
	{
		File[] files = getFiles(new File("target/reports"), "jrprint");
		for(int i = 0; i < files.length; i++)
		{
			long start = System.currentTimeMillis();
			File sourceFile = files[i];

			JasperPrint jasperPrint = (JasperPrint)JRLoader.loadObject(sourceFile);

			File destFile = new File(sourceFile.getParent(), jasperPrint.getName() + ".docx");
		
			JRDocxExporter exporter = new JRDocxExporter();
		
			exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(destFile));
		
			exporter.exportReport();

			System.err.println("Report : " + sourceFile + ". DOCX creation time : " + (System.currentTimeMillis() - start));
		}
	}
	
	
	/**
	 *
	 */
	public void xlsx() throws JRException
	{
		File[] files = getFiles(new File("target/reports"), "jrprint");
		for(int i = 0; i < files.length; i++)
		{
			long start = System.currentTimeMillis();
			File sourceFile = files[i];

			JasperPrint jasperPrint = (JasperPrint)JRLoader.loadObject(sourceFile);

			File destFile = new File(sourceFile.getParent(), jasperPrint.getName() + ".xlsx");
		
			JRXlsxExporter exporter = new JRXlsxExporter();
		
			exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(destFile));
			SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
			configuration.setOnePagePerSheet(true);
			exporter.setConfiguration(configuration);
		
			exporter.exportReport();

			System.err.println("Report : " + sourceFile + ". XLSX creation time : " + (System.currentTimeMillis() - start));
		}
	}
	
	
	/**
	 *
	 */
	public void xlsxMetadata() throws JRException
	{
		File[] files = getFiles(new File("target/reports"), "jrprint");
		for(int i = 0; i < files.length; i++)
		{
			long start = System.currentTimeMillis();
			File sourceFile = files[i];

			JasperPrint jasperPrint = (JasperPrint)JRLoader.loadObject(sourceFile);

			File destFile = new File(sourceFile.getParent(), jasperPrint.getName() + ".metadata.xlsx");
		
			XlsxMetadataExporter exporter = new XlsxMetadataExporter();
		
			exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(destFile));
			SimpleXlsxMetadataReportConfiguration configuration = new SimpleXlsxMetadataReportConfiguration();
			configuration.setOnePagePerSheet(true);
			exporter.setConfiguration(configuration);
		
			exporter.exportReport();

			System.err.println("Report : " + sourceFile + ". Metadata XLSX creation time : " + (System.currentTimeMillis() - start));
		}
	}
	
	
	/**
	 *
	 */
	public void pptx() throws JRException
	{
		File[] files = getFiles(new File("target/reports"), "jrprint");
		for(int i = 0; i < files.length; i++)
		{
			long start = System.currentTimeMillis();
			File sourceFile = files[i];

			JasperPrint jasperPrint = (JasperPrint)JRLoader.loadObject(sourceFile);

			File destFile = new File(sourceFile.getParent(), jasperPrint.getName() + ".pptx");
		
			JRPptxExporter exporter = new JRPptxExporter();
		
			exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(destFile));
		
			exporter.exportReport();

			System.err.println("Report : " + sourceFile + ". PPTX creation time : " + (System.currentTimeMillis() - start));
		}
	}
	
	
	/**
	 *
	 */
	public static final Date truncateToMonth(Date date)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month);
		return calendar.getTime();
	}
	
	
	/**
	 *
	 */
	public static final Date truncateToYear(Date date)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		return calendar.getTime();
	}

}
