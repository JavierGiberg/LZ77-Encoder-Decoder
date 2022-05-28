
public class main {

	public static void main(String[] args) {
		LZ077EncoderDecoder test= new LZ077EncoderDecoder(20,20);
		//banandnabdabananaman
		String[] input_names = {"Dr' Amit Wanna F*** With Me? Okay. You Wanna Play Rough? Okay! Say Hello To My Little LZ77!",""};
		String[] output_names = null;
		test.Compress(input_names,output_names);
		System.out.println(test.getOutput());
		input_names[0]=test.getOutput();
		test.Decompress(input_names, output_names);
		
		
		System.out.println();
	}

}
