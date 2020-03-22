package day022;

import java.awt.*;
import javax.swing.*;

/*
  * 我的第一个小游戏
  * 桌球
 */

public class MyGame extends JFrame{

	Image ball = Toolkit.getDefaultToolkit().getImage("images/ball.png");
	Image desk = Toolkit.getDefaultToolkit().getImage("images/desk.jpg");
	
	//小球的坐标
	double x = 100;
	double y = 100;
	//方向
	boolean right = true;
	
	//画窗口的方法
	public void paint(Graphics g) {
		System.out.println("窗口被划了一次！");
		//先画桌面，再画小球
		g.drawImage(desk,0,0,null);
		g.drawImage(ball,(int)x,(int)y,null);
		
		if(right) {
			//向右
			x += 10;
		}else {
			//向左
			x -= 10;
		}
		
		//856是窗口的宽度，40是桌边框的宽度，30是小球的直径
		if(x > 856-40-30) {
			//改变方向
			right = false;
		}
		if(x < 40) {
			//改变方向
			right = true;
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
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		MyGame mg = new MyGame();
		mg.launchFrame();
	}
}
