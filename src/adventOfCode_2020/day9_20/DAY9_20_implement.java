package adventOfCode_2020.day9_20;

import common.AocSolverAbstract;

import java.util.*;

public class DAY9_20_implement extends AocSolverAbstract<Long, Long> {

    private  int preamble = 25; // количество символов, что берем из списка чисел (5, 25). Меняется во 2 части много раз
    private ArrayList<XMAS> allObjXMAS = new ArrayList<XMAS>(); // Меняется во 2 части много раз
    long invalid = 0; // хранит число из 1 части

    // сохранить все выборки преамбулы-маска
    @Override
    protected void prepareCommonData(ArrayList<String> linesArrList) {

        XMAS currentXMAS_Obj;
        final int CONDITION = linesArrList.size() - preamble; // аналог   (2^(длина/преамбула)) - 1
        for (int indexLine = 0; indexLine < CONDITION; indexLine++) {
            currentXMAS_Obj = new XMAS();

            // заполнить преамбулу значениями, условие выхода учитывает смещение по списку на 1
            for (int indexPreamble = indexLine; indexPreamble < indexLine + preamble; indexPreamble++) {
                Long currentDigit = getDigitLong(linesArrList, indexPreamble);
                currentXMAS_Obj.preambleArrLis.add(currentDigit);
            }

            // учесть следующее за преамбулой число,  указывает на ИНДЕКС массива
            currentXMAS_Obj.setMask(getDigitLong(linesArrList, preamble + indexLine));

            // сохранить объект
            allObjXMAS.add(currentXMAS_Obj);
        }
    }

    // создать число из строки
    private Long getDigitLong(ArrayList<String> linesArrList, int indexPreamble) {
        return Long.valueOf(linesArrList.get(indexPreamble));
    }

    // часть 1: Найти не действительное число, находя два числа которые дадут в сумме указанное число (mask)
    @Override
    protected Long calculatePart1_Solution(ArrayList<String> linesArrList) {

        // Пройти по всем объектам
        for (int indexObj = 0; indexObj < allObjXMAS.size(); indexObj++) {

            // заполнить  HashMap уникальными значениями  из всей коллекции
            Map<Long, Integer> currentPreambleMap = new HashMap<Long, Integer>();

            int indexDiginInArr = 0;
            for (Long currentDigit : allObjXMAS.get(indexObj).getPreambleArrLis()) {
                currentPreambleMap.put(currentDigit, indexDiginInArr);
                // !!!! Сохраняет последний индекс из двух идентичных
                // ? переопределить метод сохранения, но не нужно тк индекс не используется
                indexDiginInArr++;
            }

            // проитерироваться по map в поисках двух чисел
            Long currentMask = allObjXMAS.get(indexObj).getMask();
            int indexXMAS = 0; // для учета длины map
            for (Long currentDigit : currentPreambleMap.keySet()) {
                long currentDiff = currentMask - currentDigit;


                 // если маска есть суммма двух, то прервать цикл
                if (currentPreambleMap.containsKey(currentDiff) && currentDiff != currentDigit) {  // исключить сложение одинаковых чисел
                    break;
                    // если конец и нет нужного числа то возврат маски
                } else if (indexXMAS == currentPreambleMap.size() - 1) {
                    invalid = currentMask;
                    return currentMask; // выедет то число
                }
                indexXMAS++;
            }
        }
        return null;
    }


    //  часть 2 : Найти последовательную группу чисел, что дадут недействительное число из прошлого решения
    @Override
    protected Long calculatePart2_Solution(ArrayList<String> linesArrList) {


        // пройти с увеличение размера преамбулы
        for(int currentSizePreamble = 2; currentSizePreamble < linesArrList.size(); currentSizePreamble++ ) {

            // переопределить преамбулу
            this.preamble = currentSizePreamble; // неявное преобразование, но currentSizePreamble всегда будет int

            // переопределить коллекцию объектов allObjXMAS
            allObjXMAS.clear();

            // группировать по 2, по 3, по 4 и тд
            prepareCommonData(linesArrList);

            // пройти по коллеции текущего объекта, так по всем объектам
            for (int indexObj = 0; indexObj < allObjXMAS.size(); indexObj++) {

                long currentSum = allObjXMAS.get(indexObj).getSumInPreambleArrLis();

                // сравнить сумму вошедших в коллекцию чисел с invalid
                if (invalid == currentSum) {

                    // найти min max в коллекции
                    long maxDigit = Collections.max(allObjXMAS.get(indexObj).preambleArrLis);
                    long minDigit = Collections.min(allObjXMAS.get(indexObj).preambleArrLis);

                    long weakness = maxDigit + minDigit;
                    return weakness;

                }
            }
        }
        return null;
    }
}



















