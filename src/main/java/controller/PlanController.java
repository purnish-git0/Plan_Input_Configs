package controller;

import dto.PlanDTO;
import entity.Plan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import repository.PlanRepository;

@RestController
@RequestMapping("/api/v1/plan")
public class PlanController {

    private PlanRepository planRepository;

    public PlanController(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    @PostMapping("/create")
    public ResponseEntity<Plan> createPlan(@RequestBody PlanDTO planDTO) {
        Plan plan = Plan.builder()
                .name(planDTO.getName())
                .type(planDTO.getPlanType())
                .planCategory(planDTO.getPlanCategory())
                .build();

        planRepository.save(plan);

        return ResponseEntity.ok(plan);
    }


}
