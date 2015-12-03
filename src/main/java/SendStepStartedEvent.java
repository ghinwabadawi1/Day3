/**
 * Created by eghibad on 12/3/15.
 */

import com.ericsson.duraci.datawrappers.ExecutionId;
import com.ericsson.duraci.datawrappers.ResultCode;
import com.ericsson.duraci.eiffelmessage.messages.EiffelEvent;
import com.ericsson.duraci.eiffelmessage.messages.EiffelMessage;
import com.ericsson.duraci.eiffelmessage.messages.events.EiffelJobFinishedEvent;
import com.ericsson.duraci.eiffelmessage.messages.events.EiffelJobStepStartedEvent;
import com.ericsson.duraci.eiffelmessage.mmparser.clitool.EiffelConfig;
import com.ericsson.duraci.eiffelmessage.sending.MessageSender;
import com.ericsson.duraci.eiffelmessage.sending.exceptions.EiffelMessageSenderException;

public class SendStepStartedEvent {

    public static void main(String[] args) throws InterruptedException {
        final EiffelConfig config = new EiffelConfig(
                "ScottDomain",
                "GhinwaExchange",
                "142.133.111.127");

        ExecutionId executionId = new ExecutionId();

        final EiffelEvent eStepStartedEvent = EiffelJobStepStartedEvent.Factory.create(executionId, 3, 0);
        final EiffelMessage eMessage = EiffelMessage.Factory.create("ScottDomain", eStepStartedEvent);
        final MessageSender sender = new MessageSender.Factory(config).create();
        try {
            sender.send(eMessage);
        } catch (EiffelMessageSenderException e) {
            e.printStackTrace();
            System.exit(1);
        }
        System.out.println("Sending Message : " + eMessage);
        Thread.sleep(50000);
        sender.dispose();
    }
}
