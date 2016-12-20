package controller;

import constants.Constants;
import interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import session.SessionInfo;

import javax.servlet.http.HttpServletRequest;

@Controller
//@RequestMapping("")
public class WelcomeController {

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "")
    public String defaultPage(ModelMap model) {
        return "welcome";
    }


    @RequestMapping(value = "/welcome")
    public String welcomePage(ModelMap model) {
        return "welcome";
    }

    @RequestMapping(value = "/changeLocale")
    public String changeLocale(ModelMap model, HttpServletRequest request, @RequestParam(value = "locale", defaultValue = Constants.RU_LOCALES) String locale) {

        SessionInfo sessionInfo = (SessionInfo)request.getSession().getAttribute("sessionInfo");


        if ( locale.equals(Constants.RU_LOCALES)) {
            sessionInfo.setLocale(Constants.RU_LOCALES);
        } else if (locale.equals(Constants.EN_LOCALES)) {
            sessionInfo.setLocale(Constants.EN_LOCALES);
        }  else {
            sessionInfo.setLocale(Constants.DEFAULT_LOCALE);
    }

        return "welcome";
    }




    @ExceptionHandler(Exception.class)
    public ModelAndView handleAllException(Exception ex) {

        ModelAndView model = new ModelAndView("jsp/error");
        model.addObject("errorKey", "Error was thrown: " + ex);
        return model;
    }


}



