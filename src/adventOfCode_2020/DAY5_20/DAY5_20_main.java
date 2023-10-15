package adventOfCode_2020.DAY5_20;

import java.io.IOException;


public class DAY5_20_main {
    public static void main(String[] args) throws IOException {
        //Получить данные с файла
        String fileName = "test"; // "test"   "DAY5_20"
        // Пример, на основе 3 дня
        DAY5_20_implement day1Solver = new DAY5_20_implement();
        day1Solver.showResult(fileName);
    }
}
