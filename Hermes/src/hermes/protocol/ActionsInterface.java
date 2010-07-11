package hermes.protocol;

public interface ActionsInterface {
	
	public void addLine(int lineNumb);
	public void deleteLine(int lineNumb);
	public void deleteSign(int lineNumb, int signNumb, char deletedSign);
}
