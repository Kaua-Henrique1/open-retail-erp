package com.mega.erp.modules.stock.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "categoria")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    // TODO: Mapear como relacionamento quando a entidade Empresa for criada.
    @Column(name = "empresa_id", nullable = false)
    private UUID companyId;

    @Column(name = "nome", nullable = false, length = 100)
    private String name;
}
