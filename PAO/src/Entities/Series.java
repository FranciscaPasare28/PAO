package Entities;

public class Series {
    private int number;
    private boolean olympic;
    public Series(){}

    public Series(int number, boolean olympic) {
        this.number = number;
        this.olympic = olympic;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isOlympic() {
        return olympic;
    }

    public void setOlympic(boolean olympic) {
        this.olympic = olympic;
    }
}
