import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class HitMouse extends JFrame implements ActionListener{
private Container contentPane;
private JButton[] btns;
private JPanel p1;
private JPanel p2;
private JLabel lb1;
private JLabel lb2;
private JButton b1;
private ImageIcon image;
private ImageIcon baozha;
JComboBox comb;
//定义计时器,在一定时间间隔可以激发事件，并且其实现监听实现ActionListener
private Timer timer,postion;//倒计时和老鼠跳动的位置频率
//定义老鼠位置的索引和跳动的时间间隔
private int index;
private int time;
//定义标记位,表示老鼠是否被打中
private Boolean flag;

public HitMouse(){
this.setTitle("mouse game");
this.setBounds(300, 300, 400, 400);
this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
contentPane=this.getContentPane();
btns=new JButton[9];
p1=new JPanel();
p2=new JPanel();
//使用相对路径获得图片
//image=new ImageIcon("src/test/picture/mouse.jpg");
//使用绝对路径获得图片
image=new ImageIcon("d:/mouse.jpg");
baozha=new ImageIcon("d:/123.jpg");
/*
* 1000表示计时器1000毫秒激发一次
* this表示实现了actionlistener的实例
*/
timer=new Timer(1000,this);
time=700;
postion=new Timer(time,this);
initGui();
}
public void initGui(){
contentPane.setLayout(new BorderLayout());
p1.setLayout(new FlowLayout());
comb=new JComboBox(new Object[]{"初级","中级","高级","测试"});
comb.addItemListener(new ItemListener(){
public void itemStateChanged(ItemEvent e) {
if("初级".equals(e.getItem())){
time=1000;
}else if("中级".equals(e.getItem())){
time=700;
}else if("高级".equals(e.getItem())){
time=300;
}else if("测试".equals(e.getItem())){
time=3000;
}
postion=new Timer(time,HitMouse.this);
}

});
lb1=new JLabel("10");
lb2=new JLabel("0");
b1=new JButton("start");
b1.addActionListener(this);
p1.add(comb);
p1.add(new JLabel("倒计时:"));
p1.add(lb1);
p1.add(new JLabel("得分:"));
p1.add(lb2);
p1.add(b1);
contentPane.add(p1,BorderLayout.NORTH);
p2.setLayout(new GridLayout(3,3));
for(int i=0;i<9;i++){
btns[i]=new JButton();
btns[i].setEnabled(false);
btns[i].addActionListener(this);
p2.add(btns[i]);
}
//btns[0].setIcon(image);
contentPane.add(p2,BorderLayout.CENTER);
}
public void go(){
this.setVisible(true);
}

public static void main(String[] args) {
new HitMouse().go();
}
@Override
public void actionPerformed(ActionEvent e) {
Object o=e.getSource();
if(o==timer){
int n=Integer.parseInt(lb1.getText().trim());
if(n==0){
comb.setEnabled(true);
b1.setEnabled(true);
for(int i=0;i<9;i++){
btns[i].setEnabled(false);
}
timer.stop();
postion.stop();

}else{
n--;
lb1.setText(""+n);
}
}
if(o==postion){
for(int i=0;i<9;i++){
btns[i].setIcon(null);
}
index=(int)(Math.random()*9);
btns[index].setIcon(image);
flag=false;
}
if(o==b1){
comb.setEnabled(false);
b1.setEnabled(false);
for(int i=0;i<9;i++){
btns[i].setEnabled(true);
}
timer.start();
postion.start();
lb1.setText("10");
lb2.setText("0");
}
for(int i=0;i<9;i++){
if(index==i&&o==btns[i]&&!flag){
int goal=Integer.parseInt(lb2.getText().trim());
goal++;
btns[i].setIcon(baozha);
lb2.setText(goal+"");
flag=true;
}
}

}
}