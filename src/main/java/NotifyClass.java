import java.text.MessageFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * NotifyClass
 *
 * @Author Tretyakov Alexandr
 */

public class NotifyClass {
    private List<String> synchroList;

    public NotifyClass(){
        synchroList = Collections.synchronizedList(new ArrayList<>());
    }

    public String removeElement() throws InterruptedException {
        synchronized (synchroList) {
            while (synchroList.isEmpty()) {
                System.out.println(MessageFormat.format("Flow  {1}: is empty and waiting {0}",Instant.now(), Thread.currentThread().getName()));
                synchroList.wait();
                System.out.println(MessageFormat.format("Flow  {1}: is not empty and woke up {0}", Instant.now(), Thread.currentThread().getName()));
            }
            System.out.println(MessageFormat.format("Flow  {1}: deleting an element {0}", Instant.now(), Thread.currentThread().getName()));
            return synchroList.remove(0);
        }
    }

    public void addElement(String element) {
        synchronized (synchroList) {
            synchroList.add(element);
            synchroList.notifyAll();
            System.out.println(MessageFormat.format("Flow  {1}: added an element {2} and woke everyone up {0}", Instant.now(), Thread.currentThread().getName(), element));
        }
    }
}
