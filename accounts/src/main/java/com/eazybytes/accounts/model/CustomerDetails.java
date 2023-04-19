package com.eazybytes.accounts.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter @Getter @ToString
public class CustomerDetails {

    private Accounts accounts;
    private List<Cards> cards;
    private List<Loans> loans;

}
