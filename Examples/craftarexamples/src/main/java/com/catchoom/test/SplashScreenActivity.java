// com.craftar.craftarexamplesir is free software. You may use it under the MIT license, which is copied
// below and available at http://opensource.org/licenses/MIT
//
// Copyright (c) 2014 Catchoom Technologies S.L.
//
// Permission is hereby granted, free of charge, to any person obtaining a copy of
// this software and associated documentation files (the "Software"), to deal in
// the Software without restriction, including without limitation the rights to use,
// copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the
// Software, and to permit persons to whom the Software is furnished to do so, subject to
// the following conditions:
//
// The above copyright notice and this permission notice shall be included in all
// copies or substantial portions of the Software.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
// INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
// PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE
// FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
// OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
// DEALINGS IN THE SOFTWARE.

package com.catchoom.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;

import com.craftar.CraftARCloudRecognition;
import com.craftar.CraftARError;
import com.craftar.CraftARSDK;
import com.craftar.SetCloudCollectionListener;

public class SplashScreenActivity extends Activity implements SetCloudCollectionListener{

	private final static String TAG = "SplashScreenActivity";	

	//Collection token of the collection you want to load.
	//Note that you can load several collections at once, but every search 
	//request is performed only on ONE collection (the one that you have set through CraftARCloudRecognition.setCollection()).
	final static String COLLECTION_TOKEN="e49ae2f6f0784389";

	CraftARCloudRecognition mCloudIR;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.splash_screen);
			
		CraftARSDK.Instance().init(getApplicationContext());
		
		//Initialize the CloudIR Module
		mCloudIR = CraftARCloudRecognition.Instance();
			
		mCloudIR.setCollection(COLLECTION_TOKEN, this);		
		
    }
	
	@Override
	public void collectionReady() {
		Intent launchersActivity = new Intent( SplashScreenActivity.this, LaunchersActivity.class);
		startActivity(launchersActivity);
		finish();
	}

	@Override
	public void setCollectionFailed(CraftARError error) {
		Toast.makeText(getApplicationContext(), "setCollection failed! ("+error.getErrorCode()+"):"+error.getErrorMessage(), Toast.LENGTH_SHORT).show();
		//Error loading the collection into memory. No recognition can be performed unless a collection has been set.
		Log.e(TAG,"SetCollectionFailed ("+error.getErrorCode()+"):"+error.getErrorMessage());
		Toast.makeText(getApplicationContext(), "Error loading", Toast.LENGTH_SHORT).show();
	}
	
}
