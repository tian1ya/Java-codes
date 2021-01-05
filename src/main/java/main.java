import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class main {
    public static void main(String[] args) {

        String regex = "\\$\\{(.*?)\\}";
//        "\\$\\{([^{}]+?)\\}"

        String ss = "dsfadsfd${dsa}${dd}${dsadsds}";

        Pattern compile = Pattern.compile(regex);

        Matcher matcher1 = compile.matcher(ss);

        boolean matcher = matcher1.find();

        while (matcher1.find()) {
            System.out.println(matcher1.group(0));
        }




//        System.out.println(matcher);
//        convert(2000);
    }

    public static void convert(int totalSecs) {
        int hours = totalSecs / 3600;
        int minutes = (totalSecs % 3600) / 60;
        int seconds = totalSecs % 60;

        System.out.println(5 / 2);
        System.out.println(5 % 2);
        System.out.println(String.format("%02d:%02d:%02d", hours, minutes, seconds));
    }
}
