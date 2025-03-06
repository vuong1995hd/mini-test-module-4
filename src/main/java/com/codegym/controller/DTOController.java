package com.codegym.controller;

import com.codegym.model.DTO.TypeDTO;
import com.codegym.service.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dto")
public class DTOController {
    @Autowired
    private ITypeService typeService;

    @ModelAttribute("message")
    public String getMessage() {
        return "Hello World from DTOController";
    }

    @GetMapping("")
    public ResponseEntity<Iterable<TypeDTO>> showDTO(Model model) {
        List<TypeDTO> typeDTOs = (List<TypeDTO>) typeService.getType();
        if (typeDTOs.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(typeDTOs, HttpStatus.OK);
//        Iterable<TypeDTO> result = typeService.getType();
//        model.addAttribute("types", result);
//        return "/index";
    }
}
