package tamagochi;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Collections;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class fds extends JPanel {

	/**
	 * Create the panel.
	 */
	public fds() {
		setBackground(new Color(240, 255, 255));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{341, 233, 0};
		gridBagLayout.rowHeights = new int[]{0, 20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblPlayerName = new JLabel("  Player: ");
		lblPlayerName.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		GridBagConstraints gbc_lblPlayerName = new GridBagConstraints();
		gbc_lblPlayerName.anchor = GridBagConstraints.WEST;
		gbc_lblPlayerName.insets = new Insets(0, 0, 5, 5);
		gbc_lblPlayerName.gridx = 0;
		gbc_lblPlayerName.gridy = 1;
		add(lblPlayerName, gbc_lblPlayerName);
		
		JLabel lblPetName = new JLabel("  What is your pet ");
		lblPetName.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		GridBagConstraints gbc_lblPetName = new GridBagConstraints();
		gbc_lblPetName.anchor = GridBagConstraints.WEST;
		gbc_lblPetName.insets = new Insets(0, 0, 5, 5);
		gbc_lblPetName.gridx = 0;
		gbc_lblPetName.gridy = 3;
		add(lblPetName, gbc_lblPetName);
		
		JLabel lblSpeciesOption = new JLabel("  What breed would you like your pet to be?");
		lblSpeciesOption.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		GridBagConstraints gbc_lblSpeciesOption = new GridBagConstraints();
		gbc_lblSpeciesOption.anchor = GridBagConstraints.WEST;
		gbc_lblSpeciesOption.gridwidth = 2;
		gbc_lblSpeciesOption.insets = new Insets(0, 0, 5, 0);
		gbc_lblSpeciesOption.gridx = 0;
		gbc_lblSpeciesOption.gridy = 5;
		add(lblSpeciesOption, gbc_lblSpeciesOption);
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
		gbc_textFieldPetName.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldPetName.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldPetName.gridx = 0;
		gbc_textFieldPetName.gridy = 4;
		add(textFieldPetName, gbc_textFieldPetName);
		textFieldPetName.setColumns(10);
		
		JLabel lblAppetite = new JLabel("  Appetite: " + defaultAppetite);
		lblAppetite.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		GridBagConstraints gbc_lblAppetite = new GridBagConstraints();
		gbc_lblAppetite.anchor = GridBagConstraints.WEST;
		gbc_lblAppetite.insets = new Insets(0, 0, 5, 5);
		gbc_lblAppetite.gridx = 0;
		gbc_lblAppetite.gridy = 7;
		add(lblAppetite, gbc_lblAppetite);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Nobutaka\\workspace\\tamagochi\\Images\\Bird.png"));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridheight = 5;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 7;
		add(lblNewLabel, gbc_lblNewLabel);
		
		JLabel lblFatigue = new JLabel("  Fatigue: " + defaultFatigue);
		lblFatigue.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		GridBagConstraints gbc_lblFatigue = new GridBagConstraints();
		gbc_lblFatigue.anchor = GridBagConstraints.WEST;
		gbc_lblFatigue.insets = new Insets(0, 0, 5, 5);
		gbc_lblFatigue.gridx = 0;
		gbc_lblFatigue.gridy = 8;
		add(lblFatigue, gbc_lblFatigue);
		
		JLabel lblDepression = new JLabel("  Depression: " + defaultDepression);
		lblDepression.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		GridBagConstraints gbc_lblDepression = new GridBagConstraints();
		gbc_lblDepression.anchor = GridBagConstraints.WEST;
		gbc_lblDepression.insets = new Insets(0, 0, 5, 5);
		gbc_lblDepression.gridx = 0;
		gbc_lblDepression.gridy = 9;
		add(lblDepression, gbc_lblDepression);
		
		JLabel lblAggression = new JLabel("  Aggression: " + defaultAggression);
		lblAggression.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		GridBagConstraints gbc_lblAggression = new GridBagConstraints();
		gbc_lblAggression.anchor = GridBagConstraints.WEST;
		gbc_lblAggression.insets = new Insets(0, 0, 5, 5);
		gbc_lblAggression.gridx = 0;
		gbc_lblAggression.gridy = 10;
		add(lblAggression, gbc_lblAggression);
		
		JLabel lblFavouriteFood = new JLabel("  Favourite food: " + defaultFavouriteFood);
		lblFavouriteFood.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		GridBagConstraints gbc_lblFavouriteFood = new GridBagConstraints();
		gbc_lblFavouriteFood.anchor = GridBagConstraints.WEST;
		gbc_lblFavouriteFood.insets = new Insets(0, 0, 5, 5);
		gbc_lblFavouriteFood.gridx = 0;
		gbc_lblFavouriteFood.gridy = 11;
		add(lblFavouriteFood, gbc_lblFavouriteFood);
		
		JComboBox<String> comboBoxSpecies = new JComboBox<String>();
		comboBoxSpecies.setModel(new DefaultComboBoxModel<String>(new String[] {"Bird", "Cat", "CatDog", "Dog", "Ocelot", "Tiger"}));
		comboBoxSpecies.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		GridBagConstraints gbc_comboBoxSpecies = new GridBagConstraints();
		
		gbc_comboBoxSpecies.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxSpecies.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxSpecies.gridx = 0;
		gbc_comboBoxSpecies.gridy = 6;
		add(comboBoxSpecies, gbc_comboBoxSpecies);
		
		JLabel lblFavouriteToy = new JLabel("  Favourite toy: " + defaultFavouriteToy);
		lblFavouriteToy.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		GridBagConstraints gbc_lblFavouriteToy = new GridBagConstraints();
		gbc_lblFavouriteToy.anchor = GridBagConstraints.WEST;
		gbc_lblFavouriteToy.insets = new Insets(0, 0, 5, 5);
		gbc_lblFavouriteToy.gridx = 0;
		gbc_lblFavouriteToy.gridy = 12;
		add(lblFavouriteToy, gbc_lblFavouriteToy);
		
		JButton btnNewButton = new JButton("Confirm");
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 13;
		add(btnNewButton, gbc_btnNewButton);
		setSize(575, 475);
		setVisible(true);
	}

}
