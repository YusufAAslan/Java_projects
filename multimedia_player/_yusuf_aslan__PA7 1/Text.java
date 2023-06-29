// Implement the Text class which nonPlayable
// And override the needed methods

public class Text implements NonPlayable {
    private String name;
    private String otherInfo;

    public Text(String name, String otherInfo) {
        this.name = name;
        this.otherInfo = otherInfo;
    }

    @Override
    public void show() {
        System.out.println("Showing text: " + name);
    }

    @Override
    public void info() {
        System.out.println("Text Info: Name=" + name + ", OtherInfo=" + otherInfo);
    }
}
