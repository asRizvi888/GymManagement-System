package GymManagement;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class HomePage extends JFrame implements ActionListener{
	
    private ImageIcon icon;
	Font font = new Font("SANS_SERIF",Font.BOLD,20);
    
    Container container = getContentPane();
	
	JLabel welcome = new JLabel("Welcome to APU Gym Management System");
	
	// HomePage options
	JButton btn1 = new JButton("Add Member");
	JButton btn2 = new JButton("Add Trainer");
	JButton btn3 = new JButton("Dashboard");
	JButton btn4 = new JButton("Sign Out");
	
	// Panels
	AddMember addMember = new AddMember();
	AddTrainer addTrainer = new AddTrainer();

	public void btnConfig (JButton btn, int h) {
		btn.setBounds(75, h * 125, 200, 50);
		btn.setBackground(Color.WHITE);
		btn.setForeground(new Color(5, 65, 90));
		btn.setFocusPainted(false);
		btn.setFont(font);
	}
	
	public void initComponent(){
		welcome.setVisible(true);
		welcome.setBounds(400, 120, 800, 400);
		welcome.setFont(new Font("SANS_SHERIF", Font.BOLD, 36));
		welcome.setForeground(Color.WHITE);
		
		btnConfig(btn1, 1);
		btnConfig(btn2, 2);
		btnConfig(btn3, 3);
		btnConfig(btn4, 4);
	}
	
	public void addComponent () {
		initComponent();
		
		container.setLayout(null);
		
		container.add(welcome);
		container.add(btn1);
		container.add(btn2);
		container.add(btn3);
		container.add(btn4);
		
		container.add(addMember);
		container.add(addTrainer);
	}
	
	public void btnActions () {
		btn1.addActionListener(this);
		btn2.addActionListener(this);
		btn3.addActionListener(this);
		btn4.addActionListener(this);
	}
	
    public HomePage() {
        image();
		addComponent();
		btnActions();
	}
    
    public void image () {
        icon = new ImageIcon(getClass().getResource("gym.jpg"));
        this.setIconImage(icon.getImage());
    }
		
	@Override
	public void actionPerformed (ActionEvent e) {
		
		if (e.getSource() == btn1) {			
			addMember.setVisible(!addMember.isVisible());
			addTrainer.setVisible(false);
			welcome.setVisible(false);
		}
		
		if (e.getSource() == btn2) {	
			addMember.setVisible(false);		
			addTrainer.setVisible(!addTrainer.isVisible());
			welcome.setVisible(false);
		}
		
		if (e.getSource() == btn3) {
			Dashboard dashboard = new Dashboard();
			container.add(dashboard);
			addMember.setVisible(false);
			addTrainer.setVisible(false);	
			welcome.setVisible(false);
			dashboard.setVisible(!(dashboard.isVisible()));
		}
		
		if (e.getSource() == btn4) {
			MyClass.Login();
			this.dispose();
		}
	}

    public static void Home () {   
		
        HomePage frame = new HomePage();

        frame.setVisible(true);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(5, 65, 90));
        frame.setSize(1280, 720);
        
        frame.setTitle("GYM Management Software");
        frame.setResizable(false);	
		
    }
}
