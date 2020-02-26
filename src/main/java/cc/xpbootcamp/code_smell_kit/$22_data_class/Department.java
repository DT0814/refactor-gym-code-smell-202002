package cc.xpbootcamp.code_smell_kit.$22_data_class;

import lombok.AllArgsConstructor;
import lombok.Data;

//坏味道： 纯数据类。 原本在User类和Corporation类之间还有Department类，但随着业务变化，此类不再被使用。
@Data
@AllArgsConstructor
public class Department {

    private String name;
    private String type;
    private User manager;
    private String code;
}
