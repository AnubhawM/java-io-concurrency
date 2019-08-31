package com.cooksys.ftd.ticker.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public enum QuoteField {

    OPEN("open"),
    CLOSE("close"),
    HIGH("high"),
    LOW("low"),
    LATEST_PRICE("latestPrice"),
    CHANGE("change"),
    CHANGE_PERCENT("changePercent");

    @XmlValue
    private String type;

    private QuoteField(String type) {
        this.type = type;
    }

    public String getField() {
        return this.type;
    }

}

