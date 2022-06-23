package main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Iterator;

import util.Constant;
import util.GameUtil;

public class Bird {
	//С����ζ���
	private Rectangle rect;
	//С����ٶ�
	private int acceleration;
	//С�������ֵ
	public boolean life=true;
	//���С��ͼƬ
	private BufferedImage[] images;
	public static final int BIRD_IMG_COUNT=3;
	//���״̬
	private int state;
	public static final int STATE_NORMAR=0;//ƽ�ŷ�
	public static final int STATE_UP=1;//���Ϸ�
	public static final int STATE_DOWN=2;//���·�
	//С��λ��
	private int x=200,y=200;
	//С���ƶ����� ����
	private boolean up=false,down=false;
	//С���ƶ��ٶ�
	private int speed=4;
	//���췽���ж���Դ��ʼ��
	public Bird() {
		images=new BufferedImage[BIRD_IMG_COUNT];
		for (int i=0;i<BIRD_IMG_COUNT;i++) {
			images[i]=GameUtil.loadBufferedImage(Constant.BIRD_IMG[i]);
		}
		int w=images[0].getTileWidth();
		int h=images[0].getHeight();
		rect=new Rectangle(w,h);
	}
	//����С��
	public void draw(Graphics g) {
		flyLogic();
		g.drawImage(images[state],x,y,null);
		//����С��ľ���
//		g.drawRect(x, y, (int)rect.getWidth(),rect.height);
		rect.x=this.x;
		rect.y=this.y;
		
	}
	//����С����ƶ�����
	public void flyLogic() {
		if(up) {
			acceleration--;
			y+=acceleration;
			//�������y��ָ���·�
			if(acceleration<-10) {
				acceleration=-10;
			}
			if(y<20) {
				y=20;
				acceleration=0;
			}
			
		}
		if(!up) {
			acceleration++;
			
			y+=acceleration;//��������
			if(acceleration>10) {
				acceleration=10;
			}
			if(y>475) {
				y=475;
				acceleration=0;
			}
		}
	}
	public void fly(int fly) {
		switch (fly) {
		case 1:
			state=1;
			up=true;
			break;
		case 5:
			state=2;
			up=false;
			break;
		}
	}
	public Rectangle getRect() {
		return rect;
	}
	/*
	 * ���»���С��λ��
	 */
	public void restartDraw() {
		life=true;
		x=200;
		y=200;
	}

}
