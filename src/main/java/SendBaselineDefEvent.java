/**
 * Created by eghibad on 12/3/15.
 */

import com.ericsson.duraci.datawrappers.BaselineContext;
import com.ericsson.duraci.datawrappers.BaselinePart;
import com.ericsson.duraci.eiffelmessage.messages.EiffelEvent;
import com.ericsson.duraci.eiffelmessage.messages.EiffelMessage;
import com.ericsson.duraci.eiffelmessage.messages.events.EiffelBaselineDefinedEvent;
import com.ericsson.duraci.eiffelmessage.mmparser.clitool.EiffelConfig;
import com.ericsson.duraci.eiffelmessage.sending.MessageSender;
import com.ericsson.duraci.eiffelmessage.sending.exceptions.EiffelMessageSenderException;
import java.util.ArrayList;
import java.util.List;

public class SendBaselineDefEvent {

    public static void main(String[] args) throws InterruptedException {
        final EiffelConfig config = new EiffelConfig(
                "ScottDomain",
                "GhinwaExchange",
                "142.133.111.127");

        List<BaselinePart> baselineContextList = new ArrayList<BaselinePart>();
        BaselineContext baselineContext = new BaselineContext();

        final EiffelEvent eBaseLineDefEvent = EiffelBaselineDefinedEvent.Factory.create("baseLineTest",baselineContextList,baselineContext);
        final EiffelMessage eMessage = EiffelMessage.Factory.create("ScottDomain", eBaseLineDefEvent);
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
