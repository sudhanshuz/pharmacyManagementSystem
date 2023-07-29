package com.pms.report.service;

import java.io.ByteArrayInputStream;

import java.io.ByteArrayOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.pms.report.model.Sales;
@Service
public class PdfService {
	private Logger logger=LoggerFactory.getLogger(PdfService.class);
	public ByteArrayInputStream createPdf(Sales sales) throws JsonProcessingException {
		String title="Sales Report";
		String content="PharmaCare";
		
		ByteArrayOutputStream output=new ByteArrayOutputStream();
		Document document=new Document();
		PdfWriter.getInstance(document, output);
		
		HeaderFooter footer=new HeaderFooter(true, new Phrase("page no"));
		footer.setAlignment(Element.ALIGN_CENTER);
		footer.setBorderWidthBottom(0);
		document.setFooter(footer);
		document.open();
		Font titleFont=FontFactory.getFont(FontFactory.HELVETICA_BOLD,25);
		Paragraph titlePara=new Paragraph(title,titleFont);
		titlePara.setAlignment(Element.ALIGN_CENTER);
		document.add(titlePara);
		
		Font ContentFont=FontFactory.getFont(FontFactory.HELVETICA_BOLD,25);
		Paragraph contentPara=new Paragraph(content,ContentFont);
		contentPara.setAlignment(Element.ALIGN_CENTER);
		document.add(contentPara);
		
		
		
		
		PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100);
        table.setSpacingBefore(20);

        // Set the table headers
        Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        PdfPCell headerCell = new PdfPCell(new Phrase("Drug Name", headerFont));
        table.addCell(headerCell);
        headerCell = new PdfPCell(new Phrase("Date and Time", headerFont));
        table.addCell(headerCell);
        headerCell = new PdfPCell(new Phrase("Total Amount", headerFont));
        table.addCell(headerCell);
        headerCell = new PdfPCell(new Phrase("Paid Amount", headerFont));
        table.addCell(headerCell);
        headerCell = new PdfPCell(new Phrase("Balance", headerFont));
        table.addCell(headerCell);

        // Add data rows to the table
        for (int i = 1; i <= sales.getDrugName().size(); i++) {
            table.addCell(sales.getDrugName().get(i-1));
            if(i==1) {
            table.addCell(sales.getDateAndTime());
            table.addCell(Double.toString(sales.getTotalAmt()));
            table.addCell(Double.toString(sales.getPaidAmt()));
            table.addCell(Double.toString(sales.getBalance()));}
            else {
            	table.addCell("");
                table.addCell("");
                table.addCell("");
                table.addCell("");
            }
        }

        // Add the table to the document
		
		document.add(table);
		document.close();
		
		return new ByteArrayInputStream(output.toByteArray());
	}
}
