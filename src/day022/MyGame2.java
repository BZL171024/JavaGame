package day022;

import java.awt.*;
import javax.swing.*;

/*
  * 我的第一个小游戏
  * 桌球
 */

public class MyGame2 extends JFrame{

	Image ball = Toolkit.getDefaultToolkit().getImage("images/ball.png");
	Image desk = Toolkit.getDefaultToolkit().getImage("images/desk.jpg");
	
	//小球的坐标
	double x = 100;
	double y = 100;
	
	double degree = 3.14/3;		//弧度，此时就是60度
	
	//画窗口的方法
	public void paint(Graphics g) {
		System.out.println("窗口被划了一次！");
		//先画桌面，再画小球
		g.drawImage(desk,0,0,null);
		g.drawImage(ball,(int)x,(int)y,null);
		
		x += 10*Math.cos(degree);
		y += 10*Math.sin(degree);
		
		//500是窗口高度；40是桌子边框，30是球直径；最后一个40是标题栏的高度
		//碰到上下边界
		if((y > 500 - 40 - 30) || (y < 40 + 20)) {
			degree = -degree;
		}
		//碰到左右边界
		if((x < 40) || (x > 856 - 40 - 30)){
			//180度减去相应角度
			degree = 3.14-degree;
		}
	}
	
	//窗口加载
	public void launchFrame() {
		setSize(856, 500);
		setLocation(50, 50);
		setVisible(true);
		
		//重画窗口
		while(true) {
			repaint();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		MyGame2 mg = new MyGame2();
		mg.launchFrame();
	}
}
