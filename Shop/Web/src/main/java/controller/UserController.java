package controller;

import constants.Constants;
import interfaces.IUserService;
import model.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pagenator.Pagenator;
import session.SessionInfo;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/getAll")
    public String getAllUsers(ModelMap model, @RequestParam(value = "rowsonpage", defaultValue = "") String rowsOnPage,
                              @RequestParam(value = "nextpage", defaultValue = "") String nextPage) {
        fillModel(model, rowsOnPage, nextPage);
        return "user/main";
    }


    @RequestMapping(value = "/login")
    public String loginUser(ModelMap model) {
        model.put("user", new User());
        return "user/login";
    }


    @RequestMapping(value = "/afterlogin")
    public String afterLoginUser(ModelMap model, HttpServletRequest request, @Valid @ModelAttribute("user") User user, BindingResult br) {

        if (user == null) {
            model.put("message", "Can not login user, please call to administrator or try again");
        } else if ( br.hasErrors()) {

            StringBuffer buffer = new StringBuffer("");

            for (Iterator iterator = br.getAllErrors().iterator(); iterator.hasNext(); ) {
                FieldError fieldError = (FieldError) iterator.next();
                buffer.append("error: " + fieldError.getDefaultMessage() + ", ");
            }
            model.put("message", buffer);
             } else {

            // check password
            User loginUser =  (User)userService.checkPassword(user);

            if (loginUser == null) {
                model.put("message", "Can not login user " + user.getName() + ", invalid login name or password");
            } else {

                SessionInfo sessionInfo = (SessionInfo)request.getSession().getAttribute("sessionInfo");
                sessionInfo.logIn(loginUser);
                     }
        }

        return "welcome";
    }




    @RequestMapping(value = "/logout")
    public String logoutUser(ModelMap model, HttpServletRequest request) {

        SessionInfo sessionInfo = (SessionInfo) request.getSession().getAttribute("sessionInfo");
        sessionInfo.logOut();
        return "welcome";
    }


    @RequestMapping(value = "/signup")
    public String signUpUser(ModelMap model) {
        model.put("user", new User());
        return "user/signup";
    }


        @RequestMapping(value = "/aftersignup")
    public String afterSignUpUser(ModelMap model, @Valid @ModelAttribute("user") User user, BindingResult br) {

        if (user == null) {
            model.put("message", "Can not sign up user, please call to administrator");
        } else if ( br.hasErrors()) {

            StringBuffer buffer = new StringBuffer("");

            for (Iterator iterator = br.getAllErrors().iterator(); iterator.hasNext(); ) {
                FieldError fieldError = (FieldError) iterator.next();
                buffer.append("error: " + fieldError.getDefaultMessage() + ", ");
            }
            model.put("message", buffer);


        } else {

              if (userService.getUser(user.getName()) != null) {
                model.put("message", "Can not sign up user " + user.getName() + ", because he is. Try a different name!");
            } else {

                user = (User) userService.saveOrUpdate(user);
                model.put("message", "Info: " + user.getName() + ",  id =" + user.getId() + ", was signed up");
                fillModel(model, Constants.ROWS_ON_PAGE, Constants.DEFAULT_CURRENT_PAGE);
            }
        }

        return "user/main";
    }


    @RequestMapping(value = "/edit")
    public String editUser(ModelMap model, @RequestParam(value = "id", defaultValue = "0") Long id) {

        if (id == 0) {
            model.put("message", "Can not edit user whith id=" + id + ", please call to administrator");
        } else {
            User user = (User)userService.select(id);
            user.setSalt("");
            user.setPassword("");
            model.put("user", user);
        }

        return "user/edit";
    }


    @RequestMapping(value = "/afteredit")
    public String aftereditUser(ModelMap model, @Valid @ModelAttribute("user") User user, BindingResult br) {

        if (user == null) {
            model.put("message", "Can not update user, please call to administrator");
        } else if ( br.hasErrors()) {

            StringBuffer buffer = new StringBuffer("");

            for (Iterator iterator = br.getAllErrors().iterator(); iterator.hasNext(); ) {
                FieldError fieldError = (FieldError) iterator.next();
                buffer.append("error: " + fieldError.getDefaultMessage() + ", ");
            }
            model.put("message", buffer);

        } else {
            user = (User) userService.saveOrUpdate(user);
            model.put("message", "Info: " + user.getName() + ",  id =" + user.getId() + ", was update");
              }


        return "user/main";
    }



    @RequestMapping(value = "/delete")
    public String deletePerson(ModelMap model, @RequestParam(value = "id", defaultValue = "0") Long id) {


        if (id == 0) {
            model.put("message", "Can not delete user whith id=" + id + ", please call to administrator");
        } else {
            String name = userService.delete(id);
            model.put("message", "Info: " + name + ", id =" + id + ", was deleted");
              }

        return "user/main";
    }



    private void fillModel(ModelMap model, String rowsOnPage, String nextPage) {

        if (StringUtils.isEmpty(rowsOnPage)) {
            rowsOnPage = Constants.ROWS_ON_PAGE;
        }
        if (StringUtils.isEmpty(nextPage)) {
            nextPage = Constants.DEFAULT_CURRENT_PAGE;
        }

        List<User> users = null;  // = new ArrayList<>();
        Pagenator pagenatorUser = new Pagenator(users, Long.valueOf(rowsOnPage), 1, Long.valueOf(nextPage));


        userService.selectAll(pagenatorUser);

        model.put("rowsonpage", rowsOnPage);
        model.put("users", pagenatorUser.getObjects());
        model.put("currentpage", nextPage);
        model.put("countpages", pagenatorUser.getCountPages());
    }


    @ExceptionHandler(Exception.class)
    public ModelAndView handleAllException(Exception ex) {

        ModelAndView model = new ModelAndView("jsp/error");
        model.addObject("errorKey", "Error was thrown: " + ex);
        return model;
    }


}



