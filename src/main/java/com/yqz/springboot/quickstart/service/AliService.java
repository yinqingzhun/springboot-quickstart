package com.yqz.springboot.quickstart.service;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import com.yqz.springboot.quickstart.util.JsonUtils;

@Service
public class AliService {

	public void callTicket(HttpServletRequest request, HttpServletResponse response) {
		String endpoint = "oss-cn-beijing.aliyuncs.com";
		String accessId = "LTAIgpGwLuRCXkIe";
		String accessKey = "8CEtUtOH5kNDwmBTxwJr9Wb0RWCVUe";
		String bucket = "bj-res";
		String dir = "user-dir/";
		String host = "http://" + bucket + "." + endpoint;
		OSSClient client = new OSSClient(endpoint, accessId, accessKey);
		try {
			long expireTime = 30;
			long expireEndTime = System.currentTimeMillis() + expireTime * 1000;
			Date expiration = new Date(expireEndTime);
			PolicyConditions policyConds = new PolicyConditions();
			policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);
			policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);

			String postPolicy = client.generatePostPolicy(expiration, policyConds);
			byte[] binaryData = postPolicy.getBytes("utf-8");
			String encodedPolicy = BinaryUtil.toBase64String(binaryData);
			String postSignature = client.calculatePostSignature(postPolicy);

			Map<String, String> respMap = new LinkedHashMap<String, String>();
			respMap.put("accessid", accessId);
			respMap.put("policy", encodedPolicy);
			respMap.put("signature", postSignature);
			// respMap.put("expire", formatISO8601Date(expiration));
			respMap.put("dir", dir);
			respMap.put("host", host);
			respMap.put("expire", String.valueOf(expireEndTime / 1000));
			String ja1 = JsonUtils.serialize(respMap);
			System.out.println(ja1.toString());
			response.setHeader("Access-Control-Allow-Origin", "*");
			response.setHeader("Access-Control-Allow-Methods", "GET, POST");
			response(request, response, ja1.toString());

		} catch (Exception e) {
		}
	}

	private void response(HttpServletRequest request, HttpServletResponse response, String results) throws IOException {
		String callbackFunName = request.getParameter("callback");
		if (callbackFunName == null || callbackFunName.equalsIgnoreCase(""))
			response.getWriter().println(results);
		else
			response.getWriter().println(callbackFunName + "( " + results + " )");
		response.setStatus(HttpServletResponse.SC_OK);
		response.flushBuffer();
	}
}
