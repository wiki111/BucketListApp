package bucketlistapp.controller;

import java.security.Principal;

import bucketlistapp.utils.WebUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

    @RequestMapping(value = { "/"}, method = RequestMethod.GET)
    public String welcomePage(Model model) {
        return "welcome";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Model model) {
        return "user/login";
    }

    @RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
    public String logoutSuccessfulPage(Model model) {
        model.addAttribute("title", "Logout");
        return "user/logoutSuccessful";
    }

    @RequestMapping(value = "/user_profile", method = RequestMethod.GET)
    public String userInfo(Model model, Principal principal) {

        String userName = principal.getName();

        System.out.println("User Name: " + userName);

        User loggedinUser = (User) ((Authentication) principal).getPrincipal();

        String userInfo = WebUtils.toString(loggedinUser);
        model.addAttribute("userInfo", userInfo);

        return "user/profile";
    }
}
