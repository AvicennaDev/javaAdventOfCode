package adventOfCode_2020.DAY4_20;

import common.AocSolverAbstract;

import java.util.ArrayList;


public class DAY4_20_implement extends AocSolverAbstract<Integer, Integer> {

    // Решение части 1
    @Override

    protected Integer calculatePart1_Solution(ArrayList<String> linesArrList) {

        String[] fieldsArr = {"byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"};
        int countField = 0; // обнулить при ""
        int countValidPasport = 0;
        int sizeFile = linesArrList.size();
        int sizeArrFields = fieldsArr.length;

        for (int indexFile = 0; indexFile < sizeFile; indexFile++) {
            String currentLine = linesArrList.get(indexFile);

            if (currentLine.equals("")) {
                if (countField == sizeArrFields) {
                    countValidPasport++;
                }
                countField = 0; // Обнуление количества полей
                continue;
            }
            for (int indexField = 0; indexField < sizeArrFields; indexField++) {

                String currenField = fieldsArr[indexField];
                if (currentLine.contains(currenField + ":")) {
                    countField++;
                }
            }
        }

        // учет последнего паспорта
        if (countField == sizeArrFields) {
            countValidPasport++;
        }
        return countValidPasport;
    }

    @Override
    protected Integer calculatePart2_Solution(ArrayList<String> linesArrList) {
        //prepareCommonData(linesArrList);

        // поля в паспорте
        String[] fieldsArr = {"byr", "iyr", "eyr", "hgt", "ecl", "pid", "hcl"}; // hcl нет  в 1 части
        int sizeFile = linesArrList.size();

        // дублирование кода
        int countField = 0; //  для подсчета допустымых полей у текущего паспорта
        int countValidPasport = 0; // содержит сумму действительных паспортов
        int sizeArrFields = fieldsArr.length;
        boolean newPassportBool = true; // определяет создавать или нет новый объект "паспорт"
        Passport curentPasport = new Passport();

        ArrayList<Passport> passportsArrLis = new ArrayList<Passport>();

        for (int indexFileLine = 0; indexFileLine < sizeFile; indexFileLine++) {

            String currentLine = linesArrList.get(indexFileLine);

            // создать новый объект паспорт если newPassportBool = true
            if(newPassportBool){
                curentPasport =  new Passport(); // создаст  новую ссылку
                newPassportBool = false;
            }

            if (currentLine == "") {
                countField = curentPasport.allField.size(); // количество всех полей у объекта их 7(у 2 части)
                // добавить паспорт если есть все поля
                if (countField == sizeArrFields) {
                    passportsArrLis.add(curentPasport); // почему важно new вне if
                }
                newPassportBool = true;
                continue;
            }

            /**
             * В  StringBuilder нет split
             */
             String[] fieldsStrArr = currentLine.split(" ");
            //заполнить данные коллекцию полей паспорта
            for (String indexInArr : fieldsStrArr) {

                /**
                 * вынести удаление cid в подготовку данных
                 */
                // если cid, то не сохранять
                if(!indexInArr.contains("cid")) {
                    curentPasport.allField.add(indexInArr);
                }
            }

        }

        // учесть последний паспорт
        /**
         * возможно стоит вынести в if
         */
        countField = curentPasport.allField.size(); // количество всех полей у объекта их 7(у 2 части)
        // добавить паспорт если есть все поля
        if (countField == sizeArrFields) {
            passportsArrLis.add(curentPasport); // почему важно new вне if
        }

        // пройти по коллекции объектов Паспорта
        int sizeArrLisPasp = passportsArrLis.size();
        for(int indexObj = 0; indexObj < sizeArrLisPasp; indexObj++){

            // вызвать метод для заполнения полей объекта паспорт
            passportsArrLis.get(indexObj).fillFilder();
            boolean isValidPassport = passportsArrLis.get(indexObj).isValid_part_2();
            if(isValidPassport){
                countValidPasport++;
            }
        }

        int test = 9;


        // вызвать метод для заполнения паспорта на случай не учтенного последнего паспорта
        return countValidPasport;
    }

//    private String toString(StringBuilder fieldsForDel) {
//    }
}
