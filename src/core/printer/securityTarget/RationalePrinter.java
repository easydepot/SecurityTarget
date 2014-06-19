package core.printer.securityTarget;

import java.util.ArrayList;

import core.RiskAnalysisObject;
import core.cc.SecurityTarget;
import core.printer.CoverageTablePrinter;
import core.printing.BasicElementWithChildren;
import core.printing.Section;
import core.threat.Threat;

public class RationalePrinter {
  SecurityTarget st;

public RationalePrinter(SecurityTarget st) {
	super();
	this.st = st;
}

Section result = new Section("Rationale");

public  BasicElementWithChildren addRationalSection() throws Exception {
	
	addThreatAgainstObjectiveRationale();
	return result;
	
}

private void addThreatAgainstObjectiveRationale() throws Exception {
	result.addSection("Rationale between Threats and Objective");
	ArrayList<RiskAnalysisObject> leftcolumnedVector = convertListOfTreatToListOfRiskAnalysisObject(st.getListeMenaceBienEssentiel());
	CoverageTablePrinter printer = new CoverageTablePrinter(st.getAssetAgainstThreatCoverageTable(),leftcolumnedVector);
	result.add(printer.generateTable());
	
	
}

private ArrayList<RiskAnalysisObject> convertListOfTreatToListOfRiskAnalysisObject(
		ArrayList<Threat> listeMenaceBienEssentiel) {
	ArrayList<RiskAnalysisObject> returnedList = new ArrayList<RiskAnalysisObject>();
	for (Threat asset: listeMenaceBienEssentiel){
		returnedList.add(asset);
	}
	return returnedList;
}
  
}
