package br.com.rvbraga.frontProvider.service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import br.com.rvbraga.frontProvider.models.Escola;
import br.com.rvbraga.frontProvider.models.Fornecedor;
import br.com.rvbraga.frontProvider.models.Programa;
import br.com.rvbraga.frontProvider.models.Rota;
import reactor.core.publisher.Mono;

@Service
public class FornecedorService {
	@Autowired
    private WebClient.Builder webClientBuilder;
	
	
    public List<Fornecedor> listFornecedores() {
        Mono<Fornecedor[]> response = webClientBuilder.build()
                .get()
                .uri("http://localhost:8085/api/fornecedores")
                .retrieve()
                .bodyToMono(Fornecedor[].class);         
        return Arrays.asList(response.block());
    }
   
    public Fornecedor saveFornecedor(Fornecedor fornecedor) {
    	
        Mono<Fornecedor> response = webClientBuilder.build()
                .post()
                .uri("http://localhost:8085/api/fornecedores")
                .body(Mono.just(fornecedor), Fornecedor.class)
                .retrieve()
                .bodyToMono(Fornecedor.class);
                

        return response.block();
    }

   
    public Fornecedor getFornecedor(UUID id) {
        Mono<Fornecedor> response = webClientBuilder.build()
                .get()
                .uri("http://localhost:8085/api/fornecedores/" + id)
                .retrieve()
                .bodyToMono(Fornecedor.class);

        
        return response.block();
    }


    public Fornecedor updateFornecedor(UUID id,Fornecedor fornecedor) {
       Mono<Fornecedor> response =  webClientBuilder.build()
                .put()                
                .uri("http://localhost:8085/api/fornecedores/" + id)
                .body(Mono.just(fornecedor), Fornecedor.class)
                .retrieve()
                .bodyToMono(Fornecedor.class);
               

        return response.block();
    }
    public void deleteFornecedor(UUID id) {
    	webClientBuilder.build()
        .delete()
        .uri("http://localhost:8085/api/fornecedores/" + id)
        .retrieve()
        .bodyToMono(Void.class)
        .block();
         
     }   
}
