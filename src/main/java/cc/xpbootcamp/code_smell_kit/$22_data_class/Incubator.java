package cc.xpbootcamp.code_smell_kit.$22_data_class;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Incubator { //保温箱

    private String incubatorCode;
    private Boolean privateProperty;
    private String lockSerialNumber;
    private String serviceState ;
}
