package core.printer.securityTarget;

import java.util.ArrayList;

import core.RiskAnalysisObject;
import core.cc.SecurityTarget;
import core.printer.CoverageTablePrinter;
import core.printing.BasicElementWithChildren;
import core.printing.Section;
import core.printing.list.ListItem;
import core.printing.table.TablePrinter;
import core.securityObjective.SecurityObjective;
import core.sfr.SFR;
import core.sfr.SFRDependency;
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
	
	result.addSection("SFR Dependencies Rationale");
	result.addText("The following table displays the SFR dependencies");
	TablePrinter table = new TablePrinter();
	table.addHeader("SFR");
	table.addHeader("Required Dependency");
	table.addHeader("Dependency Satisfaction");
	for (SFR sfr: st.getSFRPart().getListOfTOESFR()){
		table.newline();
		table.add(sfr.getFullIdent());
		ListItem l = new ListItem();
		for (SFRDependency dep: sfr.getListOfDependancies()){
			l.addItem(dep.getFamily());	
		}
		table.add(l);
		ListItem l2 = new ListItem();
		for (SFRDependency dep: sfr.getListOfDependancies()){
			for (SFR covering_sfr: dep.getCoveringSFRs()){
				l2.addItem(covering_sfr.getFullIdent());
			}
		}
	
		  table.add(l2);
		

		
	}
	result.add(table);
	
	addSecurityObjevtiveVsSFRRational();
	
	
}

private void addSecurityObjevtiveVsSFRRational() throws Exception {
	result.addSection("SFR vs TOE SEcurity Objective Rationale");
	result.addText("The following table displays how the SFRs cover the TOE Security Objectives.");
	TablePrinter objective_coverage_table = new TablePrinter();
	result.add(objective_coverage_table);
	objective_coverage_table.addHeader("SFR");
	objective_coverage_table.addHeader("Objective");
	for (SFR sfr: this.st.getSFRPart().getListOfTOESFR()){
		objective_coverage_table.newline();
		objective_coverage_table.add(sfr.getFullIdent());
		ListItem l = new ListItem();
		objective_coverage_table.add(l);
		for (SecurityObjective obj:sfr.getListOfCoveredObjectives()){
			l.addItem(obj.getFullId());
		}
	}
	result.addText("The following table displays how the TOE Security Objectives are covered by the SFRs.");
	TablePrinter sfr_coverage_table = new TablePrinter();
	result.add(sfr_coverage_table);
	sfr_coverage_table.addHeader("Objective");
	sfr_coverage_table.addHeader("SFR");
	for (SecurityObjective obj:st.getSecurityObjective()){
		sfr_coverage_table.newline();
		sfr_coverage_table.addCell(obj.getFullId());
		ListItem l = new ListItem();
		sfr_coverage_table.add(l);
		for (SFR sfr: st.getSFRPart().getListOfSFRcovering(obj)){
			l.addItem(sfr.getFullIdent());
		}
	}
	
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
