package adventOfCode_2020.DAY8_20;

public class Instraction {

    private int id = 0; // идентификатор для map

    // nop +0 первое операция, второе аргумент
    private String operation;
    private int argument;

    private  boolean noSecondBool = true; // для обозначения повтора использования

    public boolean isNoSecondBool() {
        return noSecondBool;
    }

    public void setNoSecondBool(boolean noSecondBool) {
        this.noSecondBool = noSecondBool;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public int getArgument() {
        return argument;
    }

    public void setArgument(int argument) {
        this.argument = argument;
    }
}
