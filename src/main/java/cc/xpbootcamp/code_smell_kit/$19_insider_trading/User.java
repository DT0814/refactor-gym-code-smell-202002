package cc.xpbootcamp.code_smell_kit.$19_insider_trading;

import java.util.List;

public class User {
    private String userName;
    private String userAddress;
    private String gender;
    private String age;
    private List<UserContact> userContactList;

    public User(String userName, String userAddress) {
        this.userName = userName;
        this.userAddress = userAddress;
    }

    public List<UserContact> getUserContactList() {
        return userContactList;
    }

    public void setUserContactList(List<UserContact> userContactList) {
        this.userContactList = userContactList;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public String getGender() {
        return gender;
    }

    public String getAge() {
        return age;
    }

    // Code smell: Insider trading
    public String buildMessage(UserContact userContact) {
        return "Dear "
                + userName
                + "( "
                + userContact.getStreetNumber()
                + userContact.getStreetName()
                + " ), your contact has been approved.";
    }

    public void sendMessage(UserContact userContact, MessageController messageController) {
        messageController.sendMessage(buildMessage(userContact), userContact.getPhoneNumber());
    }

}
