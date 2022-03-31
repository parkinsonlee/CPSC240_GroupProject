package CPSC240_GroupProject;

import javax.swing.*;
import javax.swing.event.*;

import java.awt.event.*;

import java.util.Scanner;


public class YearIterator {
	public static JFrame frame = new JFrame("Select Year");

	public static void main(String[] args) {
		
		int year = 2000;
		
		BoxLayout mainLayout = new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS);
        frame.getContentPane().setLayout(mainLayout);
		
		JButton buttonB = new JButton(" < ");
		buttonB.addActionListener(new ButtonListener2(year));
		buttonB.setPreferredSize(new Dimension(24, 0));
		buttonB.setBounds((150, 150);
	    frame.getContentPane().add(buttonB, new ButtonListener2(year));
		
		JButton buttonA = new JButton(" > ");
		buttonA.addActionListener(new ButtonListener2A(year));
		buttonA.setPreferredSize(new Dimension(24, 0));
		buttonA.setBounds(200, 150)
	    frame.getContentPane().add(buttonA, new ButtonListener2A(year));
	    
		frame.pack();
	    frame.setVisible(true);
	    
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}


}
