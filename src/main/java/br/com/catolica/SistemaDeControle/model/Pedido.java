package br.com.seuprojeto.controlepedidos.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_mesa", nullable = false)
    private Integer numeroMesa;

    @Column(name = "descricao_pedido", length = 500)
    private String descricao;

    @Column(name = "status")
    private String status;

    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao = LocalDateTime.now();
}

