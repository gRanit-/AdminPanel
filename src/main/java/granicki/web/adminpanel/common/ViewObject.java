package granicki.web.adminpanel.common;

import org.apache.log4j.Logger;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * <b>KAZDY OBIEKT MUSI MIEC DOMYSLNY KONSTUKTOR!</b>
 * <br>
 * Created by mc109881 on 2015-06-11.
 */
public class ViewObject implements Serializable {

    private static final long serialVersionUID = -6381743827150155888L;

    private Logger logger = Logger.getLogger(this.getClass());

    private ViewObjectType viewObjectType;

    private final long viewObjectID = 0; // ID różnie się miedzy instancjami >>tej samej klasy<<<, pomiedzy roznymi klasami moze byc takie samo


    private String viewObjectName = "ViewObject";
    private Command editObject;
    private Command deleteObject;


    Map<String, Object> fieldNameValueMap;

    public ViewObject(Object object, ViewObjectType viewObjectType) {
        this.viewObjectType = viewObjectType;

        fieldNameValueMap = new HashMap<String, Object>();

        for (Field field : object.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            Object value = null;
            try {
                value = field.get(object);
            } catch (IllegalAccessException e) {
                //System.out.println(e);
                logger.error(e.getMessage(), e);
            }
            if (value != null) {
                fieldNameValueMap.put(field.getName(), value);
            }
        }

    }

    public ViewObject(Map<String, Object> fieldNameValueMap, ViewObjectType viewObjectType) {
        this.fieldNameValueMap = fieldNameValueMap;
        this.viewObjectType = viewObjectType;
    }

    public String getViewObjectName() {
        return viewObjectName;
    }

    public void setViewObjectName(String viewObjectName) {
        this.viewObjectName = viewObjectName;
    }

    public Map<String, Object> getFieldNameValueMap() {
        return fieldNameValueMap;
    }

    public void setFieldNameValueMap(Map<String, Object> fieldNameValueMap) {
        this.fieldNameValueMap = fieldNameValueMap;
    }

    public ViewObjectType getViewObjectType() {
        return viewObjectType;
    }

    public void setViewObjectType(ViewObjectType viewObjectType) {
        this.viewObjectType = viewObjectType;
    }

    public long getViewObjectID() {
        return viewObjectID;
    }

    public Command getEditObject() {
        return editObject;
    }

    public void setEditObject(Command editObject) {
        this.editObject = editObject;
    }

    public Command getDeleteObject() {
        return deleteObject;
    }

    public void setDeleteObject(Command deleteObject) {
        this.deleteObject = deleteObject;
    }

    @Override
    public String toString() {
        return "ViewObject{" +
                "viewObjectType=" + viewObjectType +
                ", viewObjectID=" + viewObjectID +
                ", viewObjectName='" + viewObjectName + '\'' +
                ", editObject=" + editObject +
                ", deleteObject=" + deleteObject +
                
                '}';
    }
}
