package core.printer.securityTarget;

import java.util.ArrayList;















import core.RiskAnalysisObject;
import core.asset.CCAsset;
import core.cc.SecurityTarget;
import core.cc.TermAndDefinition;
import core.cc.attributes.SecurityAttribute;
import core.printer.CoverageTablePrinter;
import core.printer.RiskAnalysisPrinter;
import core.printing.BasicElement;
import core.printing.BasicElementWithChildren;
import core.printing.Doc;
import core.printing.Section;
import core.printing.Sequence;
import core.printing.SimpleText;
import core.printing.list.ListItem;
import core.printing.table.TablePrinter;
import core.securityObjective.SecurityObjective;
import core.threat.Threat;

public class SecurityTargetPrinter extends RiskAnalysisPrinter {

	public SecurityTargetPrinter(SecurityTarget project) {
		super(project);
	}
	
	
	

	protected Doc createDocWithThrowException() throws Exception {
		addTitlePage(result, this.getProject());
		result.addTableOfContents();
		addContent();
		return result;
	}




	private void addContent() throws Exception {
		addIntroduction();	
		addConformanceClaim();
		
		getSecurityProblemDefinitionSection();
		addSecurityObjectiveSection();
		//addSecurityRequirementSection();
		addRationalSection();
	}




	

	private void addRationalSection() throws Exception {
		RationalePrinter printer = new RationalePrinter(this.getProject());
		result.add(printer.addRationalSection());
		
	}




	private void getSecurityProblemDefinitionSection() throws Exception {
       SecurityProblemePrinter stprinter = new SecurityProblemePrinter(this.getProject(),this.result);
       stprinter.getSecurityProblemDefinitionSection();
       
	}




	private void addSecurityRequirementSection() throws Exception {
		result.addSection("Security requirements");
		result.addText("This section describes the operations and requirements that a TOE shall fulfil in order to be compliant to this PP");
		result.addSection("Introduction");
		result.addSection("Subjects Objects and security attributes");
		addSubjectAttributesSection();
		
		
		addObjectAttributesSection();
		result.pop();
		result.pop();
		result.pop();
		
	}




	private void addSubjectAttributesSection() throws Exception {
		result.addSection("Subjects");
		result.addText("The following table describes the list of subjects and attributes.");
		result.newLine();
		BasicElement table = createSubjectTable();
		result.add(table);
		result.pop();
		
		
	}
	private void addObjectAttributesSection() throws Exception {
		result.addSection("Objects/Information");
		result.addText("The following table describes the list of subjects and attributes.");
		result.newLine();
		BasicElement table = createObjectTable();
		result.add(table);
		result.pop();
	}



	private BasicElement createSubjectTable() throws Exception {
		
		BasicElement table = createListOfAttributeTable(this.getProject().getRoles(), "S",
				"Subject");
		
		
		
		return table;
	}
	
	private BasicElement createObjectTable() throws Exception {
		
		BasicElement table = createListOfAttributeTable(getListOfCCAssetofTheTOE(), "O",
				"Object");
		
		
		
		return table;
	}




	private BasicElement createListOfAttributeTable(
			ArrayList<CCAsset> listOfAsset, String qualifier, String firstColumn)
			throws Exception {
		TablePrinter table = new TablePrinter();
		addTableHeader(table,firstColumn);
		addContent(listOfAsset, qualifier, table);
		return table;
	}




	private ArrayList<CCAsset> getListOfCCAssetofTheTOE() {
		ArrayList<CCAsset> listOfAsset = new ArrayList<CCAsset>();
		for (RiskAnalysisObject asset: this.getProject().getSystemDescription().getAssetList()){
			listOfAsset.add((CCAsset) asset);
		}
		return listOfAsset;
	}

	



	private void addContent(ArrayList<CCAsset> listOfAsset, String qualifier,
			TablePrinter table) throws Exception {
		for (CCAsset subject: listOfAsset){
			addAssetLines(qualifier, table, subject);
		}
	}




	static protected void addAssetLines(String qualifier, TablePrinter table,
			CCAsset asset) throws Exception {
		table.newline();	
		table.addCell(qualifier + "." + asset.getId() );
		addSecurityAttributesColumns(table, asset);
		
	}




	private static void addSecurityAttributesColumns(TablePrinter table,
			CCAsset asset) throws Exception {
		if (asset.getListOfSecurityAttribute().isEmpty()){
			completeLineWithNonApplicable(table);
		} else {
			completeLinesWithAttributeValues(table, asset);
		}
	}




	private static void completeLinesWithAttributeValues(TablePrinter table,
			CCAsset asset) throws Exception {
		SecurityAttribute firstAttribute = getFirstAttribute(asset);
		fillAttributesCells(table, firstAttribute);
		for (int i = 1; i <asset.getListOfSecurityAttribute().size();i++){
			table.newline();
			table.addCell("");
			SecurityAttribute currentAttribute = asset.getListOfSecurityAttribute().get(i);
			fillAttributesCells(table, currentAttribute);
		}
	}




	private static void fillAttributesCells(TablePrinter table,
			SecurityAttribute firstAttribute) throws Exception {
		table.addCell("AT." + firstAttribute.getId());
		table.addCell(getListOfPossibleValues(firstAttribute));
		table.addCell(firstAttribute.getInitialValue());
	}




	private static SecurityAttribute getFirstAttribute(CCAsset asset) {
		ArrayList<SecurityAttribute> listOfAttribute = asset.getListOfSecurityAttribute();
		SecurityAttribute firstAttribute = listOfAttribute.get(0);
		return firstAttribute;
	}




	private static String getListOfPossibleValues(SecurityAttribute firstAttribute) {
		String listOfPossibleValue ="";
		for (String possiblevalue: firstAttribute.getListOfPossibleValue()){
		
		  listOfPossibleValue+= possiblevalue +", ";
		 
		}
		listOfPossibleValue = listOfPossibleValue.substring(0, listOfPossibleValue.length()-2);
		return listOfPossibleValue;
	}




	private static void completeLineWithNonApplicable(TablePrinter table)
			throws Exception {
		table.addCell("n/A");
		table.addCell("n/A");
		table.addCell("n/A");
	}




	static protected void addTableHeader(TablePrinter table,String firstColumn) throws Exception {
		table.addHeader(firstColumn);
		table.addHeader("Security Attribute");
		table.addHeader("Possible Values");
		table.addHeader("Initial Value");
	}




	private void addSecurityObjectiveSection() throws Exception {
		result.addSection("Security Objectives");
		addSectionTOESecurityObjective();
		addSectionEnvironmentSecurityObjective();
		/*result.addSection("Rationale for Security Objective	");
		result.addText("TODO");
		result.pop();*/
		result.addSection("Extended component definition");
		result.addText("There is none.");
		result.pop();
		result.pop();
		
	}


    

	private void addSectionTOESecurityObjective() throws Exception {
		result.addSection("Security Objectives for the TOE");
		for (SecurityObjective secobj: this.getProject().getSecurityObjective()){
			printTOESecurityObjectiveSection(secobj);
		}
		result.pop();
	}




	private void printTOESecurityObjectiveSection(SecurityObjective secobj)
			throws Exception {
		result.addSection("OT." + secobj.getId());
		result.add(secobj.getDescription());
		result.add(printListOfSubobjective(secobj));

		ArrayList<RiskAnalysisObject> listOfCoveredThreat = this.getProject().getThreatAgainstObjectives().getListOfCoveredObject(secobj);
		if (!listOfCoveredThreat.isEmpty()){
			result.newLine();
			result.addText("Covered Threat:");	
		  ListItem l = new ListItem();
		  result.add(l);
		  for (RiskAnalysisObject t:  listOfCoveredThreat){
			  l.addItem("T." + t.getId() +" ");
			  
		  }
		}
		
		ArrayList<RiskAnalysisObject> listOfCoveredOSP = this.getProject().getOSPAgainstObjectives().getListOfCoveredObject(secobj);
		if (!listOfCoveredOSP.isEmpty()){
			result.newLine();
			result.addText("Covered OSP:");
		  ListItem l = new ListItem();
		  result.add(l);
		  for (RiskAnalysisObject t:  listOfCoveredOSP){
			  l.addItem("OSP." + t.getId() +" ");
			  
		  }
		}
		
		result.pop();
	}




	
	



	protected static ListItem getListPrintStructure(SecurityObjective secobj) throws Exception {
		ListItem result = new ListItem();
		for (SecurityObjective sub: secobj.getListSubObjective()){
			result.addItem(SecurityTargetPrinter.printSubObjectiveLine(sub));
		}
		return result;
	}
	
	protected static BasicElement printListOfSubobjective(SecurityObjective secobj) throws Exception {
		Sequence objectiveList = new Sequence();
		if (secobj.getListSubObjective().isEmpty()){
			return objectiveList;
		} else{
			
			objectiveList.addText("This objective covers the following subobjectives:");
			ListItem listItemOfSubobjective = SecurityTargetPrinter.getListPrintStructure(secobj);
			
			objectiveList.add(listItemOfSubobjective);
			return objectiveList;
		}
		
	}
	
	
	
	private void addSectionEnvironmentSecurityObjective() throws Exception {
		result.addSection("Security Objectives for the Environment of the TOE");
		for (SecurityObjective secobj: this.getProject().getEnvironementSecurityObjective()){
			result.addSection("OE." + secobj.getId());
			result.add(secobj.getDescription());
			ArrayList<RiskAnalysisObject> listOfCoveredHypothesis = this.getProject().getHypothesisAgainstEnvironmentObjectives().getListOfCoveredObject(secobj);
			if (!listOfCoveredHypothesis.isEmpty()){
				result.newLine();
				result.addText("Covered Hypothesis:");	
			  ListItem l = new ListItem();
			  result.add(l);
			  for (RiskAnalysisObject t:  listOfCoveredHypothesis){
				  l.addItem("A." + t.getId() +" ");
				  
			  }
			}
			
			result.pop();
		}
		result.pop();
	}



	

	



	



	


	




	private void addConformanceClaim() throws Exception {
		result.addSection("Conformance Claim");
		result.addSection("CC Conformance Claim");
		// TODO add CC reference;
		result.addText("This Protection Profile (PP) is CC Part 2 extended and CC Part 3 conformant and written according to the" +
				"	Common Criteria version " + this.getProject().getIdentification().getCc_version() + this.getProject().getIdentification().getCc_revision() +".");
		

		result.pop();
		result.addSection("PP Claim");
		result.addText("This PP does not claim conformance to any other Protection Profile");
		result.pop();
		addPackageClaimSection(result);
		result.addSection("Conformance Rationale");
		result.addText("Since this PP is not claiming conformance to any other protection profile, no rationale is necessary here.");
	result.pop();
		result.addSection("Conformance Statement");
		result.addText("The conformance required by this PP is the demonstrable-PP conformance. This will facilitate conformance " +
				"claim to both this PP and other PP for Security Target (ST) authors.");
		
		result.pop();
		
		
		result.pop();
	}




	private void addPackageClaimSection(Doc result) throws Exception {
		result.addSection("Package Claim");
		result.addText("The evaluation assurance level for this PP is " + this.getProject().getAssuranceLevel().getLevel() + " augmented with the assurance components:");
		result.add(getAugmentationListItem());
		result.newLine();
		result.addText("Editor’s note: different countries currently use different levels of EAL. So national bodies are requested to explicitly comment on this choice.").setItalic(true);
		result.pop();
	}




	public ListItem getAugmentationListItem() throws Exception {
		ListItem listAugmentation = new ListItem();
		for (int i = 0; i < this.getProject().getAssuranceLevel().numberOfAugmentation();i++){
			listAugmentation.addItem(this.getProject().getAssuranceLevel().getAugmentation(i));
		}
		return listAugmentation;
	}

	private void addTitlePage(Doc result, SecurityTarget st) {
		result.setTitle("Profil de Protection");
		result.setSubtitle(st.getIdentification().getTitle());
	}

	private void addIntroduction() throws Exception {
		result.addSection("Introduction");
		result.addSection("Scope");
		result.addText(this.getProject().getScope());
		result.pop();
		result.addSection("Normative Reference");
		result.pop();
		result.addSection("Terms and definitions");
		ListItem l = SecurityTargetPrinter.printListOfTermAndDefinition(this.getProject().getTermAndDefinitionList());
		result.add(l);
		result.pop();
		result.addSection("Symbols and abbreviations");
		result.pop();
		addToeOverviewSection();
	}




	private void addToeOverviewSection() throws Exception {
		result.addSection("TOE overview");
		result.addSection("TOE Type");
		result.addText(this.getProject().getType());
		result.pop();
		result.addSection("TOE Usage");
		result.addText(this.getProject().getToeUsage());
		result.pop();
		addToeEnvironementSection();
		addRoleSection();
		addToeOperationSection();
		result.addSection("TOE Environment Operations");
		result.pop();
		result.pop();
		result.pop();
	}




	private void addToeOperationSection() throws Exception {
		result.addSection("TOE operations");
		result.addSection("Introduction");
		result.addText("This section describes operations that are performed in the TOE or in its environment. The Operations are:");
		result.add(super.convertListOfFunctionalityInListItem());
		result.pop();
		addEachToeOperationDescription();
	
		result.pop();
	}




	private void addEachToeOperationDescription() {
		try {
		addEachToeOperationDescriptionWithException(); 
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}




	private void addEachToeOperationDescriptionWithException() throws Exception {
		for (int i = 0; i < this.getProject().getNumberOfBienEssentiel();i++){
			result.addSection(((SimpleText)this.getProject().getBienEssentiel(i).getDescription()).getText());
			
				result.pop();
			}
	}



	private ListItem listOfCCAsset2ListPrint(ArrayList<CCAsset> assets ) throws Exception{
		ListItem l = new ListItem();
		for (CCAsset entity:assets){
			
			l.addItem(entity.getDescription());
		}
		return l;
		
	}

	private void addToeEnvironementSection() throws Exception {
		result.addSection("TOE Environment");
		result.addSection("TOE Environment Overview");
		result.pop();
		
		
		result.addSection("External Entities");
		result.addText("The following entities are external entities of the TOE. They are connected to the TOE.");
		result.add(listOfCCAsset2ListPrint(this.getProject().getToeEnvironment().getDirectExternalEntities()));
		
		result.pop();
		result.addSection("Other Entities");
		result.addText("The following entities are not directly external entities of the TOE, because they are not connected to the TOE, but to external entities of the TOE.");
		result.add(listOfCCAsset2ListPrint(this.getProject().getToeEnvironment().getIndirectExternalEntities()));
		
		result.pop();
		result.pop();
		
		
	}




	private void addRoleSection() throws Exception {
		result.addSection("Roles");
		result.addText("The use of the TOE in its operational environment implis directly or indirectly the following roles:");
		for (CCAsset role: this.getProject().getRoles()){
			result.addSection(role.getName()+"(S." + role.getId()+")");
			result.pop();
		}
		
		result.pop();
	}




	@Override
	public SecurityTarget getProject() {
		return (SecurityTarget) super.getProject();
	}




	protected static BasicElement printSubObjectiveLine(SecurityObjective secobj) throws Exception{
		
		return secobj.getDescription();
	}




	public static ListItem printListOfTermAndDefinition(ArrayList<TermAndDefinition> arrayList) throws Exception {
		ListItem l = new ListItem();
		for (TermAndDefinition term: arrayList){
			l.addItem(SecurityTargetPrinter.printLineTermAndDefinition(term));
		}
		return l;
	}




	public static BasicElementWithChildren printLineTermAndDefinition(TermAndDefinition def) throws Exception {
		Sequence s = new Sequence();
		s.addText(def.getTerm()).setBold(true);
		s.addText(": ");
		s.addText(def.getDefinition());
		return s;
	}
	
	
	
	

}
