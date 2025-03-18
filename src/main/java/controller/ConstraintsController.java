package controller;

import constraints.ConstraintExternalToInput;
import constraints.FrontEndConstraintImpl;
import dto.CreateConstraintExternalToInputDTO;
import dto.FrontEndConstraintsDTO;
import entity.ExternalVariable;
import entity.Input;
import interfaces.ExternalConstraint;
import interfaces.FrontEndConstraint;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repository.ConstraintsExternalToInputRepository;
import repository.ExternalVariableRepository;
import repository.FrontEndConstraintRepository;
import repository.InputRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/constraints")
public class ConstraintsController {

    private InputRepository inputRepository;

    private ConstraintsExternalToInputRepository constraintsExternalToInputRepository;

    private FrontEndConstraintRepository frontEndConstraintRepository;

    private ExternalVariableRepository externalVariableRepository;

    public ConstraintsController(InputRepository inputRepository,
                                 ConstraintsExternalToInputRepository constraintsExternalToInputRepository,
                                 FrontEndConstraintRepository frontEndConstraintRepository,
                                 ExternalVariableRepository externalVariableRepository) {



        this.inputRepository = inputRepository;

        this.constraintsExternalToInputRepository = constraintsExternalToInputRepository;

        this.frontEndConstraintRepository = frontEndConstraintRepository;

        this.externalVariableRepository = externalVariableRepository;

    }




    @PostMapping("/create-front-end-constraints")
    public ResponseEntity<Set<FrontEndConstraintImpl>> createFrontEndConstraints(@RequestBody Set<FrontEndConstraintsDTO> frontEndConstraintsDTOs) {

        Set<FrontEndConstraintImpl> retVal = new HashSet<>();

        frontEndConstraintsDTOs.forEach(frontEndConstraintsDTO -> {

            Input constraintOn = inputRepository.findById(frontEndConstraintsDTO.getConstraintOn()).get();
            if(constraintOn.getFrontEndConstraints() == null) {
                constraintOn.setFrontEndConstraints(new HashSet<>());
            }

            Set<Input> constrainedByCollector = new HashSet<>();
            frontEndConstraintsDTO.getConstrainedBy().forEach(constrainedBy -> {
                Input constrainedByInput = inputRepository.findById(constrainedBy).get();
                constrainedByCollector.add(constrainedByInput);
            });

            FrontEndConstraintImpl feConstraint = FrontEndConstraintImpl
                    .builder()
                    .constraintOn(constraintOn)
                    .constrainedByInputs(constrainedByCollector)
                    .build();

            frontEndConstraintRepository.save(feConstraint);

            retVal.add(feConstraint);


        });

        return ResponseEntity.ok(retVal);
    }

    @PostMapping("/create-constraint-external-to-input")
    public ResponseEntity<Set<ConstraintExternalToInput>> createConstraintsExternalToInput(@RequestBody Set<CreateConstraintExternalToInputDTO> createConstraintExternalToInputDTOS) {

        Set<ConstraintExternalToInput> retVal = new HashSet<>();

        createConstraintExternalToInputDTOS.forEach(createConstraintExternalToInputDTO -> {
            Input constraintOn = inputRepository
                    .findById(createConstraintExternalToInputDTO.getConstraintOn()).get();

            Set<ExternalVariable> externalVariableCollector = new HashSet<>();

            createConstraintExternalToInputDTO.getExternalVariablesIds().forEach(externalVariableId -> {
                ExternalVariable externalVariable = externalVariableRepository.findById(externalVariableId).get();
                externalVariableCollector.add(externalVariable);
            });

            ConstraintExternalToInput constraintExternalToInput = ConstraintExternalToInput
                    .builder()
                    .constraintOn(constraintOn)
                    .externalVariable(externalVariableCollector)
                    .build();

            constraintsExternalToInputRepository.save(constraintExternalToInput);

            retVal.add(constraintExternalToInput);
        });

        return ResponseEntity.ok(retVal);
    }

    @GetMapping("/get-front-end-constraints-for-input")
    public ResponseEntity<List<FrontEndConstraintImpl>> getFrontEndConstraintsForInput(@RequestParam(name = "planId") Integer inputId) {
        return ResponseEntity.ok(frontEndConstraintRepository.getFrontEndConstraintsForInput(inputId));
    }

    @GetMapping("/get-external-variables-constraints-for-input")
    public ResponseEntity<List<ConstraintExternalToInput>> getExternalConstraintsForInput(@RequestParam(name = "planId") Integer inputId) {
        return ResponseEntity.ok(constraintsExternalToInputRepository.getExternalVariableConstraintsForInput(inputId));
    }
}
