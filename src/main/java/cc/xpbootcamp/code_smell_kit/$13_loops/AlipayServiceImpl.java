package cc.xpbootcamp.code_smell_kit.$13_loops;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class AlipayServiceImpl {

    private static final Logger LOGGER = LoggerFactory.getLogger(AlipayServiceImpl.class);


    public String notify(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.debug("异步通知");
        Map<String, String[]> requestParams = request.getParameterMap();
        //验签
        Map<String,String> params = new HashMap<>();
        //腐烂点：for循环
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            params.put(name, valueStr);
        }
       //TODO 二次校验
        return "success";
    }

}
