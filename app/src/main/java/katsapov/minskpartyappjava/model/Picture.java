package katsapov.minskpartyappjava.model;

public class Picture {

    private int image;
    private String name;

    Picture(String name, int image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }
    public int getImage() {
        return image;
    }
}
