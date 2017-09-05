package backgammon;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.event.MouseInputAdapter;

public class MouseHandler extends MouseInputAdapter {
	private Point pointPressed;
	private int[][] startPositionsWhite = { { 46, 427, 467 }, {}, {}, {}, {}, {}, {}, {}, {}, {}, {},
			{ 500, 307, 347, 387, 427, 467 }, {}, {}, {}, {}, { 340, 10, 50, 90 }, {}, { 245, 10, 50, 90, 130, 170 } };
	private int[][] startPositionsBlack = { {}, {}, {}, {}, {}, { 245, 307, 347, 387, 427, 467 }, {},
			{ 340, 387, 427, 467 }, {}, {}, {}, {}, { 500, 10, 50, 90, 130, 170 }, {}, {}, {}, {}, {}, {}, {}, {}, {},
			{}, { 46, 10, 50 } };
	private final int[][] checkerPosibleXPositions = { { 55 }, { 95 }, { 133 }, { 173 }, { 214 }, { 254 }, { 310 },
			{ 350 }, { 390 }, { 435 }, { 470 }, { 510 }, { 510 }, { 470 }, { 435 }, { 390 }, { 350 }, { 310 }, { 254 },
			{ 214 }, { 173 }, { 133 }, { 95 }, { 55 } };

	private static int[] fieldWhiteCheckersCounter = { 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 3, 0, 5, 0, 0, 0,
			0, 0 };
	private static int[] fieldBlackCheckersCounter = { 0, 0, 0, 0, 0, 5, 0, 3, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 2 };
	private int[] checkersYOrderPositionsTop = { 10, 50, 90, 130, 170, 170, 170, 170, 170, 170, 170, 170, 170, 170,
			170, };
	private int[] checkersYOrderPositionsBottom = { 467, 427, 387, 347, 307, 307, 307, 307, 307, 307, 307, 307, 307,
			307, 307 };
	private static int[] fieldsCheckersCounter = { 2, 0, 0, 0, 0, 5, 0, 3, 0, 0, 0, 5, 5, 0, 0, 0, 3, 0, 5, 0, 0, 0, 0,
			2 };
	private static ArrayList<Checker> checkers = new ArrayList<>();
	JLabel firstDiceJpg = new JLabel();
	JLabel secondDiceJpg = new JLabel();
	JButton dicecButton = new JButton("");
	private static int firstDiceNumber = 1;
	private static int secondDiceNumber = 1;
	private static ArrayList<Integer> numbersToPlay = new ArrayList<>();
	static JLabel checkersTurnLabel = new JLabel("ROLL");

	public MouseHandler() {
	}

	public void distributeCheckers() {
		for (int i = 5; i < 24; i++) {
			for (int j = 0; j < fieldBlackCheckersCounter[i]; j++) {
				checkers.add(new Checker("/img/brown_checker.jpg", startPositionsBlack[i][0],
						startPositionsBlack[i][j + 1], new MouseHandler(), "Black", i));
			}
		}
		for (int i = 0; i < 19; i++) {
			for (int j = 0; j < fieldWhiteCheckersCounter[i]; j++) {
				checkers.add(new Checker("/img/gray_checker.jpg", startPositionsWhite[i][0],
						startPositionsWhite[i][j + 1], new MouseHandler(), "White", i));
			}
		}
	}

	public void mouseDragged(final MouseEvent event) {
		Checker checker = (Checker) event.getSource();
		Point pointDragged = event.getPoint();
		Point loc = checker.getLocation();
		loc.translate(pointDragged.x - pointPressed.x, pointDragged.y - pointPressed.y);
		checker.setLocation(loc);
	}

	private Checker findChecker(int field, String color) {
		for (int i = 0; i < checkers.size(); i++) {
			if (checkers.get(i).getCurrentPosition() == field) {
				if (checkers.get(i).getColor().equalsIgnoreCase(color)) {
					// checkers.get(i).setLocation(0, 0);
					return checkers.get(i);
				}
			}
		}
		return null;
	}

	private boolean isTheNextPositionHigher(Checker e) {
		if (e.getColor().equalsIgnoreCase("black")) {
			System.out.println("Black " + "current: " + e.getCurrentPosition() + "  old POsition: " + e.oldPosition);
			System.out.println(!(e.getCurrentPosition() > e.oldPosition));
			return !(e.getCurrentPosition() >= e.oldPosition);
		}
		System.out.println("White " + "oldPo: " + e.oldPosition + "  curr " + e.getCurrentPosition());
		return e.getCurrentPosition() > e.oldPosition;
	}

	private void blackCheckerReleased(Checker e) {
		if (fieldWhiteCheckersCounter[e.getCurrentPosition()] == 1) {
			Checker white = findChecker(e.getCurrentPosition(), "white");
			System.out.println("BlackReleased:  WHite checker was hit!!!");
			fieldWhiteCheckersCounter[e.getCurrentPosition()]--;
			fieldsCheckersCounter[e.getCurrentPosition()]--;
			white.setLocation(0, 460);
		}
		int y = checkersYOrderPositionsTop[fieldsCheckersCounter[e.getCurrentPosition()]];
		if (e.getY() > 250) {
			y = checkersYOrderPositionsBottom[fieldsCheckersCounter[e.getCurrentPosition()]];
		}
		if (isWantedFieldAvailableForUse(e.getColor(), e.getCurrentPosition())) {
			e.setLocation(checkerPosibleXPositions[e.getCurrentPosition()][0] - 10, y);
			if (e.oldPosition != 25) {
				fieldsCheckersCounter[e.oldPosition]--;
				fieldBlackCheckersCounter[e.oldPosition]--;
			}
			fieldsCheckersCounter[e.getCurrentPosition()]++;
			fieldBlackCheckersCounter[e.getCurrentPosition()]++;
		} else {
			e.setLocation((int) e.position.getX(), (int) e.position.getY());
		}
	}

	private void whiteCheckerReleased(Checker e) {
		if (fieldBlackCheckersCounter[e.getCurrentPosition()] == 1) {
			Checker black = findChecker(e.getCurrentPosition(), "black");
			System.out.println("WhiteReleased:  Black checker was hit!!!'");
			fieldBlackCheckersCounter[e.getCurrentPosition()]--;
			fieldsCheckersCounter[e.getCurrentPosition()]--;
			black.setLocation(0, 10);
		}
		int y = checkersYOrderPositionsTop[fieldsCheckersCounter[e.getCurrentPosition()]];
		if (e.getY() > 250) {
			y = checkersYOrderPositionsBottom[fieldsCheckersCounter[e.getCurrentPosition()]];
		}
		if (fieldBlackCheckersCounter[e.getCurrentPosition()] > 1) {
			e.setLocation((int) e.position.getX(), (int) e.position.getY());
		} else {
			e.setLocation(checkerPosibleXPositions[e.getCurrentPosition()][0] - 10, y);
			if (e.oldPosition != -1) {
				fieldsCheckersCounter[e.oldPosition]--;
				fieldWhiteCheckersCounter[e.oldPosition]--;
			}
			fieldsCheckersCounter[e.getCurrentPosition()]++;
			fieldWhiteCheckersCounter[e.getCurrentPosition()]++;
		}
	}

	private void moveToOldPosition(Checker checker, MouseEvent event) {
		checker.setLocation((int) checker.position.getX(), (int) checker.position.getY());
		setCurrentPositionOnBoard(checker.getX() + 20, checker.getY(), event);
	}

	private boolean isThereSuchDiceNumber(int currentPosition) {
		for (int i = 0; i < numbersToPlay.size(); i++) {
			if (numbersToPlay.get(i) == currentPosition) {
				return true;
			}
		}
		return false;
	}

	void checkerMovements(Checker checker, MouseEvent event) {
		if (numbersToPlay.size() != 0) {
			int currentPosition = checker.getCurrentPosition() - checker.oldPosition;
			if (checker.getColor().equalsIgnoreCase("black")) {
				currentPosition = (checker.oldPosition == 25) ? checker.oldPosition - checker.getCurrentPosition() + 1
						: checker.oldPosition - checker.getCurrentPosition();
			}
			if (isTheNextPositionHigher(checker)) {
				if (isThereSuchDiceNumber(currentPosition)) {
					if (checker.getColor().equalsIgnoreCase("white")) {
						if (isWantedFieldAvailableForUse("white", checker.getCurrentPosition())) {
							whiteCheckerReleased(checker);
							numbersToPlay.remove(numbersToPlay.indexOf(currentPosition));
						} else {
							moveToOldPosition(checker, event);
						}
					} else {
						if (isWantedFieldAvailableForUse("black", checker.getCurrentPosition())) {
							blackCheckerReleased(checker);
							numbersToPlay.remove(numbersToPlay.indexOf(currentPosition));
						} else {
							moveToOldPosition(checker, event);
						}
					}
				} else {
					moveToOldPosition(checker, event);
				}
			} else {
				moveToOldPosition(checker, event);
			}
		} else {
			moveToOldPosition(checker, event);
		}
	}

	public void mouseReleased(MouseEvent event) {
		Checker checker = (Checker) event.getSource();
		setCurrentPositionOnBoard(checker.getX() + 20, checker.getY(), event);
		System.out.println(checker.oldPosition + "  old   new  " + checker.getCurrentPosition());
		checkerMovements(checker, event);
		updateFieldsCounters(checker);
	};

	private boolean isWantedFieldAvailableForUse(String checkerColor, int field) {
		if (checkerColor.equalsIgnoreCase("black")) {
			System.out.println("IswantedFieldavailabel Black:  "
					+ (checkersTurnLabel.getText().equalsIgnoreCase("<html>Black's<br>Turn!</html>")));
			return fieldWhiteCheckersCounter[field] < 2
					&& checkersTurnLabel.getText().equalsIgnoreCase("<html>Black's<br>Turn!</html>");
		}

		System.out.println("IswantedFieldavailabel White:  " + (fieldBlackCheckersCounter[field] < 2
				&& checkersTurnLabel.getText().equalsIgnoreCase("<html>White's<br>Turn!</html>")));
		return fieldBlackCheckersCounter[field] < 2
				&& checkersTurnLabel.getText().equalsIgnoreCase("<html>White's<br>Turn!</html>");
	}

	private void updateFieldsCounters(Checker checker) {
		System.out.println("\nWhite");
		for (int k = 0; k < fieldBlackCheckersCounter.length; k++) {
			System.out.print(fieldWhiteCheckersCounter[k] + " ");
		}
		System.out.println("\nBlack");
		for (int k = 0; k < fieldBlackCheckersCounter.length; k++) {
			System.out.print(fieldBlackCheckersCounter[k] + " ");
		}
		System.out.println("\nCOUNTER");
		for (int k = 0; k < fieldBlackCheckersCounter.length; k++) {
			System.out.print(fieldsCheckersCounter[k] + " ");
		}
		System.out.println();
	}

	public void setCurrentPositionOnBoard(int x, int y, MouseEvent e) {
		Checker checker = (Checker) e.getSource();
		for (int i = 0, j = checkerPosibleXPositions.length - 1; i < checkerPosibleXPositions.length; i++, j--) {
			if (x >= checkerPosibleXPositions[i][0] && x <= checkerPosibleXPositions[i][0] + 20) {
				if (y < 250) {
					checker.setCurrentPosition(j);
				} else {
					checker.setCurrentPosition(i);
				}
				break;
			}
		}
	}

	public void dicec() {
		dicecButton.setIcon(new ImageIcon(SwingBackgammon.class.getResource("/img/rollDicesButton.jpg")));
		dicecButton.addActionListener(new ButtonAction());
		dicecButton.setBounds(565, 46, 89, 96);
		firstDiceJpg.setVerticalAlignment(SwingConstants.TOP);
		firstDiceJpg.setIcon(new ImageIcon(SwingBackgammon.class.getResource("/img/one.jpg")));
		firstDiceJpg.setBounds(565, 189, 50, 50);
		secondDiceJpg.setVerticalAlignment(SwingConstants.TOP);
		secondDiceJpg.setIcon(new ImageIcon(SwingBackgammon.class.getResource("/img/one.jpg")));
		secondDiceJpg.setBounds(565, 260, 50, 50);
		setLabel();
	}

	@Override
	public void mousePressed(final MouseEvent e) {
		pointPressed = e.getPoint();
		Checker checker = (Checker) e.getSource();
		checker.position = checker.getLocation();
		if (isTheNextPositionHigher(checker)) {
			System.out.println("IN   ::: " + checker.oldPosition);
			checker.oldPosition = checker.getCurrentPosition();
			System.out.println(checker.oldPosition + "    mousePressed");
		}
		if (checker.getLocation().getX() < 40) {
			if (checker.getColor().equalsIgnoreCase("white")) {
				checker.oldPosition = -1;
				checker.setCurrentPosition(-1);
			} else {
				checker.oldPosition = 25;
				checker.setCurrentPosition(25);
			}
		}
		System.out.println(checker.oldPosition + "   mousePressed  " + checker.getCurrentPosition());
	}

	public ArrayList<Checker> getCheckers() {
		System.out.println(checkers.size());
		return checkers;
	}

	private void eraseArrayListElemets() {
		while (numbersToPlay.size() != 0) {
			numbersToPlay.remove(0);
			System.out.println(1);
		}
	}

	private void setLabel() {
		checkersTurnLabel.setText("<html>White's<br>Turn!</html>");
		secondDiceJpg.setVerticalAlignment(SwingConstants.TOP);
		checkersTurnLabel.setFont(new Font("Serif", Font.BOLD, 15));
		checkersTurnLabel.setBounds(565, 370, 60, 40);
	}

	class ButtonAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Random random = new Random();
			System.out.println("BUTTON ACTION");
			MouseHandler.firstDiceNumber = random.nextInt(6) + 1;
			MouseHandler.secondDiceNumber = random.nextInt(6) + 1;
			eraseArrayListElemets();
			if (checkersTurnLabel.getText().equalsIgnoreCase("<html>White's<br>Turn!</html>")) {
				checkersTurnLabel.setText("<html>Black's<br>Turn!</html>");
			} else {
				checkersTurnLabel.setText("<html>White's<br>Turn!</html>");
			}
			numbersToPlay.add(firstDiceNumber);
			numbersToPlay.add(secondDiceNumber);
			if (firstDiceNumber == secondDiceNumber) {
				System.out.println("CHIFT");
				numbersToPlay.add(firstDiceNumber);
				numbersToPlay.add(secondDiceNumber);
				System.out.println("ARRAY SIZE :  " + numbersToPlay.size());
			}
			System.out.println(firstDiceNumber);
			setDiceJpg(MouseHandler.this.firstDiceJpg, firstDiceNumber);
			setDiceJpg(MouseHandler.this.secondDiceJpg, secondDiceNumber);
		}

		private void setDiceJpg(JLabel diceJpg, int diceNumber) {
			switch (diceNumber) {
			case 1:
				diceJpg.setIcon(new ImageIcon(SwingBackgammon.class.getResource("/img/one.jpg")));
				break;
			case 2:
				diceJpg.setIcon(new ImageIcon(SwingBackgammon.class.getResource("/img/two.jpg")));
				break;
			case 3:
				diceJpg.setIcon(new ImageIcon(SwingBackgammon.class.getResource("/img/three.jpg")));
				break;
			case 4:
				diceJpg.setIcon(new ImageIcon(SwingBackgammon.class.getResource("/img/four.jpg")));
				break;
			case 5:
				diceJpg.setIcon(new ImageIcon(SwingBackgammon.class.getResource("/img/five.jpg")));
				break;
			case 6:
				diceJpg.setIcon(new ImageIcon(SwingBackgammon.class.getResource("/img/six.jpg")));
				break;
			default:
				diceJpg.setIcon(new ImageIcon(SwingBackgammon.class.getResource("/img/one.jpg")));
				break;
			}
		}
	}

}
