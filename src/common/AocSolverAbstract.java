package common;


// использован только для 2018 года
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class AocSolverAbstract<T, V> {

     //Универсальная часть решения задач, получить данные, передать в другие функции, вывести результат

    public void showResult(String filePath) throws IOException {
        ArrayList<String> linesArrLis = readLines(filePath);


        prepareCommonData(linesArrLis);

        T solutionPart1 = calculatePart1_Solution(linesArrLis);
        V solutionPart2 = calculatePart2_Solution(linesArrLis);

        System.out.println("Part 1 = " + solutionPart1 + "\n"
                + "Part 2 = " + solutionPart2);
    }

    // если в задаче необходимо приготовить какие-то общеие данные (классы, массивы и т.д.),
    // то они происходят в этом методе.
    protected void prepareCommonData(ArrayList<String> linesArrList){

    }

    // для решения в наследуемом классе, необходимо переопределить
    //часть 1
    protected abstract T calculatePart1_Solution(ArrayList<String> linesArrList);

    // для решения в наследуемом классе, необходимо переопределить
    //часть 2
    protected abstract V calculatePart2_Solution(ArrayList<String> linesArrList);

    // чтение файла, внутренняя функция класса
    private ArrayList<String> readLines(String filePath) throws IOException {
        ArrayList<String> linesArrList = new ArrayList<String>();
        FileReader fileReader = new FileReader(filePath);
        Scanner scanner = new Scanner(fileReader);

        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            linesArrList.add(line);
        }
        fileReader.close();
        scanner.close();
        return linesArrList;
    }
}
