package javadb;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sept.rest.webservices.restfulwebservices.todo.Post.Status;


// to download GSON jar for this
//	chat_overhead:
//					Chat!D		User1 		User2 		
//					-------------------------------
//					1			s123456		s238197		
//					2			s821712		s128719`	
//					3			s201938		s281929
//
//
//	chat_text
//					ChatID		Text				Time (not impl.)	Sender
//					-----------------------------------------------------------
//					3			Howdy				23/8 4:30 pm		s281929
//					1			Hey its duncan...						s123456
//					2			Straight fire ...						s128719
//					1			Dont talk to m...						s238197

// an extension of the database SPECIFIC to chat
public class ChatBase extends DatabaseRef {
	
	public ChatBase() throws SQLException
	{
		super();
	}
	
	// to put in test mode eg: OVERHEAD_TABLE += "Test";
	public static String OVERHEAD_TABLE = "chat_overhead";
	public static String TEXT_TABLE = "chat_text";

	
	
	
	
	public Overhead createChat(String user1, String user2) throws SQLException
	{					
		if (!usersExist(user1, user2))				throw new NullPointerException("Users do not exist in database.");
		if (overheadAlreadyExists(user1, user2))	throw new NullPointerException("cannot have multiple chats between same users.");
		update(String.format("insert into %s(User1, User2) VALUES ('%s', '%s')", OVERHEAD_TABLE, user1, user2));
		
		pointToLastValue(OVERHEAD_TABLE);
		return new Overhead(data.getInt(1), data.getString(2), data.getString(3));
	}
	
	
	
	
	
	
	
	public Text addText(String text, int chatID, String sender) throws SQLException
	{															
			// both chat id and sender should already exist
			if (!senderANDChatIDCorrect(chatID, sender))	
				throw new NullPointerException("wrong sender in chat.");
		try {
			System.out.printf("insert into %s(ChatID, Text, Sender) VALUES (%d,'%s','%s')", TEXT_TABLE, chatID, text, sender);
			System.out.println();
			update(String.format("insert into %s(ChatID, Text, Sender) VALUES (%d,'%s','%s')", TEXT_TABLE, chatID, text, sender));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			pointToLastValue(TEXT_TABLE);
			return new Text(data.getInt(1), data.getString(2), data.getString(3));
	}
	
	
	
	
	
	
	
	public List<Text> retrievetexts(int chatID) throws SQLException
	{
		List<Text> texts = new ArrayList<>();
		data = DatabaseRef.query(String.format("select * from %s where chatID=%d", TEXT_TABLE, chatID));
		while (data.next())
			texts.add(new Text(data.getInt(1), data.getString(2), data.getString(3)));
		return texts;
	}
	
	
	
	
	
	
	
	
	// sender matches a user from one of the overheads
	private boolean senderANDChatIDCorrect(int chatID, String sender) throws SQLException
	{
		return valuesExist(String.format("SELECT count(*) FROM %s WHERE %s = %d AND (%s = '%s' OR %s = '%s')", 
		OVERHEAD_TABLE, "ChatID", chatID,  "User1", sender, "User2", sender));
	}
	
	public boolean usersExist(String user1, String user2) throws SQLException
	{
		return 	valuesExist(String.format("SELECT count(*) FROM %s WHERE %s = '%s'", "login", "ID", user1))
			&&	valuesExist(String.format("SELECT count(*) FROM %s WHERE %s = '%s'", "login", "ID", user2));
	}
	// can't create multiple chats between same users
	public boolean overheadAlreadyExists(String user1, String user2) throws SQLException
	{
		return valuesExist(String.format("SELECT count(*) FROM %s WHERE (%s = '%s' AND %s = '%s') OR (%s = '%s' AND %s = '%s')", 
		OVERHEAD_TABLE, "User1", user1, "User2", user2, "User2", user1, "User1", user2));
		
	}
	
	/* to be moved to DatabaseRef after merging*/
	
	public ResultSet pointToLastValue(String table) throws SQLException
	{
		data = DatabaseRef.query(String.format("select * from %s", table));
		while (data.next())
			if (data.isLast())
				return data;
		return null;
	}
	
	
	
	
	// to help toJSON formatting
	public class Text
	{
		public Text(int chatID, String text, String sender)
		{
			this.chatID = chatID;
			this.text = text;
			this.sender = sender;
		}
		
		private int chatID;
		private String text;
		private String sender;
		
		public int getChatID() {return chatID;}
	}
	public class Overhead
	{
		public Overhead(int chatID, String user1, String user2)
		{
			this.chatID = chatID;
			this.user1 = user1;
			this.user2 = user2;
		}
		private int chatID;
		private String user1;
		private String user2;
		
		public int getChatID() {return chatID;}
		
	}
	
}
