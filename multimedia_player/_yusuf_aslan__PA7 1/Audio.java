// Implement the Audio class which playable
// And override the needed methods

public class Audio implements Playable {
    private String name;
    private String duration;
    private String otherInfo;

    public Audio(String name, String duration, String otherInfo) {
        this.name = name;
        this.duration = duration;
        this.otherInfo = otherInfo;
    }

    @Override
    public void play() {
        System.out.println("Playing audio: " + name);
    }

    @Override
    public void stop() {
        System.out.println("Stopping audio: " + name);
    }

    @Override
    public void info() {
        System.out.println("Audio Info: Name=" + name + ", Duration=" + duration + ", OtherInfo=" + otherInfo);
    }
}
