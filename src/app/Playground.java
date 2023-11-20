package app;

import java.util.Arrays;

public class Playground {
    public static void main(String[] args) {
        String tmp = "anjay,ga,sih\nlah,kok\nga,anjay";
        String[] tmp2 = tmp.split("\n");
        for (String s : tmp2) {
            System.out.println(Arrays.toString(s.split(",")));
        }
    }
}
