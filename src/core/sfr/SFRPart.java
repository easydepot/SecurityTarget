package core.sfr;

import java.util.ArrayList;

import core.securityObjective.SecurityObjective;

public class SFRPart {
	SFRList listOfTOESFR = new SFRList();

	public ArrayList<SFRElementAssignement> getListOfAssignement() {
		return listOfTOESFR.getListOfAssignement();
	}

	public SFRList getListOfTOESFR() {
		return listOfTOESFR;
	}
	
	public  void addSFRInstance(String id) throws Exception {
		if (this.contains(id)){throw new Exception("TOE contains already an instance of " + id);}
		listOfTOESFR.add(SFR.getNewSFRInstance(id));		
	}
	
	public boolean contains(String sfrIdent){
		for (SFR sfr:this.listOfTOESFR){
			if (sfr.getIdent().contentEquals(sfrIdent.toUpperCase())){
				return true;
			}}
		return false;
	}
	
	



	public  void addSFRInstance(String id, String instance_name)
			throws Exception {
		listOfTOESFR.add(SFR.getNewSFRInstance(id,instance_name));
	}
	
	public SFR findSFRbyID(String fullident) throws Exception {
		String[] s = fullident.split("/");
		if (s.length == 1){
			return findSFRbyID(s[0],null);
		} else {
		  return findSFRbyID(s[0],s[1]);
		}
	}
	
	public SFRList getListOnNoneAttachedSFR(){
		SFRList result = new SFRList();
		for (SFR sfr : this.getListOfTOESFR()){
			if (sfr.getListOfCoveredObjectives().isEmpty()){
				result.add(sfr);
			}
		}
		return result;
	}


	public SFR findSFRbyID(String ident, String instance) throws Exception {
		for (SFR sfr:this.listOfTOESFR){
			if (sfr.getIdent().contentEquals(ident.toUpperCase())){
				if (instance==null){
				  return sfr;
				} else {
					if (sfr.getInstance().contentEquals(instance)){
						return sfr;
					}
				}
				
			}
		}
		throw new Exception("SFR " + ident +"/" + instance+ " not found");
		
	}

	public SFRList getListOfSFRcovering(SecurityObjective obj) {
		SFRList result = new SFRList();
		for (SFR sfr: this.listOfTOESFR){
			if (sfr.isCovered(obj)){
				result.add(sfr);
			}
		}
		return result;
	}
	

}
