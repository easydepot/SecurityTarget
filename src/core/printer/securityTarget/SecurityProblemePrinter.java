package core.printer.securityTarget;

import java.util.ArrayList;

import core.RiskAnalysisObject;
import core.asset.Asset;
import core.asset.CCAsset;
import core.cc.Hypothesis;
import core.cc.OSP;
import core.cc.SecurityTarget;
import core.printer.CoverageTablePrinter;
import core.printing.BasicElement;
import core.printing.BasicElementWithChildren;
import core.printing.Doc;
import core.printing.Section;
import core.printing.Sequence;
import core.printing.SimpleText;
import core.printing.WarningText;
import core.securityneed.SecurityNeed;
import core.threat.Threat;

public class SecurityProblemePrinter {
	
	public void getSecurityProblemDefinitionSection() throws Exception {
		result.addSection("Security problem definition");
		addAssetSection();
        addThreatSection();
		addSectionOSP();
		addHypothesisSection();
		
		result.pop();
	}

	protected void addAssetSection() throws Exception {
		result.addSection("Assets");
		addAssetsDescription();
		result.pop();
	}

	protected void addThreatSection() throws Exception {
		result.addSection("Threats");
        addThreatDescription();
        addThreatRationale();
		result.pop();
	}

	protected void addSectionOSP() throws Exception {
		result.addSection("Organizational security policies");
		for (OSP osp: this.getProject().getListOfOSP()){
			result.addSection("OSP." + osp.getId());
			System.out.println(osp.getId());
			result.add(osp.getDescription());
			
			
			result.pop();
		}
		result.pop();
	}

	protected void addHypothesisSection() throws Exception {
		result.addSection("Assumptions");
		for (Hypothesis h: this.getProject().getToeEnvironment().getListHypothesis()){
			result.addSection("H." + h.getId());
			result.add(h.getDescription());
			result.pop();
		}
		result.pop();
	}
	
	private  ArrayList<RiskAnalysisObject> convert(ArrayList<Asset> list){
		ArrayList<RiskAnalysisObject> returnedList = new ArrayList<RiskAnalysisObject>();
		for (Asset asset: list){
			returnedList.add(asset);
		}
		return returnedList;
	}


	private void addThreatRationale() throws Exception {
		result.addSection("Rationale between Threats and Asset");
		ArrayList<RiskAnalysisObject> leftcolumnedVector = convert(this.getProject().getSystemDescription().getAssetList());
		CoverageTablePrinter printer = new CoverageTablePrinter(this.getProject().getAssetAgainstThreatCoverageTable(),leftcolumnedVector);
		result.add(printer.generateTable());
		result.pop();
		
	}
	

	




	public SecurityProblemePrinter(SecurityTarget st, Doc result) {
		super();
		this.st = st;
		this.result = result;
	}

	SecurityTarget st;
	Doc result;
	
	private void addThreatDescription() throws Exception {
		   for (Threat threat: this.getProject().getListeMenaceBienEssentiel()){
			   if (threat.isEnabled()){
			   result.addSection(threat.getFullId());
			   result.add(threat.getDescription());
			   result.newLine();
			   result.newLine();
			   result.addText("This threat targets the following assets:");
			   if (st.getListOfCoveredAsset(threat).size()==0){
				   result.addText("TODO").setColor("red");
			   }
			   for (CCAsset object: st.getListOfCoveredAsset(threat)){
				   if (object.isEnabled()){
				     result.addText("A." + object.getId()+ " ");
				   }
			   }
			   result.newLine();
			   result.pop();
			   }
		   }
			
		}




		private SecurityTarget getProject() {
		return this.st;
	}




		protected void addAssetsDescription() throws Exception {
			for (Asset asset: this.getProject().getSystemDescription().getAssetList()){
				addAssetSection(asset);
			}
			
		}




		private void addAssetSection(Asset asset) throws Exception {
			addAssetSectionTitle(asset);
			result.add(asset.getDescription());
			result.newLine();
			result.newLine();

			addAssetProtection(asset);
			result.pop();
		}




		protected void addAssetProtection(Asset asset) throws Exception {
			result.add(getAssetProtectionLine((Asset)asset));
		}




		private void addAssetSectionTitle(Asset asset) throws Exception {
			result.addSection(getAssetTitleLine(asset));
		}
		protected String getAssetTitleLine(Asset asset) {
			return "A." + asset.getId()+": " + asset.getName();
		}
		
		protected BasicElement getAssetProtectionLine(Asset asset) throws Exception {
			Sequence result = new Sequence(); 

			if (asset.getListOfSecurityNeed().isEmpty()){
				return new WarningText("New security need defined for that asset");
			} else {
			  result.add(new SimpleText("Protection:"));
			}
			for (SecurityNeed need: asset.getListOfSecurityNeed()){
				result.add(new SimpleText(need.getValue()+" "));
			}

			return result;
		}


	
	

}
