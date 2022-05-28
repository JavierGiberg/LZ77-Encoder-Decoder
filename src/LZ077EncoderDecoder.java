import java.util.ArrayList;

/**
 * Assignment 2 Submitted by: Student 1. ID# XXXXXXXXX Student 2. ID# XXXXXXXXX
 */

public class LZ077EncoderDecoder implements Compressor {
	private static int offset;
	private static int lenghtOfMatch;
	private static int pointer;
	private static int s;// search window
	private static int t;// Look head buffer
	private static int match;
	private static String input;
	private static String output;
	private static ArrayList<Integer> index;

	public LZ077EncoderDecoder(int s, int t) {

		this.s = s;
		this.t = t;
		this.pointer = 0;
		this.output = "";
		this.match = 0;
		index = new ArrayList<Integer>();

	}

//--------------------------------------------------------------------------
	@Override
	public void Compress(String[] input_names, String[] output_names) {
		input = input_names[0];
		for (int i = 0; i < input.length(); i++) {
			match = 0;
			if (pointer >= input.length())
				break;
			searchMatch();

		}

	}

//--------------------------------------------------------------------------
	static void searchMatch() {
		System.out.println("Enter to searchMatch");
		int window;

		if (pointer < s) {
			window = pointer;
		} else {
			window = s;
		}
		for (int i = 0; i < window; i++) {
			if (input.charAt((pointer - window) + i) == input.charAt(pointer)) {
				match++;

				index.add((pointer - window) + i);

				// System.out.println("index: " + (index.get(i)));
				System.out.println("match: " + match);
			}

		}
		if (match == 0) {
			char Char = input.charAt(pointer);
			offset = 0;
			lenghtOfMatch = 0;
			buildString(Char);
			pointer++;

		}
		if (match != 0) {
			getBestMatch(window);

		}
		System.out.println(output);
	}

//--------------------------------------------------------------------------
	static void getBestMatch(int window) {
		System.out.println("Enter to getBestMatch");
		int best_count = 0;
		offset = 0;
		lenghtOfMatch = 0;
		int count_temp = 0;
		int pointer_temp = pointer;
		boolean flag = false;

		for (int i = 0; i < match; i++) {
			count_temp = 0;
			int indexFirst = index.get(i);
			for (int j = 0; j < window; j++) {
				if (pointer_temp + j >= input.length()) {
					flag = true;
					break;
				}
				if (i <= match && (pointer_temp + j) != input.length() - 1)
					if (input.charAt(indexFirst + j) == input.charAt(pointer_temp + j)) {

						System.out.println(input.charAt(indexFirst + j) + " " + input.charAt(pointer_temp + j));
						System.out.println((indexFirst + j) + " " + (pointer_temp + j));
						count_temp++;

					}
				if (input.charAt(indexFirst + j) != input.charAt(pointer_temp + j))
					break;

			}

			if (count_temp >= best_count) {
				best_count = count_temp;
				if (flag)
					pointer--;
				offset = pointer_temp - indexFirst;
				lenghtOfMatch = count_temp;
				indexFirst++;
			}

		}
		System.out.println("lenghtOfMatch " + lenghtOfMatch);
		pointer += best_count;
		if (flag) {
			buildString(input.charAt(input.length() - 1));
			pointer = input.length();
			return;
		}

		else
			buildString(input.charAt(pointer));
		pointer++;

		index.clear();

	}

//--------------------------------------------------------------------------
	static void buildString(char Char) {

		output += "(" + offset + "," + lenghtOfMatch + "," + Char + ")";
	}

//--------------------------------------------------------------------------
	@Override
	public void Decompress(String[] input_names, String[] output_names) {
		// TODO Auto-generated method stub

	}

}
