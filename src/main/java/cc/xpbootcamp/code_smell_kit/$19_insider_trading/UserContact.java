package cc.xpbootcamp.code_smell_kit.$19_insider_trading;

import java.util.List;

public class UserContact {
    private String phoneNumber;
    private String streetNumber;
    private String streetName;
    private double totalPrice;
    private User user;

    public UserContact(String phoneNumber, String streetNumber, String streetName, double totalPrice) {
        this.phoneNumber = phoneNumber;
        this.streetNumber = streetNumber;
        this.streetName = streetName;
        this.totalPrice = totalPrice;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public String getStreetName() {
        return streetName;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String buildContactHeader() {
        return "Dear " + user.getUserName() + "( " + streetNumber + ", " + streetName + " )";
    }

    public String buildContactFooter() {
        return "Total price: " + totalPrice + "\n"
                + "Your name: " + user.getUserName() + "\n"
                + "Your phone number: " + phoneNumber + "\n"
                + "Your street number: " + streetNumber + "\n"
                + "Your street name: " + streetName + "\n";
    }

}
