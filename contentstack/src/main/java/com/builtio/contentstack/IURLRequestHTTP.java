package com.builtio.contentstack;

import android.support.v4.util.ArrayMap;

import com.builtio.contentstack.utilities.CSAppConstants;

import org.json.JSONObject;

/**
 * 
 * @author built.io, Inc
 *
 */
public interface IURLRequestHTTP {
	
	public void send();

    public void setHeaders(ArrayMap headers);

    public ArrayMap getHeaders();

    public void setRequestMethod(CSAppConstants.RequestMethod requestMethod);

    public CSAppConstants.RequestMethod getRequestMethod();

    public JSONObject getResponse();
    
    public void setInfo(String info);

    public String getInfo();
    
    public void setController(String controller);

    public String getController();
    
    public void setCallBackObject(ResultCallBack builtResultCallBackObject);

    public ResultCallBack getCallBackObject();

    public void setTreatDuplicateKeysAsArrayItems(boolean treatDuplicateKeysAsArrayItems);

    public boolean getTreatDuplicateKeysAsArrayItems();
    
    
}
