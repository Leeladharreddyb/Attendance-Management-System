package Test;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Faculty_after_login {
	public Faculty_after_login(Frame f, String s){
		f.removeAll();
		f.repaint();
		Font font = new Font("Times new roman", Font.PLAIN, 15);
		Button b1 = new Button("Logout");
		b1.setFont(font);
		b1.setBounds(400,400,50,30);
		f.add(b1);
		b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Login_class(f);
            }
        });
		Button b2 = new Button("Mark Attendance");
		b2.setFont(new Font("Times new roman", Font.PLAIN, 20));
		b2.setBounds(200,250,200,35);
		b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Marking_Attendance();
            }
        });
		f.add(b2);
	}
}
