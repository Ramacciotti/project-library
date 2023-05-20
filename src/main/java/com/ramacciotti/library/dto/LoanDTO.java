package com.ramacciotti.library.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.With;

import java.util.Date;

@With
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoanDTO {

    @NotNull
    @NotEmpty
    @NotBlank
    private String name;

    @NotNull
    @NotEmpty
    @NotBlank
    private String cpf;

    private Integer phone;

    private Date loanDate;

    private Date returnDate;

}
