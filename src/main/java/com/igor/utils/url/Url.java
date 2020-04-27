package com.igor.utils.url;

import com.igor.utils.parser.PropertyLoader;
import com.sun.javafx.binding.StringFormatter;

import static com.igor.utils.constant.Constants.DEFAULT_DNS_STR;
import static com.igor.utils.constant.Constants.URL_PROPERTIES_NAME;

public class Url {
    public Protocol protocol;
    public String dns;
    public String urn;

    public Url() {
        protocol = Protocol.HTTP;
        dns = PropertyLoader.getValue(URL_PROPERTIES_NAME, DEFAULT_DNS_STR);
        urn = "";
    }

    public Url(Protocol protocol, String dns, String urn) {
        this.protocol = protocol;
        this.dns = dns;
        this.urn = urn;
    }

    public void setProtocol(Protocol protocol) {
        this.protocol = protocol;
    }

    public void setDns(String dns) {
        this.dns = dns;
    }

    public void setUrn(String urn) {
        this.urn = urn;
    }

    public String getUrl() {
        return StringFormatter.format("%s://%s/%s", protocol.getValue(), dns, urn).getValue();
    }
}
