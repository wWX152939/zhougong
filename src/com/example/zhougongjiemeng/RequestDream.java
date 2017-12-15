package com.example.zhougongjiemeng;

import mythware.common.LogUtils;
import mythware.http.CloudUpdateVersionServer.CloudInterface;
import mythware.http.CloudUpdateVersionServer.CloudResponseStatus;
import mythware.http.DataManagerInterface;
import mythware.http.DataUtils;
import mythware.http.NetworkAsyncTask;

public class RequestDream {
	private final String URL_CLOUD = "http://v.juhe.cn/dream/query";
	
	private static RequestDream mClassCloudManager;
	public static synchronized RequestDream getInstance() {
		if (mClassCloudManager == null) {
			mClassCloudManager = new RequestDream();
		}
		return mClassCloudManager;
	}
	
	public void getInfo(final CloudInterface cloudInterface, String content) {
		NetworkAsyncTask networkAsyncTask = new NetworkAsyncTask(new DataManagerInterface() {
			
			@Override
			public void getDataOnResult(String responseBody) {
				LogUtils.i("wzw responseBody:" + responseBody);
				if (responseBody == null) {
					cloudInterface.cloudCallback(CloudResponseStatus.ErrorNetwork, null);
					return;
				}
				DreamBean info = (DreamBean) DataUtils.getObject(DreamBean.class, responseBody);
				if (info.getError_code() == 0 && info.getReason().equals("successed")) {
					cloudInterface.cloudCallback(CloudResponseStatus.Succ, info);
				} else {
					cloudInterface.cloudCallback(CloudResponseStatus.Failed, info);
				}
			}
		});
		String url = URL_CLOUD + "?q=" + content + "&cid=&full=1&key=4f20b298eb4fac360eb395bef6eab771";
		networkAsyncTask.setUrl(url).setRequestType(NetworkAsyncTask.TYPE_GET).execute();
	}
}
