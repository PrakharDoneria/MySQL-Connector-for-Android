package com.mysql;

import com.mysql.OptionsActivity;
import android.animation.*;
import android.app.*;
import android.content.*;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.os.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.google.android.material.textfield.*;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;
import org.json.*;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
	
	DBHelper dbHelper;
	
	private LinearLayout linear1;
	private TextView textview1;
	private LinearLayout linear2;
	private TextInputLayout textinputlayout1;
	private LinearLayout linear3;
	private TextInputLayout textinputlayout2;
	private LinearLayout linear4;
	private Button addUserButton;
	private EditText editTextEmail;
	private EditText editTextPassword;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.main);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear1 = findViewById(R.id.linear1);
		textview1 = findViewById(R.id.textview1);
		linear2 = findViewById(R.id.linear2);
		textinputlayout1 = findViewById(R.id.textinputlayout1);
		linear3 = findViewById(R.id.linear3);
		textinputlayout2 = findViewById(R.id.textinputlayout2);
		linear4 = findViewById(R.id.linear4);
		addUserButton = findViewById(R.id.addUserButton);
		editTextEmail = findViewById(R.id.editTextEmail);
		editTextPassword = findViewById(R.id.editTextPassword);
		
		addUserButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				editTextEmail = findViewById(R.id.editTextEmail);
				editTextPassword = findViewById(R.id.editTextPassword);
				
				Button addUserButton = findViewById(R.id.addUserButton);
				addUserButton.setOnClickListener(new View.OnClickListener() {
					    @Override
					    public void onClick(View v) {
						        // Call the function to add a new user
						        String email = editTextEmail.getText().toString();
						        String password = editTextPassword.getText().toString();
						
						        // Check if email and password are not empty before adding
						        if (!email.isEmpty() && !password.isEmpty()) {
							            dbHelper.addUser(email, password);
							        } else {
							            // Show an error message or handle the empty fields case
							            SketchwareUtil.showMessage(getApplicationContext(), "Email or Password can not be empty");
							        }
						    }
				});
			}
		});
	}
	
	private void initializeLogic() {
		dbHelper = new DBHelper(this);
	}
	
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		
	}
	
	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}
	
	@Deprecated
	public int getLocationX(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[0];
	}
	
	@Deprecated
	public int getLocationY(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[1];
	}
	
	@Deprecated
	public int getRandom(int _min, int _max) {
		Random random = new Random();
		return random.nextInt(_max - _min + 1) + _min;
	}
	
	@Deprecated
	public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
		ArrayList<Double> _result = new ArrayList<Double>();
		SparseBooleanArray _arr = _list.getCheckedItemPositions();
		for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
			if (_arr.valueAt(_iIdx))
			_result.add((double)_arr.keyAt(_iIdx));
		}
		return _result;
	}
	
	@Deprecated
	public float getDip(int _input) {
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
	}
	
	@Deprecated
	public int getDisplayWidthPixels() {
		return getResources().getDisplayMetrics().widthPixels;
	}
	
	@Deprecated
	public int getDisplayHeightPixels() {
		return getResources().getDisplayMetrics().heightPixels;
	}
}