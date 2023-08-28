package com.animal.main.Controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@Controller
public class LoginController {

    @RequestMapping("/doLogin")
    public String doLogin(Authentication auth) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getName() != null) {
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            for (final GrantedAuthority grantedAuthority : authorities) {
                String authorityName = grantedAuthority.getAuthority();
                if (authorityName.equals("ROLE_ADMIN")) {
                    return "redirect:/admin/";
                } else if (authorityName.equals("ROLE_USER")) {
                    return "redirect:/user/";
                } else {
                    SecurityContextHolder.clearContext();
                    return "LoginForm";
                }
            }
        }
        return "redirect:/LoginForm";
    }

    @RequestMapping("/LoginForm")
    public String LoginForm() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getName() != null) {
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            for (final GrantedAuthority grantedAuthority : authorities) {
                String authorityName = grantedAuthority.getAuthority();
                if (authorityName.equals("ROLE_ADMIN")) {
                    return "redirect:/admin/";
                } else if (authorityName.equals("ROLE_USER")) {
                    return "redirect:/user/";
                } else {
                    return "LoginForm";
                }
            }
        }

        return "LoginForm";
    }

    @RequestMapping("/")
    public String index() {
        return "redirect:/LoginForm";
    }

    @RequestMapping("/logout")
    public String logout(Model model) {
        SecurityContextHolder.clearContext();
        model.addAttribute("msg", "You've been logged out successfully.");
        return "redirect:/LoginForm?logout";
    }

}
