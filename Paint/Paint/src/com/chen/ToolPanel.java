package com.chen;
import java.awt.*;

import javax.swing.*;
public class ToolPanel extends JPanel{
	JButton pencil=null;//�������ֹ���
	JButton line=null;
	JButton rec=null;
	JButton eraser=null;
	public ToolPanel()
	{
		this.setPreferredSize(new Dimension(50,400));//���ô�С
		this.setLayout(new GridLayout(8,1,5,5));//��Ϊ���񲼾�
		pencil=new JButton();
	    pencil.setIcon(new ImageIcon(getClass().getResource("/image/10463478_185802588000_2.jpg")));//���ð�ť����
	    pencil.setPreferredSize(new Dimension(50,50));
	    pencil.setActionCommand("pencil");
	    this.add(pencil);
	    line=new JButton();
	    line.setIcon(new ImageIcon(getClass().getResource("/image/017.jpg")));
	    line.setPreferredSize(new Dimension(50,50));
	    line.setActionCommand("line");
	    this.add(line);
	    rec=new JButton();
	    rec.setIcon(new ImageIcon(getClass().getResource("/image/2.jpg")));
	    rec.setPreferredSize(new Dimension(50,50));
	    rec.setActionCommand("rec");
	    this.add(rec);
	    eraser=new JButton();
	    eraser.setPreferredSize(new Dimension(50,50));
	    eraser.setIcon(new ImageIcon(getClass().getResource("/image/45670000000.jpg")));
	    this.add(eraser);
	    eraser.setActionCommand("eraser");
	}
}
