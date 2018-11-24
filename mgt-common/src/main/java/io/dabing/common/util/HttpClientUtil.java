package io.dabing.common.util;

import com.alibaba.fastjson.JSON;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 使用apache httpclient 4.3以上的版本jar 提供httpclient连接工具类
 * 
 * @author YangKui
 * 
 */
public class HttpClientUtil {
	
	private static Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);
	private static PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
	static {
		cm.setMaxTotal(800);
		cm.setDefaultMaxPerRoute(800);
	}

	/**
	 * 通过get方式获取指定地址的内容
	 * 
	 * @param url
	 *            需要访问的地址如：http://www.baidu.com
	 * @param chartset
	 *            字符编码，将地址返回的内容进行字符编码，如果为空则默认为：UTF-8
	 * @return 地址对应的内容
	 */
	public static String get(String url, int socketTime, int connectTimeout, String chartset)
			throws ClientProtocolException, IOException {
		CloseableHttpClient httpclient = HttpClients.custom().setConnectionManager(cm).build();
		RequestConfig requetConfig = RequestConfig.custom().setSocketTimeout(socketTime)
				.setConnectTimeout(connectTimeout).build();
		HttpGet httpGet = new HttpGet(url);
		httpGet.setConfig(requetConfig);
		CloseableHttpResponse response1 = null;
		try {
			response1 = httpclient.execute(httpGet);
			HttpEntity entity1 = response1.getEntity();
			if (chartset == null || "".equals(chartset)) {
				chartset = "UTF-8";
			}
			String responseBody = EntityUtils.toString(entity1, chartset);
			EntityUtils.consume(entity1);
			StatusLine statusLine = response1.getStatusLine();
			int statusCode = statusLine.getStatusCode();
			if(statusCode!=200){
				byte[] contextByte = Base64.encodeBase64(responseBody.getBytes(), true);  
			    String responseBodyLog = new String(contextByte);
				logger.error("current request url error,satusCode:{},responseBody:{}",statusCode,responseBodyLog);
				throw new IOException("request url statusCode is 500!");
			}
			return responseBody;
		} finally {
			if (response1 != null) {
				response1.close();
			}
		}
	}

	/**
	 * 使用post方式提交参数
	 * 
	 * @param url
	 * @param params
	 *            提交的参数已key,value的形式保存在map当中
	 * @param socketTime
	 * @param connectTimeout
	 * @param chartset
	 * @return
	 * @throws org.apache.http.client.ClientProtocolException
	 * @throws IOException
	 */
	public static String post(String url, Map<String, String> params, int socketTime, int connectTimeout,
			String chartset) throws ClientProtocolException, IOException {
		CloseableHttpClient httpclient = HttpClients.custom().setConnectionManager(cm).build();
		RequestConfig requetConfig = RequestConfig.custom().setSocketTimeout(socketTime)
				.setConnectTimeout(connectTimeout).build();
		HttpPost httpPost = new HttpPost(url);
		httpPost.setConfig(requetConfig);
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		for (Map.Entry<String, String> entry : params.entrySet()) {
			nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
		}
		if (chartset == null || "".equals(chartset)) {
			chartset = "UTF-8";
		}
		httpPost.setEntity(new UrlEncodedFormEntity(nvps, chartset));
		CloseableHttpResponse response1 = null;
		try {
			response1 = httpclient.execute(httpPost);
			HttpEntity entity1 = response1.getEntity();
			// do something useful with the response body
			// and ensure it is fully consumed
			String responseBody = EntityUtils.toString(entity1, chartset);
			EntityUtils.consume(entity1);
			StatusLine statusLine = response1.getStatusLine();
			int statusCode = statusLine.getStatusCode();
			if(statusCode!=200){
				byte[] contextByte = Base64.encodeBase64(responseBody.getBytes(), true);  
			    String responseBodyLog = new String(contextByte);
				logger.error("current request url error,satusCode:{},responseBody:{}",statusCode,responseBodyLog);
				throw new IOException("request url statusCode is 500!");
			}
			return responseBody;
		} finally {
			if (response1 != null) {
				response1.close();
			}
		}
	}

	/**
	 * 使用post格式提交请求一段整体内容，可以是xml或json格式
	 * @param url 请求路径
	 * @param content xml报文
	 * @param socketTime 连接时间（单位毫秒）
	 * @param connectTimeout 连接等待时间（单位毫秒）
	 * @param reqChartset 请求报文字符编码，默认为UTF-8
	 * @param respChartset 返回报文字符编码，默认为UTF-8
	 * @param contentType http内容类型
	 * @return
	 * @throws org.apache.http.client.ClientProtocolException
	 * @throws IOException
	 */
	public static String post(String url, String content, int socketTime, int connectTimeout,String reqChartset, String respChartset, String contentType)
			throws  IOException {
		CloseableHttpClient httpclient = HttpClients.custom().setConnectionManager(cm).build();
		RequestConfig requetConfig = RequestConfig.custom().setSocketTimeout(socketTime)
				.setConnectTimeout(connectTimeout).build();
		HttpPost httpPost = new HttpPost(url);
		httpPost.setConfig(requetConfig);
		if (reqChartset == null || "".equals(reqChartset)) {
			reqChartset = "UTF-8";
		}
		StringEntity myEntity = new StringEntity(content, reqChartset);
		httpPost.addHeader("Content-Type", contentType);
		httpPost.setEntity(myEntity);
		CloseableHttpResponse response1 = null;
		try {
			response1 = httpclient.execute(httpPost);
			HttpEntity entity1 = response1.getEntity();
			if (respChartset == null || "".equals(respChartset)) {
				respChartset = "UTF-8";
			}
			StatusLine statusLine = response1.getStatusLine();
			int statusCode = statusLine.getStatusCode();
			if(statusCode!=200){
				logger.error("current request url error,satusCode:{},ReasonPhrase:{}",statusCode,statusLine.getReasonPhrase());
				throw new IOException("request url statusCode is 500!");
			}
			String responseBody = EntityUtils.toString(entity1, respChartset);
			EntityUtils.consume(entity1);
			return responseBody;
		} finally {
			if (response1 != null)
				response1.close();
		}
	}

	/**
	 * 使用默认参数发送xml格式内容
	 * 默认：
	 * 	1.超时时间为30秒
	 * 	2.编码为utf-8
	 * @throws IOException
	 */
	public static String postXml(String url, String content) throws  IOException{
		return post(url,content,30000, 30000, "UTF-8", "UTF-8", "application/xml; charset=UTF-8");
	}
	/**
	 * 使用默认参数发送JSON格式内容
	 * 默认：
	 * 	1.超时时间为30秒
	 * 	2.编码为utf-8
	 * @throws IOException
	 */
	public static String postJSON(String url, String content) throws  IOException{
		return post(url,content,20000, 20000, "UTF-8", "UTF-8", "application/json; charset=UTF-8");
	}

	public static void main(String[] args)  {
		testJson();
	}

	public static void testXml(){
		String retval = "";
		//String reqContent = "<PackageList><Package><Header><Version>2</Version><RequestType>230</RequestType><SessionId>WX_211307</SessionId><InsureType>100</InsureType><SendTime>2014-05-20 11:40:12</SendTime><SellerId>GW000001</SellerId><Status>100</Status><ErrorMessage>?б????</ErrorMessage></Header><Response><Order><TBOrderId>211307</TBOrderId><Premium>852028.00</Premium><PayNo></PayNo><SubOrderList><SubOrder type=\"biz\"><TBOrderId>211307</TBOrderId><ItemId>211307</ItemId><Premium>791528.00</Premium>					<ProposalNo>TDDK201464010899000001</ProposalNo><PolicyNo>PDDK201464010899000001</PolicyNo></SubOrder><SubOrder type=\"force\"><TBOrderId>211307</TBOrderId><ItemId>211307</ItemId><Premium>60500.00</Premium><ProposalNo>TDDK201464010899000001</ProposalNo><PolicyNo>PDDK201464010899000001</PolicyNo></SubOrder></SubOrderList></Order></Response><Sign/></Package></PackageList> ";
		String xml = "" +
				"<xml>\n" +
				"  <appid><![CDATA[wx2421b1c4370ec43b]]></appid>\n" +
				"  <attach><![CDATA[支付测试]]></attach>\n" +
				"  <bank_type><![CDATA[CFT]]></bank_type>\n" +
				"  <fee_type><![CDATA[CNY]]></fee_type>\n" +
				"  <is_subscribe><![CDATA[Y]]></is_subscribe>\n" +
				"  <mch_id><![CDATA[10000100]]></mch_id>\n" +
				"  <nonce_str><![CDATA[5d2b6c2a8db53831f7eda20af46e531c]]></nonce_str>\n" +
				"  <openid><![CDATA[oUpF8uMEb4qRXf22hE3X68TekukE]]></openid>\n" +
				"  <out_trade_no><![CDATA[1409811653]]></out_trade_no>\n" +
				"  <result_code><![CDATA[SUCCESS]]></result_code>\n" +
				"  <return_code><![CDATA[SUCCESS]]></return_code>\n" +
				"  <sign><![CDATA[93D42280CBAE1DE7684686DEBC1DBDE3]]></sign>\n" +
				"  <sub_mch_id><![CDATA[10000100]]></sub_mch_id>\n" +
				"  <time_end><![CDATA[20140903131540]]></time_end>\n" +
				"  <total_fee>1</total_fee>\n" +
				"  <trade_type><![CDATA[JSAPI]]></trade_type>\n" +
				"  <transaction_id><![CDATA[1004400740201409030005092168]]></transaction_id>\n" +
				"</xml>";
		try{
			retval = HttpClientUtil.postXml("http://localhost:8080/chuangdao/pay/wx/notify.do", xml );
		}catch(Exception e){
			logger.error("request error", e);
		}

		System.out.println("#####" + retval);
	}
	//测试调用微信消息通知接口
	public static void testJson(){
		String retval = "";
		String json = "";
		Map<String,Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("touser","oznXBviosXdISFhAzIrxtZxR9V0g");
		jsonMap.put("template_id","eFqLLddk4BCSxuQMrDJRBzBlDETR-zT38g6-XXU2pA0");
		jsonMap.put("url","http://www.baidu.com");
		Map<String,Object> dataMap = new HashMap<String, Object>();

		Map<String,String> valueMap0 = new HashMap<String, String>();
		valueMap0.put("value","恭喜您购买成功!");
		valueMap0.put("color","#173177");
		dataMap.put("first",valueMap0);

		Map<String,String> valueMap = new HashMap<String, String>();
		valueMap.put("value","123165467");
		valueMap.put("color","#173177");
		dataMap.put("orderNo",valueMap);

		Map<String,String> valueMap2 = new HashMap<String, String>();
		valueMap.put("value","100.56");
		valueMap.put("color","#173177");
		dataMap.put("amount",valueMap2);

		jsonMap.put("data",dataMap);
		try{
			String access_token = HttpClientUtil.get("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx5a87a16cc030e471&secret=e36b66a13de9733e5f3ee4129620c146",30000,30000,"UTF-8");
			System.out.println(access_token);
			Map<String,String> map  = (Map<String, String>) JSON.parse(access_token);

			System.out.println("access_token:"+map.get("access_token"));
			json = JSON.toJSONString(jsonMap);
			System.out.println(json);
			retval = HttpClientUtil.postJSON("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+map.get("access_token"), json );
		}catch(Exception e){
			logger.error("request error", e);
		}
		System.out.println("#####" + retval);
	}

}
