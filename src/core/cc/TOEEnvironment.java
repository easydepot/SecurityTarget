package core.cc;

import java.util.ArrayList;

import core.asset.CCAsset;

public class TOEEnvironment {
	private ArrayList<CCAsset> roles = new ArrayList<CCAsset>();
	private ArrayList<CCAsset> directExternalEntities = new ArrayList<CCAsset>();
	private ArrayList<CCAsset> indirectExternalEntities = new ArrayList<CCAsset>();
	
	
	public void addRole(CCAsset role){
		this.roles.add(role);
	}


	public TOEEnvironment() {
	}


	public ArrayList<CCAsset> getRoles() {
		return roles;
	}


	


	public ArrayList<CCAsset> getDirectExternalEntities() {
		return directExternalEntities;
	}


	


	public ArrayList<CCAsset> getIndirectExternalEntities() {
		return indirectExternalEntities;
	}


	public void addDirectExternalEntitiese(CCAsset entity) {
		this.directExternalEntities.add(entity);
		
	}


	public void addIndirectExternalEntity(CCAsset entity) {
		this.indirectExternalEntities.add(entity);
		
	}


	
	
	
	
	
}