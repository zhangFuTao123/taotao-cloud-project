package com.taotao.cloud.payment.biz.daxpay.core.channel.alipay.service;

import cn.bootx.platform.common.core.util.BigDecimalUtil;
import cn.bootx.platform.daxpay.code.pay.PayChannelCode;
import cn.bootx.platform.daxpay.code.pay.PayStatusCode;
import cn.bootx.platform.daxpay.core.pay.local.AsyncPayInfoLocal;
import cn.bootx.platform.daxpay.core.payment.dao.PaymentManager;
import cn.bootx.platform.daxpay.core.payment.entity.Payment;
import cn.bootx.platform.daxpay.core.channel.alipay.dao.AliPaymentManager;
import cn.bootx.platform.daxpay.core.channel.alipay.entity.AliPayment;
import cn.bootx.platform.daxpay.dto.pay.AsyncPayInfo;
import cn.bootx.platform.daxpay.dto.payment.PayChannelInfo;
import cn.bootx.platform.daxpay.dto.payment.RefundableInfo;
import cn.bootx.platform.daxpay.exception.payment.PayFailureException;
import cn.bootx.platform.daxpay.param.pay.PayModeParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * 支付宝支付记录
 *
 * @author xxm
 * @date 2021/2/26
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AliPaymentService {

    private final AliPaymentManager aliPaymentManager;

    private final PaymentManager paymentManager;

    /**
     * 支付调起成功 更新 payment 中 异步支付类型信息
     */
    public void updatePaySuccess(Payment payment, PayModeParam payModeParam) {
        AsyncPayInfo asyncPayInfo = AsyncPayInfoLocal.get();
        payment.setAsyncPayMode(true).setAsyncPayChannel(PayChannelCode.ALI);
        List<PayChannelInfo> payTypeInfos = payment.getPayChannelInfo();
        List<RefundableInfo> refundableInfos = payment.getRefundableInfo();
        // 清除已有的异步支付类型信息
        payTypeInfos.removeIf(payTypeInfo -> PayChannelCode.ASYNC_TYPE.contains(payTypeInfo.getPayChannel()));
        refundableInfos.removeIf(payTypeInfo -> PayChannelCode.ASYNC_TYPE.contains(payTypeInfo.getPayChannel()));
        // 更新支付宝支付类型信息
        payTypeInfos.add(new PayChannelInfo().setPayChannel(PayChannelCode.ALI)
            .setPayWay(payModeParam.getPayWay())
            .setAmount(payModeParam.getAmount())
            .setExtraParamsJson(payModeParam.getExtraParamsJson()));
        payment.setPayChannelInfo(payTypeInfos);
        // 更新支付宝可退款类型信息
        refundableInfos.add(new RefundableInfo().setPayChannel(PayChannelCode.ALI).setAmount(payModeParam.getAmount()));
        payment.setRefundableInfo(refundableInfos);
        // 如果支付完成(付款码情况) 调用 updateSyncSuccess 创建支付宝支付记录
        if (Objects.equals(payment.getPayStatus(), PayStatusCode.TRADE_SUCCESS)) {
            this.createAliPayment(payment, payModeParam, asyncPayInfo.getTradeNo());
        }
    }

    /**
     * 更新异步支付记录成功状态, 并创建支付宝支付记录
     */
    public void updateAsyncSuccess(Long id, PayModeParam payModeParam, String tradeNo) {
        // 更新支付记录
        Payment payment = paymentManager.findById(id).orElseThrow(() -> new PayFailureException("支付记录不存在"));

    }

    /**
     * 创建支付宝支付记录
     */
    private void createAliPayment(Payment payment, PayModeParam payModeParam, String tradeNo) {
        // 创建支付宝支付记录
        AliPayment aliPayment = new AliPayment();
        aliPayment.setTradeNo(tradeNo)
            .setPaymentId(payment.getId())
            .setAmount(payModeParam.getAmount())
            .setRefundableBalance(payModeParam.getAmount())
            .setBusinessId(payment.getBusinessId())
            .setUserId(payment.getUserId())
            .setPayStatus(PayStatusCode.TRADE_SUCCESS)
            .setPayTime(LocalDateTime.now());
        aliPaymentManager.save(aliPayment);
    }

    /**
     * 取消状态
     */
    public void updateClose(Long paymentId) {
        Optional<AliPayment> aliPaymentOptional = aliPaymentManager.findByPaymentId(paymentId);
        aliPaymentOptional.ifPresent(aliPayment -> {
            aliPayment.setPayStatus(PayStatusCode.TRADE_CANCEL);
            aliPaymentManager.updateById(aliPayment);
        });
    }

    /**
     * 更新退款
     */
    public void updatePayRefund(Long paymentId, BigDecimal amount) {
        Optional<AliPayment> aliPaymentOptional = aliPaymentManager.findByPaymentId(paymentId);
        aliPaymentOptional.ifPresent(payment -> {
            BigDecimal refundableBalance = payment.getRefundableBalance().subtract(amount);
            payment.setRefundableBalance(refundableBalance);
            if (BigDecimalUtil.compareTo(refundableBalance, BigDecimal.ZERO) == 0) {
                payment.setPayStatus(PayStatusCode.TRADE_REFUNDED);
            }
            else {
                payment.setPayStatus(PayStatusCode.TRADE_REFUNDING);
            }
            aliPaymentManager.updateById(payment);
        });
    }

}
