package core.sfr;

import core.securityObjective.SecurityObjective;
import core.securityObjective.SecurityObjectiveList;

public class SFRObjectiveResolver {
	SFRPart sfrpart;
	SecurityObjectiveList listObObjectives;
	
	public SFRObjectiveResolver(SFRPart sfrpart,
			SecurityObjectiveList listObObjectives) {
		super();
		this.sfrpart = sfrpart;
		this.listObObjectives = listObObjectives;
	}

	public SecurityObjectiveList getListOfUncoveredSecurityObjective() {
		SecurityObjectiveList result = new SecurityObjectiveList();
		for (SecurityObjective obj:this.listObObjectives){
			//if (!sfrpart.getListOfTOESFR().isCovering(obj)){
				result.add(obj);
			//}
			
		}
		return result;
		
	}
	

}
