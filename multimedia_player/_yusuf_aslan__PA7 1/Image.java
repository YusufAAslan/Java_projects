// Implement the Image class
// And ovrride needed methods to show , info

public class Image implements NonPlayable {
    private String name;
    private String dimension;
    private String otherInfo;

    public Image(String name, String dimension, String otherInfo) {
        this.name = name;
        this.dimension = dimension;
        this.otherInfo = otherInfo;
    }

    @Override
    public void show() {
        System.out.println("Showing image: " + name);
    }

    @Override
    public void info() {
        System.out.println("Image Info: Name=" + name + ", Dimension=" + dimension + ", OtherInfo=" + otherInfo);
    }
}
