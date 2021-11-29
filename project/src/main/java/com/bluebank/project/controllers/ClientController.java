package com.bluebank.project.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bluebank.project.dtos.ClientDTO;
import com.bluebank.project.models.Client;
import com.bluebank.project.services.ClienteService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/cliente")
@Api(value="API BlueBank")
@CrossOrigin(origins="*")
public class ClientController {

	@Autowired
	ClienteService clienteService;
	
	@PostMapping()
	@ApiOperation(value="Cadastra um novo cliente com os dados pessoais")
	@ResponseStatus(HttpStatus.CREATED)
	public ClientDTO registerClient(@Validated @RequestBody Client cliente, BindingResult br) {//throws DataIntegrityViolationException, Exception {
//		if(br.hasErrors()) throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());
		return clienteService.registerNewClient(cliente);
	}
	
	@GetMapping("/{cpfcnpj}")
	@ApiOperation(value="Consulta os dados de um cliente atraves do CPF ou CNPJ")
	@ResponseStatus(HttpStatus.OK)
	public ClientDTO consultClientRegistryByCpfcnpj(@PathVariable("cpfcnpj") String cpfcnpj) {
		return clienteService.consultarCadastroCliente(cpfcnpj);
	}
	
	@GetMapping("/nome/{nome}")
	@ApiOperation(value="Consulta os dados de um cliente atraves do nome")
	@ResponseStatus(HttpStatus.OK)
	public List<ClientDTO> consultClientRegistryByName(@PathVariable("nome") String nome) {
		return clienteService.buscarClientePorNome(nome);
	}
	
	@PutMapping("/{cpfcnpj}")
	@ApiOperation(value="Atualiza o cadastro do cliente atraves do CPF ou CNPJ")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public ClientDTO updateClientRegistry(@PathVariable("cpfcnpj") String cpfcnpj, @RequestBody ClientDTO clientDTO) {//, BindingResult br) throws DataIntegrityViolationException, Exception {
//		if(br.hasErrors()) throw new ConstraintException(br.getAllErrors().get(0).getDefaultMessage());
		return clienteService.atualizarCadastroCliente(cpfcnpj, clientDTO);
	}
	
	@DeleteMapping("/{cpfcnpj}")
	@ApiOperation(value="Desativa o cadastro do cliente")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deactivateClientRegistry(@PathVariable("cpfcnpj") String cpfcnpj) {
		clienteService.desativarContaCliente(cpfcnpj);
	}
}