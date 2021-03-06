
package ru.athena.ws.edss;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.6b20 
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "EdssService", targetNamespace = "http://www.athena.ru/ws/edss", wsdlLocation = "http://v-ibank-gate:9080/ws/edss/EdssService/EdssService.wsdl")
public class EdssService
    extends Service
{

    private final static URL EDSSSERVICE_WSDL_LOCATION;
    private final static WebServiceException EDSSSERVICE_EXCEPTION;
    private final static QName EDSSSERVICE_QNAME = new QName("http://www.athena.ru/ws/edss", "EdssService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://v-ibank-gate:9080/ws/edss/EdssService/EdssService.wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        EDSSSERVICE_WSDL_LOCATION = url;
        EDSSSERVICE_EXCEPTION = e;
    }

    public EdssService() {
        super(__getWsdlLocation(), EDSSSERVICE_QNAME);
    }

    public EdssService(WebServiceFeature... features) {
        super(__getWsdlLocation(), EDSSSERVICE_QNAME, features);
    }

    public EdssService(URL wsdlLocation) {
        super(wsdlLocation, EDSSSERVICE_QNAME);
    }

    public EdssService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, EDSSSERVICE_QNAME, features);
    }

    public EdssService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public EdssService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns EdssPortType
     */
    @WebEndpoint(name = "EdssPort")
    public EdssPortType getEdssPort() {
        return super.getPort(new QName("http://www.athena.ru/ws/edss", "EdssPort"), EdssPortType.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns EdssPortType
     */
    @WebEndpoint(name = "EdssPort")
    public EdssPortType getEdssPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://www.athena.ru/ws/edss", "EdssPort"), EdssPortType.class, features);
    }

    private static URL __getWsdlLocation() {
        if (EDSSSERVICE_EXCEPTION!= null) {
            throw EDSSSERVICE_EXCEPTION;
        }
        return EDSSSERVICE_WSDL_LOCATION;
    }

}
