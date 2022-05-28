import java.util.ArrayList;

/**
 * Assignment 2 Submitted by: Javier Giberg.
 */

public class LZ077EncoderDecoder implements Compressor {
	private static int offset;
	private static int lenghtOfMatch;
	private static int pointer;
	private static int s;
	private static int t;
	private static int match;
	private static int countResultLength;
	private static String inputCompress;
	private static String outputCompress;
	private static String inputDecompress;
	private static String outputDecompress;
	private static ArrayList<Integer> index;
	private static char Char_temp;

	public LZ077EncoderDecoder(String s, String t) {

		this.s = Integer.parseInt(s);
		this.t = Integer.parseInt(t) + 6;
		this.pointer = 0;
		this.outputCompress = "";
		this.outputDecompress = "";
		this.match = 0;
		this.index = new ArrayList<Integer>();

	}

//--------------------------------------------------------------------------
	
	public String Compress(String[] input_names) {
		inputCompress = input_names[0];
		for (int i = 0; i < inputCompress.length(); i++) {
			match = 0;
			if (pointer >= inputCompress.length())
				break;
			searchMatch();
		}
		return outputCompress;
	}

//--------------------------------------------------------------------------
	static void searchMatch() {
		int window;

		if (pointer < s) {
			window = pointer;
		} else {
			window = s;
		}
		for (int i = 0; i < window; i++) {
			if (inputCompress.charAt((pointer - window) + i) == inputCompress.charAt(pointer)) {
				match++;
				index.add((pointer - window) + i);
			}

		}
		if (match == 0) {
			char Char = inputCompress.charAt(pointer);
			offset = 0;
			lenghtOfMatch = 0;
			buildResult(Char);
			pointer++;

		}
		if (match != 0) {
			getBestMatch(window);

		}
	}

//--------------------------------------------------------------------------
	static void getBestMatch(int window) {
		int best_count = 0;
		offset = 0;
		lenghtOfMatch = 0;
		int count_temp = 0;
		int pointer_temp = pointer;
		boolean flag = false;

		for (int i = 0; i < match; i++) {
			count_temp = 0;
			int indexFirst = index.get(i);
			for (int j = 0; j < t; j++) {
				if (pointer_temp + j >= inputCompress.length()) {
					flag = true;
					break;
				}
				if (i <= match && (pointer_temp + j) != inputCompress.length() - 1)
					if (inputCompress.charAt(indexFirst + j) == inputCompress.charAt(pointer_temp + j)) {
						count_temp++;
					}
				if (inputCompress.charAt(indexFirst + j) != inputCompress.charAt(pointer_temp + j))
					break;
			}
			if (count_temp >= best_count) {
				best_count = count_temp;
				if (flag)
					pointer--;
				offset = pointer_temp - indexFirst;

				lenghtOfMatch = count_temp;
				if (lenghtOfMatch == 0)
					offset = 0;
				indexFirst++;
			}

		}
		pointer += best_count;
		if (flag) {
			buildResult(inputCompress.charAt(inputCompress.length() - 1));
			pointer = inputCompress.length();
			return;
		}

		else
			buildResult(inputCompress.charAt(pointer));
		pointer++;

		index.clear();

	}
//--------------------------------------------------------------------------
	public int countResultLength() {
		return countResultLength;
	}

//--------------------------------------------------------------------------
	static void buildResult(char Char) {
		outputCompress += "(" + offset + "," + lenghtOfMatch + "," + Char + ")";
		countResultLength++;
	}

//--------------------------------------------------------------------------
	
	public String Decompress(String[] input_names) {
		inputDecompress = input_names[0];

		buildDecompress();

		return  outputDecompress;
	}
//--------------------------------------------------------------------------
	static void buildDecompress() {
		while (inputDecompress.length() > 0) {
			getInterval();
			buildOutput();

		}
	}
//--------------------------------------------------------------------------
	static void getInterval() {
		int cut_counter = 1;
		String offset_temp = "";
		String lenghtOfMatch_temp = "";
		Char_temp = 0;

		while (inputDecompress.charAt(0 + cut_counter) != ',') {
			offset_temp += inputDecompress.charAt(cut_counter);
			cut_counter++;

		}
		offset = Integer.parseInt(offset_temp);

		cut_counter++;

		while (inputDecompress.charAt(0 + cut_counter) != ',') {
			lenghtOfMatch_temp += inputDecompress.charAt(cut_counter);
			cut_counter++;

		}
		lenghtOfMatch = Integer.parseInt(lenghtOfMatch_temp);
		while (inputDecompress.charAt(0 + cut_counter) != ')') {
			Char_temp = inputDecompress.charAt(cut_counter);
			cut_counter++;

		}

		cut_counter++;
		inputDecompress = inputDecompress.substring(cut_counter);

	}
//--------------------------------------------------------------------------
	static void buildOutput() {
		int l = outputDecompress.length();
		if (offset == 0)
			outputDecompress += Char_temp;
		else {
			for (int i = 0; i < lenghtOfMatch; i++) {

				outputDecompress += outputDecompress.charAt(l - offset + i);
			}
			outputDecompress += Char_temp;
		}

	}

	

}
