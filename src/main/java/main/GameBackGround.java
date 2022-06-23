package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import util.Constant;
import util.GameUtil;

public class GameBackGround {
	//存放背景图片
	private BufferedImage bgimg;
	//构造器初始化资源
	public GameBackGround() {
		bgimg=GameUtil.loadBufferedImage(Constant.BG_IMG_PATH);
	}
	//绘制图片
	public void draw(Graphics g) {
		//填充背景色
		g.setColor(Constant.BG_COLOR);
		g.fillRect(0, 0, Constant.frame_width, Constant.frame_height);
		g.setColor(Color.black);;
		//得到图片的宽度和高度
	    int height=bgimg.getHeight();
	    int width=bgimg.getWidth();
	    //所需要的图片的张数
	    int count=Constant.frame_width/width+1;
	    for(int i=0;i<count;i++) {
	    	g.drawImage(bgimg, width*i, Constant.frame_height-height, null);
	    }
	}

}
