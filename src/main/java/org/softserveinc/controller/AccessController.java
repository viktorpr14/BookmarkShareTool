package org.softserveinc.controller;

import org.softserveinc.domain.User;
import org.softserveinc.domain.UserRole;
import org.softserveinc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

@Controller
public class AccessController {

    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @RequestMapping("/login")
    public String login(Model model, @RequestParam(required=false) String message) {
        model.addAttribute("message", message);
        return "access/login";
    }

    @RequestMapping(value = "/denied")
    public String denied() {
        return "access/denied";
    }

    @RequestMapping(value = "/login/failure")
    public String loginFailure() {
        String message = "Login Failure!";
        return "redirect:/login?message="+message;
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String addUser(User user, HttpServletRequest request, HttpServletResponse responsee) {
        user.setRoles(new ArrayList<UserRole>(){{add(new UserRole(2, "ROLE_USER"));}});
        userService.saveUserIntoDB(user);
        authenticateUserAndSetSession(user, request);
        return "redirect:/userProfile";
    }

    private void authenticateUserAndSetSession(User user, HttpServletRequest request) {
        String username = user.getUsername();
        String password = user.getPassword();
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        request.getSession();
        token.setDetails(new WebAuthenticationDetails(request));
        Authentication authenticatedUser = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String getSingUpPage(Model model) {
        model.addAttribute("user", new User());
        return "access/signup";

    }
}
