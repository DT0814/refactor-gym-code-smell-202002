package cc.xpbootcamp.code_smell_kit.$22_data_class;

import java.util.List;

//坏味道： User是纯数据类，应为属性"incubators"设置添加、删除方法
public class User {

    private String name;
    private String phone;
    private List<Incubator> incubators;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Incubator> getIncubators() {
        return incubators;
    }

    public void setIncubators(List<Incubator> incubators) {
        this.incubators = incubators;
    }
}
