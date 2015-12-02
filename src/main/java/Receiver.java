/**
 * Created by eghibad on 12/2/15.
 */

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Vector;

import com.ericsson.duraci.datawrappers.MessageBus;
import com.ericsson.duraci.eiffelmessage.binding.MessageBusBindings;
import com.ericsson.duraci.eiffelmessage.binding.exceptions.EiffelMessageBindingException;
import com.ericsson.duraci.eiffelmessage.messages.EiffelEvent;
import com.ericsson.duraci.eiffelmessage.messages.EiffelMessage;

public class Receiver {

    public static void main(String[] args) throws Exception {
        Collection<EiffelMessage> messageList = Collections.<EiffelMessage>emptyList();
        messageList = new Vector<EiffelMessage>();
        MyMessageConsumer consumer = new MyMessageConsumer(messageList);
        final MessageBus mb = new MessageBus("142.133.111.127", "GhinwaExchange",
                "Receiver");
        try {
            MessageBusBindings messageBusBindings = new MessageBusBindings.Factory().create(mb, "ScottDomain");
            messageBusBindings.add(consumer, "#." + "ScottDomain", false, false);

            while (true) {
                System.out.println("Checking...");
                if (messageList.isEmpty()) {
                    System.out.println("  - empty");
                } else {
                    System.out.println("  - " + messageList.size() + " entries...");
                    for (EiffelMessage message : messageList) {
                        EiffelEvent event = message.getEvent();
                        String eventType = message.getEventType();
                        System.out.println("Event Type: " + eventType);
                        System.out.println(event.toString());
                        System.exit(0);
                    }
                }
                Thread.sleep(5000);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (EiffelMessageBindingException e) {
            e.printStackTrace();
        }

    }


}
