package com.project.credit.transaction.service;

import com.project.credit.card.entity.CreditCard;
import com.project.credit.card.exception.CardException;
import com.project.credit.card.service.CreditCardService;
import com.project.credit.merchant.entity.Merchant;
import com.project.credit.merchant.exception.MerchantException;
import com.project.credit.merchant.service.MerchantService;
import com.project.credit.transaction.dto.TransactionRequestDto;
import com.project.credit.transaction.dto.TransactionResponseDto;
import com.project.credit.transaction.entity.Transaction;
import com.project.credit.transaction.exception.DateException;
import com.project.credit.transaction.exception.TransactionException;
import com.project.credit.transaction.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
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
    public Transaction transferAmount(TransactionRequestDto transactionRequestDto) throws CardException, MerchantException, TransactionException {

        if (transactionRequestDto == null) {
            throw new TransactionException("Transaction details cannot be empty");
        }
        if (transactionRequestDto.getCustomerCreditCardNumber() == null || transactionRequestDto.getCustomerCreditCardNumber().isEmpty()) {
            throw new TransactionException("From card number cannot be empty");
        }
        if (transactionRequestDto.getMerchantCardNumber() == null || transactionRequestDto.getMerchantCardNumber().isEmpty()) {
            throw new TransactionException("To card number cannot be empty");
        }
        if (transactionRequestDto.getAmount() == null || transactionRequestDto.getAmount() <= 0) {
            throw new TransactionException("Amount cannot be empty or less than or equal to zero");
        }
        if (transactionRequestDto.getCustomerName() == null || transactionRequestDto.getCustomerName().isEmpty()) {
            throw new TransactionException("From card holder name cannot be empty");
        }
        if (transactionRequestDto.getValidUpto() == null) {
            throw new TransactionException("Expiry date cannot be empty");
        }
        if (transactionRequestDto.getCvv() == null || transactionRequestDto.getCvv() <= 0) {
            throw new TransactionException("CVV cannot be empty");
        }
        CreditCard creditCard = creditCardService.findCreditCardByCardNumber(transactionRequestDto.getCustomerCreditCardNumber());
        Merchant merchant = merchantService.getMerchantByCardNumber(transactionRequestDto.getMerchantCardNumber());

        if (transactionRequestDto.getValidUpto().compareTo(new Date(System.currentTimeMillis())) < 0) {
            throw new CardException("Card has expired");
        }
        if (creditCard.getCurrentLimit() < transactionRequestDto.getAmount()) {
            throw new TransactionException("Insufficient Credit Limit");
        }
        if (!creditCard.getCustomer().getName().equals(transactionRequestDto.getCustomerName())) {
            throw new TransactionException("Card holder name does not match");
        }
        if (!creditCard.getCvv().equals(transactionRequestDto.getCvv())) {
            throw new TransactionException("CVV does not match");
        }
        if (creditCard.getCardCreatedOn().compareTo(new Date(System.currentTimeMillis())) > 0) {
            throw new CardException("Card has not been activated");
        }
        if (!merchant.getName().equals(transactionRequestDto.getMerchantName())){
            throw new MerchantException("Merchant name does not match");
        }
        creditCard.setCurrentLimit(creditCard.getCurrentLimit() - transactionRequestDto.getAmount());
        creditCardService.updateCreditCard(creditCard);
        merchant.setBalance(merchant.getBalance() + transactionRequestDto.getAmount());
        merchantService.updateMerchant(merchant);
        Transaction transaction = new Transaction(transactionRequestDto.getTransactionName(), transactionRequestDto.getDescription(), transactionRequestDto.getAmount(), creditCard, merchant);
        Transaction resultTransaction =  addTransaction(transaction);
        creditCard.getTransactions().add(resultTransaction);
        creditCardService.updateCreditCard(creditCard);
        return resultTransaction;
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
    public List<Transaction> getAllTransactionsByCustomerCreditCardNumber(String customerCreditCardNumber) throws CardException, TransactionException {
        CreditCard creditCard = creditCardService.findCreditCardByCardNumber(customerCreditCardNumber);
        List<Transaction> transactions = creditCard.getTransactions();
        if (transactions.isEmpty()) {
            throw new TransactionException("No transactions found");
        }
        return transactions;
    }

    @Override
    public List<Transaction> getAllTransactionsByCustomerCreditCardNumberForParticularDuration(String customerCreditCardNumber, Date startDate, Date endDate) throws CardException, DateException, TransactionException {
        if (startDate == null || endDate == null) {
            throw new DateException("Start date and end date cannot be empty");
        }
        if (startDate.compareTo(endDate) > 0) {
            throw new DateException("Start date cannot be greater than end date");
        }
        CreditCard creditCard = creditCardService.findCreditCardByCardNumber(customerCreditCardNumber);
        List<Transaction> transactions = new ArrayList<>();
        for(Transaction transaction : creditCard.getTransactions()){
            if (transaction.getDate().compareTo(startDate) >= 0 && transaction.getDate().compareTo(endDate) <= 0) {
                transactions.add(transaction);
            }
        }
        if (transactions.isEmpty()) {
            throw new TransactionException("No transactions found");
        }
        return transactions;
    }

    @Override
    public Transaction deleteTransactionById(Long id) throws TransactionException, CardException {
        Transaction transaction = getTransactionById(id);
        CreditCard creditCard = transaction.getCreditCard();
        creditCard.getTransactions().remove(transaction);
        creditCardService.updateCreditCard(creditCard);
        transactionRepository.deleteById(id);
        return transaction;
    }
    public TransactionResponseDto covertTransactionToTransactionResponseDto(Transaction transaction) {
        return new TransactionResponseDto(transaction.getTransactionId(), transaction.getTransactionName(), transaction.getDescription(), transaction.getAmount(), transaction.getDate(), transaction.getTransactionType(), transaction.getCreditCard().getCardNumber(), transaction.getMerchant().getCardNumber());
    }
}
