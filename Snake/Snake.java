package Snake;

import java.awt.Color;

import javax.swing.JFrame;//����һ������

public class Snake {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame =new JFrame();//ʵ����һ��JFrame��Ķ���
		frame.setBounds(400,200,900,720);//���ô���Ĵ�С����ʼx����ʼy����ȣ��߶ȣ�
		frame.setResizable(false);//�Ƿ���Ե��������Сresizable
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//����رշ�ʽ���˳�����ͬʱ�˳�����
		
		SnakePane pane =new SnakePane();//ʵ����һ������
		pane.setBackground(Color.red);
		frame.add(pane);//��������ӵ�������
		
		frame.setVisible(true);//ʹ������ʾ����
	}

}
