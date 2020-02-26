package cc.xpbootcamp.code_smell_kit.$04_long_parameter_list;

public class Incubator {

    private String incubatorCode;
    private Boolean privateProperty = Boolean.FALSE;
    private String lockSerialNumber;
    private String serviceState ;
    private String reservationState ;
    private String qualityState;
    private Boolean inRecycleSite;
    private String bindingPhone;

    public String getIncubatorCode() {
        return incubatorCode;
    }

    public void setIncubatorCode(String incubatorCode) {
        this.incubatorCode = incubatorCode;
    }

    public Boolean getPrivateProperty() {
        return privateProperty;
    }

    public void setPrivateProperty(Boolean privateProperty) {
        this.privateProperty = privateProperty;
    }

    public String getLockSerialNumber() {
        return lockSerialNumber;
    }

    public void setLockSerialNumber(String lockSerialNumber) {
        this.lockSerialNumber = lockSerialNumber;
    }

    public String getServiceState() {
        return serviceState;
    }

    public void setServiceState(String serviceState) {
        this.serviceState = serviceState;
    }

    public String getReservationState() {
        return reservationState;
    }

    public void setReservationState(String reservationState) {
        this.reservationState = reservationState;
    }

    public String getQualityState() {
        return qualityState;
    }

    public void setQualityState(String qualityState) {
        this.qualityState = qualityState;
    }

    public Boolean getInRecycleSite() {
        return inRecycleSite;
    }

    public void setInRecycleSite(Boolean inRecycleSite) {
        this.inRecycleSite = inRecycleSite;
    }

    public String getBindingPhone() {
        return bindingPhone;
    }

    public void setBindingPhone(String bindingPhone) {
        this.bindingPhone = bindingPhone;
    }
}
