package adventOfCode_2020.DAY8_20;

import common.AocSolverAbstract;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.*;

public class DAY8_20_implement extends AocSolverAbstract<Integer, Integer> {

    // map  для хранения объектов "инструкции"
    private Map<Integer, Instruction> instractionMap = new HashMap<Integer, Instruction>();

    // количество объектов в map, -1 тк размер коллекции считается с 1
    private int accumulator; // хранит значение аккомулятора, которое нужно подсчитать
    final String JMP = "jmp";
    final String NOP = "nop";
    private int countInstruction; //  инициализация происходит позже
    private int step = 0;         // step указатель на текущий объект (равен его id)
    private int sumAccumulatorInLastInstruction = 0; // хранит значение на момент прохода через последний оператор в списке
    boolean isPart2 = false; // для запуска внутри кода для 1 части кода для 2 части


    // сохранить строки в объекты "инструкция"
    // сохранить объекты в map
    @Override
    protected void prepareCommonData(ArrayList<String> linesArrList) {

        Instruction currentInsractionObj;
        // индекс строки будет ключом в map для каждого объекта, его же id
        for (int indexLine = 0; indexLine < linesArrList.size(); indexLine++) {

            currentInsractionObj = new Instruction();
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
        countInstruction = instractionMap.size() - 1;
    }

    // часть 1: Движение по бесконечному циклу, остановится перед первым повтором операции в инструкции(объекте)
    @Override
    protected Integer calculatePart1_Solution(ArrayList<String> linesArrList) {
        accumulator = 0; // инициализация в методе т.к. используется в каждой части, но глобальная

        // дойти до первого повтора, заполняя accumulator
        findRepetition();
        return accumulator;
    }

    // выйти при первом повторе или после применения последнего оператора
    private void findRepetition() {
        do {
            // для второй части
            if ((step == countInstruction) && isPart2) {

                // если текущая инструкция содержит "acc" то добавить ее содержимое
                if(instractionMap.get(step).getOperation().equals("acc")){
                    accumulator += instractionMap.get(step).getArgument();
                }
                sumAccumulatorInLastInstruction = accumulator ;
                break;
            }
            step = getStep(step);
            //  продолжить до появления повтора

        } while (instractionMap.get(step).getIsSecondBool());
        step = 0;         // step указатель на текущий объект (равен его id)
    }

    //  Определяет место, с которого начнется движение по объектам, id следующего объекта-инструкции
    private int getStep(int step) {

        Instruction currentObj = instractionMap.get(step);

        String currentOperationStr = currentObj.getOperation(); // для однократного обращения к этому полю
        int argumentObj = currentObj.getArgument();

        // отметка у объекта, что он будет использован
        currentObj.setIsSecondBool(false);

        // изменяется информация о повторе и currentId
        if (currentOperationStr.equals(NOP)) {

            step++;
        } else if (currentOperationStr.equals(JMP)) {

            // если число не выходит за пределы списка(количества id) или он же - размер map
            if (0 <= step && countInstruction >= step) {
                step += argumentObj;
            } else {
                step = abs((-currentObj.getId()) + (argumentObj % countInstruction));
            }
        } else {
            accumulator += argumentObj;
            step++;
        }
        return step;
    }


    //  часть 2 : движение до первого значения больше количества инструкций (объектов)
    @Override
    protected Integer calculatePart2_Solution(ArrayList<String> linesArrList) {

        Instruction currentObj;

        //accumulator = 0; // инициализация в методе т.к. используется в каждой части, но глобальная
        isPart2 = true; // для запуска второй части в фукции для первой

        for (int indexObj = 0; indexObj < countInstruction; indexObj++) {
            accumulator = 0; // инициализация в методе т.к. используется в каждой части, но глобальная

            // итерация по коллекции map, вернуть исходное значение в поле IsSecondBool
            fastBackIsRepetition();
             currentObj = instractionMap.get(indexObj);

            // выполнить замену поля "операция" в объекте
            changeOperatorInInstuction(currentObj);

            // определить на какой позиции
            findRepetition();
            if(sumAccumulatorInLastInstruction != 0){
                break;
            }

            // восстановить значение поля "операция" в объекте
            changeOperatorInInstuction(currentObj);

        }

        return sumAccumulatorInLastInstruction;
    }

    // итерация по коллекции map, вернуть исходное значение в поле IsSecondBool
    private void fastBackIsRepetition() {
        Instruction currentObj;
        for(int indexInstruction = 0; indexInstruction < instractionMap.size(); indexInstruction++){
            currentObj = instractionMap.get(indexInstruction);
            currentObj.setIsSecondBool(true);
        }
    }

    // выполнить замену поля "операция" в объекте
    private void changeOperatorInInstuction(Instruction currentObj) {
          if (currentObj.getOperation().equals(NOP)) {
            currentObj.setOperation(JMP);
        } else if (currentObj.getOperation().equals(JMP)) {
            currentObj.setOperation(NOP);
        }
    }
}
