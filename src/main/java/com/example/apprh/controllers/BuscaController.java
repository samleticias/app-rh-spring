package com.example.apprh.controllers;

import com.example.apprh.repository.CandidatoRepository;
import com.example.apprh.repository.DependenteRepository;
import com.example.apprh.repository.FuncionarioRepository;
import com.example.apprh.repository.VagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BuscaController {

    @Autowired
    private FuncionarioRepository fr;

    @Autowired
    private VagaRepository vr;

    @Autowired
    private DependenteRepository dr;

    @Autowired
    private CandidatoRepository cr;

    //método GET
    @RequestMapping("/")
    public ModelAndView abrirIndex() {
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }

}
