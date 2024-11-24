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

    // método POST
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ModelAndView buscarIndex(@RequestParam("buscar") String buscar, @RequestParam("nome") String nome) {

        ModelAndView mv = new ModelAndView("index");
        String mensagem = "Resultados da busca por " + buscar;

        if(nome.equals("nomefuncionario")) {
            mv.addObject("funcionarios", fr.findByNomes(buscar));

        }else if(nome.equals("nomedependente")) {
            mv.addObject("dependentes", dr.findByNomesDependentes(buscar));

        }else if(nome.equals("nomecandidato")) {
            mv.addObject("candidatos", cr.findByNomesCandidatos(buscar));

        }else if(nome.equals("titulovaga")) {
            mv.addObject("vagas", vr.findByNomesVaga(buscar));

        }else {
            mv.addObject("funcionarios", fr.findByNomes(buscar));
            mv.addObject("dependentes", dr.findByNomesDependentes(buscar));
            mv.addObject("candidatos", cr.findByNomesCandidatos(buscar));
            mv.addObject("vagas", vr.findByNomesVaga(buscar));
        }

        mv.addObject("mensagem", mensagem);

        return mv;
    }
}
