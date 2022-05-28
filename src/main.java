
public class main {
	/**
	 * Assignment 2 Submitted by: Javier Giberg.
	 */
	public static void main(String[] args) {
		// banandnabdabananaman
		String[] input_names = new String[3];
		String[] output_names = new String[2];

		input_names[0] = "banandnabdabananaman";//String
		input_names[1] = "4";//S
		input_names[2] = "4";//T

		LZ077EncoderDecoder LZ077 = new LZ077EncoderDecoder(input_names[1], input_names[2]);

		output_names[0] = LZ077.Compress(input_names);
		output_names[1] = ""+LZ077.countResultLength();

		System.out.println("Input String To Encoded whit LZ77:\n"
		+input_names[0]+
		"\n\nLength origina symbol: "+input_names[0].length()+
		"\nBuffer properties => S="+input_names[1]+" T="+input_names[2]+
		"\n\nResult Encoded: \n"+output_names[0]+
		"\n\nLength Encoder symbol: "+output_names[1]+
		"\nResult Decoder: "+LZ077.Decompress(output_names));
		

	}

}
