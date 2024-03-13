package com.tour.controller;

import com.tour.model.Itinerary;
import com.tour.repository.ItineraryRepository;
import com.tour.repository.PackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RequestMapping("/itinerary")
@Controller
public class ItineraryController {

    @Autowired
    ItineraryRepository repo;

    @Autowired
    PackRepository packRepo;


    @RequestMapping("/pack/list")
    public String packList(Model model) {
        model.addAttribute("datalist", packRepo.findAll());
        return "itinerary_pack";
    }

    @RequestMapping("/list/{packid}")
    public String list(Model model, @PathVariable String packid) {

        model.addAttribute("datalist", repo.findByPackageId(packid).get());
        model.addAttribute("packid", packid);
        return "itinerary";
    }

    @RequestMapping("/create/{packid}")
    public String create(Model model, @PathVariable String packid) {
        int day = repo.findByPackageId(packid).get().size() + 1;
        int dur = Integer.parseInt(packRepo.findByPackageId(packid).get().getDuration().split(" ")[0]);
        System.out.println("duration : " + dur);
        if (dur >= day) {
            model.addAttribute("day", day);
            model.addAttribute("packid", packid);

            return "itinerary_create";
        } else {
            model.addAttribute("datalist", repo.findByPackageId(packid).get());
            model.addAttribute("packid", packid);
            model.addAttribute("msg", "Day count exceeds the package duration");
            return "itinerary";
        }
    }

    @RequestMapping("/save")
    public String save(Itinerary obj) {
        repo.save(obj);
        return "redirect:/itinerary/list/" + obj.getPackageId();
    }


    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        Optional<Itinerary> obj = repo.findById(id);
        repo.delete(obj.get());

        return "redirect:/itinerary/list/" + obj.get().getPackageId();
    }

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable String id, Model model, HttpServletRequest req) {
        model.addAttribute("obj", repo.findById(id).get());
        return "itinerary_edit";
    }

    @RequestMapping("/update")
    public String update(Itinerary obj) {
        repo.save(obj);
        return "redirect:/itinerary/list/" + obj.getPackageId();
    }


}
