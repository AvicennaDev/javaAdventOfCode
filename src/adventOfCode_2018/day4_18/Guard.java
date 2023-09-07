package adventOfCode_2018.day4_18;


public class Guard {
    private int id = 0;
    int [] sleepMinutsArr = new int[60];
    private   int maxMinSleep = 0;
    private int indexMaxSleepMin = -1;

    // для обхода коллекции уч записей
    public Guard(int id) {
        this.id = id;
    }

    public Guard(){} // для инициализции во второй части

    // для получения объекта с нужны данными в конечом решении
    public Guard(int maxMinSleep, int indexMaxSleepMin) {
        this.maxMinSleep = maxMinSleep;
        this.indexMaxSleepMin = indexMaxSleepMin;
    }

    public void setIndexMaxSleepMin(int indexMaxSleepMin) {
        this.indexMaxSleepMin = indexMaxSleepMin;
    }

    public int getMaxMinSleep() {
        return maxMinSleep;
    }

    public int getIndexMaxSleepMin() {
        return indexMaxSleepMin;
    }

    public void setMaxTeemSleep(int maxMinSleep) {
        this.maxMinSleep = maxMinSleep;
    }

    public int getId() {
        return id;
    }

    public int[] getSleepMinutsArr() {
        return sleepMinutsArr;
    }

    public void setId(int id) {
        this.id = id;
    }


}
