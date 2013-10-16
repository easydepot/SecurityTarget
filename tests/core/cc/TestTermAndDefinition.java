package core.cc;

import junit.framework.Assert;

import org.junit.Test;

public class TestTermAndDefinition {
	
	@Test
	public void test_termAndDefinitionList_getTerm() {
		TermAndDefinition t = new TermAndDefinition("term","definition");
		Assert.assertEquals("term", t.getTerm());
	}
	
	@Test
	public void test_termAndDefinitionList_getDefinition() {
		TermAndDefinition t = new TermAndDefinition("term","definition");
		Assert.assertEquals("definition", t.getDefinition());
	}
}
