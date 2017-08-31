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
	private int[][]startPositionsWhite = {{245,10},{245,50},{245,90},{245,130},{245,170},{340,10},{340,50},{340,90},{500,467},{500,427},{500,387},{500,347},{500,307},{46,467},{46,427}};
	private int[][]startPositionsBlack = {{46,10},{46,50},{500,10},{500,50},{500,90},{500,130},{500,170},{245,467},{245,427},{245,387},{245,347},{245,307},{340,467},{340,427},{340,387}};
	public Checker(final String filePath,final int x, final int y) {
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
	private class MouseHandler extends MouseInputAdapter {
		@Override
		public void mouseDragged(final MouseEvent e) {
			Point pointDragged = e.getPoint();
			Point loc = getLocation();
			loc.translate(pointDragged.x - pointPressed.x, pointDragged.y - pointPressed.y);
			setLocation(loc);
		}

		@Override
		public void mousePressed(final MouseEvent e) {
			pointPressed = e.getPoint();
		}
	}
}
