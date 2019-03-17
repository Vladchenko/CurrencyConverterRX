package com.example.vladislav.currencyconverter.datasource;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name="ValCurs")
public class Currencies {

    @Attribute(name="name", required=false)
    String name;

    @Attribute(name="Date", required=false)
    String date;

    @Element(name="ValCurs", required=false)
    Currency valute;

    public String getName() {return this.name;}
    public void setName(String value) {this.name = value;}

    public String getDate() {return this.date;}
    public void setDate(String value) {this.date = value;}

    public Currency getValute() {return this.valute;}
    public void setValute(Currency value) {this.valute = value;}

    @Element(name="Valute")
    public static class Currency {

        @Element(name="CharCode", required=false)
        String charCode;

        @Element(name="Value", required=false)
        String value;

        @Attribute(name="ID", required=false)
        String iD;

        @Element(name="Nominal", required=false)
        String nominal;

        @Element(name="NumCode", required=false)
        String numCode;

        @Element(name="Name", required=false)
        String name;

        public String getCharCode() {return this.charCode;}
        public void setCharCode(String value) {this.charCode = value;}

        public String getValue() {return this.value;}
        public void setValue(String value) {this.value = value;}

        public String getID() {return this.iD;}
        public void setID(String value) {this.iD = value;}

        public String getNominal() {return this.nominal;}
        public void setNominal(String value) {this.nominal = value;}

        public String getNumCode() {return this.numCode;}
        public void setNumCode(String value) {this.numCode = value;}

        public String getName() {return this.name;}
        public void setName(String value) {this.name = value;}

    }

}