
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
		equals = false;
	}

	public boolean getEquals() {
		return equals;
	}

	public String getResult() {
		stringResult = String.valueOf(result);
		return stringResult;
	}

	public void performCalc(String op) {
		if (dec) {
			if (op.contentEquals("+")) {
				result = Long.parseLong(prevInput) + Long.parseLong(input);
			} else if (op.contentEquals("-")) {
				result = Long.parseLong(prevInput) - Long.parseLong(input);
			} else if (op.contentEquals("*")) {
				result = Long.parseLong(prevInput) * Long.parseLong(input);
			} else if (op.contentEquals("/")) {
				result = Long.parseLong(prevInput) / Long.parseLong(input);
			} else if (op.contentEquals("%")) {
				result = Long.parseLong(prevInput) % Long.parseLong(input);
			} else if (op.contentEquals("+/-")) {
				input = String.valueOf("" + (-1 * Long.parseLong(input)));
			}
		}
		else if(hex) {
			
		}

		if (op.contentEquals("=")) {
			if (calc.isEmpty()) {
				prevInput = getResult();
				performCalc(operator);
			} else if (calc.substring(calc.length() - 1).contentEquals(operator) && input.isEmpty()) {

				input = "0";
				performCalc(operator);
			} else if (calc.substring(calc.length() - 1).contentEquals(operator) && !input.isEmpty()) {

				performCalc(operator);
			}

			equals = true;

		}
	}
}
