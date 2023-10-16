package adventOfCode_2020.DAY5_20;

import common.AocSolverAbstract;

import java.util.ArrayList;


public class DAY5_20_implement extends AocSolverAbstract<Integer, Integer> {

    final int ONE = 1;  // учет 1 того, что числа считаются от 0 ONE= 1;
    final int TWO = 2;

    @Override
    protected void prepareCommonData(ArrayList<String> linesArrList) {
        super.prepareCommonData(linesArrList);
    }

    @Override
    protected Integer calculatePart1_Solution(ArrayList<String> linesArrList) {


        int desiredRow = 0;
        int desiredColumn = 0;
        final int countLine = linesArrList.size();
        int highestId = 0;

        // пройти по всем строкам
        for (int indexLine = 0; indexLine < countLine; indexLine++) {

            // получить значение строки в самолете из 0-127
            String currentLine = linesArrList.get(indexLine);
            desiredRow = getRow_1(currentLine);
            desiredColumn = getColunm_1(currentLine);

            // получить идентификатор по указанным условиям
            highestId = getHighestId_part1(highestId, desiredRow, desiredColumn);

            int t = 8;
        }

        return highestId;
    }

    // получить идентификатор по указанным условиям
    private int getHighestId_part1(int highestId, int desiredRow, int desiredColumn) {

        // формула получения id по условию части 1
        // id = row * 8 + column
        int currentId = 0;
        final int RATIO = 8; // коэффциент по условию задачи
        currentId = desiredRow * RATIO + desiredColumn;
        if(highestId < currentId){
            highestId = currentId;
        }
        return highestId;
    }

    //

    // получить значение столбца(ряда) для части 1
    private int getColunm_1(String currentLine) {
        int column = 0;

        int lower = 0;   //нижнее значение диапазона,
        int upper = 7;   //верхнее значение диапазона.
        final int FROM = 7; // выделить из строки в 10 символов только нужные последнии 3

        String currentStr = currentLine.substring(FROM);

        // выбрать одно значение из каждой пары - пройти по отрезкам
        column = chooseBetweenFBorLR(lower, upper, currentStr);
        return column;
    }

    // получить значение строки в самолете из 0-127,
    private int getRow_1(String currentLine) {

        int row = 0;
        int lower = 0;   //нижнее значение диапазона,
        int upper = 127;   //верхнее значение диапазона.
        final int BEFORE = 3; // выделить из строки в 10 символов только нужные 7

        // выбрать одно значение из каждой пары - пройти по отрезкам
        String currentStr = currentLine.substring(0, currentLine.length() - BEFORE);
        row = chooseBetweenFBorLR(lower, upper, currentStr);

        return row;
    }

    // логика выбора диаопазона на отрезке ( перебор значений между диапазонами)
    private int chooseBetweenFBorLR(int lower, int upper, String currentStr) {
        char[] charArr = currentStr.toCharArray();

        // выбрать между F и B для строк, или между L и R для столбцов (рядов)
        // при установке нижнего или верхнего предела всегда использовать формулу середины отрезка
        for (char currentChar : charArr) {
            if (currentChar == 'F' || currentChar == 'L') {

                // формула середины отрезка(геометрия), округление всегда будет вниз
                upper = (int) Math.floor((lower + upper) / TWO);
            } else {

                // округление вверх
                lower = (lower + upper) / TWO + ONE;
            }
        }
        // выбран один из 2 пределов, в теории они идентичны
        return upper;
    }

    @Override
    protected Integer calculatePart2_Solution(ArrayList<String> linesArrList) {
        return null;
    }
}






















