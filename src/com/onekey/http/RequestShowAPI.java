package com.onekey.http;

import mythware.common.LogUtils;
import mythware.http.CloudUpdateVersionServer.CloudInterface;
import mythware.http.CloudUpdateVersionServer.CloudResponseStatus;
import mythware.http.DataManagerInterface;
import mythware.http.DataUtils;
import mythware.http.NetworkAsyncTask;

import com.onekey.bean.PicBean;

public class RequestShowAPI {
	private final String URL_CLOUD = "http://route.showapi.com";
	private final String URL_PIC = URL_CLOUD + "/197-1";
	
	private static RequestShowAPI mClassCloudManager;
	public static synchronized RequestShowAPI getInstance() {
		if (mClassCloudManager == null) {
			mClassCloudManager = new RequestShowAPI();
		}
		return mClassCloudManager;
	}
	
	public void getInfo(final CloudInterface cloudInterface, String timestamp, int page) {
		NetworkAsyncTask networkAsyncTask = new NetworkAsyncTask(new DataManagerInterface() {
			
			@Override
			public void getDataOnResult(String responseBody) {
				LogUtils.i("wzw responseBody:" + responseBody);
				if (responseBody == null) {
					cloudInterface.cloudCallback(CloudResponseStatus.ErrorNetwork, null);
					return;
				}
				PicBean info = (PicBean) DataUtils.getObject(PicBean.class, responseBody);
				if (info.getShowapi_res_body().getCode() == 200 && info.getShowapi_res_body().getMsg().equals("success")) {
					cloudInterface.cloudCallback(CloudResponseStatus.Succ, info);
				} else {
					cloudInterface.cloudCallback(CloudResponseStatus.Failed, info);
				}
			}
		});
		String url = URL_PIC + "?num=10&page=" + page + "&rand=1&showapi_appid=52718&showapi_test_draft=false&showapi_timestamp=" + timestamp + "&showapi_sign=dc53a00937ef467992c7adbc505448cf";
		networkAsyncTask.setUrl(url).setRequestType(NetworkAsyncTask.TYPE_GET).execute();
	}
}
