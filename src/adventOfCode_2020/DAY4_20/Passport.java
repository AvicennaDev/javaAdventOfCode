package adventOfCode_2020.DAY4_20;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class Passport {

    private int birthYear; // (Год рождения) – четыре цифры; минимум 1920 и максимум 2002
    private int issueYear; // (Год выпуска) – четыре цифры; минимум 2010 и максимум 2020.
    private int expirationYear; // (Год окончания срока действия) – четыре цифры; минимум 2020 и максимум 2030
    private int height; // (Высота)  если cm, то не менее 150и не более 193; in, то не менее 59 и не более 76
    private String hairColor; // (Цвет волос) – a, #за которым следуют ровно шесть символов 0– 9или a– f.
    private String eyeColor; //(Цвет глаз) – ровно один из: amb blu brn gry grn hzl oth
    private int passportID; // (ID паспорта) – девятизначное число, включая лидирующие нули.

    private boolean isValid_part_2 = false; //флаг для отметки допустимости паспорта, изменяется при проверке значений
    // содержит все заключения по полям
    /**
     * set просит переопределить ненужные на данный момент методы
     */
    private ArrayList<Boolean> isValidOrInvalidField = new ArrayList<Boolean>();
    ArrayList<String> allField = new ArrayList<String>(); // содержит все считанные поля

    // проверить данные с коллекции
    //получает массив полей которые необходимо проанализировать
    public void fillFilder() {

        int sizeAllFieldArrList = allField.size();

        //пройти по каждому элементу коллекции
        for (int indexAllFieldArrLis = 0; indexAllFieldArrLis < sizeAllFieldArrList; indexAllFieldArrLis++) {
            String curentField = allField.get(indexAllFieldArrLis);

            // выбрать поле и заполнить поле
            selectField(curentField);
        }

        // повлиять на поле isValid_part_2
        checkIfPassportIsValid();
    }

    // проверить есть в коллекции по информации полей значение false
    private void checkIfPassportIsValid(){
        // проверить имеется ли в наличии значение false  в коллекции isValidOrInvalidField
        if(isValidOrInvalidField.contains(false)){
            this.isValid_part_2 = false;
        }else {
            this.isValid_part_2 = true;
        }
    }

    // выбрать и заполнить поле
    private void selectField(String curentField) {
        //  String subStrField = curentField.substring(0,3);

        /**
         * в switch для java от 7 допустим аргумент String
         */

        //String[] fieldsArr = {"byr", "iyr", "eyr", "hgt", "ecl", "pid", "hcl"};

        // границы диапазонов
        int minLimit = 0;
        int maxLimit = 0;
        if (curentField.contains("byr")) {

            //(Год рождения) – четыре цифры; минимум 1920 и максимум 2002
            // пример строки: "byr:1980"
            // допустимые диапазоны
            minLimit = 1920;
            maxLimit = 2002;
            this.birthYear = fillYearAndHeight(curentField, minLimit, maxLimit);
        } else if (curentField.contains("iyr")) {

            //(Год выпуска) – четыре цифры; минимум 2010 и максимум 2020.
            // Пример: "iyr:2019"
            // допустимые диапазоны
            minLimit = 2010;
            maxLimit = 2020;
            this.issueYear = fillYearAndHeight(curentField, minLimit, maxLimit);
        } else if (curentField.contains("eyr")) {

            //(Год окончания срока действия) – четыре цифры; минимум 2020 и максимум 2030
            // Пример строки: "eyr:2029"
            // допустимые диапазоны
            minLimit = 2020;
            maxLimit = 2030;
            this.expirationYear = fillYearAndHeight(curentField, minLimit, maxLimit);
        } else if (curentField.contains("hgt")) {

            fillHeight(curentField);
        } else if (curentField.contains("hcl")) {

            // (Цвет волос) – a, #за которым следуют ровно шесть символов 0– 9 или a– f
            fillHeirColor(curentField);
        } else if (curentField.contains("pid")) {
            // (ID паспорта) – девятизначное число, включая лидирующие нули.
            fillPassportId(curentField);
        } else if (curentField.contains("ecl")) {

            //(Цвет глаз) – ровно один из: amb blu brn gry grn hzl oth
            fillEyeColor(curentField);
        }
    }



    // Заполнить паспорт и проверить его на действительность
    // (ID паспорта) – девятизначное число, включая лидирующие нули.
    private void fillPassportId(String curentField) {
        final int COUNT_DIGIT = 9;
        String onVerification = curentField.substring(4);

        // если в строке есть что-то кроме чисел, то будет не пустой
        String strForDelDigit = onVerification.replaceAll("\\d", "");
        if ((onVerification.length() != COUNT_DIGIT) || (strForDelDigit.length() != 0)) {
            this.isValidOrInvalidField.add(false);
        } else {
            // проверить будет ли сумма больше 0
            this.passportID = Integer.parseInt(onVerification);
            if (passportID > 0) {
                this.isValidOrInvalidField.add(true);
            } else {
                this.isValidOrInvalidField.add(false);
            }
        }
    }

    // заполнить поле "цвет глаз", определить действительно ли оно
    private void fillEyeColor(String curentField) {
        String allColor = "amb blu brn gry grn hzl oth";
        this.eyeColor = curentField.substring(4);

        // символов может быть больше 3
        if (allColor.contains(eyeColor) && !(eyeColor.length() > 3)) {
            this.isValidOrInvalidField.add(true);
        } else {
            this.isValidOrInvalidField.add(false);
        }
    }

    // Выделить строку для цвета волос и определить ее верность
    private void fillHeirColor(String curentField) {
        int test2 = 9;
        this.hairColor = curentField.substring(5);
        int sizeHairColor = hairColor.length();
        if (!curentField.contains("#") || (sizeHairColor != 6)) {
            this.isValidOrInvalidField.add(false);
        } else {
            //hcl(Цвет волос) –  # за которым следуют ровно шесть символов 0 – 9 или a– f.

            String validChar = "abcdef";

            /**
             * аналог удаления чисел firstname1 = firstname1.replaceAll("[0-9]","");
             */
            // удалить все числа из строки
            String strForDelDigit = hairColor.replaceAll("\\d", "");
            char[] charStrHair = strForDelDigit.toCharArray();

            int sizeCharArr = charStrHair.length;

            // пройти по массиву символов, узнать если этот символ в списке допустимых
            for (int indexChar = 0; indexChar < sizeCharArr; indexChar++) {

                if (!validChar.contains(String.valueOf(charStrHair[indexChar]))) {
                    this.isValidOrInvalidField.add(false);
                    break;
                } else if (indexChar == (sizeCharArr - 1)) {
                    this.isValidOrInvalidField.add(true);
                }
            }

            // если строка состояла из чисел, то действительна
            if (strForDelDigit.length() == 0) {
                this.isValidOrInvalidField.add(true);
            }
            int test = 9;
        }
    }


    // получить число для заполнения года и роста
    private int fillYearAndHeight(String curentField, int minLimit, int maxLimit) {
        int currentDigit = 0;

        // Сформироать число для поля
        if (curentField.contains("in") || curentField.contains("cm")) {

            //исключить символы размерности, их 2
            String strForDigit = curentField.substring(4, curentField.length() - 2);
            currentDigit = Integer.parseInt(strForDigit);
        } else {

            // Число определяется одинаково для "byr", "iyr", "eyr"
            currentDigit = Integer.parseInt(curentField.substring(4));
        }


        // установить флаг для текущего поля
        /**
         * Для необходимости иметь связь между полем и значением использовать map вместо set и AL
         */
        if (minLimit <= currentDigit && maxLimit >= currentDigit) {
            this.isValidOrInvalidField.add(true);
        } else {
            this.isValidOrInvalidField.add(false);
        }
        return currentDigit;
    }

    // Обработать строку с размерностями для поля height
    private void fillHeight(String curentField) {

        //(Высота)  если cm, то не менее 150 и не более 193; in, то не менее 59 и не более 76
        // Пример строки: "hgt:74in"
        int minLimit;
        int maxLimit;//Если в конце строки-поля есть размерность, то продолжить ее анализ
        if (curentField.contains("in")) {
            minLimit = 59;
            maxLimit = 76;
            this.expirationYear = fillYearAndHeight(curentField, minLimit, maxLimit);
        } else if (curentField.contains("cm")) {
            minLimit = 150;
            maxLimit = 193;
            this.expirationYear = fillYearAndHeight(curentField, minLimit, maxLimit);
        } else {
            this.isValidOrInvalidField.add(false);
        }
    }

    public int getByr() {
        return birthYear;
    }

    public void setByr(int byr) {
        this.birthYear = byr;
    }

    public int getIyr() {
        return issueYear;
    }

    public void setIyr(int iyr) {
        this.issueYear = iyr;
    }

    public int getEyr() {
        return expirationYear;
    }

    public void setEyr(int eyr) {
        this.expirationYear = eyr;
    }

    public int getHgt() {
        return height;
    }

    public void setHgt(int hgt) {
        this.height = hgt;
    }

    public String getHcl() {
        return hairColor;
    }

    public void setHcl(String hcl) {
        this.hairColor = hcl;
    }

    public String getEcl() {
        return eyeColor;
    }

    public void setEcl(String ecl) {
        this.eyeColor = ecl;
    }

    public int getPid() {
        return passportID;
    }

    public void setPid(int pid) {
        this.passportID = pid;
    }

    public boolean isValid_part_2() {
        return isValid_part_2;
    }

    public void setValid_part_2(boolean valid_part_2) {
        isValid_part_2 = valid_part_2;
    }
}
