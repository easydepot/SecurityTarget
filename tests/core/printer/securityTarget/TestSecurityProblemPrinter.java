package core.printer.securityTarget;

import junit.framework.Assert;

import org.junit.Test;

import core.asset.Asset;
import core.cc.SecurityTarget;
import core.printing.Doc;
import core.printing.Sequence;
import core.printing.SimpleText;
import core.printing.WarningText;
import core.securityneed.Integrity;


public class TestSecurityProblemPrinter {
	@Test
	public void test_getAssetProtectionLine_shall_warning_when_empty() throws Exception{
		SecurityTarget st = new SecurityTarget();
		SecurityProblemePrinter stprinter = new SecurityProblemePrinter(st, new Doc());
		Asset asset = new Asset("id");
		Assert.assertEquals(WarningText.class, stprinter.getAssetProtectionLine(asset).getClass());
	}
	
	
	@Test
	public void test_getAssetProtectionLine_shall_display_listofprotection_when_non_empty() throws Exception{
		SecurityTarget st = new SecurityTarget();
		SecurityProblemePrinter stprinter = new SecurityProblemePrinter(st,new Doc());
		Asset asset = new Asset("id");
		asset.addSecurityNeed(new Integrity());
		Sequence s = (Sequence) stprinter.getAssetProtectionLine(asset);
		Assert.assertEquals("Protection:", ((SimpleText)s.get(0)).getText());
		Assert.assertEquals("integrity ", ((SimpleText)s.get(1)).getText());
	}
	
}
