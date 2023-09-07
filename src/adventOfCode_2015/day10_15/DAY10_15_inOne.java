package adventOfCode_2015.day10_15;
// https://adventofcode.com/2015/day/10

public class DAY10_15_inOne {

    /**
     *
     * Например:
     *
     * 1становится 11( 1копия цифры 1).
     * 11становится 21( 2копии цифры 1).
     * 21становится 1211(один, 2за которым следует один 1).
     * 1211становится 111221(один 1, один 2и два 1s).
     * 111221 становится 312211(три 1s, два 2s и один 1).
     *
     * ввод 1321131112
     * часть 1 - 40 раз, ответ 492982
     * часть 2 - 50 раз, ответ 6989950
     *
     */

    public static void main(String[] args) {

        String sequencesStr = "1";
        int time = 5;
        StringBuilder sequencesSB = new StringBuilder(sequencesStr);
        for (int currentTime = 0; currentTime < time; currentTime++) {
//            StringBuilder result = new StringBuilder(); // хранит конечную строку "смотри-говори"
            StringBuilder result = new StringBuilder(); // хранит конечную строку "смотри-говори"
            //int currentCount = 0;
            int countDigit = 0; // count
            char charInSB = '0';
            for (int currentIndexChar = 0; currentIndexChar < sequencesSB.length(); currentIndexChar++) {
                char currentChar = sequencesSB.charAt(currentIndexChar);
                if (currentChar != charInSB) {

                    // если не равно 0, то добавить количество, добавить текущий символ
                    if (charInSB != '0') {
                        result.append(countDigit);
                        result.append(charInSB);
                    }
                    charInSB = currentChar;
                    countDigit = 1;
                } else {
                    countDigit++;

                }
            }
            result.append(countDigit);
            result.append(charInSB);
            sequencesSB = result;
        }
        //System.out.println(sequencesStr.toString());
        System.out.println(sequencesSB.toString());
    }
}
