package GymManagement;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
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
public class AddTrainer extends JPanel {
	
	String[] attributes = {"Trainer", "Service days"};
	ArrayList<Trainer> arr = new ArrayList<>();
		
	public AddTrainer () {
				
		Scanner TrainerIO = null;
		
		try {
			TrainerIO = new Scanner(new File("trainers.txt"));
		} catch (FileNotFoundException ex) {
			Logger.getLogger(AddTrainer.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		while (TrainerIO.hasNext()) {
			
			String _name = TrainerIO.nextLine();
			String _days = TrainerIO.nextLine();
			
			TrainerIO.nextLine();

			Trainer trainer = new Trainer(_name, _days);
			
			arr.add(trainer);
		}
		
		// GUI
		setLayout(null);
		setVisible(false);
		setBackground(Color.WHITE);
		setBounds(360, 0, 920, 720);
		
		// Init Component
		JLabel b1title = new JLabel("Trainer Enrollment");
		JLabel name = new JLabel("Name:");
		JLabel days = new JLabel("Service days:");
		JTextField nameInput = new JTextField();
		JButton OkButton1 = new JButton("Enroll");
		JTextField dayField = new JTextField();
		JTable table = new JTable();
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		
		// add component
		add(b1title);
		b1title.setBounds(300, 30, 500, 50);
		b1title.setFont(new Font("SANS_SERIF",Font.BOLD,36));

		add(name);
		name.setBounds(50, 95, 100, 100);
		name.setFont(new Font("SANS_SERIF",Font.BOLD,20));
		
		add(nameInput);
		nameInput.setBounds(200, 120, 400, 50);
		nameInput.setFont(new Font("SANS_SHERIF",Font.PLAIN,18));
		
		add(days);
		days.setBounds(50, 180, 300, 100);
		days.setFont(new Font("SANS_SHERIF",Font.BOLD,20));
		
		add(dayField);
		dayField.setBounds(200, 205 ,650, 50);
		dayField.setFont(new Font("SANS_SHERIF",Font.PLAIN,18));
		
		add(OkButton1);
		OkButton1.setBounds(650, 120, 200, 50);
		OkButton1.setBackground(Color.GREEN);
		OkButton1.setForeground(Color.WHITE);
		OkButton1.setFocusPainted(false);
		OkButton1.setFont(new Font("SANS_SERIF",Font.BOLD,20));
		OkButton1.setBorder(null);
		
		tableModel.setColumnIdentifiers(attributes);

		int size = arr.size();
				
		for (int i=0; i<size; ++i) {
			Trainer member = arr.get(i);
			
			String[] data = new String[2];
			
			data[0] = member.name;
			data[1] = member.days;
			
			tableModel.addRow(data);
		}
		table.setModel(tableModel);

		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane);
		scrollPane.setBounds(50, 300, 800, 350);
		
		OkButton1.addActionListener((ActionEvent e) -> {
			
			String _name = nameInput.getText();
			String _days = dayField.getText();
			
			Trainer newTrainer = new Trainer(_name, _days);
			arr.add(newTrainer);
			
			Vector<String> rowData = new Vector<>();
			rowData.add(_name);
			rowData.add(_days);
			
			tableModel.addRow(rowData);
			
			try (PrintWriter p1 = new PrintWriter("trainers.txt")) {

			try{

				for(int i=0; i<arr.size(); i++) {

					Trainer a = arr.get(i);

					p1.println(a.name);
					p1.println(a.days);
					
					p1.println();
				}

			} catch(NumberFormatException ex){}

		} catch(FileNotFoundException ex){
			Logger.getLogger(AddMember.class.getName()).log(Level.SEVERE, null, ex);
		}
		});

	}

}
