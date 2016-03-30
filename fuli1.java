package fuli1;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class fuli1  extends JFrame
{
    private JLabel a1;
    private JLabel a2;
    private JLabel a3;
    private JLabel a4;
    private JLabel a5;
    private JTextField b1;
    private JTextField b2;
    private JTextField b3;
    private JTextField b4;
    private JTextField b5;
    private JButton c1;
    private JButton c2;
    private JButton c3;
    private JButton c4;
    private JButton c5;
    private JButton c6;
    private JTextArea text;
    public fuli1()
    {
        creatComponents()    ;
        layoutComponents();
        registerHandlers();
        setTitle("存款应用程序");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
    }
    private void  creatComponents()    
    {
        a1=new JLabel("本  金 ");
        a1.setFont(new Font("宋体",Font.BOLD,18));
        a2=new JLabel("年利率 ");
        a2.setFont(new Font("宋体",Font.BOLD,18));
        a3=new JLabel("年 数 ");
        a3.setFont(new Font("宋体",Font.BOLD,18));
        a4=new JLabel("本息 ");
        a4.setFont(new Font("宋体",Font.BOLD,18));
        a5=new JLabel("月利率 ");
        a5.setFont(new Font("宋体",Font.BOLD,18));
        
        
        
        b1=new JTextField(10);//本金
        b2=new JTextField(10);//利率
        b3=new JTextField(10);//年数
        b4=new JTextField(10);//本息
        b5=new JTextField(10);//月利率
        c1=new JButton("复利");
        c1.setFont(new Font("宋体",Font.BOLD,15));
        c3=new JButton("单利");
        c3.setFont(new Font("宋体",Font.BOLD,15));
        c4=new JButton("定投");
        c4.setFont(new Font("宋体",Font.BOLD,15));
        c2=new JButton("清除");
        c2.setFont(new Font("宋体",Font.BOLD,15));
        c5=new JButton("复利存期/利率");
        c5.setFont(new Font("宋体",Font.BOLD,15));
        c6=new JButton("等额还款");
        c6.setFont(new Font("宋体",Font.BOLD,15));
        text=new JTextArea();
    }
    private void layoutComponents()
    {
        //Container c=this.getContentPane();    
        setLayout(new FlowLayout());
        JPanel panel1=new JPanel();
        panel1.add(a1);
        panel1.add(b1);
        
        JPanel panel2=new JPanel();
        panel2.add(a2);
        panel2.add(b2);
        panel2.add(a5);
        panel2.add(b5);
        
        JPanel panel3=new JPanel();
        panel3.add(a3);
        panel3.add(b3);
        JPanel panel4=new JPanel();
        panel4.add(a4);
        panel4.add(b4);
        
        JPanel panel5=new JPanel();
        panel5.add(c1);
        panel5.add(c3);
        panel5.add(c4);
        panel5.add(c5);
        panel5.add(c2);
        panel5.add(c6);
        JPanel  leftpanel=new JPanel(new GridLayout(6,2));
        leftpanel.add(panel1);
        leftpanel.add(panel2);
        leftpanel.add(panel3);
        leftpanel.add(panel4);
        leftpanel.add(panel5);
        JScrollPane panel6=new JScrollPane(text);
        panel6.setPreferredSize(new Dimension(250,100));
        add(leftpanel);
        add(panel6);
    }
    private  void registerHandlers()
    {
         c1ActionEventHander hander1=new  c1ActionEventHander();
         c1.addActionListener(hander1);
         c2ActionEventHander hander2=new  c2ActionEventHander();
         c2.addActionListener(hander2);
    c3ActionEventHander hander3=new  c3ActionEventHander();
        c3.addActionListener(hander3);
        c4ActionEventHander hander4=new  c4ActionEventHander();
        c4.addActionListener(hander4);
        c5ActionEventHander hander5=new  c5ActionEventHander();
        c5.addActionListener(hander5);
        c6ActionEventHander hander6=new  c6ActionEventHander();
        c6.addActionListener(hander6);
    }
    private class c1ActionEventHander implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
           double principal;
            double amount;
            double rate;
            int n;
            NumberFormat currencyformatter=NumberFormat.getCurrencyInstance();
            String output="年"+"/t"+"复利存款"+"/n";
             double year =1;
             principal=Double.parseDouble(b1.getText());
             rate=Double.parseDouble(b2.getText()); 
             n=Integer.parseInt(b3.getText());
            if(b1.getText().equals("")&&b4.getText()!=null)//本金
            {
                
            	if(judge()){
            		amount = Double.parseDouble(b4.getText());
                    if (amount < 0) {
                        JOptionPane.showMessageDialog(null, "期待值输入有误，请重新输入数字");
                        amount = 0;
                    }
                    else{
                
                amount = Double.parseDouble(b4.getText());
                principal=0;
                while (year <= n) {
                    principal=amount/(Math.pow(1 + rate, year));
                    year = year + 1;
                }
                
                output="本金"+currencyformatter.format(principal) + "\n";
               text.setText(output);
            
            	}
            	}
                 
            }
            else if (b4.getText().equals("") && b1.getText() != null
                    && b3.getText() != null && b2.getText() != null) // 求期望值
            {
                if(judge()){
                    principal = Double.parseDouble(b1.getText()); // b1本金
                    if (principal < 0) {
                        JOptionPane.showMessageDialog(null, "本金输入有误，请重新输入数字");
                        rate = 0;
                    }
                    else{
                while (year <= n) {
                    amount = CalculateA(year,n,principal,rate);
                    output += String.valueOf(year) + "\t"+ currencyformatter.format(amount) + "\n";
                   // year = year + 1;
                }

                text.setText(output);
                }
                    } 
                }
            }

}
    private class  c2ActionEventHander implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            b1.setText("");
            b2.setText("");
            b3.setText("");
            b4.setText("");
            text.setText("");
        }
    }
    private class c3ActionEventHander implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            double principal;
            double amount;
            double rate;
            int n;
            NumberFormat currencyformatter = NumberFormat.getCurrencyInstance();
            String output = "年" + "/" + "单利存款";
            int year = 1;
            
            rate = Double.parseDouble(b2.getText());
            n = Integer.parseInt(b3.getText());
            if(b1.getText().equals(""))
            {
                if (b4.getText().equals(""))
                {
                    JOptionPane.showMessageDialog(null, "请输入本金或者本息");
                    
                }
                else{
                    amount = Double.parseDouble(b4.getText());
                    principal=0;
                    while (year <= n) {
                        principal = amount / (1 + rate* year);
                        year = year + 1;
                    }
                    
                    output="本金"+currencyformatter.format(principal) + "\n";
                    text.setText(output);
                }
            }
            else {
                principal = Double.parseDouble(b1.getText()); // 字符串转化为数字
                
                if (b4.getText().equals("")) {
                    while (year <= n) {
                        amount = principal * (1 + rate* year);
                        output += String.valueOf(year) + "\t"
                                + currencyformatter.format(amount) + "\n";
                        year = year + 1;
                    }
    
                    text.setText(output);
                    }
                else{
                    JOptionPane.showMessageDialog(null, "本金和本息输入一个即可");
                }
            }
            
        }
    }
    private class c4ActionEventHander implements ActionListener
    {
    public void actionPerformed(ActionEvent e)
    {
    	 double principal;
         double amount;
         double rate;
         int n;
         NumberFormat currencyformatter=NumberFormat.getCurrencyInstance();
         String output="年"+"/t"+"复利存款"+"/n";
          double year =1;
        //  principal=Double.parseDouble(b1.getText());
          rate=Double.parseDouble(b2.getText()); 
          n=Integer.parseInt(b3.getText());
          principal = Double.parseDouble(b1.getText()); // 字符串转化为数字
          
          if (b4.getText().equals("")) {
              while (year <= n) {
                  amount = principal*(Math.pow(1+ rate, year+1)-1)/rate;
                  output += String.valueOf(year) + "\t"+ currencyformatter.format(amount) + "\n";
                  year = year + 1;
              }

              text.setText(output);
              
          }
          else{
              JOptionPane.showMessageDialog(null, "请输入每年定投的金额");
          }
        
    }
    }
    private class c5ActionEventHander implements ActionListener//计算年份和利率
    {
        public void actionPerformed(ActionEvent e)
        {
        	double amount;
        	double rate;
        	double principal;
        	String output;
        	 NumberFormat currencyformatter = NumberFormat.getCurrencyInstance();
        	amount=Double.parseDouble(b4.getText());
        	principal=Double.parseDouble(b1.getText());
           
        	if(b3.getText().equals("")){
        		rate=Double.parseDouble(b2.getText());
        		double year=(int)((Math.log(amount)/Math.log(1+rate))-(Math.log(principal)/Math.log(1+rate)));;
        		output="年份"+currencyformatter.format(year) + "\n";
        		text.setText(output);
        	}
        	else{
        		if(b2.getText().equals("")){
        			int year=Integer.parseInt(b3.getText());
        			rate=((Math.pow(amount/principal, 1.0/year))-1);
        			output="利率"+currencyformatter.format(rate) + "\n";
            		text.setText(output);
        		}
        		else{
                    JOptionPane.showMessageDialog(null, "利率和存期输入一个即可");
                }
        	}
        	
        }
    }
    private class c6ActionEventHander implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
        	double principal;
            double amount;
            double rate;
            double trate;
           
            NumberFormat currencyformatter=NumberFormat.getCurrencyInstance();
            String output="月"+"/t"+"等额还款"+"/n";
            
           //  principal=Double.parseDouble(b1.getText());
             rate=Double.parseDouble(b5.getText()); 
             double year=Integer.parseInt(b3.getText());
             principal = Double.parseDouble(b1.getText()); // 字符串转化为数字
                 if (b4.getText().equals("")) {
                    
                    	 rate=rate/12;
                    	 trate=rate+(double)1;
                         for(int i=1;i<year*12;i++){
                        	 trate*=(rate+(double)1);
                        	 
                     }
                     amount=(principal*trate*rate)/(trate-(double)1);
                        	output +="每月"+ currencyformatter.format(amount) + "\n";
                        	text.setText(output);
                 }
                 else{
                     JOptionPane.showMessageDialog(null, "请输入贷款金额（本金）");
                 }
        }
    }
    public static void main(String[] args)
    {
         fuli1 frame=new fuli1();
        frame.setVisible(true);
        frame.setSize(600,450); 
      frame.setResizable(false);
    }
    //本息
    public double CalculateA(double years2,int n2,double principal2,double rate2){
 
    	double amount2 = 0;
        while (years2 <= n2) {
        	/*amount = principal * Math.pow(1 + rate, year);
            output += String.valueOf(year) + "\t"+ currencyformatter.format(amount) + "\n";
            year = year + 1;*/
            amount2 = principal2 * Math.pow(1 + rate2, years2);
                   
            
            years2 = years2 + 1;
        }
        return amount2;
    }
    public void Amount(){//本息
    	double principal;
        double amount;
        double rate;
        int n;
        NumberFormat currencyformatter=NumberFormat.getCurrencyInstance();
        String output="年"+"/t"+"复利存款"+"/n";
         double year =1;
         principal=Double.parseDouble(b1.getText());
         rate=Double.parseDouble(b2.getText()); 
         n=Integer.parseInt(b3.getText());
    	principal = Double.parseDouble(b1.getText()); // 字符串转化为数字
        
            while (year <= n) {
                amount = principal * Math.pow(1 + rate, year);
                output += String.valueOf(year) + "\t"+ currencyformatter.format(amount) + "\n";
                year = year + 1;
            }

            text.setText(output);
    	
    }
    public boolean judge(){
    	double rate;
    	int n;
    	double amount;
    
    	 if(b1.getText().equals("")&&b4.getText()!=null)//本金
         {
             
         	try {
                 rate = Double.parseDouble(b2.getText());
                 if (rate < 0) {
                     JOptionPane.showMessageDialog(null, "利率输入有误，请重新输入数字");
                     rate = 0;
                 }
                 n = Integer.parseInt(b3.getText());
                 if (n < 0) {
                     JOptionPane.showMessageDialog(null, "年份输入有误，请重新输入数字");
                     n = 0;
                 }
                 
             } catch (Exception e2) {
                 // TODO: handle exception
                 JOptionPane.showMessageDialog(null, "数据输入有误，请重新输入数字");

             }
            
    }
    	 return true;
    }
    
}
