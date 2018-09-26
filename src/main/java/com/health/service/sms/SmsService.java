package com.health.service.sms;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import com.health.bo.clinic.ClinicQueryBo;
import com.health.common.Constant;
import com.health.dao.clinic.ClinicDao;
import com.health.entity.clinic.ClinicEntity;
import com.health.service.BaseService;
import com.health.transfer.clinic.ClinicTransfer;
import org.json.JSONException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.core.HttpHeaders;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author henry
 */
public class SmsService extends BaseService {
    
    /**
     * 调用短信接口发送短信
     * @param mobile
     * @param message
     * @param extend
     * @param ext
     * @return
     */
    public int sendMessage(String mobile, String message, String extend, String ext){
        int result = -1;
        try {
            SmsSingleSender sender = new SmsSingleSender(Constant.APP_ID, Constant.APP_KEY);
            SmsSingleSenderResult callBack = sender.send(Constant.SMS_TYPE, Constant.NATIONAL_CODE, mobile, message, extend, ext);
            System.out.println(callBack);
            result = callBack.result;
        } catch (HTTPException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    /**
     * 调用短信接口发送短信
     * @param mobile
     * @param templateId
     * @param params
     * @return
     */
    public int sendWithParam(String mobile, int templateId, String[] params){
        int result = -1;
        try {
            SmsSingleSender sender = new SmsSingleSender(Constant.APP_ID, Constant.APP_KEY);
            SmsSingleSenderResult callBack = sender.sendWithParam(Constant.NATIONAL_CODE, mobile, templateId, params, Constant.SMS_SIGN,"","");
            System.out.println(callBack);
            result = callBack.result;
        } catch (HTTPException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
