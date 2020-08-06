package org.opengroup.osdu.unitservice.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/_ah")
public class HealthCheck {

	@GetMapping("/liveness_check")
	public ResponseEntity livenessCheck() {
		return ResponseEntity.ok().build();
	}

	@GetMapping("/readiness_check")
	public ResponseEntity readinessCheck() {
		return ResponseEntity.ok().build();
	}
}
