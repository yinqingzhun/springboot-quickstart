package com.yqz.springboot.quickstart.service;

import java.io.IOException;
import java.security.MessageDigest;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.yqz.springboot.quickstart.model.SmsReturnValue;
import com.yqz.springboot.quickstart.util.JsonUtils;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Service
public class SmsService {
	private final String APP_KEY = "";

	public SmsReturnValue sendCode(String phone) {
		String url = "https://api.netease.im/sms/sendcode.action";
		OkHttpClient okHttpClient = new OkHttpClient();

		RequestBody body = new FormBody.Builder().add("phone", phone).build();

		Request.Builder requestBuilder = new Request.Builder();
		HashMap<String, String> headers = getPostHeaders();
		headers.entrySet().stream().forEach(p -> requestBuilder.addHeader(p.getKey(), p.getValue()));
		Request request = requestBuilder.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8")
				.url(url).post(body).build();

		Call call = okHttpClient.newCall(request);
		try {
			Response response = call.execute();
			if (response.code() == 200) {
				SmsReturnValue result = JsonUtils.deserialize(SmsReturnValue.class, response.body().string());
				return result;
			}
			System.out.println(response.body().string());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public SmsReturnValue verifyCode(String phone, String code) {
		String url = "https://api.netease.im/sms/verifycode.action";
		OkHttpClient okHttpClient = new OkHttpClient();

		RequestBody body = new FormBody.Builder().add("phone", phone).add("code", code).build();

		Request.Builder requestBuilder = new Request.Builder();
		HashMap<String, String> headers = getPostHeaders();
		headers.entrySet().stream().forEach(p -> requestBuilder.addHeader(p.getKey(), p.getValue()));
		Request request = requestBuilder.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8")
				.url(url).post(body).build();

		Call call = okHttpClient.newCall(request);
		try {
			Response response = call.execute();
			if (response.code() == 200) {
				SmsReturnValue result = JsonUtils.deserialize(SmsReturnValue.class, response.body().string());
				return result;
			}
			System.out.println(response.body().string());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private HashMap<String, String> getPostHeaders() {
		HashMap<String, String> map = new HashMap<String, String>();
		String nonce = getNonce();
		String time = getCurTime().toString();
		String checksum = getCheckSum(APP_KEY, nonce, time);
		map.put("AppKey", APP_KEY);
		map.put("Nonce", nonce);
		map.put("CurTime", time);
		map.put("CheckSum", checksum);
		return map;
	}

	private Long getCurTime() {
		return new Date().getTime() / 1000;
	}

	private String getNonce() {
		return UUID.randomUUID().toString();
	}

	// 计算并获取CheckSum
	public static String getCheckSum(String appSecret, String nonce, String curTime) {
		return encode("sha1", appSecret + nonce + curTime);
	}

	// 计算并获取md5值
	public static String getMD5(String requestBody) {
		return encode("md5", requestBody);
	}

	private static String encode(String algorithm, String value) {
		if (value == null) {
			return null;
		}
		try {
			MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
			messageDigest.update(value.getBytes());
			return getFormattedText(messageDigest.digest());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static String getFormattedText(byte[] bytes) {
		int len = bytes.length;
		StringBuilder buf = new StringBuilder(len * 2);
		for (int j = 0; j < len; j++) {
			buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
			buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
		}
		return buf.toString();
	}

	private static final char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
			'e', 'f' };
}
