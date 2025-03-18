package controller;

import dto.InputDTO;
import entity.Input;
import entity.Plan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repository.InputRepository;
import repository.PlanRepository;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/input")
public class InputController {

    private final InputRepository inputRepository;

    private final PlanRepository planRepository;

    public InputController(InputRepository inputRepository,
                           PlanRepository planRepository) {
        this.inputRepository = inputRepository;

        this.planRepository = planRepository;
    }


    @PostMapping("/create-inputs")
    public ResponseEntity<Set<Input>> createInputs(@RequestBody Set<InputDTO> inputDTOs) {

        Set<Input> retVal = new HashSet<>();

        inputDTOs.forEach(inputDTO -> {
            if(inputDTO.getPlanId() != null) {
                Plan plan = planRepository.findById(inputDTO.getPlanId()).get();
                Input newInput = Input.builder()
                        .name(inputDTO.getName())
                        .type(inputDTO.getType())
                        .build();
                if(plan.getInputs() == null || plan.getInputs().isEmpty()) {
                    plan.setInputs(new HashSet<>());
                }

                plan.getInputs().add(newInput);

                planRepository.save(plan);

                inputRepository.save(newInput);

                retVal.add(newInput);
            }
        });

        return ResponseEntity.ok(retVal);


    }



    @GetMapping("/find-inputs-by-plan")
    public ResponseEntity<Set<Input>> getInputsByPlan(@RequestParam("planId") Integer planId) {

        Plan plan = planRepository.findById(planId).get();

        return ResponseEntity.ok(plan.getInputs());
    }





}
