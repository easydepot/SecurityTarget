package core.sfr;

public class SFRDependency {
	
	String sfr_family;
	SFRList sfrlist = new SFRList();

	public SFRDependency(String sfr_family) {
		this.sfr_family = sfr_family;
	}
	

	public String getFamily() {
		return sfr_family;
	}

	public boolean isCovered() {
		
		return !this.sfrlist.isEmpty();
	}
	
	public boolean canBeCoveredBy(SFR sfr){
		String family_in_the_SFR = sfr.getIdent();
		if(family_in_the_SFR.contentEquals(sfr_family.toUpperCase())){
			return true;
		} else {
			return false;
		}
	}

	public void coverBySFR(SFR sfr) throws Exception {
		String family_in_the_SFR = sfr.getIdent();
		if (this.sfrlist.contains(sfr)){
			return;
		}
		if (canBeCoveredBy(sfr)){
		  sfrlist.add(sfr);
		}
		else {
			throw new Exception("Bad dependancy coverage " + family_in_the_SFR + " instead of " + sfr_family);
		}
		
	}

	public SFRList getCoveringSFRs() {
		return this.sfrlist;
	}

}
