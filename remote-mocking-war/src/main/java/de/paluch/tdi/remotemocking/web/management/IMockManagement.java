package de.paluch.tdi.remotemocking.web.management;

import de.paluch.tdi.remotemocking.web.management.model.DynamicMockDataList;
import de.paluch.tdi.remotemocking.web.management.model.DynamicMockDataResult;
import de.paluch.tdi.remotemocking.web.management.model.MockInvocationData;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.ws.rs.FormParam;
import javax.ws.rs.PathParam;
import java.util.List;

/**
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a>
 */
@WebService
public interface IMockManagement {

    /**
     * Put Mock-Data to Cache.
     *
     * @param id
     * @param data
     */
    public void put(@WebParam(name = "id") @PathParam("id") String id,
                    @WebParam(name = "data") @FormParam("data") String data);

    /**
     * Retrieve Mock-Data from Cache.
     *
     * @param id
     * @return DynamicMockDataResult
     */
    public DynamicMockDataResult get(@WebParam(name = "id") @PathParam("id") String id);

    /**
     * List Mock-Data in Cache.
     *
     * @return DynamicMockDataList
     */
    public DynamicMockDataList list();

    /**
     * Remove Mock-Data from Cache.
     *
     * @param id
     */
    public void delete(@WebParam(name = "id") @PathParam("id") String id);

    /**
     * Remove recorded invocation data from the cache.
     *
     * @param id
     */
    public void deleteInvocationData(@WebParam(name = "id") @PathParam("id") String id);

    /**
     * Remove all recorded invocation data from the cache.
     */
    public void deleteAllInvocationData();

    /**
     * Delete all from Cache.
     */
    public void deleteAll();

    /**
     * Retrieves the Mock invocation data.
     *
     * @param id
     * @return VerificationMockData
     */
    public MockInvocationData getInvocationData(@WebParam(name = "id") String id);

    /**
     * Retrieve Mock invocations.
     *
     * @return List<MockInvocationData>
     */
    public List<MockInvocationData> listInvocations();
}
