/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.transcapital.osok.webserv;

import java.io.IOException;
import java.io.StringReader;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.*;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author panfilov_ms
 */
@WebService(serviceName = "NewWebService")
public class NewWebService {

    /**
     * Операция веб-службы
     */
    @WebMethod(operationName = "loadZayavka")
    public String loadZayavka(@WebParam(name = "data") String data/*, @WebParam(name = "connectionstring") String connectionstring*/) {
        
        String connectionstring = null;
        
        int count = 0; 
        int vcount = 0;
        String vlog = "";
        try {
            
            count = 0;
            vcount = 0;
            StringReader sr = new StringReader(data);
            InputSource is = new InputSource(sr);
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
            Element rootElement = doc.getDocumentElement();
            NodeList zayavkasNodeList = rootElement.getElementsByTagName("Zayavka");
            
            for (int i = 0; i < zayavkasNodeList.getLength(); i++) {
            Element zayavkaElement = (Element) zayavkasNodeList.item(i);
            int valid_status = 0;

            Zayavka z = new Zayavka();
            Element tag = getFirstElementByTagName(zayavkaElement, "summ");
            if (tag != null) {z.setSumm(getNodeText(tag));}
            
            tag = getFirstElementByTagName(zayavkaElement, "period");
            if (tag != null) {z.setPeriod(getNodeText(tag));}
            
            tag = getFirstElementByTagName(zayavkaElement, "family");
            if (tag != null) {z.setFamily(getNodeText(tag));}
            
            tag = getFirstElementByTagName(zayavkaElement, "name");
            if (tag != null) {z.setName(getNodeText(tag));}
            
            tag = getFirstElementByTagName(zayavkaElement, "patr");
            if (tag != null) {z.setPatr(getNodeText(tag));}
            
            tag = getFirstElementByTagName(zayavkaElement, "birthday");
            if (tag != null) {z.setBirthday(getNodeText(tag));}
            
            tag = getFirstElementByTagName(zayavkaElement, "phone");
            if (tag != null) {z.setPhone(getNodeText(tag));}
            
            tag = getFirstElementByTagName(zayavkaElement, "mail");
            if (tag != null) {z.setEmail(getNodeText(tag));}
            
            tag = getFirstElementByTagName(zayavkaElement, "nationality");
            if (tag != null) {z.setNationality(getNodeText(tag));}
            
            tag = getFirstElementByTagName(zayavkaElement, "region1");
            if (tag != null) {z.setRor(getNodeText(tag));}
            
            tag = getFirstElementByTagName(zayavkaElement, "region2");
            if (tag != null) {z.setRoe(getNodeText(tag));}
            
            tag = getFirstElementByTagName(zayavkaElement, "form");
            if (tag != null) {z.setForm(getNodeText(tag));}
            
            tag = getFirstElementByTagName(zayavkaElement, "seniority");
            if (tag != null) {z.setSeniority(getNodeText(tag));}
            
            tag = getFirstElementByTagName(zayavkaElement, "seniority_all");
            if (tag != null) {z.setSeniority_all(getNodeText(tag));}
            
            tag = getFirstElementByTagName(zayavkaElement, "traffic_source");
            if (tag != null) {z.setTraffic_source(getNodeText(tag));}
            
            tag = getFirstElementByTagName(zayavkaElement, "product");
            if (tag != null) {z.setProduct(getNodeText(tag));}
            
            tag = getFirstElementByTagName(zayavkaElement, "existsfixphone");
            if (tag != null) {z.setExistsfixphone(getNodeText(tag));}
            
            tag = getFirstElementByTagName(zayavkaElement, "work");
            if (tag != null) {z.setWork(getNodeText(tag));}
            
            tag = getFirstElementByTagName(zayavkaElement, "formwork");
            if (tag != null) {z.setFormwork(getNodeText(tag));}
            
            tag = getFirstElementByTagName(zayavkaElement, "form2");
            if (tag != null) {z.setForm2(getNodeText(tag));}
            
            Vzayavka vz = new Vzayavka();
            
            // проверка заявки
//------------------------------------------------------------------------------    
            
            // проверка источника траффика
            if (convertTrafficSource(z) != null) 
            {vz.setVtraffic_source(convertTrafficSource(z));}
            else
            {
                vlog = vlog + " В заявке на сумму " + z.getSumm() + " от " + z.getFamily() + " " + z.getName() + " источник траффика не входит в заданный список!\n";
                valid_status = 1;   
            }
            
            // проверка продукта
            if (convertProduct(z) != null) 
            {vz.setVproduct(convertProduct(z));}
            else
            {
                vlog = vlog + " В заявке на сумму " + z.getSumm() + " от " + z.getFamily() + " " + z.getName() + " продукт не входит в заданный список!\n";
                valid_status = 1;   
            }
            
            // проверка суммы
            try {   
            vz.setVsumm(new Double(z.getSumm().replaceAll(" ","")));
            
            if (vz.getVproduct() == 22387) // экспресс
                {
                    if (vz.getVsumm() < 50000.00 || vz.getVsumm() > 200000.00) { 
                        vlog = vlog + " В заявке на сумму " + z.getSumm() + " от " + z.getFamily() + " " + z.getName() + " сумма должна быть от 50000 до 200000 руб (согласно продукту ЭКСПРЕСС)!\n";
                        valid_status = 1; 
                    }
                }
            else if (vz.getVproduct() == 23058) // потреб
                {
                    if (vz.getVsumm() < 200000.00 || vz.getVsumm() > 1000000.00) {
                        vlog = vlog + " В заявке на сумму " + z.getSumm() + " от " + z.getFamily() + " " + z.getName() + " сумма должна быть от 200000 до 1000000 руб (согласно продукту ПОТРЕБ)!\n";
                        valid_status = 1; 
                    } 
                }
            else
                {}
            } catch (Exception ex) {
                vlog = vlog + " В заявке на сумму " + z.getSumm() + " от " + z.getFamily() + " " + z.getName() + " неправильный формат суммы!\n";
                valid_status = 1;
            }
            
            // проверка срока
            try {   
            vz.setVperiod(new Short(z.getPeriod()));
            
            if (vz.getVperiod() >= 6 && vz.getVperiod() <= 60) 
            {}
            else
            {
                vlog = vlog + " В заявке на сумму " + z.getSumm() + " от " + z.getFamily() + " " + z.getName() + " период должен быть в интервале от 6 до 60 мес!\n";
                valid_status = 1;    
            }
                        
            } catch (Exception ex) {
                vlog = vlog + " В заявке на сумму " + z.getSumm() + " от " + z.getFamily() + " " + z.getName() + " неправильный формат срока!\n";
                valid_status = 1;
            }
            
            // проверка даты рождения
            try { 
            vz.setVbirthday(new Date(new Integer(z.getBirthday().substring(6,10)) - 1900,new Integer(z.getBirthday().substring(3,5)) - 1,new Integer(z.getBirthday().substring(0,2))));    
                
            Calendar c1 = Calendar.getInstance();
            Calendar c2 = Calendar.getInstance();
            c1.setTime((new Date(System.currentTimeMillis())));
            c1.add(Calendar.YEAR, -21);
            c2.setTime(vz.getVbirthday());
            if (c2.before(c1)) 
            {}
            else
            {
                vlog = vlog + " В заявке на сумму " + z.getSumm() + " от " + z.getFamily() + " " + z.getName() + " клиент моложе 21 года на текущую дату! СТОП-ФАКТОР!\n";
                valid_status = 1;    
            }    
                
            } catch (Exception ex) {
                vlog = vlog + " В заявке на сумму " + z.getSumm() + " от " + z.getFamily() + " " + z.getName() + " неправильный формат даты рождения!\n";
                valid_status = 1;
            }
            
            // проверка гражданства
            if ("РФ".equals(z.getNationality())) 
            {}
            else
            {
                vlog = vlog + " В заявке на сумму " + z.getSumm() + " от " + z.getFamily() + " " + z.getName() + " гражданство клиента не РФ! СТОП-ФАКТОР!\n";
                valid_status = 1;   
            }
            
            // проверка региона регистрации
            if (convertRegion(z,1) != null) 
            {vz.setVregion1(convertRegion(z,1));}
            else
            {
                vlog = vlog + " В заявке на сумму " + z.getSumm() + " от " + z.getFamily() + " " + z.getName() + " регион регистрации клиента не входит в список регионов присутствия Банка! СТОП-ФАКТОР!\n";
                valid_status = 1;   
            }
            // проверка региона трудоустройства
            if (convertRegion(z,2) != null) 
            {vz.setVregion2(convertRegion(z,2));}
            else
            {
                vlog = vlog + " В заявке на сумму " + z.getSumm() + " от " + z.getFamily() + " " + z.getName() + " регион трудоустройства клиента не входит в список допустимых регионов! СТОП-ФАКТОР!\n";
                valid_status = 1;   
            }
            
            // проверка имени
            // проверка фамилии
            // проверка отчества
            // проверка e-mail
            
            // проверка мобильного телефона
            if (z.getPhone() != null) 
            {}
            else
            {
                vlog = vlog + " В заявке на сумму " + z.getSumm() + " от " + z.getFamily() + " " + z.getName() + " не указан мобильный телефон! СТОП-ФАКТОР!\n";
                valid_status = 1;   
            }
            
            // проверка наличия стационарного рабочего телефона
            if ("ДА".equals(z.getExistsfixphone()))
            {}
            else
            {
                vlog = vlog + " В заявке на сумму " + z.getSumm() + " от " + z.getFamily() + " " + z.getName() + " нет наличия стационарного телефона! СТОП-ФАКТОР!\n";
                valid_status = 1;   
            }
            
            
            
            if (vz.getVproduct() == 22387) // экспресс
            {
            // проверка стажа на текущем месте работы
            if ("3 и более месяцев".equals(z.getSeniority())) 
            {}
            else
            {
                vlog = vlog + " В заявке на сумму " + z.getSumm() + " от " + z.getFamily() + " " + z.getName() + " стаж на текущем месте работы " + z.getSeniority() + "! СТОП-ФАКТОР!\n";
                valid_status = 1;   
            }
            // проверка общего трудового стажа
            if ("12 и более месяцев".equals(z.getSeniority_all())) 
            {}
            else
            {
                vlog = vlog + " В заявке на сумму " + z.getSumm() + " от " + z.getFamily() + " " + z.getName() + " общий трудовой стаж " + z.getSeniority_all() + "! СТОП-ФАКТОР!\n";
                valid_status = 1;   
            }
            }
            else if (vz.getVproduct() == 23058) // потреб
            {   
                // проверка работы
                if ("По найму".equals(z.getWork()))
                    {vz.setVwork(17300);
                    // проверка формы подтверждения ежемесячного дохода
                    if ("Справка по форме 2НДФЛ".equals(z.getForm())) 
                    {vz.setVform(17281);}
                    else if ("Справка по форме Банка".equals(z.getForm()))
                    {vz.setVform(17282);}
                    else
                    {
                        vlog = vlog + " В заявке на сумму " + z.getSumm() + " от " + z.getFamily() + " " + z.getName() + " некорректная форма подтверждения ежемесячного дохода! СТОП-ФАКТОР!\n";
                        valid_status = 1;   
                    } 
                    // проверка стажа на текущем месте работы
                    if ("3 и более месяцев".equals(z.getSeniority())) 
                    {}
                    else
                    {
                        vlog = vlog + " В заявке на сумму " + z.getSumm() + " от " + z.getFamily() + " " + z.getName() + " стаж на текущем месте работы " + z.getSeniority() + "! СТОП-ФАКТОР!\n";
                        valid_status = 1;   
                    }
                    // проверка общего трудового стажа
                    if ("12 и более месяцев".equals(z.getSeniority_all())) 
                    {}
                    else
                    {
                        vlog = vlog + " В заявке на сумму " + z.getSumm() + " от " + z.getFamily() + " " + z.getName() + " общий трудовой стаж " + z.getSeniority_all() + "! СТОП-ФАКТОР!\n";
                        valid_status = 1;   
                    }
                    // проверка формы подтверждения трудоустройства
                    if ("Копия трудовой книжки (заверенной работодателем)".equals(z.getFormwork())) 
                    {vz.setVformwork(17400);}
                    else if ("Копия трудового контракта/договора (заверенные работодателем)".equals(z.getFormwork()))
                    {vz.setVformwork(17401);}
                    else
                    {
                        vlog = vlog + " В заявке на сумму " + z.getSumm() + " от " + z.getFamily() + " " + z.getName() + " некорректная форма подтверждения трудоустройства! СТОП-ФАКТОР!\n";
                        valid_status = 1;   
                    }
                    }
                else if ("Индивидуальный предприниматель, нотариус, адвокат".equals(z.getWork()))
                    {vz.setVwork(17301);
                    // проверка формы подтверждения дохода
                    if ("Налоговая декларация за предыдущий календарный год и истекшие кварталы текущего года (при наличии)".equals(z.getForm2()))
                        {vz.setVform2(17500);
                        }
                    else
                    {
                        vlog = vlog + " В заявке на сумму " + z.getSumm() + " от " + z.getFamily() + " " + z.getName() + " некорректная форма подтверждения дохода! СТОП-ФАКТОР!\n";
                        valid_status = 1;   
                    }
                    }
                else
                    {
                        vlog = vlog + " В заявке на сумму " + z.getSumm() + " от " + z.getFamily() + " " + z.getName() + " некорректный вид работы! СТОП-ФАКТОР!\n";
                        valid_status = 1;   
                    }   
            }
           else
            {}
            
         
            
//------------------------------------------------------------------------------             
            
            
            if (valid_status == 0) {
                vcount++;
                ConnectToOracleDB connectToOracleDB = new ConnectToOracleDB(z, vz, connectionstring);                        // сохраняем в базу, если прошло проверку
            } 
            else
            {    
                ConnectToOracleDB connectToOracleDB = new ConnectToOracleDB(z, null, connectionstring);                      // сохраняем в базу, если не прошло проверку
            }
            count++;
            
            }
                    
            return "Количество заявок для разбора: " + count + "\n" + vlog + "Количество загруженных заявок: " + vcount;
            
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(NewWebService.class.getName()).log(Level.SEVERE, null, ex);
            return "Ошибка разбора XML! Количество добавленных заявок: " + count + " Message: " + ex.getMessage();
        } catch (SAXException ex) {
            Logger.getLogger(NewWebService.class.getName()).log(Level.SEVERE, null, ex);
            return "Ошибка XML! Количество добавленных заявок: " + count + " Message: " + ex.getMessage();
        } catch (IOException ex) {
            Logger.getLogger(NewWebService.class.getName()).log(Level.SEVERE, null, ex);
            return "Ошибка ввода-вывода! Количество добавленных заявок: " + count + " Message: " + ex.getMessage();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(NewWebService.class.getName()).log(Level.SEVERE, null, ex);
            return "Ошибка поиска класса! Количество добавленных заявок: " + count + " Message: " + ex.getMessage();
	} catch (SQLException ex) {
            Logger.getLogger(NewWebService.class.getName()).log(Level.SEVERE, null, ex);
            return "Ошибка SQL! Количество добавленных заявок: " + count + " Message: " + ex.getMessage();
        } catch (NullPointerException ex) {
            Logger.getLogger(NewWebService.class.getName()).log(Level.SEVERE, null, ex);
            return "Ошибка данных!" + count + " Message: " + ex.getMessage();
        }
    }
    
  private Element getFirstElementByTagName(Element element, String tagName) {
    Element e = null;

    NodeList nl = element.getElementsByTagName(tagName);
    e = (Element) nl.item(0);

    return e;
  }

  private String getNodeText(Node node) {
    NodeList list = node.getChildNodes();
    for (int i = 0; i < list.getLength(); i++) {
      Node child = list.item(i);
      if (child.getNodeType() == Node.CDATA_SECTION_NODE) {
        CDATASection section = (CDATASection) child;
        return section.getData();
      } else if (child.getNodeType() == Node.TEXT_NODE) {
        return child.getNodeValue();
      }
    }
    return "";
  }
  
  private Integer convertRegion(Zayavka z, int n) {
      String fild = null;
      if (n==1) {fild = z.getRor();} else if (n==2) {fild = z.getRoe();}
      if ("Москва и Московская обл.".equals(fild))  {
          return 17152;
      }
      else if ("С.Петербург и Ленинград.обл.".equals(fild))  {
          return 17153;
      }
      else if ("Алтайский край".equals(fild))  {
          return 17207;
      }
      else if ("Белгородская обл.".equals(fild))  {
          return 17208;
      }
      else if ("Брянская обл.".equals(fild))  {
          return 17209;
      }
      else if ("Воронежская обл.".equals(fild))  {
          return 17210;
      }
      else if ("Свердловская обл.".equals(fild))  {
          return 17211;
      }
      else if ("Калининградская обл.".equals(fild))  {
          return 17212;
      }
      else if ("Калужская обл.".equals(fild))  {
          return 17213;
      }
      else if ("Краснодарский край".equals(fild))  {
          return 17214;
      }
      else if ("Нижегородская обл.".equals(fild))  {
          return 17215;
      }
      else if ("Новосибирская обл.".equals(fild))  {
          return 17216;
      }
      else if ("Пермский край".equals(fild))  {
          return 17217;
      }
      else if ("Республика Коми".equals(fild))  {
          return 17218;
      }
      else if ("Ростовская обл.".equals(fild))  {
          return 17219;
      }
      else if ("Рязанская обл.".equals(fild))  {
          return 17220;
      }
      else if ("Самарская обл.".equals(fild))  {
          return 17221;
      }
      else if ("Тюменская обл.".equals(fild))  {
          return 17222;
      }
      else if ("Ярославская обл.".equals(fild))  {
          return 17223;
      }
      else {
          return null;
      }
  }
  
    private Integer convertRegion2(Zayavka z, int n) {
      String fild = null;
      if (n==1) {fild = z.getRor();} else if (n==2) {fild = z.getRoe();}
      if ("Москва и Московская обл.".equals(fild))  {
          return 17152;
      }
      else if ("Калужская обл.".equals(fild))  {
          return 17213;
      }
      else if ("Рязанская обл.".equals(fild))  {
          return 17220;
      }
      else if ("Ярославская обл.".equals(fild))  {
          return 17223;
      }
      else {
          return null;
      }
  }
    
    private String convertTrafficSource(Zayavka z) {
      String fild = null;
      fild = z.getTraffic_source();
      if ("banki.ru".equals(fild))  {
          return "banki.ru";
      }
      else if ("banki.partners".equals(fild))  {
          return "banki.partners";
      }
      else {
          return null;
      }
  }
        
    private Integer convertProduct(Zayavka z) {
      String fild = null;
      fild = z.getProduct();
      if ("Потребительский кредит".equals(fild))  {
          return 23058;
      }
      else if ("Экспресс-кредит".equals(fild))  {
          return 22387;
      }
      else {
          return null;
      }
  }

    
}