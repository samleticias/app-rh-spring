package com.example.apprh.controllers;

import com.example.apprh.models.Dependente;
import com.example.apprh.models.Funcionario;
import com.example.apprh.repository.DependenteRepository;
import com.example.apprh.repository.FuncionarioRepository;
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
public class FuncionarioController {

    @Autowired
    private FuncionarioRepository fr;

    @Autowired
    private DependenteRepository dr;

    // método GET que chama o formulário para cadastrar funcionários
    @RequestMapping("/cadastrarFuncionario")
    public String form() {
        return "funcionario/form-funcionario";
    }

    // método POST que cadastra funcionários
    @RequestMapping(value = "/cadastrarFuncionario", method = RequestMethod.POST)
    public String form(@Valid Funcionario funcionario, BindingResult result, RedirectAttributes attributes) {

        if (result.hasErrors()) {
            attributes.addFlashAttribute("mensagem", "Verifique os campos");
            return "redirect:/cadastrarFuncionario";
        }

        fr.save(funcionario);
        attributes.addFlashAttribute("mensagem", "Funcionário cadastrado com sucesso!");
        return "redirect:/cadastrarFuncionario";
    }

    // método GET que lista funcionários
    @RequestMapping("/funcionarios")
    public ModelAndView listaFuncionarios() {
        ModelAndView mv = new ModelAndView("funcionario/lista-funcionario");
        Iterable<Funcionario> funcionarios = fr.findAll();
        mv.addObject("funcionarios", funcionarios);
        return mv;
    }

    // GET que lista dependentes e detalhes dos funcionário
    @RequestMapping("/detalhes-funcionario/{id}")
    public ModelAndView detalhesFuncionario(@PathVariable("id") long id) {
        Funcionario funcionario = fr.findById(id);
        ModelAndView mv = new ModelAndView("funcionario/detalhes-funcionario");
        mv.addObject("funcionarios", funcionario);
        return mv;

    }
}
