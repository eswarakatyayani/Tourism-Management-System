package com.tour.controller;

import com.tour.model.Booking;
import com.tour.model.Payment;
import com.tour.model.Trip;
import com.tour.repository.BookingRepository;
import com.tour.repository.PaymentRepository;
import com.tour.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@RequestMapping("/booking")
@Controller
public class BookingController {

    @Autowired
    BookingRepository repo;

    @Autowired
    TripRepository tripRepo;

    @Autowired
    PaymentRepository payRepo;

    static String ymd_dmy(String date) throws ParseException {
        String arr[] = date.split("-");
        return arr[2] + "-" + arr[1] + "-" + arr[0];
    }

    @RequestMapping("/list")
    public String home(Model model) {
        // List<Booking> bookings = (List<Booking>) repo.findAll();
        List<Booking> bookings = repo.findByBookingStatusNot("Pending").get();
        bookings.forEach(b -> b.setPayment(payRepo.findByBookingId(b.getBookingId()).get()));
        model.addAttribute("datalist", bookings);
        return "booking_list";
    }

    @RequestMapping("/mylist")
    public String home(Model model, HttpServletRequest request) {
        List<Booking> bookings = repo.findByCustId(request.getSession().getAttribute("userid").toString()).get();
        bookings.forEach(b -> b.setPayment(payRepo.findByBookingId(b.getBookingId()).get()));
        model.addAttribute("datalist", bookings);
        return "booking_mylist";
    }

    @RequestMapping("/accept/{id}")
    public String userAccept(@PathVariable String id) {
        Optional<Booking> obj = repo.findById(id);

        Booking booking = obj.get();
        booking.setBookingStatus("Requested");
        booking.setTripPosponded(false);
        repo.save(booking);

        return "redirect:/booking/mylist";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        Optional<Booking> obj = repo.findById(id);

        Booking booking = obj.get();
        Trip trip = tripRepo.findById(booking.getTripId()).get();
        System.out.println(trip);
        int avl = trip.getAvlSeats() + booking.getAdult() + booking.getChild();
        trip.setAvlSeats(avl);
        System.out.println(trip);
        tripRepo.save(trip);

        Payment payment = payRepo.findByBookingId(booking.getBookingId()).get();
        payment.setPaymentStatus("Refunded");
        payRepo.save(payment);

        booking.setBookingStatus("Cancelled");
        booking.setTripPosponded(false);
        repo.save(booking);

        return "redirect:/booking/mylist";
    }

    @RequestMapping("/delete1/{id}")
    public String delete1(@PathVariable String id) {
        Optional<Booking> obj = repo.findById(id);

        Booking booking = obj.get();
        Trip trip = tripRepo.findById(booking.getTripId()).get();
        System.out.println(trip);
        int avl = trip.getAvlSeats() + booking.getAdult() + booking.getChild();
        trip.setAvlSeats(avl);
        System.out.println(trip);
        tripRepo.save(trip);

        Payment payment = payRepo.findByBookingId(booking.getBookingId()).get();
        payment.setPaymentStatus("Refunded");
        payRepo.save(payment);

        booking.setBookingStatus("Cancelled");
        booking.setTripPosponded(false);
        repo.save(booking);

        return "redirect:/booking/list";
    }

    @RequestMapping("/confirm/{id}")
    public String empUpdate(@PathVariable String id) {
        Optional<Booking> obj = repo.findById(id);
        if (obj.isPresent()) {
            Booking booking = obj.get();
            booking.setBookingStatus("Confirmed");
            repo.save(booking);

        }
        return "redirect:/booking/list";
    }

    @RequestMapping("/my-payment/{id}")
    public String viewPayment(@PathVariable String id, Model model) {
        Payment payment = payRepo.findByBookingId(id).get();
        model.addAttribute("payment", payment);
        return "cust_payment_view";
    }

    @RequestMapping("/payment/{id}")
    public String adminViewPayment(@PathVariable String id, Model model) {
        Payment payment = payRepo.findByBookingId(id).get();
        model.addAttribute("payment", payment);
        return "payment_view";
    }
}
