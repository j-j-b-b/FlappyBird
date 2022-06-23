package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import util.Constant;
import util.GameUtil;

public class GameBackGround {
	//��ű���ͼƬ
	private BufferedImage bgimg;
	//��������ʼ����Դ
	public GameBackGround() {
		bgimg=GameUtil.loadBufferedImage(Constant.BG_IMG_PATH);
	}
	//����ͼƬ
	public void draw(Graphics g) {
		//��䱳��ɫ
		g.setColor(Constant.BG_COLOR);
		g.fillRect(0, 0, Constant.frame_width, Constant.frame_height);
		g.setColor(Color.black);;
		//�õ�ͼƬ�Ŀ�Ⱥ͸߶�
	    int height=bgimg.getHeight();
	    int width=bgimg.getWidth();
	    //����Ҫ��ͼƬ������
	    int count=Constant.frame_width/width+1;
	    for(int i=0;i<count;i++) {
	    	g.drawImage(bgimg, width*i, Constant.frame_height-height, null);
	    }
	}

}
