package com.jedisebas.webmirror;

import org.apache.juli.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.jedisebas.webmirror.LoggedUser.name;

@Controller
public class UserController {

    public final UserService userService;
    public final EventService eventService;

    public static String ip = "192.168.0.50";

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
        name = null;
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
                picturesFinal.add("http://"+ip+"/mirror/" + str);
            }
            List<Picture> pictureList = new ArrayList<>();
            for (int i=0; i<pictures.size(); i++) {
                pictureList.add(new Picture(pictures.get(i), picturesFinal.get(i)));
            }
            model.addAttribute("pictures", pictureList);
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
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    model.addAttribute("chartData", getChartData());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        if (LoggedUser.isLogged && name.equals(LoggedUser.name)) {
            return "raspberry_monitor";
        } else {
            return "redirect:/index/";
        }
    }

    private List<List<Object>> getChartData() {
        return List.of(
                List.of(1, randint()),
                List.of(2, randint()),
                List.of(3, randint()),
                List.of(4, randint()),
                List.of(5, randint()),
                List.of(6, randint()),
                List.of(7, randint()),
                List.of(8, randint()),
                List.of(9, randint()),
                List.of(10, randint()),
                List.of(11, randint()),
                List.of(12, randint())
        );
    }

    private int randint() {
        double x;
        x = Math.random();
        x *= 100;
        x = Math.round(x);
        return (int) x;
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
        return "redirect:/home/" + name;
    }

    @GetMapping("/delete/{name}")
    public String deleteProfile(Model model, @PathVariable String name) {
        model.addAttribute("helloname", name);
        if (LoggedUser.isLogged && name.equals(LoggedUser.name)) {
            userService.deleteAccount();
        }
        return "redirect:/index/";
    }

    @PostMapping("/deleteEvent/{event_name}")
    public String deleteEvent(Model model, @PathVariable String event_name) {
        model.addAttribute("helloname", name);
        if (LoggedUser.isLogged && name.equals(LoggedUser.name)) {
            eventService.deleteEvent(event_name);
        }
        return "redirect:/home/" + name;
    }

    @PostMapping("/deletePicture/{picture_name}")
    public String deletePicture(Model model, @PathVariable String picture_name) {
        model.addAttribute("helloname", name);
        if (LoggedUser.isLogged && name.equals(LoggedUser.name)) {
            userService.deletePicture(picture_name);
        }
        return "redirect:/home/gallery/" + name;
    }

    @PostMapping("/downloadPicture/{picture_name}")
    public String downloadPicture(Model model, @PathVariable String picture_name) {
        model.addAttribute("helloname", name);
        if (LoggedUser.isLogged && name.equals(LoggedUser.name)) {
            new FTPConfig().download(picture_name);
        }
        return "redirect:/home/gallery/" + name;
    }

    @PostMapping("/uploadPicture")
    public String uploadPicutre(Model model) {
        model.addAttribute("helloname", name);
        return "redirect:/home/account/" + name;
    }

    @PostMapping("/registerIg")
    public String registerInstagram(Model model, @ModelAttribute User user) {
        model.addAttribute("helloname", name);
        if (LoggedUser.isLogged && name.equals(LoggedUser.name)) {
            userService.registerIg(user);
        }
        return "redirect:/home/account/" + name;
    }
}
