package core.sfr;

import static org.junit.Assert.*;

import org.junit.Test;

import core.securityObjective.SecurityObjective;
import core.securityObjective.SecurityObjectiveList;

public class TestSFRObjectiveResolver {
	
	
	SFRObjectiveResolver sut;

	@Test
	public void test() {
		
		SFRPart sfrpart = new SFRPart();
		SecurityObjectiveList objlist = new SecurityObjectiveList();
		objlist.add(new SecurityObjective("obj1"));
		
		SFRObjectiveResolver sut = new SFRObjectiveResolver(sfrpart, objlist);
		assertFalse(sut.getListOfUncoveredSecurityObjective().isEmpty());
		
		
	}

}
