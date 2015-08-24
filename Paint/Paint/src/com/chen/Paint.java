/*
 * chen 
 * painting
 * pencil line rec eraser
 * �����ʱ����Ҫ���Ϻ�׺jpgŶ����������
 * �����¼���ȱ�㣺
 * 1.�ظ���ͼƬ�Ḳ��
 * 2.���ֲ����������״û���Զ���
 * 3.ʺһ������Ƥ����
 * 4.δ�����˳������ѣ��������ˣ�
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
public  class Paint extends JFrame implements ActionListener{//����
	  JMenu jm=null;//�˵���
	  JMenuBar jmb=null;
	  JMenuItem jmi1=null;
	  JMenuItem jmi2=null;
	  JMenuItem jmi3=null;
	  Mypanel mp=null;//mypanel��ͼ��
	  ToolPanel tp=null;//������
	  ColorPanel cp=null;//��ɫ��
	  ColorNow cn=null;//��ǰ��ɫ����ר������һ��panel
	  String filename=null;//��Ŵ��ļ���·��
      public static void main(String []args)
      {//������
    	  Paint p=new Paint();
      }
     public Paint()
     { 
    	 //ҳ�沼�֣�����ܹ��캯��
    	jmb=new JMenuBar();//�ļ��˵��������ִ��������ü���
    	jm=new JMenu("�ļ�");
    	jmi1=new JMenuItem("��");
    	jmi1.setActionCommand("open");
    	jmi1.addActionListener(this);
    	jmi2=new JMenuItem("����");
    	jmi2.setActionCommand("save");
    	jmi2.addActionListener(this);
    	jmi3=new JMenuItem("���Ϊ");
    	jmi3.setActionCommand("store");
    	jmi3.addActionListener(this);
    	jm.add(jmi1);
    	jm.add(jmi2);
    	jm.add(jmi3);
    	jmb.add(jm);
    	this.setLayout(new FlowLayout(FlowLayout.CENTER));//���������Ϊ��ʽ���֣����ж���
    	tp=new ToolPanel();//���칤����
    	tp.eraser.addActionListener(this);//ÿ����ťע�����
    	tp.eraser.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));//����ƶ�����ť��ʱ�任Ϊ����
    	tp.line.addActionListener(this);
    	tp.line.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    	tp.pencil.addActionListener(this);
    	tp.pencil.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    	tp.rec.addActionListener(this);
    	tp.rec.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    	this.add(tp);//����������ӵ�Frame��
    	mp=new Mypanel();//��������
    	mp.addMouseListener(mp);//ע�����������ƶ��������Լ������Լ�
    	mp.addMouseMotionListener(mp);
    	mp.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));//��ʼ״̬��Ǧ�ʣ������Զ�������ˣ�����ʮ�ּ�
    	this.add(mp);//��ӵ��ܿ����
    	cn=new ColorNow();//��ǰ��ɫ����ȷʵ�е㼦�ߣ�ֵ�øĽ�
    	this.add(cn);
    	cp=new ColorPanel();//��ɫ��
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
    	this.add(cp);//��ӵ�Frame��
    	this.setJMenuBar(jmb);
    	this.setSize(480,570);
    	this.setVisible(true);
    	this.setResizable(false);//����������ʽ���֣����Ҹ����û�����ô�С�仯�����ԣ����������ܿ���޷��ı��С��ֵ�øĽ�
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     }
	 @Override
 	 public void actionPerformed(ActionEvent e) {
		// TODO �Զ����ɵķ������
		if(e.getActionCommand().equals("open"))
		{//��һ���ļ�
			JFileChooser jfc=new JFileChooser();//����һ��ѡ���ļ���
			jfc.setDialogTitle("��ѡ��ͼƬ");
			jfc.showOpenDialog(null);
			jfc.setVisible(true);
			filename=jfc.getSelectedFile().getAbsolutePath();//�õ���ǰ�ļ�·��
            mp.drawpic(filename);//mp�з�װ��һ���򿪵�ǰ·��ͼƬ�ķ���
		}
		if(e.getActionCommand().equals("save"))
		{//����һ���ļ�
			if(filename==null)
			{//��������ļ�δ���������Ǳ���==���Ϊ
				JFileChooser jfc=new JFileChooser();
				jfc.setDialogTitle("�ļ�");
				jfc.showSaveDialog(null);
				jfc.setVisible(true);
				String file=jfc.getSelectedFile().getAbsolutePath();
				
				File f=new File(file);
				try {
					ImageIO.write(mp.screen, "JPG", f);//ע��mp.screen��mp�е�һ��BufferedImage��
				} catch (IOException e1) {
					// TODO �Զ����ɵ� catch ��
					e1.printStackTrace();
				}
			}
			else{//��ʱ���ڵ�ǰͼƬ�Ļ����Ͻ����޸ģ�����ֱ�ӱ��浽��ǰ·��
				File f=new File(filename);
				try {
					ImageIO.write(mp.screen, "JPG", f);
				} catch (IOException e1) {
					// TODO �Զ����ɵ� catch ��
					e1.printStackTrace();
				}
			}
		}
		if(e.getActionCommand().equals("store"))
		{//���Ϊ��Ҳ����Ҫ����һ��ѡ�񱣴��ļ�����
			JFileChooser jfc=new JFileChooser();
			jfc.setDialogTitle("�ļ�");
			jfc.showSaveDialog(null);
			jfc.setVisible(true);
			String file=jfc.getSelectedFile().getAbsolutePath();//�õ�·��
			
			File f=new File(file);
			try {
				ImageIO.write(mp.screen, "JPG", f);
			} catch (IOException e1) {
				// TODO �Զ����ɵ� catch ��
				e1.printStackTrace();
			}
			
		}//������Ǹ��ְ�ť�Ĵ���
		if(e.getActionCommand().equals("pencil"))
		{
			mp.setnum(1, mp.color, mp.startx, mp.starty);//setnum�������޸�mp�и��ֲ����ĺ���
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
			cn.setBackground(Color.white);//��ʱ�ı���ɫҪ�ѵ�ǰ��ɫ���Ǹ�panel�ı����ı�
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
