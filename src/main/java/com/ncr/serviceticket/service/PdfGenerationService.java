package com.ncr.serviceticket.service;

import com.itextpdf.text.DocumentException;
import com.ncr.serviceticket.dto.MasterTicketDto;

import java.io.IOException;

public interface PdfGenerationService {

    void generatePdf(MasterTicketDto masterTicketDto) throws IOException, DocumentException;
}
