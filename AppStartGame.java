package tamagochi;

import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class AppStartGame extends JPanel {

	/**
	 * Create the panel.
	 */
	public AppStartGame() {
		setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblNewLabel = new JLabel("New label");
		add(lblNewLabel);
		
		JLabel lblHowManyPlayers = new JLabel("How many players would you like?");
		add(lblHowManyPlayers);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3"}));
		add(comboBox);
		
		JLabel lblHowManyPets = new JLabel("How Many pets would you like?");
		add(lblHowManyPets);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3"}));
		add(comboBox_1);
		
		JButton btnConfirm = new JButton("Confirm");
		add(btnConfirm);
		
	}

}
