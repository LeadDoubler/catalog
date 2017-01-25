package com.asap.security.resourse;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Map.Entry;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class XStreamResourceRepository implements ResourceRepository {
	private static final String CONFIGURATION_FILE = "security.configuration.xml";

	private static XStream xstream = new XStream();
	static {
		xstream = new XStream(new DomDriver());
		xstream.setMode(XStream.NO_REFERENCES);
		xstream.alias("parameters", SecurityResource.class);
		xstream.alias("configuration", Map.class);
		xstream.alias("conf", Entry.class);
		xstream.alias("bean", String.class);
	}

	public void saveResources(Map<String, SecurityResource> resources) {
		xstream = new XStream(new DomDriver());
		xstream.setMode(XStream.NO_REFERENCES);
		xstream.alias("parameters", SecurityResource.class);
		xstream.alias("configuration", Map.class);
		xstream.alias("bean", Entry.class);
		xstream.alias("class", String.class);
		URL url = this.getClass().getResource("/" + CONFIGURATION_FILE);
		if (url == null) {

			return;
		} else {
			OutputStream ostream;
			try {
				File file = new File(url.getFile());
				ostream = new FileOutputStream(file);
				xstream.toXML(resources, ostream);
				return;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return;
	}

	public Map<String, SecurityResource> getResources() {
		Map<String, SecurityResource> resources = null;
		URL url = this.getClass().getResource("/" + CONFIGURATION_FILE);
		if (url == null) {
			return resources;
		} else {
			InputStream istream;
			try {
				istream = url.openConnection().getInputStream();
				resources = (Map<String, SecurityResource>) xstream
						.fromXML(istream);
				return resources;
			} catch (Exception e) {
				// ignore this excetion
				e.printStackTrace();
			}
		}
		return resources;
	}

}