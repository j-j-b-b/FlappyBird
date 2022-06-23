package main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import util.GameUtil;

public class GameFrontGround {
	//云彩的个数
	private static final int CLOUD_COUNT=2;
	//存放云彩的容器
	private List<Cloud> clouds;
	//云彩的飞行速度
	private static final int CLOUD_SPEED=1;
	//使用到的图片资源
	private BufferedImage[] img;
	//用于产生随机数的函数
	private Random random;
	//构造器初始化数据
	public GameFrontGround() {
		clouds=new ArrayList<>();
		img=new BufferedImage[CLOUD_COUNT];
		//将容器中添加云彩图片
		for(int i=0;i<CLOUD_COUNT;i++) {
			img[i]=GameUtil.loadBufferedImage("img/cloud"+i+".png");
		}
		random=new Random();
	}
	//绘制云彩
	public void draw(Graphics g) {
//云不移动时候的画法
//		Cloud cloud=new Cloud(img[1],CLOUD_SPEED,300,300);
//		clouds.add(cloud);
		logic();
		for(int i=0;i<clouds.size();i++) {
			//画云朵
			clouds.get(i).draw(g);
		}
	}
	/*
	 * 用于云彩个数控制
	 */
	private void logic() {
		if((int)(500*Math.random())<5) {
			Cloud cloud=new Cloud(img[random.nextInt(CLOUD_COUNT)],CLOUD_SPEED,600,random.nextInt(150));
			clouds.add(cloud);
		}
		//移除云彩
		for(int i=0;i<clouds.size();i++) {
			Cloud cloud=clouds.get(i);
			if(cloud.isOutFrame()) {
				clouds.remove(i);
				i--;
			}
			
		}
	}

}
