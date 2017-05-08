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
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Collections;
import java.awt.event.ActionEvent;



public class App{

	JFrame frame;
	private int numPlayers;
	private Player currentPlayer;
	private JLabel lblIntroTitle;
	private int gameLength;
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
		ImageIcon defaultPetImage = new ImageIcon("C:\\Users\\Nobutaka\\workspace\\tamagochi\\Images\\Bird.png");
		
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
					ImageIcon birdImage = new ImageIcon("C:\\Users\\Nobutaka\\workspace\\tamagochi\\Images\\Bird.png");
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
					ImageIcon catImage = new ImageIcon("C:\\Users\\Nobutaka\\workspace\\tamagochi\\Images\\Cat.png");
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
					ImageIcon catDogImage = new ImageIcon("C:\\Users\\Nobutaka\\workspace\\tamagochi\\Images\\CatDog.png");
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
					ImageIcon dogImage = new ImageIcon("C:\\Users\\Nobutaka\\workspace\\tamagochi\\Images\\Dog.png");
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
					ImageIcon ocelotImage = new ImageIcon("C:\\Users\\Nobutaka\\workspace\\tamagochi\\Images\\Ocelot.png");
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
					ImageIcon tigerImage = new ImageIcon("C:\\Users\\Nobutaka\\workspace\\tamagochi\\Images\\Tiger.png");
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
					gameLength = Integer.parseInt(numDays);
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
									}
								});
					}
					else {
						
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
