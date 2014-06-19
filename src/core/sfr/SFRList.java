package core.sfr;

import java.util.ArrayList;

public class SFRList extends ArrayList<SFR>{

	
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

}
