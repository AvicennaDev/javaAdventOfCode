package adventOfCode_2020.DAY3_20;

import common.AocSolverAbstract;
import java.util.ArrayList;

public class DAY3_20_implement extends AocSolverAbstract<Integer, Long> {


    private char[][] mapThereArr; // ? может дать путаницу с map

    // поля будут проинициализированы в функции подготовки данных
    private int row = 0;
    private int column = 0;
    private int countThree = 0;


    // Записать карту деревьев в глобавльный двумерный массив символов
    @Override
    protected void prepareCommonData(ArrayList<String> linesArrList) {
        this.row = linesArrList.size();
        char[] currentLineCharArr = linesArrList.get(0).toCharArray(); // 0 тк необходимо узнать ширину по любой строчке
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
        final int STEP_RIGHT = 3; // шаг вправо по строке
        final int STEP_DOWN = 1;
        if (getCountThree(STEP_RIGHT, STEP_DOWN)) {
            return countThree;
        }
        return countThree;
    }

    // Часть 2
    // Движение с разными шагами вправо и вниз и произведение деревьев на каждую пару условий
    // возвращает Long тк выходит за диапазон int(и даже обертка инта не меняет это)
    @Override
    protected Long calculatePart2_Solution(ArrayList<String> linesArrList) {

        final int[] STEP_RIGHT = {1, 3, 5, 7, 1}; // каждая ячека - шаг в право
        final int[] STEP_DOWN = {1, 1, 1, 1, 2};  // каждая ячейка - шаг вниз
        final int SIZE_ARR   = STEP_DOWN.length; // длина массива пар движений
        long product = 1; //  каждое последующее произведение попавшихся деревьев. с 1, тк с 0 всегда будет 0

        // получить и перемножить все деревья для каждой пары шагов "вправо-вниз"
        for (int indexArr = 0; indexArr < SIZE_ARR; indexArr++) {
            getCountThree(STEP_RIGHT[indexArr], STEP_DOWN[indexArr]);
            product *= countThree;
        }
        return product;
    }


    // для обеих частей
    // Получить количество встреченных деревьев на карте по указанным параметрам
    // передвижения "#"
    private boolean getCountThree(final int STEP_RIGHT, final int STEP_DOWN) {
        this.countThree = 0; // важно для части 2, когда метод вызывается много раз
        for (int indexRow = 0; indexRow < this.row; ) {

            final char TREE = '#';

            // проверить наличие дерева и посчитать их
            for (int indexColumn = 0; indexColumn < column; ) {

                // временно хранит текущий символ
                char currentPositionChar = mapThereArr[indexRow][indexColumn];
                // учет того, что сейчас в той ячейке, что и дерево т.е. врезался в дерево
                if (TREE == currentPositionChar) {
                    countThree++;
                }

                // условие движения по карте.
                // проверка за выход количества столбцов, при наличии будующего выхода за предел
                // прокрутить точку отчета столбцов до начала
                if ((indexColumn + STEP_RIGHT) > column) {
                    // логика: предконец + stepRight -  column, column = всего столбцов (ширина)
                    indexColumn = (indexColumn + STEP_RIGHT) - column;
                } else {
                    indexColumn += STEP_RIGHT;
                }
                indexRow += STEP_DOWN;

                // проверить выхождение за количетсво строк
                if (indexRow >= this.row) {
                    return true;
                }
            }
        }
        return true;
    }

}
