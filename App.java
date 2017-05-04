import java.awt.EventQueue;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



public class App{

	JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App window = new App();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public App() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(0, 51, 51));
		frame.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblIntroTitle = new JLabel("The Virtual Pets");
		lblIntroTitle.setFont(new Font("Papyrus", Font.BOLD, 46));
		lblIntroTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblIntroTitle.setBackground(new Color(51, 0, 102));
		lblIntroTitle.setForeground(new Color(255, 255, 255));
		frame.getContentPane().add(lblIntroTitle);
		
		Button startButton = new Button("Start a new game!");
		
		startButton.setForeground(new Color(255, 255, 255));
		startButton.setFont(new Font("Papyrus", Font.PLAIN, 16));
		startButton.setBackground(new Color(0, 51, 51));
		frame.getContentPane().add(startButton);
		Button instructionButton = new Button("Check the instruction.");
		frame.getContentPane().add(instructionButton);
		instructionButton.setForeground(Color.WHITE);
		instructionButton.setFont(new Font("Papyrus", Font.PLAIN, 16));
		instructionButton.setBackground(new Color(0, 51, 51));
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		        frame.getContentPane().remove(lblIntroTitle);
		        frame.getContentPane().remove(startButton);
		        frame.getContentPane().remove(instructionButton);
		        frame.getContentPane().setLayout(new BorderLayout(0, 0));
				JTextPane txtpnWelcome = new JTextPane();
				txtpnWelcome.setFont(new Font("Bradley Hand ITC", Font.PLAIN, 20));
				txtpnWelcome.setBackground(new Color(240, 255, 255));
				txtpnWelcome.setText("Welcome to the world of the Virtual Pets.\r\nWe know you are eager to play with your very own virtual pets,\r\nbut we would like to ask you a few questions.");
				frame.getContentPane().add(txtpnWelcome, BorderLayout.CENTER);
				JButton btnSure = new JButton("Sure!");
				btnSure.setFont(new Font("Times New Roman", Font.PLAIN, 18));
				frame.getContentPane().add(btnSure, BorderLayout.SOUTH);
				frame.pack();
				frame.setVisible(true);
					btnSure.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							frame.getContentPane().remove(btnSure);
							frame.getContentPane().remove(txtpnWelcome);
							frame.getContentPane().setBackground(new Color(240, 255, 255));
							frame.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
							JLabel lblQuantityPlayers = new JLabel("How many players would you like?");
							lblQuantityPlayers.setBackground(new Color(240, 255, 240));
							frame.getContentPane().add(lblQuantityPlayers);
							JComboBox comboBoxNumsPlayers = new JComboBox();
							comboBoxNumsPlayers.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3"}));
							comboBoxNumsPlayers.setEditable(false);
							frame.getContentPane().add(comboBoxNumsPlayers);
							JButton btnConfirm = new JButton("Confirm");
							frame.getContentPane().add(btnConfirm);
							frame.pack();
							frame.setVisible(true);
							btnConfirm.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent arg0) {
									frame.getContentPane().remove(lblQuantityPlayers);
									frame.getContentPane().remove(comboBoxNumsPlayers);
									frame.getContentPane().remove(btnConfirm);
									int countPlayers = 0;
									while (countPlayers < Integer.parseInt((String) comboBoxNumsPlayers.getSelectedItem())) {
										countPlayers++;
										String questionName = "What is player " + Integer.toString(countPlayers) + "'s name?";
										System.out.println(questionName);
										frame.getContentPane().setBackground(new Color(240, 255, 255));
										frame.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
										JLabel lblPlayerName = new JLabel(questionName);
										lblPlayerName.setBackground(new Color(240, 255, 255));
										frame.getContentPane().add(lblPlayerName);
										JTextField textFieldPlayerName = new JTextField();
										textFieldPlayerName.setColumns(10);
										frame.getContentPane().add(textFieldPlayerName);
										frame.getContentPane().add(btnConfirm);
										frame.pack();
										frame.setVisible(true);
									}
								}
							});
						}
				});
			}
			
		});
		instructionButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			        frame.getContentPane().remove(lblIntroTitle);
			        frame.getContentPane().remove(startButton);
			        frame.getContentPane().remove(instructionButton);
			        frame.getContentPane().setLayout(new BorderLayout(0, 0));
					frame.getContentPane().setBackground(new Color(169, 169, 169));
					TextArea textInstruction = new TextArea();
					textInstruction.setForeground(new Color(0, 0, 0));
					textInstruction.setBackground(new Color(230, 230, 250));
					textInstruction.setText("Welcome to the World of The Virtual Pets.\r\nYour goal here is let your pet have a great time.");
					textInstruction.setFont(new Font("Monospaced", Font.PLAIN, 14));
					frame.getContentPane().add(textInstruction, BorderLayout.CENTER);
					Button buttonGoBack = new Button("Go back");
					frame.getContentPane().add(buttonGoBack, BorderLayout.SOUTH);
					JLabel lblInstructionTitle = new JLabel("Learn the world of the Virtual Pet");
					lblInstructionTitle.setForeground(new Color(255, 255, 255));
					lblInstructionTitle.setBackground(new Color(0, 0, 0));
					lblInstructionTitle.setFont(new Font("Bradley Hand ITC", Font.PLAIN, 28));
					lblInstructionTitle.setHorizontalAlignment(SwingConstants.CENTER);
					frame.getContentPane().add(lblInstructionTitle, BorderLayout.NORTH);
					frame.pack();
			        frame.setVisible(true);
					buttonGoBack.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							frame.getContentPane().setBackground(new Color(0, 51, 51));
							frame.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
							frame.getContentPane().remove(lblInstructionTitle);
							frame.getContentPane().remove(buttonGoBack);
							frame.getContentPane().remove(textInstruction);
							frame.getContentPane().add(lblIntroTitle);
							frame.getContentPane().add(startButton);
							frame.getContentPane().add(instructionButton);
							frame.pack();
					        frame.setVisible(true);
						}
					});
				}
		});
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
