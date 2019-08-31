package com.cooksys.ftd.ticker.dto;

import javax.xml.bind.annotation.*;
import java.util.Set;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class QuoteRequest {

    @XmlElementWrapper
    @XmlElement(name = "field")
    private Set<QuoteField> fields;

    @XmlElementWrapper
    @XmlElement(name = "symbol")
    private Set<String> symbols;

    public QuoteRequest() {}

    public QuoteRequest(Set<QuoteField> fields, Set<String> symbols) {
        this.symbols = symbols;
        this.fields = fields;
    }

    public Set<String> getSymbols() {
        return symbols;
    }

    public void setSymbols(Set<String> symbols) {
        this.symbols = symbols;
    }

    public Set<QuoteField> getFields() {
        return fields;
    }

    public void setFields(Set<QuoteField> fields) {
        this.fields = fields;
    }
}

