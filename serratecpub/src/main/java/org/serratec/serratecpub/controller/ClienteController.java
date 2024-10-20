package org.serratec.serratecpub.controller;

import java.util.List;
import java.util.Optional;

import org.serratec.serratecpub.dto.ClienteDto;
import org.serratec.serratecpub.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired

    private ClienteService clienteService;

    @GetMapping
    public List<ClienteDto> listarClientes(){
        return clienteService.obterTodosClientes();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retorna cliente pelo id", description = "Dado um determinado id, será retornado o cliente")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "404", description = "Caso a lista esteja vazia é porque não tem cliente com esse id. Verifique!"),
			@ApiResponse(responseCode = "200", description = "Cliente localizado!") })
    public ResponseEntity<ClienteDto> listarClientePorId(@PathVariable Long id){
        Optional<ClienteDto> dto = clienteService.obterClientePorId(id);
        if(dto.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto.get());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteDto cadastrarCliente(@RequestBody ClienteDto clienteDto){
        return clienteService.salvarCliente(clienteDto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir cliente pelo id", description = "Dado um determinado id, será excluído o cliente")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "404", description = "Caso a lista esteja vazia é porque não tem cliente com esse id. Verifique!"),
			@ApiResponse(responseCode = "200", description = "Cliente excluído!") })
    public ResponseEntity<String> excluirCliente(@PathVariable Long id){
        if(!clienteService.excluirCliente(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado");
        }
        return ResponseEntity.ok("Cliente com id" + id + " excluído com sucesso!");
    }

    @PutMapping("/{id}")
    @Operation(summary = "Alterar cliente pelo id", description = "Dado um determinado id, será alterado o cliente")
   	@ApiResponses(value = {
   			@ApiResponse(responseCode = "404", description = "Caso a lista esteja vazia é porque não tem cliente com esse id. Verifique!"),
   			@ApiResponse(responseCode = "200", description = "Cliente alterado!") })
    public ResponseEntity<ClienteDto> alterarCliente(@PathVariable Long id,
    @RequestBody ClienteDto clienteDto){
        Optional<ClienteDto> clienteAlterado = clienteService.alterarCliente(id, clienteDto);
        if (!clienteAlterado.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(clienteAlterado.get());

    }

    
}
