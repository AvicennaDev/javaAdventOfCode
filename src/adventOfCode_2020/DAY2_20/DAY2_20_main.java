package adventOfCode_2020.DAY2_20;
// --- День 2: Философия паролей ---

import java.io.IOException;

// общий для копирования в каждый каталог
public class DAY2_20_main {

   public static void main(String[] args) throws IOException {
       //Получить данные с файла
       String fileName = "DAY2_20"; // "TEST"   "DAY2_20"
       // Пример, на основе 3 дня
//    Day_18_Implement day3Solver = new Day3_18_Implement(1000);
//    //day3Solver.showResult(fileName);

       DAY2_20_implement day2Solver = new DAY2_20_implement();
       day2Solver.showResult(fileName);


   }

}
