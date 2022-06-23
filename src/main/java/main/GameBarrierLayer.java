package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameBarrierLayer {
	private GameTime gameTime;
	private int txt;
	private Random random=new Random();
	private List<Barrier> barriers;
	public GameBarrierLayer() {
		barriers=new ArrayList<>();
		gameTime=new GameTime();
	}
	//绘制障碍物
	public void draw(Graphics g,Bird bird) {
//		Barrier barrier=new Barrier(200,0,200,0);
//		barriers.add(barrier);
//		barriers.get(0).draw(g);
//		Barrier barrier1=new Barrier(300,0,300,2);
//		barriers.add(barrier1);
//		barriers.get(1).draw(g);
		for(int i=0;i<barriers.size();i++) {
			Barrier barrier=barriers.get(i);
			if(barrier.isVisible()) {
				barrier.draw(g);
			}else {
				 Barrier remove=barriers.remove(i);
				 Barrierpool.setPool(remove);
				 i--;
			}
		}
		collideBird(bird);
		logic(g);
	}
	public void logic(Graphics g) {
		if(barriers.size()==0) {
			ran();
			gameTime.begin();
//			Barrier top=new Barrier(600,0,numberTop,0);
//			barriers.add(top);
//			Barrier down=new Barrier(600,500-numberDown,numberDown,2);
//			barriers.add(down);
			insert(600,0,numberTop,0);
			insert(600,500-numberDown,numberDown,2);
		}else {
			long differ=gameTime.differ();
			g.setColor(Color.white);
			g.setFont(new Font("微软雅黑",1,20));
			g.drawString("坚持了"+differ+"秒",30,50);
			txt=getTxt();
			if(differ<txt) {
				g.drawString("最高成绩:"+txt, 200, 50);
			}else {
				setTxt(String.valueOf(differ));
				g.drawString("最高成绩:"+getTxt(), 200, 50);
			}
			//判断最后一个障碍物是否完全进入屏幕中
			Barrier last=barriers.get(barriers.size()-1);
			if(last.isInFrame()) {
				ran();
				if(number<200) {
					insert(600,100,340,4);
				}
				else {
					insert(600,0,numberTop,0);
					insert(600,500-numberDown,numberDown,2);
				}
				/*
				 * 节省空间
				 */
//				Barrier top=new Barrier(600,0,numberTop,0);
//				barriers.add(top);
//				Barrier down=new Barrier(600,500-numberDown,numberDown,2);
//				barriers.add(down);
			}
		}
	}
	File file=new File("D:\\game.txt");
	/*
	 * 用于得到文件中的数据
	 */
	public int getTxt() {
		BufferedReader in=null;
		try {
			in = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int read=0;
		try {
			read = Integer.parseInt(in.readLine());
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return read;
	}
	/*
	 * 用于储存数据
	 */
	public void setTxt(String str) {
		FileWriter filewriter=null;
		try {
			filewriter = new FileWriter(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			filewriter.write(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			filewriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/*
	 * 用于从池中获取对象
	 */
	public void insert(int x,int y,int num,int type) {
		Barrier top=Barrierpool.getPool();
		top.setX(x);
		top.setY(y);
		top.setHeight(num);
		top.setType(type);
		top.setVisible(true);
		barriers.add(top);
	}
	//上方障碍物的高度
	private int numberTop;
	//下方障碍物的高度
	private int numberDown;
	private int number;
	//产生两个100—500之间的随机高度
	public void ran() {
		numberTop=random.nextInt(400)+100;
		numberDown=random.nextInt(400)+100;
		number=random.nextInt(500);
		//如果管道重合，重新随机
		if(numberDown+numberTop>450) {
			ran();
		}
	}
	/*
	 * 判断障碍物和小鸟发生碰撞
	 */
	public boolean collideBird(Bird bird) {
		for(int i=0;i<barriers.size();i++) {
			Barrier barrier=barriers.get(i);
			//判断矩形是否有相交
			if(barrier.getRect().intersects(bird.getRect())) {
				//碰撞后的处理
				bird.life=false;
			}
		}
		return false;
	}
	/*
	 * 用于清空障碍物的池子
	 */
	public void restant() {
		barriers.clear();
	}

}
