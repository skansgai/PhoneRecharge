package huawei.com.activity;

import java.util.Map;

import com.alipay.sdk.app.AuthTask;
import com.alipay.sdk.app.EnvUtils;
import com.alipay.sdk.app.PayTask;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import huawei.com.R;
import huawei.com.alipay.AuthResult;
import huawei.com.alipay.PayResult;
import huawei.com.util.OrderInfoUtil2_0;
public class PayDemoActivity extends FragmentActivity {

	/** 支付宝支付业务：入参app_id */
	public static final String APPID = "2016081600257197";

	/** 支付宝账户登录授权业务：入参pid值 */
	public static final String PID = "2088102171399720";
	/** 支付宝账户登录授权业务：入参target_id值 */
	public static final String TARGET_ID = "urcvnx2393@sandbox.com";

	/** 工具地址：https://doc.open.alipay.com/docs/doc.htm?treeId=291&articleId=106097&docType=1 */
	public static final String RSA2_PRIVATE = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCrCsCbEfPkY/r2WpTYe9syxx7OKLOCWxCJCfy2Jtf5DgRO56VKrsamrcX1uCLHQ6yFUJ/5d+bS5XugjBCh35sGHL2RdCICHvGl0l4aU0gbCAXMd1YaKpi4ASUz2Y+sA2QtdwIQiKeJ+l5eZM6SzroCIa3COhA16VzQ9DV3tA5f5UVKUwJ1dgy5mbiQsYxPsqnOxdsu03uzN5K31AMDS4sPKrR7rfHpE7Yf3PeeCeroz6KJVwe0+fWNWTcQLCQPP9BwBDHo9zIeUeauwH3NTeQt8aopH6M0bMdGj1CeHu/V5gzm6mx6VijyaQVoByD3dBwanZHFbBElB7Ac69ZCEIw5AgMBAAECggEBAJ24p40XS/ByZYxgUNsjsyiqxvRSQd3VvXSw2fDZ9tP5oe1k3F32tsYifeYtKxzWgbLZevsfHqGg7ZRJNFkgiaJkREZpcabFBA3l49Kgs2AB0N8PuD0exH8Ax159o2d6JRlroWK/sMaiMf0XNgnYSVwH1GPDCQm9rFIer2M3jP3NOmEZXPGmfLscecuGRUyAvVK6o1dd2L+bkb1O3CH4E5xMDlnlwtFZ+vH/eCTryvy+KhHwy+HDddDzSY/YKnvxdJT3xotdpSHVtc0Shh8mwv/FhLlTkbEmwbeYHrldS7kPiXso22N6d9S2axZP1CL3t7oB7d/zOmlHdstW0dIrqmUCgYEA0gSr64Ju8y80hTE20nI3W4Hl62h67uNfrgb1h4QwjDEbz37HLBgVvZ8Lv4p++DR+f2UIEPD2cS/QkUNk+WxVoH9VUfIdcHtcBDPJGYwvhLbG9Rf5UoeVYi1xKrQ03rT/LiipX2ZfSluYxTSqjcn58G01FY1K7L3jBcTcFAoZPQMCgYEA0H1/jLvid/gYvDyrzmLB714NYql0dhD9KTkVGuFeiaUDGB4mbO4Nc8/Sq+KkO9RvM56rLQ/EMppM1gb92D1XiR7z1QSAs/xfWAZCszqyfMS/rQ2OZEbZ6nuafBHTpRbxEX0Y5z1ghDPodf73OF11yZzn4LrIbr1xBmQV0eEnVxMCgYAiGZIUhjQfli2P5sLA/ryQRhIf2/IB8bcqWYTqQQNk7+BVG3+ZYDu/cyCENCeTg2p6otvhbE6HaD0UKP4vJXDtw18GFCrFhbIWGJZxaHqasNazGvZneQ4/R6OVTHc9JeQ9CWsey09zklXGx1td9Ep5yDGTU0K0F8zRtfoJ4u3gIwKBgQC/K+QR6pHNSYt8xvEOjJWhZA9IAXONcyR/cRz+CdrCwbDlo3e+eEWThON6wpWvWqogngP9jLBtafi8Y6rNZennDJ+uGrPc1bOjJhJw/4h8SXgsOPrwzdqxXGcaDsqPXUrsq1Vza18due8Uixaq81+2343rDboNeN2za4Bqc1a8SwKBgBQuSyF65y8oNnFN/QJ5JEO5nYVQzMwRZpaQJx775RhkPOr58O5qXvDdwxN9XXiAqCjL79qlcmfJgTdxTUOB2HkhjkbXj69M1/X0+9TECBCOw5cupYfl/42VMHVnz6GwgbDmHsqUJQ7rhcMzw1Xsi/OuC/fYSERw2l4qSk7NKo61";
	public static final String RSA_PRIVATE = "";

	private static final int SDK_PAY_FLAG = 1;
	private static final int SDK_AUTH_FLAG = 2;

	@SuppressLint("HandlerLeak")
	private Handler mHandler = new Handler() {
		@SuppressWarnings("unused")
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case SDK_PAY_FLAG: {
				@SuppressWarnings("unchecked")
				PayResult payResult = new PayResult((Map<String, String>) msg.obj);
				/**
				 对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
				 */
				String resultInfo = payResult.getResult();// 同步返回需要验证的信息
				String resultStatus = payResult.getResultStatus();
				if (TextUtils.equals(resultStatus, "9000")) {
					Toast.makeText(PayDemoActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(PayDemoActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
				}
				break;
			}
			case SDK_AUTH_FLAG: {
				AuthResult authResult = new AuthResult((Map<String, String>) msg.obj, true);
				String resultStatus = authResult.getResultStatus();
				if (TextUtils.equals(resultStatus, "9000") && TextUtils.equals(authResult.getResultCode(), "200")) {
					Toast.makeText(PayDemoActivity.this,
							"授权成功\n" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT)
							.show();
				} else {
					Toast.makeText(PayDemoActivity.this,
							"授权失败" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT).show();
				}
				break;
			}
			default:
				break;
			}
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pay_main);
		EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);
	}
	
	/**
	 * 支付宝支付业务
	 * @param v
	 */
	public void payV2(View v) {
		if (TextUtils.isEmpty(APPID) || (TextUtils.isEmpty(RSA2_PRIVATE) && TextUtils.isEmpty(RSA_PRIVATE))) {
			new AlertDialog.Builder(this).setTitle("警告").setMessage("需要配置APPID | RSA_PRIVATE")
					.setPositiveButton("确定", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialoginterface, int i) {
							finish();
						}
					}).show();
			return;
		}

        boolean rsa2 = (RSA2_PRIVATE.length() > 0);
		Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(APPID, rsa2);
		String orderParam = OrderInfoUtil2_0.buildOrderParam(params);

		String privateKey = rsa2 ? RSA2_PRIVATE : RSA_PRIVATE;
		String sign = OrderInfoUtil2_0.getSign(params, privateKey, rsa2);
		final String orderInfo = orderParam + "&" + sign;
		
		Runnable payRunnable = new Runnable() {

			@Override
			public void run() {
				PayTask alipay = new PayTask(PayDemoActivity.this);
				Map<String, String> result = alipay.payV2(orderInfo, true);
				Log.i("msp", result.toString());
				
				Message msg = new Message();
				msg.what = SDK_PAY_FLAG;
				msg.obj = result;
				mHandler.sendMessage(msg);
			}
		};

		Thread payThread = new Thread(payRunnable);
		payThread.start();
	}

	/**
	 * 支付宝账户授权业务
	 * @param v
	 */
	public void authV2(View v) {
		if (TextUtils.isEmpty(PID) || TextUtils.isEmpty(APPID)
				|| (TextUtils.isEmpty(RSA2_PRIVATE) && TextUtils.isEmpty(RSA_PRIVATE))
				|| TextUtils.isEmpty(TARGET_ID)) {
			new AlertDialog.Builder(this).setTitle("警告").setMessage("需要配置PARTNER |APP_ID| RSA_PRIVATE| TARGET_ID")
					.setPositiveButton("确定", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialoginterface, int i) {
						}
					}).show();
			return;
		}

		boolean rsa2 = (RSA2_PRIVATE.length() > 0);
		Map<String, String> authInfoMap = OrderInfoUtil2_0.buildAuthInfoMap(PID, APPID, TARGET_ID, rsa2);
		String info = OrderInfoUtil2_0.buildOrderParam(authInfoMap);
		
		String privateKey = rsa2 ? RSA2_PRIVATE : RSA_PRIVATE;
		String sign = OrderInfoUtil2_0.getSign(authInfoMap, privateKey, rsa2);
		final String authInfo = info + "&" + sign;
		Runnable authRunnable = new Runnable() {

			@Override
			public void run() {
				// 构造AuthTask 对象
				AuthTask authTask = new AuthTask(PayDemoActivity.this);
				// 调用授权接口，获取授权结果
				Map<String, String> result = authTask.authV2(authInfo, true);

				Message msg = new Message();
				msg.what = SDK_AUTH_FLAG;
				msg.obj = result;
				mHandler.sendMessage(msg);
			}
		};

		// 必须异步调用
		Thread authThread = new Thread(authRunnable);
		authThread.start();
	}
	
	/**
	 * get the sdk version. 获取SDK版本号
	 */
	public void getSDKVersion() {
		PayTask payTask = new PayTask(this);
		String version = payTask.getVersion();
		Toast.makeText(this, version, Toast.LENGTH_SHORT).show();
	}

	/**
	 * 原生的H5（手机网页版支付切natvie支付） 【对应页面网页支付按钮】
	 * @param v
	 */
	public void h5Pay(View v) {
		Intent intent = new Intent(this, H5PayDemoActivity.class);
		Bundle extras = new Bundle();
		String url = "https://openapi.alipaydev.com/gateway.do";
		extras.putString("url", url);
		intent.putExtras(extras);
		startActivity(intent);
	}

}
