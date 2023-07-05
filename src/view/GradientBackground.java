
package view;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

/**
 * @author ThuyNguyen
 *
 */
public class GradientBackground extends JPanel {
	
	private static int VERTICAL;
	private static int HORIZONTAL;
	private static int DIAGONAL_DOWN;
	private static int DIAGONAL_UP;
	
	
	private Color color1, color2;
	private int direction;
	
	
	
	
	public GradientBackground() {
		super();
		color1 = new Color(156, 89, 254, 1);
		color2 = new Color(111, 83, 253, 1);
	}
	public GradientBackground(Color color1, Color color2) {
		super();
		this.color1 = color1;
		this.color2 = color2;
	}
	public GradientBackground(Color color1, Color color2, int direction) {
		super();
		this.color1 = color1;
		this.color2 = color2;
		this.direction = direction;
	}
	
	@Override 
	protected void paintComponent(Graphics g ) {
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		GradientPaint gradientPanit;
		
		if(direction == HORIZONTAL) {
			gradientPanit = new GradientPaint(0, getHeight()/2, color1, getWidth(),getHeight()/2,color2);
		}else if (direction == DIAGONAL_DOWN) {
			gradientPanit = new GradientPaint(0, getHeight(), color1, getWidth(),0,color2);
		}else if (direction == DIAGONAL_UP) {
			gradientPanit = new GradientPaint(0, 0, color1, getWidth(),getHeight(),color2);
		}else {
			gradientPanit = new GradientPaint(0, 0, color1, 0,getHeight(),color2);
		}
		
		g2d.setPaint(gradientPanit);
		g2d.fillRect(0,0,getWidth(),getHeight());
	}
	
	public Color getColor1() {
		return color1;
	}
	public void setColor1(Color color1) {
		this.color1 = color1;
	}
	public Color getColor2() {
		return color2;
	}
	public void setColor2(Color color2) {
		this.color2 = color2;
	}
	public int getDirection() {
		return direction;
	}
	public void setDirection(int direction) {
		this.direction = direction;
	}
	
}
