
package com.transcapital.dit.osok.archivepay;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.6b20 
 * Generated source version: 2.2
 * 
 */
@WebService(name = "EdssPortType", targetNamespace = "http://www.athena.ru/ws/edss")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface EdssPortType {


    /**
     * 
     * @param sign
     * @param content
     * @param devSource
     * @param devType
     * @param docLabel
     * @param sysFilial
     * @param fileName
     * @param docID
     * @param user
     * @param docOperDate
     * @return
     *     returns java.lang.String
     * @throws EdssFault
     */
    @WebMethod(operationName = "DEVReestrInsert", action = "http://www.athena.ru/ws/edss/DEVReestrInsert")
    @WebResult(name = "ErrorMessage", targetNamespace = "")
    @RequestWrapper(localName = "DEVReestrInsert", targetNamespace = "http://www.athena.ru/ws/edss", className = "com.transcapital.dit.osok.archivepay.DEVReestrInsert")
    @ResponseWrapper(localName = "DEVReestrInsertResponse", targetNamespace = "http://www.athena.ru/ws/edss", className = "com.transcapital.dit.osok.archivepay.DEVReestrInsertResponse")
    public String devReestrInsert(
        @WebParam(name = "DevType", targetNamespace = "")
        String devType,
        @WebParam(name = "DevSource", targetNamespace = "")
        String devSource,
        @WebParam(name = "FileName", targetNamespace = "")
        String fileName,
        @WebParam(name = "Content", targetNamespace = "")
        String content,
        @WebParam(name = "DocID", targetNamespace = "")
        long docID,
        @WebParam(name = "DocLabel", targetNamespace = "")
        String docLabel,
        @WebParam(name = "DocOperDate", targetNamespace = "")
        XMLGregorianCalendar docOperDate,
        @WebParam(name = "User", targetNamespace = "")
        String user,
        @WebParam(name = "SysFilial", targetNamespace = "")
        double sysFilial,
        @WebParam(name = "Sign", targetNamespace = "")
        String sign)
        throws EdssFault
    ;

    /**
     * 
     * @param docID
     * @return
     *     returns java.lang.String
     * @throws EdssFault
     */
    @WebMethod(operationName = "DEVReestrDelete", action = "http://www.athena.ru/ws/edss/DEVReestrDelete")
    @WebResult(name = "ErrorMessage", targetNamespace = "")
    @RequestWrapper(localName = "DEVReestrDelete", targetNamespace = "http://www.athena.ru/ws/edss", className = "com.transcapital.dit.osok.archivepay.DEVReestrDelete")
    @ResponseWrapper(localName = "DEVReestrDeleteResponse", targetNamespace = "http://www.athena.ru/ws/edss", className = "com.transcapital.dit.osok.archivepay.DEVReestrDeleteResponse")
    public String devReestrDelete(
        @WebParam(name = "DocID", targetNamespace = "")
        long docID)
        throws EdssFault
    ;

}
