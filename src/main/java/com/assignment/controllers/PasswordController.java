package com.assignment.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.assignment.entities.Account;
import com.assignment.repositories.AccountRepository;
import com.assignment.services.MailService;
import com.assignment.services.ParamService;
import com.assignment.services.VerifyService;

@Controller
@RequestMapping("/account")
public class PasswordController {

    @Autowired
    AccountRepository accountRepo;
    
    @Autowired
    ParamService paramService;
    
    @Autowired
    MailService mailService;
    
    @Autowired
    VerifyService verifyService;
    
    @RequestMapping("forgot-password/form")
    public String getForgotPasswordForm() {
        return "account/forgot-password";
    }
    
    @RequestMapping("forgot-password")
    public String forgotPassword(Model model) {
        
        String username = paramService.getString("username", "");
        Account account =accountRepo.findByUsername(username);
        if(account==null) {
            model.addAttribute("fPasswordErrorMessage","Tài khoản không tồn tại");
        }else {
            
            if(!account.isActivated()) {
                mailService.sendEmailRegister(account.getEmail(), account.getFullname(), account.getToken());
                return "redirect:/account/verify-account";
            }
            
            String verifyCode = verifyService.createVerifyCode();
            String token = verifyService.createToken();
            account.setVerifyCode(verifyCode);
            account.setToken(token);            
            accountRepo.save(account);
            mailService.sendEmailForgotPassword(account.getEmail(), account.getFullname(), verifyCode);
            return "redirect:/account/verify-password/" + token;
        }
        
        return "account/forgot-password";
    }
    
    @RequestMapping("verify-password/{token}")
    public String getVerifyPasswordForm(Model model, @PathVariable("token") String token) {
        Account account = accountRepo.findByToken(token);
        if(account==null) {
            return "redirect:/account/verify-account/error";
        }
        return "account/verify-password";
    }
    
    @RequestMapping("verify-password")
    public String verifyPassword(Model model) {
        String verifyCode = paramService.getString("verifyCode", "");
        Account account = accountRepo.findByVerifyCode(verifyCode);
        if(account == null) {
            model.addAttribute("fPasswordErrorMessage","Mã xác thực không chính xác");
        }else {
            account.setVerifyCode(null);
            accountRepo.save(account);
            return "redirect:/account/verify-account/" + account.getToken();
        }
        return "account/verify-password";
    }
    
    @RequestMapping("change-password/form")
    public String getChangePasswordForm() {
        return "account/change-password";
    }
    
    @RequestMapping("change-password")
    public String changePassword() {
        return "account/change-password";
    }
    
}
