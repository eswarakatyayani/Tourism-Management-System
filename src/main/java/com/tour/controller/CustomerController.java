package com.tour.controller;

import com.tour.model.*;
import com.tour.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RequestMapping("/customer")
@Controller
public class CustomerController {

    @Autowired
    CustomerRepository repo;

    @Autowired
    PackRepository packRepo;

    @Autowired
    ItineraryRepository itiRepo;

    @Autowired
    BookingRepository bookRepo;

    @Autowired
    PaymentRepository paymentRepo;

    @Autowired
    TripRepository tripRepo;

    @RequestMapping()
    public String login(Model model) {
        return "cust_login";
    }

    @RequestMapping("index")
    public String index(Model model) {
        return "index";
    }

    @RequestMapping("/home")
    public String home(Model model, HttpServletRequest req) {

        model.addAttribute("datalist", packRepo.findAll());
        return "cust_home";
    }

    @RequestMapping("/logout")
    public String logout(Model model, HttpServletRequest req) {
        req.getSession().invalidate();
        return "logout";
    }

    @PostMapping("/login")
    public String show(Login login, Model model, HttpServletRequest request) {
        Optional<Customer> obj = repo.findByEmailIdAndPassword(login.getEmail(), login.getPassword());
        if (obj.isPresent()) {
            model.addAttribute("name", obj.get().getFirstname() + " " + obj.get().getLastname());
            request.getSession().setAttribute("id", obj.get().getId());
            request.getSession().setAttribute("userid", obj.get().getCustomerId());
            request.getSession().setAttribute("usertype", "customer");
            request.getSession().setAttribute("name", obj.get().getFirstname() + " " + obj.get().getLastname());
            return "redirect:/customer/home";
        } else {
            model.addAttribute("msg", "Invalid Login Credentials");
            return "cust_login";
        }
    }

    @RequestMapping("/list")
    public String list(Model model) {
        model.addAttribute("datalist", repo.findAll());
        return "cust";
    }

    @RequestMapping("/create")
    public String create(Model model) {
        return "cust_create";
    }

    @RequestMapping("/save")
    public String save(Customer obj) {
        Optional<Customer> idobj = repo.findTopByOrderByIdDesc();
        String id = null;
        if (idobj.isPresent()) {
            int idnum = Integer.parseInt(idobj.get().getCustomerId().substring(5));
            idnum++;
            id = "CUST0" + idnum;
        } else {
            id = "CUST064901";
        }

        obj.setCustomerId(id);
        repo.save(obj);
        return "redirect:/customer";
    }

    @RequestMapping("/show/{id}")
    public String show(@PathVariable String id, Model model) {
        model.addAttribute("obj", repo.findById(id).get());
        return "cust_show";
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam String id) {
        Optional<Customer> obj = repo.findById(id);
        repo.delete(obj.get());

        return "redirect:/customer/list";
    }

    @RequestMapping("/edit")
    public String edit(Model model, HttpServletRequest req) {
        model.addAttribute("obj", repo.findByCustomerId(req.getSession().getAttribute("userid").toString()).get());
        System.out.println(repo.findByCustomerId(req.getSession().getAttribute("userid").toString()).get());
        return "cust_edit";
    }

    @RequestMapping("/update")
    public String update(Customer obj) {
        repo.save(obj);
        return "redirect:/customer/home/";
    }

    @RequestMapping("/empedit/{id}")
    public String empEdit(@PathVariable String id, Model model) {
        model.addAttribute("obj", repo.findById(id).get());
        return "cust_empedit";
    }

    @RequestMapping("/empupdate")
    public String empUpdate(Customer obj) {
        repo.save(obj);
        return "redirect:/customer/show/" + obj.getId();
    }

    @RequestMapping("/pack/details/{packid}")
    public String home(@PathVariable String packid, Model model, HttpServletRequest req) {

        model.addAttribute("package", packRepo.findByPackageId(packid).get());
        model.addAttribute("itinerarylist", itiRepo.findByPackageId(packid).get());
        model.addAttribute("triplist", tripRepo.findByPackageId(packid).get());

        return "cust_pack_details";
    }

    @RequestMapping("/trip/book/{tripid}")
    public String tripBook(@PathVariable String tripid, Model model, HttpServletRequest req) {
        Trip trip = tripRepo.findById(tripid).get();
        req.getSession().setAttribute("tripid", tripid);
        req.getSession().setAttribute("packageid", trip.getPackageId());
        req.getSession().setAttribute("availableSeats", trip.getAvlSeats());
        model.addAttribute("avlSeats", trip.getAvlSeats());
        return "cust_book";
    }

    @RequestMapping("/trip/book/add")
    public String addItem(Tourist obj, HttpServletRequest req, Model model) throws IOException {

        Booking booking = (Booking) req.getSession().getAttribute("booking");
        Pack pack = packRepo.findByPackageId(req.getSession().getAttribute("packageid").toString()).get();
        int avlSeats = Integer.parseInt(req.getSession().getAttribute("availableSeats").toString());

        if (booking == null) {
            booking = new Booking();
            booking.setCustId(req.getSession().getAttribute("userid").toString());
            booking.setPackageId(req.getSession().getAttribute("packageid").toString());
            booking.setTripId(req.getSession().getAttribute("tripid").toString());
            String strDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
            booking.setBookedDate(strDate);
            if (obj.getTouristType().equals("Adult")) {
                booking.setAdult(1);
                booking.setChild(0);
                booking.setAmount(Double.parseDouble(pack.getAdultPrice()));
            } else {
                booking.setAdult(0);
                booking.setChild(1);
                booking.setAmount(Double.parseDouble(pack.getChildPrice()));
            }
            List<Tourist> tourists = new ArrayList<>();
            tourists.add(obj);
            booking.setTourists(tourists);
        } else {
            int tourist_size = booking.getTourists().size();

            if (tourist_size < avlSeats) {
                if (obj.getTouristType().equals("Adult")) {
                    booking.setAdult(booking.getAdult() + 1);
                    booking.setAmount(booking.getAmount() + Double.parseDouble(pack.getAdultPrice()));
                } else {
                    booking.setChild(booking.getChild() + 1);
                    booking.setAmount(booking.getAmount() + Double.parseDouble(pack.getChildPrice()));
                }
                List<Tourist> tourists = booking.getTourists();
                tourists.add(obj);
                booking.setTourists(tourists);
            } else {
                model.addAttribute("msg", "Tourist count cannot exceed available seats");
            }

        }
        // System.out.println("Booking : " + booking);
        req.getSession().setAttribute("booking", booking);
        model.addAttribute("booking", booking);
        model.addAttribute("avlSeats", avlSeats);

        return "cust_book";
    }

    @RequestMapping("/trip/book/cancel")
    public String tripCancel(HttpServletRequest req, Model model) throws IOException {

        req.getSession().removeAttribute("tripid");
        req.getSession().removeAttribute("packageid");
        req.getSession().removeAttribute("booking");

        return "redirect:/customer/home/";
    }

    @RequestMapping("/trip/book/payment")
    public String confirmPayment(HttpServletRequest req, Model model) {
        Booking booking = (Booking) req.getSession().getAttribute("booking");
        model.addAttribute("booking", booking);
        return "cust_payment";
    }

    @RequestMapping("/trip/book/save")
    public String tripSave(Payment payment, HttpServletRequest req) {

        Booking booking = (Booking) req.getSession().getAttribute("booking");
        if (booking != null) {
            Optional<Booking> idobj = bookRepo.findTopByOrderByIdDesc();
            String id = null;
            if (idobj.isPresent()) {
                int idnum = Integer.parseInt(idobj.get().getBookingId().substring(5));
                idnum++;
                id = "BOOK0" + idnum;
            } else {
                id = "BOOK064901";
            }

            booking.setBookingId(id);
            booking.setBookingStatus("Requested");
            bookRepo.save(booking);
            System.out.println("hello");
            Trip trip = tripRepo.findById(booking.getTripId()).get();
            System.out.println(trip);
            int avl = trip.getAvlSeats() - booking.getAdult() - booking.getChild();
            trip.setAvlSeats(avl);
            System.out.println(trip);
            tripRepo.save(trip);

            payment.setBookingId(booking.getBookingId());
            payment.setPaymentDate(booking.getBookedDate());
            payment.setAmount(booking.getAmount());
            payment.setPaymentStatus("Paid");
            paymentRepo.save(payment);

            req.getSession().removeAttribute("tripid");
            req.getSession().removeAttribute("packageid");
            req.getSession().removeAttribute("booking");
        }

        return "redirect:/booking/mylist/";
    }

}
