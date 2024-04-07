package fr.uga.l3miage.spring.tp3.components;

import fr.uga.l3miage.spring.tp3.exceptions.technical.NotFoundSessionException;
import fr.uga.l3miage.spring.tp3.models.EcosSessionEntity;
import fr.uga.l3miage.spring.tp3.repositories.EcosSessionProgrammationRepository;
import fr.uga.l3miage.spring.tp3.repositories.EcosSessionProgrammationStepRepository;
import fr.uga.l3miage.spring.tp3.repositories.EcosSessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SessionComponent {
    private final EcosSessionRepository ecosSessionRepository;
    private final EcosSessionProgrammationRepository ecosSessionProgrammationRepository;
    private final EcosSessionProgrammationStepRepository ecosSessionProgrammationStepRepository;


    public EcosSessionEntity createSession(EcosSessionEntity entity){
        ecosSessionProgrammationStepRepository.saveAll(entity.getEcosSessionProgrammationEntity().getEcosSessionProgrammationStepEntities());
        ecosSessionProgrammationRepository.save(entity.getEcosSessionProgrammationEntity());
        return ecosSessionRepository.save(entity);
    }

    public EcosSessionEntity getSession(Long id) throws NotFoundSessionException {
        return ecosSessionRepository.findById(id).orElseThrow(() -> new NotFoundSessionException(String.format("La session %s n'existe pas", id))) ;
    }
}
