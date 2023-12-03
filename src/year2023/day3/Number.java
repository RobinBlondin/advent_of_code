package year2023.day3;

public class Number {
    int number;
    int column;
    int row;

    public Number(int number, int column, int row) {
        this.number = number;
        this.column = column;
        this.row = row;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }
}
