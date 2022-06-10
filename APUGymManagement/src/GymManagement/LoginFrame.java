package GymManagement;
        
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame implements ActionListener {

    Container container = getContentPane();
    
    JLabel Banner = new JLabel("Admin LOGIN");
    
    JLabel userLabel = new JLabel("USERNAME");
    JLabel passwordLabel = new JLabel("PASSWORD");
    JTextField userTextField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JButton loginButton = new JButton("LOGIN");
    JButton resetButton = new JButton("RESET");
	JButton memberButton = new JButton("Continue as Member");
	JButton trainerButton = new JButton("Continue as Trainer");
    JCheckBox showPassword = new JCheckBox("Show Password");
    
    public LoginFrame() {
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
    }

    public void setLayoutManager() {
        container.setLayout(null);
    }

    public void setLocationAndSize() {
        
        Banner.setBounds(400, 75, 300, 30);
        Banner.setForeground(Color.WHITE);
        Banner.setFont(new Font("SANS_SERIF",Font.BOLD,36));
        
        userLabel.setBounds(400, 150, 200, 30);
        userLabel.setForeground(Color.WHITE);
        
        passwordLabel.setBounds(400, 220, 100, 30);
        passwordLabel.setForeground(Color.WHITE);
        
        userTextField.setBounds(550, 150, 250, 30);
        passwordField.setBounds(550, 220, 250, 30);
        
        showPassword.setBounds(680, 265, 120, 30);
        
        loginButton.setBounds(400, 350, 100, 30);
        resetButton.setBounds(700, 350, 100, 30);
		
		memberButton.setBounds(400, 450, 400, 50);
		memberButton.setBackground(Color.white);
		memberButton.setForeground(new Color(5, 65, 90));
		memberButton.setFocusPainted(false);
		memberButton.setFont(new Font("SANS_SERIF",Font.BOLD,20));
		memberButton.setBorder(null);
		
		trainerButton.setBounds(400, 550, 400, 50);
		trainerButton.setBackground(Color.white);
		trainerButton.setForeground(new Color(5, 65, 90));
		trainerButton.setFocusPainted(false);
		trainerButton.setFont(new Font("SANS_SERIF",Font.BOLD,20));
		trainerButton.setBorder(null);
		
    }

    public void addComponentsToContainer() {
        container.add(Banner);
        container.add(userLabel);
        container.add(passwordLabel);
        container.add(userTextField);
        container.add(passwordField);
        container.add(showPassword);
        container.add(loginButton);
        container.add(resetButton);
		container.add(memberButton);
		container.add(trainerButton);
    }

    public void addActionEvent() {
        loginButton.addActionListener(this);
        resetButton.addActionListener(this);
        showPassword.addActionListener(this);
		memberButton.addActionListener(this);
		trainerButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Coding Part of LOGIN button
        if (e.getSource() == loginButton) {
            
            String userText;
            String pwdText;
            
            userText = userTextField.getText();
            pwdText = passwordField.getText();
            
            if (userText.equalsIgnoreCase("admin") && pwdText.equalsIgnoreCase("12345"))			{		
                JOptionPane.showMessageDialog(this, "Login Successful!");
                
                HomePage homePage = new HomePage();
		
				homePage.Home();
		
                this.dispose();
                
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Username or Password!");
            }
        }
		
        //Coding Part of RESET button
        if (e.getSource() == resetButton) {
            userTextField.setText("");
            passwordField.setText("");
        }
		
       //Coding Part of showPassword JCheckBox
        if (e.getSource() == showPassword) {
            if (showPassword.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('*');
            }
        }
		
		if (e.getSource() == memberButton) {
			MemberAuth mAuth = new MemberAuth();
			mAuth.GUI();
			
			this.dispose();
		}
		
		if (e.getSource() == trainerButton) {
			TrainerFrame tf = new TrainerFrame();
			tf.GUI();
			
			this.dispose();
		}
    }
}
