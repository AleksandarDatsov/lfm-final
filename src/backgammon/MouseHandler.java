package backgammon;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.event.MouseInputAdapter;

public class MouseHandler extends MouseInputAdapter {
	private Point pointPressed;
	private int[][] startPositionsWhite = { { 245, 10 }, { 245, 50 }, { 245, 90 }, { 245, 130 }, { 245, 170 },
			{ 340, 10 }, { 340, 50 }, { 340, 90 }, { 500, 467 }, { 500, 427 }, { 500, 387 }, { 500, 347 }, { 500, 307 },
			{ 46, 467 }, { 46, 427 } };
	private int[][] startPositionsBlack = { { 46, 10 }, { 46, 50 }, { 500, 10 }, { 500, 50 }, { 500, 90 },
			{ 500, 130 }, { 500, 170 }, { 245, 467 }, { 245, 427 }, { 245, 387 }, { 245, 347 }, { 245, 307 },
			{ 340, 467 }, { 340, 427 }, { 340, 387 } };
	private final int[][] checkerPosibleXPositions = { { 55 }, { 95 }, { 133 }, { 173 }, { 214 }, { 254 },
			{ 310 }, { 350 }, { 390 }, { 435 }, { 470 }, { 510 }, { 510 }, { 470 }, { 435 }, { 390 }, { 350 }, { 310 },
			{ 254 }, { 214 }, { 173 }, { 133 }, { 95 }, { 55 } };

	private  final int[] checkerFieldPositionsUp = { 23, 22, 21, 20, 19, 18, 17, 16, 15, 14, 13, 12 };

	private  int[] checkersYOrderPositionsTop = { 0, 10, 50, 90, 130, 170, 170, 170, 170, 170, 170, 170, 170, 170,
			170, };
	private  int[] checkersYOrderPositionsBottom = { 0, 467, 427, 387, 347, 307, 307, 307, 307, 307, 307, 307,
			307, 307, 307 };
	private static int[] fieldsCheckersCounter = { 2, 0, 0, 0, 0, 5, 0, 3, 0, 0, 0, 5, 5, 0, 0, 0, 3, 0, 5, 0, 0, 0, 0,
			2 };
	private static ArrayList<Checker> checkers = new ArrayList<>();

	public MouseHandler() {
	}

	public void distributeCheckers() {

		for (int i = 0; i < 15; i++) {
			checkers.add(new Checker("/img/brown_checker.jpg", startPositionsBlack[i][0], startPositionsBlack[i][1],
					new MouseHandler(), "Black"));
		}
		for (int i = 0; i < 15; i++) {
			checkers.add(new Checker("/img/gray_checker.jpg", startPositionsWhite[i][0], startPositionsWhite[i][1],
					new MouseHandler(), "White"));

		}
	}

	public void mouseDragged(final MouseEvent event) {
		Checker source = (Checker) event.getSource();

		Point pointDragged = event.getPoint();
		Point loc = source.getLocation();
		loc.translate(pointDragged.x - pointPressed.x, pointDragged.y - pointPressed.y);
		setCurrentPositionOnBoard(source.getX() + 20, source.getY(), event);
		source.setLocation(loc);
	}

	public void mouseReleased(MouseEvent event) {
		Checker source = (Checker) event.getSource();

		int currentPosCopy = source.getCurrentPosition();
		if (currentPosCopy > 11) {
			for (int i = 0; i < checkerFieldPositionsUp.length; i++) {
				if (currentPosCopy == checkerFieldPositionsUp[i]) {
					currentPosCopy = i;
					break;
				}
			}
		}
		int y = checkersYOrderPositionsTop[fieldsCheckersCounter[source.getCurrentPosition()]];
		if (source.getY() > 250) {
			y = checkersYOrderPositionsBottom[fieldsCheckersCounter[source.getCurrentPosition()]];
		}

		source.setLocation(checkerPosibleXPositions[currentPosCopy][0] - 10, y);
		for (int i = 0; i < fieldsCheckersCounter.length; i++) {
			System.out.print(fieldsCheckersCounter[i] + " ");
		}
		System.out.println();
	};

	public void setCurrentPositionOnBoard(int x, int y, MouseEvent e) {
		Checker checker = (Checker)e.getSource();
		checker.oldPosition = checker.getCurrentPosition();
		for (int i = 0, j = checkerPosibleXPositions.length - 1; i < checkerPosibleXPositions.length; i++, j--) {
			if (x >= checkerPosibleXPositions[i][0] && x <= checkerPosibleXPositions[i][0] + 20) {
				if (y < 250) {
					checker.setCurrentPosition(j);
				} else {
					checker.setCurrentPosition(i);
				}
				if (checker.getCurrentPosition()!= -1) {
					fieldsCheckersCounter[checker.oldPosition]--;
				}
				fieldsCheckersCounter[checker.getCurrentPosition()]++;
				break;
			}
		}
	}

	@Override
	public void mousePressed(final MouseEvent e) {
		pointPressed = e.getPoint();
	}

	public ArrayList<Checker> getCheckers() {
		return checkers;
	}

}
