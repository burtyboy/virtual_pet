package main;



import javax.swing.JFrame;
import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JTextPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class Instruction extends JFrame{

	public JFrame frame;
	/**
	 * Create the application.
	 */
	public Instruction() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 204, 255));
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JButton btnGoBack = new JButton("Go back");
		btnGoBack.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				App window = new App();
				window.frame.setVisible(true);
			}
		});
		frame.getContentPane().add(btnGoBack, BorderLayout.SOUTH);
		
		JTextPane txtpnLearnItYourself = new JTextPane();
		txtpnLearnItYourself.setBackground(new Color(0, 153, 255));
		txtpnLearnItYourself.setText("Learn it yourself mate!");
		frame.getContentPane().add(txtpnLearnItYourself, BorderLayout.CENTER);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}