
package ru.athena.ws.edss;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ru.athena.ws.edss package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _DEVReestrDeleteResponse_QNAME = new QName("http://www.athena.ru/ws/edss", "DEVReestrDeleteResponse");
    private final static QName _DEVReestrInsert_QNAME = new QName("http://www.athena.ru/ws/edss", "DEVReestrInsert");
    private final static QName _DEVReestrInsertResponse_QNAME = new QName("http://www.athena.ru/ws/edss", "DEVReestrInsertResponse");
    private final static QName _DEVReestrDelete_QNAME = new QName("http://www.athena.ru/ws/edss", "DEVReestrDelete");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ru.athena.ws.edss
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DEVReestrDeleteResponse }
     * 
     */
    public DEVReestrDeleteResponse createDEVReestrDeleteResponse() {
        return new DEVReestrDeleteResponse();
    }

    /**
     * Create an instance of {@link DEVReestrInsert }
     * 
     */
    public DEVReestrInsert createDEVReestrInsert() {
        return new DEVReestrInsert();
    }

    /**
     * Create an instance of {@link DEVReestrInsertResponse }
     * 
     */
    public DEVReestrInsertResponse createDEVReestrInsertResponse() {
        return new DEVReestrInsertResponse();
    }

    /**
     * Create an instance of {@link DEVReestrDelete }
     * 
     */
    public DEVReestrDelete createDEVReestrDelete() {
        return new DEVReestrDelete();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DEVReestrDeleteResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.athena.ru/ws/edss", name = "DEVReestrDeleteResponse")
    public JAXBElement<DEVReestrDeleteResponse> createDEVReestrDeleteResponse(DEVReestrDeleteResponse value) {
        return new JAXBElement<DEVReestrDeleteResponse>(_DEVReestrDeleteResponse_QNAME, DEVReestrDeleteResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DEVReestrInsert }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.athena.ru/ws/edss", name = "DEVReestrInsert")
    public JAXBElement<DEVReestrInsert> createDEVReestrInsert(DEVReestrInsert value) {
        return new JAXBElement<DEVReestrInsert>(_DEVReestrInsert_QNAME, DEVReestrInsert.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DEVReestrInsertResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.athena.ru/ws/edss", name = "DEVReestrInsertResponse")
    public JAXBElement<DEVReestrInsertResponse> createDEVReestrInsertResponse(DEVReestrInsertResponse value) {
        return new JAXBElement<DEVReestrInsertResponse>(_DEVReestrInsertResponse_QNAME, DEVReestrInsertResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DEVReestrDelete }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.athena.ru/ws/edss", name = "DEVReestrDelete")
    public JAXBElement<DEVReestrDelete> createDEVReestrDelete(DEVReestrDelete value) {
        return new JAXBElement<DEVReestrDelete>(_DEVReestrDelete_QNAME, DEVReestrDelete.class, null, value);
    }

}
