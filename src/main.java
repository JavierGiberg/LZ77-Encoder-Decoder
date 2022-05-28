
public class main {

	public static void main(String[] args) {
		LZ077EncoderDecoder test= new LZ077EncoderDecoder(32,32);
		//banandnabdabananaman
		String[] input_names = {"How much wood would a woodchuck chuck if a woodchuck could chuck wood? He would chuck, he would, as much as he could, and chuck as much wood As a woodchuck would if a woodchuck could chuck wood.",""};
		
		String[] output_names = null;
		test.Compress(input_names,output_names);
		System.out.println();
	}

}
