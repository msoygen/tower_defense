package model;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLRead{
	protected DocumentBuilderFactory dbf;
	protected DocumentBuilder db;
	protected Document dom;
	protected NodeList l;
	protected Element root;
    protected InputStream inputStream;
    
    protected void initXML(String tag){
		try {
			this.db = this.dbf.newInstance().newDocumentBuilder();
		} catch (ParserConfigurationException e1) {
			e1.printStackTrace();
		}

		try {
			this.dom = this.db.parse(this.inputStream);
		} catch (SAXException | IOException e) {
			e.printStackTrace();
		}

		// normalize XML structure
		this.dom.normalizeDocument();
		this.l = this.dom.getElementsByTagName(tag);

		// get root element
		this.root = this.dom.getDocumentElement();
    }
}