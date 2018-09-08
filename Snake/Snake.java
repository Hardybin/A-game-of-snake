package Snake;

import java.awt.Color;

import javax.swing.JFrame;//创建一个窗体

public class Snake {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame =new JFrame();//实例化一个JFrame类的对象
		frame.setBounds(400,200,900,720);//设置窗体的大小（起始x，起始y，宽度，高度）
		frame.setResizable(false);//是否可以调整窗体大小resizable
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//窗体关闭方式，退出窗口同时退出程序
		
		SnakePane pane =new SnakePane();//实例化一个对象
		pane.setBackground(Color.red);
		frame.add(pane);//将画布添加到窗体中
		
		frame.setVisible(true);//使窗体显示出来
	}

}
