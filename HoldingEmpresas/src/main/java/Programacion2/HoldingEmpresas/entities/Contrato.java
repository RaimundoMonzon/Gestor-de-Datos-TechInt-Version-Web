package Programacion2.HoldingEmpresas.entities;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity 
@Table(name = "contratos")
public class Contrato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "asesor_id")
    private Asesor asesor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

    @Column(nullable = false)
    private Date fechaCelebracion;

    @Column(nullable = false)
    private Date fechaCaducidad;

    @ManyToMany
    @JoinTable(
        name = "contrato_area", 
        joinColumns = @JoinColumn(name = "contrato_id"), 
        inverseJoinColumns = @JoinColumn(name = "area_id")
    ) private List<Area> areasOperadas;
}
