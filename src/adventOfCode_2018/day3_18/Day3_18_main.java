package adventOfCode_2018.day3_18;

import java.io.IOException;


public class Day3_18_main {
    public static void main(String[] args) throws IOException {
        String fileName = "DAY3_18"; // "TEST"   "DAY3_18"
        //  установить rowColumn =  длины стороны полотна, для теста 8, для задачи 1000
        Day3_18_Implement day3Solver = new Day3_18_Implement(1000);
        day3Solver.showResult(fileName);


    }

}