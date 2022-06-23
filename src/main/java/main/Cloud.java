package main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Cloud {
	//云彩图片
	private BufferedImage img;
	//云彩的速度
	private int speed;
	//云彩的位置
	private int x,y;
	
	public Cloud(BufferedImage img,int speed,int x,int y) {
		this.img=img;
		this.speed=speed;
		this.x=x;
		this.y=y;
	}
	
	public void draw(Graphics g) {
		x-=speed;
		g.drawImage(img, x, y, null);
	}
	/*
	 * 用于判断云彩是否飞出屏幕以外
	 */
	public boolean isOutFrame() {
		if(x<-100) {
			return true;
		}
		return false;
	}

}
