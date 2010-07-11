package hermes.protocol;

import java.util.HashMap;
import java.util.Map;

import org.jivesoftware.smack.packet.Message;

public class InProtocol {

	private ActionsInterface actionsInterface;
	private String username;

	public InProtocol(ActionsInterface actions, String username) {
		this.actionsInterface = actions;
		this.username = username;
	}

	public void parse(Message msg) {
		System.out.println("InProtocol received: " + msg.getBody());
		System.out.println("from: " + msg.getFrom());
		System.out.println("username set: " + username);
		
		Map<String, String> paramsMap = createParamsMap(msg.getBody());

		if (!paramsMap.get("fromuser").equals(username)) {
			if (paramsMap.get("command").equals("addSign")) {
				actionsInterface.addSign(Integer.parseInt(paramsMap
						.get("lineNumb")), Integer.parseInt(paramsMap
						.get("signNumb")), paramsMap
						.get("addedSign"));
			}
		}

	}

	private Map<String, String> createParamsMap(String params) {
		Map<String, String> paramsMap = new HashMap<String, String>();
		String[] paramsSplit = params.split("&");
		for (String s : paramsSplit) {
			String[] singleParamSplit = s.split("=");
			paramsMap.put(singleParamSplit[0], singleParamSplit[1]);
		}
		return paramsMap;
	}
}
