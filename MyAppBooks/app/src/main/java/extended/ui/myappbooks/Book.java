package extended.ui.myappbooks;

import java.util.Arrays;

public class Book {
    private int ID;
    private String NameBook;
    private String Author;
    private String Caterogy;
    private String Desc;
    private int Cost;
    private byte[] Img;
    private int Count;

    public Book(int ID, String nameBook, String desc, int cost, byte[] img) {
        this.ID = ID;
        NameBook = nameBook;
        Desc = desc;
        Cost = cost;
        Img = img;
    }

    @Override
    public String toString() {
        return "Book{" +
                "ID=" + ID +
                ", NameBook='" + NameBook + '\'' +
                ", Author='" + Author + '\'' +
                ", Caterogy='" + Caterogy + '\'' +
                ", Desc='" + Desc + '\'' +
                ", Cost=" + Cost +
                ", Img=" + Arrays.toString(Img) +
                ", Count=" + Count +
                '}';
    }

    public Book(String nameBook, String desc, int cost, byte[] img) {
        NameBook = nameBook;
        Desc = desc;
        Cost = cost;
        Img = img;

    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNameBook() {
        return NameBook;
    }

    public void setNameBook(String nameBook) {
        NameBook = nameBook;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getCaterogy() {
        return Caterogy;
    }

    public void setCaterogy(String caterogy) {
        Caterogy = caterogy;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

    public int getCost() {
        return Cost;
    }

    public void setCost(int cost) {
        Cost = cost;
    }

    public byte[] getImg() {
        return Img;
    }

    public void setImg(byte[] img) {
        Img = img;
    }

    public int getCount() {
        return Count;
    }

    public void setCount(int count) {
        Count = count;
    }
}
