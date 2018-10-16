package no.lwb.base.listener.observer;

/**
 * @author WeiBin Lin
 */
public class ObserverTest {

    public static void main(String[] args) {
        Subject subject = new Subject();
        SubjectObserver subjectObserver = new SubjectObserver(subject);
        SubjectObserver1 subjectObserver1 = new SubjectObserver1(subject);
        subject.saySomething("φ(0￣*)啦啦啦_φ(*￣0￣)′");
        subjectObserver1.remove();
        subject.saySomething("倒数 G.E.M.");
        subject.deleteObserver(subjectObserver);
        subject.saySomething("沙漠骆驼");
    }
}
