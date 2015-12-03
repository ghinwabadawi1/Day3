/**
 * Created by eghibad on 12/3/15.
 */

import com.ericsson.duraci.datawrappers.ExecutionId;
import com.ericsson.duraci.datawrappers.LogReference;
import com.ericsson.duraci.datawrappers.ResultCode;
import com.ericsson.duraci.eiffelmessage.messages.EiffelEvent;
import com.ericsson.duraci.eiffelmessage.messages.EiffelMessage;
import com.ericsson.duraci.eiffelmessage.messages.events.EiffelJobStepFinishedEvent;
import com.ericsson.duraci.eiffelmessage.mmparser.clitool.EiffelConfig;
import com.ericsson.duraci.eiffelmessage.sending.MessageSender;
import com.ericsson.duraci.eiffelmessage.sending.exceptions.EiffelMessageSenderException;
import java.util.HashMap;
import java.util.Map;

public class SendStepFinishedEvent {
    public static void main(String[] args) throws InterruptedException {
        final EiffelConfig config = new EiffelConfig(
                "ScottDomain",
                "GhinwaExchange",
                "142.133.111.127");

        ExecutionId executionId = new ExecutionId();
        Map<String, LogReference> logReferences = new HashMap<String, LogReference>();
        final EiffelEvent eStepFinishedEvent = EiffelJobStepFinishedEvent.Factory.create(ResultCode.ERROR,logReferences,executionId);
        final EiffelMessage eMessage = EiffelMessage.Factory.create("ScottDomain", eStepFinishedEvent);
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
