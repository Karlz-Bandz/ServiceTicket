package com.ncr.serviceticket.controller.impl;

import com.itextpdf.text.DocumentException;
import com.ncr.serviceticket.dto.MasterTicketDto;
import com.ncr.serviceticket.service.PdfGenerationService;
import com.ncr.serviceticket.controller.PdfGenerationController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class PdfGenerationControllerImpl implements PdfGenerationController {

    private final PdfGenerationService pdfGenerationService;

    @Override
    public ResponseEntity<byte[]> export(MasterTicketDto masterTicketDto) throws DocumentException, IOException {
        return new ResponseEntity<>(pdfGenerationService.generatePdf(masterTicketDto), HttpStatus.CREATED);
    }
}
