package core.cc;

import core.RiskAnalysisObject;

public class OSP extends RiskAnalysisObject{

	public OSP(String id2) {
		super(id2);
		
	}

	public boolean hasDescription() {
		
		return this.getDescription()!=null;
	}

	@Override
	public String getFullId() {
		return "OSP."+this.getId();
	}
  
  
}
