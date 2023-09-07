package studyProject;

public class Person implements Comparable<Person> {

    int id = 0;
    String firstName;
    String lastName;

    public Person(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }


    @Override
    public int compareTo(Person o) {
//        if(id == o.id){
//            return 0;
//        } else  if (id > o.id){
//            return 1;
//        } else{
//            return 0;
//        }
return  Integer.compare(id, o.id); // может дать переполнение  вычитание, но интежер реализует свой compere, сравнивает на отрицательность
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }



}
