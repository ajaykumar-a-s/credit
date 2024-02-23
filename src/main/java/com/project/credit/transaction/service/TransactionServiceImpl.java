package com.project.credit.transaction.service;

import com.project.credit.card.entity.CreditCard;
import com.project.credit.card.exception.CardException;
import com.project.credit.card.service.CreditCardService;
import com.project.credit.customer.exception.CustomerException;
import com.project.credit.merchant.entity.Merchant;
import com.project.credit.merchant.exception.MerchantException;
import com.project.credit.merchant.service.MerchantService;
import com.project.credit.transaction.dto.TransactionDto;
import com.project.credit.transaction.entity.Transaction;
import com.project.credit.transaction.exception.DateException;
import com.project.credit.transaction.exception.TransactionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private CreditCardService creditCardService;

    @Autowired
    private MerchantService merchantService;

    @Override
    public Transaction transferAmount(TransactionDto transactionDto) throws CardException, MerchantException, TransactionException {

        if (transactionDto == null) {
            throw new TransactionException("Transaction details cannot be empty");
        }
        if (transactionDto.getFromCardNumber() == null || transactionDto.getFromCardNumber().isEmpty()) {
            throw new TransactionException("From card number cannot be empty");
        }
        if (transactionDto.getToCardNumber() == null || transactionDto.getToCardNumber().isEmpty()) {
            throw new TransactionException("To card number cannot be empty");
        }
        if (transactionDto.getAmount() == null || transactionDto.getAmount() <= 0) {
            throw new TransactionException("Amount cannot be empty or less than or equal to zero");
        }
        if (transactionDto.getFromCardHolderName() == null || transactionDto.getFromCardHolderName().isEmpty()) {
            throw new TransactionException("From card holder name cannot be empty");
        }
        if (transactionDto.getExpiryDate() == null) {
            throw new TransactionException("Expiry date cannot be empty");
        }
        if (transactionDto.getCvv() == null || transactionDto.getCvv().isEmpty()) {
            throw new TransactionException("CVV cannot be empty");
        }
        CreditCard creditCard = creditCardService.findCreditCardByCardNumber(transactionDto.getFromCardNumber());
        Merchant merchant = merchantService.getMerchantByCardNumber(transactionDto.getToCardNumber());
        if (creditCard == null) {
            throw new CardException("From card number does not exist");
        }
        if (merchant == null) {
            throw new MerchantException("To card number does not exist");
        }
        if (transactionDto.getExpiryDate().compareTo(new Date(System.currentTimeMillis())) < 0) {
            throw new CardException("Card has expired");
        }
        if (creditCard.getCurrentLimit() < transactionDto.getAmount()) {
            throw new TransactionException("Insufficient Credit Limit");
        }
        creditCard.setCurrentLimit(creditCard.getCurrentLimit() - transactionDto.getAmount());
        creditCardService.updateCreditCard(creditCard);
        merchant.setBalance(merchant.getBalance() + transactionDto.getAmount());
        merchantService.updateMerchant(merchant);
        Transaction transaction = new Transaction(transactionDto.getName(), transactionDto.getDescription(), transactionDto.getAmount(), creditCard, merchant);
        return addTransaction(transaction);
    }

    @Override
    public Transaction addTransaction(Transaction transaction) throws TransactionException {
        return null;
    }

    @Override
    public Transaction getTransactionById(Long id) throws TransactionException {
        return null;
    }

    @Override
    public List<Transaction> getAllTransactions() throws TransactionException {
        return null;
    }

    @Override
    public List<Transaction> getAllTransactionsByCardId(String cardId) throws CardException, TransactionException {
        return null;
    }

    @Override
    public List<Transaction> getAllTransactionsByCustomerId(Long customerId) throws CustomerException, TransactionException {
        return null;
    }

    @Override
    public List<Transaction> getAllTransactionsByCardIdForParticularDuration(String cardNumber, Date startDate, Date endDate) throws CardException, DateException, TransactionException {
        return null;
    }
}
