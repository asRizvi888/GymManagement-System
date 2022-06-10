package GymManagement;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Admin
 */
public class MemberLogIN implements ActionListener {
	
	JFrame mAuthFrame = new JFrame();
	JPanel panel = new JPanel();
	
	// init component
	JButton SignINButton = new JButton("Sign IN");
	JLabel Banner = new JLabel("Log In as Member");
	JLabel USERNAME = new JLabel("USERNAME");
	JLabel PASSWORD = new JLabel("PASSWORD");
	JTextField usrTxtFld = new JTextField();
	JPasswordField pwdTxtFld = new JPasswordField();
		
	public MemberLogIN () {
		panel.setLayout(null);
		panel.setBackground(new Color(5, 65, 90));
		
		// component layout
		Banner.setBounds(500, 200, 350, 30);
		Banner.setFont(new Font("SANS_SERIF",Font.BOLD,26));
		Banner.setForeground(Color.WHITE);
		
		USERNAME.setBounds(500, 275, 200, 30);
		USERNAME.setForeground(Color.WHITE);
		
		usrTxtFld.setBounds(500, 300, 300, 30);
		pwdTxtFld.setBounds(500, 400, 300, 30);

		PASSWORD.setBounds(500, 375, 200, 30);
		PASSWORD.setForeground(Color.WHITE);

		SignINButton.setBounds(500, 500, 300, 50);
		SignINButton.setBackground(Color.WHITE);
		SignINButton.setForeground(new Color(5, 65, 90));
		SignINButton.setFocusPainted(false);
		SignINButton.setFont(new Font("SANS_SERIF",Font.BOLD,20));
		
		// component did mount
		panel.add(Banner);
		panel.add(USERNAME);
		panel.add(PASSWORD);
		panel.add(usrTxtFld);
		panel.add(pwdTxtFld);
		panel.add(SignINButton);
		
		SignINButton.addActionListener(this);
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
			
			String UName = usrTxtFld.getText();
			String UPass = pwdTxtFld.getText();
			
			boolean found = false;
			
			for (int i=0; i<users.size(); ++i) {
				UserCredential user = users.get(i);
				
				if (user.UName.equals(UName) && user.Password.equals(UPass)) {
					found = true;
					break;
				}
			}
			
			if (found) {
				JOptionPane.showMessageDialog(mAuthFrame, "Successfully logged in !");
				
				MemberPanel memberPanel = new MemberPanel(UName);
				memberPanel.GUI();
				
				mAuthFrame.dispose();
			} else {
				JOptionPane.showMessageDialog(mAuthFrame, "Unmatched credential !");
			}
		}
	}
	
}
