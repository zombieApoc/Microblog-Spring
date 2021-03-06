package com.theironyard.clt;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class MicroblogSpringController {

    ArrayList<Messages> messages = new ArrayList<>();

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(Model model, HttpSession session) {
        model.addAttribute("name", session.getAttribute("userName"));
        return "home";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(HttpSession session, String userName) {
        session.setAttribute("userName", userName);
        return "redirect:/";
    }
    @RequestMapping(path = "/addmessage", method = RequestMethod.POST)
    public String addmessage(HttpSession session, String message) {
        session.setAttribute("message", message);
        Messages log = new Messages(messages.size() + 1, message);
        messages.add(log);
        return "redirect:/";
    }
    @RequestMapping(path = "/deletemessage", method = RequestMethod.POST)
    public String deletemessage(HttpSession session, int id) {
        session.setAttribute("id", id);
        messages.remove(id - 1);
        return "redirect:/";
    }
}
