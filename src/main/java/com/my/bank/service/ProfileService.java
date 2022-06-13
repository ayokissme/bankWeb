package com.my.bank.service;

import com.my.bank.dto.CustomerDto;
import com.my.bank.dto.responseBody.CustomerEdited;
import com.my.bank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Service
public class ProfileService {

    @Autowired
    private UserRepository userRepository;

    public ModelAndView getProfileMaV(CustomerDto customer, String viewName) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("customer", customer);
        mav.setViewName(viewName);
        return mav;
    }

    public ModelAndView editProfile(CustomerDto customer, BindingResult bindingResult, @Valid CustomerEdited customerEdited) {
        ModelAndView mav = new ModelAndView();
        if (bindingResult.hasErrors()) {
            mav.setViewName("profile/profile-edit");
            mav.addObject("customer", customerEdited);
            mav.addObject("message", "Error");
            return mav;
        }

        customer.setPhoneNumber(customerEdited.getPhoneNumber());
        customer.setFirstName(customerEdited.getFirstName());
        customer.setLastName(customerEdited.getLastName());
        customer.setGender(customerEdited.getGender());
        customer.setCountry(customerEdited.getCountry());
        userRepository.save(customer);
        return getProfileMaV(customer, "profile/profile");
    }
}
