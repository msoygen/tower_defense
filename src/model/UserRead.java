package model;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.io.File;

public class UserRead extends XMLRead{

	public UserRead() {
		this.inputStream = this.getClass().getResourceAsStream("/User.xml");
	}

	public GameUser XmlWriterSingle(String username, int gold) {
		this.initXML("user");
		String stringGold = Integer.toString(gold);

		int i = l.getLength();

		Element newElement = dom.createElement("user");
		newElement.setAttribute("id", Integer.toString(i + 1));

		Element nameNode = dom.createElement("name");
		nameNode.appendChild(dom.createTextNode(username));
		newElement.appendChild(nameNode);

		Element goldNode = dom.createElement("gold");
		goldNode.appendChild(dom.createTextNode(stringGold));
		newElement.appendChild(goldNode);

		root.appendChild(newElement);

		DOMSource source = new DOMSource(dom);

		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = null;
		try {
			transformer = transformerFactory.newTransformer();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		}
		StreamResult result = new StreamResult(new File("User.xml"));
		try {
			transformer.transform(source, result);
		} catch (TransformerException e) {
			e.printStackTrace();
		}

		GameUser user = new GameUser(username, gold);

		return user;
	}

	public GameUser ModifyXML(GameUser user) {
		this.initXML("user");
		try {
			for (int i = 0; i < l.getLength(); i++) {
				String currName = root.getElementsByTagName("name").item(i).getTextContent();
				if (user.getUsername().equals(currName) && currName != null) {
					Element goldElement = (Element) l.item(i);
					Node goldAmount = goldElement.getElementsByTagName("gold").item(0).getFirstChild();
					goldAmount.setNodeValue(Integer.toString(user.getGold()));
					
					String username = root.getElementsByTagName("name").item(i).getTextContent();
					int gold = Integer.parseInt(root.getElementsByTagName("gold").item(i).getTextContent());
					user = new GameUser(username, gold);
					//this.dom.getDocumentElement().normalize();
					// for output to file, console
					TransformerFactory transformerFactory = TransformerFactory.newInstance();
					Transformer transformer = transformerFactory.newTransformer();
					// for pretty print
					DOMSource source = new DOMSource(dom);
		
					// write to console or file
					StreamResult file = new StreamResult(new File("User.xml"));
		
					// write data
					transformer.transform(source, file);
					return user;
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return null;
	}

	public GameUser initUser(String name) {
		this.initXML("user");
		GameUser user = null;
		boolean isDone = false;
		try {
			for (int i = 0; i < l.getLength(); i++) {
				// print attributes
				String curName = root.getElementsByTagName("name").item(i).getTextContent();
				if (name.equals(curName) && curName != null) {

					String username = root.getElementsByTagName("name").item(i).getTextContent();
					int gold = Integer.parseInt(root.getElementsByTagName("gold").item(i).getTextContent());
					user = new GameUser(username, gold);

					isDone = true;

					return user;
				}
			}

			if (!isDone) {
				user = this.XmlWriterSingle(name, 25);
				return user;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}

		return user;

	}

}