package com.ncr.serviceticket.controller;

import com.itextpdf.text.DocumentException;
import com.ncr.serviceticket.dto.MasterTicketDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@RequestMapping("/pdf")
public interface PdfGenerationController {

    @PostMapping("/export")
    ResponseEntity<byte[]> export(@RequestBody MasterTicketDto masterTicketDto) throws DocumentException, IOException;
}
