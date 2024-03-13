package com.tour.controller;

import com.tour.model.Booking;
import com.tour.model.Trip;
import com.tour.repository.BookingRepository;
import com.tour.repository.PackRepository;
import com.tour.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RequestMapping("/trip")
@Controller
public class TripController {

    @Autowired
    TripRepository repo;

    @Autowired
    PackRepository packRepo;


    @Autowired
    BookingRepository bookRepo;


    @RequestMapping("/pack/list")
    public String packList(Model model) {
        model.addAttribute("datalist", packRepo.findAll());
        return "trip_pack";
    }

    @RequestMapping("/list/{packid}")
    public String list(Model model, @PathVariable String packid) {

        model.addAttribute("datalist", repo.findByPackageId(packid).get());
        model.addAttribute("packid", packid);
        String packname = packRepo.findByPackageId(packid).get().getPackageName();
        model.addAttribute("packname", packname);
        return "trip";
    }

    @RequestMapping("/create/{packid}")
    public String create(Model model, @PathVariable String packid) {
        model.addAttribute("packid", packid);
        String packname = packRepo.findByPackageId(packid).get().getPackageName();
        model.addAttribute("packname", packname);
        int dur = Integer.parseInt(packRepo.findByPackageId(packid).get().getDuration().split(" ")[0]);
        model.addAttribute("duration", dur);
        return "trip_create";
    }

    @RequestMapping("/save")
    public String save(Trip obj) {
        obj.setAvlSeats(obj.getTotalSeats());
        repo.save(obj);
        return "redirect:/trip/list/" + obj.getPackageId();
    }


    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        Optional<Trip> obj = repo.findById(id);
        repo.delete(obj.get());

        Optional<List<Booking>> bookings = bookRepo.findByTripId(id);
        bookRepo.deleteAll(bookings.get());

        return "redirect:/trip/list/" + obj.get().getPackageId();
    }

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable String id, Model model, HttpServletRequest req) {
        Trip trip = repo.findById(id).get();
        model.addAttribute("obj", trip);
        int dur = Integer.parseInt(packRepo.findByPackageId(trip.getPackageId()).get().getDuration().split(" ")[0]);
        model.addAttribute("duration", dur);
        return "trip_edit";
    }

    @RequestMapping("/update")
    public String update(Trip obj) {
        List<Booking> bookings = bookRepo.findByTripIdAndBookingStatusNot(obj.getId(), "Cancelled").get();
        bookings.forEach(b -> {
            b.setBookingStatus("Pending");
            b.setTripPosponded(true);
        });
        bookRepo.saveAll(bookings);

        obj.setPostponedStatus("Postponded");
        repo.save(obj);
        return "redirect:/trip/list/" + obj.getPackageId();
    }


}
