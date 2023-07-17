package com.pms.report.controller;

import java.io.ByteArrayInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pms.report.service.PdfService;

@RestController
public class PDFController {
	@Autowired
	private PdfService pdfService;
	
	@GetMapping("/pdf")
	public ResponseEntity<InputStreamResource> createPdf() {
		ByteArrayInputStream pdf=pdfService.createPdf();
		HttpHeaders httpHeaders=new HttpHeaders();
		httpHeaders.add("Content-Disposition","inline;file=lcwd.pdf");
		return ResponseEntity.ok().headers(httpHeaders).contentType(MediaType.APPLICATION_PDF)
					.body(new InputStreamResource(pdf));
	}
}
