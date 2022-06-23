package main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Cloud {
	//�Ʋ�ͼƬ
	private BufferedImage img;
	//�Ʋʵ��ٶ�
	private int speed;
	//�Ʋʵ�λ��
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
	 * �����ж��Ʋ��Ƿ�ɳ���Ļ����
	 */
	public boolean isOutFrame() {
		if(x<-100) {
			return true;
		}
		return false;
	}

}
