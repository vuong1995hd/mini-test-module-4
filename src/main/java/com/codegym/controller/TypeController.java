package com.codegym.controller;


import com.codegym.model.Computer;
import com.codegym.model.Type;
import com.codegym.service.IComputerService;
import com.codegym.service.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/type")
public class TypeController {

    @Autowired
    private ITypeService typeService;

    @Autowired
    private IComputerService computerService;

    @GetMapping("")
    public ModelAndView getAllTypes() {
        Iterable<Type> types = typeService.findAll();
        ModelAndView modelAndView = new ModelAndView("/type/list");
        modelAndView.addObject("types", types);
        return modelAndView;
    }
    @GetMapping("/create")
    public ModelAndView createForm() {
        ModelAndView modelAndView = new ModelAndView("/type/create");
        modelAndView.addObject("type", new Type());
        return modelAndView;
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("types") Type type,
                         RedirectAttributes redirectAttributes) {
        typeService.save(type);
        redirectAttributes.addFlashAttribute("message", "Create new province successfully");
        return "redirect:/type";
    }

    @GetMapping("/update/{id}")
    public ModelAndView updateForm(@PathVariable Long id) {
        Optional<Type> type = typeService.findById(id);
        if (type.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/type/update");
            modelAndView.addObject("type", type.get());
            return modelAndView;
        } else {
            return new ModelAndView("/error_404");
        }
    }

    @PostMapping("/update/{id}")
    public String update(@ModelAttribute("type") Type type,
                         RedirectAttributes redirect) {
        typeService.save(type);
        redirect.addFlashAttribute("message", "Update province successfully");
        return "redirect:/type";
    }

    @GetMapping("/view-type/{id}")
    public ModelAndView viewType(@PathVariable("id") Long id){
        Optional<Type> typeOptional = typeService.findById(id);
        if(!typeOptional.isPresent()){
            return new ModelAndView("/error_404");
        }

        Iterable<Computer> computers = computerService.findAllByType(typeOptional.get());

        ModelAndView modelAndView = new ModelAndView("/computer/list");
        modelAndView.addObject("computers", computers);
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        Optional<Type> province = typeService.findById(id);
        if (province.isPresent()) {
            typeService.remove(id);
            return "redirect:/type";
        }
        return "redirect:/error_404";
    }


}
