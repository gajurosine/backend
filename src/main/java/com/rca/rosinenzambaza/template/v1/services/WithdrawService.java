package com.rca.rosinenzambaza.template.v1.services;

import com.rca.rosinenzambaza.template.v1.dto.requests.CreateWithdrawDTO;

import com.rca.rosinenzambaza.template.v1.models.Withdraw;

import java.util.UUID;

public interface WithdrawService {
    Withdraw createWithdraw(CreateWithdrawDTO createWithdrawDTO);
    Withdraw getWithdrawById(UUID withdrawId);

    Withdraw updateWithdraw(UUID uuid, CreateWithdrawDTO createWithdrawDTO);
}
