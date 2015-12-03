
/**
 * Created by eghibad on 12/3/15.
 */
import com.ericsson.duraci.datawrappers.scm.SCMChangeSet;
import com.ericsson.duraci.datawrappers.scm.SCMIdentifier;
import com.ericsson.duraci.datawrappers.scm.identifiers.GenericIdentifier;
import com.ericsson.duraci.eiffelmessage.messages.EiffelEvent;
import com.ericsson.duraci.eiffelmessage.messages.EiffelMessage;
import com.ericsson.duraci.eiffelmessage.messages.events.EiffelSCMChangedEvent;
import com.ericsson.duraci.eiffelmessage.mmparser.clitool.EiffelConfig;
import com.ericsson.duraci.eiffelmessage.sending.MessageSender;
import com.ericsson.duraci.eiffelmessage.sending.exceptions.EiffelMessageSenderException;

public class SendSCMChangedEvent {

    public static void main(String[] args) throws InterruptedException {
        final EiffelConfig config = new EiffelConfig(
                "ScottDomain",
                "GhinwaExchange",
                "142.133.111.127");

        SCMIdentifier identifier = new GenericIdentifier("type");
        SCMChangeSet changeSet = new SCMChangeSet("contributor", "team", "commitMsg");
        final EiffelEvent eSendSCMChngEvent = EiffelSCMChangedEvent.Factory.create(identifier,changeSet);
        final EiffelMessage eMessage = EiffelMessage.Factory.create("ScottDomain", eSendSCMChngEvent);
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
