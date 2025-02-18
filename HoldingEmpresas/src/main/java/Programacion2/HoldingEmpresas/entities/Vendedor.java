package Programacion2.HoldingEmpresas.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder // Lombok SuperBuilder for inheritance
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)
public class Vendedor extends UserEntity {
    @Column()
    private Double ingresosTotales;

    @OneToMany(
        mappedBy = "manager", 
        fetch = FetchType.EAGER, 
        cascade = { CascadeType.PERSIST, CascadeType.MERGE }, 
        orphanRemoval = false)
    @JsonManagedReference("vendedor-subcontratados")
    private List<Vendedor> subContratados;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "manager_id")
    @JsonBackReference("vendedor-subcontratados")
    private Vendedor manager; // Rename from managerID to manager

    public void addSubContratado(Vendedor sub) {
        subContratados.add(sub);
        sub.setManager(this);
    }

    public void removeSubContratado(Vendedor sub) {
        subContratados.remove(sub);
        sub.setManager(null);
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "empresa_id")
    @JsonBackReference("empresa-vendedores")
    private Empresa empresa;
}