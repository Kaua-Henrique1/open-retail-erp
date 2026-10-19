package com.mega.erp.modules.reports.controller;

import com.mega.erp.modules.reports.dto.InventoryReportResponseDTO;
import com.mega.erp.modules.reports.dto.SalesSummaryResponseDTO;
import com.mega.erp.modules.reports.dto.SlowMovingProductsResponseDTO;
import com.mega.erp.modules.reports.domain.ReportCategory;
import com.mega.erp.modules.reports.domain.ReportPeriod;
import com.mega.erp.modules.reports.domain.StockStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/reports")
@Tag(name = "Painel do Empresário (Reports)", description = "Dashboards e relatórios gerenciais focados em tomada de decisão.")
public class ReportController {

    @GetMapping("/inventory")
    @Operation(summary = "Posição de Estoque em Tempo Real", description = "Lista produtos com paginação, permitindo filtrar por categoria e status de alerta de reposição.")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Relatório gerado com sucesso"), @ApiResponse(responseCode = "400", description = "Parâmetros inválidos")})
    public ResponseEntity<InventoryReportResponseDTO> getInventoryReport(
            @Parameter(description = "Filtro por Categoria (ROUPAS, UTILIDADES)") @RequestParam(required = false) ReportCategory categoria,
            @Parameter(description = "Filtro de Status do Estoque") @RequestParam(defaultValue = "TODOS") StockStatus status_estoque,
            @Parameter(description = "Página atual") @RequestParam(defaultValue = "1") int page,
            @Parameter(description = "Limite de itens por página") @RequestParam(defaultValue = "20") int limit) {

        // TODO: Implementar lógica no ReportService
        return ResponseEntity.ok().build();
    }

    @GetMapping("/sales-summary")
    @Operation(summary = "Fechamento de Vendas Diário/Semanal", description = "Resumo financeiro de vendas contendo faturamento total, quantidade de vendas e ticket médio.")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Resumo gerado com sucesso"), @ApiResponse(responseCode = "400", description = "Parâmetros de data inválidos")})
    public ResponseEntity<SalesSummaryResponseDTO> getSalesSummary(
            @Parameter(description = "Período de análise", required = true) @RequestParam ReportPeriod periodo,
            @Parameter(description = "Data inicial (Obrigatório se periodo=PERSONALIZADO)") @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime data_inicio,
            @Parameter(description = "Data final (Obrigatório se periodo=PERSONALIZADO)") @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime data_fim) {

        // TODO: Implementar lógica no ReportService
        return ResponseEntity.ok().build();
    }

    @GetMapping("/slow-moving-products")
    @Operation(summary = "Produtos Sem Giro (Encalhados)", description = "Identifica produtos que não possuem saída há X dias, ajudando no planejamento de promoções.")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Relatório gerado com sucesso")})
    public ResponseEntity<SlowMovingProductsResponseDTO> getSlowMovingProducts(
            @Parameter(description = "Dias sem registros de venda") @RequestParam(defaultValue = "30") int dias_sem_giro,
            @Parameter(description = "Filtro por Categoria") @RequestParam(required = false) ReportCategory categoria) {

        // TODO: Implementar lógica no ReportService
        return ResponseEntity.ok().build();
    }
}