package backgammon;

import java.awt.EventQueue;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;

public class SwingBackgammon {

	private JFrame frame;

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

	public SwingBackgammon() {
		try {
			initialize();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void initialize() throws IOException {
		frame = new JFrame();
		frame.setBounds(200, 200, 544, 546);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame = new JFrame("Draggable Components");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(680, 562);
		frame.setLocationRelativeTo(null);
		JPanel panel = new JPanel(null);
		MouseHandler handler = new MouseHandler();
		handler.distributeCheckers();
		for (int i = 0; i < 30; i++) {
			panel.add(handler.getCheckers().get(i));
		}
		frame.getContentPane().add(panel);
		JLabel label_1 = new JLabel();
		label_1.setIcon(new ImageIcon(SwingBackgammon.class.getResource("/img/backgammon_board.jpg")));
		label_1.setBounds(45, 0, 500, 516);
		panel.add(label_1);
		panel.setBackground(Color.LIGHT_GRAY);
		
		handler.dicec();
		panel.add(handler.dicecButton);
		panel.add(handler.firstDiceJpg);
		panel.add(handler.secondDiceJpg);
		panel.add(handler.checkersTurnLabel);
		frame.setVisible(true);
	}
}