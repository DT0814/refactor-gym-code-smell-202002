package cc.xpbootcamp.code_smell_kit.$22_data_class;

import java.util.List;

public class RentPlatform { //保温箱租赁平台

    public void initializeIncubatorsForUser(User user, List<Incubator> incubators){
        user.setIncubators(incubators);
    }

    public void rentAnIncubator(User user, Incubator anIncubator){
        user.getIncubators().add(anIncubator);
    }

    public void restoreAnIncubator(User user, Incubator incubator){
        user.getIncubators().remove(incubator);
    }
}
