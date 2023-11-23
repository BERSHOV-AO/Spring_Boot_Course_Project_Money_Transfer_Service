package ru.netology.moneytransferservice.model;

import java.util.Objects;

public class Amount {

    private Integer value;
    private String type;

    public Amount() {
    }

    public Amount(int value, String type) {
        this.value = value;
        this.type = type;
    }

    public Integer getValue() {
        return value;
    }

    public String getType() {
        return type;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public void setType(String type) {
        this.type = type;
    }

//    @Override
//    public String toString() {
//        return "Amount{" +
//                "value=" + value +
//                ", type='" + type + '\'' +
//                '}';
//    }


    @Override
    public String toString() {
        return Integer.toString(value) + " " + type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Amount amount = (Amount) o;
        return Objects.equals(value, amount.value) && Objects.equals(type, amount.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, type);
    }

    //-------------------------------------------
//    @Override
//    public int hashCode() {
//        return super.hashCode();
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//        return super.equals(obj);
//    }
    //--------------------------------------------
}
