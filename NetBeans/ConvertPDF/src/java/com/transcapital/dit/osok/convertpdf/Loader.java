/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.transcapital.dit.osok.convertpdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfWriter;
import it.sauronsoftware.base64.Base64;
import java.io.*;
import java.sql.Timestamp;
import java.util.GregorianCalendar;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import org.apache.log4j.Logger;
import ru.athena.ws.edss.EdssPortType;

/**
 *
 * @author panfilov_ms
 */
public class Loader extends Thread {
    
    private static Logger logger = Logger.getLogger(MainServlet.class.getName());
    private String path;
    private String inname;
    private EdssPortType port;
    private volatile Thread stopMe;

    public Loader(String path, String inname, EdssPortType port) {
        super();
        this.path = path;
        this.inname = inname;
        this.port = port;
        
    }
    
    
    @Override
    public void run (){
        
        StringBuffer buffer = null;

        FileInputStream mainFile = null;
        InputStreamReader isr = null;
        Reader reader = null;
        File ff = null;
        FileOutputStream outFile = null;
        OutputStreamWriter osw = null;
        Writer out = null;
        
        String DevType;
        String DevSource;
        String FileName;
        String DocID;
        String DocLabel;
        String DocOperDate;
        String User;
        String SysFilial;
        String Sign;
        
        try{
            
            stopMe = Thread.currentThread();
            
            while (stopMe == Thread.currentThread()) {               
            File f = new File(path + inname);
            
            if (f.exists()) {
            logger.info("mainFile:  " + path + inname);
            
            buffer = new StringBuffer();
            mainFile = new FileInputStream(f);
            isr = new InputStreamReader(mainFile,"windows-1251");
            reader = new BufferedReader(isr);
            int fileNum = 0; 
            
            int ch; // the code of one character
            Timestamp FDate = new Timestamp(System.currentTimeMillis());
            
            GregorianCalendar gc = new GregorianCalendar();
            gc.setTime(FDate);
            XMLGregorianCalendar xgc = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
            
            String currTime = FDate.toString().replaceAll(":", "_");           
            logger.info("currTime: " + currTime);
            File fNext = new File(path + currTime + "/" + inname);
            new File(path + currTime).mkdir();
            
            // читаем посимвольно исходный файлs
            do {
                ch = reader.read();
                buffer.append((char)ch);
                
                // если нашли в исходном файле разделители "\u000C" или "\uFFFF", то формируем новый файл и чистим буфер
                if (buffer.toString().contains("\u000C") || buffer.toString().contains("\uFFFF")) {
                    
                    fileNum = fileNum + 1;
                    ff = new File(path + currTime + "/" + fileNum + ".txt");
                    ff.createNewFile();                    
                    logger.info("out file name:  " + path + currTime + "/" + fileNum + ".txt");
                    
                    outFile = new FileOutputStream(ff);
                    osw = new OutputStreamWriter(outFile,"windows-1251");
                    out = new BufferedWriter(osw);
                    out.write(buffer.toString().replaceAll("\uFFFF", "").replaceAll("\u000C", ""));
                    
                    // определяем параметры документа
                    
                    DevType = buffer.substring(buffer.indexOf("DevType:")+8,buffer.lastIndexOf("DevSource:")).trim();
                    DevSource = buffer.substring(buffer.indexOf("DevSource:")+10,buffer.lastIndexOf("FileName:")).trim();
                    FileName = buffer.substring(buffer.indexOf("FileName:")+9,buffer.lastIndexOf("DocID:")).trim();
                    DocID = buffer.substring(buffer.indexOf("DocID:")+6,buffer.lastIndexOf("DocLabel:")).trim();
                    DocLabel = buffer.substring(buffer.indexOf("DocLabel:")+9,buffer.lastIndexOf("DocOperDate:")).trim();
                    DocOperDate = buffer.substring(buffer.indexOf("DocOperDate:")+12,buffer.lastIndexOf("User:")).trim();
                    User = buffer.substring(buffer.indexOf("User:")+5,buffer.lastIndexOf("SysFilial:")).trim();
                    SysFilial = buffer.substring(buffer.indexOf("SysFilial:")+10,buffer.lastIndexOf("Sign:")).trim();
                    Sign = buffer.substring(buffer.indexOf("Sign:")+5,buffer.lastIndexOf("DocState:1.")).trim();
                    logger.info("DevType: " + DevType);
                    logger.info("DevSource: " + DevSource);
                    logger.info("FileName: " + FileName);
                    logger.info("DocID: " + DocID);
                    logger.info("DocLabel: " + DocLabel);
                    logger.info("DocOperDate: " + DocOperDate);
                    logger.info("User: " + User);
                    logger.info("SysFilial: " + SysFilial);
                    logger.info("Sign: " + Sign);
                    
                    // кидаем результат в веб-сервис
                    logger.info("++");
                    int idDoc = (int) (Math.random()*100000);
                    //Timestamp FDatea = new Timestamp(DocOperDate);
                    GregorianCalendar gca = new GregorianCalendar();
                    gc.set(Integer.parseInt(DocOperDate.substring(0, 4)), Integer.parseInt(DocOperDate.substring(4, 6)), Integer.parseInt(DocOperDate.substring(6, 8)));
                    XMLGregorianCalendar xgcXML = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
                    String encoded = Base64.encode(buffer.toString().replaceAll("\uFFFF", "").replaceAll("\u000C", ""));   
                    logger.info("xgcXML: " + xgcXML.toString());
                    //String res = port.devReestrInsert("IBC_PaymentOrderMadeByClientBC", "NewAthene", currTime + " " + fileNum + ".txt", encoded, 12305554, "test" + currTime, xgc, "SUPER", 1, "SUPER");
                    //String res = port.devReestrInsert(DevType, DevSource, FileName, encoded, Integer.parseInt(DocID), DocLabel, xgcXML, User, Integer.parseInt(SysFilial), Sign);
                    
                    // сформируем PDF
                    Document document = new Document(PageSize.A4, 50, 50, 50, 50);
                    PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(path + currTime + "/" + fileNum + ".pdf"));
                    document.open();
                    Anchor anchorTarget = new Anchor(/*"First page of the document."*/buffer.toString().replaceAll("\uFFFF", "").replaceAll("\u000C", ""));
                    anchorTarget.setName("BackToTop");
                    Paragraph paragraph1 = new Paragraph();
                    paragraph1.setSpacingBefore(50);
                    paragraph1.add(anchorTarget);
                    document.add(paragraph1);
                    document.add(new Paragraph("Some more text on the first page with different color and font type.", FontFactory.getFont(FontFactory.COURIER, 14, Font.BOLD,	new CMYKColor(0, 255, 0, 0))));
                    document.close();
                    
                    out.close();
                    osw.close();
                    outFile.close();
                    
                    buffer.delete(0, buffer.length());
                }
            } while (ch > -1);
            
            reader.close();
            isr.close();
            mainFile.close();
            f.renameTo(fNext);
            }
            
            sleep(30000);                                                       // ждем 30 сек...
            logger.info("ищем исходный файл ещё раз");
            }
        } catch(IOException e){
            System.out.println("Could not write or read to a txt file " + e.toString());
            logger.error("Exception! Could not write or read to a txt file " + e.getMessage());
        } catch (InterruptedException ex) {
            logger.error("Exception! " + ex.getMessage());
        } catch (Exception ex) {
            logger.error("Ex! " + ex.getMessage());
        //} catch (EdssFault ex) {
        //    logger.error("Exception EdssFault! " + ex.getMessage());                 
        } finally {
            try {
                out.close();
                osw.close();
                outFile.close();
                reader.close();
                isr.close();
                mainFile.close();
            } catch (Exception ex) {
                System.out.println("Error: " + ex.toString());
                logger.error("Exception! " + ex.getMessage());
            }
        }
        
    }
    
    public void stopMe() {
        stopMe = null;
    }
    
    
    
}
