package adventOfCode_2020.DAY1_20;

import common.AocSolverAbstract;

import java.util.ArrayList;



// общий класс для копирования в каждый каталог задач
public class DAY1_20_implement extends AocSolverAbstract<Integer, Integer> {

    private int oneDigit = 0;
    private int twoDigit = 0;
    private  int threeDigit = 0;
    ArrayList<Integer> entriesArr = new ArrayList<Integer>();

    @Override
    protected void prepareCommonData(ArrayList<String> linesArrList) {

        for (int indexLine = 0; indexLine < linesArrList.size(); indexLine++) {
            int currentDigit = Integer.parseInt(linesArrList.get(indexLine));
            entriesArr.add(currentDigit);
        }
    }

    @Override
    protected Integer calculatePart1_Solution(ArrayList<String> linesArrList) {
        int multiplying = 0;

        // поиск пары чисел, что дадут 2020 в сумме
        for (int indexArr = 0; indexArr < entriesArr.size(); indexArr++) {

            oneDigit = entriesArr.get(indexArr);

            for(int indexTwoDigit = indexArr + 1; indexTwoDigit < entriesArr.size(); indexTwoDigit++){
                twoDigit = entriesArr.get(indexTwoDigit);
                int add = (oneDigit + twoDigit);
                if (add == 2020) {
                    multiplying = oneDigit * twoDigit;
                    System.out.println("Part 1, one = " + oneDigit + ", two = " + twoDigit + ", Multiplying = " + multiplying);

                    return multiplying;
                }
            }
        }
        multiplying = oneDigit * twoDigit;
        System.out.println("Part 1, one = " + oneDigit + ", two = " + twoDigit + ", Multiplying = " + multiplying);


        return multiplying;
    }

    @Override
    protected Integer calculatePart2_Solution(ArrayList<String> linesArrList) {
        int multiplying = 0;
        // поиск трех чисел, что дадут 2020 в сумме
        for (int indexArr = 0; indexArr < entriesArr.size(); indexArr++) {
            //oneDigit = entriesArr.get(indexArr);
            for(int indexTwoDigit = 0; indexTwoDigit < entriesArr.size(); indexTwoDigit++){
               // twoDigit = entriesArr.get(indexTwoDigit);
                for (int indexThreeDigit = 0; indexThreeDigit < entriesArr.size(); indexThreeDigit++){
                 //  threeDigit = entriesArr.get(indexThreeDigit);
                    oneDigit = entriesArr.get(indexArr);
                    twoDigit = entriesArr.get(indexTwoDigit);
                    threeDigit = entriesArr.get(indexThreeDigit);
                    int add = (oneDigit + twoDigit + threeDigit);
                    if (add == 2020) {
                        multiplying = oneDigit * twoDigit * threeDigit;
                        System.out.println("Part 2, one = " + oneDigit + ", two = " + twoDigit +
                        ", Multiplying = " + multiplying);
                        return multiplying;
                    }
                }
            }
        }
//        multiplying = oneDigit * twoDigit * threeDigit;
//        System.out.println("Part 2, one = " + oneDigit + ", two = " + twoDigit +
//                ", Multiplying = " + multiplying);
        return -1;
    }
}
