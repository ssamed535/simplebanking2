package com.eteration.simplebanking.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    public String generateApprovalCode() {
        Integer[] chunkLengths = {8, 4, 4, 12};
        String approvalCode = "";
        for (Integer length: chunkLengths) {
            approvalCode += RandomStringUtils.randomAlphanumeric(length) + "-";
        }
        if (approvalCode != null || approvalCode != "")
            approvalCode = StringUtils.substring(approvalCode, 0, approvalCode.length() - 1);
        return approvalCode;
    }
}
