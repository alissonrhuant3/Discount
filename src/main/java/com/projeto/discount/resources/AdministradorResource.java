package com.projeto.discount.resources;

import com.projeto.discount.entities.Store;
import com.projeto.discount.services.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Controller
public class AdministradorResource {

    private static String caminhoImagens = "C:\\Users\\1118346673\\Documents\\imagens\\";


    @Autowired
    private StoreService ss;

    //Retorna pagina Administrador!
    @RequestMapping("/administrador")
    public ModelAndView store() {
        ModelAndView mv = new ModelAndView("page/administrador");

        Iterable<Store> store = ss.findAll();
        mv.addObject("store", store);
        return mv;
    }

    //Deletar Jogo por ID!
    @RequestMapping("/administrador/{codigoGame}/excluir")
    public String excluirJogo(@PathVariable long codigoGame) {
        Store store = ss.findByCodigoGame(codigoGame);
        ss.deleteByCodigoGame(store.getCodigoGame());
        return "redirect:/administrador";
    }

    //Retorna pagina Alterar jogo!
    @GetMapping(value="/alterar/{codigoGame}")
    public ModelAndView formAlterar(@PathVariable("codigoGame") long codigoGame) {
        Store store = ss.findByCodigoGame(codigoGame);
        ModelAndView mv = new ModelAndView("page/editarjogo");
        mv.addObject("store", store);
        return mv;
    }

    //Confirma alteração do jogo!
    @PostMapping(value="/alterar/{codigoGame}")
    public String alterarJogo(@Validated Store store, BindingResult result, RedirectAttributes attributes, @RequestParam("file") MultipartFile file) {
        ss.save(store);

        try {
            if(!file.isEmpty()) {
                byte[] bytes = file.getBytes();
                java.nio.file.Path caminho = Paths.get(caminhoImagens+ String.valueOf(store.getCodigoGame()) +file.getOriginalFilename());
                Files.write(caminho, bytes);

                store.setNomeImagem(String.valueOf(store.getCodigoGame())+file.getOriginalFilename());
                ss.save(store);
            }
        }catch(IOException e) {
            e.printStackTrace();
        }

        return "redirect:/administrador";
    }
}
