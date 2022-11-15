package com.assignment.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.assignment.entities.Account;
import com.assignment.repositories.AccountRepository;
import com.assignment.services.MailService;
import com.assignment.services.ParamService;
import com.assignment.services.S3Service;
import com.assignment.services.VerifyService;
import com.assignment.utilities.HashUtil;

@Controller
@RequestMapping("/account")
public class RegisterController {

    @Autowired
    AccountRepository accountRepo;

    @Autowired
    S3Service s3Service;

    @Autowired
    ParamService paramService;

    @Autowired
    MailService mailService;

    @Autowired
    VerifyService verifyService;

    @GetMapping("/register")
    public String getForm(Model model) {
        Account acc = new Account();
        model.addAttribute("acc", acc);
        return "account/register";
    }

    @PostMapping("/register")
    public String register(Model model,
            @Valid @ModelAttribute("acc") Account acc,
            BindingResult result,
            @RequestParam(name = "file", required = false) MultipartFile multipartFile) {

        boolean validate = true;
        String rePassword = paramService.getString("rePassword", "");

        if (!rePassword.equalsIgnoreCase(acc.getPassword())) {
            model.addAttribute("repasswordErrorMessage", "Mật khẩu không khớp");
            validate = false;
        }
        if (accountRepo.existsByUsername(acc.getUsername().trim()) != 0) {
            model.addAttribute("usernameErrorMessage", "Tên đăng nhập đã được sử dụng");
            validate = false;
        }
        if (accountRepo.existsByEmail(acc.getEmail().trim()) != 0) {
            model.addAttribute("emailErrorMessage", "Email đã được sử dụng");
            validate = false;
        }
        if (!result.hasErrors() && validate == true) {
            if (!multipartFile.getOriginalFilename().equals("")) {
                acc.setImage(s3Service.saveFile(multipartFile));
            }

            String token = verifyService.createToken();
            acc.setToken(token);
            String hashedPass = HashUtil.hash(acc.getPassword());
            acc.setPassword(hashedPass);
            
            this.accountRepo.save(acc);
            this.mailService.sendEmailRegister(acc.getEmail(), acc.getFullname(), token);
            return "redirect:/account/verify-account";

        }
        return "account/register";
    }

    @RequestMapping("/verify-account")
    public String verifyAccountNotification(Model model) {
        model.addAttribute("verifyMessage", "Vui lòng kiểm tra email để xác thực tài khoản");
        return "account/verify-account";
    }

    @RequestMapping("/verify-account/{token}")
    public String verifyAccount(Model model, @PathVariable("token") String token) {
        Account acc = accountRepo.findByToken(token);
        if (acc == null) {
            model.addAttribute("verifyMessage", "Link không hợp lệ");
            return "account/verify-account";
        }
        if (!acc.isActivated()) {
            acc.setToken(null);
            acc.setActivated(true);
            accountRepo.save(acc);
            model.addAttribute("verifyMessage", "Xác thực tài khoản thành công");
            return "account/verify-account";
        }
        String newPassword = verifyService.createVerifyCode();
        acc.setToken(null);
        acc.setPassword(newPassword);
        accountRepo.save(acc);
        mailService.sendEmailNewPassword(acc.getEmail(), acc.getFullname(), newPassword);
        model.addAttribute("verifyMessage", "Mật khẩu mới đã được gửi đến mail của bạn");
        return "account/verify-account";
    }

}
