package core.cc.attributes;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestSecurityAttribut {

	@Test
	public void test_setInitialValue_shall_not_fail_when_initial_value_is_authorized() {
		SecurityAttribute at = new SecurityAttribute("att_id");
		at.getListOfPossibleValue().add("yes");
		at.getListOfPossibleValue().add("no");
		try {
			at.setInitialValue("yes");
		} catch (Exception e) {
			fail("Shall not throw an exception");
		}
		
	}
	
	@Test
	public void test_getFullName_defaultCased() {
		SecurityAttribute at = new SecurityAttribute("att_id");
		assertEquals("AT.att_id",at.getFullId());
	}
	
	@Test
	public void test_getFullName_case_attribute_is_information() {
		SecurityAttribute at = new SecurityAttribute("att_id");
		at.setType_information(true);
		assertEquals("I.att_id",at.getFullId());
	}
	
	
	
	
	
	@Test
	public void test_setInitialValue_shall_fail_when_initial_value_is_not_authorized() {
		SecurityAttribute at = new SecurityAttribute("att_id");
		at.getListOfPossibleValue().add("yes");
		at.getListOfPossibleValue().add("no");
		try {
			at.setInitialValue("unknown");
			fail("Shall throw an exception");
		} catch (Exception e) {
			
		}
		
	}

}
