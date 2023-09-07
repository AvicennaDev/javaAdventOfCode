package adventOfCode_2020.DAY1_20;
// --- День 1: Отчет о ремонте ---


import java.io.IOException;

public class DAY1_20_main {
    public static void main(String[] args) throws IOException {
        //Получить данные с файла
        String fileName = "DAY1_20"; // "TEST"   "DAY1_20"
        // Пример, на основе 3 дня
   DAY1_20_implement day1Solver = new DAY1_20_implement();
    day1Solver.showResult(fileName);
    }
}
