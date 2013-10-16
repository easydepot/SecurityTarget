package core.cc;

import java.util.ArrayList;

import core.RiskAnalysis;
import core.RiskAnalysisObject;
import core.asset.Asset;
import core.asset.CCAsset;
import core.coverageTable.CoverageTable;
import core.securityObjective.SecurityObjective;
import core.threat.Threat;

public class SecurityTarget extends RiskAnalysis {
	
	boolean isProtectionProfile = false;
	Identification identification = new Identification();
	AssuranceLevel assuranceLevel = new AssuranceLevel();
	TOEOverview data = new TOEOverview();
	
	ArrayList<SecurityObjective> toeSecurityObjectives = new ArrayList<SecurityObjective>();
	ArrayList<SecurityObjective> EnvironementSecurityObjective = new ArrayList<SecurityObjective>();
	ArrayList<TermAndDefinition> TermAndDefinitionList = new ArrayList<TermAndDefinition> ();
	ArrayList<OSP> listOfOSP = new ArrayList<OSP>();
	
	CoverageTable assetAgainstThreat = new CoverageTable();
	
	
	
	
	public CoverageTable getAssetAgainstThreatCoverageTable() {
		return assetAgainstThreat;
	}
	
	





	public void addCoverage(CCAsset coveredObject,
			Threat coveringObject) {
		assetAgainstThreat.addCoverage(coveredObject, coveringObject);
	}


	


	public ArrayList<OSP> getListOfOSP(){
		ArrayList<OSP> result = new ArrayList<OSP>();
		result.addAll(listOfOSP);
		return result;
	}
	
	
	public void addTermAndDefinition(String key, String definition){
		TermAndDefinition newTerm = new TermAndDefinition(key,definition);
		this.TermAndDefinitionList.add(newTerm);
		
	}
	
	TOEEnvironment toeEnvironment = new TOEEnvironment();



	public TOEEnvironment getToeEnvironment() {
		return toeEnvironment;
	}

	public AssuranceLevel getAssuranceLevel() {
		return assuranceLevel;
	}

	

	
	






	public ArrayList<CCAsset> getRoles() {
		return toeEnvironment.getRoles();
	}
	



	public ArrayList<SecurityObjective> getEnvironementSecurityObjective() {
		return EnvironementSecurityObjective;
	}

	
	public ArrayList<SecurityObjective> getSecurityObjective() {
		return toeSecurityObjectives;
	}

	

	@Override
	public CCAsset searchBienSupport(String id) throws Exception {
		try {
		  return (CCAsset) super.searchBienSupport(id);}
		catch (Exception e){
		  for (RiskAnalysisObject asset: this.toeEnvironment.getRoles()){
			  if (asset.getId().contentEquals(id)){
				  return (CCAsset) asset;
			  }  
			  
			
		  
		}throw e;}
		  
	}

	public String getToeUsage() {
		return data.toeUsage;
	}

	public void setToeUsage(String toeUsage) {
		this.data.toeUsage = toeUsage;
	}

	public String getType() {
		return data.type;
	}

	public void setType(String type) {
		this.data.type = type;
	}

	public String getScope() {
		return data.scope;
	}

	public void setScope(String scope) {
		this.data.scope = scope;
	}

	
	
	
	

	public Identification getIdentification() {
		return identification;
	}

	

	public ArrayList<TermAndDefinition> getTermAndDefinitionList() {
		ArrayList<TermAndDefinition> result = new ArrayList<TermAndDefinition>();
		result.addAll(this.TermAndDefinitionList);
		return result;
		
	}


	public void addOSP(OSP osp) {
		this.listOfOSP.add(osp);
		
	}


	public ArrayList<CCAsset> getListOfCoveredAsset(Threat t) {
		ArrayList<CCAsset> result = new ArrayList<CCAsset>();
		
		for (RiskAnalysisObject object: this.assetAgainstThreat.getListOfCoveredObject(t)){
			result.add(((CCAsset) object));
		}
	
		return result;
	}





	public void addRole(CCAsset asset) {
		this.toeEnvironment.addRole(asset);
		
	}



	
	
	
	
	

}
