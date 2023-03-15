package br.com.raissafrota.msclientes.service;

import br.com.raissafrota.msclientes.dto.request.ClienteRequest;
import br.com.raissafrota.msclientes.dto.response.ClienteResponse;
import br.com.raissafrota.msclientes.entity.Cliente;
import br.com.raissafrota.msclientes.repository.ClienteRepository;
import java.util.Optional;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository repository;

    @Transactional
    public ClienteResponse save(ClienteRequest request){
        var cliente = repository.save(Cliente.of(request));
        return  ClienteResponse.of(cliente);
    }

    public Optional<Cliente> getByCPF(String cpf){
        return repository.findByCpf(cpf);
    }
}
