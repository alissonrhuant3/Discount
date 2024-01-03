package com.projeto.discount.resources;

import com.projeto.discount.entities.Store;
import com.projeto.discount.services.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;

@Controller
public class StoreResource {

    private static String caminhoImagens = "C:\\Users\\1118346673\\Documents\\imagens\\";


    @Autowired
    private StoreService ss;

    //Retorna pagina inicial index!
    @GetMapping("/")
    public ModelAndView store(){
        ModelAndView mv = new ModelAndView("index");

        Iterable<Store> store = ss.findAll();
        mv.addObject("store",store);
        return mv;
    }

    //Retorna pagina dos jogos multiplayer!
    @GetMapping("/multiplayer")
    public ModelAndView storeMultiplayer(){
        ModelAndView mv = new ModelAndView("page/multiplayer");

        Iterable<Store> store = ss.findByMultiplayer();
        mv.addObject("store",store);
        return mv;
    }

    //Retorna pagina dos jogos singleplayer!
    @GetMapping("/singleplayer")
    public ModelAndView storeSingleplayer(){
        ModelAndView mv = new ModelAndView("page/singleplayer");

        Iterable<Store> store = ss.findBySingleplayer();
        mv.addObject("store",store);
        return mv;
    }

    //Retorna pagina dos jogos coop!
    @GetMapping("/cooperativo")
    public ModelAndView storeCooperativo(){
        ModelAndView mv = new ModelAndView("page/cooperativo");

        Iterable<Store> store = ss.findByCooperativo();
        mv.addObject("store",store);
        return mv;
    }

    //Retorna pagina inserirjogo
    @GetMapping(value = "/inserirjogo")
    public String inserirJogos(){
        return "page/inserirjogo";
    }

    //Método para adicionar novo jogo com upload de imagem!
    @PostMapping(value = "/inserirjogo")
    public String inserirJogos(@Validated Store store, @RequestParam("file") MultipartFile file) {
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
        return "redirect:/";
    }


    //Método para mostrar imagem se existir!
    @GetMapping("/mostrarImagem/{imagem}")
    @ResponseBody
    public byte[] retornarImagem(@PathVariable("imagem") String imagem) throws IOException {
//		System.out.println(imagem);
        File imagemArquivo = new File(caminhoImagens + imagem);//imagem == store.nomeImagem!
        if (imagem != null || imagem.trim().length() > 0) {
            return Files.readAllBytes(imagemArquivo.toPath());
        }
        return null;
    }

}
