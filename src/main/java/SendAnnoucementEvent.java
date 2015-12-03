
/**
 * Created by eghibad on 12/3/15.
 */
import com.ericsson.duraci.datawrappers.BaselineContext;
import com.ericsson.duraci.datawrappers.BaselinePart;
import com.ericsson.duraci.eiffelmessage.messages.EiffelEvent;
import com.ericsson.duraci.eiffelmessage.messages.EiffelMessage;
import com.ericsson.duraci.eiffelmessage.messages.events.EiffelAnnouncementEvent;
import com.ericsson.duraci.eiffelmessage.mmparser.clitool.EiffelConfig;
import com.ericsson.duraci.eiffelmessage.sending.MessageSender;
import com.ericsson.duraci.eiffelmessage.sending.exceptions.EiffelMessageSenderException;

public class SendAnnoucementEvent {
    public static void main(String[] args) throws InterruptedException {
        final EiffelConfig config = new EiffelConfig(
                "ScottDomain",
                "GhinwaExchange",
                "142.133.111.127");


        final EiffelEvent eSendAnnoucementEvent = EiffelAnnouncementEvent.Factory.create("subject", "body","url","fatal");
        final EiffelMessage eMessage = EiffelMessage.Factory.create("ScottDomain", eSendAnnoucementEvent);
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
