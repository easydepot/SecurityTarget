package core.sfr;

import java.util.ArrayList;

public class SFRDependencyList extends ArrayList<SFRDependency>{

	public SFRDependencyList getListOfUncoveredDependencies() {
		SFRDependencyList result = new SFRDependencyList();
		for (SFRDependency dep: this){
			if (!dep.isCovered()) {
				result.add(dep);
			}
		}
		return result;
		
	}

	public void addDependency(String dependency_family_ID) {
		this.add(new SFRDependency(dependency_family_ID));
		
	}

}
