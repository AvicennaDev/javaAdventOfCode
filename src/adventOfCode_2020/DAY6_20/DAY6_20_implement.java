package adventOfCode_2020.DAY6_20;

import common.AocSolverAbstract;

import java.util.ArrayList;

public class DAY6_20_implement extends AocSolverAbstract<Integer, Integer> {

    ArrayList<GroupParticipants> allGroup = new ArrayList<GroupParticipants>();

    // сохранить ответы по группам
    @Override
    protected void prepareCommonData(ArrayList<String> linesArrList) {

        // первая группа создается вне цикла, остальные внутри
        GroupParticipants currenGroup = new GroupParticipants();
        for (int indexLine = 0; indexLine < linesArrList.size(); indexLine++) {

            // если встретилась пустая строка, то сохранить текущий объект в коллекцию и создать новый
            if (linesArrList.get(indexLine).isEmpty()) {
                allGroup.add(currenGroup);
                currenGroup = new GroupParticipants();
                continue;
            }
            currenGroup.allPerson.add(linesArrList.get(indexLine));

        }

        // проверить наличие не сохраненной группы
        if (!currenGroup.allPerson.isEmpty()) {
            allGroup.add(currenGroup);
        }
    }

    // часть 1: подсчитать уникальные ответы во всей группе
    @Override
    protected Integer calculatePart1_Solution(ArrayList<String> linesArrList) { // убрать параметры

        int countYesAnswers = 0;

        // подсчитать ответы "да" для всех объектов сразу, часть 1
        countYesAnswers = getAllYesAnswers();

        return countYesAnswers;
    }

    // подсчитать уникальные символы во всей группе для всех объектов сразу
    private int getAllYesAnswers() {
        int countYesAnswers = 0;

        for (int currentObject = 0; currentObject < allGroup.size(); currentObject++) {
            countYesAnswers += allGroup.get(currentObject).getAllAnyYesAnswers();
        }
        return countYesAnswers;
    }


    //  часть 2 : посчитать только те символы, что были у каждого участника
    @Override
    protected Integer calculatePart2_Solution(ArrayList<String> linesArrList) {

        int sumIdenticalAnswers = 0;

        // подсчитать сумму полей "идентичные ответы" всех объектов, часть 2
        sumIdenticalAnswers = getSumIdenticalAnswers();
        return sumIdenticalAnswers;
    }

    // подсчитать сумму полей "идентичные ответы" всех объектов, часть 2
    private int getSumIdenticalAnswers() {

        int sumIdenticalAnswers = 0;

        for (int currentObject = 0; currentObject < allGroup.size(); currentObject++) {
            sumIdenticalAnswers += allGroup.get(currentObject).getSumIdenticalAnswers();
        }
        return sumIdenticalAnswers;
    }
}






















