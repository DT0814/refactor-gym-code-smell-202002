package cc.xpbootcamp.code_smell_kit.$22_data_class;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class Corporation {
    private static final long serialVersionUID = -1097609251884367614L;

    private String organizationCode;
    private String corporationName;
    private String taxNumber;
    private String legalPerson;
    private String linkman;
    private String phone;
    private String registeredAddress;
    private String businessLicense;
    private BigDecimal deposit;
    private BigDecimal balance;

    public boolean isLegal(){
        String regex = "/^\\d{15}$/";
        return businessLicense.matches(regex);
    }

    public boolean canRentIncubator() {
        return deposit.compareTo(BigDecimal.ZERO) > 0 && balance.compareTo(BigDecimal.ZERO) > 0;
    }


}
