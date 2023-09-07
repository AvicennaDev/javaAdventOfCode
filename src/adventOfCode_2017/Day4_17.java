package adventOfCode_2017;
import java.io.FileNotFoundException;
import java.util.*;
import java.io.*;
// https://gist.github.com/snarkbait/0c5d7b14246619e8fb2b47cfea96de83


/**
 * https://adventofcode.com/2017/day/4
 *
 * aa bb cc dd ee is valid.
 * aa bb cc dd aa is not valid - the word aa appears more than once.
 * aa bb cc dd aaa
 *
 */

public class Day4_17 {
    private static boolean part1(String[] s) {
        Set<String> set = new HashSet<String>(Arrays.asList(s));
        return set.size() == s.length;
    }

    private static boolean part1Our(String[] s) {
        for (int i = 0; i < s.length; i++){
            for (int j = 0; j < s.length; j++){
                if (i != j && s[i].equals(s[j])){
                    return false;
                }
            }
        }

        return true;
    }

    // string[] array = { "a", "a", "b" } = 3
    // HashSet<string> hashset = { "a", "b" } = 2
    private static boolean part2(String[] s) {
        return noAnagrams(s);
    }

    private static boolean isAnagram(String s, String t) {
        char[] c = s.toCharArray();
        char[] d = t.toCharArray();
        Arrays.sort(c);
        Arrays.sort(d);
        return Arrays.equals(c, d);
    }

    private static boolean noAnagrams(String[] s) {
        for (int i = 0; i < s.length - 1; i++) {
            for (int j = i + 1; j < s.length; j++) {
                if (isAnagram(s[i], s[j])) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) throws FileNotFoundException {

        String[] input = readFile("DAY4_17.txt");

        int resultPart1 = 0;
        int resultPart2 = 0;

        for (int i = 0; i < input.length; i++){
            String[] lineParts = input[i].split(" ");

            if (part1Our(lineParts)){
                resultPart1++;
            }

            if (part2(lineParts)){
                resultPart2++;
            }
        }

        System.out.println("Part 1: " + resultPart1);
        System.out.println("Part 2: " + resultPart2);
    }

    /* Считать содержимое файла */
    private static String[] readFile(String fileName) throws FileNotFoundException {
        FileReader file = new FileReader(fileName);
        //  scanner  считывает содержимое файла и делит всё на части (строки т.е. токены)
        // \n символ разделитель для токенов
        Scanner fileScanner = new Scanner(file);

        //коллекции
        ArrayList<String> lines = new ArrayList<String>();

        while (fileScanner.hasNext()) {
            /* Заменяет ненужное слово through на пустоту */
            // nextLine смотреть полную строку до \n
            String line = fileScanner.nextLine().replace(" through ", ",");
            lines.add(line);
        }
        fileScanner.close();
        // какой именно массив передается(строки)
        return lines.toArray(new String[lines.size()]);
    }
}


// считать с док

