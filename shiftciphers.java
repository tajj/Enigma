import java.util.Scanner;

public class shiftciphers {
	String[] alphabet;
	String[] shift1;
	String[] shift2;
	String[] shift3;
	String[] reflection;
	public shiftciphers() {
		String[] alpha= {"*", " ", "a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
		String[] shifs= {"*", "h", " ","z","s","i","b","e","j","m","a","g","o","d","k","r","q","p","w","n","f","l","u","c","t","v","y","x"};
		String[] shif2= {"*", "s", "h","z","i","b"," ","e","j","d","a","g","m","o","k","r","q","p","w","n","f","l","u","c","t","y","v","x"};
		String[] shif3= {"*", "s", "z","i","h","b"," ","e","j","g","d","a","m","r","x","o","q","p","w","n","v","y","u","c","t","l","f","k"};
		String[] refle= {"*", " ", "z","b","c","e","d","g","f","i","h","k","j","o","n","m","l","q","p","s","r","u","t","w","v","y","x","a"};
		alphabet= alpha;
		shift1=shifs;
		shift2=shif2;
		shift3=shif3;
		reflection=refle;
	}
	public void setcipher (String[] thing){
		shift1= thing;
	}

	public String shift(String text, String [] shifter){
		String shift[]=shifter;

		String[] textarray= new String[text.length()];
		String ciphertext="";
		for(int i =0; i<textarray.length; i++){
			String letter =text.substring(i, i+1);
			textarray[i]=letter;
		}
		for (int k=0; k<textarray.length; k++){
			int letternum=0;
			for (int m=0; !alphabet[m].equals(textarray[k]); m++){
				letternum=m;
			}
			
			textarray[k]=shift[letternum+1];}
			
		for (int g=0; g<textarray.length; g++){
			ciphertext=ciphertext+textarray[g];
		}
		return ciphertext;
		
	}
	public String shiftbackwards(String text, String [] shifter){
		String shift[]=shifter;

		String[] textarray= new String[text.length()];
		String ciphertext="";
		for(int i =0; i<textarray.length; i++){
			String letter =text.substring(i, i+1);
			textarray[i]=letter;
		}
		for (int k=0; k<textarray.length; k++){
			int letternum=0;
			for (int m=0; !shift[m].equals(textarray[k]); m++){
				letternum=m;
			}
		
			textarray[k]=alphabet[letternum+1];}

		for (int g=0; g<textarray.length; g++){
			ciphertext=ciphertext+textarray[g];
		}
		return ciphertext;
	}
	public String encode(String text, String[] shifter1, String[] shifter2, String[] shifter3){
		String ciphertext="";
		String[] shifta= shifter1;
		String[] shiftb = shifter2;
		String[] shiftc= shifter3;
		String[] textarray= new String[text.length()];
		for(int i =0; i<textarray.length; i++){
			String letter =text.substring(i, i+1);
			textarray[i]=letter;
		}
		for (int k=0; k<textarray.length;k++){
			String letter = textarray[k];
			letter= shift(letter, shifta);
			letter = shift(letter, shiftb);
			letter = shift(letter, shiftc);
			letter = shift(letter, reflection);
			letter= shiftbackwards(letter, shiftc);
			letter= shiftbackwards(letter, shiftb);
			letter= shiftbackwards(letter, shifta);
			shifta= moveby1(shifta);
			if(k%26==0&&k!=0){
			shiftb= moveby1(shiftb);}
			if (k % (26 * 26) == 0 && k != 0) {
				shiftc = moveby1(shiftc);
			}

			ciphertext = ciphertext + letter;

		}
		return ciphertext;
	}

	public static String[] moveby1(String[] shifter) {
		String[] newshift = new String[28];
		newshift[0] = shifter[0];
		String letter = shifter[1];
		for (int i = 1; i < 27; i++) {
			newshift[i] = shifter[i + 1];
		}
		newshift[27] = letter;
		return newshift;
	}

	public static void main(String[] args) {
		shiftciphers cipher = new shiftciphers();
		Scanner sc1 = new Scanner(System.in);
		String[] s1= {};
		String[] s2={};
		String[] s3={};
		System.out.println("Enter some combination of 123 to set the order of your rotors."
				+ " Hit enter between each number. repeats are ok. for example '1'ENTER'2'ENTER'1' ");
		for (int i = 0; i < 3; i++) {
			int num = sc1.nextInt();
			if (i == 0) {
				if (num == 1) {
					s1 = cipher.shift1;
				}
				if (num == 2) {
					s1 = cipher.shift2;
				}
				if (num == 3) {
					s1 = cipher.shift3;
				}
			}
			if (i == 1) {
				if (num == 1) {
					s2 = cipher.shift1;
				}
				if (num == 2) {
					s2 = cipher.shift2;
				}
				if (num == 3) {
					s2 = cipher.shift3;
				}
			}
			if (i == 2) {
				if (num == 1) {
					s3 = cipher.shift1;
				}
				if (num == 2) {
					s3 = cipher.shift2;
				}
				if (num == 3) {
					s3 = cipher.shift3;
				}
			}

		}
		System.out.println("Please enter the message you wish to encode via the enigma machine");
		Scanner sc = new Scanner(System.in);
		String stuff= sc.nextLine();
		String thing =cipher.encode(stuff, s1, s2, s3);
		System.out.println(thing);
	
		
		System.out.println("Enter 'yes' to send your code back through the machine on the same settings");
		String otherthing = sc.nextLine();
		if(otherthing.equals("yes")){
		System.out.println(cipher.encode(thing, s1, s2, s3));}
		
	}
}
