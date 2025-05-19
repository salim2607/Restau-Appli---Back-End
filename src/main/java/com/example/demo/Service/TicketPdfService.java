package com.example.demo.Service;

import com.example.demo.entity.Commande;
import com.example.demo.entity.LigneCommande;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;



    @Service
    public class TicketPdfService {

        public byte[] generateTicketPdf(String ticketText) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter writer = new PdfWriter(baos);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            document.add(new Paragraph(ticketText));
            document.close();
            return baos.toByteArray();
        }
}
