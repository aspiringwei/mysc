package no.lwb.base.listener.observer;

/**
 * @author WeiBin Lin
 */
public class ObserverTest {

    public static void main(String[] args) {
        Subject subject = new Subject();
        subject.addObserver(new SubjectObserver());
        subject.saySomething("我怎么这么好看..");
    }
}
