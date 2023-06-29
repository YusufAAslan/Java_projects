/**
 * This class represents a viewer that views non-playable objects.
 */

import java.util.List;

public class Viewer implements Observer {
    private NonPlayable currentlyViewing;

    /**
     * Updates the viewer with new information from the dataset.
     * object The object that triggered the update.
     */
    @Override
    public void update(Object object) {
        if (object instanceof NonPlayable) {
            currentlyViewing = (NonPlayable) object;
        }
    }


    /**
     * Returns the currently viewing object.
     * The currently viewing object.
     */

    public NonPlayable currentlyViewing() {
        return currentlyViewing;
    }


    /**
     * Displays the list of non-playables in the view list.
     * nonPlayables The list of non-playables to be displayed.
     */
    public void showList(List<NonPlayable> nonPlayables) {
        System.out.println("View List:");
        for (NonPlayable nonPlayable : nonPlayables) {
            nonPlayable.info();
        }
    }


    /**
     * Shows the next non-playable object in the view list of the given type.
     * nonPlayables The list of non-playables.
     * type The type of non-playable to show (e.g., "image", "txt").
     */
    public void next(List<NonPlayable> nonPlayables, String type) {
        int currentIndex = nonPlayables.indexOf(currentlyViewing);
        int nextIndex = getNextIndex(nonPlayables, currentIndex, type);
        if (nextIndex != -1) {
            currentlyViewing = nonPlayables.get(nextIndex);
            currentlyViewing.show();
        } else {
            System.out.println("No next " + type + " found.");
        }
    }

    

    /**
     * Shows the previous non-playable object in the view list of the given type.
     * nonPlayables The list of non-playables.
     * type The type of non-playable to show (e.g., "image", "txt").
     */
    public void previous(List<NonPlayable> nonPlayables, String type) {
        int currentIndex = nonPlayables.indexOf(currentlyViewing);
        int previousIndex = getPreviousIndex(nonPlayables, currentIndex, type);
        if (previousIndex != -1) {
            currentlyViewing = nonPlayables.get(previousIndex);
            currentlyViewing.show();
        } else {
            System.out.println("No previous " + type + " found.");
        }
    }


    /**
     * Gets the index of the next non-playable object of the given type.
     * nonPlayables The list of non-playables.
     * currentIndex The current index.
     * type The type of non-playable to search for.
     * return The index of the next non-playable object, or -1 if not found.
     */
    private int getNextIndex(List<NonPlayable> nonPlayables, int currentIndex, String type) {
        for (int i = currentIndex + 1; i < nonPlayables.size(); i++) {
            if (nonPlayables.get(i).getClass().getSimpleName().equalsIgnoreCase(type)) {
                return i;
            }
        }
        return -1;
    }


    /**
     * Gets the index of the previous non-playable object of the given type.
     * nonPlayables The list of non-playables.
     * currentIndex The current index.
     * type The type of non-playable to search for.
     * return The index of the previous non-playable object, or -1 if not found.
     */    
    private int getPreviousIndex(List<NonPlayable> nonPlayables, int currentIndex, String type) {
        for (int i = currentIndex - 1; i >= 0; i--) {
            if (nonPlayables.get(i).getClass().getSimpleName().equalsIgnoreCase(type)) {
                return i;
            }
        }
        return -1;
    }
}
