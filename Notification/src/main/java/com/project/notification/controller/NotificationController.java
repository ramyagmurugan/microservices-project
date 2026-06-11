package com.project.notification.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Notification APIs")
public class NotificationController {

    @Operation(summary = "Health Check")
    @GetMapping("/notification/health")
    public String health() {

        return "Notification Service Running";
    }
}
