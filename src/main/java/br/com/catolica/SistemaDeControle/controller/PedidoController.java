package br.com.catolica.SistemaDeControle.controller;

import br.com.catolica.SistemaDeControle.controle_pedidos.model.Pedido;
import br.com.catolica.SistemaDeControle.controle_pedidos.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Indica que esta classe é um Controller REST e retorna dados (JSON/XML )
@RequestMapping("/api/pedidos") // Define o caminho base para todos os endpoints deste Controller
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    // POST /api/pedidos
    // Cria um novo pedido
    @PostMapping
    public ResponseEntity<Pedido> criarPedido(@RequestBody Pedido pedido) {
        Pedido novoPedido = pedidoService.salvar(pedido);
        // Retorna o status 201 CREATED
        return new ResponseEntity<>(novoPedido, HttpStatus.CREATED);
    }

    // GET /api/pedidos
    // Lista todos os pedidos
    @GetMapping
    public ResponseEntity<List<Pedido>> listarPedidos() {
        List<Pedido> pedidos = pedidoService.buscarTodos();
        // Retorna o status 200 OK
        return ResponseEntity.ok(pedidos);
    }

    // GET /api/pedidos/{id}
    // Busca um pedido por ID
    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscarPedidoPorId(@PathVariable Long id) {
        return pedidoService.buscarPorId(id)
                .map(ResponseEntity::ok) // Se encontrar, retorna 200 OK
                .orElseGet(() -> ResponseEntity.notFound().build()); // Se não encontrar, retorna 404 NOT FOUND
    }

    // PUT /api/pedidos/{id}
    // Atualiza um pedido existente
    @PutMapping("/{id}")
    public ResponseEntity<Pedido> atualizarPedido(@PathVariable Long id, @RequestBody Pedido pedido) {
        try {
            Pedido pedidoAtualizado = pedidoService.atualizar(id, pedido);
            return ResponseEntity.ok(pedidoAtualizado); // Retorna 200 OK
        } catch (RuntimeException e) {
            // No futuro, vamos melhorar este tratamento de erro
            return ResponseEntity.notFound().build(); // Retorna 404 NOT FOUND
        }
    }

    // DELETE /api/pedidos/{id}
    // Deleta um pedido
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPedido(@PathVariable Long id) {
        pedidoService.deletar(id);
        // Retorna o status 204 NO CONTENT (sucesso sem corpo de resposta)
        return ResponseEntity.noContent().build();
    }
}
