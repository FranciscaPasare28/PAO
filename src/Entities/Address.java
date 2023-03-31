package Entities;

public class Address {
    private String country, state, city, street;
    private int zipCode;

    public Address(){}

    public Address(String street, String city, String state, String country, int zipCode) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zipCode = zipCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public  String toString(){
        return "\n->Country: " + this.country
                + "\n->State: " + this.state
                + "\n->Street: " + this.street
                + "\n->Zip Code: " +this.zipCode;
    }
}
