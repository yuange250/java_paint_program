package com.chen;
import java.awt.*;

import javax.swing.*;
public class ColorNow extends JPanel{
   public ColorNow(){//初始化的构造函数，无非就是设置大小，初始化背景颜色
	   this.setSize(300, 100);
	   this.setPreferredSize(new Dimension(100,100));
	   this.setBackground(Color.black);
   }
}
