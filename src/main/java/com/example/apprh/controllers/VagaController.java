package com.example.apprh.controllers;

import com.example.apprh.models.Candidato;
import com.example.apprh.models.Vaga;
import com.example.apprh.repository.CandidatoRepository;
import com.example.apprh.repository.VagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class VagaController {

    @Autowired
    private VagaRepository vr;

    @Autowired
    private CandidatoRepository cr;

    // método GET que chama o formulário que cadastra vaga
    @RequestMapping("/cadastrarVaga")
    public String form() {
        return "vaga/form-vaga";
    }

    // método POST que cadastra a vaga
    @RequestMapping(value = "/cadastrarVaga", method = RequestMethod.POST)
    public String form(@Valid Vaga vaga, BindingResult result, RedirectAttributes attributes) {

        if (result.hasErrors()) {
            attributes.addFlashAttribute("mensagem", "Verifique os campos...");
            return "redirect:/cadastrarVaga";
        }

        vr.save(vaga);
        attributes.addFlashAttribute("mensagem", "Vaga cadastrada com sucesso!");
        return "redirect:/cadastrarVaga";
    }

    // método GET que lista as vagas
    @RequestMapping("/vagas")
    public ModelAndView listaVagas() {
        ModelAndView mv = new ModelAndView("vaga/lista-vaga");
        Iterable<Vaga> vagas = vr.findAll();
        mv.addObject("vagas", vagas);
        return mv;
    }

    // método GET que mostra os detalhes da vaga e os candidatos
    @RequestMapping("/vaga/{codigo}")
    public ModelAndView detalhesVaga(@PathVariable("codigo") long codigo) {
        Vaga vaga = vr.findByCodigo(codigo);
        ModelAndView mv = new ModelAndView("vaga/detalhes-vaga");
        mv.addObject("vaga", vaga);

        Iterable<Candidato> canditados = cr.findByVaga(vaga);
        mv.addObject("candidatos", canditados);

        return mv;

    }

}
