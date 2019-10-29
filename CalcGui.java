import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class CalcGui extends JPanel implements ActionListener, MouseListener {
	JButton[] numButtons = new JButton[10];
	JButton[] alphabetButtons = new JButton[6];
	JButton divButton, multButton, subButton, addButton, equalsButton;
	JButton rightPButton, leftPButton;
	JButton secButton, modButton, ceButton, clearButton, backButton, negateButton, decimalButton;
	JLabel calcField, inputField, hexField, decField, octField, binField;
	JButton hexLabel, decLabel, octLabel, binLabel;
	GridBagConstraints gbc = new GridBagConstraints();
	Calculations c = new Calculations();
	private int firstDigit;
	private int exp;
	private boolean bubble;
	Image img;

	public CalcGui() {
		img = Toolkit.getDefaultToolkit().createImage("bg.jpg");

		firstDigit = 0;
		exp = 0;
		bubble = false;

		// setBackground(Color.DARK_GRAY);
		setLayout(new GridBagLayout());
		gbc.insets = new Insets(2, 2, 2, 2);

		secButton = new JButton("2nd");
		modButton = new JButton("Mod");
		ceButton = new JButton("CE");
		clearButton = new JButton("C");
		backButton = new JButton("\u00AB");
		secButton.addActionListener(this);
		secButton.addMouseListener(this);
		modButton.addActionListener(this);
		modButton.addMouseListener(this);
		ceButton.addActionListener(this);
		ceButton.addMouseListener(this);
		clearButton.addActionListener(this);
		clearButton.addMouseListener(this);
		backButton.addActionListener(this);
		backButton.addMouseListener(this);
		setButton(secButton, 0, 7, gbc, 0);
		setButton(modButton, 1, 7, gbc, 0);
		setButton(ceButton, 2, 7, gbc, 0);
		setButton(clearButton, 3, 7, gbc, 0);
		setButton(backButton, 4, 7, gbc, 0);

		leftPButton = new JButton("(");
		leftPButton.addActionListener(this);
		leftPButton.addMouseListener(this);
		setButton(leftPButton, 0, 11, gbc, 0);
		rightPButton = new JButton(")");
		rightPButton.addActionListener(this);
		rightPButton.addMouseListener(this);
		setButton(rightPButton, 1, 11, gbc, 0);

		negateButton = new JButton("+/-");
		negateButton.addActionListener(this);
		negateButton.addMouseListener(this);
		setButton(negateButton, 2, 11, gbc, 0);

		for (int i = 8; i < 13; i++) {
			if (i == 8) {
				divButton = new JButton("\u00F7");
				divButton.addActionListener(this);
				divButton.addMouseListener(this);
				setButton(divButton, 5, 7, gbc, 0);
			} else if (i == 9) {
				multButton = new JButton("X");
				multButton.addActionListener(this);
				multButton.addMouseListener(this);
				setButton(multButton, 5, 8, gbc, 0);
			} else if (i == 10) {
				subButton = new JButton("-");
				subButton.addActionListener(this);
				subButton.addMouseListener(this);
				setButton(subButton, 5, 9, gbc, 0);
			} else if (i == 11) {
				addButton = new JButton("+");
				addButton.addActionListener(this);
				addButton.addMouseListener(this);
				setButton(addButton, 5, 10, gbc, 0);
			} else if (i == 12) {
				equalsButton = new JButton("=");
				equalsButton.addActionListener(this);
				equalsButton.addMouseListener(this);
				setButton(equalsButton, 5, 11, gbc, 0);
			}
		}

		int col = 0;
		int row = 8;
		char c = 65;
		for (int i = 0; i < 6; i++) {
			if (col == 2) {
				col = 0;
				row++;
			}
			alphabetButtons[i] = new JButton(Character.toString((char) c));
			add(alphabetButtons[i], gbc);
			if (i >= 0 && i <= 1) {
				setButton(alphabetButtons[i], col, row, gbc, 0);
				c++;
				col++;
			} else if (i >= 2 && i <= 3) {
				setButton(alphabetButtons[i], col, row, gbc, 0);
				c++;
				col++;
			} else {
				setButton(alphabetButtons[i], col, row, gbc, 0);
				c++;
				col++;
			}
			alphabetButtons[i].addActionListener(this);
			alphabetButtons[i].addMouseListener(this);
		}
		hideLetters(alphabetButtons, 1);

		col = 2;
		row = 10;
		for (int i = 0; i < 10; i++) {
			if (col == 5) {
				col = 2;
				row--;
			}
			numButtons[i] = new JButton(String.valueOf(i));
			numButtons[i].addActionListener(this);
			numButtons[i].addMouseListener(this);
			if (i >= 7 && i <= 9) {
				setButton(numButtons[i], col, row, gbc, 0);
				col++;
			} else if (i >= 4 && i <= 6) {
				setButton(numButtons[i], col, row, gbc, 0);
				col++;
			} else if (i >= 1 && i <= 3) {
				setButton(numButtons[i], col, row, gbc, 0);
				col++;
			} else {
				setButton(numButtons[0], 3, 11, gbc, 0);
			}
		}

		hexLabel = new JButton("Hex ");
		decLabel = new JButton("Dec ");
		octLabel = new JButton("Oct ");
		binLabel = new JButton("Bin ");
		hexLabel.addActionListener(this);
		hexLabel.addMouseListener(this);
		decLabel.addActionListener(this);
		decLabel.addMouseListener(this);
		octLabel.addActionListener(this);
		octLabel.addMouseListener(this);
		binLabel.addActionListener(this);
		binLabel.addMouseListener(this);
		setButton(hexLabel, 0, 2, gbc, 1);
		setButton(decLabel, 0, 3, gbc, 1);
		setButton(octLabel, 0, 4, gbc, 1);
		setButton(binLabel, 0, 5, gbc, 1);

		clickedLabel(hexLabel, decLabel, octLabel, binLabel, 2);

		calcField = new JLabel("", SwingConstants.RIGHT);
		inputField = new JLabel("0", SwingConstants.RIGHT);
		hexField = new JLabel("0", SwingConstants.LEFT);
		decField = new JLabel("0", SwingConstants.LEFT);
		octField = new JLabel("0", SwingConstants.LEFT);
		binField = new JLabel("0", SwingConstants.LEFT);
		inputField.setBorder(BorderFactory.createLineBorder(Color.white, 4));
		setLabel(calcField, 0, 0, gbc, 6, 15);
		setLabel(inputField, 0, 1, gbc, 6, 35);
		setLabel(hexField, 1, 2, gbc, 5, 15);
		setLabel(decField, 1, 3, gbc, 5, 15);
		setLabel(octField, 1, 4, gbc, 5, 15);
		setLabel(binField, 1, 5, gbc, 5, 15);
	}

	public void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, this);
	}

	public void setLabel(JLabel label, int x, int y, GridBagConstraints gbc, int width, int font) {
		label.setPreferredSize(new Dimension(55, 55));
		label.setFont(new Font("Arial", Font.BOLD, font));
		label.setForeground(Color.white);
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = width;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		add(label, gbc);
	}

	public void setButton(JButton b, int x, int y, GridBagConstraints gbc, int n) {
		b.setPreferredSize(new Dimension(55, 55));
		b.setFont(new Font("Arial", Font.BOLD, 18));
		/****/
		b.setOpaque(false);
		b.setContentAreaFilled(false);

		/*****/
		if (n == 1) {
			// b.setBackground(Color.DARK_GRAY);
			b.setForeground(Color.white);
			b.setBorder(null);
		} else {
			// b.setBackground(Color.black);
			b.setForeground(Color.white);
			b.setBorder(new LineBorder(Color.white));
		}
		b.setFocusPainted(false);
		gbc.gridx = x;
		gbc.gridy = y;
		add(b, gbc);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		try {
			if (e.getSource() == numButtons[0] || e.getSource() == numButtons[1] || e.getSource() == numButtons[2]
					|| e.getSource() == numButtons[3] || e.getSource() == numButtons[4]
					|| e.getSource() == numButtons[5] || e.getSource() == numButtons[6]
					|| e.getSource() == numButtons[7] || e.getSource() == numButtons[8]
					|| e.getSource() == numButtons[9]) {
				for (int i = 0; i < 10; i++) {
					if (c.getInput().length() < 17) {
						if (e.getSource() == numButtons[i]) {
							if (c.getDec() || c.getHex()) {
								getDigits(numButtons, i);
							} else if (c.getOct()) {
								if (!numButtons[i].getText().contentEquals("8")
										|| !numButtons[i].getText().contentEquals("8")) {
									getDigits(numButtons, i);
								}
							} else if (c.getBin()) {
								if (numButtons[i].getText().contentEquals("1")
										|| numButtons[i].getText().contentEquals("0"))
									getDigits(numButtons, i);
							}

							inputField.setText(c.getInput());
							updateConversion(c.getInput());
						}
					}
				}
			} else {
				if ((e.getSource() == alphabetButtons[0] || e.getSource() == alphabetButtons[1]
						|| e.getSource() == alphabetButtons[2] || e.getSource() == alphabetButtons[3]
						|| e.getSource() == alphabetButtons[4] || e.getSource() == alphabetButtons[5]
						|| e.getSource() == alphabetButtons[5]) && c.getHex()) {
					for (int i = 0; i < 6; i++) {
						if (c.getInput().length() < 17) {
							if (e.getSource() == alphabetButtons[i]) {
								if (c.getInput().contentEquals("0")) {
									c.setInput("");
									c.storeInput(alphabetButtons[i].getText());
									firstDigit++;
								} else {
									c.storeInput(alphabetButtons[i].getText());
								}
							}
						}
					}
					inputField.setText(c.getInput());
					updateConversion(c.getInput());
				}
			}

			if (e.getSource() == clearButton) {
				c.setCalc("");
				c.setInput("0");
				c.setOperator("");
				c.setPrevInput("0");
				c.setResult("0");
				c.setEquals(false);
				bubble = false;
				firstDigit = 0;
				exp = 0;
				calcField.setText("");
				inputField.setText("0");
				hexField.setText("0");
				decField.setText("0");
				binField.setText("0");
				octField.setText("0");
			} else if (e.getSource() == ceButton) {
				c.setInput("0");
				updateConversion(c.getInput());
				firstDigit = 0;
				inputField.setText("0");
			} else if (e.getSource() == backButton) {
				String tempStr = c.getInput().substring(0, c.getInput().length() - 1);
				if (tempStr.contentEquals("")) {
					c.setInput("0");
					firstDigit = 0;
				} else {
					c.setInput(tempStr);
				}
				updateConversion(c.getInput());
				inputField.setText(c.getInput());
			} else if (e.getSource() == negateButton) {
				c.performCalc("+/-");
				inputField.setText(c.getInput());
			} else if (e.getSource() == leftPButton && bubble == false) {
				c.storeCalc("(");
				calcField.setText(c.getCalc());
				bubble = true;
			} else if (e.getSource() == rightPButton && bubble == true) {
				c.storeCalc(c.getInput());
				c.storeCalc(")");
				equals();
			}
			if (e.getSource() == equalsButton) {
				equals();
				exp = 0;
				firstDigit = 0;
			} else if (e.getSource() == addButton) {
				doMath("+");
				c.setEquals(false);
				exp++;
				firstDigit = 0;
			} else if (e.getSource() == divButton) {
				doMath("/");
				c.setEquals(false);
				exp++;
				firstDigit = 0;
			} else if (e.getSource() == multButton) {
				doMath("*");
				c.setEquals(false);
				exp++;
				firstDigit = 0;
			} else if (e.getSource() == subButton) {
				doMath("-");
				c.setEquals(false);
				exp++;
				firstDigit = 0;
			} else if (e.getSource() == modButton) {
				doMath("%");
				c.setEquals(false);
				exp++;
				firstDigit = 0;
			} else if (e.getSource() == modButton) {
				doMath("%");
				c.setEquals(false);
				exp++;
				firstDigit = 0;
			}

			if (e.getSource() == hexLabel) {
				clickedLabel(hexLabel, decLabel, octLabel, binLabel, 1);
			} else if (e.getSource() == decLabel) {
				clickedLabel(hexLabel, decLabel, octLabel, binLabel, 2);
			} else if (e.getSource() == octLabel) {
				clickedLabel(hexLabel, decLabel, octLabel, binLabel, 3);
			} else if (e.getSource() == binLabel) {
				clickedLabel(hexLabel, decLabel, octLabel, binLabel, 4);
			}
		} catch (Exception e1) {

		}
	}

	public void updateConversion(String s) {
		if (c.getHex()) {
			decField.setText(c.getConversion(s, "hex", "dec"));
			binField.setText(c.getConversion(s, "hex", "bin"));
			octField.setText(c.getConversion(s, "hex", "oct"));
			hexField.setText(s);
		} else if (c.getDec()) {
			binField.setText(c.getConversion(s, "dec", "bin"));
			hexField.setText(c.getConversion(s, "dec", "hex"));
			octField.setText(c.getConversion(s, "dec", "oct"));
			decField.setText(s);
		} else if (c.getOct()) {
			binField.setText(c.getConversion(s, "oct", "bin"));
			hexField.setText(c.getConversion(s, "oct", "hex"));
			decField.setText(c.getConversion(s, "oct", "dec"));
			octField.setText(s);
		} else if (c.getBin()) {
			decField.setText(c.getConversion(s, "bin", "dec"));
			hexField.setText(c.getConversion(s, "bin", "hex"));
			octField.setText(c.getConversion(s, "bin", "oct"));
			binField.setText(s);
		}
	}

	public void getDigits(JButton b[], int i) {
		if (b[i].getText().contentEquals("0") && firstDigit == 0) {
			c.setInput("0");
		} else if (!b[i].getText().contentEquals("0") && firstDigit == 0) {
			c.setInput("");
			c.storeInput(b[i].getText());
			firstDigit++;
		} else {
			c.storeInput(b[i].getText());
		}
	}

	public void clickedLabel(JButton h, JButton d, JButton o, JButton b, int labelNum) {
		if (labelNum == 1) {
			h.setBorder(BorderFactory.createBevelBorder(0, Color.black, Color.white));
			d.setBorder(null);
			o.setBorder(null);
			b.setBorder(null);
			try {
				c.setInput(hexField.getText());
				inputField.setText(c.getInput());
			} catch (Exception e2) {
			}
			c.setHex(true);
			c.setDec(false);
			c.setOct(false);
			c.setBin(false);
			hideLetters(alphabetButtons, 0);
			hideNumbers(numButtons, 0, 0, 0);
		} else if (labelNum == 2) {
			h.setBorder(null);
			d.setBorder(BorderFactory.createBevelBorder(0, Color.black, Color.white));
			o.setBorder(null);
			b.setBorder(null);
			try {
				c.setInput(decField.getText());
				inputField.setText(c.getInput());
			} catch (Exception e2) {
			}
			c.setHex(false);
			c.setDec(true);
			c.setOct(false);
			c.setBin(false);
			hideLetters(alphabetButtons, 1);
			hideNumbers(numButtons, 0, 0, 0);
		} else if (labelNum == 3) {
			h.setBorder(null);
			d.setBorder(null);
			o.setBorder(BorderFactory.createBevelBorder(0, Color.black, Color.white));
			b.setBorder(null);
			try {
				c.setInput(octField.getText());
				inputField.setText(c.getInput());
			} catch (Exception e2) {
			}
			c.setHex(false);
			c.setDec(false);
			c.setOct(true);
			c.setBin(false);
			hideLetters(alphabetButtons, 1);
			hideNumbers(numButtons, 1, 1, 0);
		} else if (labelNum == 4) {
			h.setBorder(null);
			d.setBorder(null);
			o.setBorder(null);
			b.setBorder(BorderFactory.createBevelBorder(0, Color.black, Color.white));
			try {
				c.setInput(binField.getText());
				inputField.setText(c.getInput());
			} catch (Exception e2) {
			}
			c.setHex(false);
			c.setDec(false);
			c.setOct(false);
			c.setBin(true);
			hideLetters(alphabetButtons, 1);
			hideNumbers(numButtons, 1, 0, 1);
		}
	}

	public void hideLetters(JButton b[], int value) {
		if (value == 0) {
			for (int i = 0; i < 6; i++) {
				b[i].setForeground(Color.WHITE);
				b[i].setBorder(new LineBorder(Color.WHITE));
			}
		} else {
			for (int i = 0; i < 6; i++) {
				b[i].setForeground(Color.GRAY);
			}
		}
	}

	public void hideNumbers(JButton b[], int value, int oct, int bin) {
		if (value == 0 && oct == 0 && bin == 0) {
			for (int i = 0; i < 10; i++) {
				b[i].setForeground(Color.WHITE);
				b[i].setBorder(new LineBorder(Color.WHITE));
			}
		} else if (value == 1 && oct == 1) {
			for (int i = 0; i < 10; i++) {
				if (i < 8) {
					b[i].setForeground(Color.WHITE);
					b[i].setBorder(new LineBorder(Color.WHITE));
				} else {
					b[i].setForeground(Color.GRAY);
				}
			}
		} else if (value == 1 && bin == 1) {
			for (int i = 0; i < 10; i++) {
				if (i < 2) {
					b[i].setForeground(Color.WHITE);
					b[i].setBorder(new LineBorder(Color.WHITE));
				} else {
					b[i].setForeground(Color.GRAY);
				}
			}
		}
	}

	public void doMath(String op) {
		if (c.getEquals()) {
			c.setInput(c.getResult());
			c.setPrevInput("0");
		}
		if (exp == 0) {
			c.setOperator(op);
			c.storeCalc(c.getInput());
			c.storeCalc(op);
			c.setPrevInput(c.getInput());
			c.setInput("");
		} else {
			if (c.getBin()) {
				c.performCalc(c.getOperator());
				c.setOperator(op);
				c.storeCalc(c.getInput());
				c.storeCalc(op);
				c.setPrevInput(c.getResult());
				c.setInput("");
				inputField.setText(c.getResult());
				updateConversion(c.getResult());
			} else if (c.getDec()) {
				c.performCalc(c.getOperator());
				c.setOperator(op);
				c.storeCalc(c.getInput());
				c.storeCalc(op);
				c.setPrevInput(c.getResult());
				c.setInput("");
				inputField.setText(c.getResult());
				updateConversion(c.getResult());
			}

		}
		calcField.setText(c.getCalc());
	}

	public void equals() {
		c.performCalc("=");
		c.setCalc("");
		calcField.setText("");
		inputField.setText(c.getResult());
		updateConversion(c.getResult());
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		Color c = new Color(143, 200, 220);
		for (int i = 0; i < 10; i++) {
			if (e.getSource() == numButtons[i]) {
				numButtons[i].setBorder(BorderFactory.createLineBorder(c, 3));
			}
			if (i < 6) {
				if (e.getSource() == alphabetButtons[i]) {
					alphabetButtons[i].setBorder(BorderFactory.createLineBorder(c, 3));
				}
			}
		}
		if (e.getSource() == secButton) {
			secButton.setBorder(BorderFactory.createLineBorder(c, 3));
		} else if (e.getSource() == modButton) {
			modButton.setBorder(BorderFactory.createLineBorder(c, 3));
		} else if (e.getSource() == ceButton) {
			ceButton.setBorder(BorderFactory.createLineBorder(c, 3));
		} else if (e.getSource() == clearButton) {
			clearButton.setBorder(BorderFactory.createLineBorder(c, 3));
		} else if (e.getSource() == addButton) {
			addButton.setBorder(BorderFactory.createLineBorder(c, 3));
		} else if (e.getSource() == multButton) {
			multButton.setBorder(BorderFactory.createLineBorder(c, 3));
		} else if (e.getSource() == subButton) {
			subButton.setBorder(BorderFactory.createLineBorder(c, 3));
		} else if (e.getSource() == addButton) {
			addButton.setBorder(BorderFactory.createLineBorder(c, 3));
		} else if (e.getSource() == negateButton) {
			negateButton.setBorder(BorderFactory.createLineBorder(c, 3));
		} else if (e.getSource() == rightPButton) {
			rightPButton.setBorder(BorderFactory.createLineBorder(c, 3));
		} else if (e.getSource() == leftPButton) {
			leftPButton.setBorder(BorderFactory.createLineBorder(c, 3));
		} else if (e.getSource() == divButton) {
			divButton.setBorder(BorderFactory.createLineBorder(c, 3));
		} else if (e.getSource() == equalsButton) {
			equalsButton.setBorder(BorderFactory.createLineBorder(c, 3));
		} else if (e.getSource() == backButton) {
			backButton.setBorder(BorderFactory.createLineBorder(c, 3));
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		Color c = new Color(255, 255, 255);
		for (int i = 0; i < 10; i++) {
			if (e.getSource() == numButtons[i]) {
				numButtons[i].setBorder(BorderFactory.createLineBorder(c));
			}
			if (i < 6) {
				if (e.getSource() == alphabetButtons[i]) {
					alphabetButtons[i].setBorder(BorderFactory.createLineBorder(c));
				}
			}
		}
		if (e.getSource() == secButton) {
			secButton.setBorder(BorderFactory.createLineBorder(c));
		} else if (e.getSource() == backButton) {
			backButton.setBorder(BorderFactory.createLineBorder(c));
		} else if (e.getSource() == modButton) {
			modButton.setBorder(BorderFactory.createLineBorder(c));
		} else if (e.getSource() == ceButton) {
			ceButton.setBorder(BorderFactory.createLineBorder(c));
		} else if (e.getSource() == divButton) {
			divButton.setBorder(BorderFactory.createLineBorder(c));
		} else if (e.getSource() == equalsButton) {
			equalsButton.setBorder(BorderFactory.createLineBorder(c));
		} else if (e.getSource() == clearButton) {
			clearButton.setBorder(BorderFactory.createLineBorder(c));
		} else if (e.getSource() == addButton) {
			addButton.setBorder(BorderFactory.createLineBorder(c));
		} else if (e.getSource() == multButton) {
			multButton.setBorder(BorderFactory.createLineBorder(c));
		} else if (e.getSource() == subButton) {
			subButton.setBorder(BorderFactory.createLineBorder(c));
		} else if (e.getSource() == addButton) {
			addButton.setBorder(BorderFactory.createLineBorder(c));
		} else if (e.getSource() == negateButton) {
			negateButton.setBorder(BorderFactory.createLineBorder(c));
		} else if (e.getSource() == rightPButton) {
			rightPButton.setBorder(BorderFactory.createLineBorder(c));
		} else if (e.getSource() == leftPButton) {
			leftPButton.setBorder(BorderFactory.createLineBorder(c));
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
