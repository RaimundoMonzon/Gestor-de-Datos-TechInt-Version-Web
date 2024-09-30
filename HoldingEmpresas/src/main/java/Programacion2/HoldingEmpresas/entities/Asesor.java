package Programacion2.HoldingEmpresas.entities;

import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder  // Lombok SuperBuilder for inheritance
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)
public class Asesor extends UserEntity{
    @Column()
    private String titulacion;

    @ManyToMany
    @JoinTable(
        name = "asesor_area", 
        joinColumns = @JoinColumn(name = "asesor_id"), 
        inverseJoinColumns = @JoinColumn(name = "area_id")
    ) private List<Area> areasOperadas;

    @OneToMany(
        targetEntity = Contrato.class, 
        fetch = FetchType.LAZY,
        mappedBy = "asesor",
        cascade = CascadeType.ALL
    ) private List<Contrato> contratos;
}
