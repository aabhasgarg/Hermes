package hermes;

import hermes.view.View;
import hermes.view.jabber.ChatTab;

import java.util.HashMap;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManager;
import org.jivesoftware.smack.ChatManagerListener;

public class ChatAdministration {

    private HashMap<String, ChatTab> currentChats;

    public ChatAdministration() {
	this.currentChats = new HashMap<String, ChatTab>();
    }

    public void setThisAsChatListener(ChatManager manager) {
	manager.addChatListener(new ChatManagerListener() {

	    @Override
	    public void chatCreated(Chat arg0, boolean arg1) {
		ChatAdministration.this.chatCreated(arg0, arg1);
	    }
	});
    }

    /**
     * 
     * @param chat
     * @param createtdLocally
     */
    public void chatCreated(Chat chat, boolean createtdLocally) {
	if (currentChats.containsKey(chat.getParticipant())) {
	    View.CURRENT_INSTANCE.putChatTabInForeground(currentChats.get(chat
		    .getParticipant()));
	} else {
	    ChatTab ct = new ChatTab(chat);
	    currentChats.put(chat.getParticipant(), ct);
	    View.CURRENT_INSTANCE.addChatTab(ct);
	}
    }

    public void initChat(String with) {
	Controller.CURRENT_INSTANCE.conn.getChatManager()
		.createChat(with, null);
    }
}
