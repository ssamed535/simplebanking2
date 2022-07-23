package com.eteration.simplebanking;

import com.eteration.simplebanking.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TransactionServiceTests {

    @Spy
    private TransactionService transactionService;

    @Test
    void testGenerateApprovalCode() {
        Integer[] chunkLengths = {8, 4, 4, 12};

        String approvalCode = transactionService.generateApprovalCode();
        String[] approvalCodeChunks = approvalCode.split("-");

        assertEquals(4, approvalCodeChunks.length);
        assertEquals(chunkLengths.length, approvalCodeChunks.length);

        for (int i = 0; i < approvalCodeChunks.length; i++)
            assertEquals(chunkLengths[i], approvalCodeChunks[i].length());
    }
}