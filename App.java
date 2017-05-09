package tamagochi;

import java.awt.EventQueue;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
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
import java.awt.SystemColor;
import java.awt.TextArea;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.awt.event.ActionEvent;



public class App{

	JFrame frame;
	private int numPlayers;
	private Player currentPlayer;
	private JLabel lblIntroTitle;
	private int gameLength;
	private int day = 1;
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
		lblIntroTitle.setIcon(new ImageIcon("Images/IntroTitle.jpg"));
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
		String questionName = "Player " + Integer.toString(playerID) + ", what is your name?";
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
		currentPlayer = (Player) game.playerArray.get(index);
		frame.getContentPane().setBackground(new Color(240, 255, 255));
		frame.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		JLabel lblPlayer = new JLabel("Player: " + currentPlayer.getName());
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
				int numPets = Integer.parseInt((String) comboBoxNumsPets.getSelectedItem());
				int countPets = 1;
				petName(countPets, numPets);
				}
		});
	}
	public void petName(int petID, int numPets) {
		frame.getContentPane().setBackground(new Color(240, 255, 255));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 111, 18, 0};
		gridBagLayout.rowHeights = new int[]{0, 20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JLabel lblPlayerName = new JLabel("  Player: " + currentPlayer.getName());
		lblPlayerName.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		GridBagConstraints gbc_lblPlayerName = new GridBagConstraints();
		gbc_lblPlayerName.anchor = GridBagConstraints.WEST;
		gbc_lblPlayerName.insets = new Insets(0, 0, 5, 5);
		gbc_lblPlayerName.gridx = 1;
		gbc_lblPlayerName.gridy = 1;
		frame.getContentPane().add(lblPlayerName, gbc_lblPlayerName);
		
		JLabel lblPetName = new JLabel("  What is your pet " + Integer.toString(petID) + "'s name?");
		lblPetName.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		GridBagConstraints gbc_lblPetName = new GridBagConstraints();
		gbc_lblPetName.anchor = GridBagConstraints.WEST;
		gbc_lblPetName.insets = new Insets(0, 0, 5, 5);
		gbc_lblPetName.gridx = 1;
		gbc_lblPetName.gridy = 2;
		frame.getContentPane().add(lblPetName, gbc_lblPetName);
		
		JLabel lblSpeciesOption = new JLabel("  What breed would you like your pet to be?");
		lblSpeciesOption.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		GridBagConstraints gbc_lblSpeciesOption = new GridBagConstraints();
		gbc_lblSpeciesOption.insets = new Insets(0, 0, 5, 5);
		gbc_lblSpeciesOption.gridx = 1;
		gbc_lblSpeciesOption.gridy = 3;
		frame.getContentPane().add(lblSpeciesOption, gbc_lblSpeciesOption);
		String box = "\u239A";
		Pet animal = new Bird("Default");
		String defaultAppetite = String.join("", Collections.nCopies(animal.getHungerDrop(), box));
		String defaultFatigue = String.join("", Collections.nCopies(animal.getEnergyDrop(), box));
		String defaultDepression = String.join("", Collections.nCopies(animal.getHappinessDrop(), box));
		String defaultAggression = String.join("", Collections.nCopies(animal.getAggression(), box));
		String defaultFavouriteFood = animal.getFavouriteFood();
		String defaultFavouriteToy = animal.getFavouriteToy();
		ImageIcon defaultPetImage = new ImageIcon("Images/Bird.png");
		
		JTextField textFieldPetName = new JTextField();
		textFieldPetName.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		GridBagConstraints gbc_textFieldPetName = new GridBagConstraints();
		gbc_textFieldPetName.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldPetName.gridwidth = 2;
		gbc_textFieldPetName.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldPetName.gridx = 2;
		gbc_textFieldPetName.gridy = 2;
		frame.getContentPane().add(textFieldPetName, gbc_textFieldPetName);
		textFieldPetName.setColumns(10);
		
		JLabel lblAppetite = new JLabel("  Appetite: " + defaultAppetite);
		lblAppetite.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		GridBagConstraints gbc_lblAppetite = new GridBagConstraints();
		gbc_lblAppetite.anchor = GridBagConstraints.WEST;
		gbc_lblAppetite.insets = new Insets(0, 0, 5, 5);
		gbc_lblAppetite.gridx = 1;
		gbc_lblAppetite.gridy = 6;
		frame.getContentPane().add(lblAppetite, gbc_lblAppetite);
		
		JLabel lblPets = new JLabel("");
		lblPets.setIcon(defaultPetImage);
		GridBagConstraints gbc_lblPets = new GridBagConstraints();
		gbc_lblPets.gridwidth = 2;
		gbc_lblPets.gridheight = 5;
		gbc_lblPets.insets = new Insets(0, 0, 5, 5);
		gbc_lblPets.gridx = 2;
		gbc_lblPets.gridy = 6;
		frame.getContentPane().add(lblPets, gbc_lblPets);
		
		JLabel lblFatigue = new JLabel("  Fatigue: " + defaultFatigue);
		lblFatigue.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		GridBagConstraints gbc_lblFatigue = new GridBagConstraints();
		gbc_lblFatigue.anchor = GridBagConstraints.WEST;
		gbc_lblFatigue.insets = new Insets(0, 0, 5, 5);
		gbc_lblFatigue.gridx = 1;
		gbc_lblFatigue.gridy = 7;
		frame.getContentPane().add(lblFatigue, gbc_lblFatigue);
		
		JLabel lblDepression = new JLabel("  Depression: " + defaultDepression);
		lblDepression.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		GridBagConstraints gbc_lblDepression = new GridBagConstraints();
		gbc_lblDepression.anchor = GridBagConstraints.WEST;
		gbc_lblDepression.insets = new Insets(0, 0, 5, 5);
		gbc_lblDepression.gridx = 1;
		gbc_lblDepression.gridy = 8;
		frame.getContentPane().add(lblDepression, gbc_lblDepression);
		
		JLabel lblAggression = new JLabel("  Aggression: " + defaultAggression);
		lblAggression.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		GridBagConstraints gbc_lblAggression = new GridBagConstraints();
		gbc_lblAggression.anchor = GridBagConstraints.WEST;
		gbc_lblAggression.insets = new Insets(0, 0, 5, 5);
		gbc_lblAggression.gridx = 1;
		gbc_lblAggression.gridy = 9;
		frame.getContentPane().add(lblAggression, gbc_lblAggression);
		
		JLabel lblFavouriteFood = new JLabel("  Favourite food: " + defaultFavouriteFood);
		lblFavouriteFood.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		GridBagConstraints gbc_lblFavouriteFood = new GridBagConstraints();
		gbc_lblFavouriteFood.anchor = GridBagConstraints.WEST;
		gbc_lblFavouriteFood.insets = new Insets(0, 0, 5, 5);
		gbc_lblFavouriteFood.gridx = 1;
		gbc_lblFavouriteFood.gridy = 10;
		frame.getContentPane().add(lblFavouriteFood, gbc_lblFavouriteFood);
		
		JLabel lblFavouriteToy = new JLabel("  Favourite toy: " + defaultFavouriteToy);
		lblFavouriteToy.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		GridBagConstraints gbc_lblFavouriteToy = new GridBagConstraints();
		gbc_lblFavouriteToy.anchor = GridBagConstraints.WEST;
		gbc_lblFavouriteToy.insets = new Insets(0, 0, 5, 5);
		gbc_lblFavouriteToy.gridx = 1;
		gbc_lblFavouriteToy.gridy = 11;
		frame.getContentPane().add(lblFavouriteToy, gbc_lblFavouriteToy);
		
		JComboBox comboBoxSpecies = new JComboBox();
		comboBoxSpecies.setModel(new DefaultComboBoxModel(new String[] {"Bird", "Cat", "CatDog", "Dog", "Ocelot", "Tiger"}));
		comboBoxSpecies.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		GridBagConstraints gbc_comboBoxSpecies = new GridBagConstraints();
		gbc_comboBoxSpecies.gridwidth = 2;
		gbc_comboBoxSpecies.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxSpecies.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxSpecies.gridx = 2;
		gbc_comboBoxSpecies.gridy = 3;
		frame.getContentPane().add(comboBoxSpecies, gbc_comboBoxSpecies);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		GridBagConstraints gbc_btnConfirm = new GridBagConstraints();
		gbc_btnConfirm.insets = new Insets(0, 0, 5, 5);
		gbc_btnConfirm.gridx = 3;
		gbc_btnConfirm.gridy = 11;
		frame.getContentPane().add(btnConfirm, gbc_btnConfirm);

		
		frame.getContentPane().add(btnConfirm, gbc_btnConfirm);
		frame.pack();
		frame.setVisible(true);
		comboBoxSpecies.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (comboBoxSpecies.getSelectedItem() == "Bird") {
					Pet bird = new Bird(textFieldPetName.getText());
					String birdAppetite = String.join("", Collections.nCopies(bird.getHungerDrop(), box));
					String birdFatigue = String.join("", Collections.nCopies(bird.getEnergyDrop(), box));
					String birdDepression = String.join("", Collections.nCopies(bird.getHappinessDrop(), box));
					String birdAggression = String.join("", Collections.nCopies(bird.getAggression(), box));
					String birdFavouriteFood = bird.getFavouriteFood();
					String birdFavouriteToy = bird.getFavouriteToy();
					ImageIcon birdImage = new ImageIcon("Images/Bird.png");
					lblAppetite.setText("  Appetite: " + birdAppetite);
					lblFatigue.setText("  Fatigue: " + birdFatigue);
					lblDepression.setText("  Depression: " +birdDepression);
					lblAggression.setText("  Aggression: " + birdAggression);
					lblFavouriteFood.setText("  Favourite food: " + birdFavouriteFood);
					lblFavouriteToy.setText("  Favourite toy: " + birdFavouriteToy);
					lblPets.setIcon(birdImage);
				}
				if (comboBoxSpecies.getSelectedItem() == "Cat") {
					Pet cat = new Cat(textFieldPetName.getText());
					String catAppetite = String.join("", Collections.nCopies(cat.getHungerDrop(), box));
					String catFatigue = String.join("", Collections.nCopies(cat.getEnergyDrop(), box));
					String catDepression = String.join("", Collections.nCopies(cat.getHappinessDrop(), box));
					String catAggression = String.join("", Collections.nCopies(cat.getAggression(), box));
					String catFavouriteFood = cat.getFavouriteFood();
					String catFavouriteToy = cat.getFavouriteToy();
					ImageIcon catImage = new ImageIcon("Images/Cat.png");
					lblAppetite.setText("  Appetite: " + catAppetite);
					lblFatigue.setText("  Fatigue: " + catFatigue);
					lblDepression.setText("  Depression: " + catDepression);
					lblAggression.setText("  Aggression: " + catAggression);
					lblFavouriteFood.setText("  Favourite food: " + catFavouriteFood);
					lblFavouriteToy.setText("  Favourite toy: " + catFavouriteToy);
					lblPets.setIcon(catImage);
				}
				if (comboBoxSpecies.getSelectedItem() == "CatDog") {
					Pet catDog = new CatDog(textFieldPetName.getText());
					String catDogAppetite = String.join("", Collections.nCopies(catDog.getHungerDrop(), box));
					String catDogFatigue = String.join("", Collections.nCopies(catDog.getEnergyDrop(), box));
					String catDogDepression = String.join("", Collections.nCopies(catDog.getHappinessDrop(), box));
					String catDogAggression = String.join("", Collections.nCopies(catDog.getAggression(), box));
					String catDogFavouriteFood = catDog.getFavouriteFood();
					String catDogFavouriteToy = catDog.getFavouriteToy();
					ImageIcon catDogImage = new ImageIcon("Images/CatDog.png");
					lblAppetite.setText("  Appetite: " + catDogAppetite);
					lblFatigue.setText("  Fatigue: " + catDogFatigue);
					lblDepression.setText("  Depression: " + catDogDepression);
					lblAggression.setText("  Aggression: " + catDogAggression);
					lblFavouriteFood.setText("  Favourite food: " + catDogFavouriteFood);
					lblFavouriteToy.setText("  Favourite toy: " + catDogFavouriteToy);
					lblPets.setIcon(catDogImage);
				}
				if (comboBoxSpecies.getSelectedItem() == "Dog") {
					Pet dog = new Dog(textFieldPetName.getText());
					String dogAppetite = String.join("", Collections.nCopies(dog.getHungerDrop(), box));
					String dogFatigue = String.join("", Collections.nCopies(dog.getEnergyDrop(), box));
					String dogDepression = String.join("", Collections.nCopies(dog.getHappinessDrop(), box));
					String dogAggression = String.join("", Collections.nCopies(dog.getAggression(), box));
					String dogFavouriteFood = dog.getFavouriteFood();
					String dogFavouriteToy = dog.getFavouriteToy();
					ImageIcon dogImage = new ImageIcon("Images/Dog.png");
					lblAppetite.setText("  Appetite: " + dogAppetite);
					lblFatigue.setText("  Fatigue: " + dogFatigue);
					lblDepression.setText("  Depression: " + dogDepression);
					lblAggression.setText("  Aggression: " + dogAggression);
					lblFavouriteFood.setText("  Favourite food: " + dogFavouriteFood);
					lblFavouriteToy.setText("  Favourite toy: " + dogFavouriteToy);
					lblPets.setIcon(dogImage);
				}
				if (comboBoxSpecies.getSelectedItem() == "Ocelot") {
					Pet ocelot = new Ocelot(textFieldPetName.getText());
					String ocelotAppetite = String.join("", Collections.nCopies(ocelot.getHungerDrop(), box));
					String ocelotFatigue = String.join("", Collections.nCopies(ocelot.getEnergyDrop(), box));
					String ocelotDepression = String.join("", Collections.nCopies(ocelot.getHappinessDrop(), box));
					String ocelotAggression = String.join("", Collections.nCopies(ocelot.getAggression(), box));
					String ocelotFavouriteFood = ocelot.getFavouriteFood();
					String ocelotFavouriteToy = ocelot.getFavouriteToy();
					ImageIcon ocelotImage = new ImageIcon("Images/Ocelot.png");
					lblAppetite.setText("  Appetite: " + ocelotAppetite);
					lblFatigue.setText("  Fatigue: " + ocelotFatigue);
					lblDepression.setText("  Depression: " + ocelotDepression);
					lblAggression.setText("  Aggression: " + ocelotAggression);
					lblFavouriteFood.setText("  Favourite food: " + ocelotFavouriteFood);
					lblFavouriteToy.setText("  Favourite toy: " + ocelotFavouriteToy);
					lblPets.setIcon(ocelotImage);
				}
				if (comboBoxSpecies.getSelectedItem() == "Tiger") {
					Pet tiger = new Tiger(textFieldPetName.getText());
					String tigerAppetite = String.join("", Collections.nCopies(tiger.getHungerDrop(), box));
					String tigerFatigue = String.join("", Collections.nCopies(tiger.getEnergyDrop(), box));
					String tigerDepression = String.join("", Collections.nCopies(tiger.getHappinessDrop(), box));
					String tigerAggression = String.join("", Collections.nCopies(tiger.getAggression(), box));
					String tigerFavouriteFood = tiger.getFavouriteFood();
					String tigerFavouriteToy = tiger.getFavouriteToy();
					ImageIcon tigerImage = new ImageIcon("Images/Tiger.png");
					lblAppetite.setText("  Appetite: " + tigerAppetite);
					lblFatigue.setText("  Fatigue: " + tigerFatigue);
					lblDepression.setText("  Depression: " + tigerDepression);
					lblAggression.setText("  Aggression: " + tigerAggression);
					lblFavouriteFood.setText("  Favourite food: " + tigerFavouriteFood);
					lblFavouriteToy.setText("  Favourite toy: " + tigerFavouriteToy);
					lblPets.setIcon(tigerImage);
				}
			}
		});
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String petName = textFieldPetName.getText();
				if (petName.trim().length() == 0) {
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
					if (petName.length() >= 20) {
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
							if(person.getName().equals(petName)){
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
						for (Pet animal:currentPlayer.petArray){
							if(animal.getName().equals(petName)){
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
							if (comboBoxSpecies.getSelectedItem() == "Bird") {
								Pet bird = new Bird(petName);
								currentPlayer.petArray.add(bird);
							}
							if (comboBoxSpecies.getSelectedItem() == "Cat") {
								Pet cat = new Cat(petName);
								currentPlayer.petArray.add(cat);
							}
							if (comboBoxSpecies.getSelectedItem() == "CatDog") {
								Pet catDog = new CatDog(petName);
								currentPlayer.petArray.add(catDog);
							}
							if (comboBoxSpecies.getSelectedItem() == "Dog") {
								Pet dog = new Dog(petName);
								currentPlayer.petArray.add(dog);
							}
							if (comboBoxSpecies.getSelectedItem() == "Ocelot") {
								Pet ocelot = new Ocelot(petName);
								currentPlayer.petArray.add(ocelot);
							}
							if (comboBoxSpecies.getSelectedItem() == "Tiger") {
								Pet tiger = new Tiger(petName);
								currentPlayer.petArray.add(tiger);
							}
							if (petID == numPets){
								frame.getContentPane().remove(lblPlayerName);
								frame.getContentPane().remove(comboBoxSpecies);
								frame.getContentPane().remove(btnConfirm);
								frame.getContentPane().remove(lblFavouriteToy);
								frame.getContentPane().remove(lblFavouriteFood);
								frame.getContentPane().remove(lblAggression);
								frame.getContentPane().remove(lblDepression);
								frame.getContentPane().remove(lblFatigue);
								frame.getContentPane().remove(lblPets);
								frame.getContentPane().remove(lblAppetite);
								frame.getContentPane().remove(textFieldPetName);
								frame.getContentPane().remove(lblSpeciesOption);
								frame.getContentPane().remove(lblPetName);
								if (game.playerArray.size() == game.playerArray.indexOf(currentPlayer) + 1){
									frame.getContentPane().add(lblIntroTitle);
									numDays();
									return;
								}
								else {
									frame.revalidate();
									frame.repaint();
									frame.getContentPane().add(lblIntroTitle);
									NumPets((game.playerArray.indexOf(currentPlayer)) + 1);
									return;
								}
							}
							frame.getContentPane().remove(lblPlayerName);
							frame.getContentPane().remove(comboBoxSpecies);
							frame.getContentPane().remove(btnConfirm);
							frame.getContentPane().remove(lblFavouriteToy);
							frame.getContentPane().remove(lblFavouriteFood);
							frame.getContentPane().remove(lblAggression);
							frame.getContentPane().remove(lblDepression);
							frame.getContentPane().remove(lblFatigue);
							frame.getContentPane().remove(lblPets);
							frame.getContentPane().remove(lblAppetite);
							frame.getContentPane().remove(textFieldPetName);
							frame.getContentPane().remove(lblSpeciesOption);
							frame.getContentPane().remove(lblPetName);
							frame.revalidate();
							frame.repaint();
							petName(petID + 1, numPets);
							return;
						}
					}
				}
			}
		});
	}
	public void numDays() {
		frame.setBackground(new Color(240, 255, 240));
		frame.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblNumDays = new JLabel("How many days would you like to play?");
		lblNumDays.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		lblNumDays.setBackground(new Color(240, 255, 240));
		frame.getContentPane().add(lblNumDays);
		
		JTextField textFieldNumDays = new JTextField();
		textFieldNumDays.setToolTipText("");
		textFieldNumDays.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		frame.getContentPane().add(textFieldNumDays);
		textFieldNumDays.setColumns(10);
		
		JButton btnConfirm = new JButton("Confirm");
		frame.getContentPane().add(btnConfirm);
		
		frame.pack();
		frame.setVisible(true);
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String numDays = textFieldNumDays.getText();
				try {
					gameLength = Integer.parseInt(numDays); //Number of days cannot exceed the maximum number.
					if (gameLength < 0) {
						try {
							ErrorNegativeNumber dialog = new ErrorNegativeNumber();
							dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
							dialog.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					if (gameLength > 10){
						frame.getContentPane().remove(textFieldNumDays);
						frame.getContentPane().remove(btnConfirm);
						lblNumDays.setText("Do you really want to play " + numDays + " days?");
						JButton btnYes = new JButton("Yes");
						frame.getContentPane().add(btnYes);
						JButton btnNo = new JButton("No");
						frame.getContentPane().add(btnNo);
						btnNo.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
								frame.getContentPane().remove(btnNo);
								frame.getContentPane().remove(btnYes);
								lblNumDays.setText("How many days would you like to play?");
								frame.getContentPane().add(textFieldNumDays);
								frame.getContentPane().add(btnConfirm);
								frame.revalidate();
								frame.repaint();
									}
								});
						btnYes.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
								frame.getContentPane().remove(btnConfirm);
								frame.getContentPane().remove(lblIntroTitle);
								frame.getContentPane().remove(textFieldNumDays);
								frame.getContentPane().remove(lblNumDays);
								frame.getContentPane().remove(btnNo);
								frame.getContentPane().remove(btnYes);
								GameLoop();
									}
								});
					}
					else {
						frame.getContentPane().remove(btnConfirm);
						frame.getContentPane().remove(lblIntroTitle);
						frame.getContentPane().remove(textFieldNumDays);
						frame.getContentPane().remove(lblNumDays);
						GameLoop();
					}
				}
				catch(NumberFormatException e){
					try {
						ErrorInvalidNumber dialog = new ErrorInvalidNumber();
						dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						dialog.setVisible(true);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
	}
	public void GameLoop() {
		if (day <= gameLength) {
			PlayDay(0);
		}
		else { //Should display the final score
		}
	}
	public void PlayDay(int playerIndex) {
		if ((playerIndex + 1) <= game.playerArray.size()) {
			currentPlayer = game.playerArray.get(playerIndex);
			currentPlayer.setMoney(20);
			for (Pet animal: currentPlayer.petArray){
				game.setPetCondition(animal);
			}
			StoryBoard();
		}
		else{
			day++;
			GameLoop();
		}
	}
	public void StoryBoard() {

		frame.setBackground(new Color(240, 255, 255));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 89, 0, 123, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frame.setLayout(gridBagLayout);
		
		JLabel lblShop = new JLabel("");
		lblShop.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
		});
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 255, 255));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridheight = 4;
		gbc_panel.gridwidth = 8;
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 0;
		frame.getContentPane().add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblPlayerName = new JLabel("Pets:");
		lblPlayerName.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlayerName.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		GridBagConstraints gbc_lblPlayerName = new GridBagConstraints();
		gbc_lblPlayerName.gridwidth = 2;
		gbc_lblPlayerName.insets = new Insets(0, 0, 0, 5);
		gbc_lblPlayerName.gridx = 0;
		gbc_lblPlayerName.gridy = 0;
		panel.add(lblPlayerName, gbc_lblPlayerName);
		
		JLabel lblPet2 = new JLabel("");
		JLabel lblPet1 = new JLabel("");
		JLabel lblPet3 = new JLabel("");
		
		int petNum = 1;
		ImageIcon petImage;
		String imageCode1 = "Images/NoPet.png";
		String imageCode2 = "Images/NoPet.png";
		String imageCode3 = "Images/NoPet.png";
		for (Pet animal: currentPlayer.petArray) {
			String imageCode = "Images/";
			if (petNum == 1) {
				if (animal instanceof Bird) {
					imageCode = imageCode + "Bird";
				}
				if (animal instanceof Cat) {
					imageCode = imageCode + "Cat";
				}
				if (animal instanceof CatDog) {
					imageCode = imageCode + "CatDog";
				}
				if (animal instanceof Dog) {
					imageCode = imageCode + "Dog";
				}
				if (animal instanceof Ocelot) {
					imageCode = imageCode + "Ocelot";
				}
				if (animal instanceof Tiger) {
					imageCode = imageCode + "Tiger";
				}
				imageCode1 = imageCode + "Active.png";
				petImage = new ImageIcon(imageCode1);
				lblPet1.setIcon(petImage);
				GridBagConstraints gbc_lblPet1 = new GridBagConstraints();
				gbc_lblPet1.insets = new Insets(0, 0, 0, 5);
				gbc_lblPet1.gridx = 3;
				gbc_lblPet1.gridy = 0;
				panel.add(lblPet1, gbc_lblPet1);
				if (animal.isDead()) {
					lblPet1.setEnabled(false);
				}
			}
			if (petNum == 2) {
				if (animal instanceof Bird) {
					imageCode = imageCode + "Bird";
				}
				if (animal instanceof Cat) {
					imageCode = imageCode + "Cat";
				}
				if (animal instanceof CatDog) {
					imageCode = imageCode + "CatDog";
				}
				if (animal instanceof Dog) {
					imageCode = imageCode + "Dog";
				}
				if (animal instanceof Ocelot) {
					imageCode = imageCode + "Ocelot";
				}
				if (animal instanceof Tiger) {
					imageCode = imageCode + "Tiger";
				}
				imageCode2 = imageCode + ".png";
				petImage = new ImageIcon(imageCode2);
				lblPet2.setIcon(petImage);
				GridBagConstraints gbc_lblPet2 = new GridBagConstraints();
				gbc_lblPet2.insets = new Insets(0, 0, 0, 5);
				gbc_lblPet2.gridx = 4;
				gbc_lblPet2.gridy = 0;
				panel.add(lblPet2, gbc_lblPet2);
				if (animal.isDead()) {
					lblPet2.setEnabled(false);
				}
			}
			if (petNum == 3) {
				if (animal instanceof Bird) {
					imageCode = imageCode + "Bird";
				}
				if (animal instanceof Cat) {
					imageCode = imageCode + "Cat";
				}
				if (animal instanceof CatDog) {
					imageCode = imageCode + "CatDog";
				}
				if (animal instanceof Dog) {
					imageCode = imageCode + "Dog";
				}
				if (animal instanceof Ocelot) {
					imageCode = imageCode + "Ocelot";
				}
				if (animal instanceof Tiger) {
					imageCode = imageCode + "Tiger";
				}
				imageCode3 = imageCode + ".png";
				petImage = new ImageIcon(imageCode3);
				lblPet3.setIcon(petImage);
				GridBagConstraints gbc_lblPet3 = new GridBagConstraints();
				gbc_lblPet3.gridx = 5;
				gbc_lblPet3.gridy = 0;
				panel.add(lblPet3, gbc_lblPet3);
				if (animal.isDead()) {
					lblPet3.setEnabled(false);
				}
			}
			petNum++;
		}
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(173, 216, 230));
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.gridheight = 9;
		gbc_panel_1.gridwidth = 3;
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 4;
		frame.getContentPane().add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0, 0, 0, 20, 0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel lblPlayer = new JLabel("Player: " + currentPlayer.getName());
		lblPlayer.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		GridBagConstraints gbc_lblPlayer = new GridBagConstraints();
		gbc_lblPlayer.anchor = GridBagConstraints.WEST;
		gbc_lblPlayer.insets = new Insets(0, 0, 5, 0);
		gbc_lblPlayer.gridx = 1;
		gbc_lblPlayer.gridy = 1;
		panel_1.add(lblPlayer, gbc_lblPlayer);
		
		JLabel lblDay = new JLabel("Day: " + Integer.toString(day));
		lblDay.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		GridBagConstraints gbc_lblDay = new GridBagConstraints();
		gbc_lblDay.insets = new Insets(0, 0, 5, 0);
		gbc_lblDay.anchor = GridBagConstraints.WEST;
		gbc_lblDay.gridx = 1;
		gbc_lblDay.gridy = 2;
		panel_1.add(lblDay, gbc_lblDay);
		
		JLabel lblMoney = new JLabel("Money: $" + Integer.toString(currentPlayer.getMoney()));
		lblMoney.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		GridBagConstraints gbc_lblMoney = new GridBagConstraints();
		gbc_lblMoney.insets = new Insets(0, 0, 5, 0);
		gbc_lblMoney.anchor = GridBagConstraints.WEST;
		gbc_lblMoney.gridx = 1;
		gbc_lblMoney.gridy = 3;
		panel_1.add(lblMoney, gbc_lblMoney);
		
		JLabel lblScore = new JLabel("Current score: " + Integer.toString(currentPlayer.getScore()) + " points");
		lblScore.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		GridBagConstraints gbc_lblScore = new GridBagConstraints();
		gbc_lblScore.insets = new Insets(0, 0, 5, 0);
		gbc_lblScore.anchor = GridBagConstraints.WEST;
		gbc_lblScore.gridx = 1;
		gbc_lblScore.gridy = 4;
		panel_1.add(lblScore, gbc_lblScore);
		
		JLabel lblInventory = new JLabel("Inventory:");
		lblInventory.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		GridBagConstraints gbc_lblInventory = new GridBagConstraints();
		gbc_lblInventory.insets = new Insets(0, 0, 5, 0);
		gbc_lblInventory.anchor = GridBagConstraints.WEST;
		gbc_lblInventory.gridx = 1;
		gbc_lblInventory.gridy = 6;
		panel_1.add(lblInventory, gbc_lblInventory);
		
		String itemName = "";
		ArrayList<String> itemNames = new ArrayList<String>(0);
		ArrayList<String> items = new ArrayList<String>(0);
		for (Item item:currentPlayer.inventory) {
			itemName = item.getName();
			itemNames.add(itemName);
			if (itemNames.contains(itemName) == false) {
				itemNames.add(itemName);
			}
		}
		for (String name:itemNames) {
			int occurrences = Collections.frequency(items, name);
			itemName = name + " x" + Integer.toString(occurrences) + "\r\n*";
		}
		JTextArea txtInventoryList = new JTextArea();
		txtInventoryList.setBackground(new Color(173, 216, 230));
		txtInventoryList.setText(itemName);
		GridBagConstraints gbc_txtInventoryList = new GridBagConstraints();
		gbc_txtInventoryList.fill = GridBagConstraints.BOTH;
		gbc_txtInventoryList.gridx = 1;
		gbc_txtInventoryList.gridy = 7;
		panel_1.add(txtInventoryList, gbc_txtInventoryList);
		
		String box = "\u239A";
		Pet pet = currentPlayer.petArray.get(0);
		String petHappiness = String.join("", Collections.nCopies(pet.getHappiness(), box));
		String petBladder = String.join("", Collections.nCopies(pet.getBladder(), box));
		String petEnergy = String.join("", Collections.nCopies(pet.getEnergy(), box));
		String petHunger = String.join("", Collections.nCopies(pet.getHunger(), box));
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(173, 216, 230));
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.gridheight = 9;
		gbc_panel_2.gridwidth = 3;
		gbc_panel_2.insets = new Insets(0, 0, 5, 5);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 5;
		gbc_panel_2.gridy = 4;
		frame.getContentPane().add(panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_panel_2.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_2.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		JLabel lblPetName = new JLabel("Pet name: " + pet.getName());
		lblPetName.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		GridBagConstraints gbc_lblPetName = new GridBagConstraints();
		gbc_lblPetName.anchor = GridBagConstraints.WEST;
		gbc_lblPetName.gridwidth = 2;
		gbc_lblPetName.insets = new Insets(0, 0, 5, 5);
		gbc_lblPetName.gridx = 1;
		gbc_lblPetName.gridy = 1;
		panel_2.add(lblPetName, gbc_lblPetName);
		
		JLabel lblHappiness = new JLabel("Happiness: " + petHappiness);
		lblHappiness.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		GridBagConstraints gbc_lblHappiness = new GridBagConstraints();
		gbc_lblHappiness.anchor = GridBagConstraints.WEST;
		gbc_lblHappiness.gridwidth = 3;
		gbc_lblHappiness.insets = new Insets(0, 0, 5, 0);
		gbc_lblHappiness.gridx = 1;
		gbc_lblHappiness.gridy = 2;
		panel_2.add(lblHappiness, gbc_lblHappiness);
		
		JLabel lblBladder = new JLabel("Bladder: " + petBladder);
		lblBladder.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		GridBagConstraints gbc_lblBladder = new GridBagConstraints();
		gbc_lblBladder.anchor = GridBagConstraints.WEST;
		gbc_lblBladder.gridwidth = 3;
		gbc_lblBladder.insets = new Insets(0, 0, 5, 0);
		gbc_lblBladder.gridx = 1;
		gbc_lblBladder.gridy = 3;
		panel_2.add(lblBladder, gbc_lblBladder);
		
		JLabel lblEnergy = new JLabel("Energy: " + petEnergy);
		lblEnergy.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		GridBagConstraints gbc_lblEnergy = new GridBagConstraints();
		gbc_lblEnergy.insets = new Insets(0, 0, 5, 0);
		gbc_lblEnergy.anchor = GridBagConstraints.WEST;
		gbc_lblEnergy.gridwidth = 3;
		gbc_lblEnergy.gridx = 1;
		gbc_lblEnergy.gridy = 4;
		panel_2.add(lblEnergy, gbc_lblEnergy);
		
		JLabel lblHunger = new JLabel("Hunger: " + petHunger);
		lblHunger.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		GridBagConstraints gbc_lblHunger = new GridBagConstraints();
		gbc_lblHunger.anchor = GridBagConstraints.WEST;
		gbc_lblHunger.gridwidth = 2;
		gbc_lblHunger.insets = new Insets(0, 0, 5, 5);
		gbc_lblHunger.gridx = 1;
		gbc_lblHunger.gridy = 5;
		panel_2.add(lblHunger, gbc_lblHunger);
		
		JLabel lblWeight = new JLabel("Weight: " + Integer.toString(pet.getWeight()) + " kg");
		lblWeight.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		GridBagConstraints gbc_lblWeight = new GridBagConstraints();
		gbc_lblWeight.anchor = GridBagConstraints.WEST;
		gbc_lblWeight.gridwidth = 2;
		gbc_lblWeight.insets = new Insets(0, 0, 5, 5);
		gbc_lblWeight.gridx = 1;
		gbc_lblWeight.gridy = 6;
		panel_2.add(lblWeight, gbc_lblWeight);
		String condition = "Healthy";
		if(pet.isMisbehave()) {
			condition = "Misbehaving";
		}
		if (pet.isSick()) {
			condition = "Sick";
		}
		if (pet.isDead()) {
			condition = "Dead";
		}
		JLabel lblCondition = new JLabel("Condition: " + condition);
		lblCondition.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblCondition.setBackground(SystemColor.control);
		GridBagConstraints gbc_lblCondition = new GridBagConstraints();
		gbc_lblCondition.anchor = GridBagConstraints.WEST;
		gbc_lblCondition.gridwidth = 2;
		gbc_lblCondition.insets = new Insets(0, 0, 5, 5);
		gbc_lblCondition.gridx = 1;
		gbc_lblCondition.gridy = 7;
		panel_2.add(lblCondition, gbc_lblCondition);
		
		JLabel lblAction = new JLabel("Action: " + Integer.toString(pet.getActionsRemaning()) + " turns");
		lblAction.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		GridBagConstraints gbc_lblAction = new GridBagConstraints();
		gbc_lblAction.anchor = GridBagConstraints.WEST;
		gbc_lblAction.gridwidth = 2;
		gbc_lblAction.insets = new Insets(0, 0, 5, 5);
		gbc_lblAction.gridx = 1;
		gbc_lblAction.gridy = 8;
		panel_2.add(lblAction, gbc_lblAction);
		
		JLabel lblFavouriteFood = new JLabel("Favourite Food: " + pet.getFavouriteFood());
		lblFavouriteFood.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		GridBagConstraints gbc_lblFavouriteFood = new GridBagConstraints();
		gbc_lblFavouriteFood.anchor = GridBagConstraints.WEST;
		gbc_lblFavouriteFood.gridwidth = 2;
		gbc_lblFavouriteFood.insets = new Insets(0, 0, 5, 5);
		gbc_lblFavouriteFood.gridx = 1;
		gbc_lblFavouriteFood.gridy = 9;
		panel_2.add(lblFavouriteFood, gbc_lblFavouriteFood);
		
		JLabel lblFavouriteToy = new JLabel("Favourite Toy: " + pet.getFavouriteToy());
		lblFavouriteToy.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		GridBagConstraints gbc_lblFavouriteToy = new GridBagConstraints();
		gbc_lblFavouriteToy.anchor = GridBagConstraints.WEST;
		gbc_lblFavouriteToy.gridwidth = 2;
		gbc_lblFavouriteToy.insets = new Insets(0, 0, 0, 5);
		gbc_lblFavouriteToy.gridx = 1;
		gbc_lblFavouriteToy.gridy = 10;
		panel_2.add(lblFavouriteToy, gbc_lblFavouriteToy);
		lblShop.setIcon(new ImageIcon("Images/ShopButton.png"));
		GridBagConstraints gbc_lblShop = new GridBagConstraints();
		gbc_lblShop.insets = new Insets(0, 0, 0, 5);
		gbc_lblShop.gridx = 1;
		gbc_lblShop.gridy = 14;
		
		lblPet1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				Pet pet1 = currentPlayer.petArray.get(0);
				String pet1Happiness = String.join("", Collections.nCopies(pet1.getHappiness(), box));
				String pet1Bladder = String.join("", Collections.nCopies(pet1.getBladder(), box));
				String pet1Energy = String.join("", Collections.nCopies(pet1.getEnergy(), box));
				String pet1Hunger = String.join("", Collections.nCopies(pet1.getHunger(), box));
				lblPetName.setText("Pet name: " + pet1.getName());
				lblHappiness.setText("Happiness: " + pet1Happiness);
				lblBladder.setText("Bladder: " + pet1Bladder);
				lblEnergy.setText("Energy: " + pet1Energy);
				lblHunger.setText("Hunger: " + pet1Hunger);
				lblWeight.setText("Weight: " + Integer.toString(pet1.getWeight()) + " kg");
				String condition = "Healthy";
				if(pet1.isMisbehave()) {
					condition = "Misbehaving";
				}
				if (pet1.isSick()) {
					condition = "Sick";
				}
				if (pet1.isDead()) {
					condition = "Dead";
				}
				lblCondition.setText("Condition: " + condition);
				lblAction.setText("Action: " + Integer.toString(pet1.getActionsRemaning()) + " turns");
				lblFavouriteFood.setText("Favourite Food: " + pet1.getFavouriteFood());
				lblFavouriteToy.setText("Favourite Toy: " + pet1.getFavouriteToy());
				int petNum = 1;
				for (Pet pet: currentPlayer.petArray){
					String imageCode = "Images/";
					if (pet instanceof Bird) {
						imageCode = imageCode + "Bird";
					}
					if (pet instanceof Cat) {
						imageCode = imageCode + "Cat";
					}
					if (pet instanceof CatDog) {
						imageCode = imageCode + "CatDog";
					}
					if (pet instanceof Dog) {
						imageCode = imageCode + "Dog";
					}
					if (pet instanceof Ocelot) {
						imageCode = imageCode + "Ocelot";
					}
					if (pet instanceof Tiger) {
						imageCode = imageCode + "Tiger";
					}
					if (petNum == 1) {
						lblPet1.setIcon(new ImageIcon(imageCode + "Active.png"));
					}
					if (petNum == 2) {
						lblPet2.setIcon(new ImageIcon(imageCode + ".png"));
					}
					if (petNum == 3) {
						lblPet3.setIcon(new ImageIcon(imageCode + ".png"));
					}
					petNum++;
				}
			}
		});
		lblPet2.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Pet pet1 = currentPlayer.petArray.get(1);
				String pet1Happiness = String.join("", Collections.nCopies(pet1.getHappiness(), box));
				String pet1Bladder = String.join("", Collections.nCopies(pet1.getBladder(), box));
				String pet1Energy = String.join("", Collections.nCopies(pet1.getEnergy(), box));
				String pet1Hunger = String.join("", Collections.nCopies(pet1.getHunger(), box));
				lblPetName.setText("Pet name: " + pet1.getName());
				lblHappiness.setText("Happiness: " + pet1Happiness);
				lblBladder.setText("Bladder: " + pet1Bladder);
				lblEnergy.setText("Energy: " + pet1Energy);
				lblHunger.setText("Hunger: " + pet1Hunger);
				lblWeight.setText("Weight: " + Integer.toString(pet1.getWeight()) + " kg");
				String condition = "Healthy";
				if(pet1.isMisbehave()) {
					condition = "Misbehaving";
				}
				if (pet1.isSick()) {
					condition = "Sick";
				}
				if (pet1.isDead()) {
					condition = "Dead";
				}
				lblCondition.setText("Condition: " + condition);
				lblAction.setText("Action: " + Integer.toString(pet1.getActionsRemaning()) + " turns");
				lblFavouriteFood.setText("Favourite Food: " + pet1.getFavouriteFood());
				lblFavouriteToy.setText("Favourite Toy: " + pet1.getFavouriteToy());
				int petNum = 1;
				for (Pet pet: currentPlayer.petArray){
					String imageCode = "Images/";
					if (pet instanceof Bird) {
						imageCode = imageCode + "Bird";
					}
					if (pet instanceof Cat) {
						imageCode = imageCode + "Cat";
					}
					if (pet instanceof CatDog) {
						imageCode = imageCode + "CatDog";
					}
					if (pet instanceof Dog) {
						imageCode = imageCode + "Dog";
					}
					if (pet instanceof Ocelot) {
						imageCode = imageCode + "Ocelot";
					}
					if (pet instanceof Tiger) {
						imageCode = imageCode + "Tiger";
					}
					if (petNum == 1) {
						lblPet1.setIcon(new ImageIcon(imageCode + ".png"));
					}
					if (petNum == 2) {
						lblPet2.setIcon(new ImageIcon(imageCode + "Active.png"));
					}
					if (petNum == 3) {
						lblPet3.setIcon(new ImageIcon(imageCode + ".png"));
					}
					petNum++;
				}
				
			}
		});
		
		lblPet3.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Pet pet1 = currentPlayer.petArray.get(2);
				String pet1Happiness = String.join("", Collections.nCopies(pet1.getHappiness(), box));
				String pet1Bladder = String.join("", Collections.nCopies(pet1.getBladder(), box));
				String pet1Energy = String.join("", Collections.nCopies(pet1.getEnergy(), box));
				String pet1Hunger = String.join("", Collections.nCopies(pet1.getHunger(), box));
				lblPetName.setText("Pet name: " + pet1.getName());
				lblHappiness.setText("Happiness: " + pet1Happiness);
				lblBladder.setText("Bladder: " + pet1Bladder);
				lblEnergy.setText("Energy: " + pet1Energy);
				lblHunger.setText("Hunger: " + pet1Hunger);
				lblWeight.setText("Weight: " + Integer.toString(pet1.getWeight()) + " kg");
				String condition = "Healthy";
				if(pet1.isMisbehave()) {
					condition = "Misbehaving";
				}
				if (pet1.isSick()) {
					condition = "Sick";
				}
				if (pet1.isDead()) {
					condition = "Dead";
				}
				lblCondition.setText("Condition: " + condition);
				lblAction.setText("Action: " + Integer.toString(pet1.getActionsRemaning()) + " turns");
				lblFavouriteFood.setText("Favourite Food: " + pet1.getFavouriteFood());
				lblFavouriteToy.setText("Favourite Toy: " + pet1.getFavouriteToy());
				int petNum = 1;
				for (Pet pet: currentPlayer.petArray){
					String imageCode = "Images/";
					if (pet instanceof Bird) {
						imageCode = imageCode + "Bird";
					}
					if (pet instanceof Cat) {
						imageCode = imageCode + "Cat";
					}
					if (pet instanceof CatDog) {
						imageCode = imageCode + "CatDog";
					}
					if (pet instanceof Dog) {
						imageCode = imageCode + "Dog";
					}
					if (pet instanceof Ocelot) {
						imageCode = imageCode + "Ocelot";
					}
					if (pet instanceof Tiger) {
						imageCode = imageCode + "Tiger";
					}
					if (petNum == 1) {
						lblPet1.setIcon(new ImageIcon(imageCode + ".png"));
					}
					if (petNum == 2) {
						lblPet2.setIcon(new ImageIcon(imageCode + ".png"));
					}
					if (petNum == 3) {
						lblPet3.setIcon(new ImageIcon(imageCode + "Active.png"));
					}
					petNum++;
				}
			}
		});
		frame.getContentPane().add(lblShop, gbc_lblShop);
		
		JLabel lblToilet = new JLabel("");
		lblToilet.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		lblToilet.setIcon(new ImageIcon("Images/ToiletButton.png"));
		GridBagConstraints gbc_lblToilet = new GridBagConstraints();
		gbc_lblToilet.insets = new Insets(0, 0, 0, 5);
		gbc_lblToilet.gridx = 2;
		gbc_lblToilet.gridy = 14;
		frame.getContentPane().add(lblToilet, gbc_lblToilet);
		
		JLabel lblFeed = new JLabel("");
		lblFeed.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		lblFeed.setIcon(new ImageIcon("Images/FoodButton.png"));
		GridBagConstraints gbc_lblFeed = new GridBagConstraints();
		gbc_lblFeed.insets = new Insets(0, 0, 0, 5);
		gbc_lblFeed.gridx = 3;
		gbc_lblFeed.gridy = 14;
		frame.getContentPane().add(lblFeed, gbc_lblFeed);
		
		JLabel lblPlay = new JLabel("");
		lblPlay.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		lblPlay.setIcon(new ImageIcon("Images/PlayButton.png"));
		GridBagConstraints gbc_lblPlay = new GridBagConstraints();
		gbc_lblPlay.insets = new Insets(0, 0, 0, 5);
		gbc_lblPlay.gridx = 4;
		gbc_lblPlay.gridy = 14;
		frame.getContentPane().add(lblPlay, gbc_lblPlay);
		
		JLabel lblSleep = new JLabel("");
		lblSleep.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		lblSleep.setIcon(new ImageIcon("Images/SleepButton.png"));
		GridBagConstraints gbc_lblSleep = new GridBagConstraints();
		gbc_lblSleep.insets = new Insets(0, 0, 0, 5);
		gbc_lblSleep.gridx = 5;
		gbc_lblSleep.gridy = 14;
		frame.getContentPane().add(lblSleep, gbc_lblSleep);
		
		JLabel lblDiscipline = new JLabel("");
		lblDiscipline.setIcon(new ImageIcon("Images/DisplineButton.png"));
		GridBagConstraints gbc_lblDiscipline = new GridBagConstraints();
		gbc_lblDiscipline.insets = new Insets(0, 0, 0, 5);
		gbc_lblDiscipline.gridx = 6;
		gbc_lblDiscipline.gridy = 14;
		frame.getContentPane().add(lblDiscipline, gbc_lblDiscipline);
		
		JLabel lblNextDay = new JLabel("");
		lblNextDay.setIcon(new ImageIcon("Images/NextDayButton.png"));
		GridBagConstraints gbc_lblNextDay = new GridBagConstraints();
		gbc_lblNextDay.insets = new Insets(0, 0, 0, 5);
		gbc_lblNextDay.gridx = 7;
		gbc_lblNextDay.gridy = 14;
		lblNextDay.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				for (Pet pet: currentPlayer.petArray) {
					game.perDayScores(currentPlayer, pet);
					game.statDrops(pet);
				}
				frame.getContentPane().remove(panel);
				frame.getContentPane().remove(lblNextDay);
				frame.getContentPane().remove(lblDiscipline);
				frame.getContentPane().remove(lblSleep);
				frame.getContentPane().remove(lblPlay);
				frame.getContentPane().remove(lblFeed);
				frame.getContentPane().remove(lblToilet);
				frame.getContentPane().remove(lblFavouriteToy);
				frame.getContentPane().remove(lblFavouriteFood);
				frame.getContentPane().remove(lblAction);
				frame.getContentPane().remove(lblCondition);
				frame.getContentPane().remove(lblWeight);
				frame.getContentPane().remove(lblHunger);
				frame.getContentPane().remove(lblEnergy);
				frame.getContentPane().remove(lblBladder);
				frame.getContentPane().remove(lblHappiness);
				frame.getContentPane().remove(lblPetName);
				frame.getContentPane().remove(panel_2);
				frame.getContentPane().remove(txtInventoryList);
				frame.getContentPane().remove(lblInventory);
				frame.getContentPane().remove(lblScore);
				frame.getContentPane().remove(lblMoney);
				frame.getContentPane().remove(lblDay);
				frame.getContentPane().remove(lblPlayer);
				frame.getContentPane().remove(panel_1);
				frame.getContentPane().remove(lblPet3);
				frame.getContentPane().remove(lblPet1);
				frame.getContentPane().remove(lblPet2);
				frame.getContentPane().remove(lblPlayerName);
				frame.getContentPane().remove(lblShop);
				int currentPlayerIndex = game.playerArray.indexOf(currentPlayer);
				PlayDay(currentPlayerIndex + 1);
				return;
			}
		});
		
		frame.getContentPane().add(lblNextDay, gbc_lblNextDay);
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
