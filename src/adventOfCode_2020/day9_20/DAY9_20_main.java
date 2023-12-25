package adventOfCode_2020.day9_20;


import adventOfCode_2020.day9_20.DAY9_20_implement;

import java.io.IOException;


public class DAY9_20_main {
    public static void main(String[] args) throws IOException {


        //Получить данные с файла
        String fileName = "DAY9_20"; // "test"   "DAY9_20"  PREAMBLE для теста 5, для входных данных 25
        // Пример, на основе 3 дня
        DAY9_20_implement day9Solver = new DAY9_20_implement();
        day9Solver.showResult(fileName);
    }
}
