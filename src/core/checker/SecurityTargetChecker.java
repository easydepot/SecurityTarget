package core.checker;

import core.asset.Asset;
import core.asset.CCAsset;
import core.cc.SecurityTarget;

public class SecurityTargetChecker {
	SecurityTarget securityTarget;
	
	
	

	public SecurityTarget getSecurityTarget() {
		return securityTarget;
	}

	public void setSecurityTarget(SecurityTarget securityTarget) {
		this.securityTarget = securityTarget;
	}
	
	public void checkAll() throws Exception{
		
		checkThatAtLeastOneFunctionIsDefined();
		checkRole();
		checkAllAssets();
		
		System.out.print("[Checking OK]");
	}

	void checkRole() throws Exception {
		if (this.getSecurityTarget().getRoles().isEmpty()){
			throw new Exception("No Role defined");
		}
		
	}

	protected void checkAllAssets() throws Exception {
		if (this.getSecurityTarget().getSystemDescription().getAssetList().isEmpty()){
			throw new Exception("No Asset defined");
		}
		for (Asset asset: this.getSecurityTarget().getSystemDescription().getAssetList()){
			checkAsset(asset);
			
			
		}
	}

	protected void checkAsset(Asset asset) throws Exception {
		CCAsset cast_asset = (CCAsset) asset;
		checkAssetSecurityNeed(cast_asset);
		checkAssetDescription(cast_asset);
	}

	static protected void checkAssetSecurityNeed(CCAsset asset)
			throws Exception {
		if (hasNoSecurityNeedDefined(asset)){
			throw new Exception("Asset A." + asset.getName() + " has no Security Need DEfined");
		}
	}

	static protected void checkAssetDescription(CCAsset asset)
			throws Exception {
		if (asset.getDescription()==null ){
			throw new Exception("Asset A." + asset.getName() + " is not described");
		}
		if (asset.getDescription().isEmpty() ){
			throw new Exception("Asset A." + asset.getName() + " is empty");
		}
	}

	static protected boolean hasNoSecurityNeedDefined(Asset cast_asset) {
		return cast_asset.getListOfSecurityNeed().isEmpty();
	}

	protected void checkThatAtLeastOneFunctionIsDefined() throws Exception {
		if (this.securityTarget.getNumberOfBienEssentiel()==0){
			throw new Exception("No TOE functionnalities are defined");
		}
	}

	

}
