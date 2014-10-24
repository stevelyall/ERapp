package ca.stevenlyall.ersurvey;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

public class SurveyActivity extends Activity {
	
	RatingBar rating;
	Button nextButton, backButton;
	TextView questionTextView;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_survey);
	    
	    rating = (RatingBar) findViewById(R.id.rating);
	    nextButton = (Button) findViewById(R.id.nextButton);
	    backButton = (Button) findViewById(R.id.backButton);
	    questionTextView = (TextView) findViewById(R.id.questionTextView);
	    
	    // TODO Auto-generated method stub
	}

}
