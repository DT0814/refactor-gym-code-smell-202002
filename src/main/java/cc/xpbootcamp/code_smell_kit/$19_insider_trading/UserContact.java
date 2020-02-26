public class UserContact {
  private String phoneNumber;
  private String streetNumber;
  private String streetName;
  private double totalPrice;

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

  public String buildContactHeader(User user) {
      return "Dear " + user.getUserName() + "( " + streetNumber + ", " + streetName + " )";
  }

  public String buildContactFooter(UserContact userContact) {
      return "Total price: " + userContact.getTotalPrice() + "\n"
              + "Your name: " + user.getUserName() + "\n"
              + "Your phone number: " + phoneNumber + "\n"
              + "Your street number: " + streetNumber + "\n"
              + "Your street name: " + streetName + "\n";
  }
}
