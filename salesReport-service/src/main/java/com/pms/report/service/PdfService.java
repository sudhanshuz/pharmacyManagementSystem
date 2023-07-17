package com.pms.report.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfWriter;
@Service
public class PdfService {
	private Logger logger=LoggerFactory.getLogger(PdfService.class);
	public ByteArrayInputStream createPdf() {
		String title="xyz";
		String content="main content";
		
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
		
		Font paraFont=FontFactory.getFont(FontFactory.HELVETICA,18);
		Paragraph contentPara=new Paragraph(content,paraFont);
		contentPara.add(new Chunk("added text"));
		document.add(contentPara);
		document.close();
		
		return new ByteArrayInputStream(output.toByteArray());
	}
}
