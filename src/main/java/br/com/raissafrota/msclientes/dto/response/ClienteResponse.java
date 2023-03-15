package br.com.raissafrota.msclientes.dto.response;

import br.com.raissafrota.msclientes.entity.Cliente;
import javax.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteResponse {

    private Long id;
    private String cpf;
    private String nome;
    private Integer idade;

    public static ClienteResponse of(Cliente cliente) {
        var response = new ClienteResponse();
        BeanUtils.copyProperties(cliente,response);
        return response;
    }

}
