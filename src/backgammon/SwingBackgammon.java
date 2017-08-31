package backgammon;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.SwingConstants;

public class SwingBackgammon {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SwingBackgammon window = new SwingBackgammon();
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
	public SwingBackgammon() {
		try {
			initialize();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws IOException
	 */
	JLabel firstDice = new JLabel();
	JLabel secondDice = new JLabel();

	private void initialize() throws IOException {
		frame = new JFrame();
		frame.setBounds(200, 200, 544, 546);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame = new JFrame("Draggable Components");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(680, 562);
		frame.setLocationRelativeTo(null);
		JPanel panel = new JPanel(null);
		Controller controller = new Controller();
	
		for (int i = 0; i < 30; i++) {
			panel.add(controller.getCheckers().get(i));
		}

		frame.getContentPane().add(panel);
		JLabel label_1 = new JLabel();
		label_1.setIcon(new ImageIcon(SwingBackgammon.class.getResource("/img/backgammon_board.jpg")));
		label_1.setBounds(45, 0, 500, 516);
		panel.add(label_1);

		JButton btnNewButton = new JButton("New button");
		btnNewButton.setIcon(new ImageIcon(SwingBackgammon.class.getResource("/img/dices.jpg")));
		btnNewButton.addActionListener(new ButtonAction());
		btnNewButton.setBounds(565, 46, 89, 96);
		panel.add(btnNewButton);

		firstDice.setVerticalAlignment(SwingConstants.TOP);
		firstDice.setIcon(new ImageIcon(SwingBackgammon.class.getResource("/img/one.jpg")));
		firstDice.setBounds(565, 189, 50, 50);
		panel.add(firstDice);
		panel.setBackground(Color.LIGHT_GRAY);
		secondDice.setVerticalAlignment(SwingConstants.TOP);
		secondDice.setIcon(new ImageIcon(SwingBackgammon.class.getResource("/img/one.jpg")));
		secondDice.setBounds(565, 260, 50, 50);
		panel.add(secondDice);
		frame.setVisible(true);
	}

	class ButtonAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Random random = new Random();
			int firstDice = random.nextInt(6) + 1;
			int secondDice = random.nextInt(6) + 1;
			setDiceJpg(SwingBackgammon.this.firstDice, firstDice);
			setDiceJpg(SwingBackgammon.this.secondDice, secondDice);
		}

		private void setDiceJpg(JLabel label, int diceNumber) {
			switch (diceNumber) {
			case 1:
				label.setIcon(new ImageIcon(SwingBackgammon.class.getResource("/img/one.jpg")));
				break;
			case 2:
				label.setIcon(new ImageIcon(SwingBackgammon.class.getResource("/img/two.jpg")));
				break;
			case 3:
				label.setIcon(new ImageIcon(SwingBackgammon.class.getResource("/img/three.jpg")));
				break;
			case 4:
				label.setIcon(new ImageIcon(SwingBackgammon.class.getResource("/img/four.jpg")));
				break;
			case 5:
				label.setIcon(new ImageIcon(SwingBackgammon.class.getResource("/img/five.jpg")));
				break;
			case 6:
				label.setIcon(new ImageIcon(SwingBackgammon.class.getResource("/img/six.jpg")));
				break;
			default:
				label.setIcon(new ImageIcon(SwingBackgammon.class.getResource("/img/one.jpg")));
				break;
			}
		}
	}

}