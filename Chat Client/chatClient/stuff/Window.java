package stuff;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class Window implements Runnable 
{
	private NetworkConnection network;
	private JFrame frame;
	private JTextArea userText, chatText;
	private JButton textButton;
	
	public final int FRAME_LENGTH = 300;
	public final int FRAME_WIDTH = 400;
	
	public Window(NetworkConnection network)
	{
		this.network = network;
		frame = new JFrame();
		frame.setSize(FRAME_LENGTH, FRAME_WIDTH);
		
		createUserText();
		createChatText();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public void createUserText()
	{
		final int TEXT_ROWS = 2;
		final int TEXT_COLUMNS = 17;
		userText = new JTextArea(TEXT_ROWS, TEXT_COLUMNS);
		JScrollPane scrollPane = new JScrollPane(userText);
		
		textButton = new JButton("Send");
		
		class ChatListener implements ActionListener
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if(userText.getText().length() > 0)
					chatText.append(">" + userText.getText() + "\n");
				
				userText.setText("");
			}
		}
		textButton.addActionListener(new ChatListener());
		
		JPanel panel = new JPanel();
		panel.add(scrollPane);
		panel.add(textButton);

		
		textButton.addActionListener(new ChatListener());
		panel.setBorder(new TitledBorder(new EtchedBorder(), "Enter Chat"));
		
		frame.add(panel, BorderLayout.SOUTH);
	}
	
	public void createChatText()
	{
		chatText = new JTextArea();
		chatText.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(chatText);
		frame.add(scrollPane);
	}

	public void run() 
	{

	}
}
