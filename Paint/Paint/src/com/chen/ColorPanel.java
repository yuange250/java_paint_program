package com.chen;
import java.awt.*;

import javax.swing.*;
public class ColorPanel extends JPanel{
	JButton white=null;//各种按钮
	JButton black=null;
	JButton red=null;
	JButton green=null;
	JButton blue=null;
	JButton yellow=null;
	public ColorPanel(){
		    this.setLayout(new GridLayout(2,3,5,5));//设置网格布局
		    this.setPreferredSize(new Dimension(150,100));//将各个按钮创建并添加进去
		    white=new JButton();
		    white.setBackground(Color.white);
		    white.setPreferredSize(new Dimension(40,40));
		    white.setActionCommand("white");
		    this.add(white);
		    black=new JButton();
		    black.setBackground(Color.black);
		    black.setPreferredSize(new Dimension(40,40));
		    black.setActionCommand("black");
		    this.add(black);
		    red=new JButton();
		    red.setBackground(Color.red);
		    red.setPreferredSize(new Dimension(40,40));
		    red.setActionCommand("red");
		    this.add(red);
		    green=new JButton();
		    green.setBackground(Color.green);
		    green.setPreferredSize(new Dimension(40,40));
		    green.setActionCommand("green");
		    this.add(green);
		    blue=new JButton();
		    blue.setBackground(Color.blue);
		    blue.setPreferredSize(new Dimension(40,40));
		    blue.setActionCommand("blue");
		    this.add(blue);
		    yellow=new JButton();
		    yellow.setBackground(Color.yellow);
		    yellow.setPreferredSize(new Dimension(40,40));
		    yellow.setActionCommand("yellow");
		    this.add(yellow);
	}
}
