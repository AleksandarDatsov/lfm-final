package backgammon;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class DicePanel extends JPanel {
	private final Dice dice;

	private JButton rollButton;
	private JLabel displayLabel;

	public DicePanel(Dice dice) {
		this.dice = dice;
		rollButton = new JButton("New button");
		rollButton.setIcon(new ImageIcon(SwingBackgammon.class.getResource("/img/dices.jpg")));
		rollButton.setBounds(555, 44, 89, 77);
		displayLabel = new JLabel("Roll Result");
		displayLabel.setFont(displayLabel.getFont().deriveFont(15.0f));
		displayLabel.setVerticalAlignment(SwingConstants.TOP);
		// displayLabel.setBounds(555, 147, 89, 77);
		rollButton.addActionListener(				
				e -> displayLabel.setText("Rolled result" + "\n" + dice.roll() + " and " + dice.roll()));
		add(rollButton);
		add(displayLabel);
	}
}