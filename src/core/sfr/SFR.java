package core.sfr;


import java.util.ArrayList;

import core.printing.BasicElement;
import core.printing.SimpleText;
import core.securityObjective.SecurityObjective;
import core.securityObjective.SecurityObjectiveList;


public class SFR {
  String family;
  String ident;
  String name;
  String instance;
  SFRDependencyList dependencies = new SFRDependencyList();

  SecurityObjectiveList listOfCoveredSecurityObjectives = new SecurityObjectiveList();

  public static ArrayList<SFR> listOfSFR = new ArrayList<SFR>();
  ArrayList<SFRElement> listOfSFRElement = new ArrayList<SFRElement>();
  
  public SFRDependencyList getListOfUncoveredDependencies() {
		return this.dependencies.getListOfUncoveredDependencies();
		
	}
  
  
  
  
  public ArrayList<SFRElement> getListOfSFRElement() {
	  ArrayList<SFRElement> result = new ArrayList<SFRElement>();
	  for (SFRElement s:listOfSFRElement){
		  result.add(s);
	  }
	return result;
}

@Override
protected Object clone() throws CloneNotSupportedException {
	SFR cloned = new SFR(this.getIdent());
	cloned.setName(this.getName());
	cloned.family = this.family;
	for (SFRElement e: this.getListOfSFRElement()){
	  cloned.addSFRElement((SFRElement) e.clone());
	}
	cloned.dependencies = (SFRDependencyList) this.dependencies.clone();
	return cloned;
}

public static SFR getNewSFRInstance(String id,String instance_name) throws Exception{
	  SFR result = getNewSFRInstance(id);
	  result.setInstance(instance_name);
	  return result;
	
	}
  
public static SFR getNewSFRInstance(String id) throws Exception{
	for (SFR sfr:listOfSFR){
		if (sfr.getIdent().contentEquals(id.toUpperCase())){
			SFR result = (SFR) sfr.clone();
			
			return result;
		}
	}
	throw new Exception("SFR "+ id + " not found");
}

  public String getInstance() {
	return instance;
}

private void setInstance(String instance) {
	this.instance = instance;
}

public static SFR addNewSFR(String id){
	  SFR sfr = new SFR(id);
	  listOfSFR.add(sfr);
	  return sfr;
  }
  
public SFR(String family, String ident, String name) {
	super();
	this.family = family;
	this.ident = ident.toUpperCase();
	this.name = name;
}
public SFR(String id) {
	this.ident = id;
}
public String getFamily() {
	return family;
}
public void setFamily(String family) {
	this.family = family;
}
public String getFullIdent() {
	return getInstanciedIdent();
}

public String getInstanciedIdent() {
	if (this.instance!=null){
	  return this.getIdent() +"/"+ instance;
	}
	else {
		return this.getIdent();
	}
}
public void setIdent(String ident) {
	this.ident = ident;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getIdent() {
	return ident.toUpperCase();
}

public void addSFRElement(SFRElement sfrElement) {
this.listOfSFRElement.add(sfrElement);	
}

public boolean hasElements() {
	return !this.listOfSFRElement.isEmpty();
}

public ArrayList<SFRElementAssignement> getListOfAssignement() {
	ArrayList<SFRElementAssignement> list = new ArrayList<SFRElementAssignement>();
	for (SFRElement e:this.listOfSFRElement){
		list.addAll(e.getListOfAssigment());
	}
	return list;
}

public SFRDependencyList getListOfDependancies() {
	return dependencies;
}

public void addSFRDependency(String SFR_Family_ID) {
	this.dependencies.add(new SFRDependency(SFR_Family_ID));
	
}

public SecurityObjectiveList getListOfCoveredObjectives() {
	return listOfCoveredSecurityObjectives;
	
}

public void coverSecurityObjective(SecurityObjective obj) {
	this.listOfCoveredSecurityObjectives.add(obj);
	
}

public boolean isCovered(SecurityObjective obj) {
	return this.listOfCoveredSecurityObjectives.contains(obj);
}




public boolean hasAllDependenciesCovered() {
	return this.getListOfUncoveredDependencies().isEmpty();
}




public boolean isInstanciated() {
	return this.instance!=null;
}




  
  
  
}
