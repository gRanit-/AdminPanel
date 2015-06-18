package itdccs.loginmanager.web.adminpanel.common;


import org.apache.log4j.Logger;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by mc109881 on 2015-06-11.
 */
public class ViewObjectType implements Serializable {
    Logger logger = Logger.getLogger(this.getClass());
    private static long objectTypeID = 0;
    private long thisObjectTypeID = objectTypeID++; //ID object type jest rozne dla kazdej klasy, >>>>>NIE JEJ INSTANCJI<<<<
    private static final long serialVersionUID = -471444585757334238L;
    private String objectViewName;
    private Class<?> originClass;

    private Command addObject;
    private Command getObject;
    private Command removeObject;
    private Command editObject;

    private Map<String, Command> additionalCommands;


    public ViewObjectType() {

    }

    public ViewObjectType(String className) {
        try {
            Class aClass = Class.forName(className);
            originClass = aClass;
        } catch (Throwable e) {
            logger.error(e.getMessage(), e);
        }


    }

    public ViewObjectType(Class aClass) {
        originClass = aClass;

    }

    public ViewObjectType(ViewObjectType viewObjectType) {
        this.thisObjectTypeID = viewObjectType.thisObjectTypeID;

        this.objectViewName = viewObjectType.objectViewName;
        this.addObject = viewObjectType.addObject;
        this.getObject = viewObjectType.getObject;
        this.removeObject = viewObjectType.removeObject;
        this.editObject = viewObjectType.editObject;
        this.additionalCommands = viewObjectType.additionalCommands;
    }

    public ViewObjectType(String objectViewName, Command addObject, Command getObject, Command removeObject, Command editObject) {
        this.objectViewName = objectViewName;
        this.addObject = addObject;
        this.getObject = getObject;
        this.removeObject = removeObject;
        this.editObject = editObject;
        thisObjectTypeID = objectTypeID++;
    }

    public static long getObjectTypeID() {
        return objectTypeID;
    }

    public long getThisObjectTypeID() {
        return thisObjectTypeID;
    }

    public String getObjectViewName() {
        return objectViewName;
    }

    public void setObjectViewName(String objectViewName) {
        this.objectViewName = objectViewName;
    }

    public Command getAddObject() {
        return addObject;
    }

    public void setAddObject(Command addObject) {
        this.addObject = addObject;
    }

    public Command getGetObject() {
        return getObject;
    }

    public void setGetObject(Command getObject) {
        this.getObject = getObject;
    }

    public Command getRemoveObject() {
        return removeObject;
    }

    public void setRemoveObject(Command removeObject) {
        this.removeObject = removeObject;
    }

    public Command getEditObject() {
        return editObject;
    }

    public void setEditObject(Command editObject) {
        this.editObject = editObject;
    }

    public Map<String, Command> getAdditionalCommands() {
        return additionalCommands;
    }

    public void setAdditionalCommands(Map<String, Command> additionalCommands) {
        this.additionalCommands = additionalCommands;
    }


    public Class getOriginClass() {
        return originClass;
    }

    public void setOriginClass(Class originClass) {
        this.originClass = originClass;
    }
}
