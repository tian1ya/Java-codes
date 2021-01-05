package PatternFind;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class patternds {

    public static void dd(String[] args) {

        String regex = "\\$\\{(.*?)\\}";


        /*
            "\\$\\{([^{}]+?)\\}"

             group(0) 是 \\$\\{([^{}]+?)\\}

             group(1) 是 ([^{}]+?)
         */

        String ss = "dsfadsfd_${dsa}_${dd}_${dsadsds}ddd";
        String ddss = ss ;

        Pattern compile = Pattern.compile(regex);

        Matcher matcher1 = compile.matcher(ss);

//        boolean matcher = matcher1.find();

        while (matcher1.find()) {
//            System.out.println(matcher1.group(0) + ", pos: "
//                    + matcher1.start() + "-" + (matcher1.end() - 1));

            int start = matcher1.start(1) - 2;
            int end = matcher1.end(1) + 1;
            System.out.println(matcher1.group(1) + ", pos: " +
                    start + "-" + end);

            String substring = ss.substring(start, end);
            System.out.println(substring);

            System.out.println("\n");

            ddss = ddss.replace(substring, "liuxux");
            System.out.println(matcher1.group(1));
        }



        System.out.println(ss);
        System.out.println(ddss);
    }

    public static void main(String[] args) {

        String regex = "(\\w)*\\/(\\d+)-(\\d+)";

        Pattern compile = Pattern.compile(regex);
        String ss = "dsds/2-4";

        Matcher matcher1 = compile.matcher(ss);

        System.out.println(matcher1.group(0));
        System.out.println(matcher1.group(1));
        System.out.println(matcher1.group(2));
    }
}
