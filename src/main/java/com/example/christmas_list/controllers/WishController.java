package com.example.christmas_list.controllers;


import com.example.christmas_list.data.WishData;
import com.example.christmas_list.data.WishRepository;
import com.example.christmas_list.models.Wish;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.Errors;

import java.util.ArrayList;
import java.util.List;

@Controller
//@RequestMapping("wish")
public class WishController {

    //TODO: Create an ArrayList that will hold he wish objects
    private static List<Wish> wishes = new ArrayList<>();

    //TODO: Create wishRepository field
    private WishRepository wishRepository;

    //TODO: Create a GET request handler that will display this create wish form
    //This form will display at:  localhost:8080/wish
    @GetMapping("/create")
    public String displayCreateWishForm(Model model){
        model.addAttribute("title", "Make a Wish");
        model.addAttribute(new Wish());
        return "wishes/createWish";
    }

    //TODO: Create a POST request handler that will process the form submission
    //The form is sent to the post handler the lives at "/wish" and the handler will process
    //the form and return the wishIndex template.
    @PostMapping("/create")
    public String processCreateWishForm(@ModelAttribute @Valid Wish newWish,
                                        Errors errors, Model model){
        if(errors.hasErrors()){
            model.addAttribute("title", "Make a Wish");
            return "wishes/createWish";
        }
        wishRepository.save(newWish);
        return "redirect:/wishlist";
    }

    //TODO: will display the wishIndex template to user when the form redirects to /wishList
    @GetMapping("/wishlist")
    public String displayWishIndex(Model model, Wish wish){
        model.addAttribute("title", "Welcome to Your Wish List");
        model.addAttribute(new Wish());
        model.addAttribute("wishes", wishRepository.findAll());
        return "wishes/wishIndex";
    }

}

