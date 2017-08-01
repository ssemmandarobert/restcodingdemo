package com.interview.model;


/**
 * Created by robert.j.ssemmanda on 31/07/2017.
 * This class is used as a pojo to wrap the aggregate query results
 * from the DB
 */
public class MessageStatics {

    /*
    * Variable declarations
    * */
    //1st group name (i.e group by )
    private String name;

    //int stat (i.e count)
    private Long stat;

    //second group name
    private String secondName;

    //aggregation of double values such as rates
    private Double currencyAmounts;


    /*
    * Constructors
    * */

    //default required by JPA
    public MessageStatics(){

    }

    //used to create an object with only one group and value
    public MessageStatics(String name, Long stat) {
        this.name = name;
        this.stat = stat;
    }

    //used to create an object with a group and semi group and a value (group by a,b)
    public  MessageStatics(String name, String secondName, Long stat){
        this.name = name;
        this.secondName = secondName;
        this.stat = stat;
    }

    //used to create an object with a group and semi group and a decimal value
    public  MessageStatics(String name, String secondName, Double currencyAmounts){
        this.name = name;
        this.secondName = secondName;
        this.currencyAmounts = currencyAmounts;
    }

    /*
    * Getter and Setter messages
    * */

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public Double getCurrencyAmounts() {
        return currencyAmounts;
    }

    public void setCurrencyAmounts(Double currencyAmounts) {
        this.currencyAmounts = currencyAmounts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getStat() {
        return stat;
    }

    public void setStat(Long stat) {
        this.stat = stat;
    }
}
