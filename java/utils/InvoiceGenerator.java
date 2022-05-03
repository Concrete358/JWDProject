package utils;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

import org.apache.log4j.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import entity.Order;
import exception.DaoException;

public class InvoiceGenerator {
	
	private static final String FilePath = "E:\\invoice";
	  private static final String InvoiceTitle = new String("JWD PROJECT");
	  private static final String SubTitle = new String("Invoice for order ");

	  private static final Logger logger = Logger.getLogger(InvoiceGenerator.class);

	  
	 public static void writeInvoice(Order order) throws DaoException {
		PDDocument invc = new PDDocument();
		PDPage newpage = new PDPage();
	    invc.addPage(newpage);
	    PDPage mypage = invc.getPage(0);
	    PDPageContentStream cs = null;
	    try {
	      cs = new PDPageContentStream(invc, mypage);
	      cs.beginText();
	      cs.setFont(PDType1Font.TIMES_ROMAN, 20);
	      cs.newLineAtOffset(250, 750);
	      cs.showText(InvoiceTitle);
	      cs.endText();
	      
	      cs.beginText();
	      cs.setFont(PDType1Font.TIMES_ROMAN, 18);
	      cs.newLineAtOffset(270, 690);
	      cs.showText(SubTitle + order.getOrderId());
	      cs.endText();
	      

	      cs.beginText();
	      cs.setFont(PDType1Font.TIMES_ROMAN, 14);
	      cs.newLineAtOffset(60, 610);
	      cs.showText("Customer Name: " + order.getUserName());
	      cs.endText();
	      
	      cs.beginText();
	      cs.setFont(PDType1Font.TIMES_ROMAN, 14);
	      cs.setLeading(20f);
	      cs.newLineAtOffset(80, 550);
	      cs.showText("Car model");
	      cs.endText();
	      
	      cs.beginText();
	      cs.setFont(PDType1Font.TIMES_ROMAN, 14);
	      cs.setLeading(20f);
	      cs.newLineAtOffset(80, 500);
	      cs.showText("Price per day");
	      cs.endText();
	      
	      cs.beginText();
	      cs.setFont(PDType1Font.TIMES_ROMAN, 14);
	      cs.setLeading(20f);
	      cs.newLineAtOffset(80, 450);
	      cs.showText("Period");
	      cs.endText();
	      
	      cs.beginText();
	      cs.setFont(PDType1Font.TIMES_ROMAN, 14);
	      cs.setLeading(20f);
	      cs.newLineAtOffset(80, 400);
	      cs.showText("Rent Cost");
	      cs.endText();
	      
	      cs.beginText();
	      cs.setFont(PDType1Font.TIMES_ROMAN, 14);
	      cs.setLeading(20f);
	      cs.newLineAtOffset(80, 350);
	      cs.showText("Repair order");
	      cs.endText();
	      
	      cs.beginText();
	      cs.setFont(PDType1Font.TIMES_ROMAN, 14);
	      cs.setLeading(20f);
	      cs.newLineAtOffset(80, 300);
	      cs.showText("Repair Cost");
	      cs.endText();
	      
	      cs.beginText();
	      cs.setFont(PDType1Font.TIMES_ROMAN, 12);
	      cs.setLeading(20f);
	      cs.newLineAtOffset(250, 550);
	      cs.showText(order.getCarName());
	      cs.endText();
	      
	      cs.beginText();
	      cs.setFont(PDType1Font.TIMES_ROMAN, 12);
	      cs.setLeading(20f);
	      cs.newLineAtOffset(250, 500);
	      cs.showText(order.getCarPrice().toString());
	      cs.endText();
	      
	      cs.beginText();
	      cs.setFont(PDType1Font.TIMES_ROMAN, 12);
	      cs.setLeading(20f);
	      cs.newLineAtOffset(250, 450);
	      cs.showText("from " + order.getRentStartDate());
	      cs.newLine();
	      cs.showText("to " + order.getRentFinishDate());
	      cs.endText();
	      
	      cs.beginText();
	      cs.setFont(PDType1Font.TIMES_ROMAN, 12);
	      cs.setLeading(20f);
	      cs.newLineAtOffset(250, 400);
	      cs.showText(order.getRentCost().toString() + " BYN");
	      cs.endText();
	      
	      cs.beginText();
	      cs.setFont(PDType1Font.TIMES_ROMAN, 14);
	      cs.newLineAtOffset(250, 350);
	      if(order.isDamageStatus()) {
	    	  cs.showText("" + order.getRepairOrderId());
	      } else {
	    	  cs.showText("NO DAMAGE");  
	      }
	      cs.endText();	      
	      
	      cs.beginText();
	      cs.setFont(PDType1Font.TIMES_ROMAN, 14);
	      cs.newLineAtOffset(250, 300);
	      if(order.isDamageStatus()) {
	    	  cs.showText(order.getRepairCost().toString() + "BYN");
	      } else {
	    	  cs.showText(" - ");  
	      }
	      cs.endText();	      
	      
	      cs.beginText();
	      cs.setFont(PDType1Font.TIMES_ROMAN, 14);
	      cs.newLineAtOffset(400, 250);
	      cs.showText("Total: " + order.getTotalCost().toString() + " BYN");
	      cs.endText();	      
	      DateTimeFormatter formatter = 
	    	        new DateTimeFormatterBuilder().appendValue(ChronoField.YEAR, 4)
	    	                                      .appendValue(ChronoField.MONTH_OF_YEAR, 2)
	    	                                      .appendValue(ChronoField.DAY_OF_MONTH, 2)
	    	                                      .appendValue(ChronoField.HOUR_OF_DAY, 2)
	    	                                      .appendValue(ChronoField.MINUTE_OF_HOUR, 2)
	    	                                      .appendValue(ChronoField.SECOND_OF_MINUTE, 2)
	    	                                      .toFormatter();
	      cs.close();
	      invc.save(FilePath + order.getOrderId() + LocalDateTime.now().format(formatter) + ".pdf");
	    } catch (IOException e) {
	      logger.warn("Exception while create PDF invoice",e);
	      throw new DaoException(e);
	    } finally {
	    	try {
				invc.close();
			} catch (IOException e) {
				logger.warn("can't close PDF invoice file", e);
			}
	    }
	    } 
}
