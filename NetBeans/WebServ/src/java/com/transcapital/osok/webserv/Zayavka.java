/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.transcapital.osok.webserv;

/**
 *
 * @author panfilov_ms
 */
public class Zayavka {
    
	private String family;
	private String name;
	private String patr;
        private String birthday;
	private String phone;
	private String email;
        private String summ;
        private String period;
        private String nationality;
        private String ror;
        private String roe;
        private String form;
        private String seniority;
        private String seniority_all;
        private String traffic_source;
        private String product;
        private String existsfixphone;
        private String work;
        private String formwork;
        private String form2;
    
    public Zayavka(){
    };     
        
    public Zayavka(String family, String name, String patr, String birthday, String phone, String email, String summ, String period, String nationality, String ror, String roe, String form, String seniority, String seniority_all, String traffic_source, String product, String existsfixphone, String work, String formwork, String form2) {
        this.family = family;
        this.name = name;
        this.patr = patr;
        this.birthday = birthday;
        this.phone = phone;
        this.email = email;
        this.summ = summ;
        this.period = period;
        this.nationality = nationality;
        this.ror = ror;
        this.roe = roe;
        this.form = form;
        this.seniority = seniority;
        this.seniority_all = seniority_all;
        this.traffic_source = traffic_source;
        this.product = product;
        this.existsfixphone = existsfixphone;
        this.work = work;
        this.formwork = formwork;
        this.form2 = form2;
    }

    public String getEmail() {
        return email;
    }

    public String getFamily() {
        return family;
    }

    public String getName() {
        return name;
    }

    public String getPatr() {
        return patr;
    }

    public String getPhone() {
        return phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPatr(String patr) {
        this.patr = patr;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getRoe() {
        return roe;
    }

    public void setRoe(String roe) {
        this.roe = roe;
    }

    public String getRor() {
        return ror;
    }

    public void setRor(String ror) {
        this.ror = ror;
    }

    public String getSumm() {
        return summ;
    }

    public void setSumm(String summ) {
        this.summ = summ;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getSeniority() {
        return seniority;
    }

    public void setSeniority(String seniority) {
        this.seniority = seniority;
    }

    public String getSeniority_all() {
        return seniority_all;
    }

    public void setSeniority_all(String seniority_all) {
        this.seniority_all = seniority_all;
    }

    public String getTraffic_source() {
        return traffic_source;
    }

    public void setTraffic_source(String traffic_source) {
        this.traffic_source = traffic_source;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getExistsfixphone() {
        return existsfixphone;
    }

    public void setExistsfixphone(String existsfixphone) {
        this.existsfixphone = existsfixphone;
    }

    public String getForm2() {
        return form2;
    }

    public void setForm2(String form2) {
        this.form2 = form2;
    }

    public String getFormwork() {
        return formwork;
    }

    public void setFormwork(String formwork) {
        this.formwork = formwork;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }
    
    
    
            
}
