package com.interview.model;

/**
 * Created by robert.j.ssemmanda on 29/07/2017.
 * This class models the message we expect to consume via rest api.
 * Also, it would be the same message stored in the embedded db for analysis later
 */

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class MessageModel {

    /*
    * Variable declarations
    * */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String userId, currencyFrom, currencyTo, originatingCountry;
    private BigDecimal amountSell,amountBuy, rate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yy HH:mm:ss", locale = "en_GB")
    private Date timePlaced;

    /*
    * Constructors
    * */
    //required by JPA
    public MessageModel(){

    }

    public MessageModel(String userId, String currencyFrom, String currencyTo, Date timePlaced, String originatingCountry, BigDecimal amountSell, BigDecimal amountBuy, BigDecimal rate) {
        this.userId = userId;
        this.currencyFrom = currencyFrom;
        this.currencyTo = currencyTo;
        this.timePlaced = timePlaced;
        this.originatingCountry = originatingCountry;
        this.amountSell = amountSell;
        this.amountBuy = amountBuy;
        this.rate = rate;
    }
    /*
    * Getter and Setter messages
    * */

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCurrencyFrom() {
        return currencyFrom;
    }

    public void setCurrencyFrom(String currencyFrom) {
        this.currencyFrom = currencyFrom;
    }

    public String getCurrencyTo() {
        return currencyTo;
    }

    public void setCurrencyTo(String currencyTo) {
        this.currencyTo = currencyTo;
    }

    public Date getTimePlaced() {
        return timePlaced;
    }

    public void setTimePlaced(Date timePlaced) {
        this.timePlaced = timePlaced;
    }

    public String getOriginatingCountry() {
        return originatingCountry;
    }

    public void setOriginatingCountry(String originatingCountry) {
        this.originatingCountry = originatingCountry;
    }

    public BigDecimal getAmountSell() {
        return amountSell;
    }

    public void setAmountSell(BigDecimal amountSell) {
        this.amountSell = amountSell;
    }

    public BigDecimal getAmountBuy() {
        return amountBuy;
    }

    public void setAmountBuy(BigDecimal amountBuy) {
        this.amountBuy = amountBuy;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    /*
    * Other util methods
    * */

    @Override
    public String toString() {
        return "MessageModel{" +
                "id='" + id + '\'' +
                "userId='" + userId + '\'' +
                ", currencyFrom='" + currencyFrom + '\'' +
                ", currencyTo='" + currencyTo + '\'' +
                ", originatingCountry='" + originatingCountry + '\'' +
                ", amountSell=" + amountSell +
                ", amountBuy=" + amountBuy +
                ", rate=" + rate +
                ", timePlaced=" + timePlaced +
                '}';
    }
}
