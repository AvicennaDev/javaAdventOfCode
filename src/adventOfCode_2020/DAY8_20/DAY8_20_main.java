package adventOfCode_2020.DAY8_20;

import adventOfCode_2020.DAY8_20.DAY8_20_implement;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class DAY8_20_main {
    public static void main(String[] args) throws IOException {


        //Получить данные с файла
        String fileName = "DAY8_20"; // "test"   "DAY8_20"
        // Пример, на основе 3 дня
        DAY8_20_implement day8Solver = new DAY8_20_implement();
        day8Solver.showResult(fileName);
    }
}
