package com.tour.controller;

import com.tour.model.Admin;
import com.tour.model.Login;
import com.tour.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/admin")
@Controller
public class AdminController {

    @Autowired
    AdminRepository repo;

    @RequestMapping()
    public String login(Model model) {
        return "admin_login";
    }

    @RequestMapping("/home")
    public String home(Model model) {
        return "admin_home";
    }

    @RequestMapping("/logout")
    public String logout(Model model, HttpServletRequest req) {
        req.getSession().invalidate();
        return "logout";
    }

    @PostMapping("/login")
    public String show(Login login, Model model, HttpServletRequest request) {
        if (!repo.findAll().iterator().hasNext()) {
            repo.save(new Admin("admin"));
            request.getSession().setAttribute("id", "1001");
            request.getSession().setAttribute("userid", "STAFF00001");
            request.getSession().setAttribute("usertype", "staff");
            request.getSession().setAttribute("name", "Admin");
            return "redirect:/admin/home/";
        }

        if (!login.getEmail().equals("admin")) {
            model.addAttribute("msg", "Invalid Username !");
            return "admin_login";
        } else if (repo.findByPassword(login.getPassword()).isPresent()) {
            request.getSession().setAttribute("id", "1001");
            request.getSession().setAttribute("userid", "STAFF00001");
            request.getSession().setAttribute("usertype", "staff");
            request.getSession().setAttribute("name", "Admin");
            return "redirect:/admin/home/";
        } else {
            model.addAttribute("msg", "Invalid Password !");
            return "admin_login";
        }
    }

    @RequestMapping("/edit")
    public String edit(Model model, HttpServletRequest req) {
        model.addAttribute("obj", repo.findAll().iterator().next());
        System.out.println(repo.findAll().iterator().next());
        return "admin_edit";
    }

    @RequestMapping("/update")
    public String update(Admin obj) {
        repo.save(obj);
        return "redirect:/admin/home/";
    }

}
