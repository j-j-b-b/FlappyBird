package main;

import java.util.ArrayList;
import java.util.List;

public class Barrierpool {
	//���ڹ���������ж��������
	private static List<Barrier> pool =new ArrayList<>();
	//���г�ʼ�Ķ���ĸ���
	public static final int initCount=16;
	//�������������
	public static final int maxCount=20;
	
	static {
		//��ʼ�����еĶ���
		for(int i=0;i<initCount;i++) {
			pool.add(new Barrier());
		}
	}
	/*
	 * �ӳ��л�ȡһ������
	 */
	public static Barrier getPool() {
		int size=pool.size();
		//����ж���ſ��Դӳ�����������
		if(size>0) {
			//�Ƴ������ض���
			return pool.remove(size-1);
		}else {
			//�����޶���
			return new Barrier();
		}
	}
	/*
	 * ������黹������
	 */
	public static void setPool(Barrier barrier) {
		if(pool.size()<maxCount) {
			pool.add(barrier);
		}
	}

}
