package com.ncr.serviceticket.controller.impl;


import com.itextpdf.text.DocumentException;
import com.ncr.serviceticket.dto.MasterTicketDto;
import com.ncr.serviceticket.service.PdfGenerationService;
import com.ncr.serviceticket.controller.PdfGenerationController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class PdfGenerationControllerImpl implements PdfGenerationController {

    private final PdfGenerationService pdfGenerationService;

    public PdfGenerationControllerImpl(PdfGenerationService pdfGenerationService) {
        this.pdfGenerationService = pdfGenerationService;
    }

    @Override
    public ResponseEntity<Void> export(MasterTicketDto masterTicketDto) throws DocumentException, IOException {
        pdfGenerationService.generatePdf(masterTicketDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
