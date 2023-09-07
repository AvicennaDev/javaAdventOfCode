package adventOfCode_2018.day5_18;

import common.AocSolverAbstract;

import java.util.ArrayList;

// общий класс для копирования в каждый каталог задач
public class DAY5_18_implement extends AocSolverAbstract<Integer, Integer> {


    @Override
    protected Integer calculatePart1_Solution(ArrayList<String> linesArrList) {
        String inputLineString = linesArrList.get(0); //dabAcCaCBAcCcaDA
        StringBuilder  inputLineStBui = new StringBuilder(inputLineString) ;  // new StringBuilder(inputLineString);         // разнообразные конструкторы
        int lengtWithoutPair = getLengthString(inputLineStBui);
        return lengtWithoutPair;
    }

    @Override
    protected Integer calculatePart2_Solution(ArrayList<String> linesArrList) {
        //Map<Integer,Integer> unitsMap = new HashMap<Integer, Integer>();
        ArrayList<Integer> lengthStrinArr = new ArrayList<Integer>(); // для хранения длин (чисел)

        StringBuilder stringForPart1 = new StringBuilder();
        int minLength = Integer.MAX_VALUE;
        // взять символ аски
        int countLetter = 25; // в алфавите 25 букв
        int startUpperCase = 65;
        for( int charIndex = startUpperCase; charIndex < (countLetter+startUpperCase); charIndex++) {
            String currentString = linesArrList.get(0);
            StringBuilder withoutCase = new StringBuilder(currentString);
            // пройти по строке, удалить не нужные символы
            for(int indexString = 0; indexString < withoutCase.length(); indexString++){
                char curentChar = withoutCase.charAt(indexString);
                if((curentChar == charIndex) ||((charIndex+32) == curentChar)){ // проверка содержит ли символ любого регистра
                    withoutCase.deleteCharAt(indexString);
                    indexString--; //  что бы не пропустил последующий символ
                }
            }
            // сохранить текущую строку в stringForPart1
            // использовать метод 1 части
            int currentLengthString =  getLengthString( withoutCase);

            // сравнить длину(найти минимум), сохранить минимум
            if(currentLengthString < minLength){
                minLength = currentLengthString;
            }
        }

        return minLength;
    }


    private int getLengthString(StringBuilder inputLineStBui) {
        boolean DeleteUnits = true;
        boolean endBool = true;
        do {
            // идет по каждой паре символов
            for (int indexStart = 0; indexStart < inputLineStBui.length() - 1; indexStart++) {
                char startChar = inputLineStBui.charAt(indexStart);
                char endChar = inputLineStBui.charAt(indexStart + 1);

                int difference = startChar - endChar;
                //если разница между числовыми значениями симолов 32, то удалить пару символов

                if (32 == Math.abs(difference)) {
                    inputLineStBui.delete(indexStart, indexStart + 2); //  удаляет точно первый индекс, не включая второй
                    DeleteUnits = true;
                }
            }
            if (DeleteUnits == false) {
                endBool = false;
            }
            DeleteUnits = false;
            // если сейчас конец, то проверить было ли удаление или включить проверку заново
//            if(deleteUnits == false){
//                break;
//            }
        } while (endBool);
        return inputLineStBui.length();
    }
}
