package cn.bitflash.util;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Random;

/**
 * @author wangjun
 */
public class Common {

	// 卖出
	public final static String STATE_SELL = "1";

	// 撤消
	public final static String STATE_CANCEL = "3";

	// 已付款
	public final static String STATE_PAY = "4";

	// 已锁定
	public final static String STATE_LOCK = "5";

	// 待确认
	public final static String STATE_CONFIRM = "6";

	// 申拆中
	public final static String STATE_COMPLAINT = "9";

	//------------------------求购------------------------

	//订单撤销
	public final static String ORDER_STATE_CANCEL = "0";
	//订单取消
	public final static String ORDER_STATE_PUBLISH = "1";
	//待付款 -- 待收款
	public final static String ORDER_STATE_STEP1 = "2";
	//待收币 -- 待确认
	public final static String ORDER_STATE_STEP2 = "3";
	//订单完成
	public final static String ORDER_STATE_FINISH = "6";
	//订单申诉
	public final static String ORDER_STATE_APPEAL = "9";
	//------------------finish----------------------


	//----------------code值---------------------

	public final static String SUCCESS = "0";
	//交易失败
	public final static String FAIL = "1";
	//交易不存在
	public final static String TRADEMISS = "2";
	//------------------finish----------------------

	// 最小价格
	public final static String MIN_PRICE = "0.33";

	public final static Double MIN_NUMBER = 100d;

	// 最小价格
	public final static Double MULTIPLE = 10d;

	// 超时时间(毫秒)
	public final static String OUT_TIME = "outTime";

	// 连接地址
	public final static String ADDRESS = "address";

	// vip等级0
	public final static String VIP_LEVEL_0 = "0";

	// 锁定订单次数
	public final static String LOCK_TRADE = "lock_trade";



	// redis缓存订单号key
	public final static String ADD_LOCKNUM = "userBuy_";


	// 发送信息标题内容值
	public final static String MSG_TEXT = "text_msg";

	public final static double poundage = 0.025;


	public final static String TOKEN = "token";
	public final static String MOBILE = "mobile";


	//申拆状态:未处理0
	public final static String COMPLAINT_NO = "0";

	/**
	 * 生成8位随机数
	 *
	 * @return
	 */
	public static String randomUtil() {

		// 字符源，可以根据需要删减
		String generateSource = "1234567890";// 去掉1和i ，0和o
		String rtnStr = "";
		for (int i = 0; i < 8; i++) {
			// 循环随机获得当次字符，并移走选出的字符
			String nowStr = String
					.valueOf(generateSource.charAt((int) Math.floor(Math.random() * generateSource.length())));
			rtnStr += nowStr;
			generateSource = generateSource.replaceAll(nowStr, "" );
		}
		return rtnStr.toUpperCase();
	}

	public static void main(String[] arg) {
		System.out.println(Common.randomUtil());
	}


	/**
	 *
	 * 保留两个小数，并不进行四舍五入
	 * 100.00 > 100.00
	 * 100.10 > 100.10
	 * 100.01 > 100.01
	 * @return
	 */
	public static String decimalFormat ( double d){
		if (d > 0) {
			DecimalFormat df = new DecimalFormat("######0.00");
			String str = df.format(d);
			return str;
		} else {
			return null;
		}
	}
}
