package adventOfCode_2020.DAY4_20;

import java.io.IOException;


public class DAY4_20_main{
    public static void main(String[] args) throws IOException {
        //Получить данные с файла
        String fileName = "DAY4_20"; // "test"   "DAY4_20"
        // Пример, на основе 3 дня
        DAY4_20_implement day1Solver = new  DAY4_20_implement();
        day1Solver.showResult(fileName);
    }
}
