package core.catalog;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import junit.framework.Assert;

import org.junit.Test;
import org.xml.sax.SAXException;

import core.asset.CCAsset;
import core.sfr.SFR;


public class TestCCFileParser {
	
	
	@Test
	public void test_that_CCFileParse_parse_and_set_the_depedencies() throws SAXException, IOException, ParserConfigurationException {
		CCFileParser.parse();
		System.out.print(SFR.listOfSFR.get(0).getListOfDependancies().get(0).getFamily());
		
	}

}
