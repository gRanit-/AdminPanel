package itdccs.loginmanager.web.adminpanel.services;

import itdccs.loginmanager.web.adminpanel.common.ViewObjectType;
import itdccs.loginmanager.common.dao.EntityDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mc109881 on 2015-06-11.
 */
@Service
public class ObjectRepositories implements Serializable {


    private static final long serialVersionUID = -8663236606261994427L;
    @Resource(name = "classEntityDAOMap")
    private Map<Class, EntityDAO> classEntityDAOMap;
    @Resource(name="viewObjectTypeMap")
    private Map<Class, ViewObjectType> viewObjectTypeMap;
    @Resource(name="viewObjectTypeMapID")
    private Map<Long, ViewObjectType> viewObjectTypeMapID;

    public ObjectRepositories() {
    }

    public ObjectRepositories(List<ViewObjectType> viewObjectTypes, Map<Class, EntityDAO> entityDAOMap) {
        classEntityDAOMap = entityDAOMap;

        viewObjectTypeMap = new HashMap<Class, ViewObjectType>();
        viewObjectTypeMapID = new HashMap<Long, ViewObjectType>();


        for (ViewObjectType viewObjectType : viewObjectTypes) {
            viewObjectTypeMap.put(viewObjectType.getOriginClass(), viewObjectType);
            viewObjectTypeMapID.put(new Long(viewObjectType.getThisObjectTypeID()), viewObjectType);
        }

    }

    public void addViewObjectType(ViewObjectType viewObjectType) {
        viewObjectTypeMap.put(viewObjectType.getOriginClass(), viewObjectType);
        viewObjectTypeMapID.put(new Long(viewObjectType.getThisObjectTypeID()), viewObjectType);
    }


    public void addEntityDAO(Class aClass, EntityDAO entityDAO) {
        classEntityDAOMap.put(aClass, entityDAO);
    }

    public EntityDAO get(Class aClass) {

        return classEntityDAOMap.get(aClass);
    }

    public ViewObjectType getViewObjectType(Class aClass) {
        return viewObjectTypeMap.get(aClass);
    }

    public ViewObjectType getViewObjectType(Long id) {

        if (viewObjectTypeMapID == null)
            System.out.println("VIEW OBJECT TYPE MAPE ID IS NULL");

        return viewObjectTypeMapID.get(id);
    }

    public void putViewObjectType(Class aClass, ViewObjectType viewObjectType) {
        viewObjectTypeMap.put(aClass, viewObjectType);
    }

    public void putViewObjectType(Long id, ViewObjectType viewObjectType) {
        viewObjectTypeMapID.put(id, viewObjectType);
    }

    public Map<Class, ViewObjectType> getViewObjectTypeMap() {
        return viewObjectTypeMap;
    }

    public void setViewObjectTypeMap(Map<Class, ViewObjectType> viewObjectTypeMap) {
        this.viewObjectTypeMap = viewObjectTypeMap;
    }

    public Map<Long, ViewObjectType> getViewObjectTypeMapID() {
        return viewObjectTypeMapID;
    }

    public void setViewObjectTypeMapID(Map<Long, ViewObjectType> viewObjectTypeMapID) {
        this.viewObjectTypeMapID = viewObjectTypeMapID;
    }

    public Map<Class, EntityDAO> getClassEntityDAOMap() {
        return classEntityDAOMap;
    }

    public void setClassEntityDAOMap(Map<Class, EntityDAO> classEntityDAOMap) {
        this.classEntityDAOMap = classEntityDAOMap;
    }

    @Override
    public String toString() {
        return "ObjectRepositories{" +
                "classEntityDAOMap=" + classEntityDAOMap +
                ", viewObjectTypeMap=" + viewObjectTypeMap +
                ", viewObjectTypeMapID=" + viewObjectTypeMapID +
                '}';
    }
}
