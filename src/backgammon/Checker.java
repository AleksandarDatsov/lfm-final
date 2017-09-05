package backgammon;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Point;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.event.MouseInputAdapter;

public class Checker extends JComponent {

	private JComponent draggable;
	private int currentPosition = -1;
	int oldPosition= 25;
	int oldX;
	Point position;
	private String color;

	public Checker(final String filePath, final int x, final int y, MouseHandler handler, String color,
			int currentPosition) {
		JLabel label = new JLabel();
		label.setIcon(new ImageIcon(SwingBackgammon.class.getResource(filePath)));
		draggable = label;
		setCurrentPosition(currentPosition);
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		setLocation(x, y);
		setSize(40, 40);
		if(color.equalsIgnoreCase("white")){
			oldPosition = -1;
		}
		setLayout(new BorderLayout());
		add(label);
		setColor(color);
		MouseInputAdapter mouseAdapter = handler;
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

	public void setCurrentPosition(int currentPosition) {
		this.currentPosition = currentPosition;
	}

	public int getCurrentPosition() {
		return currentPosition;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
}