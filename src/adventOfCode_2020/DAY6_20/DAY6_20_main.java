package adventOfCode_2020.DAY6_20;

import adventOfCode_2020.DAY5_20.DAY5_20_implement;

import java.io.IOException;


public class DAY6_20_main {
    public static void main(String[] args) throws IOException {
        //Получить данные с файла
        String fileName = "DAY6_20"; // "test"   "DAY6_20"
        // Пример, на основе 3 дня
        DAY6_20_implement day1Solver = new DAY6_20_implement();
        day1Solver.showResult(fileName);
    }
}
