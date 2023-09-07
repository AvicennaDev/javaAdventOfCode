package adventOfCode_2018.day2_18;
import common.AocSolverAbstract;

import java.util.ArrayList;

public class Day2Year18_Implement extends AocSolverAbstract<Integer, String> {


    /**
     * если вы видите следующие идентификаторы ящиков:
     * <p>
     * abcdefне содержит букв, встречающихся ровно два или три раза.
     * bababc содержит два aи три b, поэтому считается за оба.
     * abbcde содержит два b, но ни одна буква не встречается ровно три раза.
     * abcccdс одержит три c, но ни одна буква не встречается ровно два раза.
     * aabcdd содержит два aи два d, но считается только один раз.
     * abcdee содержит два e.
     * ababab содержит three aи three b, но считается только один раз.
     * Из этих идентификаторов ящиков четыре содержат букву,
     * которая встречается ровно два раза, а три из них содержат букву,
     * которая встречается ровно три раза. Их умножение дает контрольную сумму 4 * 3 = 12.
     */

//  тест 1
//    abcdef
//    bababc
//    abbcde
//    abcccd
//    aabcdd
//    abcdee
//    ababab


//    тест 2
//    abcde
//    fghij
//    klmno
//    pqrst
//    fguij
//    axcye
//    wvxyz
    @Override

    protected Integer calculatePart1_Solution(ArrayList<String> linesArrList) {
        int count2 = 0; // для хранения количетсва появления двух одинаковых символов в строке
        int count3 = 0; // для хранения количетсва появления двух одинаковых символов в строке

        for (int indexLine = 0; indexLine < linesArrList.size(); indexLine++) {
            char[] currentLineForArrList = linesArrList.get(indexLine).toCharArray(); // разложить строку на символы
            // пройти по массиву символов для подчсета 2-3 символов
            boolean count2Bool = true;
            boolean count3Bool = true;

            //коллекция для хранения символов которые уже подсчитаны
            ArrayList<Character> existCharArrLis = new ArrayList<Character>();

            for (int indexChar = 0; indexChar < currentLineForArrList.length; indexChar++) {
                char currentChar = currentLineForArrList[indexChar];
                // выйти с поиска в строке текущего символа, если его аналог исклася ранее
                if (existCharArrLis.contains(currentChar)) {
                    continue;
                }

                int repeatCount = 1; // для подсчёта символов 2 или 3, считает буквы поштучно с учетом текущей 1
                // проверить наличие текущего символа в строке и подсчитать количество
                for (int i = indexChar + 1; i < currentLineForArrList.length; i++) {
                    char checkedChar = currentLineForArrList[i];
                    if (currentChar == checkedChar) {
                        repeatCount++;
                    }
                }

                if (repeatCount == 2 && count2Bool) {
                    count2++;
                    count2Bool = false;
                }
                if (repeatCount == 3 && count3Bool) {
                    count3++;
                    count3Bool = false;
                }

                existCharArrLis.add(currentChar);
            }
        }

        int result = count2 * count3;
        return result;
    }

    @Override
    protected String calculatePart2_Solution(ArrayList<String> linesArrList) {

        // basicLine  линия которая сравнивается с другими линиями по коллекции
        for (int basicLineInt = 0; basicLineInt < linesArrList.size() - 1; basicLineInt++) {


            char[] basiclineArr = linesArrList.get(basicLineInt).toCharArray();

            for (int currentLine = basicLineInt + 1; currentLine < linesArrList.size(); currentLine++) {
                int noRepeat = 0; // переменная для отметки количества отличий
                char[] currenLineArr = linesArrList.get(currentLine).toCharArray();
                //
                char deleteChar = '\0';
                int deleteInd = 0; // для хранения индекса символая на удаление
                // сравнивает две взятые строки
                for (int i = 0; i < basiclineArr.length; i++) {

                    // сравнить два символа строки с одинаковым индексом
                    if (basiclineArr[i] != currenLineArr[i]) {
                        noRepeat++;
                        deleteInd = i;
                        deleteChar = basiclineArr[i];
                    }
                    // если количество не совпадений в строках не 1, то строки дальше не сравнивать
                    if (noRepeat > 1) {
                        break;
                    }
                }

                // если строки совпали и отличны на 1 символ
                // вывести строку удавлив символ
                // прекратить работу функции return
                if (noRepeat == 1){
                    /**
                     * Массив символов (char[]), не является строковым классом (String).
                     * Для конвертации массива символов в строку,
                     * необходимо создать эту строку из массива символов.
                     */
                    String getStrForChar = new String(basiclineArr);
                    // для replaceAll необходимо в параметры предать строку
                    String delString = new String(String.valueOf(deleteChar));
                    String oneLine = getStrForChar.substring(0,deleteInd);
                    String twoLine = getStrForChar.substring(deleteInd);
                    //getStrForChar.replaceAll(delString,""); // вернуть подстроку без символа
                    String allLine = oneLine + twoLine;
                    return  allLine;

                }
                // нет условия, что нет не совпадающих строк
            }
            //  System.out.println(basicLineInt);
        }
        return null;
    }
}
