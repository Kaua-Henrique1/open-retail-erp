package com.mega.erp.modules.stock.controller;

import com.mega.erp.modules.stock.dto.ProductRequestDTO;
import com.mega.erp.modules.stock.dto.ProductResponseDTO;
import com.mega.erp.modules.stock.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
@Tag(name = "Estoque / Produtos", description = "Endpoints para gerenciamento de catálogo de produtos e saldos de estoque")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @Operation(summary = "Cadastrar um novo produto", description = "Registra um novo produto no catálogo e ajusta o saldo inicial de estoque.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Produto cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados informados inválidos ou código de barras já existente"),
            @ApiResponse(responseCode = "401", description = "Não autorizado (Token ausente ou inválido)")
    })
    public ResponseEntity<String> createProduct(@Valid @RequestBody ProductRequestDTO dto) {
        // Implementação da regra de negócio será feita nos próximos passos
        return ResponseEntity.status(HttpStatus.CREATED).body("Produto cadastrado com sucesso!");
    }

    @GetMapping("/ean/{ean}")
    @Operation(summary = "Buscar produto por Código de Barras (EAN)", description = "Endpoint otimizado para o módulo de PDV e leitor de código de barras.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Produto encontrado com sucesso",
                    content = @Content(schema = @Schema(implementation = ProductResponseDTO.class))
            ),
            @ApiResponse(responseCode = "404", description = "Produto não localizado no banco de dados")
    })
    public ResponseEntity<ProductResponseDTO> getByEan(@PathVariable String ean) {
        return ResponseEntity.ok(productService.findByEan(ean));
    }
}