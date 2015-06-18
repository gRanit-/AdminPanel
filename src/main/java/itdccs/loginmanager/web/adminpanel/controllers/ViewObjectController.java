package itdccs.loginmanager.web.adminpanel.controllers;

import itdccs.loginmanager.web.adminpanel.common.ViewObjectType;
import itdccs.loginmanager.web.adminpanel.services.ObjectRepositories;
import itdccs.loginmanager.web.adminpanel.common.ViewObject;
import itdccs.loginmanager.web.adminpanel.services.ViewObjectBuilder;
import itdccs.loginmanager.common.dao.EntityDAO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;


/**
 * Created by mc109881 on 2015-06-11.
 */

@Controller
@RequestMapping("/adminPanel")
public class ViewObjectController {
    Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    ObjectRepositories objectRepositories;
    @Autowired
    ViewObjectBuilder viewObjectBuilder;


    @RequestMapping(value = "/objectView/{viewObjectTypeID}/add", method = RequestMethod.POST)
    public
    @ResponseBody
    String add(@PathVariable("viewObjectTypeID") long viewObjectTypeID, @RequestBody ViewObject viewObject) {

        String status = "";
        try {
            EntityDAO entityDAO = getObjectEntity(viewObjectTypeID);
            Object object = viewObjectBuilder.toOriginObject(viewObject);
            entityDAO.add(object);
            status = "Success";

        } catch (Throwable t) {
            logger.error(t);
            status = "Failure";

        }
        return status;

    }

    @RequestMapping(value = "/objectView/{viewObjectTypeID}/get/{objectPK}", method = RequestMethod.GET)
    public
    @ResponseBody
    ViewObject get(@RequestParam(required = false, value = "eager", defaultValue = "false") boolean eager, @PathVariable("viewObjectTypeID") long objectTypeID, @PathVariable(value = "objectPK") long objectPK) {
        logger.info(">>>GET");
        if (objectRepositories == null)
            logger.info("objectRepository IS NULLLLLLLLLLLLLLLLL");
        else {
            if (objectRepositories.getViewObjectTypeMap() == null)
                logger.info("ViewObjectTypeMap IS NULLLLLLLLLLLLLLLLL");
            if (objectRepositories.getViewObjectTypeMapID() == null)
                logger.info("ViewObjectTypeMapID IS NULLLLLLLLLLLLLLLLL");
        }

        ViewObject viewObject = null;
        try {
            EntityDAO entityDAO = getObjectEntity(objectTypeID);

            Object object = null;
            if (eager)
                object = entityDAO.getByPkEager(objectPK);
            else
                object = entityDAO.getByPk(objectPK);
            viewObject = viewObjectBuilder.fromObject(object).build();
        } catch (Throwable t) {
            logger.error(t.getMessage(), t);
        }

        //logger.info(viewObject.toString());
        return viewObject;
    }

    @RequestMapping(value = "/objectView/{viewObjectTypeID}/get", method = RequestMethod.GET)
    public
    @ResponseBody
    List<ViewObject> getAll(@PathVariable("viewObjectTypeID") long objectTypeID) {
        logger.info(">>>GET ALL");

        List<ViewObject> viewObjects = new LinkedList<ViewObject>();

        if (objectRepositories == null)
            logger.info("objectRepository IS NULLLLLLLLLLLLLLLLL");
        else {
            if (objectRepositories.getViewObjectTypeMap() == null)
                logger.info("ViewObjectTypeMap IS NULLLLLLLLLLLLLLLLL");
            if (objectRepositories.getViewObjectTypeMapID() == null)
                logger.info("ViewObjectTypeMapID IS NULLLLLLLLLLLLLLLLL");
        }

        try {
            EntityDAO entityDAO = getObjectEntity(objectTypeID);
            List<Object> list = entityDAO.getAll();
            for (Object o : list)
                viewObjects.add(viewObjectBuilder.fromObject(o).build());
        } catch (Throwable t) {
            logger.error(t.getMessage(), t);
        }
        return viewObjects;
    }

    @RequestMapping(value = "/objectView/{viewObjectTypeID}/remove/{objectPK}", method = RequestMethod.POST)
    public
    @ResponseBody
    String remove(@PathVariable("viewObjectTypeID") long objectTypeID, @PathVariable(value = "objectPK") long objectPK) {

        String state = "";
        try {
            EntityDAO entityDAO = getObjectEntity(objectTypeID);
            Object object = entityDAO.getByPk(new Long(objectPK));
            entityDAO.remove(object);
            state = "Success";
        } catch (Throwable t) {
            logger.error(t.getMessage(), t);
            state = "Failure";
        }
        return state;


    }

    @RequestMapping(value = "/objectView/{viewObjectTypeID}/update", method = RequestMethod.POST)
    public
    @ResponseBody
    String update(@PathVariable("viewObjectTypeID") long objectTypeID, @RequestBody ViewObject viewObject) {
        String status = "";

        try {
            EntityDAO entityDAO = getObjectEntity(objectTypeID);
            Object object = viewObjectBuilder.toOriginObject(viewObject);
            Object managedObject = entityDAO.getByPk(viewObject.getFieldNameValueMap().get("pk"));
            entityDAO.merge(managedObject);
            status = "Success";
        } catch (Throwable t) {
            logger.error(t.getMessage(), t);
            status = "Failure";
        }
        return status;


    }

    private EntityDAO getObjectEntity(long objectTypeID) {
        ViewObjectType viewObjectType = objectRepositories.getViewObjectType(objectTypeID);
        if (objectRepositories == null)
            logger.error("OBJECT REPOSITORIES IS NULLLLLLLLLLLL!!!!!!!!!");
        else {
            logger.error("----->\n" + objectRepositories.getViewObjectTypeMap().toString());

            logger.error("----->\n" + objectRepositories.getViewObjectTypeMapID().toString());
        }
        if (viewObjectType == null)
            logger.error("VIEW OBJECT TYPE == NULL");
        else if (viewObjectType.getOriginClass() == null)
            logger.error("ORIGIN CLASS == NULL");

        EntityDAO entityDAO = objectRepositories.get(viewObjectType.getOriginClass());
        entityDAO.setType(viewObjectType.getOriginClass());
        return entityDAO;
    }

}
