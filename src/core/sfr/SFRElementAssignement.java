package core.sfr;

import core.printing.BasicElement;
import core.printing.SimpleText;

public class SFRElementAssignement extends SFRElementContent {
	@Override
	protected Object clone() throws CloneNotSupportedException {
		SFRElementAssignement cloned = new SFRElementAssignement();
		if (this.isAssigned()){
			cloned.setContent(this.getContent());
		}
		return cloned;
	}

	BasicElement content;

	protected BasicElement getContent() {
		if (content!=null){
			return content;
		} else {
		  return new SimpleText("[assignement]");
		}
	}

	public SFRElementAssignement() {
		super();
	}

	public void setContent(BasicElement content) {
		this.content = content;
	}

	public boolean isAssigned() {
		if (this.ignore_assignement){
			return true;
		}
		return content!=null;
	}
	
	boolean ignore_assignement = false;

	public void ignoreAssignement() {
		ignore_assignement = true;		
	}

	public void setContent(String string) {
		this.content = new SimpleText(string);
		
	}
	
	
	
	

}
