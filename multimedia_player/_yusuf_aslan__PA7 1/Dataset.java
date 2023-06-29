// Creating the base class Dataset
import java.util.ArrayList;
import java.util.List;

public class Dataset {
    private List<Object> objects;
    private List<Observer> observers;

    /**
     * Constructor for the Dataset class.
     * Initializes the object and observer lists.
     */
    public Dataset() {
        objects = new ArrayList<>();
        observers = new ArrayList<>();
    }

    /**
     * Registers an observer to the dataset.
     * observer The observer to be registered.
     */
    public void register(Observer observer) {
        observers.add(observer);
    }


    /**
     * Removes an observer from the dataset.
     * observer The observer to be removed.
     */
    public void unregister(Observer observer) {
        observers.remove(observer);
    }

    /**
     * Adds an object to the dataset.
     * Notifies all observers about the added object.
     * object The object to be added.
     */
    public void add(Object object) {
        objects.add(object);
        notifyObservers(object);
    }

    /**
     * Removes an object from the dataset.
     * Notifies all observers about the removed object.
     * object The object to be removed.
     */    
    public void remove(Object object) {
        objects.remove(object);
        notifyObservers(object);
    }

    /**
     * Notifies all observers about a change in the dataset.
     * object The object that triggered the notification.
     */
    private void notifyObservers(Object object) {
        for (Observer observer : observers) {
            observer.update(object);
        }
    }


    /**
     * Returns the list of objects in the dataset.
     */    
    public List<Object> getObjects() {
        return objects;
    }
}
