package com.example.christmas_list.controllers;


import com.example.christmas_list.data.WishData;
import com.example.christmas_list.data.WishRepository;
import com.example.christmas_list.models.Wish;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.Errors;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
//@RequestMapping("wish")
public class WishController {


    //TODO: Create wishRepository field
    @Autowired
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
        this.wishRepository.save(newWish);
        return "redirect:/wishlist";
    }

    //TODO: will display the wishIndex template to user when the form redirects to /wishList
    @GetMapping("/wishlist")
    public String displayWishIndex(Model model){
        model.addAttribute("title", "Welcome to Your Wish List");
        model.addAttribute("wishes", wishRepository.findAll());
        return "wishes/wishIndex";
    }

    //TODO: Create a GET handler that will display the remove wish form
    @GetMapping("/remove")
    public String displayRemoveWishForm(Model model){
        model.addAttribute("title", "Remove a Wish");
        model.addAttribute("wishes", wishRepository.findAll());
        return "wishes/removeWish";
    }

    //TODO: Create a POST handler that will process the remove wish form
    @PostMapping("/remove")
    public String processRemoveWishForm(@RequestParam(required = false) int[] wishIds) {

        if (wishIds != null) {
            for (int id : wishIds) {
                wishRepository.deleteById(id);
            }
        }

        return "redirect:/wishlist";
    }


    //TODO: Create a GET handler that will display the edit wish form
    @GetMapping("/edit/{wishId}")
    public String displayEditWishForm(Model model, Wish wish, @PathVariable Integer wishId){

        Optional<Wish> result = wishRepository.findById(wishId);
        if (result.isPresent()) {
            Wish wishToEdit = result.get();
            model.addAttribute("wish", wishToEdit);
            String title = "Edit Wish " + wishToEdit.getName() + " (id=" + wishToEdit.getId() + ")";
            model.addAttribute("title", title );

        }
        return "wishes/editWish";
    }


    //* It is NOT setting the new values for the name, store or description.
    //TODO: Create a POST handler that will process the edit wish form
    @PostMapping("/edit/{wishId}")
    public String processEditWishForm(@ModelAttribute @Valid Wish wish,
                                      Errors errors, Model model, @PathVariable Integer wishId, String name, String store, String description) {

        if(errors.hasErrors()){
            model.addAttribute("error", "Must not be blank");
            return "wishes/editWish";
        }

        if (wishId != null) {
            Optional<Wish> result = wishRepository.findById(wishId);
            if (result.isPresent()) {
                Wish wishToEdit = result.get();
                wishToEdit.setName(name);
                wishToEdit.setStore(store);
                wishToEdit.setDescription(description);
                wishRepository.save(wishToEdit);
            }
            }

            return "redirect:/wishlist";
        }

//----------------------------------------------------------------------------------------


}

