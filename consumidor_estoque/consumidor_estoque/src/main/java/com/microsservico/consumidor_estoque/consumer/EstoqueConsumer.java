package com.microsservico.consumidor_estoque.consumer;

import com.phsbido.constants.RabbitMQConstantes;
import com.phsbido.dto.EstoqueDTO;
import com.phsbido.dto.PrecoDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class EstoqueConsumer {

    @RabbitListener(queues = RabbitMQConstantes.FILA_ESTOQUE)
    public void consumirEstoque(EstoqueDTO estoqueDTO){
        System.out.println(estoqueDTO.codigoProduto);
        System.out.println(estoqueDTO.quantidade);
        System.out.println("---------");
    }

    @RabbitListener(queues = RabbitMQConstantes.FILA_PRECO)
    public void consumirEstoque(PrecoDTO precoDTO){
        System.out.println(precoDTO.codigoProduto);
        System.out.println(precoDTO.preco);
        System.out.println("---------");
    }

}
