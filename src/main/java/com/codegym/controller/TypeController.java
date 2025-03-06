package com.codegym.controller;


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
@RequestMapping("/type")
public class TypeController {

    @Autowired
    private ITypeService typeService;

    @Autowired
    private IComputerService computerService;

    @GetMapping
    public ResponseEntity<Iterable<Type>> findAllType() {
        List<Type> types = (List<Type>) typeService.findAll();
        if (types.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(types, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Type> findTypeById(@PathVariable Long id) {
        Optional<Type> typeOptional = typeService.findById(id);
        if (!typeOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(typeOptional.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Type> saveType(@RequestBody Type type) {
        return new ResponseEntity<>(typeService.save(type), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Type> updateType(@PathVariable Long id, @RequestBody Type type) {
        Optional<Type> typeOptional = typeService.findById(id);
        if (!typeOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        type.setId(typeOptional.get().getId());
        return new ResponseEntity<>(typeService.save(type), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Type> deleteType(@PathVariable Long id) {
        Optional<Type> typeOptional = typeService.findById(id);
        if (!typeOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        typeService.remove(id);
        return new ResponseEntity<>(typeOptional.get(), HttpStatus.OK);
    }

//    @GetMapping("")
//    public ModelAndView getAllTypes() {
//        Iterable<Type> types = typeService.findAll();
//        ModelAndView modelAndView = new ModelAndView("/type/list");
//        modelAndView.addObject("types", types);
//        return modelAndView;
//    }
//    @GetMapping("/create")
//    public ModelAndView createForm() {
//        ModelAndView modelAndView = new ModelAndView("/type/create");
//        modelAndView.addObject("type", new Type());
//        return modelAndView;
//    }
//
//    @PostMapping("/create")
//    public String create(@ModelAttribute("types") Type type,
//                         RedirectAttributes redirectAttributes) {
//        typeService.save(type);
//        redirectAttributes.addFlashAttribute("message", "Create new province successfully");
//        return "redirect:/type";
//    }
//
//    @GetMapping("/update/{id}")
//    public ModelAndView updateForm(@PathVariable Long id) {
//        Optional<Type> type = typeService.findById(id);
//        if (type.isPresent()) {
//            ModelAndView modelAndView = new ModelAndView("/type/update");
//            modelAndView.addObject("type", type.get());
//            return modelAndView;
//        } else {
//            return new ModelAndView("/error_404");
//        }
//    }
//
//    @PostMapping("/update/{id}")
//    public String update(@ModelAttribute("type") Type type,
//                         RedirectAttributes redirect) {
//        typeService.save(type);
//        redirect.addFlashAttribute("message", "Update province successfully");
//        return "redirect:/type";
//    }
//
//    @GetMapping("/view-type/{id}")
//    public ModelAndView viewType(@PathVariable("id") Long id){
//        Optional<Type> typeOptional = typeService.findById(id);
//        if(!typeOptional.isPresent()){
//            return new ModelAndView("/error_404");
//        }
//
//        Iterable<Computer> computers = computerService.findAllByType(typeOptional.get());
//
//        ModelAndView modelAndView = new ModelAndView("/computer/list");
//        modelAndView.addObject("computers", computers);
//        return modelAndView;
//    }
//
//    @GetMapping("/delete/{id}")
//    public String delete(@PathVariable Long id) {
//        Optional<Type> province = typeService.findById(id);
//        if (province.isPresent()) {
//            typeService.remove(id);
//            return "redirect:/type";
//        }
//        return "redirect:/error_404";
//    }


}
