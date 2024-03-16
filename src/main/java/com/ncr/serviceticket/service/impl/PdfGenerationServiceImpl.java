package com.ncr.serviceticket.service.impl;

import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.FontFactory;
import com.ncr.serviceticket.dto.MasterTicketDto;
import com.ncr.serviceticket.model.Atm;
import com.ncr.serviceticket.model.Operator;
import com.ncr.serviceticket.service.AtmService;
import com.ncr.serviceticket.service.OperatorService;
import com.ncr.serviceticket.service.PdfGenerationService;
import org.springframework.stereotype.Service;

import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.File;
import java.nio.file.Files;

@Service
public class PdfGenerationServiceImpl implements PdfGenerationService {

    private final AtmService atmService;

    private final OperatorService operatorService;

    public PdfGenerationServiceImpl(AtmService atmService, OperatorService operatorService) {
        this.atmService = atmService;
        this.operatorService = operatorService;
    }

    @Override
    public byte[] generatePdf(MasterTicketDto masterTicketDto) throws IOException, DocumentException {

        Atm atm = atmService.findAtmById(masterTicketDto.getAtmId());
        Operator operator = operatorService.findById(masterTicketDto.getOperatorId());

        final String fontPath = "/fonts/OpenSans_Condensed-Light.ttf";
        final String fontEncoding = "Cp1250";
        String home = System.getProperty("user.home");
        File file = new File(home + "\\Downloads\\Zlecenie serwisowe " + atm.getAtmId() +".pdf");

        try {
            Document document = new Document(PageSize.A4);
            OutputStream outputStream = new FileOutputStream(file);
            PdfWriter.getInstance(document, outputStream);

            document.open();

            Font fontTitle = FontFactory.getFont(fontPath, fontEncoding, true);
            fontTitle.setSize(26);
            Paragraph paragraphTitle = new Paragraph("Formularz zgłoszenia awarii " + atm.getAtmId(), fontTitle);
            paragraphTitle.setSpacingAfter(30);
            paragraphTitle.setAlignment(Element.ALIGN_CENTER);

            Font fontSubTitle = FontFactory.getFont(fontPath, fontEncoding, true);
            fontSubTitle.setSize(15);
            fontSubTitle.isBold();
            Paragraph paragraphSubTitle1 = new Paragraph("Informacje o jednostce organizacyjnej, zgłaszającej uszkodzenie.", fontSubTitle);
            Paragraph paragraphSubTitle2 = new Paragraph("Informacje o zgłaszającym uszkodzenie.", fontSubTitle);
            Paragraph paragraphSubTitle3 = new Paragraph("Informacje o uszkodzonym produkcie.", fontSubTitle);

            Paragraph paragraphSubTitle4 = new Paragraph("Od Klienta:", fontSubTitle);
            Paragraph paragraphSubTitle5 = new Paragraph("Od WMC:", fontSubTitle);
            Paragraph clientDescription = new Paragraph(masterTicketDto.getClientDescription(), fontSubTitle);
            clientDescription.setSpacingAfter(25);
            Paragraph operatorDescription = new Paragraph(masterTicketDto.getOperatorDescription(), fontSubTitle);
            operatorDescription.setSpacingAfter(25);

            PdfPTable table1 = new PdfPTable(2);
            table1.setWidthPercentage(90);
            table1.setSpacingAfter(20);
            table1.setSpacingBefore(20);
            addRow(table1, "Nazwa jednostki organizacyjnej:", "CENTRUM ZARZĄDZANIA NCR");
            addRow(table1, "Adres:", "ul. Krakowiaków 32, 02-255 Warszawa");

            PdfPTable table2 = new PdfPTable(2);
            table2.setWidthPercentage(90);
            table2.setSpacingAfter(20);
            table2.setSpacingBefore(20);
            addRow(table2, "Nazwisko i imię:", operator.getName());
            addRow(table2, "Pełniona funkcja:", operator.getRole());
            addRow(table2, "Telefon", operator.getPhone());

            PdfPTable table3 = new PdfPTable(2);
            table3.setWidthPercentage(90);
            table3.setSpacingAfter(20);
            table3.setSpacingBefore(20);
            addRow(table3, "Klient:", atm.getClientName());
            addRow(table3, "Identyfikator:", atm.getAtmId());
            addRow(table3, "Numer seryjny:", atm.getSerialNo());
            addRow(table3, "Typ urządzenia:", atm.getType());
            addRow(table3, "Lokalizacja", atm.getLocation());
            addRow(table3, "Kontakt telefoniczny w miejscu awarii:", atm.getPhone());
            addRow(table3, "Data i godzina wystąpienia awarii:", "2022-12-23");

            document.add(paragraphTitle);
            document.add(paragraphSubTitle1);
            document.add(table1);
            document.add(paragraphSubTitle2);
            document.add(table2);
            document.add(paragraphSubTitle3);
            document.add(table3);
            document.add(paragraphSubTitle4);
            document.add(clientDescription);
            document.add(paragraphSubTitle5);
            document.add(operatorDescription);

            document.close();
        } catch (IOException e) {
            throw new IOException(e);
        }

        return Files.readAllBytes(file.toPath());
    }

    private void addRow(PdfPTable table, String key, String value) {
        Font font = FontFactory.getFont("/fonts/OpenSans_Condensed-Light.ttf", "Cp1250", true);
        font.setSize(13);
        Paragraph paragraphKey = new Paragraph(key, font);
        Paragraph paragraphValue = new Paragraph(value, font);
        table.addCell(paragraphKey);
        table.addCell(paragraphValue);
    }
}
