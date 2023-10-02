package adventOfCode_2020.DAY3_20;

import common.AocSolverAbstract;

import java.io.IOException;
import java.util.ArrayList;

public class DAY3_20_implement extends AocSolverAbstract<Integer, Integer> {


    private char[][] mapThereArr;

    // поля будут проинициализированы в функции подготовки данных
    private int row = 0;
    private int column = 0;
    private int countThree = 0;


    // Записать карту деревьев в глобавльный двумерный массив символов
    @Override
    protected void prepareCommonData(ArrayList<String> linesArrList) {
        this.row = linesArrList.size();
        char[] currentLineCharArr = linesArrList.get(0).toCharArray();
        this.column = currentLineCharArr.length;
        this.mapThereArr = new char[row][column];

        for (int indexRow = 0; indexRow < linesArrList.size(); indexRow++) {
            currentLineCharArr = linesArrList.get(indexRow).toCharArray();

            //записать в каждую ячейку двумерного массива элемент из текущей строки
            for (int indexColumn = 0; indexColumn < column; indexColumn++) {
                mapThereArr[indexRow][indexColumn] = currentLineCharArr[indexColumn];
            }
        }
    }


    // пройти по двумерному массиву с условием +3 вправо, +1 вниз, до достижения нижней строки.
    // подсчитать количество попадений в деревья "#"
    @Override
    protected Integer calculatePart1_Solution(ArrayList<String> linesArrList) {
        prepareCommonData(linesArrList);
        int stepRight = 3; // шаг вправо по строке
        int stepDown = 1;
        if (getCountThree(stepRight, stepDown)) {
            return countThree; // ????
        }
        return countThree;
    }

    @Override
    protected Integer calculatePart2_Solution(ArrayList<String> linesArrList) {


        int[] stepRight = {1, 3, 5, 7, 1};
        int[] stepDown = {1,1,1,1,2};
        int sizeArr = 5;
        int product = 1;
        int[]test = new int[5];
        int testPriduct = 0;

        for(int indexArr = 0;indexArr < sizeArr ;indexArr++){

            getCountThree(stepRight[indexArr], stepDown[indexArr]);
            test[indexArr] = countThree;
            product *= countThree;

        }
        for (int i =0; i < sizeArr; i++){
            testPriduct = test[0] * test[1] * test[2] * test[3] * test[4];
        }
        System.out.println("testPriduct = " + testPriduct);

        return product;
    }

    // Получить количество встреченных деревьев на карте по указанным параметрам
    // передвижения "#"
    private boolean getCountThree(int stepRight, int stepDown) {
        this.countThree = 0;
        for (int indexRow = 0; indexRow < this.row; ) {

            char tree = '#';

            // проверить наличие дерева и посчитать их
            for (int indexColumn = 0; indexColumn < column; ) {

                //System.out.println("row = " + indexRow + ", column = " + indexColumn);
                char currentPositionChar = mapThereArr[indexRow][indexColumn];
                //System.out.println("currentPositionChar " + currentPositionChar);
                if (tree == currentPositionChar) {
                    countThree++;
                }

                // условие движения по карте.
                // проверка за выход количества столбцов, при наличии будующего выхода за предел
                // прокрутить точку отчета столбцов до начала
                if ((indexColumn + stepRight) > column) {
                    // логика: предконец + stepRight -  column
                    indexColumn = (indexColumn + stepRight) - column;
                } else {
                    indexColumn += stepRight;
                }
                indexRow += stepDown;

                // проверить выхождение за количетсво строк
                if (indexRow >= this.row) {
                    return true;
                }
            }
        }
        return false;
    }


}
