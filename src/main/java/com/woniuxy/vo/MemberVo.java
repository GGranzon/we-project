package com.woniuxy.vo;


import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

@Data
@ToString
public class MemberVo {
    private String memberId;
    private String name;
    private String tel;
    private String cardNum;
    private String accountName;
    private String openingBank;
    private String accountNum;
    private String level;
    private BigDecimal balance;
    private BigDecimal frozenMoney;
    private BigDecimal overdraft;
    private Integer ableCount;
    private Integer allCount;
    private String distributorId;
    private String status;


}
