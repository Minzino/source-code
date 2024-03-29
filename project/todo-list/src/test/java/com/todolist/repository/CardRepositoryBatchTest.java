package com.todolist.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@JdbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CardRepositoryBatchTest {

    private static final Logger log = LoggerFactory.getLogger(CardRepositoryBatchTest.class);

    private final CardRepository cardRepository;

    @Autowired
    public CardRepositoryBatchTest(NamedParameterJdbcTemplate jdbcTemplate) {
        this.cardRepository = new CardRepository(jdbcTemplate);
    }

    @DisplayName("batch 없이 업데이트를 수행한다")
    @Test
    void no_batch_insert() {
        long startTime = System.currentTimeMillis();
        cardRepository.updateIdxWithNoBatch("TODO");
        long stopTime = System.currentTimeMillis();
        log.info("execute time is : {}", stopTime - startTime);
    }
}

