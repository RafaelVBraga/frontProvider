package br.com.rvbraga.frontProvider.service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import br.com.rvbraga.frontProvider.models.Produto;
import reactor.core.publisher.Mono;

@Service
public class ProdutoService {
	
	@Autowired
    private WebClient.Builder webClientBuilder;
	
	
	public List<Produto> listProdutos() {
        Mono<Produto[]> response = webClientBuilder.build()
                .get()
                .uri("http://localhost:8085/api/produtos")
                .retrieve()
                .bodyToMono(Produto[].class);         
        return Arrays.asList(response.block());
    }
   
    public Produto saveProduto(Produto Produto) {
        Mono<Produto> response = webClientBuilder.build()
                .post()
                .uri("http://localhost:8085/api/produtos")
                .body(Mono.just(Produto), Produto.class)
                .retrieve()
                .bodyToMono(Produto.class);
                

        return response.block();
    }

   
    public Produto getProduto(UUID id) {
        Mono<Produto> response = webClientBuilder.build()
                .get()
                .uri("http://localhost:8085/api/produtos/" + id)
                .retrieve()
                .bodyToMono(Produto.class);

        
        return response.block();
    }


    public Produto updateProduto(UUID id,Produto Produto) {
       Mono<Produto> response =  webClientBuilder.build()
                .put()                
                .uri("http://localhost:8085/api/produtos/" + id)
                .body(Mono.just(Produto), Produto.class)
                .retrieve()
                .bodyToMono(Produto.class);
               

        return response.block();
    }
    public void deleteProduto(UUID id) {
    	webClientBuilder.build()
        .delete()
        .uri("http://localhost:8085/api/produtos/" + id)
        .retrieve()
        .bodyToMono(Void.class)
        .block();
         
     }   

}
