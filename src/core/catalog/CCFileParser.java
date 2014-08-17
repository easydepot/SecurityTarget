package core.catalog;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import core.sfr.SFR;
import core.sfr.SFRElement;
import core.sfr.SFRElementAssignement;


public class CCFileParser extends DefaultHandler{
	
	SFR currentSFR;
	SFRElement currentSFRElement;
	SFRElementAssignement currentSFRAssignement;
	boolean within_assignmentnotes = false;
	
	public static void parse() throws SAXException, IOException, ParserConfigurationException{
		SAXParserFactory fabrique = SAXParserFactory.newInstance();
		SAXParser parseur = fabrique.newSAXParser();
		CCFileParser handler = new CCFileParser();

		File fichier = new File("res/cc3R4.xml");
		parseur.parse(fichier, handler);
	}
	
	@Override
	public void startElement(String uri, String localName,
			String qName, Attributes attributes) throws SAXException {
		if(qName.equals("f-component")){
			currentSFR = SFR.addNewSFR(attributes.getValue("id"));
			currentSFR.setName(attributes.getValue("name"));
		}
		if(qName.equals("fco-dependsoncomponent")){
			currentSFR.addSFRDependency(attributes.getValue("fcomponent"));

		}
		//<fco-dependsoncomponent fcomponent="fmt_mof.1"/>
		if(qName.equals("f-element")){
			currentSFRElement = new SFRElement(attributes.getValue("id"));
			currentSFR.addSFRElement(currentSFRElement);
		}
		if(qName.equals("fe-assignment")){
			currentSFRAssignement = currentSFRElement.addNewAssignement();
		}
		if(qName.equals("fe-selection")){
			currentSFRAssignement = currentSFRElement.addNewAssignement();
		}
		if(qName.equals("fe-assignmentnotes")){
			this.within_assignmentnotes = true;
			
		}
		if(qName.equals("fe-selectionnotes")){
			this.within_assignmentnotes = true;
			
		}
	}
	
	

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if(qName.equals("f-component")){
			currentSFR = null;
		}
		if(qName.equals("f-element")){
			currentSFRElement = null;
		}
		if(qName.equals("fe-assignment")){
			currentSFRAssignement = null;
		}
		if(qName.equals("fe-selection")){
			currentSFRAssignement = null;
		}
		if(qName.equals("fe-assignmentnotes")){
			this.within_assignmentnotes = false;
			
		}
		if(qName.equals("fe-selectionnotes")){
			this.within_assignmentnotes = false;
			
		}

	}



	@Override
	public void characters(char[] arg0, int arg1, int arg2) throws SAXException {
		if (currentSFRElement!=null && this.currentSFRAssignement==null){
			if (ifNotAssignementNote()){
			  addContentToSFRElement(arg0, arg1, arg2);	
			}
		}
	}



	private void addContentToSFRElement(char[] arg0, int arg1, int arg2) {
		currentSFRElement.addContent(String.copyValueOf(arg0, arg1, arg2));
	}



	private boolean ifNotAssignementNote() {
		return this.within_assignmentnotes == false;
	}

}
