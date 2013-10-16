package core.cc.attributes;

import java.util.ArrayList;

public class SecurityAttribute {
   String id;
   ArrayList<String> listOfPossibleValue = new ArrayList<String>();
   String initialValue;
   
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
   
   
}
