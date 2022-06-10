package GymManagement;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
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
public class MemberSignUP implements ActionListener {
	
	JFrame mAuthFrame = new JFrame();
	JPanel panel = new JPanel();
	
	// init component
	JButton SignUPButton = new JButton("Sign UP");
	JLabel Banner = new JLabel("Sign UP as Member");
	JLabel USERNAME = new JLabel("Username");
	JLabel PASSWORD = new JLabel("Password");
	JLabel CONFIRM = new JLabel("Confirm Password");
	JTextField usrTxtFld = new JTextField();
	JPasswordField pwdTxtFld = new JPasswordField();
	JPasswordField confirmField = new JPasswordField();
		
	public MemberSignUP () {
		panel.setLayout(null);
		panel.setBackground(new Color(5, 65, 90));
		
		// component layout
		Banner.setBounds(500, 150, 350, 30);
		Banner.setFont(new Font("SANS_SERIF",Font.BOLD,26));
		Banner.setForeground(Color.WHITE);
		
		USERNAME.setBounds(500, 225, 200, 30);
		USERNAME.setForeground(Color.WHITE);
		
		usrTxtFld.setBounds(500, 250, 300, 30);
		pwdTxtFld.setBounds(500, 350, 300, 30);
		confirmField.setBounds(500, 450, 300, 30);

		PASSWORD.setBounds(500, 325, 200, 30);
		PASSWORD.setForeground(Color.WHITE);

		CONFIRM.setBounds(500, 425, 200, 30);
		CONFIRM.setForeground(Color.WHITE);
		
		SignUPButton.setBounds(500, 550, 300, 50);
		SignUPButton.setBackground(Color.WHITE);
		SignUPButton.setForeground(new Color(5, 65, 90));
		SignUPButton.setFocusPainted(false);
		SignUPButton.setFont(new Font("SANS_SERIF",Font.BOLD,20));
		
		// component did mount
		panel.add(Banner);
		panel.add(USERNAME);
		panel.add(PASSWORD);
		panel.add(usrTxtFld);
		panel.add(pwdTxtFld);
		panel.add(SignUPButton);
		panel.add(CONFIRM);
		panel.add(confirmField);
		
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
		if (e.getSource() == SignUPButton) {
			
			FileParser fileParser = new FileParser();
			
			ArrayList<Member> arr = fileParser.FileToArrayList();
			
			String UName = usrTxtFld.getText();
			String Password = pwdTxtFld.getText();
			String Confirm = confirmField.getText();
			
			boolean found = false;
			
			for (int i=0; i<arr.size(); ++i) {
				Member member = arr.get(i);
				
				if (member.name.equals(UName)) {
					found = true;
					break;
				}
			}
			
			if (found) {
				if (Password.equals(Confirm)) {
					
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
					
					
					try (PrintWriter pw = new PrintWriter("credential.txt")) {
						
						for (int i=0; i<users.size(); ++i) {
							UserCredential existingUser = users.get(i);

							pw.println(existingUser.UName);
							pw.println(existingUser.Password);
							pw.println();
						}

						UserCredential newUser = new UserCredential(UName, Password);

						pw.println(newUser.UName);
						pw.println(newUser.Password);
						pw.println();
						
						JOptionPane.showMessageDialog(mAuthFrame, "Account creation successfull !");
						//System.out.println("Successfull");
						MemberLogIN memberLogIN = new MemberLogIN();
						memberLogIN.GUI();
					
						mAuthFrame.dispose();
						
					} catch (FileNotFoundException ex) {
						Logger.getLogger(MemberSignUP.class.getName()).log(Level.SEVERE, null, ex);
					}

				} else {
					JOptionPane.showMessageDialog(mAuthFrame, "Password didn't match :(");
					
					System.out.println("Unmatched");
				}
				
			} else {
				JOptionPane.showMessageDialog(mAuthFrame, "Member not registered !");
				
				//System.out.println("Unregistered");

			}
		}
	}
	
}
