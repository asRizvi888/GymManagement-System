package GymManagement;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Admin
 */
public class MemberAuth implements ActionListener {
	
	JFrame mAuthFrame = new JFrame();
	JPanel panel = new JPanel();
	
	JButton SignUPButton = new JButton("Sign UP");
	JButton SignINButton = new JButton("Sign IN");
	JLabel Banner = new JLabel("Member Authentication");

	public MemberAuth() {
		panel.setLayout(null);
		panel.setBackground(new Color(5, 65, 90));
		
		Banner.setBounds(500, 200, 350, 30);
		Banner.setFont(new Font("SANS_SERIF",Font.BOLD,26));
		Banner.setForeground(Color.WHITE);

		SignINButton.setBounds(500, 300, 300, 50);
		SignINButton.setBackground(Color.WHITE);
		SignINButton.setForeground(new Color(5, 65, 90));
		SignINButton.setFocusPainted(false);
		SignINButton.setFont(new Font("SANS_SERIF",Font.BOLD,20));
		
		SignUPButton.setBounds(500, 400, 300, 50);
		SignUPButton.setBackground(Color.WHITE);
		SignUPButton.setForeground(new Color(5, 65, 90));
		SignUPButton.setFocusPainted(false);
		SignUPButton.setFont(new Font("SANS_SERIF",Font.BOLD,20));
		
		panel.add(Banner);
		panel.add(SignUPButton);
		panel.add(SignINButton);
		
		SignINButton.addActionListener(this);
		SignUPButton.addActionListener(this);
		
	}
		
	public void GUI () {
		
		mAuthFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mAuthFrame.setTitle("Member Authentication");
		mAuthFrame.setVisible(true);
		mAuthFrame.setSize(1280, 720);
		mAuthFrame.setResizable(false);
		mAuthFrame.add(panel);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == SignINButton) {		
			MemberLogIN memberLogIN = new MemberLogIN();
			memberLogIN.GUI();
			
			mAuthFrame.dispose();
		}
		
		if (e.getSource() == SignUPButton) {
			MemberSignUP memberSignUP = new MemberSignUP();
			memberSignUP.GUI();
			
			mAuthFrame.dispose();
		}
	}	
}
