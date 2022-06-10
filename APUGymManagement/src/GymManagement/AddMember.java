package GymManagement;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Admin
 */

public class AddMember extends JPanel{
	
	String[] options = {"Accountability", "Fast Track", "Ultimate"};
	String Name, weeks, plan;
	
	public AddMember() {
		
		// GUI
		setLayout(null);
		setVisible(false);
		setBackground(Color.WHITE);
		setBounds(360, 0, 920, 720);
		
		// Init Component
		JLabel b1title = new JLabel("Member Registration");
		JLabel name = new JLabel("Name:");
		JLabel plans = new JLabel("Select your Work Out plan");
		JLabel chart = new JLabel(new ImageIcon(getClass().getResource("chart.png")));
		JTextField nameInput = new JTextField();
		JButton OkButton1 = new JButton("Register");
		JComboBox comboBox = new JComboBox(options);
		JLabel x = new JLabel("X");
		JTextField NoOfWeeks = new JTextField("1");
		JLabel week = new JLabel("week(s)");

		// add component
		add(b1title);
		b1title.setBounds(300, 30, 500, 50);
		b1title.setFont(new Font("SANS_SERIF",Font.BOLD,36));

		add(name);
		name.setBounds(50, 95, 100, 100);
		name.setFont(new Font("SANS_SERIF",Font.BOLD,20));
		
		add(nameInput);
		nameInput.setBounds(135, 120, 450, 50);
		nameInput.setFont(new Font("SANS_SHERIF",Font.PLAIN,18));
		
		add(plans);
		plans.setBounds(50, 170, 300, 100);
		plans.setFont(new Font("SANS_SHERIF",Font.BOLD,20));
		
		add(comboBox);
		comboBox.setBounds(320, 205, 120, 30);
		comboBox.setBackground(Color.WHITE);
		
		add(x);
		x.setBounds(480, 210, 20, 20);
		x.setFont(new Font("SANS_SHERIF",Font.BOLD,20));
		
		add(NoOfWeeks);
		NoOfWeeks.setBounds(550, 205 ,30, 30);
		NoOfWeeks.setFont(new Font("SANS_SHERIF",Font.PLAIN,18));
		
		add(week);
		week.setBounds(600, 205, 100, 20);
		week.setFont(new Font("SANS_SHERIF",Font.BOLD,20));

		add(chart);
		chart.setBounds(0, 280, 900, 590);
		
		add(OkButton1);
		OkButton1.setBounds(600, 120, 200, 50);
		OkButton1.setBackground(Color.GREEN);
		OkButton1.setForeground(Color.WHITE);
		OkButton1.setFocusPainted(false);
		OkButton1.setFont(new Font("SANS_SERIF",Font.BOLD,20));
		OkButton1.setBorder(null);
		
		// Button actions
		OkButton1.addActionListener((ActionEvent e) -> {
			Name = nameInput.getText();
			weeks = NoOfWeeks.getText();
			plan = comboBox.getSelectedItem().toString();
			
			if (Name.length() != 0) {
				ArrayList<Member> arr = new ArrayList<>();
				Scanner io = null;
				try {
					io = new Scanner(new File("member.txt"));
				} catch (FileNotFoundException ex) {
					Logger.getLogger(AddMember.class.getName()).log(Level.SEVERE, null, ex);
				}
				while (io.hasNext()) {
					
					String _name = io.nextLine();
					String _plan = io.nextLine();
					String _week = io.nextLine();
					String _fees = io.nextLine();
					String _paid = io.nextLine();
					String _due  = io.nextLine();
					String _trainer = io.nextLine();
					String _from = io.nextLine();
					String _to = io.nextLine();
					
					int NoOfWeek = Integer.parseInt(_week);
					int Fees = Integer.parseInt(_fees);
					int Paid = Integer.parseInt(_paid);
					int Due = Integer.parseInt(_due);
					
					io.nextLine();
					
					Member member = new Member(_name, _plan, NoOfWeek, Fees, Paid, Due, _trainer, _from, _to);
					arr.add(member);
				}
				
				try (PrintWriter p1 = new PrintWriter("member.txt")) {
					
					//System.out.println(comboBox.getSelectedIndex());
					try{
						for(int i=0; i<arr.size(); i++){
							
							Member a = arr.get(i);
							
							p1.println(a.name);
							p1.println(a.plan);
							p1.println(a.week);
							p1.println(a.fess);
							p1.println(a.paid);
							p1.println(a.due);
							p1.println(a.trainer);
							p1.println(a.from);
							p1.println(a.to);
							p1.println();

						}
							
						int unit;

						if (plan.equals(options[0])) {
							unit = 100;
						} else if (plan.equals(options[1])) {
							unit = 190;
						} else {
							unit = 270;
						}

						int _NoOfWeeks = Integer.parseInt(weeks);
						int fess = unit * _NoOfWeeks;
						int due = fess;
						int paid = fess - due;

						Member m = new Member(Name, plan, _NoOfWeeks, fess, paid, due);
						p1.println(m.name);
						p1.println(m.plan);
						p1.println(m.week);
						p1.println(m.fess);
						p1.println(m.paid);
						p1.println(m.due);
						p1.println(m.trainer);
						p1.println(m.from);
						p1.println(m.to);
						p1.println();
							
					} catch(NumberFormatException ex){
						
					}
					JOptionPane.showMessageDialog(AddMember.this, "Member has been added successfully");
					
					nameInput.setText("");
					
				}catch(FileNotFoundException ex){
					Logger.getLogger(AddMember.class.getName()).log(Level.SEVERE, null, ex);
					
				}
			} else {
				JOptionPane.showMessageDialog(AddMember.this, "Information missing !");
			}
			
		});

	}		
}
