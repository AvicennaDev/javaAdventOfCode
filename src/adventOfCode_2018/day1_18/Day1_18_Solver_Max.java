package adventOfCode_2018.day1_18;

import common.AocSolverAbstract;

import java.util.ArrayList;

public class Day1_18_Solver_Max extends AocSolverAbstract<Integer, Integer> {
    public Integer calculatePart1_Solution(ArrayList<String> linesArrList){
        int resultingFrequency = 0;
        for (int i = 0; i < linesArrList.size(); i++) {
            String currentLine = linesArrList.get(i);
            int currentDigit = getIntFromString(currentLine);
            currentDigit = changeSignDigit(currentLine, currentDigit);
            // сложить полученные числа
            resultingFrequency += currentDigit;
        }
        return resultingFrequency;
    }

    public Integer calculatePart2_Solution(ArrayList<String> linesArrList){
        ArrayList<Integer> resultingFrequencyArrList = new ArrayList<Integer>();
        // переменная для сохранения повторяющегося значения результирующей частоты
        int repeatFrequency = 0;
        // коллекция для хранения результирующтх частот
        int resultingFrequency_part2 = 0;
        // делать пока равно значение "повторяющейся частоты" равно 0

        while (repeatFrequency == 0) {
            // пройти по коллекции считанных строк
            for (int i = 0; i < linesArrList.size(); i++) {
                String currentLine = linesArrList.get(i);
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

    private int getIntFromString(String digitString) {
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
    private int changeSignDigit(String allLine, int currentDigit) {
        if (allLine.charAt(0) == '-') {
            currentDigit = -currentDigit;
        }
        return currentDigit;
    }
}