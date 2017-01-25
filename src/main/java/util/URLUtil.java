package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import javax.net.ssl.HttpsURLConnection;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by IntelliJ IDEA.
 * User: mortenandersen
 * Date: 2006-09-06
 * Time: 16:15:48
 * To change this template use File | Settings | File Templates.
 */
public class URLUtil{

    public static String getContentFrom(URL url) throws IOException {
        String encoding = "ISO-8859-1";
        return getContentFrom(encoding, url);
    }
    
    public static String getContentFrom(final String encoding, final URL url) throws UnsupportedEncodingException, IOException {
        //System.out.println("GCFURL:"+url.toString());
        StringBuffer sb = new StringBuffer();
        //System.out.println("GCF:");
        InputStream urlStream = url.openStream();
        //System.out.println("GCF:");
        InputStreamReader inputStream = new InputStreamReader(urlStream,encoding);
        BufferedReader in = new BufferedReader( inputStream );

        //System.out.println("GCF:");
        String inputLine;
        while ((inputLine = in.readLine()) != null){
            sb.append(inputLine);
        }
        in.close();
        //System.out.println("GCF:"+sb.toString());
        return sb.toString();
    }

    public static String getServerURL(HttpServletRequest req) {
        String scheme = req.getScheme();             // http
        String serverName = req.getServerName();     // hostname.com
        int serverPort = req.getServerPort();        // 80

        String serverPath = null;
        // Reconstruct original requesting URL
        if (80 == serverPort){
             serverPath =  scheme+"://"+serverName;
        }
        else{
            serverPath =  scheme+"://"+serverName+":"+serverPort;
        }
        return serverPath;
    }

    public static String getLink(HttpServletRequest req){
        String serverPath = getServerURL(req);
        String contextPath = req.getContextPath();   // /mywebapp
        return serverPath+contextPath;
    }
}
