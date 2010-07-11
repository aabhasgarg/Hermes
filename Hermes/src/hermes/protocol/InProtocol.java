package hermes.protocol;

public class InProtocol {
	
	private InActionsInterface actionsInterface;
	
	public InProtocol(InActionsInterface actions) {
		this.actionsInterface = actions;
	}
	
	public void parse(String s) {
		actionsInterface.addLine(0);
	}
}
