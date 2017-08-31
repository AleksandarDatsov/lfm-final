package backgammon;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.event.MouseInputAdapter;

public class Checker extends JComponent {
	private Point pointPressed;
	private JComponent draggable;
	private final int[][] checkerPosibleXPositions = { { 55 }, { 95 }, { 133 }, { 173 }, { 214 }, { 254 }, { 310 },
			{ 350 }, { 390 }, { 435 }, { 460 }, { 510 }, { 510 }, { 470 }, { 435 }, { 400 }, { 360 }, { 320 }, { 260 },
			{ 220 }, { 180 }, { 140 }, { 100 }, { 60 } };
	private final int[] checkerPosotionUp = { 24, 23, 22, 21, 20, 19, 18, 17, 16, 15, 14, 13 };
	private int[][] startPositionsWhite = { { 245, 10 }, { 245, 50 }, { 245, 90 }, { 245, 130 }, { 245, 170 },
			{ 340, 10 }, { 340, 50 }, { 340, 90 }, { 500, 467 }, { 500, 427 }, { 500, 387 }, { 500, 347 }, { 500, 307 },
			{ 46, 467 }, { 46, 427 } };
	private int[][] startPositionsBlack = { { 46, 10 }, { 46, 50 }, { 500, 10 }, { 500, 50 }, { 500, 90 }, { 500, 130 },
			{ 500, 170 }, { 245, 467 }, { 245, 427 }, { 245, 387 }, { 245, 347 }, { 245, 307 }, { 340, 467 },
			{ 340, 427 }, { 340, 387 } };
	private int currentPosition;

	public Checker(final String filePath, final int x, final int y) {
		JLabel label = new JLabel();
		label.setIcon(new ImageIcon(SwingBackgammon.class.getResource(filePath)));
		draggable = label;
		// draggable.setCursor(draggable.getCursor());
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		setLocation(x, y);
		setSize(40, 40);
		setLayout(new BorderLayout());
		add(label);
		MouseInputAdapter mouseAdapter = new MouseHandler();
		addMouseMotionListener(mouseAdapter);
		addMouseListener(mouseAdapter);
	}

	@Override
	public void setBorder(final javax.swing.border.Border border) {
		super.setBorder(border);
		if (border != null) {
			Dimension size = draggable.getPreferredSize();
			Insets insets = border.getBorderInsets(this);
			size.width += (insets.left + insets.right + 5);
			size.height += (insets.top + insets.bottom);
			setSize(size);
		}
	}

	public int[][] getStartPositionsBlack() {
		return startPositionsBlack;
	}

	public int[][] getStartPositionsWhite() {
		return startPositionsWhite;
	}

	public int[][] getCheckerPosiblePositions() {
		return checkerPosibleXPositions;
	}

	public int getCurrentPosition() {
		return currentPosition;
	}

	public void setCurrentPositionOnBoard(int x, int y) {

		for (int i = 0; i < checkerPosibleXPositions.length; i++) {
			if (x >= checkerPosibleXPositions[i][0] && x <= checkerPosibleXPositions[i][0] + 20) {
				if (y < 250) {
					this.currentPosition = checkerPosotionUp[i];
				} else {
					this.currentPosition = i + 1;
				}
				System.out.println(this.currentPosition);
				return;
			}
		}
	}

	private class MouseHandler extends MouseInputAdapter {
		@Override
		public void mouseDragged(final MouseEvent e) {
			Point pointDragged = e.getPoint();
			Point loc = getLocation();
			loc.translate(pointDragged.x - pointPressed.x, pointDragged.y - pointPressed.y);
			setCurrentPositionOnBoard(getX() + 20, getY() + 20);
			setLocation(loc);
		}

		public void mouseReleased(MouseEvent arg0) {
			setLocation(checkerPosibleXPositions[currentPosition - 1][0] - 10, getY());
		};

		@Override
		public void mousePressed(final MouseEvent e) {
			pointPressed = e.getPoint();
		}
	}
}
