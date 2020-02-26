package cc.xpbootcamp.code_smell_kit.$04_long_parameter_list;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class IncubatorOperator {


    //坏味道： 过长参数
    public List<Map<String, Object>> findIncubatorsInRange(double recycleSiteLatitude, double recycleSiteLongitude, double recycleRange, Date recycleDate) {
        IncubatorChecker incubatorChecker = new IncubatorChecker();
        //查找指定位置范围内的保温箱code,经纬度,距离
        List<Map<String, String>> incubatorMainInfoList = incubatorChecker.findIncubatorInRange(recycleSiteLatitude, recycleSiteLongitude, recycleRange, recycleDate);
        //筛选出空闲和不在回收站的保温箱
        List<Map<String, String>> filteredIncubatorMainInfoList =
                incubatorMainInfoList.stream().filter(info -> {
                    Incubator incubator = incubatorChecker.findFirstByIncubatorCode(info.get("code"));
                    return incubator != null && "incubator_available".equals(incubator.getServiceState().trim()) &&
                            (incubator.getInRecycleSite() == null || incubator.getInRecycleSite() == false);
                }).collect(Collectors.toList());
        //返回保温箱和经纬度map
        return filteredIncubatorMainInfoList.stream().map(info -> {
            Map<String, Object> map = new HashMap<>();
            map.put("incubator", incubatorChecker.findFirstByIncubatorCode(info.get("code")));
            map.put("latitude", info.get("latitude"));
            map.put("longitude", info.get("longitude"));
            return map;
        }).collect(Collectors.toList());
    }

}
