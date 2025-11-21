package com.nt.utility;

import java.util.concurrent.ThreadLocalRandom;
import java.util.Set;
import java.util.HashSet;

public class AccountNumberGenerator {
    
    // Store generated account numbers to ensure uniqueness
    private static Set<Long> usedAccountNumbers = new HashSet<>();
    
    /**
     * Generates a unique 12-digit account number
     * Thread-safe and ensures no duplicates
     */
    public static synchronized long generateUniqueAccountNumber() {
        long accountNumber;
        
        do {
            // Generate random 12-digit number
            accountNumber = ThreadLocalRandom.current()
                .nextLong(100000000000L, 1000000000000L);
        } while (usedAccountNumbers.contains(accountNumber));
        
        // Store to prevent duplicates
        usedAccountNumbers.add(accountNumber);
        
        return accountNumber;
    }
}
