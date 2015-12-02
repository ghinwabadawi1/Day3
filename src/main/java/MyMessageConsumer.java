/**
 * Created by eghibad on 12/2/15.
 */
import java.util.Collection;
import java.util.UUID;

import com.ericsson.duraci.eiffelmessage.binding.MessageConsumer;
import com.ericsson.duraci.eiffelmessage.binding.exceptions.EiffelMessageConsumptionException;
import com.ericsson.duraci.eiffelmessage.messages.EiffelMessage;

public class MyMessageConsumer implements MessageConsumer {

    private final Collection<EiffelMessage> messageList;
    private final String name;

    public MyMessageConsumer(final Collection<EiffelMessage> messageList) throws Exception {
        if (messageList == null) {
            throw new Exception("messageList is null");
        }
        this.messageList = messageList;
        name = "FemTestMessageConsumer." + UUID.randomUUID();
    }

    @Override
    public synchronized void consumeMessage(final EiffelMessage message) throws EiffelMessageConsumptionException {
        messageList.add(message);
    }

    @Override
    public synchronized String getName() {
        return name;
    }

    public synchronized Collection<EiffelMessage> getMessageList() {
        return messageList;
    }

}