
package GymManagement;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Admin
 */
public class MemberPanel implements ActionListener {
	
	// init component
	JFrame MemberFrame = new JFrame();
	JPanel SidePanel = new JPanel();
	JPanel MainPanel = new JPanel();
	JLabel UserImage = new JLabel(new ImageIcon(getClass().getResource("userImage.png")));
	JLabel UserName = new JLabel();
	JLabel Membership = new JLabel();
	JButton SignOUTButton = new JButton("Sign OUT");
	
	JLabel PayMentInfo = new JLabel("Payment Info");
	JLabel ScheduleManagement = new JLabel("Manage Schedule");
	
	JLabel Fees = new JLabel("FEES");
	JLabel Paid = new JLabel("PAID");
	JLabel Due = new JLabel("DUE");
	
	JLabel FeesData = new JLabel();
	JLabel PaidData = new JLabel();
	JLabel DueData = new JLabel();
	
	JLabel PaymentTitle = new JLabel("Enter amount: ");
	JTextField AmountField = new JTextField();
	JButton OkButton1 = new JButton("Make Payment");
	JButton OkButton2 = new JButton("Set Schedule");

	JLabel From = new JLabel("Start: ");
	JLabel To = new JLabel("End: ");
	JTextField FromField = new JTextField();
	JTextField ToField = new JTextField();
	
	JLabel Trainer = new JLabel("Trainer: ");
	JComboBox Trainers;

	public String token;
	int index = 0;
	
	Member member = null;
	FileParser fileParser = new FileParser();
	ArrayList<Member> members = fileParser.FileToArrayList();
	Vector<String> trainerArray = new Vector<>();

	public MemberPanel(String token) {
		this.token = token;
		
		for (int i=0; i<members.size(); ++i) {
			member = members.get(i);
			if (member.name.equals(token)) {
				index = i;
				break;
			}
		}
		// component did mount
		SidePanel.setBounds(0, 0, 380, 720);
		SidePanel.setBackground(new Color(5, 65, 90));
		SidePanel.setLayout(null);
		
		MainPanel.setBounds(380, 0, 900, 720);
		MainPanel.setBackground(Color.WHITE);
		MainPanel.setLayout(null);
		
		SidePanel.add(UserImage);
		UserImage.setBounds(50, 30, 280, 280);
		
		SidePanel.add(UserName);
		UserName.setText(token.toUpperCase());
		UserName.setBounds(120, 300, 200, 50);
		UserName.setForeground(Color.WHITE);
		UserName.setFont(new Font("SANS_SERIF",Font.BOLD,36));
		
		SidePanel.add(Membership);
		Membership.setText("Membership : " + member.plan);
		Membership.setBounds(80, 350, 350, 50);
		Membership.setForeground(Color.WHITE);
		Membership.setFont(new Font("SANS_SERIF",Font.PLAIN,20));
		
		SidePanel.add(SignOUTButton);
		SignOUTButton.setBounds(40, 550, 300, 50);
		SignOUTButton.setBackground(Color.WHITE);
		SignOUTButton.setForeground(new Color(5, 65, 90));
		SignOUTButton.setFocusPainted(false);
		SignOUTButton.setFont(new Font("SANS_SERIF",Font.BOLD,20));
		SignOUTButton.addActionListener(this);
		
		MainPanel.add(PayMentInfo);
		PayMentInfo.setBounds(730, 10, 250, 50);
		PayMentInfo.setFont(new Font("SANS_SERIF",Font.BOLD,36));
		PayMentInfo.setForeground(Color.BLACK);
		
		MainPanel.add(ScheduleManagement);
		ScheduleManagement.setBounds(690, 315, 500, 50);
		ScheduleManagement.setFont(new Font("SANS_SHERIF", Font.BOLD, 36));
		ScheduleManagement.setForeground(Color.BLACK);

		MainPanel.add(Fees);
		Fees.setBounds(600, 100, 100, 50);
		Fees.setFont(new Font("SANS_SERIF",Font.BOLD,36));
		Fees.setForeground(Color.GRAY);
		
		MainPanel.add(FeesData);
		FeesData.setBounds(620, 150, 100, 50);
		FeesData.setFont(new Font("SANS_SERIF",Font.BOLD,32));
		FeesData.setForeground(Color.MAGENTA);
		FeesData.setText((String.valueOf(member.fess)) + "$");
		
		MainPanel.add(Paid);
		Paid.setBounds(800, 100, 100, 50);
		Paid.setFont(new Font("SANS_SERIF",Font.BOLD,36));
		Paid.setForeground(Color.GRAY);
		
		MainPanel.add(PaidData);
		PaidData.setBounds(820, 150, 100, 50);
		PaidData.setFont(new Font("SANS_SERIF",Font.BOLD,32));
		PaidData.setForeground(Color.MAGENTA);
		PaidData.setText((String.valueOf(member.paid)) + "$");
		
		MainPanel.add(Due);
		Due.setBounds(1000, 100, 100, 50);
		Due.setFont(new Font("SANS_SERIF",Font.BOLD,36));
		Due.setForeground(Color.GRAY);
		
		MainPanel.add(DueData);
		DueData.setBounds(1010, 150, 100, 50);
		DueData.setFont(new Font("SANS_SERIF",Font.BOLD,32));
		DueData.setForeground(Color.MAGENTA);
		DueData.setText((String.valueOf(member.due)) + "$");

		MainPanel.add(PaymentTitle);
		PaymentTitle.setBounds(500, 230, 300, 50);
		PaymentTitle.setFont(new Font("SANS_SERIF",Font.BOLD,24));
		PaymentTitle.setForeground(Color.BLACK);
		
		MainPanel.add(AmountField);
		AmountField.setBounds(680, 230, 150, 50);
		AmountField.setFont(new Font("SANS_SERIF",Font.BOLD,20));

		MainPanel.add(OkButton1);
		OkButton1.setBounds(975, 230, 200, 50);
		OkButton1.setBackground(Color.GREEN);
		OkButton1.setForeground(Color.WHITE);
		OkButton1.setFocusPainted(false);
		OkButton1.setFont(new Font("SANS_SERIF",Font.BOLD,20));
		OkButton1.setBorder(null);
		
		OkButton1.addActionListener(this);
		
		MainPanel.add(Trainer);
		Trainer.setBounds(500, 380, 500, 50);
		Trainer.setFont(new Font("SANS_SERIF",Font.BOLD,24));
		Trainer.setForeground(Color.BLACK);
		
		trainerArray.add("Not assigned");
		
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

			trainerArray.add(_name);
		}
		
		int pos = 0;
		
		for (int i=0; i<trainerArray.size(); ++i) {
			if (trainerArray.get(i).equals(member.trainer)) {
				pos = i;
				break;
			}
		}
		
			
		Trainers = new JComboBox(trainerArray);
		MainPanel.add(Trainers);
		Trainers.setBounds(650, 380, 500, 50);
		Trainers.setSelectedIndex(pos);

		MainPanel.add(From);
		From.setBounds(500, 450, 500, 50);
		From.setFont(new Font("SANS_SERIF",Font.BOLD,24));
		From.setForeground(Color.BLACK);
		
		MainPanel.add(To);
		To.setBounds(500, 500, 500, 50);
		To.setFont(new Font("SANS_SERIF",Font.BOLD,24));
		To.setForeground(Color.BLACK);
		
		MainPanel.add(FromField);
		FromField.setBounds(650, 450, 500, 50);
		FromField.setFont(new Font("SANS_SERIF",Font.BOLD,24));
		FromField.setText(member.from);
		
		MainPanel.add(ToField);
		ToField.setBounds(650, 500, 500, 50);
		ToField.setFont(new Font("SANS_SERIF",Font.BOLD,24));
		ToField.setText(member.to);
		
		MainPanel.add(OkButton2);
		OkButton2.setBounds(750, 600, 200, 50);
		OkButton2.setBackground(Color.MAGENTA);
		OkButton2.setForeground(Color.WHITE);
		OkButton2.setFocusPainted(false);
		OkButton2.setFont(new Font("SANS_SERIF",Font.BOLD,20));
		OkButton2.setBorder(null);
		
		OkButton2.addActionListener(this);
		
	}
	
	public void GUI () {
		MemberFrame.setVisible(true);
		MemberFrame.setSize(1280, 720);
		MemberFrame.setTitle("Member Feed");
		
		MemberFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MemberFrame.setResizable(false);
		
		MemberFrame.add(SidePanel);
		MemberFrame.add(MainPanel);
		
		//System.out.println(this.token);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == SignOUTButton) {
			MyClass.Login();
			MemberFrame.dispose();
		}
		
		if (e.getSource() == OkButton1) {
			
			String _amount = AmountField.getText();
			int Amount = Integer.parseInt(_amount);
			
			if (Amount > member.due) {
				JOptionPane.showMessageDialog(MainPanel, "Amount exceeded !");
				
			} else {
				member.paid += Amount;
				member.due -= Amount;
				
				members.set(index, member);
				FileParser arrToFile = new FileParser();
				arrToFile.ArrayListToFile(members);
				
				AmountField.setText("");
				PaidData.setText(String.valueOf(member.paid));
				DueData.setText(String.valueOf(member.due));

				JOptionPane.showMessageDialog(MainPanel, "Successfully updated info !");
			}
			
		}
		
		if (e.getSource() == OkButton2) {
			String _from = FromField.getText();
			String _to = ToField.getText();
			int pos = Trainers.getSelectedIndex();
			//format time string
			_from += (_from.contains(":")?"":":00");
			_to += (_to.contains(":")?"":":00");
			
			
			member.from = _from;
			member.to = _to;
			member.trainer = trainerArray.get(pos);
			
			members.set(index, member);
			FileParser arrToFile = new FileParser();
			arrToFile.ArrayListToFile(members);
			
			JOptionPane.showMessageDialog(MainPanel, "Successfully updated info !");
		}
		
	}
	
}
