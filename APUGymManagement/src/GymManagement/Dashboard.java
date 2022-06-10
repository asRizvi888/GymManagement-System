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
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */

public class Dashboard extends JPanel{
	
	String[] attributes = {"Name", "Plan", "Weeks", "Fees", "Paid", "Due", "Trainer", "From", "To"};

	ArrayList<Member> arr = new ArrayList<>();

	public Dashboard () {
						
		Scanner io = null;
		
		try {
			io = new Scanner(new File("member.txt"));
		} catch (FileNotFoundException ex) {
			Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
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
		
		// GUI
		setLayout(null);
		setVisible(false);
		setBackground(Color.WHITE);
		setBounds(360, 0, 920, 720);
		
		// Init Component
		JLabel b1title = new JLabel("Information");
		JLabel name = new JLabel("Name:");
		
		JTextField nameField = new JTextField();
		
		JTable table = new JTable();
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		tableModel.setColumnIdentifiers(attributes);
		
		int size = arr.size();
				
		for (int i=0; i<size; ++i) {
			Member member = arr.get(i);
			
			String[] data = new String[9];
			
			data[0] = member.name;
			data[1] = member.plan;
			data[2] = String.valueOf(member.week);
			data[3] = String.valueOf(member.fess);
			data[4] = String.valueOf(member.paid);
			data[5] = String.valueOf(member.due);
			data[6] = member.trainer;
			data[7] = member.from;
			data[8] = member.to;
			
			tableModel.addRow(data);
		}
		table.setModel(tableModel);

		JScrollPane scrollPane = new JScrollPane(table);
		JButton OkButton1 = new JButton("REMOVE");
		
		// add component
		add(b1title);
		b1title.setBounds(350, 10, 500, 50);
		b1title.setFont(new Font("SANS_SERIF",Font.BOLD,36));
		
		add(name);
		name.setBounds(100, 100, 80, 30);
		name.setFont(new Font("SANS_SERIF",Font.BOLD,18));
		
		add(nameField);
		nameField.setBounds(180, 100, 200, 30);
		
		add(scrollPane);
		scrollPane.setBounds(50, 180, 800, 400);
		
		add(OkButton1);
		OkButton1.setBounds(600, 100, 200, 50);
		OkButton1.setBackground(Color.RED);
		OkButton1.setForeground(Color.WHITE);
		OkButton1.setFocusPainted(false);
		OkButton1.setFont(new Font("SANS_SERIF",Font.BOLD,20));
		OkButton1.setBorder(null);
		
		// Button actions
		OkButton1.addActionListener((ActionEvent e) -> {
			String _name = nameField.getText();
			
			Member m = null;
			Boolean found = false;
			int idx = 0;
			
			for (int i=0; i<arr.size(); ++i) {
				m = arr.get(i);
				
				if (m.name.equals(_name)) {
					found = true;
					idx = i;
					break;
				}	
			}
			
			if (!found) {
				JOptionPane.showMessageDialog(Dashboard.this, "User not found !");
			} else {
				
				tableModel.removeRow(idx);
				arr.remove(idx);
				
				ArrayList<UserCredential> users = new ArrayList<>();
					
				try (Scanner auth = new Scanner(new File("credential.txt"))) {

					while (auth.hasNext()) {
						String _uname = auth.nextLine();
						String _upass = auth.nextLine();

						UserCredential user = new UserCredential(_uname, _upass);
						users.add(user);

						auth.nextLine();
					}
				} catch (FileNotFoundException ex) {
					Logger.getLogger(MemberSignUP.class.getName()).log(Level.SEVERE, null, ex);
				}
				
				FileParser arrToFile = new FileParser();
				arrToFile.ArrayListToFile(arr);
				int index = 0;
				
				for (int i=0; i<users.size(); ++i) {
					UserCredential User = users.get(i);
					
					if (User.UName == _name) {
						index = i;
						break;
					}
				}

				users.remove(index);
				
				try (PrintWriter pw = new PrintWriter("credential.txt")) {

					for (int i=0; i<users.size(); ++i) {
						UserCredential existingUser = users.get(i);

						pw.println(existingUser.UName);
						pw.println(existingUser.Password);
						pw.println();
					}

				} catch (FileNotFoundException ex) {
					Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
				}
			JOptionPane.showMessageDialog(Dashboard.this, "Successfully updated info !");
		}
			
		});

	}		
}
