package tamagochi;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Color;

public class ErrorNoLetter extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public ErrorNoLetter() {
		getContentPane().setBackground(new Color(255, 255, 255));
		setBackground(new Color(255, 255, 255));
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.EAST);
		{
			JLabel lblErrorMessage = new JLabel("");
			lblErrorMessage.setBackground(new Color(255, 255, 255));
			lblErrorMessage.setIcon(new ImageIcon("C:\\Users\\Nobutaka\\workspace\\tamagochi\\Images\\ErrorNoLetter.png"));
			lblErrorMessage.setFont(new Font("Times New Roman", Font.PLAIN, 24));
			lblErrorMessage.setHorizontalAlignment(SwingConstants.CENTER);
			getContentPane().add(lblErrorMessage, BorderLayout.CENTER);
		}
	}

}
