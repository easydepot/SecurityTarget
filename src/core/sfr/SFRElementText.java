package core.sfr;

import core.printing.BasicElement;
import core.printing.SimpleText;

public class SFRElementText extends SFRElementContent {
	BasicElement content;

	protected BasicElement getContent() {
		return content;
	}

	public SFRElementText(String content) {
		super();
		this.content = new SimpleText(content);
	}
	
	public SFRElementText(BasicElement content) throws Exception {
		super();
		if (content==null){
			throw new Exception("Element content cannot be null");
		}
		this.content = content;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		
		try {
			return new SFRElementText(this.content);
		} catch (Exception e) {
			throw new CloneNotSupportedException("content is null");
		}
	}
	
	

}
