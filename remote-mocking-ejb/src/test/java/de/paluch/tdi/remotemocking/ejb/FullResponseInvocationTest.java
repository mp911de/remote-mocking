package de.paluch.tdi.remotemocking.ejb;

import de.paluch.tdi.remotemocking.common.SimpleResponse;
import de.paluch.tdi.remotemocking.ejb.datastore.DataStore;
import de.paluch.tdi.remotemocking.ejb.response.MockResponseFactory;
import de.paluch.tdi.remotemocking.ejb.verification.MockInvocation;
import de.paluch.tdi.remotemocking.ejb.verification.MockInvocationRecorder;
import org.junit.After;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a>
 */
public class FullResponseInvocationTest {

    @After
    public void after()
    {
        DataStore.close();
    }


    @Test
    public void testFullStack() {
        // initialize
        DataStore.getInstance();

        SimpleResponse response = MockResponseFactory.getResponse("simpleresponse", SimpleResponse.class);
        assertNotNull(response);
        assertEquals("responsevalue", response.getValue());

        MockInvocationRecorder.recordInvocation("theKey", "a", "b", "c");

        List<String> dynamicIds = DataStore.getInstance().getDynamicDataStore().listNames();
        assertEquals(1, dynamicIds.size());

        List<String> subDynamicIds = DataStore.getInstance().getDynamicDataStore().listNames("invocations/");
        assertEquals(1, subDynamicIds.size());


        MockInvocation theInvocation = (MockInvocation) DataStore.getInstance().getDynamicDataStore().get("invocations/thekey");
        assertNotNull(theInvocation);
        assertEquals(3, theInvocation.arguments.size());

    }

}
