public class User {
  private String userName;
  private String userAddress;

  public User(String userName, String userAddress) {
      this.userName = userName;
      this.userAddress = userAddress;
  }

  public String getUserName() {
      return userName;
  }

  public String getUserAddress() {
      return userAddress;
  }

  // Code smell: Insider trading
  public String buildMessage(UserContact userContact) {
      return "Dear "
              + userName
              + "( " + userContact.getStreetNumber()
              + userContact.getStreetName()
              + " ), your contact has been approved.";
  }

  public void sendMessage(UserContact userContact, MessageController messageController) {
      messageController.sendMessage(buildMessage(userContact), userContact.getPhoneNumber);
  }
}
