package Snake;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
//import java.util.Timer;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

//import javafx.scene.text.Font; 

public class SnakePane extends JPanel implements KeyListener,ActionListener{
	ImageIcon up =new ImageIcon("up.png");//实例化对象，加载图片
	ImageIcon down =new ImageIcon("down.png");
	ImageIcon left =new ImageIcon("left.png");
	ImageIcon right =new ImageIcon("right.png");
	ImageIcon Body=new ImageIcon("body.png");
	ImageIcon title=new ImageIcon("title.jpg");
	ImageIcon food =new ImageIcon("food.png");
	
	//蛇的数据结构
	int[] Snake_ix=new int[500];
	int[] Snake_iy=new int[500];
	int length=3;
	String direction="R";
	
	//生成食物
	Random r =new Random();
	int food_ix = r.nextInt(34)*25+25;
	int food_iy = r.nextInt(24)*25+75;
	
	//设置分数
	int score=0;
	
	//设置游戏开始结束
	boolean IsStart =false;
	boolean IsFailed=false;
	
	Timer timer = new Timer(150,this);//定时器
	
	public SnakePane() {
		this.setFocusable(true);//设置Component是否可以获得焦点
		InitSnake();
		this.addKeyListener(this);//添加键盘监听接口
		timer.start();
	}
	
	//初始化蛇
	public void InitSnake() {
		boolean IsStart =false;
		boolean IsFailed=false;
		length=3;
		direction="R";
		Snake_ix[0]=100;
		Snake_iy[0]=100;
		Snake_ix[1]=75;
		Snake_iy[1]=100;
		Snake_ix[2]=50;
		Snake_iy[2]=100;
	}
	
	//画图函数graphics是一个抽象函数，为paint提供画笔，需要引入包
	public void paint(Graphics g) {			
		this.setBackground(Color.BLACK);//设置背景颜色
		g.fillRect(25, 75, 850, 600);//填充一个矩形区域（起始x,起始y,高度，宽度）
		title.paintIcon(this, g, 25, 11);//贴图（对象，画笔，起始x，起始y） 
		//画蛇头
		if(direction.equals("R")) {
			right.paintIcon(this, g, Snake_ix[0], Snake_iy[0]);
		}else if(direction.equals("L")) {
			left.paintIcon(this, g, Snake_ix[0], Snake_iy[0]);
		}else if(direction.equals("U")) {
			up.paintIcon(this, g, Snake_ix[0], Snake_iy[0]);
		}else if(direction.equals("D")) {
			down.paintIcon(this, g, Snake_ix[0], Snake_iy[0]);
		}
		//画蛇身
		for(int i=1;i<length;i++) {
			Body.paintIcon(this, g, Snake_ix[i], Snake_iy[i]);
		}
		//画开始提示语
		if(!IsStart) {
			g.setColor(Color.WHITE);
			g.setFont(new Font("arial",Font.BOLD,30));//设置字体显示效果（字体，风格，字号），这个函数要加包：java.awt.Font
			g.drawString("press space to start/pause", 300, 300);//相对黑色区域的x，y
		}
		if(IsFailed) {
			g.setColor(Color.WHITE);
			g.setFont(new Font("arial",Font.BOLD,30));
			g.drawString("Game Over,if you want try again,press space!", 100, 100);
		}
		food.paintIcon(this, g, food_ix, food_iy);
		
		g.setColor(Color.BLACK);
		g.setFont(new Font("arial",Font.PLAIN,15));
		g.drawString("Score"+score, 750, 30);
		g.drawString("Length"+length, 750, 50);
	}

	@Override
	//通过监听键盘事件来检测按键摁下
	public void keyPressed(KeyEvent e) {
		int KeyCode = e.getKeyCode();
		if(KeyCode == KeyEvent.VK_SPACE) {
			if(IsFailed) {
				InitSnake();
				IsFailed=false;
				IsStart=false;
			}else{
				IsStart = !IsStart;
			}
			
			repaint();//重新画
			//蛇的方向控制
		}else if(KeyCode == KeyEvent.VK_UP && !direction.equals("D")) {
			direction = "U";
		}else if(KeyCode == KeyEvent.VK_DOWN && !direction.equals("U")) {
			direction = "D";
		}else if(KeyCode == KeyEvent.VK_LEFT && !direction.equals("R")) {
			direction = "L";
		}else if(KeyCode == KeyEvent.VK_RIGHT && !direction.equals("L")) {
			direction = "R";
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	/*
	 * 1、重新定一个闹钟
	 * 2、蛇移动
	 * 3、重画
	 * */
	public void actionPerformed(ActionEvent e) {
		timer.start();
		if(IsStart && !IsFailed) {
			//移动身体
			for(int i=length;i>=1;i--) {
				Snake_ix[i]=Snake_ix[i-1];
				Snake_iy[i]=Snake_iy[i-1];
			}
			//蛇头移动
			if(direction.equals("R")) {
				Snake_ix[0]+=25;
				if(Snake_ix[0] > 850) {
					Snake_ix[0]=25;
				}
			}else if(direction.equals("L")) {
				Snake_ix[0]-=25;
				if(Snake_ix[0]<25) {
				Snake_ix[0]=850;
				}
			}else if(direction.equals("U")) {
				Snake_iy[0]-=25;
				if(Snake_iy[0]<75) {
					Snake_iy[0] = 650;
				}
			}else if(direction.equals("D")) {
				Snake_iy[0]+=25;
				if(Snake_iy[0]>650) {
					Snake_iy[0]=75;
				}
			}
			//吃食物
			if(food_ix == Snake_ix[0]&&food_iy==Snake_iy[0]) {
				length++;
				score++;
				food_ix=r.nextInt(34)*25+25;
				food_iy=r.nextInt(24)*25+75;
			}
			for(int i=1;i<length;i++) {
				if(Snake_ix[0] == Snake_ix[i] && Snake_iy[0]==Snake_iy[i]) {
					IsFailed=true;
				}
			}
		
		}
		repaint();
		
	}
	
}