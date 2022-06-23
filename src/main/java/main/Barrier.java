package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import util.Constant;
import util.GameUtil;

public class Barrier {
    //矩形参数
    private Rectangle rect;
    private boolean mob = true;
    private int speed = 3;
    private static BufferedImage[] imgs;
    //障碍物的存活状态
    private boolean visible;

    static {
        final int COUNT = 3;
        //类加载的时候将三个图片初始化
        imgs = new BufferedImage[COUNT];
        for (int i = 0; i < COUNT; i++) {
            imgs[i] = GameUtil.loadBufferedImage(Constant.BARRIER_IMG_PATH[i]);
        }
    }

    //位置
    private int x, y;
    //宽度和高度
    private int width, height;
    //障碍物的类型
    private int type;
    //从上到下
    public static final int TYPE_TOP_NORMAL = 0;
    //从下到上
    public static final int TYPE_BOTTOM_NORMAL = 2;
    //悬浮
    public static final int TYPE_HOVER_NORMAL = 4;
    //移动
    public static final int TYPE_MOBLIE = 6;
    //获得障碍物的宽度和高度
    public static final int BARRIER_WIDTH = imgs[0].getWidth();
    public static final int BARRIER_HEIGHT = imgs[0].getHeight();
    public static final int BARRIER_HEAD_WIDTH = imgs[1].getWidth();
    public static final int BARRIER_HEAD_HEIGHT = imgs[1].getHeight();

    //定义构造器
    public Barrier() {
        rect = new Rectangle();
    }

    public Barrier(int x, int y, int height, int type) {
        super();
        this.x = x;
        this.y = y;
        this.height = height;//障碍物总高度
        this.type = type;
        this.width = BARRIER_WIDTH;
    }

    //根据不同的类型绘制障碍物
    public void draw(Graphics g) {
        switch (type) {
            case TYPE_TOP_NORMAL:
                drawTopNormal(g);
                break;
            case TYPE_BOTTOM_NORMAL:
                drawNormalTop(g);
                break;
            case TYPE_HOVER_NORMAL:
                drawHoverNormal(g);
                break;
            case TYPE_MOBLIE:
                drawMoblie(g);
                break;
        }

    }

    //绘制从上向下的障碍物
    private void drawTopNormal(Graphics g) {
        //求出所需要的障碍物的块数
        int count = (height - BARRIER_HEAD_HEIGHT) / BARRIER_HEIGHT + 1;
        //for循环绘制障碍物
        for (int i = 0; i < count; i++) {
            g.drawImage(imgs[0], x, y + i * BARRIER_HEIGHT, null);
        }
        //绘制头
        int y = height - BARRIER_HEAD_HEIGHT;
//		g.drawImage(imgs[2],x-(BARRIER_HEAD_WIDTH-BARRIER_WIDTH)/2,y,null);
        g.drawImage(imgs[2], x, y, null);
        x -= speed;
        if (x < -50) {
            visible = false;
        }
        rect(g);
    }

    //绘制从下向上的障碍物
    private void drawNormalTop(Graphics g) {
        //求出所需要的障碍物的块数
        int count = height / BARRIER_HEIGHT + 1;
        //for循环绘制障碍物
        for (int i = 0; i < count; i++) {
            g.drawImage(imgs[0], x, Constant.frame_height - i * BARRIER_HEIGHT, null);
        }
        //绘制头
        int y = Constant.frame_height - height;
//		g.drawImage(imgs[1],x-(BARRIER_HEAD_WIDTH-BARRIER_WIDTH)/2,y,null);
        g.drawImage(imgs[1], x, y, null);
        x -= speed;
        if (x < -50) {
            visible = false;
        }
        rect(g);
    }

    //绘制移动的障碍物
    private void drawMoblie(Graphics g) {
        //求出所需要的障碍物的块数
        int count = (height - BARRIER_HEAD_HEIGHT) / BARRIER_HEIGHT;
        //绘制上头
        g.drawImage(imgs[1], x, y, null);
        //for循环绘制障碍物
        for (int i = 0; i < count; i++) {
            g.drawImage(imgs[0], x, y + BARRIER_HEAD_HEIGHT + i * BARRIER_HEIGHT, null);
        }
        rect(g);
        //绘制下头
        int y11 = y + height - BARRIER_HEAD_HEIGHT;
        g.drawImage(imgs[2], x, y11, null);
        x -= speed;
        if (x < -50) {
            visible = false;
        }
        if (mob) {
            y += 5;
            if (y >= 250) {
                mob = false;
            }
        } else if (!mob) {
            y -= 5;
            if (y <= 100) {
                mob = true;
            }
        }
    }

    //绘制中间的障碍物
    private void drawHoverNormal(Graphics g) {
        //求出所需要的障碍物的块数
        int count = (height - BARRIER_HEAD_HEIGHT) / BARRIER_HEIGHT;
        //绘制上头
        g.drawImage(imgs[1], x, y, null);
        //for循环绘制障碍物
        for (int i = 0; i < count; i++) {
            g.drawImage(imgs[0], x, y + BARRIER_HEAD_HEIGHT + i * BARRIER_HEIGHT, null);
        }
        rect(g);
        //绘制下头
        int y11 = y + height - BARRIER_HEAD_HEIGHT;
        g.drawImage(imgs[2], x, y11, null);
        x -= speed;
        if (x < -50) {
            visible = false;
        }
    }

    /*
     * 绘制碰撞矩形
     */
    public void rect(Graphics g) {
        int x1 = this.x;
        int y1 = this.y;
        int w1 = imgs[0].getWidth();
//		g.setColor(Color.blue);
//		g.drawRect(x1, y1, w1, height);
        setRectangle(x1, y1, w1, height);
    }

    /*
     * 障碍物的碰撞矩形参数
     */
    public void setRectangle(int x, int y, int width, int height) {
        rect.x = x;
        rect.y = y;
        rect.width = width;
        rect.height = height;
    }

    //判断什么时候绘制下一组障碍物
    public boolean isInFrame() {
        return 600 - x > 150;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public Rectangle getRect() {
        return rect;
    }

}
