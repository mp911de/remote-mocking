package de.paluch.tdi.remotemocking.common;

import com.sun.org.apache.xml.internal.utils.LocaleUtility;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.Locale;

/**
 * Adapter for Locale-Datatype.
 *
 * @author <a href="mailto:mpaluch@paluch.biz">Mark Paluch</a>
 */
public class JaxbLocaleAdapter extends XmlAdapter<String, Locale> {

    @Override
    public Locale unmarshal(String value) throws Exception {
        return LocaleUtility.langToLocale(value);
    }

    @Override
    public String marshal(Locale value) throws Exception {
        return value.toString();
    }
}
