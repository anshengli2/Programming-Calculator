
public class Calculations {

	private String input;
	private String prevInput;
	private long result;
	private String stringResult;
	private String calc;
	private String operator;
	private boolean equals;
	private boolean hex;
	private boolean dec;
	private boolean oct;
	private boolean bin;

	public Calculations() {
		input = "0";
		prevInput = "0";
		result = 0;
		stringResult = "0";
		calc = "";
		operator = "";
		equals = false;
		hex = false;
		dec = true;
		oct = false;
		bin = false;
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

	public String getResult() {
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

	public String getConversion(String s, String base, String newBase) {
		Long temp = 0L;
		String tempStr = "";
		if (base.contentEquals("hex")) {
			temp = Long.parseLong(s, 16);
			if (newBase.contentEquals("dec")) {
				tempStr = String.valueOf(temp);
			} else if (newBase.contentEquals("bin")) {
				tempStr = Long.toBinaryString(temp);
			} else if (newBase.contentEquals("oct")) {
				tempStr = Long.toOctalString(temp);
			}
		} else if (base.contentEquals("dec")) {
			temp = Long.parseLong(s);
			if (newBase.contentEquals("hex")) {
				tempStr = Long.toHexString(temp);
				tempStr = tempStr.toUpperCase();
			} else if (newBase.contentEquals("bin")) {
				tempStr = Long.toBinaryString(temp);
			} else if (newBase.contentEquals("oct")) {
				tempStr = Long.toOctalString(temp);
			}
		} else if (base.contentEquals("oct")) {
			temp = Long.parseLong(s, 8);
			if (newBase.contentEquals("dec")) {
				tempStr = String.valueOf(temp);
			} else if (newBase.contentEquals("bin")) {
				tempStr = Long.toBinaryString(temp);
			} else if (newBase.contentEquals("hex")) {
				tempStr = Long.toHexString(temp);
				tempStr = tempStr.toUpperCase();
			}
		} else if (base.contentEquals("bin")) {
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

	public void performCalc(String op) {
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
				if (input.isEmpty() && !stringResult.isEmpty()) {
					input = String.valueOf("" + (-1 * Long.parseLong(stringResult)));
				} else {
					input = String.valueOf("" + (-1 * Long.parseLong(input)));
				}
			}
		} else if (hex) {
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
				if (input.isEmpty() && !stringResult.isEmpty()) {
					result = -1 * Long.parseLong(stringResult, 16);
				} else {
					result = -1 * Long.parseLong(input, 16);
				}
				input = getResult();
			}
		} else if (oct) {
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
		} else if (bin) {
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

		if (op.contentEquals("=")) {	
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
			} else if (calc.isEmpty()) {
				prevInput = getResult();
				performCalc(operator);
			} else if (calc.substring(calc.length() - 1).contentEquals(operator) && input.isEmpty()) {
				input = "0";
				performCalc(operator);
			} else if (calc.substring(calc.length() - 1).contentEquals(operator) && !input.isEmpty()) {
				performCalc(operator);
			}
		}
	}
}
