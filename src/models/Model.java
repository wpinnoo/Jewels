package models;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.EventListenerList;

/**
 * Hoofdklasse voor alle models die gebruik maken van een ChangeListener.
 * Bron: http://twizz.ugent.be/student/prog2/src/prog2/mvc/Model.java.
 *
 * @author Wouter Pinnoo
 */
public abstract class Model {

    /**
     * Lijst van geregistreerde listeners.
     */
    private EventListenerList listenerList = new EventListenerList();

    /**
     * Registreer een listeners.
     *
     * @param l Deze listener wordt geregistreerd in de lijst.
     */
    public void addChangeListener(ChangeListener l) {
        listenerList.add(ChangeListener.class, l);
    }

    /**
     * Maak registratie ongedaan.
     *
     * @param l Deze listener wordt verwijderd uit de lijst.
     */
    public void removeChangeListener(ChangeListener l) {
        listenerList.remove(ChangeListener.class, l);
    }
    /**
     * Uniek gebeurtenisobject met dit model als bron.
     */
    private final ChangeEvent changeEvent = new ChangeEvent(this);

    /**
     * Behandel een ChangeEvent-gebeurtenis die van het model afkomstig is door
     * een nieuwe gebeurtenis aan alle luisteraars door te geven. De nieuwe
     * gebeurtenis heeft dit model als bron.
     */
    protected void fireStateChanged() {
        Object[] listeners = listenerList.getListenerList();
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == ChangeListener.class) {
                ((ChangeListener) listeners[i + 1]).stateChanged(changeEvent);
            }
        }
    }
}
