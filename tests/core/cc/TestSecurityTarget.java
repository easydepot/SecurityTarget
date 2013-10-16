package core.cc;

import static org.junit.Assert.fail;
import junit.framework.Assert;

import org.junit.Test;

import contremesure.CounterMeasure;

import core.RiskAnalysis;
import core.TestRiskAnalysis;
import core.asset.CCAsset;
import core.asset.Functionnality;
import core.threat.Threat;

public class TestSecurityTarget extends TestRiskAnalysis {

	@Test
	public void test_searchBienSupport_must_fail_when_asset_not_found_case_no_asset() {
		SecurityTarget st = new SecurityTarget();	
		try {
			st.searchBienSupport("unknown");
			fail("the above method must fail");
		} catch (Exception e) {
			
		}	
	}
	
	SecurityTarget st;
	
	@Test
	public void test_addFunctionnality(){
		givenAnEmptySecurityTarget();
		whenAddingAFunctionality();
		Assert.assertEquals(1, st.getNumberOfBienEssentiel());
	}
	
	@Test
	public void test_addFunctionnality2(){
		givenAnEmptySecurityTarget();
		whenAddingAFunctionality();
		Assert.assertEquals(1, st.getSystemDescription().getFunctionalityList().size());
	}


	private void whenAddingAFunctionality() {
		st.getSystemDescription().addFunctionnality(new Functionnality("f1"));
	}


	private void givenAnEmptySecurityTarget() {
		st = new SecurityTarget();
	}
	
	
	@Test 
	public void searchBienSupport_must_success_when_finding_a_matching_role(){
		
		CCAsset asset = new CCAsset("ID");
		SecurityTarget st = new SecurityTarget();	
		st.addRole(asset);
		try {
		st.searchBienSupport("ID");
		} catch (Exception e) {
			fail("the above method SHALL NOT fail");
		}	

	}
	
	
	
	@Test 
	public void test_add_asset_coverage_and_verify_that_the_asset_is_covered() {
		SecurityTarget st = new SecurityTarget();	
		CCAsset asset = new CCAsset("asset1");
		asset.setDescription("description");
		st.getSystemDescription().addAsset(asset);
		
		
	}
	
	@Test 
	public void test_getListCoveredAsset_does_not_returns_null(){
		SecurityTarget st = new SecurityTarget();	
		Threat t = new Threat();
		Assert.assertNotNull(st.getListOfCoveredAsset(t));
		

	}
	

	@Test 
	public void test_getEnvironementSecurityObjective_does_not_returns_null(){
		SecurityTarget st = new SecurityTarget();	
		Assert.assertNotNull(st.getEnvironementSecurityObjective());
	}
	
	@Test 
	public void test_getSecurityObjective_does_not_returns_null(){
		SecurityTarget st = new SecurityTarget();	
		Assert.assertNotNull(st.getSecurityObjective());
	}
	
	@Test 
	public void test_getIdentification_does_not_returns_null(){
		SecurityTarget st = new SecurityTarget();	
		Assert.assertNotNull(st.getIdentification());
	}
	
	
	
	@Test 
	public void test_getListCoveredAsset_returns_emptylist_when_no_covering_is_defined(){
		SecurityTarget st = new SecurityTarget();	
		Threat t = new Threat();
		Assert.assertEquals(0, st.getListOfCoveredAsset(t).size());
		

	}
	
	@Test 
	public void test_getListCoveredAsset_returns_list_of_covered_asset(){
		SecurityTarget st = new SecurityTarget();	
		Threat t = new Threat();
		CCAsset asset1 = new CCAsset("asset1");
		CCAsset asset2 = new CCAsset("asset2");
		st.addCoverage(asset1, t);
		st.addCoverage(asset2, t);
		Assert.assertEquals(2, st.getListOfCoveredAsset(t).size());
		

	}
	
	@Test
	public void test_searchBienSupport_succeed_when_asset_is_disabled() {
		SecurityTarget st = new SecurityTarget();
		CCAsset bs = new CCAsset("ident");
		bs.disable();
		st.getSystemDescription().addAsset(bs);
		try {
			st.searchBienSupport("ident");
		} catch (Exception e) {
			fail("The method shall not fail");
		}
			
		
		
		
	}
	
	@Test
	public void test_termAndDefinitionList_emptyList() {
		SecurityTarget st = new SecurityTarget();	
		Assert.assertNotNull("",st.getTermAndDefinitionList());
		Assert.assertEquals(0, st.getTermAndDefinitionList().size());
	}
	
	@Test 
	public void test_getListOfOSP_emptyList() {
		SecurityTarget st = new SecurityTarget();	
		Assert.assertNotNull(st.getListOfOSP());
		Assert.assertEquals(0, st.getListOfOSP().size());
	}
	
	@Test 
	public void test_addOSP() {
		SecurityTarget st = new SecurityTarget();	
		OSP osp = new OSP();
		osp.setId("ID");
		osp.setDescription("description");
		st.addOSP(osp);
		Assert.assertNotNull(st.getListOfOSP());
		Assert.assertEquals(1, st.getListOfOSP().size());
	}
	
	
	@Test
	public void test_test_that_assert_coverage_table_does_not_return_null(){
		SecurityTarget st = new SecurityTarget();	
		Assert.assertNotNull(st.getAssetAgainstThreatCoverageTable());
	}
	
	@Test
	public void test_test_that_getTOEenvironment_does_not_return_null(){
		SecurityTarget st = new SecurityTarget();	
		Assert.assertNotNull(st.getToeEnvironment());
	}
	
	
	
	@Test
	public void test_termAndDefinitionList_withElements() {
		SecurityTarget st = new SecurityTarget();	
		st.addTermAndDefinition("term1", "definition1");
		st.addTermAndDefinition("term2", "definition2");
		Assert.assertNotNull("",st.getTermAndDefinitionList());
		Assert.assertEquals(2, st.getTermAndDefinitionList().size());
	}
	
	@Test
	public void test_setType() {
		SecurityTarget st = new SecurityTarget();
		st.setType("type");
		Assert.assertEquals("type",st.getType());
	}
	
	@Test
	public void test_setToeUsage() {
		SecurityTarget st = new SecurityTarget();
		st.setToeUsage("usage");
		Assert.assertEquals("usage",st.getToeUsage());
	}
	
	@Test
	public void test_setScope() {
		SecurityTarget st = new SecurityTarget();
		st.setScope("scope");
		Assert.assertEquals("scope",st.getScope());
	}
	
	@Test
	public void test_searchBienSupport_return_asset_when_found_in_object_list() throws Exception {
		SecurityTarget st = new SecurityTarget();
		st.getSystemDescription().addAsset(new CCAsset("id"));
		CCAsset returned_value =  st.searchBienSupport("id");
		Assert.assertEquals("id", returned_value.getId());
		
		
	}
	
	@Test
	public void test_searchBienSupport_return_asset_when_found_in_role_list() throws Exception {
		SecurityTarget st = new SecurityTarget();
		st.getRoles().add(new CCAsset("id"));
		CCAsset returned_value =  st.searchBienSupport("id");
		Assert.assertEquals("id", returned_value.getId());
		
		
	}

	
	@Test
	public void test_searchBienSupport_must_fail_when_asset_not_found_case_no_matching_asset() {
		SecurityTarget st = new SecurityTarget();
		st.getSystemDescription().getAssetList().add(new CCAsset("id"));
		
		try {
			st.searchBienSupport("unknown");
			fail("the above method must fail");
		} catch (Exception e) {
			
		}
		
		
		
	}

	

}
