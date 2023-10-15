package adventOfCode_2020.DAY5_20;

import common.AocSolverAbstract;

import java.util.ArrayList;


public class DAY5_20_implement extends AocSolverAbstract<Integer, Integer> {

    final int ONE = 1;  // учет 1 того, что числа считаются от 0 ONE= 1;
    final int ROWS_ON_PLANE = 127; // ряды в самолете (от 0 до 127, всего 128)
    final int TWO = 2;

    @Override
    protected void prepareCommonData(ArrayList<String> linesArrList) {
        super.prepareCommonData(linesArrList);
    }

    @Override
    protected Integer calculatePart1_Solution(ArrayList<String> linesArrList) {


        int desiredRow = 0;
        int desiredColumn = 0;


        // получить значение строки в самолете из 0-127
        desiredRow = getRow_1(linesArrList);


        return null;
    }

    // получить значение строки в самолете из 0-127
    private int getRow_1(ArrayList<String> linesArrList) {

        final int sizeArrStr = linesArrList.size();
        int earlyHalf = 0; // предыдущее значение половины
        int currentHalf = 0; // текущая половина диапазона
        int lower = 0;   //нижнее значение диапазона,
        int upper = 0;   //верхнее значение диапазона.

        for (int indexStr = 0; indexStr < sizeArrStr; indexStr++) {


            String currentStr = linesArrList.get(indexStr);
            final int THREE = 3; // выделить из строки в 10 символов только нужные 7
            final int SIZE_STR_F_B = currentStr.length() - THREE; // длина строки символов


            for (int indexChar = 0; indexChar < SIZE_STR_F_B; indexChar++) {


                    // если первая итерация, то значение предыдущей
                    // половины все число строк, иначе пересохранить текущее значение в нее
                    if (indexChar == 0) {
                        earlyHalf = ROWS_ON_PLANE + ONE;
                    } else {
                        earlyHalf = currentHalf;
                    }
                    currentHalf = earlyHalf / TWO;
                    char currentChar = currentStr.charAt(indexChar); // получить символ под текущим индексом


                    //
                    if (currentChar == 'F') {
                        //lower не меняется

                        // если впервый раз делается, то  верхнее значение это половина исходного наксимума
                        if(indexChar == 0){
                            upper = currentHalf - ONE;
                        } else {
                            upper = earlyHalf - ONE; // получить верхний предел диапазона
                        }

                    } else if (currentChar == 'B') {
                        lower = currentHalf;
                        upper = earlyHalf - ONE;
                    }


            }
        }
        return 0;
    }

    @Override
    protected Integer calculatePart2_Solution(ArrayList<String> linesArrList) {
        return null;
    }
}






















