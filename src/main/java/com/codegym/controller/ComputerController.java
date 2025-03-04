package com.codegym.controller;

import com.practicecrud.model.Computer;
import com.practicecrud.model.Type;
import com.practicecrud.service.IComputerService;
import com.practicecrud.service.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/computer")
public class ComputerController {

    @Autowired
    private IComputerService computerService;

    @Autowired
    private ITypeService typeService;

    @ModelAttribute("types")
    public Iterable<Type> listTypes() {
        return typeService.findAll();
    }
    @GetMapping("")
    public ModelAndView listComputers(
            @PageableDefault(size = 5, sort = "name", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<Computer> computers = computerService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("/computer/list");
        modelAndView.addObject("computers", computers);
        return modelAndView;
    }
    @GetMapping("/search")
    public ModelAndView listComputerSearch(@RequestParam("search") Optional<String> search,
                                           @PageableDefault(size = 5, sort = "name", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<Computer> computers ;
        if (search.isPresent()) {
            computers = computerService.findAllByNameContaining(pageable, search.get());
        } else {
            computers = computerService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("/computer/list");
        modelAndView.addObject("computers", computers);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView createComputer() {
        ModelAndView modelAndView = new ModelAndView("/computer/create");
        modelAndView.addObject("computers", new Computer());
        return modelAndView;
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("computers") Computer computer,
                            RedirectAttributes redirectAttributes) {
        computerService.save(computer);
        redirectAttributes.addFlashAttribute("message", "Create new customer successfully");
        return "redirect:/computer";
    }

    @GetMapping("/update/{id}")
    public ModelAndView updateComputer(@PathVariable Long id) {
        Optional<Computer> computer = computerService.findById(id);
        if (computer.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/computer/update");
            modelAndView.addObject("computers", computer.get());
            return modelAndView;
        } else {
            return new ModelAndView("/error_404");
        }
    }
    @PostMapping("/update/{id}")
    public String updateComputer( @ModelAttribute("computers") Computer computer,
                             RedirectAttributes redirectAttributes) {
        computerService.save(computer);
        redirectAttributes.addFlashAttribute("message", "Update customer successfully");
        return "redirect:/computer";
    }

    @GetMapping("/delete/{id}")
    public String deleteComputer(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        computerService.remove(id);
        redirectAttributes.addFlashAttribute("message", "Delete customer successfully");
        return "redirect:/computer";
    }

}
