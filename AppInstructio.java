package tamagochi;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Button;
import java.awt.TextArea;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class AppInstructio extends JPanel {

	/**
	 * Create the panel.
	 */
	public AppInstructio() {
		setBackground(new Color(255, 255, 240));
		setLayout(new BorderLayout(0, 0));
		TextArea textArea = new TextArea();
		textArea.setForeground(new Color(0, 0, 0));
		textArea.setBackground(new Color(240, 255, 240));
		textArea.setText("Welcome to the world of the Virtual pet. Your goal here is let your pet have a \r\ngreat time.");
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
		add(textArea, BorderLayout.CENTER);
		JLabel lblStartANew = new JLabel("Learn the world of the Virtual Pet");
		lblStartANew.setBackground(new Color(240, 255, 240));
		lblStartANew.setForeground(new Color(0, 0, 0));
		lblStartANew.setHorizontalAlignment(SwingConstants.CENTER);
		lblStartANew.setFont(new Font("Bradley Hand ITC", Font.BOLD, 25));
		add(lblStartANew, BorderLayout.NORTH);
		Button button = new Button("Start a new game");
		add(button, BorderLayout.SOUTH);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
	}

}
