package core.sfr;

import java.util.ArrayList;

public class SFRList extends ArrayList<SFR>{

	
	
	public boolean contains(SFR sfr) {
		for (SFR mysfr:this){
			if (sfr.getFullIdent().contentEquals(mysfr.getFullIdent())){
				return true;
			}
		}
		return false;
		
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -4802194418054821399L;

	public ArrayList<SFRElementAssignement> getListOfAssignement() {
		ArrayList<SFRElementAssignement> result = new ArrayList<SFRElementAssignement>();
		for (SFR sfr:this){
			result.addAll(sfr.getListOfAssignement());
		}
		return result;
	}

	public SFRDependencyList getListOfUncoveredDependency() {
		SFRDependencyList result = new SFRDependencyList();
		for (SFR sfr: this){
			result.addAll(sfr.getListOfUncoveredDependencies());
		}
		return result;
		
	}

}
