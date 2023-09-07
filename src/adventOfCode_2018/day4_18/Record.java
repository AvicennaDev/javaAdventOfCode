package adventOfCode_2018.day4_18;

 import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Record {
    // метод для теста
//    public static void main(String[] args) {
//        String testStr = "[1518-11-05 00:45] falls asleep";
//        // [1518-11-05 00:55] wakes up
//        // [1518-11-05 00:45] falls asleep
//        // [1518-11-01 00:00] Guard #10 begins shift
//
//        Record record = new Record(testStr);
//
//        int r = 4;
//    }

    private Date timestamps; // временная метка объекта
    private int id = -1; // id охранника
    // минута пробуждения считается бодорствованием
    private boolean asleepBool = false; // отметка, что охранник спит
    private boolean overseeBool = false; // отметка, что охранник наблюдает, возможно не нужна

    public Record(String record){

        createDateTime(record);  // заполнить поле временной метки
        if(record.contains("#")){ // если строка содержит символ номер то заполнить поле id объекта
            createId(record);
        }
        else if (record.contains("asleep")){
            this.asleepBool = true;
        } else if ( record.contains("wakes up")){
            this.overseeBool = true;
        }
    }

    // внести дату, запускается в конструкторе
    public void createDateTime(String record) {
        String currentDateStr = record.substring(1, 17);
        //  Формат даты для разбора строк
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        // Анализ строки и добавить в поле объекта временную заметку
        try {
            this.timestamps = dateFormat.parse(currentDateStr); // почему не просит new
        } catch (ParseException e) {
            System.out.println("Failed to parse date: " + currentDateStr);
        }
    }

    // Заполнить id охранника
    public void createId(String record){
        String[] recordArrStr = record.split(" ");
        String idStr = recordArrStr[3].substring(1);
        this.id = Integer.parseInt(idStr);
    }

    public Date getTimestamps() {
        return timestamps;
    }

    public int getId() {
        return id;
    }

    public boolean isAsleepBool() {
        return asleepBool;
    }

    public boolean isOverseeBool() {
        return overseeBool;
    }
}
