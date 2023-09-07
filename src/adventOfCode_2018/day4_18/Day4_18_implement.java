package adventOfCode_2018.day4_18;

import common.AocSolverAbstract;

import java.util.*;


public class Day4_18_implement extends AocSolverAbstract<Integer, Integer> {


    private List<Record> allRecordArrLis = new ArrayList<Record>(); // все учетные записи в виде объектов record
    Map<Integer, Guard> allGuardMap = new HashMap<Integer, Guard>(); // для хранения всех охранников
    Map<Integer, Integer> sleepTimeMap = new HashMap<Integer, Integer>(); // ассоциат. массив хранит id охранника и сколько мин он спал

    // конструктор обеспечит заполнение allRecordArrLis вызовом prepareCommonData
    // заполнить коллекцию объектов
    @Override
    protected void prepareCommonData(ArrayList<String> recordsArrL) {

        for (int i = 0; i < recordsArrL.size(); i++) {
            Record currentRecordOb = new Record(recordsArrL.get(i));
            allRecordArrLis.add(currentRecordOb);
        }

    }

    @Override
    protected Integer calculatePart1_Solution(ArrayList<String> recordsArrL) {
        // сохранённую коллекцию объектов необходимо отсортировать
        // Сортировка списка по полю timestamp
        Collections.sort(allRecordArrLis,
                new Comparator<Record>() {
                    @Override
                    public int compare(Record record1, Record record2) { // пользовательская реализация метода
                        return record1.getTimestamps().compareTo(record2.getTimestamps()); // сортировка по полю объекта
                    }
                });
        // краткая форма
        //  sleepRecords.sort(Comparator.comparing(record -> record.date + " " + record.minute));

        // посчитать количество минут в течении которых спал охранник
        int currentGuardId = 0;
        int sleepStartMinute = 0;
        // ассоциат. массив хранит id охранника и сколько мин он спал
        int idMaxSleep = 0; // id охрнаника который спал дольше всех ( ключ асс.масс.)
        for (Record currentRecord : allRecordArrLis) {

            // сохранить id текущего охранника, используется как ключ
            if (currentRecord.getId() >= 0) {
                currentGuardId = currentRecord.getId();
                // проверить был ли охранник

            } else if (currentRecord.isAsleepBool()) {
                // sleepStartMinute = currentRecord.getTimestamps().getTime();
                // для извлечения минут необходимо использовать Calendar
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(currentRecord.getTimestamps());
                sleepStartMinute = calendar.get(Calendar.MINUTE);

            } else {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(currentRecord.getTimestamps());
                int sleepEndMinute = calendar.get(Calendar.MINUTE);
                int sleepTime = sleepEndMinute - sleepStartMinute;
//sleepTimeMap.getOrDefault(currentGuardId, 0)извлекает значение, связанное с currentGuardId ключом,
// из файла sleepTimeMap. Если ключ присутствует в карте, он возвращает соответствующее значение.
// В противном случае он возвращает
// значение по умолчанию, указанное в качестве второго аргумента, как 0в данном случае.

                sleepTimeMap.put(currentGuardId, sleepTimeMap.getOrDefault(currentGuardId, 0) + sleepTime);
                //+ sleepTime добавляет sleepTime значение к полученному времени ожидания (либо существующее значение из карты,
                // либо 0если ключ отсутствует). На этом шаге накапливается время сна охранника.
                //     allGuardMap.put(currentGuardId,new Guard(currentGuardId));
                sleepStartMinute = 0;
            }
        }

        // найти максимальное значение в sleepTimeMap
        // инициализируем `maxSleepTimeс наименьшим возможным целочисленным значением (`IntegerInteger.MIN_VALUE)
        // чтобы гарантировать,
        // что любое встречающееся время ожидания будет больше начального значения.

        int maxSleepTime = Integer.MIN_VALUE;
        for (Map.Entry<Integer, Integer> entry : sleepTimeMap.entrySet()) { // получает node, хранящие ключ-значение
            if (maxSleepTime < entry.getValue()) {
                maxSleepTime = entry.getValue();
                idMaxSleep = entry.getKey(); //храним id охранника который спал дольше всех на данный момент
            }
        }


        // основное решение
        // пройти по отсортированным учеткам
        Guard guard = new Guard(idMaxSleep); // создали объект охранник с id который спал дольше всех


        // пройти по массиву сна, найти минуту(индекс) под которым значение самое большое, те спал чаще всего на этой минуте
        setArrSleep(idMaxSleep, guard);
        // заполнить поля объекта, максимум значение и индекс максимума(минуту)
        fillMaxValue(guard);
        int maxSleepMin = guard.getMaxMinSleep();
        int indexMaxSleepMin = guard.getIndexMaxSleepMin();

        // Каков ID выбранного вами охранника, умноженный на выбранную вами минуту?
        int inputAnswer = idMaxSleep * indexMaxSleepMin;
        return inputAnswer;
    }

    //Кто из всех охранников чаще всего спит в одну и ту же минуту?
    @Override
    protected Integer calculatePart2_Solution(ArrayList<String> linesArrList) {
        for (int indRecord = 0; indRecord < allRecordArrLis.size(); indRecord++) {

            int id;
            // получить id из строк
            Guard currentGuard = new Guard();
            if (allRecordArrLis.get(indRecord).getId() > 0) {
                id = allRecordArrLis.get(indRecord).getId();
                currentGuard.setId(id);
            } else {
                // пропустить строку без id
                continue;
            }


            // пройти по массиву сна, найти минуту(индекс) под которым значение самое большое, те спал чаще всего на этой минуте
            setArrSleep(id, currentGuard);
            // заполнить поля объекта, максимум значение и индекс максимума(минуту)
            fillMaxValue(currentGuard);
            // положить в асс. масс. текущий объект с id и массивом сна

            int maxSleepMin = currentGuard.getMaxMinSleep();
            int indexMaxSleepMin = currentGuard.getIndexMaxSleepMin();
            allGuardMap.put(currentGuard.getId(), new Guard(maxSleepMin, indexMaxSleepMin));
        }


        // пройти по асс масс, получить id c набольшей частотой засыпаний в конкретную мин
        int idMaxSleep = Integer.MIN_VALUE;
        int maxSleepMin = Integer.MIN_VALUE;
        int indexMaxSleepMin = Integer.MIN_VALUE;
        for (Map.Entry<Integer, Guard> entry : allGuardMap.entrySet()) { // получает node, хранящие ключ-значение
            if (maxSleepMin < entry.getValue().getMaxMinSleep()) {
                maxSleepMin = entry.getValue().getMaxMinSleep();
                idMaxSleep = entry.getKey(); //храним id охранника который спал дольше всех на данный момент
                indexMaxSleepMin = entry.getValue().getIndexMaxSleepMin();
            }
        }

        int inputAnswer = idMaxSleep * indexMaxSleepMin;
        return inputAnswer;
    }

    // учесть, что один охранник пришел от 0, и ушел в этот день, другой от 23 этого дня(перейдет на след день отсчет)


    // метод выполняющий заполнение массива минут у объекта охранник
    private void setArrSleep(int id, Guard currentGuard) {
        // заполнить массив объекта охранник
        // проходится по коллекции много раз, в поисках данных на каждого охранника при запуске 2 части
        boolean currentGuardBool = false;        // обозначение, что это текущий охранник, а не новый
        boolean otherGuardBool = false; //  для исключения строк идущих после ненужного id
        for (int indexOb = 0; indexOb < allRecordArrLis.size(); indexOb++) {

            // если текущая строчка содержит не id, а -1, то поставить currentGuardBool = true;
            // и если это не другой охранник
            if ((allRecordArrLis.get(indexOb).getId() == -1) && (otherGuardBool == false)) {
                currentGuardBool = true;

            }
            // отметка, что сейчас нужный нам id
            if (allRecordArrLis.get(indexOb).getId() == id) {
                otherGuardBool = false;
            }
            // если текущий объект уч записей содержит id не то, что нужно то перейти на след итерацию,
            // или если был id но не верный
            if ((allRecordArrLis.get(indexOb).getId() != id) && (currentGuardBool == false) || (otherGuardBool == true)) {
                otherGuardBool = true;
                continue;
            } else { // если текущий объект коллекции содержит нужный id охранника
                // заполнить массив объекта Guard
                // если currentGuardBool = false то новая строчка с id, то брать след стрроку коллекции объектов, иначе текущую
                if (currentGuardBool == false) {
                    indexOb++;
                }
                int startSleep = 0;
                int finishSleep = 0;

                Calendar calendarStart = Calendar.getInstance();
                calendarStart.setTime(allRecordArrLis.get(indexOb).getTimestamps()); // дублирование кода. получить минуты начала
                startSleep = calendarStart.get(Calendar.MINUTE);


                indexOb++;
                Calendar calendarFinis = Calendar.getInstance();
                calendarFinis.setTime(allRecordArrLis.get(indexOb).getTimestamps()); // дублирование кода. получить минуты начала
                finishSleep = calendarFinis.get(Calendar.MINUTE);


                // количество минут которые спит
                int sleepTime = finishSleep - startSleep;
                //       System.out.println("finish " + finishSleep + "; start " + startSleep + "; sleepTime " + sleepTime);
                // заполнить массив сна
                for (int indexSleep = startSleep; indexSleep < finishSleep; indexSleep++) {
                    currentGuard.sleepMinutsArr[indexSleep]++;
                }
                currentGuardBool = false;
            }
        }
    }

    // метод проводящий поиск максимума в массиве, который подсчитывал частоту сна объекта охранник в конкретную минуту
    private void fillMaxValue(Guard guard) {
        int maxSleepMin = 0;
        int indexMaxSleepMin = 0;
        for (int indexMin = 0; indexMin < guard.sleepMinutsArr.length; indexMin++) {
            if (maxSleepMin < guard.sleepMinutsArr[indexMin]) {
                maxSleepMin = guard.sleepMinutsArr[indexMin];
                indexMaxSleepMin = indexMin;
                /** ? почему не нужно было делать +1
                 *
                 */
            }
        }
        guard.setIndexMaxSleepMin(indexMaxSleepMin);
        guard.setMaxTeemSleep(maxSleepMin);
    }

}

