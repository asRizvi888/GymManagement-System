package GymManagement;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 *
 * @author Admin
 */
public class LoginClass extends LoginFrame {
    
    private ImageIcon icon;

    public LoginClass() {
        image();
    }

    public void image () {
        icon = new ImageIcon(getClass().getResource("gym.jpg"));
        this.setIconImage(icon.getImage());
    }
    
    public static void Login () {
                     
        LoginClass frame = new LoginClass();
        
        frame.setVisible(true);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(5, 65, 90));
        frame.setSize(1280, 720);
        
        frame.setTitle("LogIn");
        frame.setResizable(false);
        
    }
}
