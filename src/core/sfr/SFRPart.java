package core.sfr;

import java.util.ArrayList;

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
			if (sfr.getIdent().contentEquals(sfrIdent)){
				return true;
			}}
		return false;
	}
	
	



	public  void addSFRInstance(String id, String instance_name)
			throws Exception {
		listOfTOESFR.add(SFR.getNewSFRInstance(id,instance_name));
	}

	public SFR findSFRbyID(String ident, String instance) throws Exception {
		for (SFR sfr:this.listOfTOESFR){
			if (sfr.getIdent().contentEquals(ident)){
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
	

}
