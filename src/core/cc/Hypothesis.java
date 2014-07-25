package core.cc;

import core.RiskAnalysisObject;

public class Hypothesis extends RiskAnalysisObject {

	public Hypothesis(String id) {
		super(id);
	
	}

	@Override
	public String getFullId() {
		// TODO Auto-generated method stub
		return "H."+id;
	}

}
