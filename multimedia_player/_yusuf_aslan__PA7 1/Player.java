// This class represents a player that plays playable objects.

// And overrides the needed methods 

import java.util.List;

public class Player implements Observer {
    private Playable currentlyPlaying;
    /**
     * Updates the player with new information from the dataset.
     * object The object that triggered the update.
     */

    @Override
    public void update(Object object) {
        if (object instanceof Playable) {
            currentlyPlaying = (Playable) object;
        }
    }

    /**
     * Returns the currently playing object.
     */
    public Playable currentlyPlaying() {
        return currentlyPlaying;
    }


    /**
     * Displays the list of playables in the playlist.
     * playables The list of playables to be displayed.
     */
    public void showList(List<Playable> playables) {
        System.out.println("Playlist:");
        for (Playable playable : playables) {
            playable.info();
        }
    }


    /**
     * Plays the next playable object in the playlist of the given type.
     * playables The list of playables.
     * type The type of playable to play ("audio", "video",etc..).
     */
    public void next(List<Playable> playables, String type) {
        int currentIndex = playables.indexOf(currentlyPlaying);
        int nextIndex = getNextIndex(playables, currentIndex, type);
        if (nextIndex != -1) {
            currentlyPlaying = playables.get(nextIndex);
            currentlyPlaying.play();
        } else {
            System.out.println("No next " + type + " found.");
        }
    }

    /**
     * Plays the previous playable object in the playlist of the given type.
     * playables The list of playables.
     * type The type of playable to play (e.g., "audio", "video").
     */
    public void previous(List<Playable> playables, String type) {
        int currentIndex = playables.indexOf(currentlyPlaying);
        int previousIndex = getPreviousIndex(playables, currentIndex, type);
        if (previousIndex != -1) {
            currentlyPlaying = playables.get(previousIndex);
            currentlyPlaying.play();
        } else {
            System.out.println("No previous " + type + " found.");
        }
    }

    /**
     * Gets the index of the next playable object of the given type.
     * playables The list of playables.
     * currentIndex The current index.
     * type The type of playable to search for.
     * return The index of the next playable object, or -1 if not found.
     */    

    private int getNextIndex(List<Playable> playables, int currentIndex, String type) {
        for (int i = currentIndex + 1; i < playables.size(); i++) {
            if (playables.get(i).getClass().getSimpleName().equalsIgnoreCase(type)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Gets the index of the previous playable object of the given type.
     * playables The list of playables.
     * currentIndex The current index.
     * type The type of playable to search for.
     * return The index of the previous playable object, or -1 if not found.
     */
    private int getPreviousIndex(List<Playable> playables, int currentIndex, String type) {
        for (int i = currentIndex - 1; i >= 0; i--) {
            if (playables.get(i).getClass().getSimpleName().equalsIgnoreCase(type)) {
                return i;
            }
        }
        return -1;
    }
}
