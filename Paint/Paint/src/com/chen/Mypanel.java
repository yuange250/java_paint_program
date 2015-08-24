/*
 * 画图部分无疑是最难的部分，铅笔好说，直线和矩形都需要用到引导线，也就是在画直线或炬形的时候，在鼠标未released之前需要将画出来的图随位置不断更新。
 * 为了解决这几个问题，我想到一个办法，就是先创建一个BuffuredImage对象，把每一个确定下来的操作都写入到这个对象中，并在paint函数中将这个BufferredImage画到面板上，
 * 这样，比如画直线时，只要这条直线还没有定下来，就不断地repaint，这样便可以擦除未定直线的之前轨迹，并且保证之前所有的操作都完整的显示在面板上
 * 这个方法类似于双缓冲。
 */
package com.chen;
import java.awt.*;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
public class Mypanel extends JPanel implements MouseListener,MouseMotionListener{
	 int mod=1;//各种参量，没有设置为private确实是不好的习惯 mod:1 。铅笔，2。直线，3.矩形，4.橡皮
	 int color=2;//color：不细说了。。。。。
	 int startx=0;
	 int starty=0;
	 int endx=0;
	 int endy=0;
	 int picX=0;//这两个是要写入的图片的大小
	 int picY=0;
	 BufferedImage screen=null;//定义BufferedImage
	public Mypanel()
	{
	    this.setPreferredSize(new Dimension(400,400));//初始化构建
	    this.setBackground(Color.white); 
	    screen=new BufferedImage(400,400,BufferedImage.TYPE_INT_RGB);
	    Graphics gg=screen.getGraphics();
	    gg.setColor(Color.white);//这儿主要是先将BufferedImage的背景变成白色
	    gg.fillRect(0, 0, 400, 400);
	}
	public void paint(Graphics g)
	{
		super.paint(g);
		g.drawImage(screen, 0, 0, 400, 400, this);//每一次刷新都将BufferedImage画到面板上，就是把之前所有的操作画到面板上
		switch(color){
		case 1:g.setColor(Color.white);
		       break;
		case 2:g.setColor(Color.black);
	           break;
		case 3:g.setColor(Color.red);
	           break;
		case 4:g.setColor(Color.green);
	           break;
		case 5:g.setColor(Color.blue);
	           break;
		case 6:g.setColor(Color.yellow);
	           break;
		}
		if(mod==2)//这个是画未定直线，也就是这个直线最终位置还未定下来，这个是不会往Image里画的，只在屏幕上显示
		{
			g.drawLine(startx, starty, endx, endy);
		}
		if(mod==3)//这个是画未定矩形
		{
			if(endx>startx&&endy>starty)
			g.drawRect(startx, starty, endx-startx, endy-starty);
			else if(endx<startx&&endy>starty)
		    g.drawRect(endx, starty, startx-endx, endy-starty);
			else if(endx>startx&&endy<starty)
			g.drawRect(startx, endy, endx-startx, starty-endy);
			else if(endx<startx&&endy<starty)
			g.drawRect(endx, endy, startx-endx, starty-endy);
			this.repaint();
		}
	}
    public void setnum(int mod,int color,int startx,int starty)
    {//设置参量的函数
    	this.mod=mod;
    	this.color=color;
    	this.startx=startx;
    	this.starty=starty;
    }
    public void drawpic(String filename)
    {//打开图片文件时用的函数，filename传入绝对路径
    	 System.out.print(filename);
    	 File f=new File(filename);
    	 BufferedImage pic;
    	 Graphics gg=screen.getGraphics();
		try {//主要将这个图片写入到BufferedImage中
			pic = ImageIO.read(f);
			picX=pic.getWidth();//现获得图片大小，假如大于屏幕，适当缩放
			picY=pic.getHeight();
			if(picX<400&&picY<400)
			{
				gg.drawImage(pic,0,0,picX,picY,null);
				this.repaint();
			}
			else if(picX>400&&picY<400)
			{
			    gg.drawImage(pic,0,0,400,400*picY/picX,null);
			    this.repaint();
			}
			else if(picX<450&&picY>400)
			{
			    gg.drawImage(pic,0,0,400*picX/picY,400,null);
			    this.repaint();
			}
			else
			{
				if(picX>picY)
				{
					gg.drawImage(pic,0,0,400,picY*400/picX,null);
					this.repaint();
				}
				else
				{
					gg.drawImage(pic,0,0,picX*400/picY,400,null);
					this.repaint();
				}
			}
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
    }
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO 自动生成的方法存根
		endx=startx=e.getX();//每一次鼠标按下，就将初始位置设置为当前鼠标坐标
		endy=starty=e.getY();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO 自动生成的方法存根
		Graphics gg=screen.getGraphics();//松开了之后，矩形，直线的最终位置便确定了下来，此时将之写入BufferedImage
		switch(color){
		case 1:gg.setColor(Color.white);
		       break;
		case 2:gg.setColor(Color.black);
	           break;
		case 3:gg.setColor(Color.red);
	           break;
		case 4:gg.setColor(Color.green);
	           break;
		case 5:gg.setColor(Color.blue);
	           break;
		case 6:gg.setColor(Color.yellow);
	           break;
		}
		if(mod==2)
		{
			gg.drawLine(startx, starty, e.getX(), e.getY());
			this.repaint();
		}
		if(mod==3)
		{
			if(e.getX()>startx&&e.getY()>starty)
			gg.drawRect(startx, starty, e.getX()-startx, e.getY()-starty);
			else if(e.getX()<startx&&e.getY()>starty)
		    gg.drawRect(e.getX(), starty, startx-e.getX(), e.getY()-starty);
			else if(e.getX()>startx&&e.getY()<starty)
			gg.drawRect(startx, e.getY(), e.getX()-startx, starty-e.getY());
			else if(e.getX()<startx&&e.getY()<starty)
			gg.drawRect(e.getX(), e.getY(), startx-e.getX(), starty-e.getY());
			this.repaint();
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	}
	@Override
	public void mouseDragged(MouseEvent e) {//拖拽过程
		// TODO 自动生成的方法存根
		 Graphics gg=screen.getGraphics();
		switch(color){
		case 1:gg.setColor(Color.white);
		       break;
		case 2:gg.setColor(Color.black);
	           break;
		case 3:gg.setColor(Color.red);
	           break;
		case 4:gg.setColor(Color.green);
	           break;
		case 5:gg.setColor(Color.blue);
	           break;
		case 6:gg.setColor(Color.yellow);
	           break;
		}
		if(mod==1)
		{//铅笔的话就直接画就行了
			gg.drawLine(startx, starty, e.getX(), e.getY());
			this.repaint();
			this.setnum(this.mod, this.color, e.getX(), e.getY());
		} 
		if(mod==2)
		{//直线的话，拖拽只是在画未定的直线，过程在paint函数中已有
			endx=e.getX();
			endy=e.getY();
			this.repaint();
		}
		if(mod==3)
		{//矩形同上
			endx=e.getX();
			endy=e.getY();
			this.repaint();
		}
		if(mod==4)
		{//橡皮的这个方法我用的很笨，是最值得改进的地方
			gg.setColor(Color.white);
			gg.fillRect(startx, starty, (e.getX()-startx>0)?e.getX()-startx+4:startx-e.getX()+4, (e.getY()-starty>0)?e.getY()-starty+4:starty-e.getY()+4);
			this.repaint();
			this.setnum(this.mod, this.color, e.getX(), e.getY());
		}
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO 自动生成的方法存根
	}
	
}
