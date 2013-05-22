package tk.speedprog.dota2.webapi;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class HeroesXMLHandler extends DefaultHandler {

	boolean hero = false, name = false, id = false, localizedName = false;
	int cId = 0;
	String cLName = "";
	String cName = "";
	/**
	 * Handles the start of an element.
	 */
	public final void startElement(final String uri, String localName,
			String qName, final Attributes attributes) {
		if (qName.equalsIgnoreCase("hero")) {
			hero = true;
		} else if (qName.equalsIgnoreCase("name")) {
			name = true;
		} else if (qName.equalsIgnoreCase("id")) {
			id = true;
		} else if (qName.equalsIgnoreCase("localized_name")) {
			localizedName = true;
		}
	}

	/**
	 * Handles the end of an Element.
	 */
	public final void endElement(final String uri, final String localName,
			final String qName) {
		if (qName.equalsIgnoreCase("hero")) {
			hero = false;
			System.out.println("heroes[" + (cId-1) + "] = new Hero(\"" + cName + "\", " + cId + ", \"" + cLName + "\");");
		} else if (qName.equalsIgnoreCase("name")) {
			name = false;
		} else if (qName.equalsIgnoreCase("id")) {
			id = false;
		} else if (qName.equalsIgnoreCase("localized_name")) {
			localizedName = false;
		}
	}

	/**
	 * Handling character data.
	 * @param ch
	 *            the characters of the XML
	 * @param start
	 *            the current character data start
	 * @param length
	 *            the current character data length
	 * @throws SAXException
	 *             thrown if some parsing error occurs
	 */
	public final void characters(final char[] ch, final int start,
			final int length) throws SAXException {
		String cString = new String(ch, start, length);
		if (name) {
			cName = cString;
		} else if (id) {
			cId = Integer.parseInt(cString);
		} else if (localizedName) {
			cLName = cString;
		}
	}
}
