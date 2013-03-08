package de.paluch.tdi.remotemocking.web.ws;

import com.amazon.webservices.awsecommerceservice._2011_08_01.ItemSearchResponse;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a>
 */
@XmlRootElement(name = "awsItemSearchResponse")
@XmlAccessorType(XmlAccessType.FIELD)
public class AwsItemSearchResponse {

    @XmlElement(name = "ItemSearchResponse", namespace = "http://webservices.amazon.com/AWSECommerceService/2011-08-01")
    public ItemSearchResponse itemSearchResponse;
}
