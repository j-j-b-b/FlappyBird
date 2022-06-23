package util;

import java.awt.Color;

public class Constant {
	//窗口大小
	public static final int frame_width=600;
	public static final int frame_height=500;
	//窗口标题
	public static final String frame_title="FlappyBird";
	//窗口的初始化位置
	public static final int frame_x=200;
	public static final int frame_y=200;
	//图片路径
	public static final String BG_IMG_PATH="img/bird_bk.png";
	//游戏背景颜色
	public static final Color BG_COLOR=new Color(0x4B4CF);
	//小鸟的图片资源
	public static final String[] BIRD_IMG= {"img/bird_normal.png","img/bird_up.png","img/bird_down.png"};
	//障碍物的图片资源
	public static final String[] BARRIER_IMG_PATH= {"img/barrier.png","img/barrier_up.png","img/barrier_down.png"};

}
