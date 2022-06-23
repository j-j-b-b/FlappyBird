package util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

public class MyButton {
	private int x,y,height,width;
	private String text;
	private Rectangle bounds;
    public MyButton(String text,int x,int y,int width,int height) {
		// TODO Auto-generated constructor stub
    	this.text=text;
    	this.x=x;
    	this.y=y;
    	this.width=width;
    	this.height=height;
    	initBounds();
	}
    private void initBounds() {
    	this.bounds=new Rectangle(width,height);
    	bounds.x=this.x;
    	bounds.y=this.y;
    }
    public void draw(Graphics g) {
    	//Body
    	g.setColor(Color.GRAY);
    	g.fillRect(x, y, width, height);
    	//Border
    	g.setColor(Color.black);
    	g.drawRect(x, y, width, height);
    	//Text
    	g.setFont(new Font("Î¢ÈíÑÅºÚ",2,30));
    	g.drawString(text, x, y+height/2+10);
    }
    public Rectangle getBounds() {
    	return bounds;
    }

}
