package sda;

public class StringRepeater {
	public static String repeat(String string, int rep) {
	    if (rep < 1) {
            return "";
        }
        StringBuilder str = new StringBuilder(string);
        
        // np. 5>>1 = przesunięcie bitowe w prawo o 1
        // 0b0000101>>1 =>
        // 0b0000010
        int i = 0;
        for (i = 1; i < rep; i=i<<1) {
            str.append(str.toString());
        }
        str.append(repeat(string, rep - i));
        return str.toString();
    }
    public static void main(String[] args) {
        System.out.println(repeat("12", 4));
        System.out.println(repeat("12", 3));
        System.out.println(repeat("12", 1));
        System.out.println(repeat("12", 0)); //?
    }
}