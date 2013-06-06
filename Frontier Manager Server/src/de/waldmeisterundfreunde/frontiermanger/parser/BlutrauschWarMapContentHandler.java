package de.waldmeisterundfreunde.frontiermanger.parser;

import java.util.LinkedList;
import java.util.List;

import javax.swing.text.DefaultEditorKit.CutAction;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

import com.google.gwt.thirdparty.javascript.jscomp.regex.CaseCanonicalize;

import de.waldmeisterundfreunde.frontiermanger.model.Alliance;
import de.waldmeisterundfreunde.frontiermanger.model.Faction;
import de.waldmeisterundfreunde.frontiermanger.model.Keep;

public class BlutrauschWarMapContentHandler implements ContentHandler {

	private String currentCharacters = new String("");
	private Keep currentKeep = null;
	private Alliance currentAlliance = null;
	private boolean isInLi = false;
	private boolean isInSpan = false;
	private boolean isInA = false;
	private boolean isInB = false;
	private boolean isInImg = false;
	private boolean isRelicKeep = false;

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		// TODO Auto-generated method stub
		currentCharacters += new String(ch, start, length);


	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes atts) throws SAXException {

		if (localName.equals("li")) {
			isInLi = true;
			currentKeep = new Keep();
		} else if (localName.equals("a")){
			isInA = true;
			if (isInLi) {
				if (atts.getLocalName(0).equals("id") ) {
					currentKeep.setId(atts.getValue(0));
				} 
				if (atts.getLocalName(1).equals("href") ) {
					String href = atts.getValue(1);
					if (!href.equals("#" + currentKeep.getId())) {
						currentAlliance = new Alliance();
						currentAlliance.setName(href.split(".p6\\=",2)[1]);
					}

				}
			}
		} else if (localName.equals("span")){
			isInSpan = true;
			if(isInLi && isInA){
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
						currentKeep.setFaction(faction);
					}
				}
			}
		} else if (localName.equals("b")){
			isInB = true;

		} else if (localName.equals("img")){
			isInImg = true;
			if(currentAlliance != null && isInLi && isInA && isInSpan){
				if(atts.getLocalName(0).equals("src")){
					currentAlliance.setEmblemeUrl(atts.getValue(0));
				}
			}
		} 



	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {

		if (localName.equals("li")) {
			isInLi = false;
		} else if (localName.equals("a")){
			if(currentAlliance != null){
				currentKeep.setAlliance(currentAlliance);
			}
			isInA = false;
		} else if (localName.equals("span")){
			if(isInLi && isInA){
				System.out.println(currentCharacters.toString());
				String[] keepInfo = ((currentCharacters.split(".\\(",2))[1].split("\\)",2));
				isRelicKeep = keepInfo[0].contains("Relickeep");
				if (isRelicKeep){
					keepInfo = ((currentCharacters.split(".\"",2))[1].split("\"",2));
					String homeRelic = keepInfo[0];
					keepInfo = (keepInfo[1]).split("\\.",2);
					String homeRelicInfo = keepInfo[0].replace("\n", "").replaceAll("\\s{2,}", "");
					List<String> ownedRelics = new LinkedList<String>();
					if(homeRelicInfo.equals("is on its place")){
						ownedRelics.add(homeRelic);
					}
					if (keepInfo[1].contains("Captured relics:")){

						keepInfo = keepInfo[1].replaceAll("\\n", "").split("\\s{2,}");
						boolean isInForeignRelics = false;
						for(String info : keepInfo){
							if(isInForeignRelics && !info.isEmpty()){
								ownedRelics.add(info);
							}
							isInForeignRelics = (info.equals("Captured relics:")|isInForeignRelics);


						}
					}
					//here are all owned relics in the ownedRelcis List
					
				}
				
			}
			isInSpan = false;
		} else if (localName.equals("b")){
			if(isInLi && isInA && isInSpan){
				currentKeep.setName(currentCharacters.replace("\n", "").replaceAll("\\s{2,}", ""));
			}
			isInB = false;
		} else if (localName.equals("img")){
			isInImg = false;
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
