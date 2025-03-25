package org.bimsara.Sprin6_DI_With_Spring_2.Services;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
@Service
@Profile("prod")
public class FormatDocumentService implements DocumentService {
    @Override
    public void createDocument(String content) {
     try(XWPFDocument document=new XWPFDocument()){
         XWPFParagraph paragraph=document.createParagraph();
         XWPFRun run=paragraph.createRun();
         run.setText(content);
         run.setBold(true);
         run.setCapitalized(true);
         run.setColor("FF0000");

         try(FileOutputStream out=new FileOutputStream("JavaProfileFormatDocumentServiceExample.docx")){
             document.write(out);
         }
     }catch (IOException e){
         e.printStackTrace();
     }
    }
}
