package core.sfr;

import java.util.ArrayList;

import core.printing.BasicElement;
import core.printing.Sequence;

public class SFRElement {
	
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		SFRElement cloned = new SFRElement(this.ident);
		for (SFRElementContent content: this.contentList){
			cloned.contentList.add((SFRElementContent) content.clone());
		}
		return cloned;
		
	}

	ArrayList<SFRElementContent> contentList = new ArrayList<SFRElementContent>();
	
	public void addContent(String content){
		this.contentList.add(new SFRElementText(content));
	}
	
	public SFRElementAssignement addNewAssignement(){
		SFRElementAssignement result = new SFRElementAssignement();
		this.contentList.add(result);
		return result;
	}
	
	public BasicElement getContent() throws Exception{
		Sequence result = new Sequence();
		for (SFRElementContent s:this.contentList){
			result.add(s.getContent());
		}
		return result;
	}
	
	
	public SFRElement(String ident) {
		this.ident = ident;
	}

	String ident;

	public String getIdent() {
		return ident.toUpperCase();
	}

	public boolean hasContent() {
		return !this.contentList.isEmpty();
	}

	public ArrayList<SFRElementAssignement> getListOfAssigment() {
		ArrayList<SFRElementAssignement> result = new ArrayList<SFRElementAssignement>();
		for (SFRElementContent element: this.contentList){
			if (element.getClass().equals(SFRElementAssignement.class)){
				result.add((SFRElementAssignement)element);
			}
		}
		return result;
	}

	public boolean isAssigned() {
		
		for (SFRElementAssignement e:this.getListOfAssigment()){
			if (!e.isAssigned()){
				return false;
			}
		}
		return true;		
	}

	

}
