package tech.woloszyn.Elotech.domain.usecase.helper;

import tech.woloszyn.Elotech.domain.entities.Pessoa;
import tech.woloszyn.Elotech.exceptions.PropriedadeNaoInformadaException;

import java.lang.reflect.Method;
import java.util.Objects;

public class PropertiesHelper {

    public static boolean haveAllProperties(Object obj) throws Exception {
        Method[] methods = obj.getClass().getMethods();
        for (Method method : methods) {
            if(method.getName().startsWith("get") && !method.getName().equals("getId")) {
                Object returnObj = method.invoke(obj);
                if(Objects.isNull(returnObj) || returnObj.equals("")) {
                    throw new PropriedadeNaoInformadaException(method.getName().substring(3));
                }
            }
        }
        return true;
    }

}
