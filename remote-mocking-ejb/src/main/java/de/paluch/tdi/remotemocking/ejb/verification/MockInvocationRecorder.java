package de.paluch.tdi.remotemocking.ejb.verification;

import com.thoughtworks.xstream.XStream;
import de.paluch.tdi.remotemocking.ejb.datastore.DataStore;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a>
 */
public class MockInvocationRecorder {

    private static final Logger LOG = Logger.getLogger(MockInvocationRecorder.class);


    /**
     * Record the invocation of a mock.
     *
     * @param invocationKey  the key of the invocation
     * @param args the arguments that should be tracked
     */
    public static void recordInvocation(String invocationKey, Object... args) {
        String nodeId = invocationKey.toLowerCase();

        LOG.info("recording invocation of: " + nodeId);
        try {
            MockInvocationRecorder recorder = new MockInvocationRecorder();

            List<String> serializedArguments = recorder.serializeArguments(args);
            MockInvocation invocation = new MockInvocation(serializedArguments);

            DataStore.getInstance().getDynamicDataStore().put(DataStore.INVOCATIONS_PREFIX + nodeId, invocation);
        } catch (Exception e) {
            // prevent mocks from being disturbed by exceptions.
            LOG.warn(e.getMessage(), e);
        }
    }

    /**
     *  convert a Object Array Object[]  to List of String List&lt;String&gt;
     * @param args a Object Array
     * @return a List of String representing the Object[], where the
     *              List elements are transferred to a XML by XStream
     */
    private List<String> serializeArguments(Object[] args) {
        List<String> result = new ArrayList<String>();

        XStream x = new XStream();
        for (Object object : args) {
            result.add(x.toXML(object));
        }

        return result;

    }

}
