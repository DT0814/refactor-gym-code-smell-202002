package cc.xpbootcamp.code_smell_kit.$22_data_class;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class User {

    private static final long serialVersionUID = -2454234427468533820L;

    private String userType;
    private String userName;
    private String password;
    private Corporation corporation;
    private String name;
    private String phone;
    private String gender;
    private String loginState;
    private String loginIp;
    private BigDecimal deposit;
    private BigDecimal balance;

    public boolean isCollector() {
        return "COLLECTOR".equals(userType);
    }

    public boolean canRentIncubator() {
        return deposit.compareTo(BigDecimal.ZERO) > 0 && balance.compareTo(BigDecimal.ZERO) > 0 && corporation.canRentIncubator();
    }


}
