package com.lzz.util;

/**
 * Created by gl49 on 2018/7/7.
 */
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class HttpClient {
    private static String proxyHost = PropertiesUtil.get("http.proxy");

    private static final class DefaultTrustManager implements X509TrustManager {
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
    }

    private static HttpsURLConnection getHttpsURLConnection(String uri, String method) throws IOException {
        SSLContext ctx = null;
        try {
            ctx = SSLContext.getInstance("TLS");
            ctx.init(new KeyManager[0], new TrustManager[] { new DefaultTrustManager() }, new SecureRandom());
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        SSLSocketFactory ssf = ctx.getSocketFactory();

        URL url = new URL(uri);
        HttpsURLConnection httpsConn;
        String hostStr = proxyHost;
        String proxyIp;
        Integer proxyPort;
        if(StringUtils.isBlank( hostStr )){
            httpsConn = (HttpsURLConnection) url.openConnection();
        }else{
            String[] tmpArr = hostStr.split(":");
            proxyIp = tmpArr[0];
            proxyPort = Integer.valueOf(tmpArr[1]);
            InetSocketAddress addr = new InetSocketAddress(proxyIp, proxyPort);
            Proxy proxy = new Proxy(Proxy.Type.HTTP, addr);
            httpsConn = (HttpsURLConnection) url.openConnection( proxy );
        }
        httpsConn.setConnectTimeout(50000);
        httpsConn.setReadTimeout(50000);
        httpsConn.setSSLSocketFactory(ssf);
        httpsConn.setHostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String arg0, SSLSession arg1) {
                return true;
            }
        });
        httpsConn.setRequestMethod(method);
        httpsConn.setDoInput(true);
        httpsConn.setDoOutput(true);
        return httpsConn;
    }

    private static HttpURLConnection getHttpURLConnection(String uri, String method) throws IOException {
        URL url = new URL(uri);
        InetSocketAddress addr = new InetSocketAddress("10.16.46.161",3333);
        Proxy proxy = new Proxy(Proxy.Type.HTTP, addr);
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection( proxy );
        httpConn.setRequestMethod(method);
        httpConn.setDoInput(true);
        httpConn.setDoOutput(true);
        return httpConn;
    }

    private static byte[] getBytesFromStream(InputStream is) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] kb = new byte[1024];
        int len;
        while ((len = is.read(kb)) != -1) {
            baos.write(kb, 0, len);
        }
        byte[] bytes = baos.toByteArray();
        baos.close();
        is.close();
        return bytes;
    }

    private static void setBytesToStream(OutputStream os, byte[] bytes) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        byte[] kb = new byte[1024];
        int len;
        while ((len = bais.read(kb)) != -1) {
            os.write(kb, 0, len);
        }
        os.flush();
        os.close();
        bais.close();
    }

    public static byte[] doGets(String uri) throws IOException {
        HttpsURLConnection httpsConn = getHttpsURLConnection(uri, "GET");
        return getBytesFromStream(httpsConn.getInputStream());
    }

    public static byte[] doPosts(String uri, String data) throws IOException {
        HttpsURLConnection httpsConn = getHttpsURLConnection(uri, "POST");
        setBytesToStream(httpsConn.getOutputStream(), data.getBytes());
        return getBytesFromStream(httpsConn.getInputStream());
    }

    public static JSONObject httpPost(String url, JSONObject jsonParam){
        String param = jsonParam.toString();
        byte[] res = new byte[0];
        try {
            res = doPosts(url, param);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String resStr = new String( res );
        JSONObject jsonObject = JSONObject.fromObject( resStr );
        return jsonObject;
    }

    public static JSONObject httpGet(String url){
        byte[] res = new byte[0];
        try {
            res = doGets(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String resStr = new String( res );
        JSONObject jsonObject = JSONObject.fromObject( resStr );
        return jsonObject;
    }
}