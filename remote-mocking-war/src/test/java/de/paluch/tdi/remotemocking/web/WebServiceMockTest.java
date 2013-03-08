package de.paluch.tdi.remotemocking.web;

import com.amazon.webservices.awsecommerceservice._2011_08_01.AWSECommerceServicePortType;
import com.amazon.webservices.awsecommerceservice._2011_08_01.Items;
import com.amazon.webservices.awsecommerceservice._2011_08_01.OperationRequest;
import de.paluch.tdi.remotemocking.web.management.MockManagement;
import de.paluch.tdi.remotemocking.web.management.model.MockInvocationData;
import de.paluch.tdi.remotemocking.web.ws.AwsItemSearchWs;
import org.apache.commons.io.FileUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.xml.namespace.QName;
import javax.xml.ws.Endpoint;
import javax.xml.ws.Holder;
import javax.xml.ws.Service;
import java.io.File;
import java.net.URL;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/**
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a>
 */
public class WebServiceMockTest {

    private static int port;
    private static Endpoint endpoint;

    /**
     * You would call this guy as well by SOAP/REST on a real integration environment.
     * Now we're within a local integration test using JUnit, so tunneling this one using SOAP is a bit...guh.
     */
    private MockManagement mockManagement = new MockManagement();

    private String invocationKey = "AwsItemSearchWs/itemSearch/myId";

    @BeforeClass
    public static void beforeClass() throws Exception {
        port = RemoteUtil.findFreePort();
        endpoint = Endpoint.publish("http://localhost:" + port + "/AWS", new AwsItemSearchWs());
    }

    /**
     * Impl as one method since dependencies within test/test-order are ugly.
     *
     * @throws Exception
     */
    @Test
    public void test() throws Exception {
        setupMockData();
        callMock();
        verifyInvocation();
    }

    /**
     * Setup mock data.
     *
     * @throws Exception
     */
    public void setupMockData() throws Exception {
        List<MockInvocationData> invocations = mockManagement.listInvocations();
        assertEquals(0, invocations.size());

        URL url = getClass().getResource("/mock-response.xml");

        String contents = FileUtils.readFileToString(new File(url.toURI()));


        mockManagement.put(invocationKey, contents);
    }


    /**
     * Here comes your remote service call. This time explicit to the mock.
     *
     * @throws Exception
     */

    public void callMock() throws Exception {

        URL wsdlUrl = new URL("http://localhost:" + port + "/AWS?wsdl");
        QName serviceName = new QName("http://webservices.amazon.com/AWSECommerceService/2011-08-01", "AWSECommerceService");
        Service service = Service.create(wsdlUrl, serviceName);

        AWSECommerceServicePortType port = service.getPort(AWSECommerceServicePortType.class);

        Holder<OperationRequest> operationRequest = new Holder<OperationRequest>();
        Holder<List<Items>> items = new Holder<List<Items>>();
        port.itemSearch("aDomain", "myId", null, null, null, null, null, operationRequest, items);

        assertNotNull(operationRequest.value);
        assertEquals("theRequestId", operationRequest.value.getRequestId());
    }

    /**
     * Perform verifications on the invocation.
     *
     * @throws Exception
     */
    public void verifyInvocation() throws Exception {
        MockInvocationData invocation = mockManagement.getInvocationData(invocationKey);
        assertNotNull(invocation);

        assertEquals(7, invocation.arguments.size());
        assertEquals("<string>myId</string>", invocation.arguments.get(1));
    }
}