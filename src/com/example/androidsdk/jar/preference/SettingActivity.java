package com.example.androidsdk.jar.preference;

import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.androidsdk.R;

public class SettingActivity extends PreferenceActivity implements OnPreferenceChangeListener{

	private CheckBoxPreference mCheckBoxPreference1,mCheckBoxPreference2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.setting_preference);
		mCheckBoxPreference1 = (CheckBoxPreference)getPreferenceScreen().findPreference(
                "openvoice");
		mCheckBoxPreference1.setOnPreferenceChangeListener(this);
		
		mCheckBoxPreference2 = (CheckBoxPreference)getPreferenceScreen().findPreference(
		        "openshock");
		mCheckBoxPreference2.setOnPreferenceChangeListener(this);
	}
	 
	@Override
	public boolean onPreferenceChange(Preference preference, Object newValue) {
		if(preference==mCheckBoxPreference1){
			if((Boolean)newValue){
				Toast.makeText(this, "openvoice", Toast.LENGTH_SHORT).show();
			}else{
				Toast.makeText(this, "closevoice", Toast.LENGTH_SHORT).show();
			}
		}
		if(preference==mCheckBoxPreference2){
			if((Boolean)newValue){
				Toast.makeText(this, "openshock", Toast.LENGTH_SHORT).show();
			}else{
				Toast.makeText(this, "closeshock", Toast.LENGTH_SHORT).show();
			}
		}
		return true;
	}

}
