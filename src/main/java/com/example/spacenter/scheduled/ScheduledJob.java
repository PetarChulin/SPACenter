package com.example.spacenter.scheduled;


import com.example.spacenter.model.entity.MedicalProcedure;
import com.example.spacenter.model.entity.SpaProcedure;
import com.example.spacenter.repositories.MedicalProceduresRepository;
import com.example.spacenter.repositories.SpaProceduresRepository;
import com.example.spacenter.repositories.SpaSubProceduresRepos.SpaServicesRepository;
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

    private SpaProceduresRepository spaProceduresRepository;

    private CartService cartService;

    @Autowired
    public ScheduledJob(UserInitService userInitService,
                        ProceduresInitService proceduresInitService,
                        MedicalProceduresRepository medicalProceduresRepository,
                        SpaProceduresRepository spaProceduresRepository, CartService cartService) {
        this.userInitService = userInitService;
        this.proceduresInitService = proceduresInitService;
        this.medicalProceduresRepository = medicalProceduresRepository;
        this.spaProceduresRepository = spaProceduresRepository;
        this.cartService = cartService;
    }

    @Scheduled(cron = "* 00 13 * * *")
    public void reInitMedProcedures() {
        List<MedicalProcedure> procedures = medicalProceduresRepository.findAll();
        if (procedures.size() == 0) {
            proceduresInitService.initMedicalProcedures();
            LOGGER.info("initMedicalProcedures() is being executed" + formatter.format(LocalDateTime.now()));
        }
    }

    @Scheduled(cron = "* 01 13 * * *")
    public void reInitSpaProcedures() {
        List<SpaProcedure> procedures = spaProceduresRepository.findAll();
        if (procedures.size() == 0) {
            proceduresInitService.initSpaProcedures();
            LOGGER.info("initSpaProcedures() is being executed" + formatter.format(LocalDateTime.now()));
        }
    }

}
