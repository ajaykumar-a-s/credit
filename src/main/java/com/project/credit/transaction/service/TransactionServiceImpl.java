package com.project.credit.transaction.service;

import com.project.credit.card.entity.CreditCard;
import com.project.credit.card.exception.CardException;
import com.project.credit.card.service.CreditCardService;
import com.project.credit.merchant.entity.Merchant;
import com.project.credit.merchant.exception.MerchantException;
import com.project.credit.merchant.service.MerchantService;
import com.project.credit.transaction.dto.TransactionDto;
import com.project.credit.transaction.entity.Transaction;
import com.project.credit.transaction.exception.DateException;
import com.project.credit.transaction.exception.TransactionException;
import com.project.credit.transaction.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
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
        CreditCard creditCard = null;
        Merchant merchant = null;
        try {
            creditCard = creditCardService.findCreditCardByCardNumber(transactionDto.getFromCardNumber());
            merchant = merchantService.getMerchantByCardNumber(transactionDto.getToCardNumber());
        } catch (CardException | MerchantException e) {
            throw e;
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
        if (transaction == null) {
            throw new TransactionException("Transaction details cannot be empty");
        }
        return transactionRepository.save(transaction);
    }

    @Override
    public Transaction getTransactionById(Long id) throws TransactionException {
        Transaction transaction = transactionRepository.findById(id).orElse(null);
        if (transaction == null) {
            throw new TransactionException("Transaction does not exist");
        }
        return transaction;
    }

    @Override
    public List<Transaction> getAllTransactions() throws TransactionException {
        List<Transaction> transactions = transactionRepository.findAll();
        if (transactions.isEmpty()) {
            throw new TransactionException("No transactions found");
        }
        return transactions;
    }

    @Override
    public List<Transaction> getAllTransactionsByCardNumber(String cardNumber) throws CardException, TransactionException {
        CreditCard creditCard = null;
        try {
            creditCard = creditCardService.findCreditCardByCardNumber(cardNumber);
        } catch (CardException e) {
            throw e;
        }
        List<Transaction> transactions = transactionRepository.findAllByCreditCard(creditCard);
        if (transactions.isEmpty()) {
            throw new TransactionException("No transactions found");
        }
        return transactions;
    }

    @Override
    public List<Transaction> getAllTransactionsByCardNumberForParticularDuration(String cardNumber, Date startDate, Date endDate) throws CardException, DateException, TransactionException {
        if (startDate == null || endDate == null) {
            throw new DateException("Start date and end date cannot be empty");
        }
        if (startDate.compareTo(endDate) > 0) {
            throw new DateException("Start date cannot be greater than end date");
        }
        List<Transaction> transactions = transactionRepository.findAllByDateBetween(startDate, endDate);
        if (transactions.isEmpty()){
            throw new TransactionException("No transactions found");
        }
        return transactions;
    }
}
