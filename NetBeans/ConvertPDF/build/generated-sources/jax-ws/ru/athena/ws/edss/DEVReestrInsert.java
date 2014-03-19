
package ru.athena.ws.edss;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for DEVReestrInsert complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DEVReestrInsert">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DevType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DevSource" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FileName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Content" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DocID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="DocLabel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DocOperDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="User" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SysFilial" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="Sign" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DEVReestrInsert", propOrder = {
    "devType",
    "devSource",
    "fileName",
    "content",
    "docID",
    "docLabel",
    "docOperDate",
    "user",
    "sysFilial",
    "sign"
})
public class DEVReestrInsert {

    @XmlElement(name = "DevType")
    protected String devType;
    @XmlElement(name = "DevSource")
    protected String devSource;
    @XmlElement(name = "FileName")
    protected String fileName;
    @XmlElement(name = "Content")
    protected String content;
    @XmlElement(name = "DocID")
    protected long docID;
    @XmlElement(name = "DocLabel")
    protected String docLabel;
    @XmlElement(name = "DocOperDate")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar docOperDate;
    @XmlElement(name = "User")
    protected String user;
    @XmlElement(name = "SysFilial")
    protected double sysFilial;
    @XmlElement(name = "Sign")
    protected String sign;

    /**
     * Gets the value of the devType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDevType() {
        return devType;
    }

    /**
     * Sets the value of the devType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDevType(String value) {
        this.devType = value;
    }

    /**
     * Gets the value of the devSource property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDevSource() {
        return devSource;
    }

    /**
     * Sets the value of the devSource property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDevSource(String value) {
        this.devSource = value;
    }

    /**
     * Gets the value of the fileName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Sets the value of the fileName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFileName(String value) {
        this.fileName = value;
    }

    /**
     * Gets the value of the content property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets the value of the content property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContent(String value) {
        this.content = value;
    }

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

    /**
     * Gets the value of the docLabel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocLabel() {
        return docLabel;
    }

    /**
     * Sets the value of the docLabel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocLabel(String value) {
        this.docLabel = value;
    }

    /**
     * Gets the value of the docOperDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDocOperDate() {
        return docOperDate;
    }

    /**
     * Sets the value of the docOperDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDocOperDate(XMLGregorianCalendar value) {
        this.docOperDate = value;
    }

    /**
     * Gets the value of the user property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUser() {
        return user;
    }

    /**
     * Sets the value of the user property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUser(String value) {
        this.user = value;
    }

    /**
     * Gets the value of the sysFilial property.
     * 
     */
    public double getSysFilial() {
        return sysFilial;
    }

    /**
     * Sets the value of the sysFilial property.
     * 
     */
    public void setSysFilial(double value) {
        this.sysFilial = value;
    }

    /**
     * Gets the value of the sign property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSign() {
        return sign;
    }

    /**
     * Sets the value of the sign property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSign(String value) {
        this.sign = value;
    }

}
