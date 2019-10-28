import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class CalcGui extends JPanel implements ActionListener {
	JButton[] numButtons = new JButton[10];
	JButton[] alphabetButtons = new JButton[6];
	JButton divButton, multButton, subButton, addButton, equalsButton;
	JButton rightPButton, leftPButton;
	JButton secButton, modButton, ceButton, clearButton, backButton, negateButton, decimalButton;
	JLabel calcField, inputField, hexField, decField, octField, binField;
	JButton hexLabel, decLabel, octLabel, binLabel;

	JButton random;
	int randomCount;

	GridBagConstraints gbc = new GridBagConstraints();
	Calculations c = new Calculations();
	private int firstDigit;
	private int exp;
	private boolean bubble;

	public CalcGui() {
		randomCount = 0;
		firstDigit = 0;
		exp = 0;
		bubble = false;

		setBackground(Color.DARK_GRAY);
		setLayout(new GridBagLayout());
		gbc.insets = new Insets(2, 2, 2, 2);

		secButton = new JButton("2nd");
		modButton = new JButton("Mod");
		ceButton = new JButton("CE");
		clearButton = new JButton("C");
		backButton = new JButton("\u00AB");
		secButton.addActionListener(this);
		modButton.addActionListener(this);
		ceButton.addActionListener(this);
		clearButton.addActionListener(this);
		backButton.addActionListener(this);
		setButton(secButton, 0, 7, gbc, 0);
		setButton(modButton, 1, 7, gbc, 0);
		setButton(ceButton, 2, 7, gbc, 0);
		setButton(clearButton, 3, 7, gbc, 0);
		setButton(backButton, 4, 7, gbc, 0);

		leftPButton = new JButton("(");
		leftPButton.addActionListener(this);
		setButton(leftPButton, 0, 11, gbc, 0);
		rightPButton = new JButton(")");
		rightPButton.addActionListener(this);
		setButton(rightPButton, 1, 11, gbc, 0);

		negateButton = new JButton("+/-");
		negateButton.addActionListener(this);
		setButton(negateButton, 2, 11, gbc, 0);

		for (int i = 8; i < 13; i++) {
			if (i == 8) {
				divButton = new JButton("\u00F7");
				divButton.addActionListener(this);
				setButton(divButton, 5, 7, gbc, 0);
			} else if (i == 9) {
				multButton = new JButton("X");
				multButton.addActionListener(this);
				setButton(multButton, 5, 8, gbc, 0);
			} else if (i == 10) {
				subButton = new JButton("-");
				subButton.addActionListener(this);
				setButton(subButton, 5, 9, gbc, 0);
			} else if (i == 11) {
				addButton = new JButton("+");
				addButton.addActionListener(this);
				setButton(addButton, 5, 10, gbc, 0);
			} else if (i == 12) {
				equalsButton = new JButton("=");
				equalsButton.addActionListener(this);
				setButton(equalsButton, 5, 11, gbc, 0);
			}
		}
		/***********************************/
		random = new JButton(":)");
		random.addActionListener(this);
		setButton(random, 4, 11, gbc, 0);
		/***************************/
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
		decLabel.addActionListener(this);
		octLabel.addActionListener(this);
		binLabel.addActionListener(this);
		setButton(hexLabel, 0, 2, gbc, 1);
		setButton(decLabel, 0, 3, gbc, 1);
		setButton(octLabel, 0, 4, gbc, 1);
		setButton(binLabel, 0, 5, gbc, 1);

		clickedLabel(hexLabel, decLabel, octLabel, binLabel, 2);

		calcField = new JLabel("", SwingConstants.RIGHT);
		inputField = new JLabel("0", SwingConstants.RIGHT);
		hexField = new JLabel("", SwingConstants.LEFT);
		decField = new JLabel("", SwingConstants.LEFT);
		octField = new JLabel("", SwingConstants.LEFT);
		binField = new JLabel("", SwingConstants.LEFT);
		inputField.setBorder(BorderFactory.createLineBorder(Color.white, 5));
		setLabel(calcField, 0, 0, gbc, 6, 20);
		setLabel(inputField, 0, 1, gbc, 6, 50);
		setLabel(hexField, 1, 2, gbc, 5, 20);
		setLabel(decField, 1, 3, gbc, 5, 20);
		setLabel(octField, 1, 4, gbc, 5, 20);
		setLabel(binField, 1, 5, gbc, 5, 20);
	}

	public void setLabel(JLabel label, int x, int y, GridBagConstraints gbc, int width, int font) {
		label.setPreferredSize(new Dimension(80, 80));
		label.setFont(new Font("Arial", Font.BOLD, font));
		label.setForeground(Color.white);
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = width;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		add(label, gbc);
	}

	public void setButton(JButton b, int x, int y, GridBagConstraints gbc, int n) {
		b.setPreferredSize(new Dimension(80, 80));
		b.setFont(new Font("Arial", Font.BOLD, 20));
		if (n == 1) {
			b.setBackground(Color.DARK_GRAY);
			b.setForeground(Color.white);
			b.setBorder(null);
		} else {
			b.setBackground(Color.black);
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
		if (e.getSource() == numButtons[0] || e.getSource() == numButtons[1] || e.getSource() == numButtons[2]
				|| e.getSource() == numButtons[3] || e.getSource() == numButtons[4] || e.getSource() == numButtons[5]
				|| e.getSource() == numButtons[6] || e.getSource() == numButtons[7] || e.getSource() == numButtons[8]
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
		} else if (e.getSource() == ceButton) {
			c.setInput("0");
			firstDigit = 0;
			inputField.setText("0");
		} else if (e.getSource() == backButton) {
			String tempStr = c.getInput().substring(0, c.getInput().length() - 1);
			if (tempStr.contentEquals("")) {
				c.setInput("0");
			} else {
				c.setInput(tempStr);
			}
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
		/***********************************/
		if (e.getSource() == random) {
			if (randomCount == 0) {
				calcField.setText("Hey, please don't press me again.");
			} else if (randomCount == 1) {
				calcField.setText("Come on please don't do dat!");
				random.setText(":|");
			} else if (randomCount == 1) {
				calcField.setText("i swearrrrrr if you do it one more time");
				random.setText(":<");
			} else if (randomCount == 2) {
				calcField.setText("I'll screammmm");
				random.setText(">:(");
			} else if (randomCount == 3) {
				random.setText(">:o");
				JOptionPane.showMessageDialog(this, "Reeeeeeeeeeeeeeeeeee", "WARNING", JOptionPane.WARNING_MESSAGE);
			} else if (randomCount == 4) {
				calcField.setText("Yea bet you won't click me again");
				random.setText(";)");
			} else if (randomCount == 5) {
				calcField.setText("ok you're actually wasting time so please don't");
				random.setText(":>");
			} else if (randomCount == 6) {
				calcField.setText("are you that bored??");
				random.setText("?");
			} else if (randomCount == 7) {
				calcField.setText("like you should get back to studying or something");
				random.setText("0_0");
			} else if (randomCount == 8) {
				calcField.setText("i actually wasted so much time doing this xd");
				random.setText(":\")");
			} else if (randomCount == 9) {
				JOptionPane.showInputDialog(this, "Please tell me if my calc is good thx :)");
				calcField.setText("I can't read this lol");
				random.setText("1more");
			} else {
				JOptionPane.showMessageDialog(this, "Imma close you off now");
				this.setVisible(false);
			}
			randomCount++;
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
				b[i].setBackground(Color.BLACK);
			}
		} else {
			for (int i = 0; i < 6; i++) {
				b[i].setForeground(Color.LIGHT_GRAY);
				b[i].setBackground(Color.GRAY);
			}
		}
	}

	public void hideNumbers(JButton b[], int value, int oct, int bin) {
		if (value == 0 && oct == 0 && bin == 0) {
			for (int i = 0; i < 10; i++) {
				b[i].setForeground(Color.WHITE);
				b[i].setBackground(Color.BLACK);
			}
		} else if (value == 1 && oct == 1) {
			for (int i = 0; i < 10; i++) {
				if (i < 8) {
					b[i].setForeground(Color.WHITE);
					b[i].setBackground(Color.BLACK);
				} else {
					b[i].setForeground(Color.LIGHT_GRAY);
					b[i].setBackground(Color.GRAY);
				}
			}
		} else if (value == 1 && bin == 1) {
			for (int i = 0; i < 10; i++) {
				if (i < 2) {
					b[i].setForeground(Color.WHITE);
					b[i].setBackground(Color.BLACK);
				} else {
					b[i].setForeground(Color.LIGHT_GRAY);
					b[i].setBackground(Color.GRAY);
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
			c.performCalc(c.getOperator());
			c.setOperator(op);
			c.storeCalc(c.getInput());
			c.storeCalc(op);
			c.setPrevInput(c.getResult());
			c.setInput("");
			inputField.setText(c.getResult());
		}
		calcField.setText(c.getCalc());
	}

	public void equals() {
		c.performCalc("=");
		c.setCalc("");
		calcField.setText("");
		inputField.setText(c.getResult());
	}
}
