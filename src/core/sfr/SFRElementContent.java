package core.sfr;

import core.printing.BasicElement;

public abstract class SFRElementContent {
	
	protected abstract BasicElement getContent();

	@Override
	protected abstract Object clone() throws CloneNotSupportedException; 
	
	


}
