package de.hilbert.services;

import de.hilbert.repositories.ColorRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

/**
 * @author hilbert
 * @since 14.11.14
 */
@Component
public class CommonBootstrapService {

    public static Logger log = Logger.getLogger(CommonBootstrapService.class);

    @Autowired
    ColorRepository colorRepository;

    @Transactional
    public void bootstrapStocks() {
        log.info("deleting existing data ...");
        colorRepository.deleteAll();
        log.info("colors deleted");
        log.info("creating new data ...");
    }
}
