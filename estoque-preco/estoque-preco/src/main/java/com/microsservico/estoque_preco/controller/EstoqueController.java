package com.microsservico.estoque_preco.controller;

import com.microsservico.estoque_preco.service.RabbitMQService;
import com.phsbido.constants.RabbitMQConstantes;
import com.phsbido.dto.EstoqueDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "estoque")
public class EstoqueController {

    @Autowired
    private RabbitMQService rabbitMQService;

    @PutMapping
    private ResponseEntity alterarEstoque(@RequestBody EstoqueDTO estoqueDTO){
        System.out.println(estoqueDTO.codigoProduto);
        this.rabbitMQService.enviarMensagem(RabbitMQConstantes.FILA_ESTOQUE, estoqueDTO);
        return new ResponseEntity(HttpStatus.OK);
    }

}
