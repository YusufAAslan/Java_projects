// Implement the video class which playable
// And override the needed methods

public class Video implements Playable {
    private String name;
    private String duration;
    private String otherInfo;

    public Video(String name, String duration, String otherInfo) {
        this.name = name;
        this.duration = duration;
        this.otherInfo = otherInfo;
    }

    @Override
    public void play() {
        System.out.println("Playing video: " + name);
    }

    @Override
    public void stop() {
        System.out.println("Stopping video: " + name);
    }

    @Override
    public void info() {
        System.out.println("Video Info: Name=" + name + ", Duration=" + duration + ", OtherInfo=" + otherInfo);
    }
}
