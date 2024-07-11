package one.digitalinnovation.gof.service.impl;

import one.digitalinnovation.gof.handlers.ClienteInsertionException;
import one.digitalinnovation.gof.model.Cliente;
import one.digitalinnovation.gof.model.Endereco;
import one.digitalinnovation.gof.repository.ClienteRepository;
import one.digitalinnovation.gof.repository.EnderecoRepository;
import one.digitalinnovation.gof.service.ClienteService;
import one.digitalinnovation.gof.service.ViaCepService;
import org.springdoc.api.OpenApiResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementação da <b>Strategy</b> {@link ClienteService}, a qual pode ser
 * injetada pelo Spring (via {@link Autowired}). Com isso, como essa classe é um
 * {@link Service}, ela será tratada como um <b>Singleton</b>.
 */

@Service
public class ClientServiceImpl implements ClienteService {

    // Singleton - Injetar os componentes do Spring com @Autowired.
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private ViaCepService viaCepService;

    // Strategy - Implementar os métodos definidos na interface.
    // Facade - Abstrair integrações com subsistemas, provendo uma interface simples.

    @Override
    public Iterable<Cliente> buscarTodos() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente buscarPorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new OpenApiResourceNotFoundException("Cliente não encontrado com ID: " + id));
    }

    @Override
    public void inserir(Cliente cliente) {
       salvarClienteComCep(cliente);
    }

    /* Template Method Pattern - Define um algoritmo que verifica e salva um cliente com um CEP.
        A estrutura do método está definida, mas algumas partes específicas, como a busca do endereço e
        o salvamento do cliente, podem ser alteradas sem modificar o método principal.
     */
    private void salvarClienteComCep(Cliente cliente) {
        // Verificar se o Endereço do Cliente já existe (pelo CEP).
        String cep = cliente.getEndereco().getCep();
        Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
                Endereco novoEndereco = viaCepService.consultarCep(cep);
                if (novoEndereco == null || novoEndereco.getLogradouro() == null) {
                    throw new ClienteInsertionException(cliente.getNome() + " CEP inválido: " + cep + " impossível inserir um Cliente com CEP inválido!");
                }
                enderecoRepository.save(novoEndereco);
                return novoEndereco;
            });
            cliente.setEndereco(endereco);
            clienteRepository.save(cliente);
    }

    @Override
    public void atualizar(Long id, Cliente cliente) {
        // Verifica se o Cliente não existe.
       if (!clienteRepository.existsById(id)) {
           throw new OpenApiResourceNotFoundException("Cliente não encontrado com ID: " + id + " impossível atualizar um Cliente inexistente!");
       }
       // Busca o Cliente pelo ID.
        salvarClienteComCep(cliente);
    }

    @Override
    public void deletar(Long id) {
        // Verifica se o Cliente não existe.
        if (!clienteRepository.existsById(id)) {
            throw new OpenApiResourceNotFoundException("Cliente não encontrado com ID: " + id + " impossível deletar um Cliente inexistente!");
        }
        //Deletar Cliente pelo ID.
        clienteRepository.deleteById(id);
    }
}
