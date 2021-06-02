package dateStructure.dsPlay.dsa.algrithem.AboutString;

import java.util.Locale;

public class JudgeCircle {
    public static boolean judgeCircle(String moves) {
        if (moves.isEmpty()) return true;
        if (moves.length() % 2 != 0) return false;

        int left = 0, right = 0;
        for (int i = 0; i < moves.length(); i++) {
            if ("U".equals(moves.substring(i, i + 1)))
                left++;

            if ("D".equals(moves.substring(i, i + 1)))
                left--;

            if ("L".equals(moves.substring(i, i + 1)))
                right--;

            if ("R".equals(moves.substring(i, i + 1)))
                right++;
        }

        return right == 0 && left == 0;
    }

    public static void main(String[] args) {
        System.out.println(judgeCircle("LL"));
        System.out.println(judgeCircle("UD"));
    }
}
