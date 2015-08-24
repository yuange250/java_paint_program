/*
 * ��ͼ�������������ѵĲ��֣�Ǧ�ʺ�˵��ֱ�ߺ;��ζ���Ҫ�õ������ߣ�Ҳ�����ڻ�ֱ�߻���ε�ʱ�������δreleased֮ǰ��Ҫ����������ͼ��λ�ò��ϸ��¡�
 * Ϊ�˽���⼸�����⣬���뵽һ���취�������ȴ���һ��BuffuredImage���󣬰�ÿһ��ȷ�������Ĳ�����д�뵽��������У�����paint�����н����BufferredImage��������ϣ�
 * ���������续ֱ��ʱ��ֻҪ����ֱ�߻�û�ж��������Ͳ��ϵ�repaint����������Բ���δ��ֱ�ߵ�֮ǰ�켣�����ұ�֤֮ǰ���еĲ�������������ʾ�������
 * �������������˫���塣
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
	 int mod=1;//���ֲ�����û������Ϊprivateȷʵ�ǲ��õ�ϰ�� mod:1 ��Ǧ�ʣ�2��ֱ�ߣ�3.���Σ�4.��Ƥ
	 int color=2;//color����ϸ˵�ˡ���������
	 int startx=0;
	 int starty=0;
	 int endx=0;
	 int endy=0;
	 int picX=0;//��������Ҫд���ͼƬ�Ĵ�С
	 int picY=0;
	 BufferedImage screen=null;//����BufferedImage
	public Mypanel()
	{
	    this.setPreferredSize(new Dimension(400,400));//��ʼ������
	    this.setBackground(Color.white); 
	    screen=new BufferedImage(400,400,BufferedImage.TYPE_INT_RGB);
	    Graphics gg=screen.getGraphics();
	    gg.setColor(Color.white);//�����Ҫ���Ƚ�BufferedImage�ı�����ɰ�ɫ
	    gg.fillRect(0, 0, 400, 400);
	}
	public void paint(Graphics g)
	{
		super.paint(g);
		g.drawImage(screen, 0, 0, 400, 400, this);//ÿһ��ˢ�¶���BufferedImage��������ϣ����ǰ�֮ǰ���еĲ������������
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
		if(mod==2)//����ǻ�δ��ֱ�ߣ�Ҳ�������ֱ������λ�û�δ������������ǲ�����Image�ﻭ�ģ�ֻ����Ļ����ʾ
		{
			g.drawLine(startx, starty, endx, endy);
		}
		if(mod==3)//����ǻ�δ������
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
    {//���ò����ĺ���
    	this.mod=mod;
    	this.color=color;
    	this.startx=startx;
    	this.starty=starty;
    }
    public void drawpic(String filename)
    {//��ͼƬ�ļ�ʱ�õĺ�����filename�������·��
    	 System.out.print(filename);
    	 File f=new File(filename);
    	 BufferedImage pic;
    	 Graphics gg=screen.getGraphics();
		try {//��Ҫ�����ͼƬд�뵽BufferedImage��
			pic = ImageIO.read(f);
			picX=pic.getWidth();//�ֻ��ͼƬ��С�����������Ļ���ʵ�����
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
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
    }
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO �Զ����ɵķ������
		endx=startx=e.getX();//ÿһ����갴�£��ͽ���ʼλ������Ϊ��ǰ�������
		endy=starty=e.getY();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO �Զ����ɵķ������
		Graphics gg=screen.getGraphics();//�ɿ���֮�󣬾��Σ�ֱ�ߵ�����λ�ñ�ȷ������������ʱ��֮д��BufferedImage
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
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO �Զ����ɵķ������
		
	}
	@Override
	public void mouseDragged(MouseEvent e) {//��ק����
		// TODO �Զ����ɵķ������
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
		{//Ǧ�ʵĻ���ֱ�ӻ�������
			gg.drawLine(startx, starty, e.getX(), e.getY());
			this.repaint();
			this.setnum(this.mod, this.color, e.getX(), e.getY());
		} 
		if(mod==2)
		{//ֱ�ߵĻ�����קֻ���ڻ�δ����ֱ�ߣ�������paint����������
			endx=e.getX();
			endy=e.getY();
			this.repaint();
		}
		if(mod==3)
		{//����ͬ��
			endx=e.getX();
			endy=e.getY();
			this.repaint();
		}
		if(mod==4)
		{//��Ƥ������������õĺܱ�������ֵ�øĽ��ĵط�
			gg.setColor(Color.white);
			gg.fillRect(startx, starty, (e.getX()-startx>0)?e.getX()-startx+4:startx-e.getX()+4, (e.getY()-starty>0)?e.getY()-starty+4:starty-e.getY()+4);
			this.repaint();
			this.setnum(this.mod, this.color, e.getX(), e.getY());
		}
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO �Զ����ɵķ������
	}
	
}
