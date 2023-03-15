package br.com.raissafrota.msclientes.controller;

import br.com.raissafrota.msclientes.dto.request.ClienteRequest;
import br.com.raissafrota.msclientes.service.ClienteService;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
@Slf4j
public class ClienteController {

    private final ClienteService service;

    @GetMapping
    public String status(){
        log.info("Obtendo o status do microsservice de Clientes!");
        return "Aplicação subiu! Está tudo ok!";
    }

    @PostMapping
    public ResponseEntity save(@RequestBody ClienteRequest request){
        var clienteResponse = service.save(request);
        URI headerLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .query("cpf={cpf}")
                .buildAndExpand(clienteResponse.getCpf())
                .toUri();
        return ResponseEntity.created(headerLocation).build();
    }

    @GetMapping(params = "cpf")
    public ResponseEntity dadosCliente(@RequestParam("cpf") String cpf){
        var cliente = service.getByCPF(cpf);
        if(cliente.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cliente);
    }

}
