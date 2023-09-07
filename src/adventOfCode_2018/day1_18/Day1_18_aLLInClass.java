package adventOfCode_2018.day1_18;
//https://adventofcode.com/2018/day/1

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Например, если устройство отображает изменения частоты +1, -2, +3, +1, то,
 * начиная с нулевой частоты, произойдут следующие изменения:
 * <p>
 * Текущая частота  0, изменение +1; результирующая частота  1.
 * Текущая частота  1, изменение -2; результирующая частота -1.
 * Текущая частота -1, изменение +3; результирующая частота  2.
 * Текущая частота  2, изменение +1; результирующая частота  3.
 * В этом примере результирующая частота равна 3.
 * <p>
 * Вот другие примеры ситуаций:
 * <p>
 * +1, +1, +1 приводит к 3
 * +1, +1, -2 приводит к 0
 * -1, -2, -3 приводит к -6
 * <p>
 * <p>
 * Часть 2
 * вам нужно найти первую частоту, которую оно достигает дважды .
 * <p>
 * Например, используя тот же список изменений, что и выше,
 * устройство зациклится следующим образом:
 * <p>
 * Текущая частота  0, изменение +1; результирующая частота  1.
 * Текущая частота  1, изменение -2; результирующая частота -1.
 * Текущая частота -1, изменение +3; результирующая частота  2.
 * Текущая частота  2, изменение +1; результирующая частота  3.
 * (В этот момент устройство продолжает с начала списка.)
 * Текущая частота  3, изменение +1; результирующая частота  4.
 * Текущая частота  4, изменение -2; результирующая частота  2,
 * которую уже видели.
 * В этом примере первая частота, достигнутая дважды,
 * равна 2. Обратите внимание, что вашему устройству
 * может потребоваться повторить свой список изменений частоты много раз,
 * прежде чем будет найдена повторяющаяся частота,
 * и что дубликаты могут быть найдены в процессе обработки списка.
 * <p>
 * Вот другие примеры:
 * <p>
 * +1, -1первый достигает 0дважды.
 * +3, +3, +4, -2, -4первый достигает 10дважды.
 * -6, +3, +8, +5, -6первый достигает 5дважды.
 * +7, +7, -2, -7, -4первый достигает 14дважды.
 */
public class Day1_18_aLLInClass {

    public static void main(String[] args) throws IOException {
        // коллекция для хранения текущих частот

        String fileName = "DAY1_18";
        // коллекция для хранения строк с файла
        ArrayList<String> lineArrList = readFileInArrList(fileName);
        int resultingFrequency_part1 = 0;
        int resultingFrequency_part2 = 0;

        // решение часть 1
        resultingFrequency_part1 = getResultingFrequency_part1(lineArrList);

        // решение часть 2
        resultingFrequency_part2 = getRepeatFrequency_part2(lineArrList);

        System.out.println("Part 1 = " + resultingFrequency_part1 + "\n"
                + "Part 2 = " + resultingFrequency_part2);
    }

    // Решение часть 2
    private static int getRepeatFrequency_part2(ArrayList<String> lineArrList) throws IOException {
        //коллекция для ханения чисел результирующей частоты
        ArrayList<Integer> resultingFrequencyArrList = new ArrayList<Integer>();
        // переменная для сохранения повторяющегося значения результирующей частоты
        int repeatFrequency = 0;
        // коллекция для хранения результирующтх частот
        int resultingFrequency_part2 = 0;
        // делать пока равно значение "повторяющейся частоты" равно 0

        while (repeatFrequency == 0) {
            // пройти по коллекции считанных строк
            for (int i = 0; i < lineArrList.size(); i++) {
                String currentLine = lineArrList.get(i);
                int currentDigit = getIntFromString(currentLine); // повтор
                currentDigit = changeSignDigit(currentLine, currentDigit); // повтор
                // сложить полученные числа
                resultingFrequency_part2 += currentDigit;

// участок кода в котором идет отличие от части 1
                // проверить есть ли число в коллекции /
                // /contains вернет false если элемента нет;
                if (resultingFrequencyArrList.contains(resultingFrequency_part2)) {
                    repeatFrequency = resultingFrequency_part2;
                    break;
                } else {
                    resultingFrequencyArrList.add(resultingFrequency_part2);
                }
            }
        }
        return repeatFrequency;
    }


    // Решение для части 1
// большая часть использована в части 2
// возможно для экономии можно добавить в часть 2, и при первом прохождении по файлу
// поставить флаг лож на первую часть
    private static int getResultingFrequency_part1(ArrayList<String> lineArrList) throws IOException {
        int resultingFrequency = 0;
        for (int i = 0; i < lineArrList.size(); i++) {
            String currentLine = lineArrList.get(i);
            int currentDigit = getIntFromString(currentLine);
            currentDigit = changeSignDigit(currentLine, currentDigit);
            // сложить полученные числа
            resultingFrequency += currentDigit;
        }
        return resultingFrequency;
    }

    //метод для чтения строк с файла в коллекцию
    private static ArrayList<String> readFileInArrList(String filePath) throws IOException {
        ArrayList<String> lines = new ArrayList<String>();
        FileReader fileReader = new FileReader(filePath);
        Scanner scanner = new Scanner(fileReader);

        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            lines.add(line);
        }
        fileReader.close();
        scanner.close();
        return lines;
    }

    // получить число из строки c выделениием из строки символов (склейка цифр)
    private static int getIntFromString(String digitString) {
        // выбрать число из строки
        String[] arrDigitOfString = digitString.split("\\D+");
        /**
         * String str = digitString.substring(1, digitString.length());
         *  выделит подстроку, в которой находятся все символы кроме первого
         */

        // Превратить строку-число в число String.join склеит содержимое строки
        Integer intObject = Integer.parseInt(String.join("", arrDigitOfString));
        // превратить объект оболочки примитивного типа в переменную прим типа
        int digit = intObject.intValue();
        return digit;
    }

    // изменить знак числа с учетом, что всегда он стоит в начале строки
    private static int changeSignDigit(String allLine, int currentDigit) {
/**
 * char[] charArrForDigit = allLine.toCharArray();
 * // если число имело знак "-" то записать его таким
 *
 *  заменено на allLine.charAt(0)
 */
        if (allLine.charAt(0) == '-') {
            currentDigit = -currentDigit;
        }
        return currentDigit;
    }
}
