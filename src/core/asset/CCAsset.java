package core.asset;

import java.util.ArrayList;

import core.asset.Asset;
import core.cc.attributes.SecurityAttribute;

public class CCAsset extends Asset {

	public CCAsset(String id) {
		super(id);
	}

	ArrayList<SecurityAttribute> listOfSecurityAttribute = new ArrayList<SecurityAttribute>();
	
	
	public void addSecurityAttribute(SecurityAttribute securityAttribute){
		this.listOfSecurityAttribute.add(securityAttribute);
	}

	@SuppressWarnings("unchecked")
	public ArrayList<SecurityAttribute> getListOfSecurityAttribute() {
		return (ArrayList<SecurityAttribute>) listOfSecurityAttribute.clone();
	} 
	
	
	

}
