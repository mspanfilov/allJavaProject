/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.transcapital.osok.webserv;

import java.sql.Date;

/**
 *
 * @author panfilov_ms
 */
public class Vzayavka {
    
    private Double  vsumm;
    private Short   vperiod;
    private Date    vbirthday;
    private Integer vregion1;
    private Integer vregion2;
    private Integer vform;
    private String vtraffic_source;
    private Integer vproduct;
    private Integer vwork;
    private Integer vformwork;
    private Integer vform2;

    public Vzayavka() {
    }

    public Vzayavka(Double vsumm, Short vperiod, Date vbirthday, Integer vregion1, Integer vregion2, Integer vform, String vtraffic_source, Integer vproduct, Integer vwork, Integer vformwork, Integer vform2) {
        this.vsumm = vsumm;
        this.vperiod = vperiod;
        this.vbirthday = vbirthday;
        this.vregion1 = vregion1;
        this.vregion2 = vregion2;
        this.vform = vform;
        this.vtraffic_source = vtraffic_source;
        this.vproduct = vproduct;
        this.vwork = vwork;
        this.vformwork = vformwork;
        this.vform2 = vform2;
    }

    public Date getVbirthday() {
        return vbirthday;
    }

    public void setVbirthday(Date vbirthday) {
        this.vbirthday = vbirthday;
    }

    public Short getVperiod() {
        return vperiod;
    }

    public void setVperiod(Short vperiod) {
        this.vperiod = vperiod;
    }

    public Double getVsumm() {
        return vsumm;
    }

    public void setVsumm(Double vsumm) {
        this.vsumm = vsumm;
    }

    public Integer getVform() {
        return vform;
    }

    public void setVform(Integer vform) {
        this.vform = vform;
    }

    public Integer getVregion1() {
        return vregion1;
    }

    public void setVregion1(Integer vregion1) {
        this.vregion1 = vregion1;
    }

    public Integer getVregion2() {
        return vregion2;
    }

    public void setVregion2(Integer vregion2) {
        this.vregion2 = vregion2;
    }

    public String getVtraffic_source() {
        return vtraffic_source;
    }

    public void setVtraffic_source(String vtraffic_source) {
        this.vtraffic_source = vtraffic_source;
    }

    public Integer getVproduct() {
        return vproduct;
    }

    public void setVproduct(Integer vproduct) {
        this.vproduct = vproduct;
    }

    public Integer getVform2() {
        return vform2;
    }

    public void setVform2(Integer vform2) {
        this.vform2 = vform2;
    }

    public Integer getVformwork() {
        return vformwork;
    }

    public void setVformwork(Integer vformwork) {
        this.vformwork = vformwork;
    }

    public Integer getVwork() {
        return vwork;
    }

    public void setVwork(Integer vwork) {
        this.vwork = vwork;
    }
    
    
    
    
}
