package adventOfCode_2020.DAY6_20;

// класс для хранения информации о каждой группе в самолете,
// подсчитывает сколько ответов было одиновых для всей группы, в случаее если ответ есть у всех участников,
// подсчитывает количество всех уникальных ответов на группу участников

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;

public class GroupParticipants {

    ArrayList<String> allPerson = new ArrayList<String>();

    private int allAnyYesAnswers = 0; // хранит количество уникальных символов   для каждого объекта
    private String allAnswers;
    private HashSet<Character> completedQuestions = new HashSet<Character>(); // хранит уникальные знаки от всей группы

    private int sumIdenticalAnswers = 0; // хранить количество оданаковых ответов если они были у всех участников


    // для части 1: посчитать все уникальные символы в коллекции
    void setAllAnyYesAnswers() {
        allAnswers = allPerson.toString().replaceAll("[^\\p{L}]", ""); // превратить все ответы в строку

        // сохранить в hashset для исключения копий
        for (int indexChar = 0; indexChar < allAnswers.length(); indexChar++) {
            completedQuestions.add(allAnswers.charAt(indexChar));
        }
    }

    // вернет количество уникальных знаков, они же количество не повторяющихся ответов
    public int getAllAnyYesAnswers() {
        setAllAnyYesAnswers();
        allAnyYesAnswers = completedQuestions.size();
        return allAnyYesAnswers;
    }


    // для части 2 : посчитать только те символы, что были у каждого участника
    public int getSumIdenticalAnswers() {
        setSumIdenticalAnswers();
        return sumIdenticalAnswers;
    }

    public void setSumIdenticalAnswers() {

        // для наибольшей и полной выборки взять самую длинную строку - того, у кого больше всего символов
        String maxSrtAnswers = getStrMaxCountAnswers();

        // посчитать количество появлений текущего символа из большей строки в других строках(ответ другого участника)
        for (int indexCurrentChar = 0; indexCurrentChar < maxSrtAnswers.length(); indexCurrentChar++) {

            char currentChar = maxSrtAnswers.charAt(indexCurrentChar);
            int countCurrentAnswer = 0;

            //посчитать каждый символ самой длинной строки в каждой другой строке
            for (int indexPerson = 0; indexPerson < allPerson.size(); indexPerson++) {

                String currentLineAnswers = allPerson.get(indexPerson);

                // содержит ли строка подстроку, но ввиде символа
                if(currentLineAnswers.contains(String.valueOf(currentChar))){
                    countCurrentAnswer++;
                }
            }

            // у каждого ли участника был этот ответ
            // countCurrentAnswer должен соответствовать allPerson.size() количеству участниковпеш
            // сохранить значение в переменную sumIdenticalAnswers,
            // те подсчитать количество вопросов на которые ответили все
            if(countCurrentAnswer == allPerson.size()){
                sumIdenticalAnswers++;
            }
            // в любом случае по окончанию countCurrentAnswer обнулить

        }
    }

    // получить самую длинную строку - у кого больше всего ответов
    private String getStrMaxCountAnswers() {
        String max = Collections.max(allPerson, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        });
        return max;
    }
}
