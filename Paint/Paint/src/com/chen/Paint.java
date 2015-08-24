/*
 * chen 
 * painting
 * pencil line rec eraser
 * 保存的时候需要加上后缀jpg哦，懒得做了
 * 有以下几个缺点：
 * 1.重复打开图片会覆盖
 * 2.各种操作的鼠标形状没有自定义
 * 3.屎一样的橡皮工具
 * 4.未保存退出无提醒（懒得做了）
 * and so on
 * 
 */
package com.chen;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
public  class Paint extends JFrame implements ActionListener{//主类
	  JMenu jm=null;//菜单栏
	  JMenuBar jmb=null;
	  JMenuItem jmi1=null;
	  JMenuItem jmi2=null;
	  JMenuItem jmi3=null;
	  Mypanel mp=null;//mypanel画图区
	  ToolPanel tp=null;//工具区
	  ColorPanel cp=null;//颜色区
	  ColorNow cn=null;//当前颜色区，专门做了一个panel
	  String filename=null;//存放打开文件的路径
      public static void main(String []args)
      {//主程序
    	  Paint p=new Paint();
      }
     public Paint()
     { 
    	 //页面布局，主框架构造函数
    	jmb=new JMenuBar();//文件菜单栏，各种创建与设置监听
    	jm=new JMenu("文件");
    	jmi1=new JMenuItem("打开");
    	jmi1.setActionCommand("open");
    	jmi1.addActionListener(this);
    	jmi2=new JMenuItem("保存");
    	jmi2.setActionCommand("save");
    	jmi2.addActionListener(this);
    	jmi3=new JMenuItem("另存为");
    	jmi3.setActionCommand("store");
    	jmi3.addActionListener(this);
    	jm.add(jmi1);
    	jm.add(jmi2);
    	jm.add(jmi3);
    	jmb.add(jm);
    	this.setLayout(new FlowLayout(FlowLayout.CENTER));//主框架设置为流式布局，居中对齐
    	tp=new ToolPanel();//构造工具栏
    	tp.eraser.addActionListener(this);//每个按钮注册监听
    	tp.eraser.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));//鼠标移动到按钮上时变换为手型
    	tp.line.addActionListener(this);
    	tp.line.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    	tp.pencil.addActionListener(this);
    	tp.pencil.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    	tp.rec.addActionListener(this);
    	tp.rec.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    	this.add(tp);//将工具栏添加到Frame中
    	mp=new Mypanel();//创建画板
    	mp.addMouseListener(mp);//注册监听，鼠标移动监听，自己监听自己
    	mp.addMouseMotionListener(mp);
    	mp.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));//初始状态是铅笔，懒得自定义鼠标了，就用十字架
    	this.add(mp);//添加到总框架中
    	cn=new ColorNow();//当前颜色栏，确实有点鸡肋，值得改进
    	this.add(cn);
    	cp=new ColorPanel();//颜色栏
    	cp.black.addActionListener(this);
    	cp.black.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    	cp.blue.addActionListener(this);
    	cp.blue.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    	cp.green.addActionListener(this);
    	cp.green.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    	cp.red.addActionListener(this);
    	cp.red.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    	cp.white.addActionListener(this);
    	cp.white.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    	cp.yellow.addActionListener(this);
    	cp.yellow.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    	this.add(cp);//添加到Frame中
    	this.setJMenuBar(jmb);
    	this.setSize(480,570);
    	this.setVisible(true);
    	this.setResizable(false);//由于用了流式布局，而且各组件没有设置大小变化的属性，所以设置总框架无法改变大小，值得改进
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     }
	 @Override
 	 public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		if(e.getActionCommand().equals("open"))
		{//打开一个文件
			JFileChooser jfc=new JFileChooser();//创建一个选择文件框
			jfc.setDialogTitle("请选择图片");
			jfc.showOpenDialog(null);
			jfc.setVisible(true);
			filename=jfc.getSelectedFile().getAbsolutePath();//得到当前文件路径
            mp.drawpic(filename);//mp中封装了一个打开当前路径图片的方法
		}
		if(e.getActionCommand().equals("save"))
		{//保存一个文件
			if(filename==null)
			{//假如这个文件未创建，这是保存==另存为
				JFileChooser jfc=new JFileChooser();
				jfc.setDialogTitle("文件");
				jfc.showSaveDialog(null);
				jfc.setVisible(true);
				String file=jfc.getSelectedFile().getAbsolutePath();
				
				File f=new File(file);
				try {
					ImageIO.write(mp.screen, "JPG", f);//注：mp.screen是mp中的一个BufferedImage类
				} catch (IOException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
			}
			else{//此时是在当前图片的基础上进行修改，所以直接保存到当前路径
				File f=new File(filename);
				try {
					ImageIO.write(mp.screen, "JPG", f);
				} catch (IOException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
			}
		}
		if(e.getActionCommand().equals("store"))
		{//另存为，也是先要创建一个选择保存文件对象
			JFileChooser jfc=new JFileChooser();
			jfc.setDialogTitle("文件");
			jfc.showSaveDialog(null);
			jfc.setVisible(true);
			String file=jfc.getSelectedFile().getAbsolutePath();//得到路径
			
			File f=new File(file);
			try {
				ImageIO.write(mp.screen, "JPG", f);
			} catch (IOException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
			
		}//下面便是各种按钮的触发
		if(e.getActionCommand().equals("pencil"))
		{
			mp.setnum(1, mp.color, mp.startx, mp.starty);//setnum函数是修改mp中各种参量的函数
			mp.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		}
		if(e.getActionCommand().equals("line"))
		{
			mp.setnum(2, mp.color, mp.startx, mp.starty);
			mp.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		}
		if(e.getActionCommand().equals("rec"))
		{
			mp.setnum(3, mp.color, mp.startx, mp.starty);
			mp.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		}
		if(e.getActionCommand().equals("eraser"))
		{
			mp.setnum(4, mp.color, mp.startx, mp.starty);
			mp.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}
		if(e.getActionCommand().equals("white"))
		{
			mp.setnum(mp.mod, 1, mp.startx, mp.starty);
			cn.setBackground(Color.white);//此时改变颜色要把当前颜色的那个panel的背景改变
		}
		if(e.getActionCommand().equals("black"))
		{
			mp.setnum(mp.mod, 2, mp.startx, mp.starty);
			cn.setBackground(Color.black);
		}
		if(e.getActionCommand().equals("red"))
		{
			mp.setnum(mp.mod, 3, mp.startx, mp.starty);
			cn.setBackground(Color.red);
		}
		if(e.getActionCommand().equals("green"))
		{
			mp.setnum(mp.mod, 4, mp.startx, mp.starty);
			cn.setBackground(Color.green);
		}
		if(e.getActionCommand().equals("blue"))
		{
			mp.setnum(mp.mod, 5, mp.startx, mp.starty);
			cn.setBackground(Color.blue);
		}
		if(e.getActionCommand().equals("yellow"))
		{
			mp.setnum(mp.mod, 6, mp.startx, mp.starty);
			cn.setBackground(Color.yellow);
		}
	 }
}
