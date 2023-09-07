package adventOfCode_2018.day3_18;

//https://adventofcode.com/2018/day/3

// добавить класс на получение чисел сторон

import common.AocSolverAbstract;

import java.util.ArrayList;

/**
 * Подобное утверждение #123 @ 3,2: 5x4означает, что идентификатор утверждения
 * 123 указывает прямоугольник 3 в дюймах от левого края, 2 в дюймах от верхнего края
 * , 5 в дюймах в ширину и 4 в дюймах в высоту.
 * Четыре квадратных дюйма, отмеченные значком , X принадлежат как , так 1и2 .
 * (Утверждение 3, хотя и является смежным с другими, не перекрывает ни одно из них.)
 * <p>
 * Сколько квадратных дюймов ткани находится в пределах двух или более претензий?
 */

// тест 1
//        #1 @ 1,3: 4x4
//        #2 @ 3,1: 4x4
//        #3 @ 5,5: 2x2
//    длина полотна 8*8, в задаче 1000*1000
//        ........
//        ...2222.
//        ...2222.
//        .11XX22.
//        .11XX22.
//        .111133.
//        .111133.
//        ........

// тест 2


public class Day3_18_Implement extends AocSolverAbstract<Integer, Integer> {

    private int overallPlains = 0; // длины стороны полотна, для теста 8, для задачи 1000, в конструкторе
    private int[][] plansArr; // полотно планов в целом
    private ArrayList<Sector> allPlainFromFile = new ArrayList<Sector>(); // для хранения всех объектов строк-планов


    public Day3_18_Implement(int rowColumn) {
        this.overallPlains = rowColumn;
        // если инициализировать массив вне конструктора, то он примет overallPlains заданное вне конструктора
        this.plansArr = new int[overallPlains][overallPlains]; // пространство для размещения всех лоскутов
    }

    // заполнить двумерный массив (полотно со всеми планами)
    @Override
    protected void prepareCommonData(ArrayList<String> linesArrList) {
        // переменные для хранения дюймов
        int left = 0; // расстояние от левого края
        int top = 0; // расстояние от верхнего края
        int width = 0; // ширина лоскута
        int height = 0; // высота лоскута

        for (int indexNumPlan = 0; indexNumPlan < linesArrList.size(); indexNumPlan++) {

            // создать объект для формирования дюймов в числовые значения из строк
            Sector currentPlans = new Sector(linesArrList.get(indexNumPlan));
            left = currentPlans.getLeft();
            top = currentPlans.getTop();
            width = currentPlans.getWidth();
            height = currentPlans.getHeight();

            // Сначало заполнит строку по столбцам,  <= включит последнее число.
            // Перейти на строку, при этом сделать в ней отступ от края,
            // начать со след строки, число с 1, а индекс с 0 поэтому нет +1
            for (int row = top; row < (height + top); row++) {
                // Заполнить строку по столбцам,
                // при этом сделать отступ от верха,
                // начать со след столбца, число с 1, а индекс с 0 поэтому нет +1
                for (int column = left; column < (width + left); column++) {
                    this.plansArr[row][column]++; // заполнить место полотна 1, если увеличится еще, значит было пересечение планов
                }
            }
            this.allPlainFromFile.add(currentPlans); // нужно для 2 части, сохранить текущий объект
        }
    }

    // получить количество пересечений планов в двумерном массиве
    @Override
    protected Integer calculatePart1_Solution(ArrayList<String> linesArrList) {

        int countOverlap = 0; // количество перекрытий
        // пройти по массиву и посчитать количество пересечений
        for (int row = 0; row < plansArr.length; row++) {
            for (int column = 0; column < plansArr.length; column++) {
                if (plansArr[row][column] > 1) {
                    countOverlap++;
                }
            }
        }
        return countOverlap;
    }

    // получить номер плана, который ни с кем не пересекается
    @Override
    // пройти по участку в двумерном массиве, равному участку из считанной
    // строки(объекты хранят свои параметры)
    protected Integer calculatePart2_Solution(ArrayList<String> linesArrList) {

        int indexPlan = 0;
//        for (int row = 0; row < plansArr.length; row++) {
//            for (int column = 0; column < plansArr.length; column++) {
//                // если в ячейке не более чем 1, то вывести номер плана
//                // н омера двумерного массива совпадают с номерами планов
//            }
//
//        }

        // в пределах текущего лоскута найти значения больше 1
        // пройти по порядку по коллекции хранящей лоскуты
        for (int numberPlan = 0; numberPlan < allPlainFromFile.size(); numberPlan++) {
            // в рамках параметров лоскута найти наличие числа более 1.
            // получить данные текущего объекта
            int currentTopObj = allPlainFromFile.get(numberPlan).getTop();
            int currentHeightObj = allPlainFromFile.get(numberPlan).getHeight();
            int currentLeftObj = allPlainFromFile.get(numberPlan).getLeft();
            int currentWidthObj = allPlainFromFile.get(numberPlan).getWidth();
            // повторить  for из prepareCommonData
            int areaPlanObj = currentHeightObj * currentWidthObj; // текушая площадь объекта хранящего параметры из файла
            int areaPlanInArr = 0; // сложение содержимого в лоскуте массива даст площадь  с наличием значений более 1
            for (int row = currentTopObj; row < (currentHeightObj + currentTopObj); row++) {
                for (int column = currentLeftObj; column < (currentWidthObj + currentLeftObj); column++) {
                    areaPlanInArr += this.plansArr[row][column];

                }
            }
            // сравнить площади полученную и исходную
            if (areaPlanObj == areaPlanInArr) {
                indexPlan = numberPlan + 1;
                break;
            }

        }

        return indexPlan;
    }

    int[] getDigitPair(String separator, String subStringForArrLis) {
        String[] splitPair = subStringForArrLis.split(separator);

        int[] numberPair = new int[2];

        numberPair[0] = Integer.parseInt(splitPair[0]);
        numberPair[1] = Integer.parseInt(splitPair[1]);

        return numberPair;
    }
}


// тест работы двумерн массива [row][column]
//        for (int row = 0; row < 5; row++) {
//            System.out.println();
//            for (int column = 0; column < 3; column++) {
//                System.out.print(" * ");
//            }
//        }