package tamagochi;

import java.awt.EventQueue;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.awt.event.ActionEvent;



public class App{

	JFrame frame;
	private int numPlayers;
	private int numPets;
	private JLabel lblIntroTitle;
	GameEnvironment game = new GameEnvironment();

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
	public void DisplayMessage() {
		frame.getContentPane().setBackground(new Color(199, 191, 230));
        frame.getContentPane().setLayout(new BorderLayout(0, 0));
		JTextPane txtpnWelcome = new JTextPane();
		txtpnWelcome.setForeground(new Color(0, 0, 0));
		txtpnWelcome.setFont(new Font("Bradley Hand ITC", Font.PLAIN, 34));
		txtpnWelcome.setBackground(new Color(245, 255, 250));
		txtpnWelcome.setText("Welcome to the world of the Virtual Pets. We know you are eager to play with your very own virtual pets, but we would like to ask you a few questions.");
		frame.getContentPane().add(txtpnWelcome, BorderLayout.CENTER);
		JButton btnSure = new JButton("Sure!");
		btnSure.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		frame.getContentPane().add(btnSure, BorderLayout.SOUTH);
		lblIntroTitle = new JLabel("");
		lblIntroTitle.setIcon(new ImageIcon("C:\\Users\\Nobutaka\\workspace\\tamagochi\\Images\\IntroTitle.jpg"));
		lblIntroTitle.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblIntroTitle, BorderLayout.NORTH);
		frame.setSize(525, 400);
		frame.setVisible(true);
			btnSure.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					frame.getContentPane().remove(btnSure);
					frame.getContentPane().remove(txtpnWelcome);
					NumPlayers();
				}
		});
	}
	public void NumPlayers() {
		frame.getContentPane().setBackground(new Color(240, 255, 255));
		frame.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		JLabel lblQuantityPlayers = new JLabel("How many players would you like?");
		lblQuantityPlayers.setBackground(new Color(240, 255, 240));
		lblQuantityPlayers.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		frame.getContentPane().add(lblQuantityPlayers);
		JComboBox comboBoxNumsPlayers = new JComboBox();
		comboBoxNumsPlayers.setFont(new Font("Times New Roman", Font.PLAIN, 24));
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
				numPlayers = Integer.parseInt((String) comboBoxNumsPlayers.getSelectedItem());
				int countPlayers = 1;
				PlayerName(countPlayers);
				}
		});
	}
	public void PlayerName(int playerID) {
		String questionName = "What is player " + Integer.toString(playerID) + "'s name?";
		frame.getContentPane().setBackground(new Color(240, 255, 255));
		frame.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		JLabel lblPlayerName = new JLabel(questionName);
		lblPlayerName.setBackground(new Color(240, 255, 255));
		lblPlayerName.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		frame.getContentPane().add(lblPlayerName);
		JTextField textFieldPlayerName = new JTextField();
		textFieldPlayerName.setColumns(10);
		textFieldPlayerName.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		frame.getContentPane().add(textFieldPlayerName);
		JButton btnConfirm = new JButton("Confirm");
		frame.getContentPane().add(btnConfirm);
		frame.pack();
		frame.setVisible(true);
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (textFieldPlayerName.getText().trim().length() == 0) {
					try {
						ErrorNoLetter dialog = new ErrorNoLetter();
						dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						dialog.pack();
						dialog.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				else {
					if (textFieldPlayerName.getText().length() >= 20) {
						try {
							ErrorExceedChar dialog = new ErrorExceedChar();
							dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
							dialog.pack();
							dialog.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					else {
						boolean isValid = true;
						for(Player person:game.playerArray){
							if(person.getName().equals(textFieldPlayerName.getText())){
								isValid = false;
								try {
									ErrorUniqueName dialog = new ErrorUniqueName();
									dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
									dialog.pack();
									dialog.setVisible(true);
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						}
						if (isValid == true) {
							game.playerArray.add(new Player(textFieldPlayerName.getText()));
							frame.getContentPane().remove(lblPlayerName);
							frame.getContentPane().remove(textFieldPlayerName);
							frame.getContentPane().remove(btnConfirm);
							if (playerID == numPlayers) {
								NumPets(0);
								return;
								}
							PlayerName(playerID + 1);
							return;
						}
					}
				}
			}
		});
	}
	public void NumPets(int index) {
		Player playerName = (Player) game.playerArray.get(index);
		frame.getContentPane().setBackground(new Color(240, 255, 255));
		frame.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		JLabel lblPlayer = new JLabel("Player: " + playerName.getName());
		lblPlayer.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		frame.getContentPane().add(lblPlayer);
		JLabel lblQuantityPets = new JLabel("How many pets would you like?");
		lblQuantityPets.setBackground(new Color(240, 255, 240));
		lblQuantityPets.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		frame.getContentPane().add(lblQuantityPets);
		JComboBox comboBoxNumsPets = new JComboBox();
		comboBoxNumsPets.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		comboBoxNumsPets.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3"}));
		comboBoxNumsPets.setEditable(false);
		frame.getContentPane().add(comboBoxNumsPets);
		JButton btnConfirm = new JButton("Confirm");
		frame.getContentPane().add(btnConfirm);
		frame.pack();
		frame.setVisible(true);
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.getContentPane().remove(lblPlayer);
				frame.getContentPane().remove(lblQuantityPets);
				frame.getContentPane().remove(comboBoxNumsPets);
				frame.getContentPane().remove(btnConfirm);
				frame.getContentPane().remove(lblIntroTitle);
				numPets = Integer.parseInt((String) comboBoxNumsPets.getSelectedItem());
				int countPets = 1;
				petName(countPets);
				}
		});
	}
	public void petName(int petID) {
		frame.getContentPane().setBackground(new Color(240, 255, 255));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 111, 18, 0};
		gridBagLayout.rowHeights = new int[]{0, 20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JLabel lblPetName = new JLabel("What is pet 1's name?");
		lblPetName.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		GridBagConstraints gbc_lblPetName = new GridBagConstraints();
		gbc_lblPetName.insets = new Insets(0, 0, 5, 5);
		gbc_lblPetName.gridx = 1;
		gbc_lblPetName.gridy = 1;
		frame.getContentPane().add(lblPetName, gbc_lblPetName);
		
		JTextField textFieldPetName = new JTextField();
		textFieldPetName.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		GridBagConstraints gbc_textFieldPetName = new GridBagConstraints();
		gbc_textFieldPetName.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldPetName.gridwidth = 2;
		gbc_textFieldPetName.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldPetName.gridx = 2;
		gbc_textFieldPetName.gridy = 1;
		frame.getContentPane().add(textFieldPetName, gbc_textFieldPetName);
		textFieldPetName.setColumns(10);
		
		JLabel lblSpeciesOption = new JLabel("What breed would you like your pet to be?");
		lblSpeciesOption.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		GridBagConstraints gbc_lblSpeciesOption = new GridBagConstraints();
		gbc_lblSpeciesOption.insets = new Insets(0, 0, 5, 5);
		gbc_lblSpeciesOption.gridx = 1;
		gbc_lblSpeciesOption.gridy = 3;
		frame.getContentPane().add(lblSpeciesOption, gbc_lblSpeciesOption);
		
		JComboBox comboBoxSpecies = new JComboBox();
		comboBoxSpecies.setModel(new DefaultComboBoxModel(new String[] {"Bird", "Cat", "CatDog", "Dog", "Ocelot", "Tiger"}));
		comboBoxSpecies.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		GridBagConstraints gbc_comboBoxSpecies = new GridBagConstraints();
		gbc_comboBoxSpecies.gridwidth = 2;
		gbc_comboBoxSpecies.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxSpecies.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxSpecies.gridx = 2;
		gbc_comboBoxSpecies.gridy = 3;
		frame.getContentPane().add(comboBoxSpecies, gbc_comboBoxSpecies);
		
		String box = "\u239A";
		if (comboBoxSpecies.getSelectedItem() == "Bird") {
			Pet bird = new Bird(textFieldPetName.getText());
			String appetite = String.join("", Collections.nCopies(bird.getHungerDrop(), box));
			String fatigue = String.join("", Collections.nCopies(bird.getEnergyDrop(), box));
			String depression = String.join("", Collections.nCopies(bird.getHappinessDrop(), box));
			String aggression = String.join("", Collections.nCopies(bird.getHappinessDrop(), box));
			String favouriteFood = bird.getFavouriteFood();
			String favouriteToy = bird.getFavouriteToy();
			ImageIcon petImage = new ImageIcon("C:\\Users\\Nobutaka\\workspace\\tamagochi\\Images\\Bird.png");
			displayPetStats(appetite, fatigue, depression, 
					aggression, favouriteFood, favouriteToy, petImage);
		}
		
	}
	public void displayPetStats(String appetite, String fatigue, String depression, 
			String aggression, String favouriteFood, String favouriteToy, ImageIcon petImage) {
		
		JLabel lblAppetite = new JLabel("Appetite: " + appetite);
		lblAppetite.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		GridBagConstraints gbc_lblAppetite = new GridBagConstraints();
		gbc_lblAppetite.anchor = GridBagConstraints.WEST;
		gbc_lblAppetite.insets = new Insets(0, 0, 5, 5);
		gbc_lblAppetite.gridx = 1;
		gbc_lblAppetite.gridy = 5;
		frame.getContentPane().add(lblAppetite, gbc_lblAppetite);
		
		JLabel lblPets = new JLabel("");
		lblPets.setIcon(petImage);
		GridBagConstraints gbc_lblPets = new GridBagConstraints();
		gbc_lblPets.gridwidth = 2;
		gbc_lblPets.gridheight = 5;
		gbc_lblPets.insets = new Insets(0, 0, 5, 5);
		gbc_lblPets.gridx = 2;
		gbc_lblPets.gridy = 5;
		frame.getContentPane().add(lblPets, gbc_lblPets);
		
		JLabel lblFatigue = new JLabel("Fatigue: " + fatigue);
		lblFatigue.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		GridBagConstraints gbc_lblFatigue = new GridBagConstraints();
		gbc_lblFatigue.anchor = GridBagConstraints.WEST;
		gbc_lblFatigue.insets = new Insets(0, 0, 5, 5);
		gbc_lblFatigue.gridx = 1;
		gbc_lblFatigue.gridy = 6;
		frame.getContentPane().add(lblFatigue, gbc_lblFatigue);
		
		JLabel lblDepression = new JLabel("Depression: " + depression);
		lblDepression.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		GridBagConstraints gbc_lblDepression = new GridBagConstraints();
		gbc_lblDepression.anchor = GridBagConstraints.WEST;
		gbc_lblDepression.insets = new Insets(0, 0, 5, 5);
		gbc_lblDepression.gridx = 1;
		gbc_lblDepression.gridy = 7;
		frame.getContentPane().add(lblDepression, gbc_lblDepression);
		
		JLabel lblAggression = new JLabel("Aggression: " + aggression);
		lblAggression.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		GridBagConstraints gbc_lblAggression = new GridBagConstraints();
		gbc_lblAggression.anchor = GridBagConstraints.WEST;
		gbc_lblAggression.insets = new Insets(0, 0, 5, 5);
		gbc_lblAggression.gridx = 1;
		gbc_lblAggression.gridy = 8;
		frame.getContentPane().add(lblAggression, gbc_lblAggression);
		
		JLabel lblFavouriteFood = new JLabel("Favourite food: " + favouriteFood);
		lblFavouriteFood.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		GridBagConstraints gbc_lblFavouriteFood = new GridBagConstraints();
		gbc_lblFavouriteFood.anchor = GridBagConstraints.WEST;
		gbc_lblFavouriteFood.insets = new Insets(0, 0, 5, 5);
		gbc_lblFavouriteFood.gridx = 1;
		gbc_lblFavouriteFood.gridy = 9;
		frame.getContentPane().add(lblFavouriteFood, gbc_lblFavouriteFood);
		
		JLabel lblFavouriteToy = new JLabel("Favourite toy: " + favouriteToy);
		lblFavouriteToy.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		GridBagConstraints gbc_lblFavouriteToy = new GridBagConstraints();
		gbc_lblFavouriteToy.anchor = GridBagConstraints.WEST;
		gbc_lblFavouriteToy.insets = new Insets(0, 0, 5, 5);
		gbc_lblFavouriteToy.gridx = 1;
		gbc_lblFavouriteToy.gridy = 10;
		frame.getContentPane().add(lblFavouriteToy, gbc_lblFavouriteToy);
		
		JButton btnConfirm = new JButton("Confirm");
		GridBagConstraints gbc_btnConfirm = new GridBagConstraints();
		gbc_btnConfirm.insets = new Insets(0, 0, 0, 5);
		gbc_btnConfirm.gridx = 3;
		gbc_btnConfirm.gridy = 11;
		frame.getContentPane().add(btnConfirm, gbc_btnConfirm);
		frame.pack();
		frame.setVisible(true);
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
		        DisplayMessage();
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
