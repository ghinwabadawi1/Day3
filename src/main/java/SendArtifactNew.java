/**
 * Created by eghibad on 12/3/15.
 */
import com.ericsson.duraci.datawrappers.*;
import com.ericsson.duraci.eiffelmessage.messages.EiffelEvent;
import com.ericsson.duraci.eiffelmessage.messages.EiffelMessage;
import com.ericsson.duraci.eiffelmessage.messages.events.EiffelArtifactNewEvent;
import com.ericsson.duraci.eiffelmessage.mmparser.clitool.EiffelConfig;
import com.ericsson.duraci.eiffelmessage.sending.MessageSender;
import com.ericsson.duraci.eiffelmessage.sending.exceptions.EiffelMessageSenderException;

public class SendArtifactNew {
    public static void main(String[] args) throws InterruptedException {
        final EiffelConfig config = new EiffelConfig(
                "ScottDomain",
                "GhinwaExchange",
                "142.133.111.127");

        ConfidenceLevels confidenceLevels = new ConfidenceLevels();
        ArtifactGav artifactGav = new ArtifactGav();
        final EiffelEvent eFinishedEvent = EiffelArtifactNewEvent .Factory.create(confidenceLevels, artifactGav);
        final EiffelMessage eMessage = EiffelMessage.Factory.create("ScottDomain", eFinishedEvent);
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


