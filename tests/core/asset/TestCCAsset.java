package core.asset;


import junit.framework.Assert;

import org.junit.Test;



public class TestCCAsset {
	@Test
	public void test_searchBienSupport_must_fail_when_asset_not_found_case_no_asset() {
		CCAsset asset = new CCAsset("id");
		
		Assert.assertNotNull(asset.getListOfSecurityAttribute());
		
	}
}
