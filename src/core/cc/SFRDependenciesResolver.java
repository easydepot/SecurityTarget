package core.cc;

import core.sfr.SFR;
import core.sfr.SFRDependency;
import core.sfr.SFRList;

public class SFRDependenciesResolver {
	
	SecurityTarget st;
	SFR currentSFR;
	SFRDependency currentDependency;

	public SFRDependenciesResolver(SecurityTarget st) {
		super();
		this.st = st;
	}
	
	 

	public void selectSFR(String ident) throws Exception {
		st.selectSFR(ident);
		currentSFR = st.mySFR;
	}
	
	public void selectDependency(int i) throws Exception {		
		currentDependency = currentSFR.getListOfDependancies().get(i);
	}
	
	public void coverDependency(String ident) throws Exception{
		st.selectSFR(ident);
		SFR tmp = st.mySFR;
		currentDependency.coverBySFR(tmp);
	}

	private SFRDependency current_dependency;
	private SFRList listOfTOESFR;


	public void processDependencies() throws Exception {
	    listOfTOESFR = st.getSFRPart().getListOfTOESFR();
	    
	   for (SFR sfr :listOfTOESFR){
		   this.currentSFR = sfr;
		    processAutomaticDependencyResolverForTheGivenSFR(sfr);
	   }
	
	}



	private void processAutomaticDependencyResolverForTheGivenSFR(SFR sfr)
			throws Exception {
		for (SFRDependency dep: sfr.getListOfDependancies()){
			this.current_dependency = dep;
		  searchMatchingSFRsAndApplyItToCurrentDependency();	
		}
		
	}



	private void searchMatchingSFRsAndApplyItToCurrentDependency()
			throws Exception {
		for (SFR covering_sfr :listOfTOESFR){
		  tryToCoverTheCurrentDependencyByTheSFR(covering_sfr);
		}
	}



	private void tryToCoverTheCurrentDependencyByTheSFR(SFR sfr_covering_candidate)
			throws Exception {
		if (current_dependency.canBeCoveredBy(sfr_covering_candidate))
		{
			if (this.currentSFR.getInstance()==null){
			  current_dependency.coverBySFR(sfr_covering_candidate);
			} else {
			  if (sfr_covering_candidate.getInstance()==null || this.currentSFR.getInstance().contentEquals(sfr_covering_candidate.getInstance())){
				  current_dependency.coverBySFR(sfr_covering_candidate);
			  }
			}
		}
	}
	
	
	

}
