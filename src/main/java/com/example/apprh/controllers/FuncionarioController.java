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

    // método GET que lista dependentes e detalhes dos funcionário
    @RequestMapping("/detalhes-funcionario/{id}")
    public ModelAndView detalhesFuncionario(@PathVariable("id") long id) {
        Funcionario funcionario = fr.findById(id);
        ModelAndView mv = new ModelAndView("funcionario/detalhes-funcionario");
        mv.addObject("funcionarios", funcionario);

        // lista de dependentes baseada no id do funcionário
        Iterable<Dependente> dependentes = dr.findByFuncionario(funcionario);
        mv.addObject("dependentes", dependentes);

        return mv;
    }

    // método POST que adiciona dependentes
    @RequestMapping(value="/detalhes-funcionario/{id}", method = RequestMethod.POST)
    public String detalhesFuncionarioPost(@PathVariable("id") long id, Dependente dependentes, BindingResult result,
                                          RedirectAttributes attributes) {

        if(result.hasErrors()) {
            attributes.addFlashAttribute("mensagem", "Verifique os campos!");
            return "redirect:/detalhes-funcionario/{id}";
        }

        if(dr.findByCpf(dependentes.getCpf()) != null) {
            attributes.addFlashAttribute("mensagem_erro", "CPF duplicado");
            return "redirect:/detalhes-funcionario/{id}";
        }

        Funcionario funcionario = fr.findById(id);
        dependentes.setFuncionario(funcionario);
        dr.save(dependentes);
        attributes.addFlashAttribute("mensagem", "Dependente adicionado com sucesso");

        return "redirect:/detalhes-funcionario/{id}";
    }

    // método GET que deleta funcionário
    @RequestMapping("/deletarFuncionario")
    public String deletarFuncionario(long id) {
        Funcionario funcionario = fr.findById(id);
        fr.delete(funcionario);

        return "redirect:/funcionarios";
    }

    // Método que atualiza funcionário
    // método GET que chama o formulário de edição do funcionário
    @RequestMapping("/editar-funcionario")
    public ModelAndView editarFuncionario(long id) {
        Funcionario funcionario = fr.findById(id);
        ModelAndView mv = new ModelAndView("funcionario/update-funcionario");
        mv.addObject("funcionario", funcionario);

        return mv;
    }

    // método POST que atualiza o funcionário
    @RequestMapping(value = "/editar-funcionario", method = RequestMethod.POST)
    public String updateFuncionario(@Valid Funcionario funcionario,  BindingResult result, RedirectAttributes attributes){

        fr.save(funcionario);
        attributes.addFlashAttribute("successs", "Funcionário alterado com sucesso!");

        long idLong = funcionario.getId();
        String id = "" + idLong;

        return "redirect:/detalhes-funcionario/" + id;
    }

    // método GET que deleta dependente
    @RequestMapping("/deletarDependente")
    public String deletarDependente(String cpf) {
        Dependente dependente = dr.findByCpf(cpf);

        Funcionario funcionario = dependente.getFuncionario();
        String codigo = "" + funcionario.getId();

        dr.delete(dependente);

        return "redirect:/detalhes-funcionario/" + codigo;
    }
}
