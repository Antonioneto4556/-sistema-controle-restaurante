package br.com.catolica.SistemaDeControle.service;

import br.com.catolica.SistemaDeControle.controle_pedidos.model.Pedido;
import br.com.catolica.SistemaDeControle.controle_pedidos.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service // Indica ao Spring que esta classe é um componente de serviço.
public class PedidoService {

    // Injeção de Dependência: O Spring cria e gerencia a instância do Repository para nós.
    @Autowired
    private PedidoRepository pedidoRepository;

    // 1. CREATE (Criar/Salvar)
    public Pedido salvar(Pedido pedido) {
        // Aqui poderia haver regras de negócio, como verificar se a mesa existe, etc.
        return pedidoRepository.save(pedido);
    }

    // 2. READ (Buscar Todos)
    public List<Pedido> buscarTodos() {
        return pedidoRepository.findAll();
    }

    // 3. READ (Buscar por ID)
    public Optional<Pedido> buscarPorId(Long id) {
        return pedidoRepository.findById(id);
    }

    // 4. UPDATE (Atualizar)
    public Pedido atualizar(Long id, Pedido pedidoAtualizado) {
        // Busca o pedido existente
        Optional<Pedido> pedidoExistente = pedidoRepository.findById(id);

        if (pedidoExistente.isPresent()) {
            Pedido pedido = pedidoExistente.get();

            // Atualiza apenas os campos que podem ser alterados
            pedido.setNumeroMesa(pedidoAtualizado.getNumeroMesa());
            pedido.setDescricao(pedidoAtualizado.getDescricao());
            pedido.setStatus(pedidoAtualizado.getStatus());

            // Salva e retorna o pedido atualizado
            return pedidoRepository.save(pedido);
        } else {
            // No futuro, vamos lançar uma exceção personalizada aqui.
            // Por enquanto, retornamos null ou lançamos uma exceção padrão.
            throw new RuntimeException("Pedido não encontrado com o ID: " + id);
        }
    }

    // 5. DELETE (Deletar)
    public void deletar(Long id) {
        pedidoRepository.deleteById(id);
    }
}
