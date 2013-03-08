package de.paluch.tdi.remotemocking.web.management;

import de.paluch.tdi.remotemocking.ejb.datastore.DataStore;
import de.paluch.tdi.remotemocking.ejb.datastore.StaticDataStoreObject;
import de.paluch.tdi.remotemocking.ejb.verification.MockInvocation;
import de.paluch.tdi.remotemocking.web.management.model.DynamicMockData;
import de.paluch.tdi.remotemocking.web.management.model.DynamicMockDataList;
import de.paluch.tdi.remotemocking.web.management.model.DynamicMockDataResult;
import de.paluch.tdi.remotemocking.web.management.model.MockInvocationData;
import org.jboss.resteasy.annotations.providers.jaxb.Wrapped;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a>
 */
@WebService(name = "MockManagement", serviceName = "MockManagementService", portName = "MockManagementPort")
@Path("/mock-data")
public class MockManagement implements IMockManagement {

    /**
     * {@inheritDoc}
     */
    @Override
    @PUT
    @Path("/{id}")
    @Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public void put(@WebParam(name = "id") @PathParam("id") String id,
                    @WebParam(name = "data") @FormParam("data") String data) {

        DataStore.getInstance().getDynamicDataStore().put(getNodeId(id), new StaticDataStoreObject(data));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_XML)
    public DynamicMockDataResult get(@WebParam(name = "id") @PathParam("id") String id) {

        String nodeId = getNodeId(id);
        StaticDataStoreObject data = (StaticDataStoreObject) DataStore.getInstance().getDynamicDataStore().get(id);

        DynamicMockData result = null;
        if (data != null) {
            result = new DynamicMockData(nodeId, data.getValue());
        }

        DynamicMockDataResult wrapper = new DynamicMockDataResult(result);

        return wrapper;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public DynamicMockDataList list() {

        List<DynamicMockData> result = new ArrayList<DynamicMockData>();
        List<String> names = DataStore.getInstance().getDynamicDataStore().listNames();

        for (String id : names) {

            if (id.startsWith(DataStore.INVOCATIONS_PREFIX)) {
                continue;
            }

            StaticDataStoreObject data = (StaticDataStoreObject) DataStore.getInstance().getDynamicDataStore().get(id);
            if (data != null) {
                String externalId = id;
                result.add(new DynamicMockData(externalId, data.getValue()));
            }
        }

        DynamicMockDataList wrapper = new DynamicMockDataList(result);
        return wrapper;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    @DELETE
    @Path("/{id}")
    public void delete(@WebParam(name = "id") @PathParam("id") String id) {

        DataStore.getInstance().getDynamicDataStore().remove(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @DELETE
    public void deleteAll() {

        DataStore.getInstance().getDynamicDataStore().removeAll();
    }

    @Override
    @GET
    @Path("/invocations/{id}")
    @Produces(MediaType.APPLICATION_XML)
    public MockInvocationData getInvocationData(@WebParam(name = "id") String id) {

        String nodeId = DataStore.INVOCATIONS_PREFIX + id.toLowerCase();
        MockInvocation data = (MockInvocation) DataStore.getInstance().getDynamicDataStore().get(nodeId);

        MockInvocationData result = null;
        if (data != null) {
            result = new MockInvocationData(stripInvocation(id), data.arguments);
        }

        return result;
    }

    @Override
    @GET
    @Path("/invocations/")
    @Produces(MediaType.APPLICATION_XML)
    @Wrapped(element = "invocations")
    public List<MockInvocationData> listInvocations() {

        List<MockInvocationData> result = new ArrayList<MockInvocationData>();

        List<String> ids = DataStore.getInstance().getDynamicDataStore().listNames(DataStore.INVOCATIONS_PREFIX);

        for (String id : ids) {
            MockInvocation data = (MockInvocation) DataStore.getInstance().getDynamicDataStore().get(id);

            if (data != null) {
                result.add(new MockInvocationData(stripInvocation(id), data.arguments));
            }
        }

        return result;
    }


    @Override
    @DELETE
    @Path("/invocations/{id}")
    public void deleteInvocationData(@WebParam(name = "id") @PathParam("id") String id) {

        String nodeId = getNodeId(DataStore.INVOCATIONS_PREFIX + id);
        DataStore.getInstance().getDynamicDataStore().remove(nodeId);
    }

    @Override
    @DELETE
    @Path("/invocations/")
    public void deleteAllInvocationData() {
        DataStore.getInstance().getDynamicDataStore().removeFrom(DataStore.INVOCATIONS_PREFIX);
    }


    private String getNodeId(String id) {
        return id.replace('_', '/').toLowerCase();
    }

    private String stripInvocation(String id) {
        if (id.startsWith(DataStore.INVOCATIONS_PREFIX)) {
            return id.substring(DataStore.INVOCATIONS_PREFIX.length());
        }
        return id;
    }


}
