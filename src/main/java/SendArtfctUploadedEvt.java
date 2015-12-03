/**
 * Created by eghibad on 12/3/15.
 */

import com.ericsson.duraci.datawrappers.ArtifactGav;
import com.ericsson.duraci.datawrappers.ConfidenceLevels;
import com.ericsson.duraci.eiffelmessage.messages.EiffelEvent;
import com.ericsson.duraci.eiffelmessage.messages.EiffelMessage;
import com.ericsson.duraci.eiffelmessage.messages.events.EiffelArtifactUploadedEvent;
import com.ericsson.duraci.eiffelmessage.mmparser.clitool.EiffelConfig;
import com.ericsson.duraci.eiffelmessage.sending.MessageSender;
import com.ericsson.duraci.eiffelmessage.sending.exceptions.EiffelMessageSenderException;

public class SendArtfctUploadedEvt {

    public static void main(String[] args) throws InterruptedException {
        final EiffelConfig config = new EiffelConfig(
                "ScottDomain",
                "GhinwaExchange",
                "142.133.111.127");

        ArtifactGav artifactGav = new ArtifactGav();
        final EiffelEvent eArfctUploadedEvent = EiffelArtifactUploadedEvent.Factory.create(artifactGav,"packaging", "repUrl");
        final EiffelMessage eMessage = EiffelMessage.Factory.create("ScottDomain", eArfctUploadedEvent);
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
