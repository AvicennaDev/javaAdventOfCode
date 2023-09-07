package adventOfCode_2020.DAY2_20;

// содержит информация о считанной строке
public class Line {

   private int start = 0;
   private int finish = 0;

   private char currentChar = 0;
   char[] arrChar;
   private int countChar = 0;
  private boolean validPassword = false;


    public Line(int start, int finish, char currentChar, char[] arrChar) {
        this.start = start;
        this.finish = finish;
        this.currentChar = currentChar;
        this.arrChar = arrChar;



    }

// подсчитать количетсво важных символов, важно для части 1
     void getCountChar(Line currentLineObj) {
        int countChar = 0;
        int lengthArrObj = currentLineObj.getArrChar().length;
        char[] currentCharArrObj = currentLineObj.getArrChar();
        for (int indexChar = 0; indexChar < lengthArrObj; indexChar++) {

            if (currentCharArrObj[indexChar] == currentLineObj.getCurrentChar()) {
                countChar++;
            }
        }
         this.countChar = countChar;
    }

    // указать действительность пароля для части 2

    void getBoolenValidPassword (Line currentLineObj){
        boolean validPassword = false;
        boolean orOne = false;
        boolean orTwo = false;

        int index1 = currentLineObj.getStart() - 1;
        int index2 = currentLineObj.getFinish() - 1;

        char[] currentArrChar = currentLineObj.getArrChar();

        // логика на вынос
        // если  на текущем месте есть важный знак для текущего пароля, то отметить что есть этот знак
        if (currentArrChar[index1] == currentLineObj.currentChar ){
            orOne = true;
        }
        if(currentArrChar[index2] == currentLineObj.currentChar){
            orTwo = true;
        }

        // логика функции для решения части 2
        if((orOne != orTwo)){
            validPassword = true;
        }
        this.validPassword = validPassword;

    }

    public boolean isValidPassword() {
        return validPassword;
    }

    public void setValidPassword(boolean validPassword) {
        this.validPassword = validPassword;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getFinish() {
        return finish;
    }

    public void setFinish(int finish) {
        this.finish = finish;
    }

    public char getCurrentChar() {
        return currentChar;
    }

    public void setCurrentChar(char currentChar) {
        this.currentChar = currentChar;
    }

    public char[] getArrChar() {
        return arrChar;
    }

    public void setArrChar(char[] arrChar) {
        this.arrChar = arrChar;
    }

    public int getCountChar() {
        return countChar;
    }

    public void setCountChar(int countChar) {
        this.countChar = countChar;
    }
}
