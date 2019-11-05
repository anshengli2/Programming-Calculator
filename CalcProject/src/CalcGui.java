import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.event.MouseEvent;
import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

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
	private boolean lastIsEquals;
	JButton random;
	int randomCount;
	BufferedImage image;

	public CalcGui() {
		randomCount = 0;
		firstDigit = 0;
		exp = 0;
		bubble = false;
		lastIsEquals = false;

		setLayout(new GridBagLayout());
		gbc.insets = new Insets(2, 2, 2, 2); // Give spacing between each buttons or panels

		random = new JButton();
		random.addActionListener(this);
		random.addMouseListener(this);
		setButton(random, 4, 12, gbc, 0);
		

		// Creating buttons and giving them action listener
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
		setButton(secButton, 0, 8, gbc, 0);
		setButton(modButton, 1, 8, gbc, 0);
		setButton(ceButton, 2, 8, gbc, 0);
		setButton(clearButton, 3, 8, gbc, 0);
		setButton(backButton, 4, 8, gbc, 0);
		leftPButton = new JButton("(");
		leftPButton.addActionListener(this);
		leftPButton.addMouseListener(this);
		setButton(leftPButton, 0, 12, gbc, 0);
		rightPButton = new JButton(")");
		rightPButton.addActionListener(this);
		rightPButton.addMouseListener(this);
		setButton(rightPButton, 1, 12, gbc, 0);
		negateButton = new JButton("+/-");
		negateButton.addActionListener(this);
		negateButton.addMouseListener(this);
		setButton(negateButton, 2, 12, gbc, 0);
		for (int i = 8; i < 13; i++) {
			if (i == 8) {
				divButton = new JButton("\u00F7");
				divButton.addActionListener(this);
				divButton.addMouseListener(this);
				setButton(divButton, 5, 8, gbc, 0);
			} else if (i == 9) {
				multButton = new JButton("\u00D7");
				multButton.addActionListener(this);
				multButton.addMouseListener(this);
				setButton(multButton, 5, 9, gbc, 0);
			} else if (i == 10) {
				subButton = new JButton("-");
				subButton.addActionListener(this);
				subButton.addMouseListener(this);
				setButton(subButton, 5, 10, gbc, 0);
			} else if (i == 11) {
				addButton = new JButton("+");
				addButton.addActionListener(this);
				addButton.addMouseListener(this);
				setButton(addButton, 5, 11, gbc, 0);
			} else if (i == 12) {
				equalsButton = new JButton("=");
				equalsButton.addActionListener(this);
				equalsButton.addMouseListener(this);
				setButton(equalsButton, 5, 12, gbc, 0);
			}
		}
		int col = 0;
		int row = 9;
		char c = 65;
		for (int i = 0; i < 6; i++) {
			if (col == 2) {
				col = 0;
				row++;
			}
			alphabetButtons[i] = new JButton(Character.toString(c));
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
		// Starting with DEC so hide the alphabets
		hideLetters(alphabetButtons, 1);

		col = 2;
		row = 11;
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
				setButton(numButtons[0], 3, 12, gbc, 0);
			}
		}

		hexLabel = new JButton("Hex");
		decLabel = new JButton("Dec");
		octLabel = new JButton("Oct");
		binLabel = new JButton("Bin");
		hexLabel.addActionListener(this);
		hexLabel.addMouseListener(this);
		decLabel.addActionListener(this);
		decLabel.addMouseListener(this);
		octLabel.addActionListener(this);
		octLabel.addMouseListener(this);
		binLabel.addActionListener(this);
		binLabel.addMouseListener(this);
		setButton(hexLabel, 0, 3, gbc, 1);
		setButton(decLabel, 0, 4, gbc, 1);
		setButton(octLabel, 0, 5, gbc, 1);
		setButton(binLabel, 0, 6, gbc, 1);

		clickedLabel(hexLabel, decLabel, octLabel, binLabel, 2);

		calcField = new JLabel("", SwingConstants.RIGHT);
		inputField = new JLabel("0", SwingConstants.RIGHT);
		hexField = new JLabel("0", SwingConstants.LEFT);
		decField = new JLabel("0", SwingConstants.LEFT);
		octField = new JLabel("0", SwingConstants.LEFT);
		binField = new JLabel("0", SwingConstants.LEFT);
		inputField.setBorder(new MatteBorder(0, 0, 4, 0, Color.WHITE));
		setLabel(calcField, 0, 1, gbc, 6, 15);
		setLabel(inputField, 0, 2, gbc, 6, 35);
		setLabel(hexField, 1, 3, gbc, 5, 20);
		setLabel(decField, 1, 4, gbc, 5, 20);
		setLabel(octField, 1, 5, gbc, 5, 20);
		setLabel(binField, 1, 6, gbc, 5, 20);

		// First background image
		loadImage("/images/bg.jpg");
		changeModeImage("/images/mode2.png");
	}

	// Paint the background
	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, this);
	}
	
	//Change button 
	private void changeModeImage(String s) {
		try {
			Image img = ImageIO.read(getClass().getResource(s));
			random.setIcon(new ImageIcon(img));
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	// Loading the image from resources
	private void loadImage(String s) {
		try {
			image = ImageIO.read(CalcGui.class.getResource(s));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Creating the labels with set values
	public void setLabel(JLabel label, int x, int y, GridBagConstraints gbc, int width, int font) {
		label.setPreferredSize(new Dimension(55, 55));
		label.setFont(new Font("Kai", Font.BOLD, font));
		label.setForeground(Color.white);
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = width;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		add(label, gbc);
	}

	// Creating the buttons with set values
	public void setButton(JButton b, int x, int y, GridBagConstraints gbc, int n) {
		Color c = new Color(255, 255, 255);
		b.setPreferredSize(new Dimension(55, 55));
		b.setFont(new Font("Kai", Font.BOLD, 18));
		/****/
		b.setOpaque(false);
		b.setContentAreaFilled(false);
		/*****/
		if (n == 1) {
			b.setForeground(c);
			b.setBorder(null);
		} else {
			b.setForeground(c);
			b.setBorder(new LineBorder(c));
		}
		b.setFocusPainted(false);
		gbc.gridx = x;
		gbc.gridy = y;
		add(b, gbc);
	}

	@Override // Actions when buttons are clicked
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		try {
			if (e.getSource() == numButtons[0] || e.getSource() == numButtons[1] || e.getSource() == numButtons[2]
					|| e.getSource() == numButtons[3] || e.getSource() == numButtons[4]
					|| e.getSource() == numButtons[5] || e.getSource() == numButtons[6]
					|| e.getSource() == numButtons[7] || e.getSource() == numButtons[8]
					|| e.getSource() == numButtons[9]) {
				// Loop to see which button was clicked
				for (int i = 0; i < 10; i++) {
					if (c.getInput().length() < 17) { // Not letting the numbers get too big
						if (e.getSource() == numButtons[i]) {
							if (c.getDec() || c.getHex()) { // Only DEC and HEX has access to all digits
								getDigits(numButtons, i);
							} else if (c.getOct()) { // OCT is restricted to 0-7
								if (!numButtons[i].getText().contentEquals("8")
										|| !numButtons[i].getText().contentEquals("9")) {
									getDigits(numButtons, i);
								}
							} else if (c.getBin()) { // BIN restricted to 1 and 0 ONLY
								if (numButtons[i].getText().contentEquals("1")
										|| numButtons[i].getText().contentEquals("0"))
									getDigits(numButtons, i);
							}
							// Update the input field and the input field of other bases
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
						if (c.getInput().length() < 17) { // Making sure not to exceed the limit of spaces
							if (e.getSource() == alphabetButtons[i]) {
								if (c.getInput().contentEquals("0")) { // Not allowing 0 to be a leading value
									c.setInput("");
									c.storeInput(alphabetButtons[i].getText());
									firstDigit++;
								} else {
									c.storeInput(alphabetButtons[i].getText());
									firstDigit++;
								}
							}
						}
					}
					inputField.setText(c.getInput());
					updateConversion(c.getInput());
				}
			}

			if (e.getSource() == clearButton) {
				clear();
			} else if (e.getSource() == ceButton) {
				c.setInput("0");
				updateConversion(c.getInput());
				firstDigit = 0;
				inputField.setText("0");
			} else if (e.getSource() == backButton && !lastIsEquals) {
				// Take the current input string and having one less
				String tempStr = c.getInput().substring(0, c.getInput().length() - 1);
				// Lowest value possible so the current input is 0
				if (tempStr.contentEquals("")) {
					c.setInput("0");
					firstDigit = 0;
				} else {
					c.setInput(tempStr);
				}
				updateConversion(c.getInput());
				inputField.setText(c.getInput());
			} else if (e.getSource() == negateButton && !(c.getInput().isEmpty() || c.getInput().contentEquals("0"))) {
				c.performCalc("+/-");
				inputField.setText(c.getInput());
				updateConversion(c.getInput());
			} else if (e.getSource() == leftPButton && bubble == false) {///////////////////////////////////
				c.storeCalc("(");
				storeCalc("(", "");
				displayCalc();
				bubble = true;
			} else if (e.getSource() == rightPButton && bubble == true) {
				storeCalc("input", "");
				c.setEquals(true);
				c.performCalc("=");
				c.setEquals(false);
				inputField.setText(c.getResult());
				storeCalc(")", "");
				displayCalc();

			}

			if (e.getSource() == equalsButton) {
				equals();
			} else if (e.getSource() == addButton) {
				if (!c.getOperator().contentEquals(addButton.getText()) && c.getInput().isEmpty()) {
					switchOperator("+");
				} else {
					doMath("+");
				}
				exp++;
				firstDigit = 0;
			} else if (e.getSource() == divButton) {
				if (!c.getOperator().contentEquals(divButton.getText()) && c.getInput().isEmpty()) {
					switchOperator("\u00F7");
				} else {
					doMath("\u00F7");
				}
				exp++;
				firstDigit = 0;
			} else if (e.getSource() == multButton) {
				if (!c.getOperator().contentEquals(multButton.getText()) && c.getInput().isEmpty()) {
					switchOperator("\u00D7");
				} else {
					doMath("\u00D7");
				}
				exp++;
				firstDigit = 0;
			} else if (e.getSource() == subButton) {
				if (!c.getOperator().contentEquals(subButton.getText()) && c.getInput().isEmpty()) {
					switchOperator("-");
				} else {
					doMath("-");
				}
				exp++;
				firstDigit = 0;
			} else if (e.getSource() == modButton) {
				if (!c.getOperator().contentEquals(modButton.getText()) && c.getInput().isEmpty()) {
					switchOperator("Mod");
				} else {
					doMath("Mod");
				}
				exp++;
				firstDigit = 0;
			}

			// Use the other bases
			if (e.getSource() == hexLabel && !c.getHex()) {
				clickedLabel(hexLabel, decLabel, octLabel, binLabel, 1);
			} else if (e.getSource() == decLabel && !c.getDec()) {
				clickedLabel(hexLabel, decLabel, octLabel, binLabel, 2);
			} else if (e.getSource() == octLabel && !c.getOct()) {
				clickedLabel(hexLabel, decLabel, octLabel, binLabel, 3);
			} else if (e.getSource() == binLabel && !c.getBin()) {
				clickedLabel(hexLabel, decLabel, octLabel, binLabel, 4);
			}

			// Switching between background images
			if (e.getSource() == random) {
				repaint();
				if (randomCount == 0) {
					loadImage("/images/bg2.jpg");
					changeModeImage("/images/mode.png");
					switching(0);
				} else if (randomCount == 1) {
					loadImage("/images/bg.jpg");
					changeModeImage("/images/mode2.png");
					switching(1);
				}
				randomCount++;
				if (randomCount == 2) {
					randomCount = 0;
				}
			}
		} catch (Exception e1) {
			System.out.println("bad");
		}
	}

	public void switching(int val) {
		changeModes(null, numButtons, 1, val);
		changeModes(null, alphabetButtons, 1, val);
		changeModes(secButton, null, 0, val);
		changeModes(modButton, null, 0, val);
		changeModes(ceButton, null, 0, val);
		changeModes(clearButton, null, 0, val);
		changeModes(backButton, null, 0, val);
		changeModes(divButton, null, 0, val);
		changeModes(multButton, null, 0, val);
		changeModes(subButton, null, 0, val);
		changeModes(addButton, null, 0, val);
		changeModes(equalsButton, null, 0, val);
		changeModes(random, null, 0, val);
		changeModes(negateButton, null, 0, val);
		changeModes(rightPButton, null, 0, val);
		changeModes(leftPButton, null, 0, val);
		if (val == 0) {
			randomCount = 1;
		} else {
			randomCount = 0;
		}
		if (c.getHex()) {
			hideLetters(alphabetButtons, 0);
			hideNumbers(numButtons, 0, 0, 0);
		} else if (c.getDec()) {
			hideLetters(alphabetButtons, 1);
			hideNumbers(numButtons, 0, 0, 0);
		} else if (c.getOct()) {
			hideLetters(alphabetButtons, 1);
			hideNumbers(numButtons, 1, 1, 0);
		} else if (c.getBin()) {
			hideLetters(alphabetButtons, 1);
			hideNumbers(numButtons, 1, 0, 1);
		}
		if (val == 0) {
			randomCount = 0;
		} else {
			randomCount = 1;
		}
	}

	// Reset every values
	public void clear() {
		c.setCalc("");
		c.setInput("0");
		c.setOperator("");
		c.setPrevInput("0");
		c.setResult("");
		c.setEquals(false);
		bubble = false;
		lastIsEquals = false;
		firstDigit = 0;
		exp = 0;
		calcField.setText("");
		inputField.setText("0");
		hexField.setText("0");
		decField.setText("0");
		binField.setText("0");
		octField.setText("0");
		storeCalc("", "");
	}

	// Store each calculation individually
	public void storeCalc(String input, String op) {
		if (input.contentEquals("input") && op.isEmpty()) {
			c.storeCalc(hexField.getText(), 16);
			c.storeCalc(decField.getText(), 10);
			c.storeCalc(octField.getText(), 8);
			c.storeCalc(binField.getText(), 2);
		} else if (input.isEmpty() && !op.isEmpty()) {
			c.storeCalc(op, 16);
			c.storeCalc(op, 10);
			c.storeCalc(op, 8);
			c.storeCalc(op, 2);
		} else if (input.contentEquals("(")) {
			c.storeCalc("(", 16);
			c.storeCalc("(", 10);
			c.storeCalc("(", 8);
			c.storeCalc("(", 2);
		} else if (input.contentEquals(")")) {
			c.storeCalc(")", 16);
			c.storeCalc(")", 10);
			c.storeCalc(")", 8);
			c.storeCalc(")", 2);
		} else {
			c.setCalcHex("");
			c.setCalcDec("");
			c.setCalcOct("");
			c.setCalcBin("");
		}

	}

	// Display the current base calculation
	public void displayCalc() {
		if (c.getHex()) {
			calcField.setText(c.getCalcHex());
		} else if (c.getDec()) {
			calcField.setText(c.getCalcDec());
		} else if (c.getOct()) {
			calcField.setText(c.getCalcOct());
		} else if (c.getBin()) {
			calcField.setText(c.getCalcBin());
		}
	}

	public void changeModes(JButton bt, JButton bts[], int val, int mode) {
		Color c;
		if (mode == 0) {
			c = new Color(0, 0, 0);
		} else {
			c = new Color(255, 255, 255);
		}

		if (val == 0) {
			bt.setForeground(c);
			bt.setBorder(BorderFactory.createLineBorder(c));
		} else {
			int temp = bts.length;
			for (int i = 0; i < temp; i++) {
				bts[i].setForeground(c);
				bts[i].setBorder(BorderFactory.createLineBorder(c));
			}
		}
		hexField.setForeground(c);
		decField.setForeground(c);
		octField.setForeground(c);
		binField.setForeground(c);
		hexLabel.setForeground(c);
		decLabel.setForeground(c);
		octLabel.setForeground(c);
		binLabel.setForeground(c);
		inputField.setBorder(new MatteBorder(0, 0, 4, 0, c));
		inputField.setForeground(c);
		calcField.setForeground(c);
	}

	// Keep the base fields updated as the input value changes
	public void updateConversion(String s) {
		try {
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
		} catch (Exception e) {
		}
	}

	// Add the digits into the input field
	public void getDigits(JButton b[], int i) {
		// The first value can't be 0 and if entered 0, it will remain 0
		if (b[i].getText().contentEquals("0") && firstDigit == 0) {
			c.setInput("0");
		}
		// If the first value is not zero and it is the first digit entered
		else if (!b[i].getText().contentEquals("0") && firstDigit == 0) {
			c.setInput("");
			c.storeInput(b[i].getText());
			firstDigit++;
		}
		// For every digits after the first non-zero
		else {
			c.storeInput(b[i].getText());
		}
	}

	// Changing between bases
	public void clickedLabel(JButton h, JButton d, JButton o, JButton b, int labelNum) {
		// 1 for HEX, 2 for DEC, 3 for OCT, and 4 for BIN
		if (labelNum == 1) {
			// Set the border on the new active base
			h.setBorder(BorderFactory.createBevelBorder(0, Color.black, Color.white));
			d.setBorder(null);
			o.setBorder(null);
			b.setBorder(null);
			try {
				if (!calcField.getText().isEmpty()) {
					equals();
					String temp = c.getResult();
					clear();
					if (c.getDec())
						c.setInput(c.getConversion(temp, "dec", "hex"));
					else if (c.getOct())
						c.setInput(c.getConversion(temp, "oct", "hex"));
					else if (c.getBin())
						c.setInput(c.getConversion(temp, "bin", "hex"));
					inputField.setText(c.getInput());
				} else if (!c.getInput().isEmpty()) {
					c.setInput(hexField.getText());
					String temp2 = c.getInput();
					clear();
					c.setInput(temp2);
					inputField.setText(c.getInput());
				}

			} catch (Exception e2) {
			}
			c.setHex(true);
			c.setDec(false);
			c.setOct(false);
			c.setBin(false);
			hideLetters(alphabetButtons, 0);
			hideNumbers(numButtons, 0, 0, 0);
			updateConversion(c.getInput());
		} else if (labelNum == 2) {
			h.setBorder(null);
			d.setBorder(BorderFactory.createBevelBorder(0, Color.black, Color.white));
			o.setBorder(null);
			b.setBorder(null);
			try {
				if (!calcField.getText().isEmpty()) {
					equals();
					String temp = c.getResult();
					clear();
					if (c.getHex())
						c.setInput(c.getConversion(temp, "hex", "dec"));
					else if (c.getOct())
						c.setInput(c.getConversion(temp, "oct", "dec"));
					else if (c.getBin())
						c.setInput(c.getConversion(temp, "bin", "dec"));
					inputField.setText(c.getInput());
				} else if (!c.getInput().isEmpty()) {
					c.setInput(decField.getText());
					String temp2 = c.getInput();
					clear();
					c.setInput(temp2);
					inputField.setText(c.getInput());
				}

			} catch (Exception e2) {
			}
			c.setHex(false);
			c.setDec(true);
			c.setOct(false);
			c.setBin(false);
			hideLetters(alphabetButtons, 1);
			hideNumbers(numButtons, 0, 0, 0);
			updateConversion(c.getInput());
		} else if (labelNum == 3) {
			h.setBorder(null);
			d.setBorder(null);
			o.setBorder(BorderFactory.createBevelBorder(0, Color.black, Color.white));
			b.setBorder(null);
			try {
				if (!calcField.getText().isEmpty()) {
					equals();
					String temp = c.getResult();
					clear();
					if (c.getDec())
						c.setInput(c.getConversion(temp, "dec", "oct"));
					else if (c.getHex())
						c.setInput(c.getConversion(temp, "hex", "oct"));
					else if (c.getBin())
						c.setInput(c.getConversion(temp, "bin", "oct"));
					inputField.setText(c.getInput());
				} else if (!c.getInput().isEmpty()) {
					c.setInput(octField.getText());
					String temp2 = c.getInput();
					clear();
					c.setInput(temp2);
					inputField.setText(c.getInput());
				}
			} catch (Exception e2) {
			}
			c.setHex(false);
			c.setDec(false);
			c.setOct(true);
			c.setBin(false);
			hideLetters(alphabetButtons, 1);
			hideNumbers(numButtons, 1, 1, 0);
			updateConversion(c.getInput());
		} else if (labelNum == 4) {
			h.setBorder(null);
			d.setBorder(null);
			o.setBorder(null);
			b.setBorder(BorderFactory.createBevelBorder(0, Color.black, Color.white));
			try {
				if (!calcField.getText().isEmpty()) {
					equals();
					String temp = c.getResult();
					clear();
					if (c.getDec())
						c.setInput(c.getConversion(temp, "dec", "bin"));
					else if (c.getOct())
						c.setInput(c.getConversion(temp, "oct", "bin"));
					else if (c.getHex())
						c.setInput(c.getConversion(temp, "hex", "bin"));
					inputField.setText(c.getInput());
				} else if (!c.getInput().isEmpty()) {
					c.setInput(binField.getText());
					String temp2 = c.getInput();
					clear();
					c.setInput(temp2);
					inputField.setText(c.getInput());
				}

			} catch (Exception e2) {
			}
			c.setHex(false);
			c.setDec(false);
			c.setOct(false);
			c.setBin(true);
			hideLetters(alphabetButtons, 1);
			hideNumbers(numButtons, 1, 0, 1);
			updateConversion(c.getInput());
		}

	}

	// Hide the letters if it is not on HEX
	public void hideLetters(JButton b[], int value) {
		Color c1;
		if (randomCount == 1) {
			c1 = new Color(0, 0, 0);
		} else {
			c1 = new Color(255, 255, 255);
		}
		Color c2 = new Color(153, 153, 153);
		// Show the alphabet
		if (value == 0) {
			for (int i = 0; i < 6; i++) {
				b[i].setForeground(c1);
				b[i].setBorder(new LineBorder(c1));
			}
		}
		// Hide the alphabet
		else {
			for (int i = 0; i < 6; i++) {
				b[i].setForeground(c2);
			}
		}
	}

	// Hide the digits depending on the base
	public void hideNumbers(JButton b[], int value, int oct, int bin) {
		Color c1;
		if (randomCount == 1) {
			c1 = new Color(0, 0, 0);
		} else {
			c1 = new Color(255, 255, 255);
		}
		Color c2 = new Color(153, 153, 153);
		// Everything is visible
		if (value == 0 && oct == 0 && bin == 0) {
			for (int i = 0; i < 10; i++) {
				b[i].setForeground(c1);
				b[i].setBorder(new LineBorder(c1));
			}
		}
		// 8 and 9 are NOT visible
		else if (value == 1 && oct == 1) {
			for (int i = 0; i < 10; i++) {
				if (i < 8) {
					b[i].setForeground(c1);
					b[i].setBorder(new LineBorder(c1));
				} else {
					b[i].setForeground(c2);
				}
			}
		}
		// Only 1 and 0 visible
		else if (value == 1 && bin == 1) {
			for (int i = 0; i < 10; i++) {
				if (i < 2) {
					b[i].setForeground(c1);
					b[i].setBorder(new LineBorder(c1));
				} else {
					b[i].setForeground(c2);
				}
			}
		}
	}

	// Allowing the user to switch between operators
	public void switchOperator(String s) {
		if (c.getOperator().contentEquals("Mod")) {
			c.setCalc(c.getCalc().substring(0, c.getCalc().length() - 3));
			c.setCalcHex(c.getCalcHex().substring(0, c.getCalcHex().length() - 3));
			c.setCalcDec(c.getCalcDec().substring(0, c.getCalcDec().length() - 3));
			c.setCalcOct(c.getCalcOct().substring(0, c.getCalcOct().length() - 3));
			c.setCalcBin(c.getCalcBin().substring(0, c.getCalcBin().length() - 3));
		} else {
			c.setCalc(c.getCalc().substring(0, c.getCalc().length() - 1));
			c.setCalcHex(c.getCalcHex().substring(0, c.getCalcHex().length() - 1));
			c.setCalcDec(c.getCalcDec().substring(0, c.getCalcDec().length() - 1));
			c.setCalcOct(c.getCalcOct().substring(0, c.getCalcOct().length() - 1));
			c.setCalcBin(c.getCalcBin().substring(0, c.getCalcBin().length() - 1));
		}
		c.setOperator(s);
		c.storeCalc(c.getOperator());
		storeCalc("", c.getOperator());
		displayCalc();
	}

	// Main method of doing the calculations
	public void doMath(String op) {
		// If equals was the last enter and the user wants to continue operation on the
		// current value
		// Then the result is set as the input value
		if (lastIsEquals) {
			c.setInput(c.getResult());
		}
		// Equals with set the input as the result
		if (c.getEquals()) {
			c.setInput(c.getResult());
			c.setPrevInput("0");
		}
		// First value and an operator has not been entered yet
		if (exp == 0) {
			c.setOperator(op);
			c.storeCalc(c.getInput());
			storeCalc("input", "");
			c.storeCalc(op);
			storeCalc("", op);
			c.setPrevInput(c.getInput());
			c.setInput("");
		} // After the operator has been entered
		else {
			c.performCalc(c.getOperator());
			c.setOperator(op);
			int temp = c.getCalc().length() - 1;
			if (!bubble && !c.getCalc().substring(temp).isEmpty()) {
				c.storeCalc(c.getInput());
				storeCalc("input", "");
			} else if (bubble && c.getCalc().substring(temp - 1).contentEquals(c.getOperator())) {
				System.out.println("here");
				c.storeCalc(c.getInput());
				storeCalc("input", "");
			}
			c.storeCalc(op);
			storeCalc("", op);
			bubble = false;

			c.setPrevInput(c.getResult());
			c.setInput("");
			inputField.setText(c.getResult());
			updateConversion(c.getResult());
		}
		lastIsEquals = false;
		displayCalc();
	}

	// If the equals sign is continuously pressed
	public void equals() {
		c.setEquals(true);
		c.performCalc("="); // Perform the operation
		c.setCalc(""); // Reset the calcField
		calcField.setText("");
		inputField.setText(c.getResult()); // Show the result in the inputField
		updateConversion(c.getResult()); // Update the other bases
		exp = 0; // Resetting values
		firstDigit = 0;
		c.setEquals(false);
		lastIsEquals = true;
		storeCalc("", "");
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
		} else if (e.getSource() == random) {
			random.setBorder(BorderFactory.createLineBorder(c, 3));
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		Color c;
		if (randomCount == 0) {
			c = new Color(255, 255, 255);
		} else {
			c = new Color(0, 0, 0);
		}
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
		} else if (e.getSource() == random) {
			random.setBorder(BorderFactory.createLineBorder(c));
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
