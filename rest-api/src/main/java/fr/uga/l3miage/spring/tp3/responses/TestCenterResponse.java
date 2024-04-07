package fr.uga.l3miage.spring.tp3.responses;

import fr.uga.l3miage.spring.tp3.enums.TestCenterCode;
import lombok.Data;

@Data
public class TestCenterResponse {

    private Long id ;

    private TestCenterCode code ;

    private String university ;

    private String city ;

}
