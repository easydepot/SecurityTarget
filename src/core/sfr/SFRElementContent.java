package core.sfr;

public abstract class SFRElementContent {
	
	protected abstract String getContent();

	@Override
	protected abstract Object clone() throws CloneNotSupportedException; 
	
	


}
