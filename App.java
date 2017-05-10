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
	private Pet currentPet;
	private Food currentFood;
	private Toy currentToy;
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
		else {
			displayScore();
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
			items.add(item.getName());
			if (itemNames.contains(item.getName()) == false) {
				itemNames.add(item.getName());
			}
		}
		for (String name:itemNames) {
			int occurrences = Collections.frequency(items, name);
			itemName = itemName + "*" + name + " x" + Integer.toString(occurrences) + "\r\n";
		}
		JTextArea txtInventoryList = new JTextArea();
		txtInventoryList.setBackground(new Color(173, 216, 230));
		txtInventoryList.setText(itemName);
		txtInventoryList.setEditable(false);
		GridBagConstraints gbc_txtInventoryList = new GridBagConstraints();
		gbc_txtInventoryList.fill = GridBagConstraints.BOTH;
		gbc_txtInventoryList.gridx = 1;
		gbc_txtInventoryList.gridy = 7;
		panel_1.add(txtInventoryList, gbc_txtInventoryList);
		
		String box = "\u239A";
		currentPet = currentPlayer.petArray.get(0);
		String petHappiness = String.join("", Collections.nCopies(currentPet.getHappiness(), box));
		String petBladder = String.join("", Collections.nCopies(currentPet.getBladder(), box));
		String petEnergy = String.join("", Collections.nCopies(currentPet.getEnergy(), box));
		String petHunger = String.join("", Collections.nCopies(currentPet.getHunger(), box));
		JPanel panelPetStat = new JPanel();
		panelPetStat.setBackground(new Color(173, 216, 230));
		GridBagConstraints gbc_panelPetStat = new GridBagConstraints();
		gbc_panelPetStat.gridheight = 9;
		gbc_panelPetStat.gridwidth = 3;
		gbc_panelPetStat.insets = new Insets(0, 0, 5, 5);
		gbc_panelPetStat.fill = GridBagConstraints.BOTH;
		gbc_panelPetStat.gridx = 5;
		gbc_panelPetStat.gridy = 4;
		frame.getContentPane().add(panelPetStat, gbc_panelPetStat);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_panel_2.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_2.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelPetStat.setLayout(gbl_panel_2);
		
		JLabel lblPetName = new JLabel("Pet name: " + currentPet.getName());
		lblPetName.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		GridBagConstraints gbc_lblPetName = new GridBagConstraints();
		gbc_lblPetName.anchor = GridBagConstraints.WEST;
		gbc_lblPetName.gridwidth = 2;
		gbc_lblPetName.insets = new Insets(0, 0, 5, 5);
		gbc_lblPetName.gridx = 1;
		gbc_lblPetName.gridy = 1;
		panelPetStat.add(lblPetName, gbc_lblPetName);
		
		JLabel lblHappiness = new JLabel("Happiness: " + petHappiness);
		lblHappiness.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		GridBagConstraints gbc_lblHappiness = new GridBagConstraints();
		gbc_lblHappiness.anchor = GridBagConstraints.WEST;
		gbc_lblHappiness.gridwidth = 3;
		gbc_lblHappiness.insets = new Insets(0, 0, 5, 0);
		gbc_lblHappiness.gridx = 1;
		gbc_lblHappiness.gridy = 2;
		panelPetStat.add(lblHappiness, gbc_lblHappiness);
		
		JLabel lblBladder = new JLabel("Bladder: " + petBladder);
		lblBladder.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		GridBagConstraints gbc_lblBladder = new GridBagConstraints();
		gbc_lblBladder.anchor = GridBagConstraints.WEST;
		gbc_lblBladder.gridwidth = 3;
		gbc_lblBladder.insets = new Insets(0, 0, 5, 0);
		gbc_lblBladder.gridx = 1;
		gbc_lblBladder.gridy = 3;
		panelPetStat.add(lblBladder, gbc_lblBladder);
		
		JLabel lblEnergy = new JLabel("Energy: " + petEnergy);
		lblEnergy.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		GridBagConstraints gbc_lblEnergy = new GridBagConstraints();
		gbc_lblEnergy.insets = new Insets(0, 0, 5, 0);
		gbc_lblEnergy.anchor = GridBagConstraints.WEST;
		gbc_lblEnergy.gridwidth = 3;
		gbc_lblEnergy.gridx = 1;
		gbc_lblEnergy.gridy = 4;
		panelPetStat.add(lblEnergy, gbc_lblEnergy);
		
		JLabel lblHunger = new JLabel("Hunger: " + petHunger);
		lblHunger.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		GridBagConstraints gbc_lblHunger = new GridBagConstraints();
		gbc_lblHunger.anchor = GridBagConstraints.WEST;
		gbc_lblHunger.gridwidth = 2;
		gbc_lblHunger.insets = new Insets(0, 0, 5, 5);
		gbc_lblHunger.gridx = 1;
		gbc_lblHunger.gridy = 5;
		panelPetStat.add(lblHunger, gbc_lblHunger);
		
		JLabel lblWeight = new JLabel("Weight: " + Integer.toString(currentPet.getWeight()) + " kg");
		lblWeight.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		GridBagConstraints gbc_lblWeight = new GridBagConstraints();
		gbc_lblWeight.anchor = GridBagConstraints.WEST;
		gbc_lblWeight.gridwidth = 2;
		gbc_lblWeight.insets = new Insets(0, 0, 5, 5);
		gbc_lblWeight.gridx = 1;
		gbc_lblWeight.gridy = 6;
		panelPetStat.add(lblWeight, gbc_lblWeight);
		
		String condition = "Healthy";
		JLabel lblCondition = new JLabel("Condition: " + condition);
		if(currentPet.isMisbehave()) {
			condition = "Misbehaving";
			lblCondition.setText("Condition: " + condition);
			lblCondition.setForeground(new Color(255, 255, 0));
		}
		if (currentPet.isSick()) {
			condition = "Sick";
			lblCondition.setText("Condition: " + condition);
			lblCondition.setForeground(new Color(255, 128, 64));
		}
		if (currentPet.isDead()) {
			condition = "Dead";
			lblCondition.setText("Condition: " + condition);
			lblCondition.setForeground(new Color(255, 0, 0));
		}
		lblCondition.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblCondition.setBackground(SystemColor.control);
		GridBagConstraints gbc_lblCondition = new GridBagConstraints();
		gbc_lblCondition.anchor = GridBagConstraints.WEST;
		gbc_lblCondition.gridwidth = 2;
		gbc_lblCondition.insets = new Insets(0, 0, 5, 5);
		gbc_lblCondition.gridx = 1;
		gbc_lblCondition.gridy = 7;
		panelPetStat.add(lblCondition, gbc_lblCondition);
		
		JLabel lblAction = new JLabel("Action: " + Integer.toString(currentPet.getActionsRemaning()) + " turns");
		lblAction.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		GridBagConstraints gbc_lblAction = new GridBagConstraints();
		gbc_lblAction.anchor = GridBagConstraints.WEST;
		gbc_lblAction.gridwidth = 2;
		gbc_lblAction.insets = new Insets(0, 0, 5, 5);
		gbc_lblAction.gridx = 1;
		gbc_lblAction.gridy = 8;
		panelPetStat.add(lblAction, gbc_lblAction);
		
		JLabel lblFavouriteFood = new JLabel("Favourite Food: " + currentPet.getFavouriteFood());
		lblFavouriteFood.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		GridBagConstraints gbc_lblFavouriteFood = new GridBagConstraints();
		gbc_lblFavouriteFood.anchor = GridBagConstraints.WEST;
		gbc_lblFavouriteFood.gridwidth = 2;
		gbc_lblFavouriteFood.insets = new Insets(0, 0, 5, 5);
		gbc_lblFavouriteFood.gridx = 1;
		gbc_lblFavouriteFood.gridy = 9;
		panelPetStat.add(lblFavouriteFood, gbc_lblFavouriteFood);
		
		JLabel lblFavouriteToy = new JLabel("Favourite Toy: " + currentPet.getFavouriteToy());
		lblFavouriteToy.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		GridBagConstraints gbc_lblFavouriteToy = new GridBagConstraints();
		gbc_lblFavouriteToy.anchor = GridBagConstraints.WEST;
		gbc_lblFavouriteToy.gridwidth = 2;
		gbc_lblFavouriteToy.insets = new Insets(0, 0, 0, 5);
		gbc_lblFavouriteToy.gridx = 1;
		gbc_lblFavouriteToy.gridy = 10;
		panelPetStat.add(lblFavouriteToy, gbc_lblFavouriteToy);
		lblShop.setIcon(new ImageIcon("Images/ShopButton.png"));
		GridBagConstraints gbc_lblShop = new GridBagConstraints();
		gbc_lblShop.insets = new Insets(0, 0, 0, 5);
		gbc_lblShop.gridx = 1;
		gbc_lblShop.gridy = 14;
		
		lblPet1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				currentPet = currentPlayer.petArray.get(0);
				String pet1Happiness = String.join("", Collections.nCopies(currentPet.getHappiness(), box));
				String pet1Bladder = String.join("", Collections.nCopies(currentPet.getBladder(), box));
				String pet1Energy = String.join("", Collections.nCopies(currentPet.getEnergy(), box));
				String pet1Hunger = String.join("", Collections.nCopies(currentPet.getHunger(), box));
				lblPetName.setText("Pet name: " + currentPet.getName());
				lblHappiness.setText("Happiness: " + pet1Happiness);
				lblBladder.setText("Bladder: " + pet1Bladder);
				lblEnergy.setText("Energy: " + pet1Energy);
				lblHunger.setText("Hunger: " + pet1Hunger);
				lblWeight.setText("Weight: " + Integer.toString(currentPet.getWeight()) + " kg");
				String condition = "Healthy";
				lblCondition.setText("Condition: " + condition);
				if(currentPet.isMisbehave()) {
					condition = "Misbehaving";
					lblCondition.setText("Condition: " + condition);
					lblCondition.setForeground(new Color(255, 255, 0));
				}
				if (currentPet.isSick()) {
					condition = "Sick";
					lblCondition.setText("Condition: " + condition);
					lblCondition.setForeground(new Color(255, 128, 64));
				}
				if (currentPet.isDead()) {
					condition = "Dead";
					lblCondition.setText("Condition: " + condition);
					lblCondition.setForeground(new Color(255, 0, 0));
				}
				lblAction.setText("Action: " + Integer.toString(currentPet.getActionsRemaning()) + " turns");
				lblFavouriteFood.setText("Favourite Food: " + currentPet.getFavouriteFood());
				lblFavouriteToy.setText("Favourite Toy: " + currentPet.getFavouriteToy());
				int petNum = 1;
				for (Pet animal: currentPlayer.petArray){
					String imageCode = "Images/";
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
				currentPet = currentPlayer.petArray.get(1);
				String pet1Happiness = String.join("", Collections.nCopies(currentPet.getHappiness(), box));
				String pet1Bladder = String.join("", Collections.nCopies(currentPet.getBladder(), box));
				String pet1Energy = String.join("", Collections.nCopies(currentPet.getEnergy(), box));
				String pet1Hunger = String.join("", Collections.nCopies(currentPet.getHunger(), box));
				lblPetName.setText("Pet name: " + currentPet.getName());
				lblHappiness.setText("Happiness: " + pet1Happiness);
				lblBladder.setText("Bladder: " + pet1Bladder);
				lblEnergy.setText("Energy: " + pet1Energy);
				lblHunger.setText("Hunger: " + pet1Hunger);
				lblWeight.setText("Weight: " + Integer.toString(currentPet.getWeight()) + " kg");
				String condition = "Healthy";
				lblCondition.setText("Condition: " + condition);
				if(currentPet.isMisbehave()) {
					condition = "Misbehaving";
					lblCondition.setText("Condition: " + condition);
					lblCondition.setForeground(new Color(255, 255, 0));
				}
				if (currentPet.isSick()) {
					condition = "Sick";
					lblCondition.setText("Condition: " + condition);
					lblCondition.setForeground(new Color(255, 128, 64));
				}
				if (currentPet.isDead()) {
					condition = "Dead";
					lblCondition.setText("Condition: " + condition);
					lblCondition.setForeground(new Color(255, 0, 0));
				}
				lblAction.setText("Action: " + Integer.toString(currentPet.getActionsRemaning()) + " turns");
				lblFavouriteFood.setText("Favourite Food: " + currentPet.getFavouriteFood());
				lblFavouriteToy.setText("Favourite Toy: " + currentPet.getFavouriteToy());
				int petNum = 1;
				for (Pet animal: currentPlayer.petArray){
					String imageCode = "Images/";
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
				currentPet = currentPlayer.petArray.get(2);
				String pet1Happiness = String.join("", Collections.nCopies(currentPet.getHappiness(), box));
				String pet1Bladder = String.join("", Collections.nCopies(currentPet.getBladder(), box));
				String pet1Energy = String.join("", Collections.nCopies(currentPet.getEnergy(), box));
				String pet1Hunger = String.join("", Collections.nCopies(currentPet.getHunger(), box));
				lblPetName.setText("Pet name: " + currentPet.getName());
				lblHappiness.setText("Happiness: " + pet1Happiness);
				lblBladder.setText("Bladder: " + pet1Bladder);
				lblEnergy.setText("Energy: " + pet1Energy);
				lblHunger.setText("Hunger: " + pet1Hunger);
				lblWeight.setText("Weight: " + Integer.toString(currentPet.getWeight()) + " kg");
				String condition = "Healthy";
				lblCondition.setText("Condition: " + condition);
				if(currentPet.isMisbehave()) {
					condition = "Misbehaving";
					lblCondition.setText("Condition: " + condition);
					lblCondition.setForeground(new Color(255, 255, 0));
				}
				if (currentPet.isSick()) {
					condition = "Sick";
					lblCondition.setText("Condition: " + condition);
					lblCondition.setForeground(new Color(255, 128, 64));
				}
				if (currentPet.isDead()) {
					condition = "Dead";
					lblCondition.setText("Condition: " + condition);
					lblCondition.setForeground(new Color(255, 0, 0));
				}
				lblCondition.setText("Condition: " + condition);
				lblAction.setText("Action: " + Integer.toString(currentPet.getActionsRemaning()) + " turns");
				lblFavouriteFood.setText("Favourite Food: " + currentPet.getFavouriteFood());
				lblFavouriteToy.setText("Favourite Toy: " + currentPet.getFavouriteToy());
				int petNum = 1;
				for (Pet animal: currentPlayer.petArray){
					String imageCode = "Images/";
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
		lblFeed.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
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
				frame.getContentPane().remove(panelPetStat);
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
				int actionRemaining = currentPet.getActionsRemaning();
				if (actionRemaining > 0) {
					currentPet.setActionsRemaining(actionRemaining - 1);
					feed();
				}
				else {
					tiredDisplay();
				}
			}
		});
		lblSleep.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
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
				frame.getContentPane().remove(panelPetStat);
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
				int actionRemaining = currentPet.getActionsRemaning();
				if (actionRemaining > 0) {
					currentPet.setActionsRemaining(actionRemaining - 1);
					displaySleep();
				}
				else {
					tiredDisplay();
				}
			}
		});
		lblDiscipline.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
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
				frame.getContentPane().remove(panelPetStat);
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
				int actionRemaining = currentPet.getActionsRemaning();
				if (actionRemaining > 0) {
					currentPet.setActionsRemaining(actionRemaining - 1);
					displayDiscipline();
				}
				else {
					tiredDisplay();
				}
			}
		});
		lblToilet.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
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
				frame.getContentPane().remove(panelPetStat);
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
				int actionRemaining = currentPet.getActionsRemaning();
				if (actionRemaining > 0) {
					currentPet.setActionsRemaining(actionRemaining - 1);
					displayToilet();
				}
				else {
					tiredDisplay();
				}
			}
		});
		lblShop.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
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
				frame.getContentPane().remove(panelPetStat);
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
				useShop();
				}
			});
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
				frame.getContentPane().remove(panelPetStat);
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
		frame.setSize(575, 475);
		frame.setVisible(true);

	
	}
	private void tiredDisplay() {
		frame.setBackground(new Color(0, 0, 0));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{454, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frame.setLayout(gridBagLayout);
		
		JLabel lblMessage1 = new JLabel(currentPet.getName() + " is mentally tired.");
		lblMessage1.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblMessage1.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblMessage1 = new GridBagConstraints();
		gbc_lblMessage1.insets = new Insets(0, 0, 5, 0);
		gbc_lblMessage1.gridx = 0;
		gbc_lblMessage1.gridy = 0;
		frame.getContentPane().add(lblMessage1, gbc_lblMessage1);
		
		JLabel labelMessage2 = new JLabel(currentPet.getName() + " is physically tired.");
		labelMessage2.setHorizontalAlignment(SwingConstants.CENTER);
		labelMessage2.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		GridBagConstraints gbc_labelMessage2 = new GridBagConstraints();
		gbc_labelMessage2.insets = new Insets(0, 0, 5, 0);
		gbc_labelMessage2.gridx = 0;
		gbc_labelMessage2.gridy = 1;
		frame.getContentPane().add(labelMessage2, gbc_labelMessage2);
		
		JLabel labelMessage3 = new JLabel(currentPet.getName() + " is emotionally tired.");
		labelMessage3.setHorizontalAlignment(SwingConstants.CENTER);
		labelMessage3.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		GridBagConstraints gbc_labelMessage3 = new GridBagConstraints();
		gbc_labelMessage3.insets = new Insets(0, 0, 5, 0);
		gbc_labelMessage3.gridx = 0;
		gbc_labelMessage3.gridy = 2;
		frame.getContentPane().add(labelMessage3, gbc_labelMessage3);
		
		JLabel lblEmptyString = new JLabel("");
		lblEmptyString.setFont(new Font("Tahoma", Font.PLAIN, 24));
		GridBagConstraints gbc_lblEmptyString = new GridBagConstraints();
		gbc_lblEmptyString.insets = new Insets(0, 0, 5, 0);
		gbc_lblEmptyString.gridx = 0;
		gbc_lblEmptyString.gridy = 3;
		frame.getContentPane().add(lblEmptyString, gbc_lblEmptyString);
		
		JLabel lblMessage4 = new JLabel("Please choose a different pet!");
		lblMessage4.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		GridBagConstraints gbc_lblMessage4 = new GridBagConstraints();
		gbc_lblMessage4.insets = new Insets(0, 0, 5, 0);
		gbc_lblMessage4.gridx = 0;
		gbc_lblMessage4.gridy = 4;
		frame.getContentPane().add(lblMessage4, gbc_lblMessage4);
		
		JLabel lblYesBtn = new JLabel("");
		lblYesBtn.setIcon(new ImageIcon("Images/Yes.PNG"));
		GridBagConstraints gbc_lblYesBtn = new GridBagConstraints();
		gbc_lblYesBtn.gridx = 0;
		gbc_lblYesBtn.gridy = 6;
		frame.getContentPane().add(lblYesBtn, gbc_lblYesBtn);
		frame.pack();
		frame.setVisible(true);
		lblYesBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				frame.getContentPane().remove(lblYesBtn);
				frame.getContentPane().remove(lblMessage4);
				frame.getContentPane().remove(lblEmptyString);
				frame.getContentPane().remove(labelMessage3);
				frame.getContentPane().remove(labelMessage2);
				frame.getContentPane().remove(lblMessage1);
				StoryBoard();
			}
		});
	}
	private void displayToilet() {
		game.useToilet(currentPet);
		frame.setBackground(new Color(240, 255, 255));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		frame.setLayout(gridBagLayout);
		
		JPanel panelPicture = new JPanel();
		panelPicture.setBackground(new Color(240, 255, 255));
		GridBagConstraints gbc_panelPicture = new GridBagConstraints();
		gbc_panelPicture.gridheight = 7;
		gbc_panelPicture.gridwidth = 7;
		gbc_panelPicture.insets = new Insets(0, 0, 5, 0);
		gbc_panelPicture.fill = GridBagConstraints.BOTH;
		gbc_panelPicture.gridx = 0;
		gbc_panelPicture.gridy = 0;
		frame.getContentPane().add(panelPicture, gbc_panelPicture);
		panelPicture.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lblToilet = new JLabel("");
		lblToilet.setBackground(new Color(240, 255, 255));
		lblToilet.setIcon(new ImageIcon("Images/Toilet.PNG"));
		panelPicture.add(lblToilet);
		
		JPanel panelMessage = new JPanel();
		panelMessage.setBackground(new Color(240, 255, 255));
		GridBagConstraints gbc_panelMessage = new GridBagConstraints();
		gbc_panelMessage.gridheight = 2;
		gbc_panelMessage.gridwidth = 7;
		gbc_panelMessage.fill = GridBagConstraints.BOTH;
		gbc_panelMessage.gridx = 0;
		gbc_panelMessage.gridy = 7;
		frame.getContentPane().add(panelMessage, gbc_panelMessage);
		GridBagLayout gbl_panelMessage = new GridBagLayout();
		gbl_panelMessage.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panelMessage.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panelMessage.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panelMessage.rowWeights = new double[]{1.0, 0.0, 0.0, Double.MIN_VALUE};
		panelMessage.setLayout(gbl_panelMessage);
		
		JTextPane txtpnMessage = new JTextPane();
		txtpnMessage.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		txtpnMessage.setText(currentPlayer.getName() + " sent " + currentPet.getName() + " to the toilet.\r\n" 
				+ currentPet.getName() + " feels relief.");
		txtpnMessage.setBackground(new Color(240, 255, 255));
		txtpnMessage.setForeground(new Color(0, 0, 0));
		GridBagConstraints gbc_txtpnMessage = new GridBagConstraints();
		gbc_txtpnMessage.gridwidth = 11;
		gbc_txtpnMessage.gridheight = 3;
		gbc_txtpnMessage.insets = new Insets(0, 0, 0, 5);
		gbc_txtpnMessage.fill = GridBagConstraints.BOTH;
		gbc_txtpnMessage.gridx = 0;
		gbc_txtpnMessage.gridy = 0;
		panelMessage.add(txtpnMessage, gbc_txtpnMessage);
		
		JLabel lblYesBtn = new JLabel("");
		lblYesBtn.setIcon(new ImageIcon("Images/Yes.PNG"));
		GridBagConstraints gbc_lblYesBtn = new GridBagConstraints();
		gbc_lblYesBtn.gridwidth = 3;
		gbc_lblYesBtn.gridheight = 3;
		gbc_lblYesBtn.insets = new Insets(0, 0, 5, 5);
		gbc_lblYesBtn.gridx = 11;
		gbc_lblYesBtn.gridy = 0;
		panelMessage.add(lblYesBtn, gbc_lblYesBtn);
		frame.pack();
		frame.setVisible(true);
		lblYesBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				frame.getContentPane().remove(lblYesBtn);
				frame.getContentPane().remove(txtpnMessage);
				frame.getContentPane().remove(panelMessage);
				frame.getContentPane().remove(lblToilet);
				frame.getContentPane().remove(panelPicture);
				StoryBoard();
			}
		});
	}
	private void displayDiscipline() {
		game.discipline(currentPet);
		frame.setBackground(new Color(240, 255, 255));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		frame.setLayout(gridBagLayout);
		
		JPanel panelPicture = new JPanel();
		panelPicture.setBackground(new Color(240, 255, 255));
		GridBagConstraints gbc_panelPicture = new GridBagConstraints();
		gbc_panelPicture.gridheight = 7;
		gbc_panelPicture.gridwidth = 7;
		gbc_panelPicture.insets = new Insets(0, 0, 5, 0);
		gbc_panelPicture.fill = GridBagConstraints.BOTH;
		gbc_panelPicture.gridx = 0;
		gbc_panelPicture.gridy = 0;
		frame.getContentPane().add(panelPicture, gbc_panelPicture);
		panelPicture.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lblDiscipline = new JLabel("");
		lblDiscipline.setBackground(new Color(240, 255, 255));
		lblDiscipline.setIcon(new ImageIcon("Images/Displine.PNG"));
		panelPicture.add(lblDiscipline);
		
		JPanel panelMessage = new JPanel();
		panelMessage.setBackground(new Color(240, 255, 255));
		GridBagConstraints gbc_panelMessage = new GridBagConstraints();
		gbc_panelMessage.gridheight = 2;
		gbc_panelMessage.gridwidth = 7;
		gbc_panelMessage.fill = GridBagConstraints.BOTH;
		gbc_panelMessage.gridx = 0;
		gbc_panelMessage.gridy = 7;
		frame.getContentPane().add(panelMessage, gbc_panelMessage);
		GridBagLayout gbl_panelMessage = new GridBagLayout();
		gbl_panelMessage.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panelMessage.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panelMessage.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panelMessage.rowWeights = new double[]{1.0, 0.0, 0.0, Double.MIN_VALUE};
		panelMessage.setLayout(gbl_panelMessage);
		
		JTextPane txtpnMessage = new JTextPane();
		txtpnMessage.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		txtpnMessage.setText(currentPlayer.getName() + " sent " + currentPet.getName() + " to the naughty corner.\r\n" 
				+ currentPet.getName() + " is starting to behave now but is feeling unhappy.");
		txtpnMessage.setBackground(new Color(240, 255, 255));
		txtpnMessage.setForeground(new Color(0, 0, 0));
		GridBagConstraints gbc_txtpnMessage = new GridBagConstraints();
		gbc_txtpnMessage.gridwidth = 11;
		gbc_txtpnMessage.gridheight = 3;
		gbc_txtpnMessage.insets = new Insets(0, 0, 0, 5);
		gbc_txtpnMessage.fill = GridBagConstraints.BOTH;
		gbc_txtpnMessage.gridx = 0;
		gbc_txtpnMessage.gridy = 0;
		panelMessage.add(txtpnMessage, gbc_txtpnMessage);
		
		JLabel lblYesBtn = new JLabel("");
		lblYesBtn.setIcon(new ImageIcon("Images/Yes.PNG"));
		GridBagConstraints gbc_lblYesBtn = new GridBagConstraints();
		gbc_lblYesBtn.gridwidth = 3;
		gbc_lblYesBtn.gridheight = 3;
		gbc_lblYesBtn.insets = new Insets(0, 0, 5, 5);
		gbc_lblYesBtn.gridx = 11;
		gbc_lblYesBtn.gridy = 0;
		panelMessage.add(lblYesBtn, gbc_lblYesBtn);
		frame.pack();
		frame.setVisible(true);
		lblYesBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				frame.getContentPane().remove(lblYesBtn);
				frame.getContentPane().remove(txtpnMessage);
				frame.getContentPane().remove(panelMessage);
				frame.getContentPane().remove(lblDiscipline);
				frame.getContentPane().remove(panelPicture);

				StoryBoard();
			}
		});
	}
	private void displaySleep() {
		game.sleep(currentPet);
		frame.setBackground(new Color(240, 255, 255));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		frame.setLayout(gridBagLayout);
		
		JPanel panelPicture = new JPanel();
		panelPicture.setBackground(new Color(240, 255, 255));
		GridBagConstraints gbc_panelPicture = new GridBagConstraints();
		gbc_panelPicture.gridheight = 7;
		gbc_panelPicture.gridwidth = 7;
		gbc_panelPicture.insets = new Insets(0, 0, 5, 0);
		gbc_panelPicture.fill = GridBagConstraints.BOTH;
		gbc_panelPicture.gridx = 0;
		gbc_panelPicture.gridy = 0;
		frame.getContentPane().add(panelPicture, gbc_panelPicture);
		panelPicture.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lblSleep = new JLabel("");
		lblSleep.setBackground(new Color(240, 255, 255));
		lblSleep.setIcon(new ImageIcon("Images/Sleep.PNG"));
		panelPicture.add(lblSleep);
		String message;
		if (currentPet.isMisbehave()) {
			message = currentPet.getName() + " is pretending to sleep.";
		}
		else {
			message =  currentPet.getName() + " is sleeping... zzzzzzzz. \r\n"
					+ currentPet.getName() + " has woken up and feels great!";
		}
		JPanel panelMessage = new JPanel();
		panelMessage.setBackground(new Color(240, 255, 255));
		GridBagConstraints gbc_panelMessage = new GridBagConstraints();
		gbc_panelMessage.gridheight = 2;
		gbc_panelMessage.gridwidth = 7;
		gbc_panelMessage.fill = GridBagConstraints.BOTH;
		gbc_panelMessage.gridx = 0;
		gbc_panelMessage.gridy = 7;
		frame.getContentPane().add(panelMessage, gbc_panelMessage);
		GridBagLayout gbl_panelMessage = new GridBagLayout();
		gbl_panelMessage.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panelMessage.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panelMessage.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panelMessage.rowWeights = new double[]{1.0, 0.0, 0.0, Double.MIN_VALUE};
		panelMessage.setLayout(gbl_panelMessage);
		JTextPane txtpnMessage = new JTextPane();
		txtpnMessage.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		txtpnMessage.setText(currentPlayer.getName() + " put " + currentPet.getName() + " to sleep.\r\n" 
				+ message);
		txtpnMessage.setBackground(new Color(240, 255, 255));
		txtpnMessage.setForeground(new Color(0, 0, 0));
		GridBagConstraints gbc_txtpnMessage = new GridBagConstraints();
		gbc_txtpnMessage.gridwidth = 11;
		gbc_txtpnMessage.gridheight = 3;
		gbc_txtpnMessage.insets = new Insets(0, 0, 0, 5);
		gbc_txtpnMessage.fill = GridBagConstraints.BOTH;
		gbc_txtpnMessage.gridx = 0;
		gbc_txtpnMessage.gridy = 0;
		panelMessage.add(txtpnMessage, gbc_txtpnMessage);
		
		JLabel lblYesBtn = new JLabel("");
		lblYesBtn.setIcon(new ImageIcon("Images/Yes.PNG"));
		GridBagConstraints gbc_lblYesBtn = new GridBagConstraints();
		gbc_lblYesBtn.gridwidth = 3;
		gbc_lblYesBtn.gridheight = 3;
		gbc_lblYesBtn.insets = new Insets(0, 0, 5, 5);
		gbc_lblYesBtn.gridx = 11;
		gbc_lblYesBtn.gridy = 0;
		panelMessage.add(lblYesBtn, gbc_lblYesBtn);
		frame.pack();
		frame.setVisible(true);
		lblYesBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				frame.getContentPane().remove(lblYesBtn);
				frame.getContentPane().remove(txtpnMessage);
				frame.getContentPane().remove(panelMessage);
				frame.getContentPane().remove(lblSleep);
				frame.getContentPane().remove(panelPicture);
				StoryBoard();
			}
		});
	}
	private void feed(){
		ArrayList<String> foodNames = new ArrayList<String>(0);
		ArrayList<Food> foods = new ArrayList<Food>(0);
		for (Item i : currentPlayer.inventory) {
			if (i instanceof Food) {
				if (foodNames.contains(i.getName()) == false) {
					foodNames.add(i.getName());
					foods.add((Food) i);
					}
			}
		}
		frame.setBackground(new Color(240, 255, 255));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{307, 211, 0};
		gridBagLayout.rowHeights = new int[]{0, 181, 144, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		frame.setLayout(gridBagLayout); 
		
		JLabel lblTitle = new JLabel("Welcome to the kitchen");
		lblTitle.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		GridBagConstraints gbc_lblTitle = new GridBagConstraints();
		gbc_lblTitle.gridwidth = 2;
		gbc_lblTitle.insets = new Insets(0, 0, 5, 0);
		gbc_lblTitle.gridx = 0;
		gbc_lblTitle.gridy = 0;
		frame.getContentPane().add(lblTitle, gbc_lblTitle);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(160, 82, 45));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 1;
		frame.getContentPane().add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{316, 0};
		gbl_panel.rowHeights = new int[]{0, 151, 0};
		gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		comboBox.setModel(new DefaultComboBoxModel());
		for (Food foodName: foods) {
			comboBox.addItem(foodName.getName());
		}
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 0;
		gbc_comboBox.gridy = 0;
		panel.add(comboBox, gbc_comboBox);
		
		JLabel lblFood = new JLabel("");
		lblFood.setIcon(new ImageIcon());
		GridBagConstraints gbc_lblFood = new GridBagConstraints();
		gbc_lblFood.insets = new Insets(0, 0, 5, 0);
		gbc_lblFood.gridx = 0;
		gbc_lblFood.gridy = 1;
		panel.add(lblFood, gbc_lblFood);
		
		JLabel lblNutrition = new JLabel("");
		lblNutrition.setForeground(new Color(255, 255, 255));
		lblNutrition.setBackground(new Color(255, 255, 255));
		lblNutrition.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		GridBagConstraints gbc_lblNutrition = new GridBagConstraints();
		gbc_lblNutrition.insets = new Insets(0, 0, 5, 0);
		gbc_lblNutrition.anchor = GridBagConstraints.WEST;
		gbc_lblNutrition.gridx = 0;
		gbc_lblNutrition.gridy = 2;
		panel.add(lblNutrition, gbc_lblNutrition);
		
		JLabel lblTaste = new JLabel("");
		lblTaste.setForeground(Color.WHITE);
		lblTaste.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblTaste.setBackground(Color.WHITE);
		GridBagConstraints gbc_lblTaste = new GridBagConstraints();
		gbc_lblTaste.anchor = GridBagConstraints.WEST;
		gbc_lblTaste.gridx = 0;
		gbc_lblTaste.gridy = 3;
		panel.add(lblTaste, gbc_lblTaste);
		
		JLabel lblYesBtn = new JLabel("");
		lblYesBtn.setIcon(new ImageIcon("Images/Yes.PNG"));
		GridBagConstraints gbc_lblYesBtn = new GridBagConstraints();
		gbc_lblYesBtn.insets = new Insets(0, 0, 5, 0);
		gbc_lblYesBtn.gridx = 1;
		gbc_lblYesBtn.gridy = 1;
		frame.getContentPane().add(lblYesBtn, gbc_lblYesBtn);
		
		JTextPane textPaneMessage = new JTextPane();
		textPaneMessage.setForeground(new Color(255, 255, 255));
		textPaneMessage.setText("Which food would you like " + currentPet.getName() + " to eat?");
		textPaneMessage.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		textPaneMessage.setBackground(new Color(47, 79, 79));
		GridBagConstraints gbc_textPaneMessage = new GridBagConstraints();
		gbc_textPaneMessage.gridwidth = 2;
		gbc_textPaneMessage.fill = GridBagConstraints.BOTH;
		gbc_textPaneMessage.gridx = 0;
		gbc_textPaneMessage.gridy = 2;
		frame.getContentPane().add(textPaneMessage, gbc_textPaneMessage);
		if (foods.size() == 0) {
			textPaneMessage.setText(currentPlayer.getName() + " is looking for food, but could not find any food inside his bag.\r\n"
					+ currentPlayer.getName() + " has wasted " + currentPet.getName() + "'s precious time.");
			lblYesBtn.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					frame.getContentPane().remove(comboBox);
					frame.getContentPane().remove(textPaneMessage);
					frame.getContentPane().remove(lblFood);
					frame.getContentPane().remove(lblYesBtn);
					frame.getContentPane().remove(panel);
					frame.getContentPane().remove(lblTitle);
					StoryBoard();
				}
			});
		}
		else {
			currentFood = foods.get(0);
			if (currentFood.getName() == "Italian pizza") {
				lblFood.setIcon(new ImageIcon("Images/Pizza.png"));
				String box = "\u239A";
				String nutrition = String.join("", Collections.nCopies(foods.get(0).getNutrition(), box));
				String Taste = String.join("", Collections.nCopies(foods.get(0).getTaste(), box));
				lblTaste.setText("Taste: " + Taste);
				lblNutrition.setText("Nutrition: " + nutrition);
			}
			if (currentFood.getName() == "Fried Chicken") {
				lblFood.setIcon(new ImageIcon("Images/Fried Chicken.png"));
				String box = "\u239A";
				String nutrition = String.join("", Collections.nCopies(foods.get(0).getNutrition(), box));
				String Taste = String.join("", Collections.nCopies(foods.get(0).getTaste(), box));
				lblTaste.setText("Taste: " + Taste);
				lblNutrition.setText("Nutrition: " + nutrition);
			}
			if (currentFood.getName() == "Curry Rice") {
				lblFood.setIcon(new ImageIcon("Images/Curry Rice.png"));
				String box = "\u239A";
				String nutrition = String.join("", Collections.nCopies(foods.get(0).getNutrition(), box));
				String Taste = String.join("", Collections.nCopies(foods.get(0).getTaste(), box));
				lblTaste.setText("Taste: " + Taste);
				lblNutrition.setText("Nutrition: " + nutrition);
			}
			if (currentFood.getName() == "Fish and chips") {
				lblFood.setIcon(new ImageIcon("Images/FishAndChip.png"));
				String box = "\u239A";
				String nutrition = String.join("", Collections.nCopies(foods.get(0).getNutrition(), box));
				String Taste = String.join("", Collections.nCopies(foods.get(0).getTaste(), box));
				lblTaste.setText("Taste: " + Taste);
				lblNutrition.setText("Nutrition: " + nutrition);
			}
			if (currentFood.getName() == "Hamburger") {
				lblFood.setIcon(new ImageIcon("Images/Hamburger.png"));
				String box = "\u239A";
				String nutrition = String.join("", Collections.nCopies(foods.get(0).getNutrition(), box));
				String Taste = String.join("", Collections.nCopies(foods.get(0).getTaste(), box));
				lblTaste.setText("Taste: " + Taste);
				lblNutrition.setText("Nutrition: " + nutrition);
			}
			if (currentFood.getName() == "Steak") {
				lblFood.setIcon(new ImageIcon("Images/Steak.png"));
				String box = "\u239A";
				String nutrition = String.join("", Collections.nCopies(foods.get(0).getNutrition(), box));
				String Taste = String.join("", Collections.nCopies(foods.get(0).getTaste(), box));
				lblTaste.setText("Taste: " + Taste);
				lblNutrition.setText("Nutrition: " + nutrition);
			}
			comboBox.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent arg0) {
						if (comboBox.getSelectedItem() == "Italian pizza") {
							currentFood = currentPlayer.inventory.indexOf(o)
							lblFood.setIcon(new ImageIcon("Images/Pizza.png"));
							String box = "\u239A";
							String nutrition = String.join("", Collections.nCopies(currentFood.getNutrition(), box));
							String Taste = String.join("", Collections.nCopies(currentFood.getTaste(), box));
							lblTaste.setText("Taste: " + Taste);
							lblNutrition.setText("Nutrition: " + nutrition);
							}
						if (comboBox.getSelectedItem() == "Fried Chicken") {
							currentFood = new FriedChicken();
							lblFood.setIcon(new ImageIcon("Images/Fried Chicken.png"));
							String box = "\u239A";
							String nutrition = String.join("", Collections.nCopies(currentFood.getNutrition(), box));
							String Taste = String.join("", Collections.nCopies(currentFood.getTaste(), box));
							lblTaste.setText("Taste: " + Taste);
							lblNutrition.setText("Nutrition: " + nutrition);
							}
						if (comboBox.getSelectedItem() == "Curry Rice") {
							currentFood = new CurryRice();
							lblFood.setIcon(new ImageIcon("Images/Curry Rice.png"));
							String box = "\u239A";
							String nutrition = String.join("", Collections.nCopies(currentFood.getNutrition(), box));
							String Taste = String.join("", Collections.nCopies(currentFood.getTaste(), box));
							lblTaste.setText("Taste: " + Taste);
							lblNutrition.setText("Nutrition: " + nutrition);
							}
						if (comboBox.getSelectedItem() == "Steak") {
							currentFood = new Steak();
							lblFood.setIcon(new ImageIcon("Images/Steak.png"));
							String box = "\u239A";
							String nutrition = String.join("", Collections.nCopies(currentFood.getNutrition(), box));
							String Taste = String.join("", Collections.nCopies(currentFood.getTaste(), box));
							lblTaste.setText("Taste: " + Taste);
							lblNutrition.setText("Nutrition: " + nutrition);
							}
						if (comboBox.getSelectedItem() == "Fish and chips") {
							currentFood = new FishAndChip();
							lblFood.setIcon(new ImageIcon("Images/FishAndChip.png"));
							String box = "\u239A";
							String nutrition = String.join("", Collections.nCopies(currentFood.getNutrition(), box));
							String Taste = String.join("", Collections.nCopies(currentFood.getTaste(), box));
							lblTaste.setText("Taste: " + Taste);
							lblNutrition.setText("Nutrition: " + nutrition);
							}
						}
				
					});
		}
		frame.pack();
		frame.setVisible(true);
	}
	private void useShop(){
		frame.setBackground(new Color(240, 255, 255));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{650, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		frame.setLayout(gridBagLayout);
		
		JLabel lblWelcomeMessage = new JLabel("Welcome to the shop");
		lblWelcomeMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeMessage.setFont(new Font("Times New Roman", Font.PLAIN, 26));
		GridBagConstraints gbc_lblWelcomeMessage = new GridBagConstraints();
		gbc_lblWelcomeMessage.insets = new Insets(0, 0, 5, 0);
		gbc_lblWelcomeMessage.gridx = 0;
		gbc_lblWelcomeMessage.gridy = 0;
		frame.getContentPane().add(lblWelcomeMessage, gbc_lblWelcomeMessage);
		
		JPanel panelShopSections = new JPanel();
		panelShopSections.setBackground(new Color(240, 255, 255));
		GridBagConstraints gbc_panelShopSections = new GridBagConstraints();
		gbc_panelShopSections.fill = GridBagConstraints.BOTH;
		gbc_panelShopSections.gridx = 0;
		gbc_panelShopSections.gridy = 1;
		frame.getContentPane().add(panelShopSections, gbc_panelShopSections);
		GridBagLayout gbl_panelShopSections = new GridBagLayout();
		gbl_panelShopSections.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panelShopSections.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panelShopSections.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panelShopSections.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		panelShopSections.setLayout(gbl_panelShopSections);
		
		JLabel lblFoodShop = new JLabel("");
		lblFoodShop.setIcon(new ImageIcon("Images/FoodShop.png"));
		GridBagConstraints gbc_lblFoodShop = new GridBagConstraints();
		gbc_lblFoodShop.insets = new Insets(0, 0, 5, 5);
		gbc_lblFoodShop.gridx = 1;
		gbc_lblFoodShop.gridy = 1;
		panelShopSections.add(lblFoodShop, gbc_lblFoodShop);
		
		JLabel lblToyShop = new JLabel("");
		lblToyShop.setIcon(new ImageIcon("Images/ToyShop.png"));
		GridBagConstraints gbc_lblToyShop = new GridBagConstraints();
		gbc_lblToyShop.insets = new Insets(0, 0, 5, 5);
		gbc_lblToyShop.gridx = 2;
		gbc_lblToyShop.gridy = 1;
		panelShopSections.add(lblToyShop, gbc_lblToyShop);
		
		JPanel panelBankStatement = new JPanel();
		panelBankStatement.setBackground(new Color(255, 255, 255));
		GridBagConstraints gbc_panelBankStatement = new GridBagConstraints();
		gbc_panelBankStatement.gridwidth = 3;
		gbc_panelBankStatement.insets = new Insets(0, 0, 5, 5);
		gbc_panelBankStatement.fill = GridBagConstraints.BOTH;
		gbc_panelBankStatement.gridx = 3;
		gbc_panelBankStatement.gridy = 1;
		panelShopSections.add(panelBankStatement, gbc_panelBankStatement);
		GridBagLayout gbl_panelBankStatement = new GridBagLayout();
		gbl_panelBankStatement.columnWidths = new int[]{187, 0};
		gbl_panelBankStatement.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panelBankStatement.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panelBankStatement.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelBankStatement.setLayout(gbl_panelBankStatement);
		
		JLabel lblBankStatement = new JLabel("Bank statement");
		lblBankStatement.setHorizontalAlignment(SwingConstants.CENTER);
		lblBankStatement.setFont(new Font("Times New Roman", Font.BOLD, 20));
		GridBagConstraints gbc_lblBankStatement = new GridBagConstraints();
		gbc_lblBankStatement.insets = new Insets(0, 0, 5, 0);
		gbc_lblBankStatement.gridx = 0;
		gbc_lblBankStatement.gridy = 0;
		panelBankStatement.add(lblBankStatement, gbc_lblBankStatement);
		
		JLabel lblPlayerName = new JLabel("Player Name");
		lblPlayerName.setFont(new Font("Times New Roman", Font.BOLD, 18));
		GridBagConstraints gbc_lblPlayerName = new GridBagConstraints();
		gbc_lblPlayerName.insets = new Insets(0, 0, 5, 0);
		gbc_lblPlayerName.anchor = GridBagConstraints.WEST;
		gbc_lblPlayerName.gridx = 0;
		gbc_lblPlayerName.gridy = 1;
		panelBankStatement.add(lblPlayerName, gbc_lblPlayerName);
		
		JLabel lblName = new JLabel(currentPlayer.getName());
		lblName.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.insets = new Insets(0, 0, 5, 0);
		gbc_lblName.gridx = 0;
		gbc_lblName.gridy = 2;
		panelBankStatement.add(lblName, gbc_lblName);
		
		JLabel lblMoneyAvailable = new JLabel("Money available");
		lblMoneyAvailable.setFont(new Font("Times New Roman", Font.BOLD, 18));
		GridBagConstraints gbc_lblMoneyAvailable = new GridBagConstraints();
		gbc_lblMoneyAvailable.insets = new Insets(0, 0, 5, 0);
		gbc_lblMoneyAvailable.anchor = GridBagConstraints.WEST;
		gbc_lblMoneyAvailable.gridx = 0;
		gbc_lblMoneyAvailable.gridy = 3;
		panelBankStatement.add(lblMoneyAvailable, gbc_lblMoneyAvailable);
		
		JLabel lblNewLabel = new JLabel("$" + Integer.toString(currentPlayer.getMoney()));
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 4;
		panelBankStatement.add(lblNewLabel, gbc_lblNewLabel);
		
		JLabel lblVet = new JLabel("");
		lblVet.setIcon(new ImageIcon("Images/Hospital.png"));
		GridBagConstraints gbc_lblVet = new GridBagConstraints();
		gbc_lblVet.insets = new Insets(0, 0, 0, 5);
		gbc_lblVet.gridx = 1;
		gbc_lblVet.gridy = 3;
		panelShopSections.add(lblVet, gbc_lblVet);
		
		JLabel lblRevive = new JLabel("");
		lblRevive.setIcon(new ImageIcon("Images/Revive.png"));
		GridBagConstraints gbc_lblRevive = new GridBagConstraints();
		gbc_lblRevive.insets = new Insets(0, 0, 0, 5);
		gbc_lblRevive.gridx = 2;
		gbc_lblRevive.gridy = 3;
		panelShopSections.add(lblRevive, gbc_lblRevive);
		
		JLabel lblHome = new JLabel("");
		lblHome.setIcon(new ImageIcon("Images/Home.png"));
		GridBagConstraints gbc_lblHome = new GridBagConstraints();
		gbc_lblHome.gridwidth = 3;
		gbc_lblHome.insets = new Insets(0, 0, 0, 5);
		gbc_lblHome.gridx = 3;
		gbc_lblHome.gridy = 3;
		panelShopSections.add(lblHome, gbc_lblHome);
		frame.pack();
		frame.setVisible(true);
		lblToyShop.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				frame.getContentPane().remove(lblWelcomeMessage);
				frame.getContentPane().remove(lblHome);
				frame.getContentPane().remove(lblRevive);
				frame.getContentPane().remove(lblVet);
				frame.getContentPane().remove(lblNewLabel);
				frame.getContentPane().remove(lblMoneyAvailable);
				frame.getContentPane().remove(lblName);
				frame.getContentPane().remove(lblPlayerName);
				frame.getContentPane().remove(lblBankStatement);
				frame.getContentPane().remove(panelBankStatement);
				frame.getContentPane().remove(lblToyShop);
				frame.getContentPane().remove(lblFoodShop);
				frame.getContentPane().remove(panelShopSections);
				toyShop();
			}
		});
		lblFoodShop.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				frame.getContentPane().remove(lblWelcomeMessage);
				frame.getContentPane().remove(lblHome);
				frame.getContentPane().remove(lblRevive);
				frame.getContentPane().remove(lblVet);
				frame.getContentPane().remove(lblNewLabel);
				frame.getContentPane().remove(lblMoneyAvailable);
				frame.getContentPane().remove(lblName);
				frame.getContentPane().remove(lblPlayerName);
				frame.getContentPane().remove(lblBankStatement);
				frame.getContentPane().remove(panelBankStatement);
				frame.getContentPane().remove(lblToyShop);
				frame.getContentPane().remove(lblFoodShop);
				frame.getContentPane().remove(panelShopSections);
				foodShop();
			}
		});
		lblHome.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				frame.getContentPane().remove(lblWelcomeMessage);
				frame.getContentPane().remove(lblHome);
				frame.getContentPane().remove(lblRevive);
				frame.getContentPane().remove(lblVet);
				frame.getContentPane().remove(lblNewLabel);
				frame.getContentPane().remove(lblMoneyAvailable);
				frame.getContentPane().remove(lblName);
				frame.getContentPane().remove(lblPlayerName);
				frame.getContentPane().remove(lblBankStatement);
				frame.getContentPane().remove(panelBankStatement);
				frame.getContentPane().remove(lblToyShop);
				frame.getContentPane().remove(lblFoodShop);
				frame.getContentPane().remove(panelShopSections);
				StoryBoard();
			}
		});
	}
	private void toyShop() {
		frame.setBackground(new Color(240, 255, 255));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{262, 261, 296, 0};
		gridBagLayout.rowHeights = new int[]{0, 258, 402, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 1.0, Double.MIN_VALUE};
		frame.setLayout(gridBagLayout);
		
		JLabel lblTitle = new JLabel("Which toy would you like to buy");
		lblTitle.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		GridBagConstraints gbc_lblTitle = new GridBagConstraints();
		gbc_lblTitle.gridwidth = 3;
		gbc_lblTitle.insets = new Insets(0, 0, 5, 5);
		gbc_lblTitle.gridx = 0;
		gbc_lblTitle.gridy = 0;
		frame.getContentPane().add(lblTitle, gbc_lblTitle);
		
		JPanel panelToys = new JPanel();
		panelToys.setBackground(new Color(160, 82, 45));
		GridBagConstraints gbc_panelToys = new GridBagConstraints();
		gbc_panelToys.gridheight = 2;
		gbc_panelToys.insets = new Insets(0, 0, 0, 5);
		gbc_panelToys.fill = GridBagConstraints.BOTH;
		gbc_panelToys.gridx = 0;
		gbc_panelToys.gridy = 1;
		frame.getContentPane().add(panelToys, gbc_panelToys);
		GridBagLayout gbl_panelToys = new GridBagLayout();
		gbl_panelToys.columnWidths = new int[]{0, 0, 0};
		gbl_panelToys.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_panelToys.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panelToys.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelToys.setLayout(gbl_panelToys);
		
		JLabel lblDoll = new JLabel("");
		lblDoll.setIcon(new ImageIcon("Images/Doll.png"));
		GridBagConstraints gbc_lblDoll = new GridBagConstraints();
		gbc_lblDoll.insets = new Insets(0, 0, 5, 5);
		gbc_lblDoll.gridx = 0;
		gbc_lblDoll.gridy = 1;
		panelToys.add(lblDoll, gbc_lblDoll);
		
		JLabel lblFootball = new JLabel("");
		lblFootball.setIcon(new ImageIcon("Images/Football.png"));
		GridBagConstraints gbc_lblFootball = new GridBagConstraints();
		gbc_lblFootball.insets = new Insets(0, 0, 5, 0);
		gbc_lblFootball.gridx = 1;
		gbc_lblFootball.gridy = 1;
		panelToys.add(lblFootball, gbc_lblFootball);
		
		JLabel lblPillow = new JLabel("");
		lblPillow.setIcon(new ImageIcon("Images/Pillow.png"));
		GridBagConstraints gbc_lblPillow = new GridBagConstraints();
		gbc_lblPillow.insets = new Insets(0, 0, 5, 5);
		gbc_lblPillow.gridx = 0;
		gbc_lblPillow.gridy = 3;
		panelToys.add(lblPillow, gbc_lblPillow);
		
		JLabel lblToiletPaper = new JLabel("");
		lblToiletPaper.setIcon(new ImageIcon("Images/ToiletPaper.png"));
		GridBagConstraints gbc_lblToiletPaper = new GridBagConstraints();
		gbc_lblToiletPaper.insets = new Insets(0, 0, 5, 0);
		gbc_lblToiletPaper.gridx = 1;
		gbc_lblToiletPaper.gridy = 3;
		panelToys.add(lblToiletPaper, gbc_lblToiletPaper);
		
		JLabel lblToyCar = new JLabel("");
		lblToyCar.setIcon(new ImageIcon("Images/ToyCar.png"));
		GridBagConstraints gbc_lblToyCar = new GridBagConstraints();
		gbc_lblToyCar.insets = new Insets(0, 0, 0, 5);
		gbc_lblToyCar.gridx = 0;
		gbc_lblToyCar.gridy = 5;
		panelToys.add(lblToyCar, gbc_lblToyCar);
		
		JLabel lblStick = new JLabel("");
		lblStick.setIcon(new ImageIcon("Images/Stick.png"));
		GridBagConstraints gbc_lblStick = new GridBagConstraints();
		gbc_lblStick.gridx = 1;
		gbc_lblStick.gridy = 5;
		panelToys.add(lblStick, gbc_lblStick);
		
		JPanel panelStat = new JPanel();
		panelStat.setBackground(new Color(70, 130, 180));
		GridBagConstraints gbc_panelStat = new GridBagConstraints();
		gbc_panelStat.gridheight = 2;
		gbc_panelStat.insets = new Insets(0, 0, 0, 5);
		gbc_panelStat.fill = GridBagConstraints.BOTH;
		gbc_panelStat.gridx = 1;
		gbc_panelStat.gridy = 1;
		frame.getContentPane().add(panelStat, gbc_panelStat);
		GridBagLayout gbl_panelStat = new GridBagLayout();
		gbl_panelStat.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panelStat.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panelStat.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panelStat.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelStat.setLayout(gbl_panelStat);
		
		JLabel lblToy = new JLabel("");
		lblToy.setIcon(new ImageIcon("Images/Doll.png"));
		GridBagConstraints gbc_lblToy = new GridBagConstraints();
		gbc_lblToy.insets = new Insets(0, 0, 5, 5);
		gbc_lblToy.gridx = 1;
		gbc_lblToy.gridy = 1;
		panelStat.add(lblToy, gbc_lblToy);
		currentToy = new Doll();
		String box = "\u239A";
		String happiness = String.join("", Collections.nCopies(currentToy.getHappy(), box));
		String durability = String.join("", Collections.nCopies(currentToy.getDurability(), box));
		String exercise = "Exercise: Medium";
		if (currentToy.getExercise() == 0) {
			exercise = "Exercise: None";
			}
		if (currentToy.getExercise() >= 3) {
			exercise = "Exercise: High";
		}
		if (currentToy.getExercise() == 1) {
			exercise = "Exercise: low";
			}
		
		
		JLabel lblToyName = new JLabel("Name: " + currentToy.getName());
		lblToyName.setForeground(new Color(255, 255, 255));
		lblToyName.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		GridBagConstraints gbc_lblFoodName = new GridBagConstraints();
		gbc_lblFoodName.insets = new Insets(0, 0, 5, 5);
		gbc_lblFoodName.gridx = 1;
		gbc_lblFoodName.gridy = 2;
		panelStat.add(lblToyName, gbc_lblFoodName);
		
		JLabel labelNutrition = new JLabel("Happiness: " + happiness);
		labelNutrition.setForeground(Color.WHITE);
		labelNutrition.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		GridBagConstraints gbc_labelNutrition = new GridBagConstraints();
		gbc_labelNutrition.insets = new Insets(0, 0, 5, 5);
		gbc_labelNutrition.gridx = 1;
		gbc_labelNutrition.gridy = 3;
		panelStat.add(labelNutrition, gbc_labelNutrition);
		
		JLabel labelTaste = new JLabel("Durability: " + durability);
		labelTaste.setForeground(Color.WHITE);
		labelTaste.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		GridBagConstraints gbc_labelTaste = new GridBagConstraints();
		gbc_labelTaste.insets = new Insets(0, 0, 5, 5);
		gbc_labelTaste.gridx = 1;
		gbc_labelTaste.gridy = 4;
		panelStat.add(labelTaste, gbc_labelTaste);
		
		JLabel labelMealSize = new JLabel(exercise);
		labelMealSize.setForeground(Color.WHITE);
		labelMealSize.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		GridBagConstraints gbc_labelMealSize = new GridBagConstraints();
		gbc_labelMealSize.insets = new Insets(0, 0, 5, 5);
		gbc_labelMealSize.gridx = 1;
		gbc_labelMealSize.gridy = 5;
		panelStat.add(labelMealSize, gbc_labelMealSize);
		
		JLabel lblPrice = new JLabel("Price: $" + Integer.toString(currentToy.getPrice()));
		lblPrice.setForeground(Color.WHITE);
		lblPrice.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		GridBagConstraints gbc_lblPrice = new GridBagConstraints();
		gbc_lblPrice.insets = new Insets(0, 0, 5, 5);
		gbc_lblPrice.gridx = 1;
		gbc_lblPrice.gridy = 6;
		panelStat.add(lblPrice, gbc_lblPrice);
		
		JLabel lblYesBtn = new JLabel("");
		GridBagConstraints gbc_lblYesBtn = new GridBagConstraints();
		gbc_lblYesBtn.insets = new Insets(0, 0, 0, 5);
		gbc_lblYesBtn.gridx = 1;
		gbc_lblYesBtn.gridy = 9;
		panelStat.add(lblYesBtn, gbc_lblYesBtn);
		lblYesBtn.setIcon(new ImageIcon("Images/Yes.PNG"));
		
		JPanel panelBankStatement = new JPanel();
		panelBankStatement.setBackground(new Color(255, 255, 255));
		GridBagConstraints gbc_panelBankStatement = new GridBagConstraints();
		gbc_panelBankStatement.insets = new Insets(0, 0, 5, 0);
		gbc_panelBankStatement.fill = GridBagConstraints.BOTH;
		gbc_panelBankStatement.gridx = 2;
		gbc_panelBankStatement.gridy = 1;
		frame.getContentPane().add(panelBankStatement, gbc_panelBankStatement);
		GridBagLayout gbl_panelBankStatement = new GridBagLayout();
		gbl_panelBankStatement.columnWidths = new int[]{266, 0};
		gbl_panelBankStatement.rowHeights = new int[]{0, 0, 0};
		gbl_panelBankStatement.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panelBankStatement.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panelBankStatement.setLayout(gbl_panelBankStatement);
		
		JLabel lblPlayerName = new JLabel("Name");
		lblPlayerName.setFont(new Font("Times New Roman", Font.BOLD, 18));
		GridBagConstraints gbc_lblPlayerName = new GridBagConstraints();
		gbc_lblPlayerName.insets = new Insets(0, 0, 5, 0);
		gbc_lblPlayerName.gridx = 0;
		gbc_lblPlayerName.gridy = 1;
		panelBankStatement.add(lblPlayerName, gbc_lblPlayerName);
		
		JLabel lblName = new JLabel(currentPlayer.getName());
		lblName.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.insets = new Insets(0, 0, 5, 0);
		gbc_lblName.gridx = 0;
		gbc_lblName.gridy = 2;
		panelBankStatement.add(lblName, gbc_lblName);
		
		JLabel lblMoneyAvailable = new JLabel("Money available");
		lblMoneyAvailable.setHorizontalAlignment(SwingConstants.LEFT);
		lblMoneyAvailable.setFont(new Font("Times New Roman", Font.BOLD, 18));
		GridBagConstraints gbc_lblMoneyAvailable = new GridBagConstraints();
		gbc_lblMoneyAvailable.insets = new Insets(0, 0, 5, 0);
		gbc_lblMoneyAvailable.gridx = 0;
		gbc_lblMoneyAvailable.gridy = 3;
		panelBankStatement.add(lblMoneyAvailable, gbc_lblMoneyAvailable);
		
		JLabel lblNewLabel = new JLabel("$" + Integer.toString(currentPlayer.getMoney()));
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 4;
		panelBankStatement.add(lblNewLabel, gbc_lblNewLabel);
		
		JPanel panelButtons = new JPanel();
		panelButtons.setBackground(new Color(240, 255, 255));
		GridBagConstraints gbc_panelButtons = new GridBagConstraints();
		gbc_panelButtons.fill = GridBagConstraints.BOTH;
		gbc_panelButtons.gridx = 2;
		gbc_panelButtons.gridy = 2;
		frame.getContentPane().add(panelButtons, gbc_panelButtons);
		GridBagLayout gbl_panelButtons = new GridBagLayout();
		gbl_panelButtons.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_panelButtons.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panelButtons.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panelButtons.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelButtons.setLayout(gbl_panelButtons);
		
		JLabel lblExitbtn = new JLabel("");
		lblExitbtn.setIcon(new ImageIcon("Images/Exit.png"));
		GridBagConstraints gbc_lblExitbtn = new GridBagConstraints();
		gbc_lblExitbtn.gridx = 3;
		gbc_lblExitbtn.gridy = 3;
		panelButtons.add(lblExitbtn, gbc_lblExitbtn);
		frame.setSize(700, 440);
		frame.setVisible(true);
		lblYesBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (currentToy.getPrice() > currentPlayer.getMoney()) {
					try {
						ErrorMoney dialog = new ErrorMoney();
						dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						dialog.pack();
						dialog.setVisible(true);
					} catch (Exception e1) {
						e1.printStackTrace();
						}
					}
				else {
					currentPlayer.setMoney(-currentToy.getPrice());
					currentPlayer.inventory.add(currentToy);
					frame.getContentPane().remove(lblNewLabel);
					frame.getContentPane().remove(lblMoneyAvailable);
					frame.getContentPane().remove(lblName);
					frame.getContentPane().remove(lblPlayerName);
					frame.getContentPane().remove(panelBankStatement);
					frame.getContentPane().remove(lblExitbtn);
					frame.getContentPane().remove(panelButtons);
					frame.getContentPane().remove(lblYesBtn);
					frame.getContentPane().remove(lblPrice);
					frame.getContentPane().remove(labelMealSize);
					frame.getContentPane().remove(labelTaste);
					frame.getContentPane().remove(labelNutrition);
					frame.getContentPane().remove(lblToyName);
					frame.getContentPane().remove(lblToy);
					frame.getContentPane().remove(panelStat);
					frame.getContentPane().remove(lblStick);
					frame.getContentPane().remove(lblToyCar);
					frame.getContentPane().remove(lblToiletPaper);
					frame.getContentPane().remove(lblFootball);
					frame.getContentPane().remove(lblPillow);
					frame.getContentPane().remove(lblDoll);
					frame.getContentPane().remove(panelToys);
					frame.getContentPane().remove(lblTitle);
					useShop();
				}
			}
		});
		lblToiletPaper.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				currentToy = new ToiletPaper();
				String box = "\u239A";
				String happiness = String.join("", Collections.nCopies(currentToy.getHappy(), box));
				String durability = String.join("", Collections.nCopies(currentToy.getDurability(), box));
				String exercise = "Exercise: Medium";
				if (currentToy.getExercise() == 0) {
					exercise = "Exercise: None";
					}
				if (currentToy.getExercise() >= 3) {
					exercise = "Exercise: High";
				}
				if (currentToy.getExercise() == 1) {
					exercise = "Exercise: low";
					}
				lblToyName.setText("Name: " + currentToy.getName());
				labelNutrition.setText("Happiness: " + happiness);
				labelTaste.setText("Durablity: " + durability);
				labelMealSize.setText(exercise);
				lblPrice.setText("Price: $" + Integer.toString(currentToy.getPrice()));
				lblToy.setIcon(new ImageIcon("Images/ToiletPaper.png"));
			}
		});
		lblDoll.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				currentToy = new Doll();
				String box = "\u239A";
				String happiness = String.join("", Collections.nCopies(currentToy.getHappy(), box));
				String durability = String.join("", Collections.nCopies(currentToy.getDurability(), box));
				String exercise = "Exercise: Medium";
				if (currentToy.getExercise() == 0) {
					exercise = "Exercise: None";
					}
				if (currentToy.getExercise() >= 3) {
					exercise = "Exercise: High";
				}
				if (currentToy.getExercise() == 1) {
					exercise = "Exercise: low";
					}
				lblToyName.setText("Name: " + currentToy.getName());
				labelNutrition.setText("Happiness: " + happiness);
				labelTaste.setText("Durablity: " + durability);
				labelMealSize.setText(exercise);
				lblPrice.setText("Price: $" + Integer.toString(currentToy.getPrice()));
				lblToy.setIcon(new ImageIcon("Images/Doll.png"));
			}
		});
		lblToyCar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				currentToy = new ToyCar();
				String box = "\u239A";
				String happiness = String.join("", Collections.nCopies(currentToy.getHappy(), box));
				String durability = String.join("", Collections.nCopies(currentToy.getDurability(), box));
				String exercise = "Exercise: Medium";
				if (currentToy.getExercise() == 0) {
					exercise = "Exercise: None";
					}
				if (currentToy.getExercise() >= 3) {
					exercise = "Exercise: High";
				}
				if (currentToy.getExercise() == 1) {
					exercise = "Exercise: low";
					}
				lblToyName.setText("Name: " + currentToy.getName());
				labelNutrition.setText("Happiness: " + happiness);
				labelTaste.setText("Durablity: " + durability);
				labelMealSize.setText(exercise);
				lblPrice.setText("Price: $" + Integer.toString(currentToy.getPrice()));
				lblToy.setIcon(new ImageIcon("Images/ToyCar.png"));
			}
		});
		lblPillow.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				currentToy = new Pillow();
				String box = "\u239A";
				String happiness = String.join("", Collections.nCopies(currentToy.getHappy(), box));
				String durability = String.join("", Collections.nCopies(currentToy.getDurability(), box));
				String exercise = "Exercise: Medium";
				if (currentToy.getExercise() == 0) {
					exercise = "Exercise: None";
					}
				if (currentToy.getExercise() >= 3) {
					exercise = "Exercise: High";
				}
				if (currentToy.getExercise() == 1) {
					exercise = "Exercise: low";
					}
				lblToyName.setText("Name: " + currentToy.getName());
				labelNutrition.setText("Happiness: " + happiness);
				labelTaste.setText("Durablity: " + durability);
				labelMealSize.setText(exercise);
				lblPrice.setText("Price: $" + Integer.toString(currentToy.getPrice()));
				lblToy.setIcon(new ImageIcon("Images/Pillow.png"));
			}
		});
		lblFootball.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				currentToy = new Football();
				String box = "\u239A";
				String happiness = String.join("", Collections.nCopies(currentToy.getHappy(), box));
				String durability = String.join("", Collections.nCopies(currentToy.getDurability(), box));
				String exercise = "Exercise: Medium";
				if (currentToy.getExercise() == 0) {
					exercise = "Exercise: None";
					}
				if (currentToy.getExercise() >= 3) {
					exercise = "Exercise: High";
				}
				if (currentToy.getExercise() == 1) {
					exercise = "Exercise: low";
					}
				lblToyName.setText("Name: " + currentToy.getName());
				labelNutrition.setText("Happiness: " + happiness);
				labelTaste.setText("Durablity: " + durability);
				labelMealSize.setText(exercise);
				lblPrice.setText("Price: $" + Integer.toString(currentToy.getPrice()));
				lblToy.setIcon(new ImageIcon("Images/Football.png"));
			}
		});
		lblStick.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				currentToy = new Stick();
				String box = "\u239A";
				String happiness = String.join("", Collections.nCopies(currentToy.getHappy(), box));
				String durability = String.join("", Collections.nCopies(currentToy.getDurability(), box));
				String exercise = "Exercise: Medium";
				if (currentToy.getExercise() == 0) {
					exercise = "Exercise: None";
					}
				if (currentToy.getExercise() >= 3) {
					exercise = "Exercise: High";
				}
				if (currentToy.getExercise() == 1) {
					exercise = "Exercise: low";
					}
				lblToyName.setText("Name: " + currentToy.getName());
				labelNutrition.setText("Happiness: " + happiness);
				labelTaste.setText("Durablity: " + durability);
				labelMealSize.setText(exercise);
				lblPrice.setText("Price: $" + Integer.toString(currentToy.getPrice()));
				lblToy.setIcon(new ImageIcon("Images/Stick.png"));
			}
		});
		
		lblExitbtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				frame.getContentPane().remove(lblNewLabel);
				frame.getContentPane().remove(lblMoneyAvailable);
				frame.getContentPane().remove(lblName);
				frame.getContentPane().remove(lblPlayerName);
				frame.getContentPane().remove(panelBankStatement);
				frame.getContentPane().remove(lblExitbtn);
				frame.getContentPane().remove(panelButtons);
				frame.getContentPane().remove(lblYesBtn);
				frame.getContentPane().remove(lblPrice);
				frame.getContentPane().remove(labelMealSize);
				frame.getContentPane().remove(labelTaste);
				frame.getContentPane().remove(labelNutrition);
				frame.getContentPane().remove(lblToyName);
				frame.getContentPane().remove(lblToy);
				frame.getContentPane().remove(panelStat);
				frame.getContentPane().remove(lblStick);
				frame.getContentPane().remove(lblToyCar);
				frame.getContentPane().remove(lblToiletPaper);
				frame.getContentPane().remove(lblFootball);
				frame.getContentPane().remove(lblPillow);
				frame.getContentPane().remove(lblDoll);
				frame.getContentPane().remove(panelToys);
				frame.getContentPane().remove(lblTitle);
				useShop();
			}
		});
	}
	private void foodShop() {
		frame.setBackground(new Color(240, 255, 255));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{262, 261, 296, 0};
		gridBagLayout.rowHeights = new int[]{0, 258, 402, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 1.0, Double.MIN_VALUE};
		frame.setLayout(gridBagLayout);
		
		JLabel lblTitle = new JLabel("Which food would you like to buy");
		lblTitle.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		GridBagConstraints gbc_lblTitle = new GridBagConstraints();
		gbc_lblTitle.gridwidth = 3;
		gbc_lblTitle.insets = new Insets(0, 0, 5, 5);
		gbc_lblTitle.gridx = 0;
		gbc_lblTitle.gridy = 0;
		frame.getContentPane().add(lblTitle, gbc_lblTitle);
		
		JPanel panelFoods = new JPanel();
		panelFoods.setBackground(new Color(160, 82, 45));
		GridBagConstraints gbc_panelFoods = new GridBagConstraints();
		gbc_panelFoods.gridheight = 2;
		gbc_panelFoods.insets = new Insets(0, 0, 0, 5);
		gbc_panelFoods.fill = GridBagConstraints.BOTH;
		gbc_panelFoods.gridx = 0;
		gbc_panelFoods.gridy = 1;
		frame.getContentPane().add(panelFoods, gbc_panelFoods);
		GridBagLayout gbl_panelFoods = new GridBagLayout();
		gbl_panelFoods.columnWidths = new int[]{0, 0, 0};
		gbl_panelFoods.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_panelFoods.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panelFoods.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelFoods.setLayout(gbl_panelFoods);
		
		JLabel lblFriedChicken = new JLabel("");
		lblFriedChicken.setIcon(new ImageIcon("Images/Fried Chicken.png"));
		GridBagConstraints gbc_lblFriedChicken = new GridBagConstraints();
		gbc_lblFriedChicken.insets = new Insets(0, 0, 5, 5);
		gbc_lblFriedChicken.gridx = 0;
		gbc_lblFriedChicken.gridy = 1;
		panelFoods.add(lblFriedChicken, gbc_lblFriedChicken);
		
		JLabel lblCurryRice = new JLabel("");
		lblCurryRice.setIcon(new ImageIcon("Images/Curry Rice.png"));
		GridBagConstraints gbc_lblCurryRice = new GridBagConstraints();
		gbc_lblCurryRice.insets = new Insets(0, 0, 5, 0);
		gbc_lblCurryRice.gridx = 1;
		gbc_lblCurryRice.gridy = 1;
		panelFoods.add(lblCurryRice, gbc_lblCurryRice);
		
		JLabel lblFishAndChips = new JLabel("");
		lblFishAndChips.setIcon(new ImageIcon("Images/FishAndChip.png"));
		GridBagConstraints gbc_lblFishAndChips = new GridBagConstraints();
		gbc_lblFishAndChips.insets = new Insets(0, 0, 5, 5);
		gbc_lblFishAndChips.gridx = 0;
		gbc_lblFishAndChips.gridy = 3;
		panelFoods.add(lblFishAndChips, gbc_lblFishAndChips);
		
		JLabel lblPizza = new JLabel("");
		lblPizza.setIcon(new ImageIcon("Images/Pizza.png"));
		GridBagConstraints gbc_lblPizza = new GridBagConstraints();
		gbc_lblPizza.insets = new Insets(0, 0, 5, 0);
		gbc_lblPizza.gridx = 1;
		gbc_lblPizza.gridy = 3;
		panelFoods.add(lblPizza, gbc_lblPizza);
		
		JLabel lblHamburger = new JLabel("");
		lblHamburger.setIcon(new ImageIcon("Images/Hamburger.png"));
		GridBagConstraints gbc_lblHamburger = new GridBagConstraints();
		gbc_lblHamburger.insets = new Insets(0, 0, 0, 5);
		gbc_lblHamburger.gridx = 0;
		gbc_lblHamburger.gridy = 5;
		panelFoods.add(lblHamburger, gbc_lblHamburger);
		
		JLabel lblSteak = new JLabel("");
		lblSteak.setIcon(new ImageIcon("Images/Steak.png"));
		GridBagConstraints gbc_lblSteak = new GridBagConstraints();
		gbc_lblSteak.gridx = 1;
		gbc_lblSteak.gridy = 5;
		panelFoods.add(lblSteak, gbc_lblSteak);
		
		JPanel panelStat = new JPanel();
		panelStat.setBackground(new Color(70, 130, 180));
		GridBagConstraints gbc_panelStat = new GridBagConstraints();
		gbc_panelStat.gridheight = 2;
		gbc_panelStat.insets = new Insets(0, 0, 0, 5);
		gbc_panelStat.fill = GridBagConstraints.BOTH;
		gbc_panelStat.gridx = 1;
		gbc_panelStat.gridy = 1;
		frame.getContentPane().add(panelStat, gbc_panelStat);
		GridBagLayout gbl_panelStat = new GridBagLayout();
		gbl_panelStat.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panelStat.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panelStat.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panelStat.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelStat.setLayout(gbl_panelStat);
		
		JLabel lblFood = new JLabel("");
		lblFood.setIcon(new ImageIcon("Images/Fried Chicken.png"));
		GridBagConstraints gbc_lblFood = new GridBagConstraints();
		gbc_lblFood.insets = new Insets(0, 0, 5, 5);
		gbc_lblFood.gridx = 1;
		gbc_lblFood.gridy = 1;
		panelStat.add(lblFood, gbc_lblFood);
		currentFood = new FriedChicken();
		String box = "\u239A";
		String nutrition = String.join("", Collections.nCopies(currentFood.getNutrition(), box));
		String taste = String.join("", Collections.nCopies(currentFood.getTaste(), box));
		String mealSize = "Meal size: Large";
		if (currentFood.getFullness() <= 1) {
			mealSize = "Meal size: Small";
			}
		if (currentFood.getFullness() <= 3) {
				mealSize ="Meal size: Medium";
			}
		
		JLabel lblFoodName = new JLabel("Name: " + currentFood.getName());
		lblFoodName.setForeground(new Color(255, 255, 255));
		lblFoodName.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		GridBagConstraints gbc_lblFoodName = new GridBagConstraints();
		gbc_lblFoodName.insets = new Insets(0, 0, 5, 5);
		gbc_lblFoodName.gridx = 1;
		gbc_lblFoodName.gridy = 2;
		panelStat.add(lblFoodName, gbc_lblFoodName);
		
		JLabel labelNutrition = new JLabel("Nutrition: " + nutrition);
		labelNutrition.setForeground(Color.WHITE);
		labelNutrition.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		GridBagConstraints gbc_labelNutrition = new GridBagConstraints();
		gbc_labelNutrition.insets = new Insets(0, 0, 5, 5);
		gbc_labelNutrition.gridx = 1;
		gbc_labelNutrition.gridy = 3;
		panelStat.add(labelNutrition, gbc_labelNutrition);
		
		JLabel labelTaste = new JLabel("Taste: " + taste);
		labelTaste.setForeground(Color.WHITE);
		labelTaste.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		GridBagConstraints gbc_labelTaste = new GridBagConstraints();
		gbc_labelTaste.insets = new Insets(0, 0, 5, 5);
		gbc_labelTaste.gridx = 1;
		gbc_labelTaste.gridy = 4;
		panelStat.add(labelTaste, gbc_labelTaste);
		
		JLabel labelMealSize = new JLabel(mealSize);
		labelMealSize.setForeground(Color.WHITE);
		labelMealSize.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		GridBagConstraints gbc_labelMealSize = new GridBagConstraints();
		gbc_labelMealSize.insets = new Insets(0, 0, 5, 5);
		gbc_labelMealSize.gridx = 1;
		gbc_labelMealSize.gridy = 5;
		panelStat.add(labelMealSize, gbc_labelMealSize);
		
		JLabel lblPrice = new JLabel("Price: $" + Integer.toString(currentFood.getPrice()));
		lblPrice.setForeground(Color.WHITE);
		lblPrice.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		GridBagConstraints gbc_lblPrice = new GridBagConstraints();
		gbc_lblPrice.insets = new Insets(0, 0, 5, 5);
		gbc_lblPrice.gridx = 1;
		gbc_lblPrice.gridy = 6;
		panelStat.add(lblPrice, gbc_lblPrice);
		
		JLabel lblYesBtn = new JLabel("");
		GridBagConstraints gbc_lblYesBtn = new GridBagConstraints();
		gbc_lblYesBtn.insets = new Insets(0, 0, 0, 5);
		gbc_lblYesBtn.gridx = 1;
		gbc_lblYesBtn.gridy = 9;
		panelStat.add(lblYesBtn, gbc_lblYesBtn);
		lblYesBtn.setIcon(new ImageIcon("Images/Yes.PNG"));
		
		JPanel panelBankStatement = new JPanel();
		panelBankStatement.setBackground(new Color(255, 255, 255));
		GridBagConstraints gbc_panelBankStatement = new GridBagConstraints();
		gbc_panelBankStatement.insets = new Insets(0, 0, 5, 0);
		gbc_panelBankStatement.fill = GridBagConstraints.BOTH;
		gbc_panelBankStatement.gridx = 2;
		gbc_panelBankStatement.gridy = 1;
		frame.getContentPane().add(panelBankStatement, gbc_panelBankStatement);
		GridBagLayout gbl_panelBankStatement = new GridBagLayout();
		gbl_panelBankStatement.columnWidths = new int[]{266, 0};
		gbl_panelBankStatement.rowHeights = new int[]{0, 0, 0};
		gbl_panelBankStatement.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panelBankStatement.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panelBankStatement.setLayout(gbl_panelBankStatement);
		
		JLabel lblPlayerName = new JLabel("Name");
		lblPlayerName.setFont(new Font("Times New Roman", Font.BOLD, 18));
		GridBagConstraints gbc_lblPlayerName = new GridBagConstraints();
		gbc_lblPlayerName.insets = new Insets(0, 0, 5, 0);
		gbc_lblPlayerName.gridx = 0;
		gbc_lblPlayerName.gridy = 1;
		panelBankStatement.add(lblPlayerName, gbc_lblPlayerName);
		
		JLabel lblName = new JLabel(currentPlayer.getName());
		lblName.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.insets = new Insets(0, 0, 5, 0);
		gbc_lblName.gridx = 0;
		gbc_lblName.gridy = 2;
		panelBankStatement.add(lblName, gbc_lblName);
		
		JLabel lblMoneyAvailable = new JLabel("Money available");
		lblMoneyAvailable.setHorizontalAlignment(SwingConstants.LEFT);
		lblMoneyAvailable.setFont(new Font("Times New Roman", Font.BOLD, 18));
		GridBagConstraints gbc_lblMoneyAvailable = new GridBagConstraints();
		gbc_lblMoneyAvailable.insets = new Insets(0, 0, 5, 0);
		gbc_lblMoneyAvailable.gridx = 0;
		gbc_lblMoneyAvailable.gridy = 3;
		panelBankStatement.add(lblMoneyAvailable, gbc_lblMoneyAvailable);
		
		JLabel lblNewLabel = new JLabel("$" + Integer.toString(currentPlayer.getMoney()));
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 4;
		panelBankStatement.add(lblNewLabel, gbc_lblNewLabel);
		
		JPanel panelButtons = new JPanel();
		panelButtons.setBackground(new Color(240, 255, 255));
		GridBagConstraints gbc_panelButtons = new GridBagConstraints();
		gbc_panelButtons.fill = GridBagConstraints.BOTH;
		gbc_panelButtons.gridx = 2;
		gbc_panelButtons.gridy = 2;
		frame.getContentPane().add(panelButtons, gbc_panelButtons);
		GridBagLayout gbl_panelButtons = new GridBagLayout();
		gbl_panelButtons.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_panelButtons.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panelButtons.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panelButtons.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelButtons.setLayout(gbl_panelButtons);
		
		JLabel lblExitbtn = new JLabel("");
		lblExitbtn.setIcon(new ImageIcon("Images/Exit.png"));
		GridBagConstraints gbc_lblExitbtn = new GridBagConstraints();
		gbc_lblExitbtn.gridx = 3;
		gbc_lblExitbtn.gridy = 3;
		panelButtons.add(lblExitbtn, gbc_lblExitbtn);
		frame.setSize(700, 440);
		frame.setVisible(true);
		lblYesBtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (currentFood.getPrice() > currentPlayer.getMoney()) {
					try {
						ErrorMoney dialog = new ErrorMoney();
						dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						dialog.pack();
						dialog.setVisible(true);
					} catch (Exception e1) {
						e1.printStackTrace();
						}
					}
				else {
					currentPlayer.setMoney(-currentFood.getPrice());
					currentPlayer.inventory.add(currentFood);
					frame.getContentPane().remove(lblNewLabel);
					frame.getContentPane().remove(lblMoneyAvailable);
					frame.getContentPane().remove(lblName);
					frame.getContentPane().remove(lblPlayerName);
					frame.getContentPane().remove(panelBankStatement);
					frame.getContentPane().remove(lblExitbtn);
					frame.getContentPane().remove(panelButtons);
					frame.getContentPane().remove(lblYesBtn);
					frame.getContentPane().remove(lblPrice);
					frame.getContentPane().remove(labelMealSize);
					frame.getContentPane().remove(labelTaste);
					frame.getContentPane().remove(labelNutrition);
					frame.getContentPane().remove(lblFoodName);
					frame.getContentPane().remove(lblFood);
					frame.getContentPane().remove(panelStat);
					frame.getContentPane().remove(lblSteak);
					frame.getContentPane().remove(lblHamburger);
					frame.getContentPane().remove(lblPizza);
					frame.getContentPane().remove(lblCurryRice);
					frame.getContentPane().remove(lblFishAndChips);
					frame.getContentPane().remove(lblFriedChicken);
					frame.getContentPane().remove(panelFoods);
					frame.getContentPane().remove(lblTitle);
					useShop();
				}
			}
		});
		lblPizza.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				currentFood = new Pizza();
				String box = "\u239A";
				String nutrition = String.join("", Collections.nCopies(currentFood.getNutrition(), box));
				String taste = String.join("", Collections.nCopies(currentFood.getTaste(), box));
				String mealSize = "Meal size: Large";
				if (currentFood.getFullness() <= 1) {
					mealSize = "Meal size: Small";
					}
				if (currentFood.getFullness() <= 3) {
						mealSize ="Meal size: Medium";
					}
				lblFoodName.setText("Name: " + currentFood.getName());
				labelNutrition.setText("Nutrition: " + nutrition);
				labelTaste.setText("Taste: " + taste);
				labelMealSize.setText(mealSize);
				lblPrice.setText("Price: $" + Integer.toString(currentFood.getPrice()));
				lblFood.setIcon(new ImageIcon("Images/Pizza.png"));
			}
		});
		lblFriedChicken.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				currentFood = new FriedChicken();
				String box = "\u239A";
				String nutrition = String.join("", Collections.nCopies(currentFood.getNutrition(), box));
				String taste = String.join("", Collections.nCopies(currentFood.getTaste(), box));
				String mealSize = "Meal size: Large";
				if (currentFood.getFullness() <= 1) {
					mealSize = "Meal size: Small";
					}
				if (currentFood.getFullness() <= 3) {
						mealSize ="Meal size: Medium";
					}
				lblFoodName.setText("Name: " + currentFood.getName());
				labelNutrition.setText("Nutrition: " + nutrition);
				labelTaste.setText("Taste: " + taste);
				labelMealSize.setText(mealSize);
				lblPrice.setText("Price: $" + Integer.toString(currentFood.getPrice()));
				lblFood.setIcon(new ImageIcon("Images/Fried Chicken.png"));
			}
		});
		lblHamburger.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				currentFood = new Hamburger();
				String box = "\u239A";
				String nutrition = String.join("", Collections.nCopies(currentFood.getNutrition(), box));
				String taste = String.join("", Collections.nCopies(currentFood.getTaste(), box));
				String mealSize = "Meal size: Large";
				if (currentFood.getFullness() <= 1) {
					mealSize = "Meal size: Small";
					}
				if (currentFood.getFullness() <= 3) {
						mealSize ="Meal size: Medium";
					}
				lblFoodName.setText("Name: " + currentFood.getName());
				labelNutrition.setText("Nutrition: " + nutrition);
				labelTaste.setText("Taste: " + taste);
				labelMealSize.setText(mealSize);
				lblPrice.setText("Price: $" + Integer.toString(currentFood.getPrice()));
				lblFood.setIcon(new ImageIcon("Images/Hamburger.png"));
			}
		});
		lblFishAndChips.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				currentFood = new FishAndChip();
				String box = "\u239A";
				String nutrition = String.join("", Collections.nCopies(currentFood.getNutrition(), box));
				String taste = String.join("", Collections.nCopies(currentFood.getTaste(), box));
				String mealSize = "Meal size: Large";
				if (currentFood.getFullness() <= 1) {
					mealSize = "Meal size: Small";
					}
				if (currentFood.getFullness() <= 3) {
						mealSize ="Meal size: Medium";
					}
				lblFoodName.setText("Name: " + currentFood.getName());
				labelNutrition.setText("Nutrition: " + nutrition);
				labelTaste.setText("Taste: " + taste);
				labelMealSize.setText(mealSize);
				lblPrice.setText("Price: $" + Integer.toString(currentFood.getPrice()));
				lblFood.setIcon(new ImageIcon("Images/FishAndChip.png"));
			}
		});
		lblCurryRice.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				currentFood = new CurryRice();
				String box = "\u239A";
				String nutrition = String.join("", Collections.nCopies(currentFood.getNutrition(), box));
				String taste = String.join("", Collections.nCopies(currentFood.getTaste(), box));
				String mealSize = "Meal size: Large";
				if (currentFood.getFullness() <= 1) {
					mealSize = "Meal size: Small";
					}
				if (currentFood.getFullness() <= 3) {
						mealSize ="Meal size: Medium";
					}
				lblFoodName.setText("Name: " + currentFood.getName());
				labelNutrition.setText("Nutrition: " + nutrition);
				labelTaste.setText("Taste: " + taste);
				labelMealSize.setText(mealSize);
				lblPrice.setText("Price: $" + Integer.toString(currentFood.getPrice()));
				lblFood.setIcon(new ImageIcon("Images/Curry Rice.png"));
			}
		});
		lblSteak.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				currentFood = new Steak();
				String box = "\u239A";
				String nutrition = String.join("", Collections.nCopies(currentFood.getNutrition(), box));
				String taste = String.join("", Collections.nCopies(currentFood.getTaste(), box));
				String mealSize = "Meal size: Large";
				if (currentFood.getFullness() <= 1) {
					mealSize = "Meal size: Small";
					}
				if (currentFood.getFullness() <= 3) {
						mealSize ="Meal size: Medium";
					}
				lblFoodName.setText("Name: " + currentFood.getName());
				labelNutrition.setText("Nutrition: " + nutrition);
				labelTaste.setText("Taste: " + taste);
				labelMealSize.setText(mealSize);
				lblPrice.setText("Price: $" + Integer.toString(currentFood.getPrice()));
				lblFood.setIcon(new ImageIcon("Images/Steak.png"));
			}
		});
		
		lblExitbtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				frame.getContentPane().remove(lblNewLabel);
				frame.getContentPane().remove(lblMoneyAvailable);
				frame.getContentPane().remove(lblName);
				frame.getContentPane().remove(lblPlayerName);
				frame.getContentPane().remove(panelBankStatement);
				frame.getContentPane().remove(lblExitbtn);
				frame.getContentPane().remove(panelButtons);
				frame.getContentPane().remove(lblYesBtn);
				frame.getContentPane().remove(lblPrice);
				frame.getContentPane().remove(labelMealSize);
				frame.getContentPane().remove(labelTaste);
				frame.getContentPane().remove(labelNutrition);
				frame.getContentPane().remove(lblFoodName);
				frame.getContentPane().remove(lblFood);
				frame.getContentPane().remove(panelStat);
				frame.getContentPane().remove(lblSteak);
				frame.getContentPane().remove(lblHamburger);
				frame.getContentPane().remove(lblPizza);
				frame.getContentPane().remove(lblCurryRice);
				frame.getContentPane().remove(lblFishAndChips);
				frame.getContentPane().remove(lblFriedChicken);
				frame.getContentPane().remove(panelFoods);
				frame.getContentPane().remove(lblTitle);
				useShop();
			}
		});
		
	}
	private void displayScore(){
		frame.setBackground(new Color(240, 255, 255));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frame.setLayout(gridBagLayout);
		
		JLabel lblCoverPhoto = new JLabel("");
		lblCoverPhoto.setIcon(new ImageIcon("Images/IntroTitle.jpg"));
		GridBagConstraints gbc_lblCoverPhoto = new GridBagConstraints();
		gbc_lblCoverPhoto.insets = new Insets(0, 0, 5, 0);
		gbc_lblCoverPhoto.gridx = 0;
		gbc_lblCoverPhoto.gridy = 0;
		frame.add(lblCoverPhoto, gbc_lblCoverPhoto);
		int position = 1;
		for (Player player: game.playerArray){
			for (Pet animal: player.petArray) {
				game.finalScoreAdjust(player, animal);
			}
			if (position == 1) {
				JLabel lblfirstPlace = new JLabel(Integer.toString(position) + "st " + player.getName() + " " + Integer.toString(player.getScore()) + " points");
				lblfirstPlace.setFont(new Font("Times New Roman", Font.BOLD, 20));
				GridBagConstraints gbc_lblfirstPlace = new GridBagConstraints();
				gbc_lblfirstPlace.insets = new Insets(0, 0, 5, 0);
				gbc_lblfirstPlace.gridx = 0;
				gbc_lblfirstPlace.gridy = 2;
				frame.add(lblfirstPlace, gbc_lblfirstPlace);
			}
			if (position == 2) {
				JLabel lblSecondPlace = new JLabel(Integer.toString(position) + "nd " + player.getName() + " " + Integer.toString(player.getScore()) + " points");
				lblSecondPlace.setFont(new Font("Times New Roman", Font.PLAIN, 20));
				GridBagConstraints gbc_lblSecondPlace = new GridBagConstraints();
				gbc_lblSecondPlace.insets = new Insets(0, 0, 5, 0);
				gbc_lblSecondPlace.gridx = 0;
				gbc_lblSecondPlace.gridy = 4;
				frame.add(lblSecondPlace, gbc_lblSecondPlace);
			}
			if (position == 3){
				JLabel lblThirdPlace = new JLabel(Integer.toString(position) + "rd " + player.getName() + " " + Integer.toString(player.getScore()) + " points");
				lblThirdPlace.setFont(new Font("Times New Roman", Font.PLAIN, 20));
				GridBagConstraints gbc_lblThirdPlace = new GridBagConstraints();
				gbc_lblThirdPlace.gridx = 0;
				gbc_lblThirdPlace.gridy = 6;
				frame.add(lblThirdPlace, gbc_lblThirdPlace);
			}
			position++;
		}
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
