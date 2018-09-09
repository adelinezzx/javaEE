
var xmlHttp;

	function createXMLHttpRequest() {
		if (window.XMLHttpRequest) {//如果可以取得XMLHttpRequest
			
			xmlHttp = new XMLHttpRequest(); //mozilia   firefox   safari

		} else if (window.ActiveObject) { //如果可以取到ActiveObject
			
			xmlHttp = new ActiveObject("Microsoft.XMLHttp"); // Internet explorer 
		}
	}
	/**
	 * 
	 * @param url  请求的地址
	 * @param params   要发给服务器参数
	 * @param func   回调函数名
	 */
	function ajaxSend(url, params,func) {
		createXMLHttpRequest();
		xmlHttp.onreadystatechange = func;   //回调函数
		xmlHttp.open("POST", url);
		xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		xmlHttp.send(params);
	}