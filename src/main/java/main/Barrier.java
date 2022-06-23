package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import util.Constant;
import util.GameUtil;

public class Barrier {
    //���β���
    private Rectangle rect;
    private boolean mob = true;
    private int speed = 3;
    private static BufferedImage[] imgs;
    //�ϰ���Ĵ��״̬
    private boolean visible;

    static {
        final int COUNT = 3;
        //����ص�ʱ������ͼƬ��ʼ��
        imgs = new BufferedImage[COUNT];
        for (int i = 0; i < COUNT; i++) {
            imgs[i] = GameUtil.loadBufferedImage(Constant.BARRIER_IMG_PATH[i]);
        }
    }

    //λ��
    private int x, y;
    //��Ⱥ͸߶�
    private int width, height;
    //�ϰ��������
    private int type;
    //���ϵ���
    public static final int TYPE_TOP_NORMAL = 0;
    //���µ���
    public static final int TYPE_BOTTOM_NORMAL = 2;
    //����
    public static final int TYPE_HOVER_NORMAL = 4;
    //�ƶ�
    public static final int TYPE_MOBLIE = 6;
    //����ϰ���Ŀ�Ⱥ͸߶�
    public static final int BARRIER_WIDTH = imgs[0].getWidth();
    public static final int BARRIER_HEIGHT = imgs[0].getHeight();
    public static final int BARRIER_HEAD_WIDTH = imgs[1].getWidth();
    public static final int BARRIER_HEAD_HEIGHT = imgs[1].getHeight();

    //���幹����
    public Barrier() {
        rect = new Rectangle();
    }

    public Barrier(int x, int y, int height, int type) {
        super();
        this.x = x;
        this.y = y;
        this.height = height;//�ϰ����ܸ߶�
        this.type = type;
        this.width = BARRIER_WIDTH;
    }

    //���ݲ�ͬ�����ͻ����ϰ���
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

    //���ƴ������µ��ϰ���
    private void drawTopNormal(Graphics g) {
        //�������Ҫ���ϰ���Ŀ���
        int count = (height - BARRIER_HEAD_HEIGHT) / BARRIER_HEIGHT + 1;
        //forѭ�������ϰ���
        for (int i = 0; i < count; i++) {
            g.drawImage(imgs[0], x, y + i * BARRIER_HEIGHT, null);
        }
        //����ͷ
        int y = height - BARRIER_HEAD_HEIGHT;
//		g.drawImage(imgs[2],x-(BARRIER_HEAD_WIDTH-BARRIER_WIDTH)/2,y,null);
        g.drawImage(imgs[2], x, y, null);
        x -= speed;
        if (x < -50) {
            visible = false;
        }
        rect(g);
    }

    //���ƴ������ϵ��ϰ���
    private void drawNormalTop(Graphics g) {
        //�������Ҫ���ϰ���Ŀ���
        int count = height / BARRIER_HEIGHT + 1;
        //forѭ�������ϰ���
        for (int i = 0; i < count; i++) {
            g.drawImage(imgs[0], x, Constant.frame_height - i * BARRIER_HEIGHT, null);
        }
        //����ͷ
        int y = Constant.frame_height - height;
//		g.drawImage(imgs[1],x-(BARRIER_HEAD_WIDTH-BARRIER_WIDTH)/2,y,null);
        g.drawImage(imgs[1], x, y, null);
        x -= speed;
        if (x < -50) {
            visible = false;
        }
        rect(g);
    }

    //�����ƶ����ϰ���
    private void drawMoblie(Graphics g) {
        //�������Ҫ���ϰ���Ŀ���
        int count = (height - BARRIER_HEAD_HEIGHT) / BARRIER_HEIGHT;
        //������ͷ
        g.drawImage(imgs[1], x, y, null);
        //forѭ�������ϰ���
        for (int i = 0; i < count; i++) {
            g.drawImage(imgs[0], x, y + BARRIER_HEAD_HEIGHT + i * BARRIER_HEIGHT, null);
        }
        rect(g);
        //������ͷ
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

    //�����м���ϰ���
    private void drawHoverNormal(Graphics g) {
        //�������Ҫ���ϰ���Ŀ���
        int count = (height - BARRIER_HEAD_HEIGHT) / BARRIER_HEIGHT;
        //������ͷ
        g.drawImage(imgs[1], x, y, null);
        //forѭ�������ϰ���
        for (int i = 0; i < count; i++) {
            g.drawImage(imgs[0], x, y + BARRIER_HEAD_HEIGHT + i * BARRIER_HEIGHT, null);
        }
        rect(g);
        //������ͷ
        int y11 = y + height - BARRIER_HEAD_HEIGHT;
        g.drawImage(imgs[2], x, y11, null);
        x -= speed;
        if (x < -50) {
            visible = false;
        }
    }

    /*
     * ������ײ����
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
     * �ϰ������ײ���β���
     */
    public void setRectangle(int x, int y, int width, int height) {
        rect.x = x;
        rect.y = y;
        rect.width = width;
        rect.height = height;
    }

    //�ж�ʲôʱ�������һ���ϰ���
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
