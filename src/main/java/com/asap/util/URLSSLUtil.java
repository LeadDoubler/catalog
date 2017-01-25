package com.asap.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class URLSSLUtil {

	public static void main(String[] args) {
		// Method for testing connection. It saves response from https site to
		// file with name test.html
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(new File(
					"test.html"));
			fileOutputStream.write(getContentFrom(
					new URL("https://patentbors.ripe.ca/")).getBytes());
			fileOutputStream.close();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String getContentFrom(URL url) throws IOException {

		StringBuffer sb = new StringBuffer();

		// Create a trust manager that does not validate certificate chains
		TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				return null;
			}

			public void checkClientTrusted(
					java.security.cert.X509Certificate[] certs, String authType) {
			}

			public void checkServerTrusted(
					java.security.cert.X509Certificate[] certs, String authType) {
			}
		} };

		// Install the all-trusting trust manager
		try {
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, trustAllCerts, new java.security.SecureRandom());
			HttpsURLConnection
					.setDefaultSSLSocketFactory(sc.getSocketFactory());
		} catch (Exception e) {
		}

		// Now we can access an https URL without having the certificate in the
		// truststore
		URLConnection con = url.openConnection();
		// causes the VM to not display a dialog when connecting
		// to untrusted servers
		con.setAllowUserInteraction(false);
		BufferedReader in = new BufferedReader(new InputStreamReader(con
				.getInputStream()));
		String line;
		while ((line = in.readLine()) != null) {
			sb.append(line);
		}
		in.close();
		return sb.toString();
	}

}
