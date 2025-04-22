package com.microsservico.estoque_preco.connections;

import com.phsbido.constants.RabbitMQConstantes;
import jakarta.annotation.PostConstruct;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConnection {
    private static final String NOME_EXCHANGE = "amq.direct";

    private AmqpAdmin amqpAdmin;

    public RabbitMQConnection(AmqpAdmin amqpAdmin){
        this.amqpAdmin = amqpAdmin;
    }

    private Queue criarFila(String nomeFila){
        return new Queue(nomeFila, true, false, false);
    }

    private DirectExchange criarTrocaDireta(){
        return new DirectExchange(NOME_EXCHANGE, true, false);
    }

    private Binding criarRelacionamento(Queue fila, DirectExchange trocaDireta){
        return new Binding(fila.getName(), Binding.DestinationType.QUEUE, criarTrocaDireta().getName(), fila.getName(), null);
    }

    @PostConstruct
    private void adicionar(){
        Queue filaEstoque = this.criarFila(RabbitMQConstantes.FILA_ESTOQUE);
        Queue filaPreco = this.criarFila(RabbitMQConstantes.FILA_PRECO);

        DirectExchange troca = this.criarTrocaDireta();

        Binding relacionamentoEstoque = this.criarRelacionamento(filaEstoque, troca);
        Binding relacionamentoPreco = this.criarRelacionamento(filaPreco, troca);

        this.amqpAdmin.declareQueue(filaEstoque);
        this.amqpAdmin.declareQueue(filaPreco);

        this.amqpAdmin.declareExchange(troca);

        this.amqpAdmin.declareBinding(relacionamentoEstoque);
        this.amqpAdmin.declareBinding(relacionamentoPreco);
    }
}
