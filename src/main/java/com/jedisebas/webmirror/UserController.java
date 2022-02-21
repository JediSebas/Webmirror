package com.jedisebas.webmirror;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class UserController {

    public final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @ResponseBody
    @RequestMapping(path = "user")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/signup")
    public String signupView(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @GetMapping("/signuperror")
    public String signuperrorView(Model model) {
        model.addAttribute("user", new User());
        return "signuperror";
    }

    @GetMapping("/index")
    public String loginView() {
        return "index";
    }

    @GetMapping("/loginerror")
    public String loginerrorView() {
        return "loginerror";
    }

    @GetMapping("/logout")
    public String logoutView() {
        LoggedUser.id = null;
        LoggedUser.name = null;
        LoggedUser.lastname = null;
        LoggedUser.password = null;
        LoggedUser.email = null;
        LoggedUser.isLogged = false;
        return "redirect:/index/";
    }

    @GetMapping("/home/{name}")
    public String homeView(Model model, @PathVariable String name) {
        model.addAttribute("helloname", name);
        if (LoggedUser.isLogged && name.equals(LoggedUser.name)) {
            return "home";
        } else {
            return "redirect:/index/";
        }
    }

    @GetMapping("/home/personalization/{name}")
    public String personalizationView(Model model, @PathVariable String name) {
        model.addAttribute("helloname", name);
        if (LoggedUser.isLogged && name.equals(LoggedUser.name)) {
            return "personalization";
        } else {
            return "redirect:/index/";
        }
    }

    @GetMapping("/home/gallery/{name}")
    public String galleryView(Model model, @PathVariable String name) {
        model.addAttribute("helloname", name);
        if (LoggedUser.isLogged && name.equals(LoggedUser.name)) {
            return "gallery";
        } else {
            return "redirect:/index/";
        }
    }

    @GetMapping("/home/account/{name}")
    public String accountView(Model model, @PathVariable String name) {
        model.addAttribute("helloname", name);
        model.addAttribute("hellolastname", LoggedUser.lastname);
        if (LoggedUser.isLogged && name.equals(LoggedUser.name)) {
            return "account";
        } else {
            return "redirect:/index/";
        }
    }

    @GetMapping("/home/settings/{name}")
    public String settingsView(Model model, @PathVariable String name) {
        model.addAttribute("helloname", name);
        if (LoggedUser.isLogged && name.equals(LoggedUser.name)) {
            return "settings";
        } else {
            return "redirect:/index/";
        }
    }

    @PostMapping("/register")
    public String userRegister(@ModelAttribute User user) {
        if (userService.addNewUser(user)) {
            return "redirect:/index/";
        } else {
            return "redirect:/signuperror/";
        }
    }

    @PostMapping("/login")
    public String tryLogin(@ModelAttribute User user) {
        if (userService.loginUser(user)) {
            return "redirect:/home/" + userService.getUserNameFromDb(user);
        }
        return "redirect:/loginerror/";
    }
}
