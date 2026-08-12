package com.war.test;

import java.awt.Color; 
import java.awt.Font; 
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener; 

import javax.swing.JButton; 
import javax.swing.JFrame; 
import javax.swing.JLabel; 
import javax.swing.JOptionPane; 
import javax.swing.SwingConstants; 
import javax.swing.border.LineBorder; 

public class game21 extends JFrame { 
	private JLabel label_2; 
	private int number; 
	private int sum; 
	final JLabel label = new JLabel(); 
	final JLabel label_1 = new JLabel(); 

	public static void main(String[] args) { 
		new game21(); 
	} 

	public game21() { 
		super("21点？！"); 
		getContentPane().setLayout(null); 
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		final JButton button = new JButton(); 
		button.addActionListener(new ActionListener() { 
	public void actionPerformed(final ActionEvent arg0) { 
		onClick(); 
	} 
		}); 
		button.setText("出牌"); 
		button.setBounds(170, 350, 106, 28); 
		getContentPane().add(button); 
		label.setBorder(new LineBorder(Color.black, 1, false)); 
		label.setHorizontalAlignment(SwingConstants.CENTER); 
		label.setFont(new Font("", Font.BOLD, 26)); 
		label.setText("背面"); 
		label.setBounds(158, 81, 137, 153); 
		getContentPane().add(label); 

		label_1.setText("你已经拥有的牌:"); 
		label_1.setBounds(109, 22, 270, 45); 
		getContentPane().add(label_1); 
		this.setBounds(200, 300, 501, 528); 
		this.setVisible(true); 
		getContentPane().add(getLabel_2()); 
} 

	public int randNumber() { 
		try { 
			Thread.sleep(10); 
		} catch (InterruptedException e) { 
			e.printStackTrace(); 
		} 
		return (int) (Math.random() * 10 + 1); 
	} 

	public void onClick() { 
		number = this.randNumber(); 
		this.sum += number; 
		label.setText("" + number); 
		String strTemp = this.label_1.getText(); 
		strTemp += "" + number + " "; 
		label_1.setText(strTemp); 
		String temp = "合计：" + sum; 
		label_2.setText(temp); 
		isWin(); 
	} 

	public void isWin() { 
		if (sum > 21) { 
			JOptionPane.showMessageDialog(this, "你输了"); 
			clear(); 
			return; 
		} else if (sum == 21) { 
			JOptionPane.showMessageDialog(this, "你赢了"); 
			clear(); 
			return; 
		} else { 
			int i = JOptionPane.showOptionDialog(this, "是否继续？", "提示", 
					JOptionPane.OK_CANCEL_OPTION, 
					JOptionPane.INFORMATION_MESSAGE, null, null, null); 
			if (i == JOptionPane.OK_OPTION) { 
				onClick(); 
				} else 
					return; 
		} 
	} 

	private void clear() { 
		label_2.setText("合计："); 
		sum = 0; 
		number = 0; 
		label_1.setText("你已经拥有的牌:"); 
	} 

/** 
* @return 
*/ 
	protected JLabel getLabel_2() { 
		if (label_2 == null) { 
			label_2 = new JLabel(); 
			label_2.setText("合计:"); 
			label_2.setBounds(313, 35, 66, 18); 
		} 
		return label_2; 
	} 

	} 
