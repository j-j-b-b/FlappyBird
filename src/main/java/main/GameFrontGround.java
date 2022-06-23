package main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import util.GameUtil;

public class GameFrontGround {
	//�Ʋʵĸ���
	private static final int CLOUD_COUNT=2;
	//����Ʋʵ�����
	private List<Cloud> clouds;
	//�Ʋʵķ����ٶ�
	private static final int CLOUD_SPEED=1;
	//ʹ�õ���ͼƬ��Դ
	private BufferedImage[] img;
	//���ڲ���������ĺ���
	private Random random;
	//��������ʼ������
	public GameFrontGround() {
		clouds=new ArrayList<>();
		img=new BufferedImage[CLOUD_COUNT];
		//������������Ʋ�ͼƬ
		for(int i=0;i<CLOUD_COUNT;i++) {
			img[i]=GameUtil.loadBufferedImage("img/cloud"+i+".png");
		}
		random=new Random();
	}
	//�����Ʋ�
	public void draw(Graphics g) {
//�Ʋ��ƶ�ʱ��Ļ���
//		Cloud cloud=new Cloud(img[1],CLOUD_SPEED,300,300);
//		clouds.add(cloud);
		logic();
		for(int i=0;i<clouds.size();i++) {
			//���ƶ�
			clouds.get(i).draw(g);
		}
	}
	/*
	 * �����Ʋʸ�������
	 */
	private void logic() {
		if((int)(500*Math.random())<5) {
			Cloud cloud=new Cloud(img[random.nextInt(CLOUD_COUNT)],CLOUD_SPEED,600,random.nextInt(150));
			clouds.add(cloud);
		}
		//�Ƴ��Ʋ�
		for(int i=0;i<clouds.size();i++) {
			Cloud cloud=clouds.get(i);
			if(cloud.isOutFrame()) {
				clouds.remove(i);
				i--;
			}
			
		}
	}

}
