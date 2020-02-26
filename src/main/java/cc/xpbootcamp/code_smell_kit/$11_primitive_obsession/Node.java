package cc.xpbootcamp.code_smell_kit.$11_primitive_obsession;

import java.util.HashMap;
import java.util.Map;

public class Node {
    private Map<String, Object> params = new HashMap<>();

    public Map<String, Object> getParams() {
        return params;
    }

}
