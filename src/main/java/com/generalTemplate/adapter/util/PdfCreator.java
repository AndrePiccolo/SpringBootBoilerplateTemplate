package com.generalTemplate.adapter.util;

import com.generalTemplate.adapter.rest.pdf.entity.PdfContent;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;

@Component
public class PdfCreator {

    public ByteArrayOutputStream createPdf(PdfContent content) throws DocumentException {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, out);

        document.open();
        Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
        Chunk chunk = new Chunk(content.getMainContent(), font);
        document.add(chunk);
        document.close();

        return out;
    }
}
