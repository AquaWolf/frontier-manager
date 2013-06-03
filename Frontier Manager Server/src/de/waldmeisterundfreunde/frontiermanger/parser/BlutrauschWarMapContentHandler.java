package de.waldmeisterundfreunde.frontiermanger.parser;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

import com.google.gwt.thirdparty.javascript.jscomp.regex.CaseCanonicalize;

import de.waldmeisterundfreunde.frontiermanger.model.Faction;
import de.waldmeisterundfreunde.frontiermanger.model.Keep;

public class BlutrauschWarMapContentHandler implements ContentHandler {

	private String currentCharacters = null;
	private Keep currentKeep = null;

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		// TODO Auto-generated method stub
		currentCharacters = ch.toString();

	}



	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {



	}




	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes atts) throws SAXException {

		if (localName.equals("li")) {
			currentKeep = new Keep();
		}
		if (localName.equals("span")){
			if(atts.getLocalName(0).equals("class")){
				Faction faction = null;
				if (atts.getValue(0).equals("mid")) {
					faction = Faction.Midgard;
				} else if (atts.getValue(0).equals("hib")) {
					faction = Faction.Hibernia;
				} else if (atts.getValue(0).equals("alb")) {
					faction = Faction.Albion;
				}
				if (faction != null){
					//currentKeep.setFaction(faction);
				}
				System.out.println(currentCharacters);
			}
		}


	}



	@Override
	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void endPrefixMapping(String arg0) throws SAXException {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void ignorableWhitespace(char[] arg0, int arg1, int arg2)
			throws SAXException {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void processingInstruction(String arg0, String arg1)
			throws SAXException {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void setDocumentLocator(Locator arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void skippedEntity(String arg0) throws SAXException {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void startPrefixMapping(String arg0, String arg1)
			throws SAXException {
		// TODO Auto-generated method stub
		
	}
}
