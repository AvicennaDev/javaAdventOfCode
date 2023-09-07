package studyProject;

import sun.security.krb5.internal.PAEncTSEnc;

import java.util.*;

public class MainMap {
    public static void main(String[] args) {

        Person ivan = new Person(1, "Ivan", "Ivanov");
        Person petr = new Person(2, "Petr", "Petrov");
        Map<Integer, Person> personMap = new HashMap<Integer, Person>();

        personMap.put(ivan.id, ivan);
        personMap.put(petr.id, petr);

//
//        System.out.println( personMap.keySet());
//        System.out.println(personMap.entrySet()); // вернут node

//        for (Map.Entry<Integer, Person> entry: personMap.entrySet()){
//            System.out.println(entry.getKey() + " " + entry.getValue().lastName);
//        }

//
//        personMap.containsKey(4);
//        personMap.containsValue(petr);

        System.out.println(personMap.size());
      //  System.out.println(personMap.getOrDefault(5,ivan));

        List<Person> personList = Arrays.asList(
                new Person(9, "Nina", "Holtsman"),
                new Person(5, "Den", "Polo"),
                new Person(7, "Vika", "Vasnechova")
        );
//
//Collections.sort(personList);
//        System.out.println(personList);


// comparable позволяет сортировать только по одному полю
        // comparator позволяет сортировать по нескольким полям

        Collections.sort(personList, new FirstNameComparator());
        // аналог
      //  personList.sort(new FirstNameComparator());


        //  с  java8  появилась возможность не создавать класс реализующий компаратор
    //    personList.sort(Comparator);
    }


    public static class FirstNameComparator implements Comparator<Person>{

        @Override
        public int compare(Person o1, Person o2) {
            return o1.lastName.compareTo(o2.lastName);
        }
    }
}