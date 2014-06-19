package core.sfr;

public class SFRElementText extends SFRElementContent {
	String content;

	protected String getContent() {
		return content;
	}

	public SFRElementText(String content) {
		super();
		this.content = content;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return new SFRElementText(this.content);
	}
	
	

}
