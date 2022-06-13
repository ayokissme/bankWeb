package com.my.bank.controller;

import com.my.bank.dto.CustomerDto;
import com.my.bank.dto.responseBody.CustomerEdited;
import com.my.bank.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @GetMapping
    private ModelAndView getProfile(@AuthenticationPrincipal CustomerDto customer) {
        return profileService.getProfileMaV(customer, "profile/profile");
    }

    @GetMapping("/edit")
    private ModelAndView getEditPage(@AuthenticationPrincipal CustomerDto customer) {
        return profileService.getProfileMaV(customer, "profile/profile-edit");
    }

    @PatchMapping("/edit")
    private ModelAndView editPage(@Valid CustomerEdited customerEdited,
                                  BindingResult bindingResult,
                                  @AuthenticationPrincipal CustomerDto customer) {
        return profileService.editProfile(customer, bindingResult, customerEdited);
    }
}
