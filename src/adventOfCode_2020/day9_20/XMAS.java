package adventOfCode_2020.day9_20;

import java.util.ArrayList;

// класс для сохраниения преабулы и последующего числа, назван по системе шифрования
public class XMAS {

    protected ArrayList<Long> preambleArrLis = new ArrayList<Long>(); // для сохранения прембулы
    private long mask = 0;
    private long sumInPreambleArrLis = 0; // хранить сумму всех чисел в выделенно преамбуле

    public ArrayList<Long> getPreambleArrLis() {
        return preambleArrLis;
    }

    public void setPreambleArrLis(ArrayList<Long> preambleArrLis) {
        this.preambleArrLis = preambleArrLis;
    }

    public long getMask() {
        return mask;
    }

    public void setMask(long mask) {
        this.mask = mask;
    }

    // посчитать сумму элементов коллекции
    public long getSumInPreambleArrLis(){

        for (long currentDigit : preambleArrLis){
            sumInPreambleArrLis += currentDigit;
        }

        return sumInPreambleArrLis;
    }
}
