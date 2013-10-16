package core.cc;

import junit.framework.Assert;

import org.junit.Test;


public class TestAssuranceLevel {
	
	@Test 
	public void test_add_asset_coverage_and_verify_that_the_asset_is_covered() {
		AssuranceLevel a = new AssuranceLevel();
		a.setLevel("EAL4");
		Assert.assertEquals("EAL4", a.getLevel());
		
		
	}
	
	@Test 
	public void test_is_augmented_case_true() {
		AssuranceLevel a = new AssuranceLevel();
		a.addAugmentation("VAN.3");
		Assert.assertTrue(a.isAugmented());
		
	}
	
	@Test 
	public void test_is_augmented_case_false() {
		AssuranceLevel a = new AssuranceLevel();
		Assert.assertFalse(a.isAugmented());
		
	}
	
	@Test
	public void addTest_getQualificationLevel(){
		AssuranceLevel a = new AssuranceLevel();
		a.setQualificationLevel("Standard");
		Assert.assertEquals("Standard", a.getQualificationLevel());
	}


}
