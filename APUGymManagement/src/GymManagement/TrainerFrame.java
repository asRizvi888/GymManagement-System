package GymManagement;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class TrainerFrame {
	
	String[] attributes = {"Name", "Plan", "From", "To"};
	FileParser fileParser = new FileParser();
			
	ArrayList<Member> arr = fileParser.FileToArrayList();
			

	JFrame TrainerFrame = new JFrame();
	JPanel panel = new JPanel();
	JLabel Name = new JLabel("Trainer Name: ");
	JTextField NameField = new JTextField();
	JButton SearchBtn = new JButton("Search");
	JButton LogOut = new JButton("Log Out");
	JLabel EmptyMsg = new JLabel("No trainee(s) enrolled yet");
	JTable table = new JTable();
	DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
	

	JScrollPane scrollPane = new JScrollPane(table);

	
	public TrainerFrame () {
		panel.setBounds(0, 0, 1280, 720);
		panel.setBackground(Color.WHITE);
		panel.setLayout(null);
		
		tableModel.setColumnIdentifiers(attributes);
		
		panel.add(Name);
		Name.setBounds(230, 100, 300, 50);
		Name.setFont(new Font("SANS_SERIF",Font.BOLD,32));
		
		panel.add(NameField);
		NameField.setBounds(470, 100, 370, 50);
		NameField.setFont(new Font("SANS_SERIF",Font.BOLD,28));
		
		panel.add(EmptyMsg);
		EmptyMsg.setBounds(400, 350, 700, 50);
		EmptyMsg.setFont(new Font("SANS_SERIF",Font.BOLD,48));
		EmptyMsg.setVisible(false);
		
		panel.add(scrollPane);
		scrollPane.setBounds(150, 200, 1000, 350);
		scrollPane.setVisible(false);
		
		panel.add(SearchBtn);
		SearchBtn.setBounds(850, 100, 200, 50);
		SearchBtn.setBackground(Color.GREEN);
		SearchBtn.setForeground(Color.WHITE);
		SearchBtn.setFocusPainted(false);
		SearchBtn.setFont(new Font("SANS_SERIF",Font.BOLD,20));
		SearchBtn.setBorder(null);
		
		SearchBtn.addActionListener((ActionEvent e)->{
			int size = arr.size();
			int cnt = 0;
			
			scrollPane.setVisible(false);
			EmptyMsg.setVisible(false);
			
			String tName = NameField.getText();
			
			for (int i=0; i<size; ++i) {
				Member member = arr.get(i);

				if (tName.equals(member.trainer)) { 
					
					String[] data = new String[4];

					data[0] = member.name;
					data[1] = member.plan;
					data[2] = member.from;
					data[3] = member.to;

					tableModel.addRow(data);
					
					++cnt;
				}
			}
			
			if (cnt!=0) {
				table.setModel(tableModel);

				scrollPane.setVisible(true);
			} else {
				EmptyMsg.setVisible(true);
			}
		
		});
		
		panel.add(LogOut);
		LogOut.setBounds(600, 600, 200, 50);
		LogOut.setBackground(Color.MAGENTA);
		LogOut.setForeground(Color.WHITE);
		LogOut.setFocusPainted(false);
		LogOut.setFont(new Font("SANS_SERIF",Font.BOLD,20));
		LogOut.setBorder(null);
		
		LogOut.addActionListener((ActionEvent e) -> {
			MyClass.Login();
			TrainerFrame.dispose();
		});
		
	}

	
	public void GUI () {
		TrainerFrame.setVisible(true);
		TrainerFrame.setSize(1280, 720);
		TrainerFrame.setTitle("Trainer Feed");
		
		TrainerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		TrainerFrame.setResizable(false);
		TrainerFrame.add(panel);
	}	

}
