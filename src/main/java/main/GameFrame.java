package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;


import util.Constant;
import util.MyButton;

public class GameFrame extends Frame{
	//开始界面
	//游戏外挂
	private MyButton peasy,pdiff;
	boolean easy=false;
	private Music music;
	private Music butt;
	private GameBackGround gameBackGround;
	private Bird bird;
	private GameBarrierLayer gameBarrierLayer;
	private GameBarrierLayer1 gameBarrierLayer1;
	//实例化游戏前景类
	private GameFrontGround gameFrontGroud;
	//存放图片的图片
	private BufferedImage buffimg=new BufferedImage(Constant.frame_width,Constant.frame_height,BufferedImage.TYPE_4BYTE_ABGR);
	public GameFrame() throws FileNotFoundException {
		//窗口是否可见
		setVisible(true);
		//窗口大小
		setSize(Constant.frame_width,Constant.frame_height);
		//窗口标题
		setTitle(Constant.frame_title);
		//窗口的初始化位置
		setLocation(Constant.frame_x,Constant.frame_y);
		//窗口的大小不可改变
		setResizable(false);
		//窗口添加关闭事件
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);//结束程序
			}
		});	
		initGamg();
		new run1().start();
		run2 x=new run2();
		new line().start();
		x.start();
  		//添加按键监听
//   	 addKeyListener(new KeyAdapter() {
//			@Override
//			public void keyPressed(KeyEvent e) {
//				// TODO Auto-generated method stub
//				switch(e.getKeyCode()) {
//				case KeyEvent.VK_1:
//				    easy=true;
//				    new run().start();
//					break;
//			   
//				}
//				
//			}
//			
//		});
   	 addMouseListener(new MouseAdapter(){
         @Override
          public void mouseClicked(MouseEvent e){
             super.mouseClicked(e);
            if(peasy.getBounds().contains(e.getX(), e.getY())) {
            	 easy=true;
				 new run().start();
            }
            if(pdiff.getBounds().contains(e.getX(), e.getY())) {
           	     easy=false;
				 new run().start();
           }
         }
      });
  		addKeyListener(new KeyAdapter() {
  			@Override
  			public void keyPressed(KeyEvent e) {
  				// TODO Auto-generated method stub
  				add(e);
  				x.vlo();
  			}
  			@Override
  			public void keyReleased(KeyEvent e) { 
  				// TODO Auto-generated method stub
  				minu(e);
  				x.stopthis();
  			}
  		});
	}
	public void initGamg() throws FileNotFoundException {
		gameBackGround=new GameBackGround();
		bird =new Bird();
		gameFrontGroud=new GameFrontGround();
		gameBarrierLayer=new GameBarrierLayer();
		gameBarrierLayer1=new GameBarrierLayer1();
		music=new Music();
		butt=new Music();
		peasy=new MyButton("Easy", 180, 250, 100, 30);
		pdiff=new MyButton("Diff", 180, 300, 100, 30);
	    
	}
	//多线程运行
	class run extends Thread{
		@Override
		public void run() {
			// TODO Auto-generated method stub
		 while(true) {
			 repaint();
			 try {
				 Thread.sleep(33);
			 } catch (InterruptedException e) {
				 // TODO: handle exception
				 e.printStackTrace();
			 }
		}
		}
	}
	class line extends Thread{
		@Override
		public void run() {
			// TODO Auto-generated method stub
			paint(getGraphics());
		}
	}
	class run1 extends Thread{
		@Override
		public void run() {
			// TODO Auto-generated method stub
			playMusic(0);
		}
	}
	class run2 extends Thread{
		@Override
		public void run() {
			// TODO Auto-generated method stub
		}
		public void vlo() {
			playSE(1);
		}
		public void stopthis() {
			stopMusic();
		}
	}
	/*
	 * 绘制所编写的全部内容
	 */
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		Graphics graphics=buffimg.getGraphics();
		gameBackGround.draw(graphics);
		bird.draw(graphics);
		gameFrontGroud.draw(graphics);
		//一次性地将图片绘制到屏幕中
		g.drawImage(buffimg, 0, 0,null);
//		String over="按1简单";
//		g.setColor(Color.red);
//		g.setFont(new Font("微软雅黑",1,60));
//		g.drawString(over, 180, 250);
//		String reset="按2困难";
//		g.drawString(reset, 25, 350);
		drawButton(g);
	}
	private void drawButton(Graphics g) {
		// TODO Auto-generated method stub
		peasy.draw(g);
		pdiff.draw(g);
	}
	@Override
	public void update(Graphics g) {
		if(bird.life) {
			// TODO Auto-generated method stub
			//得到图片的画笔
			Graphics graphics=buffimg.getGraphics();
			gameBackGround.draw(graphics);
			bird.draw(graphics);
			gameFrontGroud.draw(graphics);
			if(easy) {
			gameBarrierLayer.draw(graphics,bird);}
			else {
				gameBarrierLayer1.draw(graphics,bird);
			}
			//一次性地将图片绘制到屏幕中
			g.drawImage(buffimg, 0, 0,null);
		}else {
			String over="游戏结束";
			g.setColor(Color.red);
			g.setFont(new Font("微软雅黑",1,60));
			g.drawString(over, 180, 250);
			String reset1="Click 1 Reset Easy";
			g.drawString(reset1, 25, 350);
			String reset2="Click 2 Reset Diff";
			g.drawString(reset2, 25, 400);
		}
	}
	//按键
	public void add(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_UP:
			bird.fly(1);
			break;
		case KeyEvent.VK_1:
			if(bird.life==false) {
				restart1();
			}
			break;
		case KeyEvent.VK_2:
			if(bird.life==false) {
				if(easy==true) {
					easy=false;
					new run().start();
				}else {
					restart2();
				}
			}
			break;
		}

	}
	//抬键
	public void minu(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_UP:
			bird.fly(5);
			break;
		}
	}
	/*
	 * 重置游戏
	 */
	public void restart1() {
		gameBarrierLayer.restant();
		bird.restartDraw();
	}
	public void restart2() {
		gameBarrierLayer1.restant();
		bird.restartDraw();
	}
	public void playMusic(int i) {
		music.setFile(i);
		music.play();
		music.loop();
	}
	public void stopMusic() {
		butt.stop();
	}
	public void playSE(int i) {
		butt.setFile(i);
		butt.play();
	}
}
