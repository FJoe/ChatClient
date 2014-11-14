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
	private JLabel chat;
	private JButton textButton;
	
	public Window(NetworkConnection network)
	{
		this.network = network;
		frame = new JFrame();
		frame.setSize(300, 400);

		chat = new JLabel();
		
		createUserText();
		createChatText();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		

	}
	
	public void createUserText()
	{
		textButton = new JButton();
		userText = new JTextArea();
		

		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 2));
		panel.add(userText);
		panel.add(textButton);
		textButton.addActionListener(new ChatListener());
		panel.setBorder(new TitledBorder(new EtchedBorder(), "Enter Chat"));
		
		frame.add(panel, BorderLayout.SOUTH);
	}
	
	public void createChatText()
	{
		JPanel panel = new JPanel();
		chatText = new JTextArea();
		chatText.setEditable(false);
		panel.add(chatText);
		frame.add(chatText);
	}

	public void run() 
	{
		class ChatListener implements ActionListener
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if(userText.getText().length() > 0)
					chatText.insert(userText.getText(), 1);
//					chatText.setText(chatText.getText() + ">" + userText.getText() + "\n");
				userText.setText("");
			}
		}
		textButton.addActionListener(new ChatListener());
	}
}
