package cc.xpbootcamp.code_smell_kit.$22_data_class;

//坏味道： 纯数据类
public class Department {

    private String name;
    private String type;
    private User Manage;
    private String code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public User getManage() {
        return Manage;
    }

    public void setManage(User manage) {
        Manage = manage;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
