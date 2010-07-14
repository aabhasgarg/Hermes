package hermes;

import hermes.view.View;

import java.util.HashMap;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManager;
import org.jivesoftware.smack.ChatManagerListener;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.packet.Message;

public class ChatAdministration {

    private HashMap currentChats;

    public ChatAdministration() {
	this.currentChats = new HashMap();
    }

    public void setThisAsChatListener(ChatManager manager) {
	manager.addChatListener(new ChatManagerListener() {

	    @Override
	    public void chatCreated(Chat arg0, boolean arg1) {
		ChatAdministration.this.chatCreated(arg0, arg1);
	    }
	});
    }

    public void chatCreated(Chat chat, boolean createtdLocally) {
	if (currentChats.containsKey(chat.getParticipant())) {
	    // put fitting chat in foreground
	} else {
	    View.CURRENT_INSTANCE.addChatWindow();
	}
    }
}
