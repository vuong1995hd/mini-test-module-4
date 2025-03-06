package com.codegym.controller;


import com.codegym.model.Computer;
import com.codegym.model.Type;
import com.codegym.service.IComputerService;
import com.codegym.service.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/computer")
public class ComputerController {

    @Autowired
    private IComputerService computerService;

    @Autowired
    private ITypeService typeService;

    @ModelAttribute("computerType")
    public Iterable<Type> listTypes() {
        return typeService.findAll();
    }

    @GetMapping("")
    public ResponseEntity<Iterable<Computer>> findAllComputer () {
        List<Computer> computers = (List<Computer>) computerService.findAll();
        if (computers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(computers, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Computer> findComputerById(@PathVariable Long id) {
        Optional<Computer> computerOptional = computerService.findById(id);
        if (!computerOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(computerOptional.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Computer> saveComputer(@RequestBody Computer computer) {
        return new ResponseEntity<>(computerService.save(computer), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Computer> updateComputer(@PathVariable Long id, @RequestBody Computer computer) {
        Optional<Computer> computerOptional = computerService.findById(id);
        if (!computerOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        computer.setId(computerOptional.get().getId());
        return new ResponseEntity<>(computerService.save(computer), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Computer> deleteComputer(@PathVariable Long id) {
        Optional<Computer> computerOptional = computerService.findById(id);
        if (!computerOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        computerService.remove(id);
        return new ResponseEntity<>(computerOptional.get(), HttpStatus.OK);
    }

//    @GetMapping("")
//    public ModelAndView listComputers(
//            @PageableDefault(size = 5, sort = "name", direction = Sort.Direction.ASC) Pageable pageable) {
//        Page<Computer> computers = computerService.findAll(pageable);
//        ModelAndView modelAndView = new ModelAndView("/computer/list");
//        modelAndView.addObject("computers", computers);
//        return modelAndView;
//    }
//    @GetMapping("/search")
//    public ModelAndView listComputerSearch(@RequestParam("search") Optional<String> search,
//                                           @PageableDefault(size = 5, sort = "name", direction = Sort.Direction.ASC) Pageable pageable) {
//        Page<Computer> computers ;
//        if (search.isPresent()) {
//            computers = computerService.findAllByNameContaining(pageable, search.get());
//        } else {
//            computers = computerService.findAll(pageable);
//        }
//        ModelAndView modelAndView = new ModelAndView("/computer/list");
//        modelAndView.addObject("computers", computers);
//        return modelAndView;
//    }
//
//    @GetMapping("/create")
//    public ModelAndView createComputer() {
//        ModelAndView modelAndView = new ModelAndView("/computer/create");
//        modelAndView.addObject("computers", new Computer());
//        return modelAndView;
//    }
//
//    @PostMapping("/create")
//    public String create(@ModelAttribute("computers") Computer computer,
//                            RedirectAttributes redirectAttributes) {
//        computerService.save(computer);
//        redirectAttributes.addFlashAttribute("message", "Create new customer successfully");
//        return "redirect:/computer";
//    }
//
//    @GetMapping("/update/{id}")
//    public ModelAndView updateComputer(@PathVariable Long id) {
//        Optional<Computer> computer = computerService.findById(id);
//        if (computer.isPresent()) {
//            ModelAndView modelAndView = new ModelAndView("/computer/update");
//            modelAndView.addObject("computers", computer.get());
//            return modelAndView;
//        } else {
//            return new ModelAndView("/error_404");
//        }
//    }
//    @PostMapping("/update/{id}")
//    public String updateComputer( @ModelAttribute("computers") Computer computer,
//                             RedirectAttributes redirectAttributes) {
//        computerService.save(computer);
//        redirectAttributes.addFlashAttribute("message", "Update customer successfully");
//        return "redirect:/computer";
//    }
//
//    @GetMapping("/delete/{id}")
//    public String deleteComputer(@PathVariable Long id, RedirectAttributes redirectAttributes) {
//        computerService.remove(id);
//        redirectAttributes.addFlashAttribute("message", "Delete customer successfully");
//        return "redirect:/computer";
//    }

}
