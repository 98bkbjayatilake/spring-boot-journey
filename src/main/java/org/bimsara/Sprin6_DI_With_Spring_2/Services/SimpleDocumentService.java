package org.bimsara.Sprin6_DI_With_Spring_2.Services;
import  org.apache.poi.xwpf.usermodel.XWPFDocument;
import  org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
@Service
@Profile("dev")
public class SimpleDocumentService implements DocumentService {
    @Override
    public  void createDocument(String content){
        //Creating(initializing) a New Word Document
        try(XWPFDocument document=new XWPFDocument()){
           //create a new paragraph with the Word document
          XWPFParagraph paragraph=document.createParagraph();
          //A run represents a portion of text with consistent styling.This line creates a new XWPFRun in the previously created paragraph
            paragraph.createRun().setText(content);

            try(FileOutputStream out=new FileOutputStream("JavaProfileDocumentServiceExample.docx")){
                document.write(out);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
