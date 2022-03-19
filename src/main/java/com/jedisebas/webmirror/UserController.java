package com.jedisebas.webmirror;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    public final UserService userService;
    public final EventService eventService;

    @Autowired
    public UserController(UserService userService, EventService eventService) {
        this.userService = userService;
        this.eventService = eventService;
    }

    @GetMapping("/user")
    @ResponseBody
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

    @GetMapping("/signuperror2")
    public String signuperror2View(Model model) {
        model.addAttribute("user", new User());
        return "signuperror2";
    }

    @GetMapping("/signuperror3")
    public String signuperror3View(Model model) {
        model.addAttribute("user", new User());
        return "signuperror3";
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
        LoggedUser.emailPassword = null;
        LoggedUser.nick = null;
        LoggedUser.isLogged = false;
        return "redirect:/index/";
    }

    @GetMapping("/home/{name}")
    public String homeView(Model model, @PathVariable String name) {
        model.addAttribute("helloname", name);
        if (LoggedUser.isLogged && name.equals(LoggedUser.name)) {
            List<Event> events = eventService.getEvents(LoggedUser.id);
            System.out.println(events);
            model.addAttribute("events", events);
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
            List<String> pictures = userService.getPictures(LoggedUser.id);
            System.out.println(pictures);
            List<String> picturesFinal = new ArrayList<>();
            for (String str: pictures) {
                picturesFinal.add("http://localhost/mirror/" + str);
            }
            model.addAttribute("pictures", picturesFinal);
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

    @GetMapping("/home/raspberry_monitor/{name}")
    public String raspberry_monitorView(Model model, @PathVariable String name) {
        model.addAttribute("helloname", name);
        if (LoggedUser.isLogged && name.equals(LoggedUser.name)) {
            return "raspberry_monitor";
        } else {
            return "redirect:/index/";
        }
    }

    @GetMapping("/home/event_log/{name}")
    public String event_logView(Model model, @PathVariable String name) {
        model.addAttribute("helloname", name);
        if (LoggedUser.isLogged && name.equals(LoggedUser.name)) {
            return "event_log";
        } else {
            return "redirect:/index/";
        }
    }

    @PostMapping("/register")
    public String userRegister(@ModelAttribute User user) {
        switch (userService.addNewUser(user)) {
            case 0:
                return "redirect:/index/";
            case 1:
                return "redirect:/signuperror/";
            case 2:
                return "redirect:/signuperror2/";
            case 3:
                return "redirect:/signuperror3/";
            default:
                return "redirect:/signup/";
        }
    }

    @PostMapping("/login")
    public String tryLogin(@ModelAttribute User user) {
        if (userService.loginUser(user)) {
            return "redirect:/home/" + userService.getUserNameFromDb(user);
        }
        return "redirect:/loginerror/";
    }

    @PostMapping("/addevent")
    public String addEvent(@ModelAttribute Event event, @ModelAttribute EventTime time) {
        String res = event.getDate() + " " + time.getTime();
        event.setUserid(LoggedUser.id);
        event.setDate(res);
        eventService.addNewEvent(event);
        return "redirect:/home/" + LoggedUser.name;
    }
}
