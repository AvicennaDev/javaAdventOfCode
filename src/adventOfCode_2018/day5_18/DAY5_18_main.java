package adventOfCode_2018.day5_18;

import java.io.IOException;

// общий для копирования в каждый каталог
public class DAY5_18_main {
    public static void main(String[] args) throws IOException {
        //Получить данные с файла
        String fileName = "DAY5_18"; // "test"   "DAY5_18"
        // Пример, на основе 3 дня
//    Day3_18_Implement day3Solver = new Day3_18_Implement(1000);
//    //day3Solver.showResult(fileName);

        DAY5_18_implement day5 = new DAY5_18_implement();
        day5.showResult(fileName);
    }
}