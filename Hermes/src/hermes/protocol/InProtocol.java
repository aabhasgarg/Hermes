package hermes.protocol;

public class InProtocol {
	
	private ActionsInterface actionsInterface;
	
	public InProtocol(ActionsInterface actions) {
		this.actionsInterface = actions;
	}
	
	public void parse(String s) {
		actionsInterface.addLine(0);
	}
}
