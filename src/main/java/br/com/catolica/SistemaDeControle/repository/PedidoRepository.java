package br.com.catolica.SistemaDeControle.repository;


import br.com.catolica.SistemaDeControle.controle_pedidos.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Indica ao Spring que esta é uma interface de repositório.
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    // A mágica acontece aqui!
    // Ao estender JpaRepository, nós ganhamos de graça vários métodos para interagir com o banco:
    // save(), findById(), findAll(), deleteById(), e muitos outros.
    // Não precisamos escrever nenhuma implementação!
}
