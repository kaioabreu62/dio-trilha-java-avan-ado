package dio.aula_spring_data_jpa_exception.controller;

import dio.aula_spring_data_jpa_exception.handler.BusinessException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cep")
public class CodigoPostalController {
    @GetMapping("/{ibge}")
    public String getCidade(@PathVariable String ibge) {
        if (ibge.equals("3550308"))
            return "São Paulo";
        else
            throw new BusinessException("Não Localizamos nenhuma cidade com o código informado!");
    }
}
