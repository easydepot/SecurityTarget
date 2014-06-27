package core.sfr;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import core.printing.SimpleText;


public class TestSFRAssignement {
	
	SFRElementAssignement sut;
	
	@Test
	public void test_that_isAssigned_return_true_when_setContent_has_been_called() {
		givenAnAssignement();
		when_setting_the_content();
		assertTrue(sut.isAssigned());
	}
	
	@Test
	public void test_that_by_default_getcontent_returns_default_value() {
		givenAnAssignement();
		assertThatGetContentReturns("[assignement]");
	}

	private void assertThatGetContentReturns(String textResult) {
		assertEquals(textResult,((SimpleText)sut.getContent()).getText());
	}
	
	@Test
	public void test_that_by_default_getcontent_returns_the_setted_content() {
		givenAnAssignement();
		when_setting_the_content();
		assertThatGetContentReturns("content");
	}



	private void when_setting_the_content() {
		sut.setContent("content");
	}


	private void givenAnAssignement() {
		SFRElement e = new SFRElement("FMT_MTD.1.1");
		sut = e.addNewAssignement();
	}
	
	
	@Test
	public void test_that_isAssigned_return_true_when_setContent_has_not_been_called() {
		givenAnAssignement();
		assertFalse(sut.isAssigned());
	}
	
	@Test
	public void test_that_isAssigned_return_true_when_setExplicitlyUnassigned() {
		givenAnAssignement();
		sut.ignoreAssignement();
		assertTrue(sut.isAssigned());
	}

}
