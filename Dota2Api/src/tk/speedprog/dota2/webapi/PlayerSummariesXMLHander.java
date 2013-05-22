package tk.speedprog.dota2.webapi;


import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class PlayerSummariesXMLHander extends DefaultHandler {

	public String[] data = new String[2];
	private boolean personaname = false, avatarmedium = false;
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) {
		if (qName.equalsIgnoreCase("personaname")) {
			personaname = true;
		} else if (qName.equalsIgnoreCase("avatarmedium")) {
			avatarmedium = true;
		}
	}

	public void endElement(String uri, String localName, String qName) {
		if (qName.equalsIgnoreCase("personaname")) {
			personaname = false;
		} else if (qName.equalsIgnoreCase("avatarmedium")) {
			avatarmedium = false;
		}
	}

	public void characters(char ch[], int start, int length)
			throws SAXException {
		String cString  = new String(ch, start, length);
		if (personaname) {
			data[0] = cString;
		} else if (avatarmedium) {
			data[1] = cString;
		}
	}
	
}
