import java.util.*;

public class Q9SM17 {

    public static void main(String[] args) {
        List<String> words = new ArrayList<>();
        words.add("MMMMMMMMMMMMMMMMMMaingo");
        words.add("aingola");
        words.add("Brown");
        words.add("Makhdusjudhsadhsdkjasdshadjashds");
        String okay = Foo(words);
        System.out.println(okay);
    }

    public static String Foo(List<String> words) {
        int length = 0;
        int length1 = 0;
        String word = "";
        int decider = 0;
        for (int i = 0; i < words.size(); i++) {
            if (words.get(i).startsWith("M")) {
                length1 = words.get(i).length();
                if (length1 > length) {
                    word = words.get(i);
                    decider = 1;
                }
            }
        }
        if (decider == 1) {
            return word;
        }
        return null;
    }
}
