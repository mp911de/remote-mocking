package de.paluch.tdi.remotemocking.web.ws;

import com.amazon.webservices.awsecommerceservice._2011_08_01.*;
import de.paluch.tdi.remotemocking.ejb.response.MockResponseFactory;
import de.paluch.tdi.remotemocking.ejb.verification.MockInvocationRecorder;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.Holder;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;
import java.util.List;

/**
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a>
 */
@WebService(name = "AWSECommerceServicePortType", targetNamespace = "http://webservices.amazon.com/AWSECommerceService/2011-08-01", portName = "AWSECommerceServicePort", serviceName = "AWSECommerceService")
public class AwsItemSearchWs implements AWSECommerceServicePortType {

    /**
     * Example Implementation for a Web-Service mock.
     *
     * @param marketplaceDomain
     * @param awsAccessKeyId
     * @param associateTag
     * @param xmlEscaping
     * @param validate
     * @param shared
     * @param request
     * @param operationRequest
     * @param items
     */
    @Override
    @WebMethod(operationName = "ItemSearch", action = "http://soap.amazon.com/ItemSearch")
    @RequestWrapper(localName = "ItemSearch", targetNamespace = "http://webservices.amazon.com/AWSECommerceService/2011-08-01", className = "com.amazon.webservices.awsecommerceservice._2011_08_01.ItemSearch")
    @ResponseWrapper(localName = "ItemSearchResponse", targetNamespace = "http://webservices.amazon.com/AWSECommerceService/2011-08-01", className = "com.amazon.webservices.awsecommerceservice._2011_08_01.ItemSearchResponse")
    public void itemSearch(
            @WebParam(name = "MarketplaceDomain", targetNamespace = "http://webservices.amazon.com/AWSECommerceService/2011-08-01") String marketplaceDomain,
            @WebParam(name = "AWSAccessKeyId", targetNamespace = "http://webservices.amazon.com/AWSECommerceService/2011-08-01") String awsAccessKeyId,
            @WebParam(name = "AssociateTag", targetNamespace = "http://webservices.amazon.com/AWSECommerceService/2011-08-01") String associateTag,
            @WebParam(name = "XMLEscaping", targetNamespace = "http://webservices.amazon.com/AWSECommerceService/2011-08-01") String xmlEscaping,
            @WebParam(name = "Validate", targetNamespace = "http://webservices.amazon.com/AWSECommerceService/2011-08-01") String validate,
            @WebParam(name = "Shared", targetNamespace = "http://webservices.amazon.com/AWSECommerceService/2011-08-01") ItemSearchRequest shared,
            @WebParam(name = "Request", targetNamespace = "http://webservices.amazon.com/AWSECommerceService/2011-08-01") List<ItemSearchRequest> request,
            @WebParam(name = "OperationRequest", targetNamespace = "http://webservices.amazon.com/AWSECommerceService/2011-08-01", mode = WebParam.Mode.OUT) Holder<OperationRequest> operationRequest,
            @WebParam(name = "Items", targetNamespace = "http://webservices.amazon.com/AWSECommerceService/2011-08-01", mode = WebParam.Mode.OUT) Holder<List<Items>> items) {

        MockInvocationRecorder.recordInvocation("AwsItemSearchWs/itemSearch/" + awsAccessKeyId, marketplaceDomain, awsAccessKeyId, associateTag, xmlEscaping, validate, shared, request);
        AwsItemSearchResponse response = MockResponseFactory.getResponse("AwsItemSearchWs/itemSearch/" + awsAccessKeyId, AwsItemSearchResponse.class);
        operationRequest.value = response.itemSearchResponse.getOperationRequest();
        items.value = response.itemSearchResponse.getItems();

    }

    @Override
    @WebMethod(operationName = "ItemLookup", action = "http://soap.amazon.com/ItemLookup")
    @RequestWrapper(localName = "ItemLookup", targetNamespace = "http://webservices.amazon.com/AWSECommerceService/2011-08-01", className = "com.amazon.webservices.awsecommerceservice._2011_08_01.ItemLookup")
    @ResponseWrapper(localName = "ItemLookupResponse", targetNamespace = "http://webservices.amazon.com/AWSECommerceService/2011-08-01", className = "com.amazon.webservices.awsecommerceservice._2011_08_01.ItemLookupResponse")
    public void itemLookup(
            @WebParam(name = "MarketplaceDomain", targetNamespace = "http://webservices.amazon.com/AWSECommerceService/2011-08-01") String marketplaceDomain,
            @WebParam(name = "AWSAccessKeyId", targetNamespace = "http://webservices.amazon.com/AWSECommerceService/2011-08-01") String awsAccessKeyId,
            @WebParam(name = "AssociateTag", targetNamespace = "http://webservices.amazon.com/AWSECommerceService/2011-08-01") String associateTag,
            @WebParam(name = "Validate", targetNamespace = "http://webservices.amazon.com/AWSECommerceService/2011-08-01") String validate,
            @WebParam(name = "XMLEscaping", targetNamespace = "http://webservices.amazon.com/AWSECommerceService/2011-08-01") String xmlEscaping,
            @WebParam(name = "Shared", targetNamespace = "http://webservices.amazon.com/AWSECommerceService/2011-08-01") ItemLookupRequest shared,
            @WebParam(name = "Request", targetNamespace = "http://webservices.amazon.com/AWSECommerceService/2011-08-01") List<ItemLookupRequest> request,
            @WebParam(name = "OperationRequest", targetNamespace = "http://webservices.amazon.com/AWSECommerceService/2011-08-01", mode = WebParam.Mode.OUT) Holder<OperationRequest> operationRequest,
            @WebParam(name = "Items", targetNamespace = "http://webservices.amazon.com/AWSECommerceService/2011-08-01", mode = WebParam.Mode.OUT) Holder<List<Items>> items) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    @WebMethod(operationName = "BrowseNodeLookup", action = "http://soap.amazon.com/BrowseNodeLookup")
    @RequestWrapper(localName = "BrowseNodeLookup", targetNamespace = "http://webservices.amazon.com/AWSECommerceService/2011-08-01", className = "com.amazon.webservices.awsecommerceservice._2011_08_01.BrowseNodeLookup")
    @ResponseWrapper(localName = "BrowseNodeLookupResponse", targetNamespace = "http://webservices.amazon.com/AWSECommerceService/2011-08-01", className = "com.amazon.webservices.awsecommerceservice._2011_08_01.BrowseNodeLookupResponse")
    public void browseNodeLookup(
            @WebParam(name = "MarketplaceDomain", targetNamespace = "http://webservices.amazon.com/AWSECommerceService/2011-08-01") String marketplaceDomain,
            @WebParam(name = "AWSAccessKeyId", targetNamespace = "http://webservices.amazon.com/AWSECommerceService/2011-08-01") String awsAccessKeyId,
            @WebParam(name = "AssociateTag", targetNamespace = "http://webservices.amazon.com/AWSECommerceService/2011-08-01") String associateTag,
            @WebParam(name = "Validate", targetNamespace = "http://webservices.amazon.com/AWSECommerceService/2011-08-01") String validate,
            @WebParam(name = "XMLEscaping", targetNamespace = "http://webservices.amazon.com/AWSECommerceService/2011-08-01") String xmlEscaping,
            @WebParam(name = "Shared", targetNamespace = "http://webservices.amazon.com/AWSECommerceService/2011-08-01") BrowseNodeLookupRequest shared,
            @WebParam(name = "Request", targetNamespace = "http://webservices.amazon.com/AWSECommerceService/2011-08-01") List<BrowseNodeLookupRequest> request,
            @WebParam(name = "OperationRequest", targetNamespace = "http://webservices.amazon.com/AWSECommerceService/2011-08-01", mode = WebParam.Mode.OUT) Holder<OperationRequest> operationRequest,
            @WebParam(name = "BrowseNodes", targetNamespace = "http://webservices.amazon.com/AWSECommerceService/2011-08-01", mode = WebParam.Mode.OUT) Holder<List<BrowseNodes>> browseNodes) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    @WebMethod(operationName = "SimilarityLookup", action = "http://soap.amazon.com/SimilarityLookup")
    @RequestWrapper(localName = "SimilarityLookup", targetNamespace = "http://webservices.amazon.com/AWSECommerceService/2011-08-01", className = "com.amazon.webservices.awsecommerceservice._2011_08_01.SimilarityLookup")
    @ResponseWrapper(localName = "SimilarityLookupResponse", targetNamespace = "http://webservices.amazon.com/AWSECommerceService/2011-08-01", className = "com.amazon.webservices.awsecommerceservice._2011_08_01.SimilarityLookupResponse")
    public void similarityLookup(
            @WebParam(name = "MarketplaceDomain", targetNamespace = "http://webservices.amazon.com/AWSECommerceService/2011-08-01") String marketplaceDomain,
            @WebParam(name = "AWSAccessKeyId", targetNamespace = "http://webservices.amazon.com/AWSECommerceService/2011-08-01") String awsAccessKeyId,
            @WebParam(name = "AssociateTag", targetNamespace = "http://webservices.amazon.com/AWSECommerceService/2011-08-01") String associateTag,
            @WebParam(name = "Validate", targetNamespace = "http://webservices.amazon.com/AWSECommerceService/2011-08-01") String validate,
            @WebParam(name = "XMLEscaping", targetNamespace = "http://webservices.amazon.com/AWSECommerceService/2011-08-01") String xmlEscaping,
            @WebParam(name = "Shared", targetNamespace = "http://webservices.amazon.com/AWSECommerceService/2011-08-01") SimilarityLookupRequest shared,
            @WebParam(name = "Request", targetNamespace = "http://webservices.amazon.com/AWSECommerceService/2011-08-01") List<SimilarityLookupRequest> request,
            @WebParam(name = "OperationRequest", targetNamespace = "http://webservices.amazon.com/AWSECommerceService/2011-08-01", mode = WebParam.Mode.OUT) Holder<OperationRequest> operationRequest,
            @WebParam(name = "Items", targetNamespace = "http://webservices.amazon.com/AWSECommerceService/2011-08-01", mode = WebParam.Mode.OUT) Holder<List<Items>> items) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    @WebMethod(operationName = "CartGet", action = "http://soap.amazon.com/CartGet")
    @RequestWrapper(localName = "CartGet", targetNamespace = "http://webservices.amazon.com/AWSECommerceService/2011-08-01", className = "com.amazon.webservices.awsecommerceservice._2011_08_01.CartGet")
    @ResponseWrapper(localName = "CartGetResponse", targetNamespace = "http://webservices.amazon.com/AWSECommerceService/2011-08-01", className = "com.amazon.webservices.awsecommerceservice._2011_08_01.CartGetResponse")
    public void cartGet(
            @WebParam(name = "MarketplaceDomain", targetNamespace = "http://webservices.amazon.com/AWSECommerceService/2011-08-01") String marketplaceDomain,
            @WebParam(name = "AWSAccessKeyId", targetNamespace = "http://webservices.amazon.com/AWSECommerceService/2011-08-01") String awsAccessKeyId,
            @WebParam(name = "AssociateTag", targetNamespace = "http://webservices.amazon.com/AWSECommerceService/2011-08-01") String associateTag,
            @WebParam(name = "Validate", targetNamespace = "http://webservices.amazon.com/AWSECommerceService/2011-08-01") String validate,
            @WebParam(name = "XMLEscaping", targetNamespace = "http://webservices.amazon.com/AWSECommerceService/2011-08-01") String xmlEscaping,
            @WebParam(name = "Shared", targetNamespace = "http://webservices.amazon.com/AWSECommerceService/2011-08-01") CartGetRequest shared,
            @WebParam(name = "Request", targetNamespace = "http://webservices.amazon.com/AWSECommerceService/2011-08-01") List<CartGetRequest> request,
            @WebParam(name = "OperationRequest", targetNamespace = "http://webservices.amazon.com/AWSECommerceService/2011-08-01", mode = WebParam.Mode.OUT) Holder<OperationRequest> operationRequest,
            @WebParam(name = "Cart", targetNamespace = "http://webservices.amazon.com/AWSECommerceService/2011-08-01", mode = WebParam.Mode.OUT) Holder<List<Cart>> cart) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    @WebMethod(operationName = "CartCreate", action = "http://soap.amazon.com/CartCreate")
    @RequestWrapper(localName = "CartCreate", targetNamespace = "http://webservices.amazon.com/AWSECommerceService/2011-08-01", className = "com.amazon.webservices.awsecommerceservice._2011_08_01.CartCreate")
    @ResponseWrapper(localName = "CartCreateResponse", targetNamespace = "http://webservices.amazon.com/AWSECommerceService/2011-08-01", className = "com.amazon.webservices.awsecommerceservice._2011_08_01.CartCreateResponse")
    public void cartCreate(
            @WebParam(name = "MarketplaceDomain", targetNamespace = "http://webservices.amazon.com/AWSECommerceService/2011-08-01") String marketplaceDomain,
            @WebParam(name = "AWSAccessKeyId", targetNamespace = "http://webservices.amazon.com/AWSECommerceService/2011-08-01") String awsAccessKeyId,
            @WebParam(name = "AssociateTag", targetNamespace = "http://webservices.amazon.com/AWSECommerceService/2011-08-01") String associateTag,
            @WebParam(name = "Validate", targetNamespace = "http://webservices.amazon.com/AWSECommerceService/2011-08-01") String validate,
            @WebParam(name = "XMLEscaping", targetNamespace = "http://webservices.amazon.com/AWSECommerceService/2011-08-01") String xmlEscaping,
            @WebParam(name = "Shared", targetNamespace = "http://webservices.amazon.com/AWSECommerceService/2011-08-01") CartCreateRequest shared,
            @WebParam(name = "Request", targetNamespace = "http://webservices.amazon.com/AWSECommerceService/2011-08-01") List<CartCreateRequest> request,
            @WebParam(name = "OperationRequest", targetNamespace = "http://webservices.amazon.com/AWSECommerceService/2011-08-01", mode = WebParam.Mode.OUT) Holder<OperationRequest> operationRequest,
            @WebParam(name = "Cart", targetNamespace = "http://webservices.amazon.com/AWSECommerceService/2011-08-01", mode = WebParam.Mode.OUT) Holder<List<Cart>> cart) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    @WebMethod(operationName = "CartAdd", action = "http://soap.amazon.com/CartAdd")
    @RequestWrapper(localName = "CartAdd", targetNamespace = "http://webservices.amazon.com/AWSECommerceService/2011-08-01", className = "com.amazon.webservices.awsecommerceservice._2011_08_01.CartAdd")
    @ResponseWrapper(localName = "CartAddResponse", targetNamespace = "http://webservices.amazon.com/AWSECommerceService/2011-08-01", className = "com.amazon.webservices.awsecommerceservice._2011_08_01.CartAddResponse")
    public void cartAdd(
            @WebParam(name = "MarketplaceDomain", targetNamespace = "http://webservices.amazon.com/AWSECommerceService/2011-08-01") String marketplaceDomain,
            @WebParam(name = "AWSAccessKeyId", targetNamespace = "http://webservices.amazon.com/AWSECommerceService/2011-08-01") String awsAccessKeyId,
            @WebParam(name = "AssociateTag", targetNamespace = "http://webservices.amazon.com/AWSECommerceService/2011-08-01") String associateTag,
            @WebParam(name = "Validate", targetNamespace = "http://webservices.amazon.com/AWSECommerceService/2011-08-01") String validate,
            @WebParam(name = "XMLEscaping", targetNamespace = "http://webservices.amazon.com/AWSECommerceService/2011-08-01") String xmlEscaping,
            @WebParam(name = "Shared", targetNamespace = "http://webservices.amazon.com/AWSECommerceService/2011-08-01") CartAddRequest shared,
            @WebParam(name = "Request", targetNamespace = "http://webservices.amazon.com/AWSECommerceService/2011-08-01") List<CartAddRequest> request,
            @WebParam(name = "OperationRequest", targetNamespace = "http://webservices.amazon.com/AWSECommerceService/2011-08-01", mode = WebParam.Mode.OUT) Holder<OperationRequest> operationRequest,
            @WebParam(name = "Cart", targetNamespace = "http://webservices.amazon.com/AWSECommerceService/2011-08-01", mode = WebParam.Mode.OUT) Holder<List<Cart>> cart) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    @WebMethod(operationName = "CartModify", action = "http://soap.amazon.com/CartModify")
    @RequestWrapper(localName = "CartModify", targetNamespace = "http://webservices.amazon.com/AWSECommerceService/2011-08-01", className = "com.amazon.webservices.awsecommerceservice._2011_08_01.CartModify")
    @ResponseWrapper(localName = "CartModifyResponse", targetNamespace = "http://webservices.amazon.com/AWSECommerceService/2011-08-01", className = "com.amazon.webservices.awsecommerceservice._2011_08_01.CartModifyResponse")
    public void cartModify(
            @WebParam(name = "MarketplaceDomain", targetNamespace = "http://webservices.amazon.com/AWSECommerceService/2011-08-01") String marketplaceDomain,
            @WebParam(name = "AWSAccessKeyId", targetNamespace = "http://webservices.amazon.com/AWSECommerceService/2011-08-01") String awsAccessKeyId,
            @WebParam(name = "AssociateTag", targetNamespace = "http://webservices.amazon.com/AWSECommerceService/2011-08-01") String associateTag,
            @WebParam(name = "Validate", targetNamespace = "http://webservices.amazon.com/AWSECommerceService/2011-08-01") String validate,
            @WebParam(name = "XMLEscaping", targetNamespace = "http://webservices.amazon.com/AWSECommerceService/2011-08-01") String xmlEscaping,
            @WebParam(name = "Shared", targetNamespace = "http://webservices.amazon.com/AWSECommerceService/2011-08-01") CartModifyRequest shared,
            @WebParam(name = "Request", targetNamespace = "http://webservices.amazon.com/AWSECommerceService/2011-08-01") List<CartModifyRequest> request,
            @WebParam(name = "OperationRequest", targetNamespace = "http://webservices.amazon.com/AWSECommerceService/2011-08-01", mode = WebParam.Mode.OUT) Holder<OperationRequest> operationRequest,
            @WebParam(name = "Cart", targetNamespace = "http://webservices.amazon.com/AWSECommerceService/2011-08-01", mode = WebParam.Mode.OUT) Holder<List<Cart>> cart) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    @WebMethod(operationName = "CartClear", action = "http://soap.amazon.com/CartClear")
    @RequestWrapper(localName = "CartClear", targetNamespace = "http://webservices.amazon.com/AWSECommerceService/2011-08-01", className = "com.amazon.webservices.awsecommerceservice._2011_08_01.CartClear")
    @ResponseWrapper(localName = "CartClearResponse", targetNamespace = "http://webservices.amazon.com/AWSECommerceService/2011-08-01", className = "com.amazon.webservices.awsecommerceservice._2011_08_01.CartClearResponse")
    public void cartClear(
            @WebParam(name = "MarketplaceDomain", targetNamespace = "http://webservices.amazon.com/AWSECommerceService/2011-08-01") String marketplaceDomain,
            @WebParam(name = "AWSAccessKeyId", targetNamespace = "http://webservices.amazon.com/AWSECommerceService/2011-08-01") String awsAccessKeyId,
            @WebParam(name = "AssociateTag", targetNamespace = "http://webservices.amazon.com/AWSECommerceService/2011-08-01") String associateTag,
            @WebParam(name = "Validate", targetNamespace = "http://webservices.amazon.com/AWSECommerceService/2011-08-01") String validate,
            @WebParam(name = "XMLEscaping", targetNamespace = "http://webservices.amazon.com/AWSECommerceService/2011-08-01") String xmlEscaping,
            @WebParam(name = "Shared", targetNamespace = "http://webservices.amazon.com/AWSECommerceService/2011-08-01") CartClearRequest shared,
            @WebParam(name = "Request", targetNamespace = "http://webservices.amazon.com/AWSECommerceService/2011-08-01") List<CartClearRequest> request,
            @WebParam(name = "OperationRequest", targetNamespace = "http://webservices.amazon.com/AWSECommerceService/2011-08-01", mode = WebParam.Mode.OUT) Holder<OperationRequest> operationRequest,
            @WebParam(name = "Cart", targetNamespace = "http://webservices.amazon.com/AWSECommerceService/2011-08-01", mode = WebParam.Mode.OUT) Holder<List<Cart>> cart) {
        throw new UnsupportedOperationException("Not implemented");
    }
}
