package adventOfCode_2018.day3_18;


public class Sector {

    // поля для хранения информации для размеров участка
    private int left = 0;
    private int top = 0;
    private int width = 0;
    private int height = 0;

    public Sector (String sectorDescription){
        String[] descriptionParts = sectorDescription.split(" ");

        getCoordinates(descriptionParts[2]);
        getSizes(descriptionParts[3]);
    }

    // получить числовые значения для расстояний от лева и от верха до площади ткани
    private void getCoordinates(String coordinateDescription){
        String[] coordinates = coordinateDescription
                .replace(":", "")
                .split(",");

        left = Integer.parseInt(coordinates[0]);
        top = Integer.parseInt(coordinates[1]);
    }

    // получить значения ширины и высоты площади ткани
    private void getSizes(String sizeDescription){
        String[] sizes = sizeDescription
                .split("x");

        width = Integer.parseInt(sizes[0]);
        height = Integer.parseInt(sizes[1]);
    }

    // з

    public int getLeft() {
        return left;
    }

    public int getTop() {
        return top;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
