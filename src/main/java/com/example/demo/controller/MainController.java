package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import com.example.demo.model.Register;
import com.example.demo.model.Enquiry;
import com.example.demo.repository.RegisterRepository;
import com.example.demo.repository.EnquiryRepository;

@Controller
public class MainController {

    private final RegisterRepository registerRepo;
    private final EnquiryRepository enquiryRepo;

    public MainController(RegisterRepository registerRepo, EnquiryRepository enquiryRepo) {
        this.registerRepo = registerRepo;
        this.enquiryRepo = enquiryRepo;
    }

    // index page
    @GetMapping("/")
    public String index() {
        return "index";
    }

    // register
    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/register")
    public String registerSave(Register register, Model model) {
        registerRepo.save(register);
        model.addAttribute("msg", "Registration Successful!");
        return "success";
    }

    // enquiry
    @GetMapping("/enquiry")
    public String enquiryPage() {
        return "enquiry";
    }

    @PostMapping("/enquiry")
    public String enquirySave(Enquiry enquiry, Model model) {
        enquiryRepo.save(enquiry);
        model.addAttribute("msg", "Enquiry Submitted!");
        return "success";
    }
}
