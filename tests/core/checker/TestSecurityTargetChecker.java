package core.checker;

import static org.junit.Assert.fail;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import junit.framework.Assert;

import org.junit.Test;
import org.xml.sax.SAXException;

import core.asset.Asset;
import core.asset.CCAsset;
import core.asset.Functionnality;
import core.cc.OSP;
import core.cc.SecurityTarget;
import core.securityneed.Integrity;
import core.securityneed.SecurityNeed;

public class TestSecurityTargetChecker {

	@Test
	public void test_Constructor() {
	
		SecurityTargetChecker sut = new SecurityTargetChecker();
		Assert.assertNotNull(sut);
	}
	
	@Test
	public void test_setSecurityTarget() throws SAXException, IOException, ParserConfigurationException {
		SecurityTarget st = new SecurityTarget();
		SecurityTargetChecker sut = new SecurityTargetChecker();
		sut.setSecurityTarget(st);
		Assert.assertEquals(st, sut.getSecurityTarget());
	}
	
	
	SecurityTarget st;
	
	@Test
	public void test_checkThatAtLeastOneFunctionIsDefined_throws_an_exception_when_no_functionality_is_defined(){
		SecurityTargetChecker sut=  givenAnUninitializedSecurityTarget() ;
		try {
			sut.checkThatAtLeastOneFunctionIsDefined();
			fail("SHALL throw an exception");
		} catch (Exception e) {
			
		}
	}
	

	@Test
	public void test_checkOSP_terminates_normaly_if_no_OSP_defined() throws Exception{
		SecurityTargetChecker sut=  givenAnUninitializedSecurityTarget() ;
		sut.checkOSP();
	}
	
	SecurityTargetChecker sut;
	
	@Test
	public void test_checkOSP_terminates_with_an_exception_if_an_OSP_defined_without_definition(){
		sut =  givenAnUninitializedSecurityTarget() ;
		when_Adding_An_OSP_to_The_Security_Target();
		check_that_checkOSP_must_throw_an_exception();
	}
	
	@Test
	public void test_checkOSP_terminates_normaly_if_an_OSP_defined_witht_definition() throws Exception{
		sut =  givenAnUninitializedSecurityTarget() ;
		when_Adding_An_OSP_to_The_Security_Target();
		when_setting_a_description_to_the_OSP();
		sut.checkOSP();
	}
	
	@Test
	public void test_checkOSP_terminates_with_an_exception_if_at_least_an_OSP_defined_without_definition() throws Exception{
		sut =  givenAnUninitializedSecurityTarget() ;
		when_Adding_An_OSP_to_The_Security_Target();
		when_Adding_Another_OSP_to_The_Security_Target();
		when_setting_a_description_to_the_OSP();
		check_that_checkOSP_must_throw_an_exception();
	}

	private void when_setting_a_description_to_the_OSP() {
		osp.setDescription("description");
	}
	
	
	
	

	private void check_that_checkOSP_must_throw_an_exception() {
		try {
		  sut.checkOSP();
		  fail("SHALL throw an exception");
		} catch (Exception e) {
			
		}
	}
	
	OSP osp;
	OSP osp2;

	private void when_Adding_An_OSP_to_The_Security_Target() {
		osp =new OSP("ospIdent");
		st.addOSP(osp);
	}
	
	private void when_Adding_Another_OSP_to_The_Security_Target() {
		osp2 =new OSP("ospIdent2");
		st.addOSP(osp2);
	}
	
	@Test
	public void test_checkAssetSecurityNeed_throw_an_exception_when_no_security_need_defined(){
		try {
			SecurityTargetChecker.checkAssetSecurityNeed(new CCAsset("ID"));
			fail("shall throw an exception");
		} catch (Exception e) {
			
		}
	}
	
	@Test
	public void test_checkRole_failure(){
		try {
			SecurityTargetChecker sut = givenAnUninitializedSecurityTarget();
			sut.checkRole();
			fail("shall throw an exception");
		} catch (Exception e) {
			
		}
	}
	
	@Test
	public void test_checkAllAssets_with_incorectAsset() {
		  SecurityTargetChecker sut = givenAnUninitializedSecurityTarget();
		  st.getSystemDescription().addAsset(new CCAsset("ID"));
		
		try {
		  sut.checkAllAssets();
		  fail("shall throw an exception");
		} catch (Exception e) {
			
		}
	}
	
	@Test
	public void test_checkAllAssets_with_givenACompletAsset() {
		  SecurityTargetChecker sut = givenAnUninitializedSecurityTarget();
		  st.getSystemDescription().addAsset(givenACompletAsset());
		
		try {
		  sut.checkAllAssets();
		  
		} catch (Exception e) {
			fail("shall NOT throw an exception");
		}
	}
	
	
	@Test
	public void test_checkAll(){
		SecurityTargetChecker sut = this.givenAnSecurityTargetWithARole();
		st.getSystemDescription().addAsset(givenACompletAsset());
		st.getSystemDescription().addFunctionnality(new Functionnality("f"));
		try {
			  sut.checkAll();
			  
			} catch (Exception e) {
				fail("shall NOT throw an exception");
			}
	}
	
	
	
	@Test
	public void test_checkAllAssets_with_no_asset() {
		SecurityTargetChecker sut = givenAnUninitializedSecurityTarget();
		try {
		  
		  sut.checkAllAssets();
		  fail("shall throw an exception");
		} catch (Exception e) {
			
		}
	}
	
	@Test
	public void test_checkAsset() {
		CCAsset asset = new CCAsset("ID");
		SecurityTargetChecker sut = new SecurityTargetChecker();
		try {
			sut.checkAsset(asset);
			fail("shall throw an exception");
		} catch (Exception e) {
			
		}
	}
	
	@Test
	public void test_checkAssetwithDescription() {
		CCAsset asset = new CCAsset("ID");
		asset.setDescription("description");
		SecurityTargetChecker sut = new SecurityTargetChecker();
		try {
			sut.checkAsset(asset);
			fail("shall throw an exception");
		} catch (Exception e) {
			
		}
	}
	
	@Test
	public void test_checkAsset_withSecurityNeedAndDescription() {;
		CCAsset asset = givenAnAssetWithSecurityNeed();
		asset.setDescription("description");
		SecurityTargetChecker sut = new SecurityTargetChecker();
		try {
			sut.checkAsset(asset);
			
		} catch (Exception e) {
			fail("shall not throw an exception");
		}
	}
	
	@Test
	public void test_checkAsset_withSecurityNeedAndNoDescription() {;
		CCAsset asset = givenAnAssetWithSecurityNeed();
		
		SecurityTargetChecker sut = new SecurityTargetChecker();
		try {
			sut.checkAsset(asset);
			fail("shall throw an exception");
		} catch (Exception e) {
			
		}
	}
	
	@Test
	public void test_checkRole_success(){
		try {
			SecurityTargetChecker sut = givenAnSecurityTargetWithARole();
			sut.checkRole();
			
		} catch (Exception e) {
			fail("shall throw an exception");
		}
	}

	private SecurityTargetChecker givenAnSecurityTargetWithARole() {
		SecurityTargetChecker sut = givenAnUninitializedSecurityTarget();
		st.addRole(new CCAsset("role1"));
		return sut;
	}
	
	@Test
	public void test_checkAssetSecurityNeed_terminates_normally_when_security_need_defined(){
		try {
			CCAsset asset = givenAnAssetWithSecurityNeed();
			SecurityTargetChecker.checkAssetSecurityNeed(asset);
		} catch (Exception e) {
			fail("shall throw an exception");

		}
	}

	private CCAsset givenAnAssetWithSecurityNeed() {
		CCAsset asset = new CCAsset("ID");
		asset.addSecurityNeed(new Integrity());
		return asset;
	}
	
	private CCAsset givenACompletAsset() {
		CCAsset asset =  givenAnAssetWithSecurityNeed();
		asset.setDescription("DESCRIPTION");
		return asset;
	}
	

	@Test
	public void test_checkThatAtLeastOneFunctionIsDefined_terminates_when_at_least_one_functionality_is_defined(){
		SecurityTargetChecker sut=  givenAnUninitializedSecurityTarget() ;
		st.addBienEssentiel(new Functionnality("f1"));
		try {
			Assert.assertNotNull(st);
			sut.checkThatAtLeastOneFunctionIsDefined();
			
		} catch (Exception e) {
			fail("SHALL terminate normaly");
		}
	}
	
	

	private SecurityTargetChecker givenAnUninitializedSecurityTarget() {
		
		SecurityTargetChecker sut = new SecurityTargetChecker();
		try {
			st = new SecurityTarget();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sut.setSecurityTarget(st);
		
		return sut;
	}
	
	
	@Test
	public void test_hasNoSecurityNeedDefined_shall_return_true_when_no_securityNeedAdded() {
		Asset asset = new Asset("id");
		Assert.assertTrue(SecurityTargetChecker.hasNoSecurityNeedDefined(asset));	
	}
	
	@Test
	public void test_hasNoSecurityNeedDefined_shall_return_false_when_atleast_oneSecurityNeedAdded() {
	
		Asset asset = new Asset("id");
		asset.addSecurityNeed(new SecurityNeed("integrity"));
		Assert.assertFalse(SecurityTargetChecker.hasNoSecurityNeedDefined(asset));
		
		
	}
	
	@Test 
	public void test_checkAssetDescription_is_failing_when_no_description_defined() {
		CCAsset asset = new CCAsset("id");
		try {
			SecurityTargetChecker.checkAssetDescription(asset);
			fail("MUST fail");
		} catch (Exception e) {		
		}
	}
	
	@Test 
	public void test_checkAssetDescription_is_failing_when_empty_description_defined() {
		CCAsset asset = new CCAsset("id");
		asset.setDescription("");
		try {
			SecurityTargetChecker.checkAssetDescription(asset);
			fail("MUST fail");
		} catch (Exception e) {		
		}
	}
	
	@Test 
	public void test_checkAssetDescription_is_successfull_when_description_defined() throws Exception {
		CCAsset asset = new CCAsset("id");
		asset.setDescription("description");
		SecurityTargetChecker.checkAssetDescription(asset);
			
		
	}
	
	@Test
	public void test_checkAsset_shall_fail_when_asset_description_not_defined() {
	
		SecurityTargetChecker sut = new SecurityTargetChecker();
		CCAsset asset = new CCAsset("id");
		asset.getListOfSecurityAttribute();
		try {
			sut.checkAsset(asset);
			fail("SHALL throw an exception");
		} catch (Exception e) {
			
		}
		
		
	}
	

}
