public class Calculations {

	private String input;
	private String prevInput;
	private long result;
	private String stringResult;
	private String calc;
	private String calcHex;
	private String calcDec;
	private String calcOct;
	private String calcBin;
	private String operator;
	private boolean equals;
	private boolean hex;
	private boolean dec;
	private boolean oct;
	private boolean bin;
	private boolean qword;
	private boolean dword;
	private boolean word;
	private boolean bite;

	public Calculations() {
		input = "0";
		prevInput = "0";
		result = 0;
		stringResult = "";
		calc = "";
		calcHex = "";
		calcDec = "";
		calcOct = "";
		calcBin = "";
		operator = "";
		equals = false;
		hex = false;
		dec = true;
		oct = false;
		bin = false;
		qword = false;
		dword = false;
		word = false;
		bite = false;

	}

	public void setHex(boolean b) {
		hex = b;
	}

	public void setDec(boolean b) {
		dec = b;
	}

	public void setOct(boolean b) {
		oct = b;
	}

	public void setBin(boolean b) {
		bin = b;
	}

	public void setQword(boolean b) {
		qword = b;
	}

	public void setDword(boolean b) {
		dword = b;
	}

	public void setWord(boolean b) {
		word = b;
	}

	public void setByte(boolean b) {
		bite = b;
	}

	public boolean getQword() {
		return qword;
	}

	public boolean getDword() {
		return dword;
	}

	public boolean getWord() {
		return word;
	}

	public boolean getByte() {
		return bite;
	}

	public boolean getHex() {
		return hex;
	}

	public boolean getDec() {
		return dec;
	}

	public boolean getOct() {
		return oct;
	}

	public boolean getBin() {
		return bin;
	}

	public void setInput(String s) {
		input = s;
	}

	public String getInput() {
		return input;
	}

	public void setPrevInput(String s) {
		prevInput = s;
	}

	public String getPrevInput() {
		return prevInput;
	}

	public void setResult(int n) {
		result = n;
	}

	public void setCalc(String s) {
		calc = s;
	}

	public String getCalc() {
		return calc;
	}

	public void setCalcHex(String s) {
		calcHex = s;
	}

	public String getCalcHex() {
		return calcHex;
	}

	public void setCalcDec(String s) {
		calcDec = s;
	}

	public String getCalcDec() {
		return calcDec;
	}

	public void setCalcOct(String s) {
		calcOct = s;
	}

	public String getCalcOct() {
		return calcOct;
	}

	public void setCalcBin(String s) {
		calcBin = s;
	}

	public String getCalcBin() {
		return calcBin;
	}

	public void setOperator(String s) {
		operator = s;
	}

	public String getOperator() {
		return operator;
	}

	public void storeInput(String s) {
		input += s;
	}

	public void storeCalc(String s) {
		calc += s;
	}

	public void storeCalc(String s, int base) {
		if (base == 16) {
			calcHex += s;
		} else if (base == 10) {
			calcDec += s;
		} else if (base == 8) {
			calcOct += s;
		} else if (base == 2)
			calcBin += s;
	}

	public void setResult(String s) {
		stringResult = s;
		if (s.isEmpty()) {
			result = 0;
		}
	}

	public void setEquals(boolean b) {
		equals = b;
	}

	public boolean getEquals() {
		return equals;
	}

	// Converting the result to find the active base
	public String getResult() {

		result = rollOver(result);
		
		if (dec) {
			stringResult = String.valueOf(result);
		} else if (bin) {
			stringResult = Long.toBinaryString(result);
		} else if (oct) {
			stringResult = Long.toOctalString(result);
		} else if (hex) {
			stringResult = Long.toHexString(result);
			stringResult = stringResult.toUpperCase();
		}
		return stringResult;
	}

	// Convert the base passed in into the new base
	public String getConversion(String s, String base, String newBase) {
		long temp = 0L;

		String tempStr = "";
		// Taking in a HEX
		if (base.contentEquals("hex")) {
			temp = Long.parseLong(s, 16);
			if (newBase.contentEquals("dec")) {
				tempStr = String.valueOf(temp);
			} else if (newBase.contentEquals("bin")) {
				tempStr = Long.toBinaryString(temp);
				tempStr = checkBin(temp, tempStr);
			} else if (newBase.contentEquals("oct")) {
				tempStr = Long.toOctalString(temp);
			}
		}
		// Taking in a DEC
		else if (base.contentEquals("dec")) {
			temp = Long.parseLong(s);
			if (newBase.contentEquals("hex")) {
				tempStr = Long.toHexString(temp);
				tempStr = checkHex(temp, tempStr);
				tempStr = tempStr.toUpperCase();
			} else if (newBase.contentEquals("bin")) {
				tempStr = Long.toBinaryString(temp);
				tempStr = checkBin(temp, tempStr);
			} else if (newBase.contentEquals("oct")) {
				tempStr = Long.toOctalString(temp);
				tempStr = checkOct(temp, tempStr);
			}
		}
		// Taking in a OCT
		else if (base.contentEquals("oct")) {
			temp = Long.parseLong(s, 8);
			if (newBase.contentEquals("dec")) {
				tempStr = String.valueOf(temp);
			} else if (newBase.contentEquals("bin")) {
				tempStr = Long.toBinaryString(temp);
				tempStr = checkBin(temp, tempStr);
			} else if (newBase.contentEquals("hex")) {
				tempStr = Long.toHexString(temp);
				tempStr = tempStr.toUpperCase();
			}
		}
		// Taking in a BIN
		else if (base.contentEquals("bin")) {
			temp = Long.parseLong(s, 2);
			if (newBase.contentEquals("dec")) {
				tempStr = String.valueOf(temp);
			} else if (newBase.contentEquals("hex")) {
				tempStr = Long.toHexString(temp);
				tempStr = tempStr.toUpperCase();
			} else if (newBase.contentEquals("oct")) {
				tempStr = Long.toOctalString(temp);
			}
		}

		return tempStr;
	}

	public String checkOct(long num, String tempStr) {
		String str = "";
		if (bite) {
			if (num < 0) {
				str = "2" + tempStr.substring(tempStr.length() - 2, tempStr.length());
			} else {
				str = tempStr;
			}
		} else if (word) {
			if (num < 0) {
				str = "1" + tempStr.substring(tempStr.length() - 5, tempStr.length());
			} else {
				str = tempStr;
			}
		} else if (dword) {
			if (num < 0) {
				str = "3" + tempStr.substring(tempStr.length() - 10, tempStr.length());
			} else {
				str = tempStr;
			}
		} else {
			str = tempStr;
		}
		return str;
	}

	public String checkHex(long num, String tempStr) {
		String str = "";
		if (bite) {
			if (num < 0) {
				str = tempStr.substring(tempStr.length() - 2, tempStr.length());
			} else {
				str = tempStr;
			}
		} else if (word) {
			if (num < 0) {
				str = tempStr.substring(tempStr.length() - 4, tempStr.length());
			} else {
				str = tempStr;
			}
		} else if (dword) {
			if (num < 0) {
				str = tempStr.substring(tempStr.length() - 8, tempStr.length());
			} else {
				str = tempStr;
			}
		} else {
			str = tempStr;
		}
		return str;
	}

	public String checkBin(long num, String tempStr) {
		String str = "";
		if (bite) {
			if (num < 0) {
				str = tempStr.substring(tempStr.length() - 8, tempStr.length());
			} else {
				str = tempStr;
			}
		} else if (word) {
			if (num < 0) {
				str = tempStr.substring(tempStr.length() - 16, tempStr.length());
			} else {
				str = tempStr;
			}
		} else if (dword) {
			if (num < 0) {
				str = tempStr.substring(tempStr.length() - 32, tempStr.length());
			} else {
				str = tempStr;
			}
		} else {
			str = tempStr;
		}
		return str;
	}

	public Long rollOver(long num) {
		if (bite) {
			byte max = Byte.MAX_VALUE;
			byte min = Byte.MIN_VALUE;
			while (num > max || num < min) {
				if (num > max) {
					num = (num - max) + (min - 1);
				} else {
					num = (max + 1) - (min - num);
				}
			}
		} else if (word) {
			short max = Short.MAX_VALUE;
			short min = Short.MIN_VALUE;
			while (num > max || num < min) {
				if (num > max) {
					num = (num - max) + (min - 1);
				} else {
					num = (max + 1) - (min - num);
				}
			}
		} else if (dword) {
			int max = Integer.MAX_VALUE;
			int min = Integer.MIN_VALUE;
			while (num > max || num < min) {
				if (num > max) {
					num = (num - max) + (min - 1);
				} else {
					num = (max + 1) - (min - num);
				}
			}
		}
		return num;
	}

	// All mathematical operations are passed in here through the operator
	public void performCalc(String op) {
		// Current base is DEC
		if (dec) {
			if (op.contentEquals("+")) {
				result = Long.parseLong(prevInput) + Long.parseLong(input);
			} else if (op.contentEquals("-")) {
				result = Long.parseLong(prevInput) - Long.parseLong(input);
			} else if (op.contentEquals("\u00D7")) {
				result = Long.parseLong(prevInput) * Long.parseLong(input);
			} else if (op.contentEquals("\u00F7")) {
				result = Long.parseLong(prevInput) / Long.parseLong(input);
			} else if (op.contentEquals("Mod")) {
				result = Long.parseLong(prevInput) % Long.parseLong(input);
			} else if (op.contentEquals("+/-")) {
				if (!stringResult.isEmpty() && !input.contentEquals("0")) {
					input = String.valueOf("" + (-1 * Long.parseLong(stringResult)));
					stringResult = input;
				} else {
					input = String.valueOf("" + (-1 * Long.parseLong(input)));
				}
			}
		}
		// Current base is HEX
		else if (hex) {
			if (op.contentEquals("+")) {
				result = Long.parseLong(prevInput, 16) + Long.parseLong(input, 16);
			} else if (op.contentEquals("-")) {
				result = Long.parseLong(prevInput, 16) - Long.parseLong(input, 16);
			} else if (op.contentEquals("\u00D7")) {
				result = Long.parseLong(prevInput, 16) * Long.parseLong(input, 16);
			} else if (op.contentEquals("\u00F7")) {
				result = Long.parseLong(prevInput, 16) / Long.parseLong(input, 16);
			} else if (op.contentEquals("Mod")) {
				result = Long.parseLong(prevInput, 16) % Long.parseLong(input, 16);
			} else if (op.contentEquals("+/-")) {
				if (!stringResult.isEmpty() && !input.contentEquals("0")) {

					input = String.valueOf(-1 * Long.parseLong(stringResult, 16));
					System.out.println("1. " + input);
				} else {
					System.out.println("here2");
					result = -1 * Long.parseLong(input, 16);
					System.out.println("2. " + result);

				}
				input = getResult();
				System.out.println("3 " + input);
			}
		}
		// Current base is OCT
		else if (oct) {
			if (op.contentEquals("+")) {
				result = Long.parseLong(prevInput, 8) + Long.parseLong(input, 8);
			} else if (op.contentEquals("-")) {
				result = Long.parseLong(prevInput, 8) - Long.parseLong(input, 8);
			} else if (op.contentEquals("\u00D7")) {
				result = Long.parseLong(prevInput, 8) * Long.parseLong(input, 8);
			} else if (op.contentEquals("\u00F7")) {
				result = Long.parseLong(prevInput, 8) / Long.parseLong(input, 8);
			} else if (op.contentEquals("Mod")) {
				result = Long.parseLong(prevInput, 8) % Long.parseLong(input, 8);
			} else if (op.contentEquals("+/-")) {
				if (input.isEmpty() && !stringResult.isEmpty()) {
					result = -1 * Long.parseLong(stringResult, 8);
				} else {
					result = -1 * Long.parseLong(input, 8);
				}
				input = getResult();
			}
		}
		// Current base is BIN
		else if (bin) {
			if (op.contentEquals("+")) {
				result = Long.parseLong(prevInput, 2) + Long.parseLong(input, 2);
			} else if (op.contentEquals("-")) {
				result = Long.parseLong(prevInput, 2) - Long.parseLong(input, 2);
			} else if (op.contentEquals("\u00D7")) {
				result = Long.parseLong(prevInput, 2) * Long.parseLong(input, 2);
			} else if (op.contentEquals("\u00F7")) {
				result = Long.parseLong(prevInput, 2) / Long.parseLong(input, 2);
			} else if (op.contentEquals("Mod")) {
				result = Long.parseLong(prevInput, 2) % Long.parseLong(input, 2);
			} else if (op.contentEquals("+/-")) {
				if (input.isEmpty() && !stringResult.isEmpty()) {
					result = -1 * Long.parseLong(stringResult, 2);
				} else {
					result = -1 * Long.parseLong(input, 2);
				}
				input = getResult();
			}
		}
		// When the equals button is hit
		if (op.contentEquals("=")) {
			// When there is nothing but the input is entered, return the input entered
			if (calc.isEmpty() && stringResult.contentEquals("0") && prevInput.contentEquals("0")) {
				if (hex) {
					result = Long.parseLong(input, 16);
				} else if (bin) {
					result = Long.parseLong(input, 2);
				} else if (oct) {
					result = Long.parseLong(input, 8);
				} else if (dec) {
					result = Long.parseLong(input);
				}
			}
			// When '=' is hit continuously, keep on doing the last operator with the last
			// input
			else if (calc.isEmpty()) {
				prevInput = getResult();
				performCalc(operator);
			}
			// When there is no input entered and an operator was hit
			else if ((calc.substring(calc.length() - 1).contentEquals(operator)
					|| (calc.substring(calc.length() - 3).contentEquals(operator))) && input.isEmpty()) {
				input = "0";
				performCalc(operator);
			}
			// When the user finish with an input still in the input field
			else if ((calc.substring(calc.length() - 1).contentEquals(operator)
					|| (calc.substring(calc.length() - 3).contentEquals(operator))) && !input.isEmpty()) {
				performCalc(operator);
			}
		}
	}
}
