package com.example.spacenter.scheduled;


import com.example.spacenter.model.entity.MedicalProcedure;
import com.example.spacenter.repositories.MedicalProceduresRepository;
import com.example.spacenter.service.CartService;
import com.example.spacenter.service.ProceduresInitService;
import com.example.spacenter.service.UserInitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class ScheduledJob {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduledJob.class);
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss");

    private final UserInitService userInitService;
    private ProceduresInitService proceduresInitService;
    private MedicalProceduresRepository medicalProceduresRepository;

    private CartService cartService;

    @Autowired
    public ScheduledJob(UserInitService userInitService,
                        ProceduresInitService proceduresInitService,
                        MedicalProceduresRepository medicalProceduresRepository, CartService cartService) {
        this.userInitService = userInitService;
        this.proceduresInitService = proceduresInitService;
        this.medicalProceduresRepository = medicalProceduresRepository;
        this.cartService = cartService;
    }

    @Scheduled(cron = "* 16 14 * * *")
    public void reInitMedProcedures() {
        List<MedicalProcedure> procedures = medicalProceduresRepository.findAll();
        if (procedures.size() == 0) {
            proceduresInitService.initMedicalProcedures();
            LOGGER.info("initMedicalProcedures() is being executed" + formatter.format(LocalDateTime.now()));
        }
    }

}
