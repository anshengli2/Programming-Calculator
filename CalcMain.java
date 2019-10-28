import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;

public class CalcMain {
	public static void main(String[] args) {
		JPanel p = new CalcGui();
		JFrame jf = new JFrame();

		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setTitle("Calculator");
		jf.setSize(600, 1000);
		jf.setResizable(false);
		jf.setVisible(true);
		jf.add(p);
	}
}
