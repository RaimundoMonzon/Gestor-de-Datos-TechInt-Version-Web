package Programacion2.HoldingEmpresas.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Programacion2.HoldingEmpresas.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import Programacion2.HoldingEmpresas.entities.Vendedor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ManagerService {
    private final UserRepository userRepository;

    public ManagerService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Core manager change logic
    @Transactional
    public void transferSubordinates(Vendedor oldManager, Vendedor newManager) {
        List<Vendedor> subordinates = new ArrayList<>(oldManager.getSubContratados());
        subordinates.forEach(sub -> {
            oldManager.removeSubContratado(sub);
            newManager.addSubContratado(sub);
        });
        
        userRepository.save(oldManager);
        userRepository.save(newManager);
    }

    @Transactional
    public void changeVendedorManager(Long vendedorId, Long newManagerId) {
        Vendedor vendedor = userRepository.findById(vendedorId)
                .filter(Vendedor.class::isInstance)
                .map(Vendedor.class::cast)
                .orElseThrow(() -> new EntityNotFoundException("Vendedor not found"));

        Vendedor newManager = userRepository.findById(newManagerId)
                .filter(Vendedor.class::isInstance)
                .map(Vendedor.class::cast)
                .orElseThrow(() -> new EntityNotFoundException("New manager not found"));

        Optional.ofNullable(vendedor.getManager())
                .ifPresent(oldManager -> {
                    oldManager.removeSubContratado(vendedor);
                    userRepository.save(oldManager);
                });

        newManager.addSubContratado(vendedor);
        userRepository.save(newManager);
    }

    @Transactional
    public void deleteManagerAndTransferSubordinates(Long oldManagerId, Long newManagerId) {
        Vendedor oldManager = userRepository.findById(oldManagerId)
                .filter(Vendedor.class::isInstance)
                .map(Vendedor.class::cast)
                .orElseThrow(() -> new EntityNotFoundException("Old manager not found"));

        Vendedor newManager = userRepository.findById(newManagerId)
                .filter(Vendedor.class::isInstance)
                .map(Vendedor.class::cast)
                .orElseThrow(() -> new EntityNotFoundException("New manager not found"));

        transferSubordinates(oldManager, newManager);

        if (oldManager.getSubContratados().isEmpty()) {
            userRepository.delete(oldManager);
        } else {
            throw new IllegalStateException("Failed to transfer all subordinates");
        }
    }
}