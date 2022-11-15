package com.assignment.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ResolvableType;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.assignment.entities.Account;
import com.assignment.repositories.AccountRepository;
import com.assignment.services.MailService;
import com.assignment.services.ParamService;
import com.assignment.services.SessionService;
import com.assignment.utilities.HashUtil;

@Controller
@RequestMapping("/account")
public class LoginController {

    @Autowired
    AccountRepository accountRepo;

    @Autowired
    SessionService session;

    @Autowired
    ParamService paramService;

    @Autowired
    MailService mailService;

    @GetMapping("/login")
    public String getForm(Model model) {
        Account acc = new Account();
        model.addAttribute("acc", acc);
        return "account/login";
    }

    @PostMapping("/login")
    public String login(Model model) {

        String email = paramService.getString("email", "");
        String password = paramService.getString("password", "");

        Account account = this.accountRepo.findByEmail(email);
        if (account == null) {
            model.addAttribute("loginErrorMessage", "Sai thông tin đăng nhập");
        } else {
            boolean checkPassword = HashUtil.verify(password, account.getPassword());
            if (!checkPassword) {
                model.addAttribute("loginErrorMessage", "Sai thông tin đăng nhập");
            } else {
                if (!account.isActivated()) {
                    mailService.sendEmailRegister(account.getEmail(), account.getFullname(), account.getToken());
                    return "redirect:/account/verify-account";
                }
                session.set("user", account);
                session.set("role", account.getAdmin());
                return "redirect:/home";
            }
        }
        return "account/login";
    }

    @RequestMapping("/logout")
    public String logout(Model model) {
        session.remove("user");
        session.remove("role");
        session.remove("admin");
        return "redirect:/home";
    }

}