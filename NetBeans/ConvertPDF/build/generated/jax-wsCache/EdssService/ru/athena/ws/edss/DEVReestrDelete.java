
package ru.athena.ws.edss;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DEVReestrDelete complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DEVReestrDelete">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DocID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DEVReestrDelete", propOrder = {
    "docID"
})
public class DEVReestrDelete {

    @XmlElement(name = "DocID")
    protected long docID;

    /**
     * Gets the value of the docID property.
     * 
     */
    public long getDocID() {
        return docID;
    }

    /**
     * Sets the value of the docID property.
     * 
     */
    public void setDocID(long value) {
        this.docID = value;
    }

}
