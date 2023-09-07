package adventOfCode_2015.day10_15;
import java.util.LinkedHashMap;


import java.io.IOException;

// общий для копирования в каждый каталог
public class DAY10_15_main {
//
//   public static void main(String[] args) {
//       //Получить данные с файла
//       //String fileName = "TEST"; // "TEST  "   "1321131112"
//       // Пример, на основе 3 дня
////    Day3_18_Implement day3Solver = new Day3_18_Implement(1000);
////    //day3Solver.showResult(fileName);
//
//
//   }
    public static final String input = "1";
    // задание 1321131112   ответ часть 1  492982 часть2
    //

    private DAY10_15_main () {

    }

    public static String DAY10_15_main (String input, int times) {
        if (input.length() == 0)
            return input;

        StringBuilder sb = new StringBuilder(input);
        for (int counter = 0; counter < times; counter++) {
            StringBuilder output = new StringBuilder();
            char currentChar = (char) 0;
            int count = 0;
            for (int i = 0; i < sb.length(); i++) {
                char c = sb.charAt(i);
                if (c != currentChar) {
                    if (currentChar != (char) 0) output.append(count).append(currentChar);
                    currentChar = c;
                    count = 1;
                } else
                    count++;
            }
            output.append(count).append(currentChar);
            sb = output;
        }
        System.out.println(sb.toString());
        return sb.toString();
    }

    private static class Test {
        public static void main(String[] args) {
            System.out.println(DAY10_15_main ("1", 1));
            System.out.println(DAY10_15_main ("1",3));
            System.out.println(DAY10_15_main (input,60));
        }
    }
}

class RunDay10_Part1 {
    public static void main(String[] args) {
        System.out.println(DAY10_15_main .DAY10_15_main (DAY10_15_main .input,5).length());
    }
}

class RunDay10_Part2{
    public static void main(String[] args){
        System.out.println(DAY10_15_main .DAY10_15_main (DAY10_15_main .input,50).length());
    }
}
