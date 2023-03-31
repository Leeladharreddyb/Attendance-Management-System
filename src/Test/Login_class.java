package Test;
import java.awt.*;
import java.awt.event.*;

public class Login_class {
	public Login_class(Frame f){
		f.removeAll();
		f.repaint();
		Button b1 = new Button("Login as Faculty");
        Button b2 = new Button("Login as Student");
        Button b3 = new Button("Quit");
        b1.setBounds(200,200,200,35);
        b2.setBounds(200,250,200,35);
        b3.setBounds(200,300,200,35);
        Font font = new Font("Arial", Font.PLAIN, 20);
        b1.setFont(font);
        b2.setFont(font);
        b3.setFont(font);
        f.add(b3);
        f.add(b2);
        f.add(b1);
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Login_Faculty(f);
            }
        });
        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Login_Student(f);
            }
        });
        b3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                f.dispose(); 
            }
        });
	}
}



