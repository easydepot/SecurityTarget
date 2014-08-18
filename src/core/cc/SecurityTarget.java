package core.cc;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import core.printing.BasicElement;
import core.printing.list.ListItem;
import core.sfr.SFR;
import core.sfr.SFRElement;
import core.sfr.SFRPart;
import core.RiskAnalysis;
import core.RiskAnalysisObject;
import core.asset.Asset;
import core.asset.CCAsset;
import core.asset.CCObject;
import core.asset.Functionnality;
import core.asset.Operation;
import core.asset.Subject;
import core.catalog.CCFileParser;
import core.coverageTable.CoverageTable;
import core.securityObjective.SecurityObjective;
import core.securityObjective.SecurityObjectiveList;
import core.threat.Threat;

public class SecurityTarget extends RiskAnalysis {
	
	boolean isProtectionProfile = false;
	Identification identification = new Identification();
	AssuranceLevel assuranceLevel = new AssuranceLevel();
	TOEOverview data = new TOEOverview();
	
	
	
	SecurityObjectiveList toeSecurityObjectives = new SecurityObjectiveList();
	ArrayList<SecurityObjective> EnvironementSecurityObjective = new ArrayList<SecurityObjective>();
	ArrayList<TermAndDefinition> TermAndDefinitionList = new ArrayList<TermAndDefinition> ();
	ArrayList<OSP> listOfOSP = new ArrayList<OSP>();
	ArrayList<Operation> listOfOperation = new ArrayList<Operation>();
	ArrayList<CCObject> listOfObject = new ArrayList<CCObject>();

	
	public boolean add(CCObject arg0) {
		return listOfObject.add(arg0);
	}

	public ArrayList<CCObject> getListOfObject() {
		return listOfObject;
	}
	
	public CCObject findCCObjectByIdent(String id) throws Exception{
		for (CCObject c : this.getListOfObject()){
			if (c.getId().contentEquals(id)){
				return c;
			}
		}
		throw new Exception("Error Object not found :ident "+id);
		
	}

	public boolean add(Operation arg0) {
		return listOfOperation.add(arg0);
	}

	public ArrayList<Operation> getListOfOperation() {
		return listOfOperation;
	}

	CoverageTable assetAgainstThreat = new CoverageTable();
	CoverageTable threatAgainstObjectives = new CoverageTable();
	CoverageTable OSPAgainstObjectives = new CoverageTable();
	CoverageTable HypothesisAgainstEnvironmentObjectives = new CoverageTable();
	
	public CoverageTable getHypothesisAgainstEnvironmentObjectives() {
		return HypothesisAgainstEnvironmentObjectives;
	}

	public CoverageTable getOSPAgainstObjectives() {
		return OSPAgainstObjectives;
	}

	public void addCoverage(OSP t, SecurityObjective o){
		this.OSPAgainstObjectives.addCoverage(t, o);
	}
	
	public void addCoverage(Hypothesis t, SecurityObjective o){
		this.HypothesisAgainstEnvironmentObjectives.addCoverage(t, o);
	}
	
	public void addCoverage(Threat t, SecurityObjective o){
		this.threatAgainstObjectives.addCoverage(t, o);
	}
	
	public CoverageTable getThreatAgainstObjectives() {
		return threatAgainstObjectives;
	}







	






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

	
	public SecurityObjectiveList getSecurityObjective() {
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









   private static RiskAnalysisObject searchRiskAnalysisObjectByID(String id,ArrayList<RiskAnalysisObject> list) throws Exception{
	   for (RiskAnalysisObject t: list){
			if (t.getId().contentEquals(id)){
				return t;
			}
		}
	   throw new Exception("");
   }


   private static ArrayList<RiskAnalysisObject> convertThreat( ArrayList<Threat> list){
	 ArrayList<RiskAnalysisObject> l = new ArrayList<RiskAnalysisObject>();
	 for (Threat e: list){
		 l.add(e);
	 }
	return l;
	   
   }
   
   private static ArrayList<RiskAnalysisObject> convertSecurityObjective( ArrayList<SecurityObjective> list){
		 ArrayList<RiskAnalysisObject> l = new ArrayList<RiskAnalysisObject>();
		 for (SecurityObjective e: list){
			 l.add(e);
		 }
		return l;
		   
	   }
   
   private static ArrayList<RiskAnalysisObject> convertToOSPList( ArrayList<OSP> list){
		 ArrayList<RiskAnalysisObject> l = new ArrayList<RiskAnalysisObject>();
		 for (OSP e: list){
			 l.add(e);
		 }
		return l;
		   
	   }

	public Threat searchThreatByID(String threatID) throws Exception {
		
		try {
			return (Threat) searchRiskAnalysisObjectByID(threatID,convertThreat(this.getListeMenaceBienEssentiel()));
		} catch (Exception e) {
			throw new Exception("Threat " + threatID + "not found");
		}
		
		
		
	}

public SecurityObjective searchTOEObjectiveByID(String securityObjectiveID) throws Exception {
		
		try {
			return (SecurityObjective) searchRiskAnalysisObjectByID(securityObjectiveID,convertSecurityObjective(this.getSecurityObjective()));
		} catch (Exception e) {
			throw new Exception("SecurityObjective " + securityObjectiveID + "not found");
		}
		
		
		
	}

public OSP searchOspByID(String ospID) throws Exception {
	
	try {
		return (OSP) searchRiskAnalysisObjectByID(ospID,convertToOSPList(this.getListOfOSP()));
	} catch (Exception e) {
		throw new Exception("OSP  " + ospID + "not found");
	}
	
	
	
}

public Hypothesis searchHypothesisByID(String hypothesisID) {
	for (Hypothesis h: this.toeEnvironment.getListHypothesis()){
		if (hypothesisID.contentEquals(h.getId())){
			return h;
		}
	}
	return null;
}

public SecurityObjective searchEnvironmementObjectiveByID(String environementID) {
	for (SecurityObjective o: this.getEnvironementSecurityObjective()){
		if (environementID.contentEquals(o.getId())){
			return o;
		}
	}
	return null;
}

/*
 * SFRPArt
 */

public void setAssignementContent(int j, String content) {
	mySFR.getListOfAssignement().get(j).setContent(content);
}

public void ignoreAssignment(int i) {
	mySFR.getListOfAssignement().get(i).ignoreAssignement();
}



public SecurityTarget() throws SAXException, IOException, ParserConfigurationException {
	super();
	CCFileParser.parse();


}

public void selectSFR(String ident, String instance)
		throws Exception {
	mySFR = listOfSFR.findSFRbyID(ident, instance);
}



public void addSFRInstance(String id) throws Exception {
	listOfSFR.addSFRInstance(id);		
}



public void addSFRInstance(String id, String instance_name)
		throws Exception {
	listOfSFR.addSFRInstance(id,instance_name);
}

 SFRPart listOfSFR = new SFRPart();




 SFR mySFR;

public SFRPart getSFRPart() {
	return this.listOfSFR;
}

public void setAssignementContent(int j, BasicElement content) {
	mySFR.getListOfAssignement().get(j).setContent(content);	
}

public void selectSFR(String identifier) throws Exception {
	String[] s = identifier.split("/");
	this.selectSFR(s[0], s[1]);
	
}

public ArrayList<Subject> getListOfSubject() {
	
	ArrayList<Subject> result = new ArrayList<Subject>();
	for (Asset e: this.getListeBienSupport()){
		if (((CCAsset) e).getType().equals("Subject")){
			
		}
	}
	return result;
}

public void setRefinementToSFRElement(int i, String refinement) {
	SFRElement e = this.mySFR.getListOfSFRElement().get(i-1);
	e.setRefinement(refinement);
	
}

public void setRefinementToSFRElement(int i, BasicElement refinement) {
	SFRElement e = this.mySFR.getListOfSFRElement().get(i-1);
	e.setRefinement(refinement);
	
}

	
	
	
	
	

}
