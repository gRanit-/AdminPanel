package granicki.web.adminpanel.controllers;

import itdccs.loginmanager.common.dao.OrganizationDAO;
import itdccs.loginmanager.common.dao.UserDAO;
import itdccs.loginmanager.common.entities.Organization;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;


/**
 * Created by mc109881 on 2015-06-10.
 */

@Controller
@RequestMapping("/organization")
public class OrganizationController {

    Logger logger = Logger.getLogger(this.getClass());

    OrganizationDAO organizationDAO;
    UserDAO userDAO;

    @RequestMapping("/get/{organizationPK}")
    public
    @ResponseBody
    Organization getOrganization(@PathVariable long organizationPK) {

        return organizationDAO.getByPk(organizationPK);
    }

    @RequestMapping("/get")
    public
    @ResponseBody
    List<Organization> getAllOrganizations() {
        return organizationDAO.getAllOrganizations();
    }

    @RequestMapping("/get/{organizationPK}/payment/{paymentDate}")
    public
    @ResponseBody
    Organization getOrganizationPayment(@PathVariable("organizationPK") long organizationPK, @PathVariable("paymentDate") String paymentDate) {

        Organization organization = null;
        DateFormat format = new SimpleDateFormat("MM-yyyy", Locale.ENGLISH);
        Date date = null;
        try {
            date = format.parse(paymentDate);
        } catch (ParseException e) {
            logger.error(e);
        }
        if (date != null)
            organization = organizationDAO.getOrganizationWithPayments(organizationPK, date);

        else organization = new Organization();

        return organization;
    }

    @RequestMapping("/get/{organizationPK}/users/")
    public
    @ResponseBody
    Organization getOrganizationPayment(@PathVariable("organizationPK") long organizationPK) {

        Organization organization = null;
        organization = organizationDAO.getOrganizationWithUsers(organizationPK);

        return organization;
    }
}
