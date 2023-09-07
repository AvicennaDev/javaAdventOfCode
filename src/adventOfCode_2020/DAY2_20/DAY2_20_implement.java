package adventOfCode_2020.DAY2_20;

import common.AocSolverAbstract;

import java.util.ArrayList;

// общий класс для копирования в каждый каталог задач
public class DAY2_20_implement extends AocSolverAbstract<Integer, Integer> {

    Line[] allLineObj;

    @Override
    protected void prepareCommonData(ArrayList<String> linesArrList) {
        // логика 1
        // Создать объект "Линия", заполнив ее поля:
        // начальное и конечное допустимое значение,
        // текущий важный символ строки,
        // массив символов текущей строки.
        allLineObj = new Line[linesArrList.size()];

        // логика 2
        // заполнить массив объектов Line
        for(int indexString = 0; indexString < linesArrList.size(); indexString++){
            allLineObj[indexString] = getLine(linesArrList.get(indexString));
        }

    }

    @Override
    protected Integer calculatePart1_Solution(ArrayList<String> linesArrList) {
        int countValidPassword = 0;
        for (int indexString = 0; indexString < linesArrList.size(); indexString++) {
            // логика 3
            // заполнить количество повторений важного символа в самом объекте
            allLineObj[indexString].getCountChar(allLineObj[indexString]);
            // логика 4
            // учесть допустимый пароль по правилам части 1
            int countChar = allLineObj[indexString].getCountChar();
            int start = allLineObj[indexString].getStart();
            int finish = allLineObj[indexString].getFinish();

            if ((countChar >= start) && (countChar <= finish)) {
                countValidPassword++;
            }
        }
        return countValidPassword;
    }



    @Override
    protected Integer calculatePart2_Solution(ArrayList linesArrList) {
        int countValidPassword = 0;
        for (int indexString = 0; indexString < linesArrList.size(); indexString++) {
            // логика 4
            // учесть допустимый пароль в объекте по правилам части 2
            allLineObj[indexString].getBoolenValidPassword(allLineObj[indexString]);

            boolean currentObj = allLineObj[indexString].isValidPassword();
            if (currentObj == true) {
                countValidPassword++;
            }
        }
        return countValidPassword;
    }

    // Заполнить поля объекта
    private Line getLine(String string) {

        String[] passwordAndPolicy = string.split(" ");
        String[] digitsPolicy = passwordAndPolicy[0].split("-");

        int start = Integer.parseInt(digitsPolicy[0]);
        int finish = Integer.parseInt(digitsPolicy[1]);

        char currentChar = passwordAndPolicy[1].charAt(0);
        char[] arrChar = passwordAndPolicy[2].toCharArray();
        Line currentLineObj = new Line(start, finish, currentChar, arrChar);

        return currentLineObj;
    }
}
