package itdccs.loginmanager.web.adminpanel.controllers;

import itdccs.loginmanager.web.adminpanel.common.ViewObjectType;
import itdccs.loginmanager.web.adminpanel.services.ObjectRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * Created by mc109881 on 2015-06-12.
 */
@Controller
@RequestMapping("/adminPanel")
public class AdminPanelViewsController {

    AdminPanelViewsController() {
    }


    @Autowired
    ObjectRepositories objectRepositories;

    @RequestMapping("/")
    public ModelAndView showMainView() {
        return new ModelAndView("adminPanel/index");
    }

    @RequestMapping("/objectView")
    public ModelAndView showObjectView() {
        return new ModelAndView("adminPanel/entities/entityView");
    }

    @RequestMapping("/allObjectsView")
    public ModelAndView showObjectViews() {
        return new ModelAndView("adminPanel/entities/allEntitiesView");
    }

    @RequestMapping("/objectTypesView")
    public ModelAndView showObjectList() {

        return new ModelAndView("adminPanel/objectTypesView");

    }

    @RequestMapping("/objectTypes")
    public
    @ResponseBody
    Map<Long, ViewObjectType> getObjectTypes() {

        return objectRepositories.getViewObjectTypeMapID();


    }

    // public void

}
