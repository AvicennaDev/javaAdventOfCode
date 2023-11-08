package adventOfCode_2020.DAY8_20;

import common.AocSolverAbstract;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.*;

public class DAY8_20_implement extends AocSolverAbstract<Integer, Integer> {

    // map  для хранения объектов "инструкции"
    private Map<Integer, Instraction> instractionMap = new HashMap<Integer, Instraction>();
    private int accumulator; // хранит значение аккомулятора, которое нужно подсчитать

    // сохранить строки в объекты "инструкция"
    // сохранить объекты в map
    @Override
    protected void prepareCommonData(ArrayList<String> linesArrList) {

        Instraction currentInsractionObj;
        // индекс строки будет ключом в map для каждого объекта, его же id
        for (int indexLine = 0; indexLine < linesArrList.size(); indexLine++) {

            currentInsractionObj = new Instraction();
            currentInsractionObj.setId(indexLine);

            // разложение строки на составляющие
            String currentStr = linesArrList.get(indexLine);
            String currentOperation = currentStr.substring(0, 3); // взять 3 первые символа для сохранения операции "nop +0"
            currentInsractionObj.setOperation(currentOperation);
            currentStr = currentStr.substring(4); // взять строку от пробела, для сохранения аргумента "nop +0"
            int currentArgument = Integer.parseInt(currentStr); //  знак +/- будет учтен
            currentInsractionObj.setArgument(currentArgument);

            instractionMap.put(indexLine, currentInsractionObj);
        }
    }

    // часть 1: Движение по бесконечному циклу, остановится перед первым повтором операции в инструкции(объекте)
    @Override
    protected Integer calculatePart1_Solution(ArrayList<String> linesArrList) {
        accumulator = 0; // инициализация в методе т.к. используется в каждой части, но глобальная

        // step указатель на текущий объект (равен его id)
        int step = 0;
        do {
            step = getStep(step);
            //  продолжить до появления повтора
        } while (instractionMap.get(step).isNoSecondBool());
        return accumulator;
    }

    //  Определяет место, с которого начнется движение по объектам,
    private int getStep(int step) {

        Instraction currentObj = instractionMap.get(step);
        // количество объектов в map, -1 тк размер коллекции считается с 1
        final int COUNT_INSTRACTION = instractionMap.size() - 1;
        String currentOperationStr = currentObj.getOperation(); // для однократного обращения к этому полю
        int argumentObj = currentObj.getArgument();

        // отметка у объекта, что он будет использован
        currentObj.setNoSecondBool(false);

        // изменяется информация о повторе и currentId
        if (currentOperationStr.equals("nop")) {

            step++;
        } else if (currentOperationStr.equals("acc")) {

            accumulator += argumentObj;
            step++;
        } else {

            // если число не выходит за пределы списка(количества id) или он же - размер map
            if ( 0 <= step && COUNT_INSTRACTION >= step) {
                step += argumentObj;
            } else {
                step = abs((-currentObj.getId()) + (argumentObj % COUNT_INSTRACTION));
            }
        }
        return step;
    }


    //  часть 2 :
    @Override
    protected Integer calculatePart2_Solution(ArrayList<String> linesArrList) {
        accumulator = 0; // инициализация в методе т.к. используется в каждой части, но глобальная


        return 0;
    }


}






















