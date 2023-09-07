package adventOfCode_2020.DAY3_20;
// --- День 3: Санная траектория ---

import java.io.IOException;

public class DAY3_20_main {
    public static void main(String[] args) throws IOException {
        //Получить данные с файла
        String fileName = "TEST"; // "TEST"   "DAY3_20"
        // Пример, на основе 3 дня
        DAY3_20_implement day1Solver = new  DAY3_20_implement();
        day1Solver.showResult(fileName);
    }

}
