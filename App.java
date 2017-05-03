package tamagochi;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import java.awt.Button;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



public class App{

	public JFrame frame;
	private GameEnvironment game = new GameEnvironment();

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
		
		JLabel lblNewLabel = new JLabel("The Virtual Pet");
		lblNewLabel.setFont(new Font("Papyrus", Font.BOLD, 46));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBackground(new Color(51, 0, 102));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		frame.getContentPane().add(lblNewLabel);
		
		Button startButton = new Button("Start a new game!");
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		startButton.setForeground(new Color(255, 255, 255));
		startButton.setFont(new Font("Papyrus", Font.PLAIN, 16));
		startButton.setBackground(new Color(0, 51, 51));
		frame.getContentPane().add(startButton);
		Button instructionButton = new Button("Check the instruction.");
		instructionButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Instruction window = new Instruction();
				window.frame.setVisible(true);
			}
		});
		frame.getContentPane().add(instructionButton);
		instructionButton.setForeground(Color.WHITE);
		instructionButton.setFont(new Font("Papyrus", Font.PLAIN, 16));
		instructionButton.setBackground(new Color(0, 51, 51));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
