package Programacion2.HoldingEmpresas.entities;

import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "asesor_id")
    @JsonBackReference("asesor-contratos")
    @ToString.Exclude
    private Asesor asesor;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "empresa_id")
    @ToString.Exclude
    @JsonBackReference("empresa-contratos")
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
    ) @JsonIgnore private List<Area> areasOperadas;
}
