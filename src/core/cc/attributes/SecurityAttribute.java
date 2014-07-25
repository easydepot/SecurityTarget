package core.cc.attributes;

import java.util.ArrayList;

public class SecurityAttribute {
   String id;
   ArrayList<String> listOfPossibleValue = new ArrayList<String>();
   String initialValue;
   String description ="";
   boolean type_information = false;
   
public boolean isType_information() {
	return type_information;
}

public void setType_information(boolean type_information) {
	this.type_information = type_information;
}

public String getDescription() {
	return description;
}

public SecurityAttribute(String id) {
	this.id = id;
}

public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getInitialValue() {
	return initialValue;
}
public void setInitialValue(String initialValue) throws Exception{
	if (!this.getListOfPossibleValue().contains(initialValue)){
		throw new Exception("Error defining initial value: " +initialValue + " not defined in the set of values");
	}
	this.initialValue = initialValue;
}
public ArrayList<String> getListOfPossibleValue() {
	return listOfPossibleValue;
}

public void setDescription(String description) {
this.description = description;	
}

public String getFullId() {
	if (this.isType_information()){
		return "I."+this.getId();
	}
	return "AT."+this.getId();
}
   
   
}
