package core.app.zh.com.core.view.verifyCodeView.sms;

public interface ReceiveSmsMessageListener {

    void onReceive(String smsSender, String smsBody);

}