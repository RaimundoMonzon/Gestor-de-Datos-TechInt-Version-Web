package Programacion2.HoldingEmpresas.entities;

import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
@Table(name = "empresas")
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String nombreEmpresa;

    @Column(nullable = false)
    private String sede;

    @Column(nullable = false)
    private Date fechaIngreso;

    @Column()
    private Double fta; // Facturacion Total Anual

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "empresa_pais", joinColumns = @JoinColumn(name = "empresa_id"), inverseJoinColumns = @JoinColumn(name = "pais_id"))
    @ToString.Exclude
    private List<Pais> paisesOperados;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "empresa_area", joinColumns = @JoinColumn(name = "empresa_id"), inverseJoinColumns = @JoinColumn(name = "area_id"))
    @ToString.Exclude
    private List<Area> areasOperadas;

    @OneToMany(targetEntity = Vendedor.class, fetch = FetchType.EAGER, mappedBy = "empresa")
    @ToString.Exclude
    @JsonManagedReference("empresa-vendedores")
    private List<Vendedor> vendedoresContratados;

    @OneToMany(mappedBy = "empresa", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @ToString.Exclude
    @JsonManagedReference("empresa-contratos")
    private List<Contrato> contratosAsesores;
}
