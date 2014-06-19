package core.sfr;

public class SFRElementAssignement extends SFRElementContent {
	@Override
	protected Object clone() throws CloneNotSupportedException {
		SFRElementAssignement cloned = new SFRElementAssignement();
		if (this.isAssigned()){
			cloned.setContent(this.getContent());
		}
		return cloned;
	}

	String content;

	protected String getContent() {
		if (this.isAssigned()){
			return content;
		} else {
		  return "[assignement]";
		}
	}

	public SFRElementAssignement() {
		super();
	}

	public void setContent(String content) {
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
	
	
	
	

}
