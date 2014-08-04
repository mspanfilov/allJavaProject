/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.transcapital.dit.osok.cpp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import javax.net.ssl.*;
import org.apache.log4j.Logger;

/**
 *
 * @author panfilov_ms
 */
public class CyberplatConnector {
    
        private static Logger logger = Logger.getLogger(CyberplatConnector.class.getName());
        String nextLine;
        URL url = null;
        URLConnection urlConn = null;
        InputStreamReader inStream = null;
        BufferedReader buff = null;
        
        public boolean doRead(String prov_id) {
            
            logger.info("start doRead");
            boolean value = false;
            try{
            logger.info("start read prov_id: " + prov_id);
            url = new URL("https://service2.cyberplat.ru/cgi-bin/view_stat.utf/help.cgi?prov_id=" + prov_id);
            
            
            TrustManager[] trustAllCerts = new TrustManager[] {
                new X509TrustManager() {
                    @Override
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }
                    @Override
                    public void checkClientTrusted( java.security.cert.X509Certificate[] certs, String authType) {
                    }
                    @Override
                    public void checkServerTrusted( java.security.cert.X509Certificate[] certs, String authType) {
                    }
                }
            };

            try {
                SSLContext sc = SSLContext.getInstance("SSL");
                sc.init(null, trustAllCerts, new java.security.SecureRandom());
                HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
                HttpsURLConnection.setDefaultHostnameVerifier( new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                    return true;
                    }
                } );
            } catch (NoSuchAlgorithmException e1) {
                e1.printStackTrace();
                logger.info("Exception:" + e1.getMessage());
            } catch (KeyManagementException e) {
                e.printStackTrace();
                logger.info("Exception:" + e.getMessage());
            }
            
            
            HttpsURLConnection urlConn = (HttpsURLConnection) url.openConnection();
            //urlConn = url.openConnection();
            inStream = new InputStreamReader(urlConn.getInputStream(), "UTF8");
            buff = new BufferedReader(inStream);
            // Read and print the lines from index.html
            while (true){
                nextLine =buff.readLine();
                if (nextLine !=null){
                    System.out.println(nextLine);
                    logger.info("read: " + nextLine);
                }else{
                    break;
                }
            }
            
            logger.info("connectToOracleDB: ");
            ConnectToOracleDB connectToOracleDB = new ConnectToOracleDB(null);  // сохраняем в базу
            logger.info("connectToOracleDB: successful ");
            
            } catch(MalformedURLException e){
                System.out.println("Please check the URL:" + e.toString() );
                logger.info("Exception:" + e.getMessage());
            } catch(IOException e1){
                System.out.println("Can’t read from the Internet: "+ e1.toString() );
                logger.info("Exception:" + e1.getMessage());
            } finally{
                if (inStream != null){
                    try{
                        inStream.close();
                        buff.close();
                    } catch(IOException e1){
                        System.out.println("Can’t close the streams: "+ e1.getMessage());
                        logger.info("Exception:" + e1.getMessage());
                    }
                }
                            return value;
            }
        }
}
