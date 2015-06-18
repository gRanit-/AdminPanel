package itdccs.loginmanager.web.adminpanel.services;


import itdccs.loginmanager.web.adminpanel.common.Command;
import itdccs.loginmanager.web.adminpanel.common.ViewObjectType;
import itdccs.loginmanager.web.adminpanel.common.ViewObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Map;


/**
 * Created by mc109881 on 2015-06-11.
 */
@Service
public class ViewObjectBuilder implements Serializable {
    private static final long serialVersionUID = 9051426275753100087L;
    Logger logger = Logger.getLogger(this.getClass());
    // private static ViewObjectBuilder instance;
    private ViewObject viewObject;

    @Autowired
    private ObjectRepositories objectRepositories;


    public ViewObjectBuilder() {
    }

  /*  public static ViewObjectBuilder getInstance() {
        if (instance == null)
            instance = new ViewObjectBuilder();

        return instance;
    }*/

    /***
     * <b>KAZDY OBIEKT MUSI MIEC DOMYSLNY KONSTUKTOR!</b>
     * <br>
     *
     * @param viewObject
     * @return
     */
    public Object toOriginObject(ViewObject viewObject) {
        Class<?> originClass = viewObject.getViewObjectType().getOriginClass();
        Object object = null;
        try {
            Constructor constructor = originClass.getConstructor(null);
            object = constructor.newInstance(null);

            for (Map.Entry entry : viewObject.getFieldNameValueMap().entrySet()) {
                Field field = originClass.getDeclaredField((String) entry.getKey());
                if (field.isAccessible()) {
                    field.set(object, entry.getValue());
                } else {
                    field.setAccessible(true);
                    field.set(object, entry.getValue());
                    field.setAccessible(false);
                }
            }
        } catch (Throwable t) {
            logger.error(t);
        }
        return object;
    }


    public ViewObjectBuilder fromObject(Object object) {
        Class aClass = object.getClass();
        // String className = aClass.toString();

        ViewObjectType viewObjectType = objectRepositories.getViewObjectType(aClass);

        if (viewObjectType == null) {
            viewObjectType = new ViewObjectType();
            viewObjectType.setOriginClass(aClass);
            objectRepositories.putViewObjectType(aClass, viewObjectType);
            objectRepositories.putViewObjectType(new Long(viewObjectType.getThisObjectTypeID()), viewObjectType);
        } else viewObjectType = new ViewObjectType(viewObjectType);


        viewObject = new ViewObject(object, viewObjectType);
        //viewObject=new ViewObject(object,);

        return this;
    }

    public ViewObjectBuilder withGetAllObjectsOfThisType(Command getAll) {
        return this;
    }

    public ViewObjectBuilder withAddCommand(Command addCommand) {
        return this;
    }

    public ViewObjectBuilder withDeleteCommand(Command deleteCommand) {
        return this;
    }

    public ViewObjectBuilder withEditCommand(Command editCommand) {
        return this;
    }

    public ViewObjectBuilder withAdditionalCommands(Map<String, Command> additionalCommands) {
        return this;
    }

    public ViewObject build() {
        return viewObject;
    }

    public ViewObjectType getTypeByID(long id) {
        return objectRepositories.getViewObjectType(new Long(id));
    }

    public ViewObjectType getTypeByClass(Class aClass) {
        return objectRepositories.getViewObjectType(aClass);
    }


}
