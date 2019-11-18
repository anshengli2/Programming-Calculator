import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.*;

public class CalcMain {
	public static void main(String[] args) {
		JPanel p = new CalcGui();
		JFrame jf = new JFrame();

		p.setMinimumSize(new Dimension(400, 750));
		p.setMaximumSize(new Dimension(400, 750));
		p.setPreferredSize(new Dimension(400, 750));
		
		jf.setTitle("Calculator");
		jf.setIconImage(Toolkit.getDefaultToolkit().getImage(CalcMain.class.getResource("/images/logo.png")));
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.add(p);
		jf.setSize(400, 750);
		jf.setResizable(false);
		jf.setVisible(true);
		jf.setLocationRelativeTo (null);
	}
}
