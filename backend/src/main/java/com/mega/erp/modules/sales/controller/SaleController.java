package com.mega.erp.modules.sales.controller;

import com.mega.erp.modules.sales.dto.SaleRequestDTO;
import com.mega.erp.modules.sales.dto.SaleResponseDTO;
import com.mega.erp.modules.sales.dto.SaleStatusUpdateDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/sales")
@Tag(name = "Vendas", description = "Endpoints para processamento e consulta de pedidos/vendas")
public class SaleController {

    @PostMapping
    @Operation(summary = "Registra uma nova venda", description = "Cria um novo pedido associando os itens (produtos) a um cliente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Venda registrada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação (ex: payload inválido)"),
            @ApiResponse(responseCode = "422", description = "Regra de negócio violada (ex: estoque insuficiente)"),
            @ApiResponse(responseCode = "409", description = "Conflito de Idempotência (requisição duplicada processada anteriormente)")
    })
    public ResponseEntity<SaleResponseDTO> createSale(
            @Parameter(description = "Chave de Idempotência para evitar registro/cobrança duplicada", required = true, example = "550e8400-e29b-41d4-a716-446655440000")
            @RequestHeader("Idempotency-Key") String idempotencyKey,

            @Valid @RequestBody SaleRequestDTO request) {

        // TODO: Chamar o SaleService (Faremos na próxima etapa)

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca detalhes de uma venda", description = "Retorna os detalhes e o status atual de uma venda pelo ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Venda encontrada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Venda não encontrada no sistema")
    })
    public ResponseEntity<SaleResponseDTO> getSaleById(
            @Parameter(description = "UUID da Venda", required = true)
            @PathVariable UUID id) {

        // TODO: Chamar o SaleService

        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/status")
    @Operation(summary = "Atualiza o status da venda", description = "Altera o status de uma venda (ex: de PENDING para PAID).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Status atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Status enviado inválido"),
            @ApiResponse(responseCode = "404", description = "Venda não encontrada")
    })
    public ResponseEntity<SaleResponseDTO> updateSaleStatus(
            @Parameter(description = "UUID da Venda", required = true)
            @PathVariable UUID id,

            @Valid @RequestBody SaleStatusUpdateDTO statusUpdateDTO) {

        // TODO: Chamar o SaleService

        return ResponseEntity.ok().build();
    }
}