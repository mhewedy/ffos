package model;

import storage.Storage;
import util.Util;

/**
 * Created by mhewedy on 07/05/14.
 */
public class Customer {

    private String name;
    private String phoneNumber;
    private String address;

    public Customer(String fName, String lName, String phoneNumber, String address) {
        this.name = ((fName == null ? "" : fName) + " " + (lName == null ? "" : lName)).trim();
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public static Customer findByPhoneNumber(String phoneNumber) {
        return Storage.get().searchCustomerByPhoneNumber(phoneNumber);
    }

    public void save() throws InvalidObjectException {
        validate();
        Storage.get().saveCustomer(this);
    }

    private void validate() throws InvalidObjectException {
        if (Util.isEmpty(name)) {
            throw new InvalidObjectException("name");
        }
        if (Util.isEmpty(phoneNumber)) {
            throw new InvalidObjectException("phoneNumber");
        }
        if (Util.isEmpty(address)) {
            throw new InvalidObjectException("address");
        }
    }

    public String getName() {
        return this.name;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public String getAddress() {
        return this.address;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        if (phoneNumber != null ? !phoneNumber.equals(customer.phoneNumber) : customer.phoneNumber != null)
            return false;

        return true;
    }

    public int hashCode() {
        return phoneNumber.hashCode();
    }

    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
