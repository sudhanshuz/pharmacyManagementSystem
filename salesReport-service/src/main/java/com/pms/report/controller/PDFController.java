package com.pms.report.controller;

import java.io.ByteArrayInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pms.report.model.Sales;
import com.pms.report.service.PdfService;

@RestController
@RequestMapping("/salesReport")
@CrossOrigin("*")
public class PDFController {
	@Autowired
	private PdfService pdfService;
	
	@PostMapping("/pdf")
	public ResponseEntity<InputStreamResource> createPdf(@RequestBody Sales sales) throws JsonProcessingException {
		ByteArrayInputStream pdf=pdfService.createPdf(sales);
		HttpHeaders httpHeaders=new HttpHeaders();
		httpHeaders.add("Content-Disposition","inline;file=lcwd.pdf");
		return ResponseEntity.ok().headers(httpHeaders).contentType(MediaType.APPLICATION_PDF)
					.body(new InputStreamResource(pdf));
	}
}
