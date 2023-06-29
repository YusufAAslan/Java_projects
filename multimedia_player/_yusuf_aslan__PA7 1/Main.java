// Main driver 
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Create dataset
        Dataset ds = new Dataset();

        // Create observers
        Player p1 = new Player();
        Player p2 = new Player();
        Viewer v1 = new Viewer();
        Viewer v2 = new Viewer();

        // Register observers to the dataset
        ds.register(p1);
        ds.register(p2);
        ds.register(v1);
        ds.register(v2);

        // Create different objects and add them to the dataset
        ds.add(new Image("imagename1", "dimension info1", "other info1"));
        ds.add(new Image("imagename2", "dimension info2", "other info2"));
        ds.add(new Image("imagename3", "dimension info3", "other info3"));
        ds.add(new Image("imagename4", "dimension info4", "other info4"));
        ds.add(new Image("imagename5", "dimension info5", "other info5"));

        ds.add(new Audio("audioname1", "duration1", "other info1"));
        ds.add(new Audio("audioname2", "duration2", "other info2"));
        ds.add(new Audio("audioname3", "duration3", "other info3"));

        ds.add(new Video("videoname1", "duration1", "other info1"));
        ds.add(new Video("videoname2", "duration2", "other info2"));
        ds.add(new Video("videoname3", "duration3", "other info3"));

        ds.add(new Text("textname1", "other info1"));
        ds.add(new Text("textname2", "other info2"));
        ds.add(new Text("textname3", "other info3"));

        // Test player object
        Playable po = p1.currentlyPlaying();
        if (po != null) {
            po.info();
        }

        // Remove the object from the dataset
        ds.remove(po);

        // Test viewer object
        NonPlayable np = v1.currentlyViewing();
        if (np != null) {
            np.info();
        }

        // Test player object methods
        List<Playable> playables = new ArrayList<>();
        for (Object object : ds.getObjects()) {
            if (object instanceof Playable) {
                playables.add((Playable) object);
            }
        }

        p1.showList(playables);
        p1.next(playables, "Video");
        p1.previous(playables, "Audio");

        // Test viewer object methods
        List<NonPlayable> nonPlayables = new ArrayList<>();
        for (Object object : ds.getObjects()) {
            if (object instanceof NonPlayable) {
                nonPlayables.add((NonPlayable) object);
            }
        }

        v1.showList(nonPlayables);
        v1.next(nonPlayables, "Image");
        v1.previous(nonPlayables, "Text");
    }
}
