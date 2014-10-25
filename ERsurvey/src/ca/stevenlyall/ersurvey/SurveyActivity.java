package ca.stevenlyall.ersurvey;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class SurveyActivity extends Activity {

	// TODO load these from a file

	Survey survey = new Survey();
	int questionNum = 0;
	boolean answered;

	// UI elements
	RatingBar rating;
	Button nextButton, backButton;
	TextView questionTextView, lowScale, medScale, highScale;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_survey);

		rating = (RatingBar) findViewById(R.id.rating);
		rating.setClickable(true);
		nextButton = (Button) findViewById(R.id.nextButton);
		backButton = (Button) findViewById(R.id.backButton);
		questionTextView = (TextView) findViewById(R.id.questionTextView);
		lowScale = (TextView) findViewById(R.id.lowDesc);
		medScale = (TextView) findViewById(R.id.medDesc);
		highScale = (TextView) findViewById(R.id.highDesc);

		askMA();
		
	}

	private void askMA() {
		answered = false;
		Log.i("answered", "false");

		lowScale.setText(survey.getMaLowScale());
		medScale.setText("");
		highScale.setText(survey.getMaHighScale());

		if (questionNum > 5) {
			Log.w("survey", "all MA questions have been asked, questionNum="
					+ questionNum);
			questionNum = 0;
			askER();
			return;
		} else {
			questionTextView.setText(survey.getMAQuestion(questionNum));

			rating.setOnTouchListener(new OnTouchListener() {
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					answered = true;
					Log.i("answered", "true");
					return false;
				}

			});
			nextButton.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					if (!answered) {
						Toast.makeText(getBaseContext(),
								"Please select an answer", Toast.LENGTH_SHORT)
								.show();
						return;
					}

					survey.setMAResponse(questionNum, rating.getRating());
					Log.i("survey",
							survey.getMACode(questionNum)
									+ " response collected: "
									+ survey.getMAResponse(questionNum));
					questionNum++;
					rating.setRating(0);
					askMA();
				}

			});

		}
	}

	private void askER() {
		
		answered = false;
		Log.i("answered", "false");

		lowScale.setText(survey.getErLowScale());
		medScale.setText("");
		highScale.setText(survey.getErHighScale());

		if (questionNum > 5) {
			Log.w("survey", "all ER questions have been asked, questionNum="
					+ questionNum);
			questionNum = 0;
			askMotiv();
			return;
		} else {
			questionTextView.setText(survey.getERQuestion(questionNum));

			rating.setOnTouchListener(new OnTouchListener() {
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					answered = true;
					Log.i("answered", "true");
					return false;
				}

			});
			nextButton.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					if (!answered) {
						Toast.makeText(getBaseContext(),
								"Please select an answer", Toast.LENGTH_SHORT).show();
						return;
					}

					survey.setERResponse(questionNum, rating.getRating());
					Log.i("survey",
							survey.getERCode(questionNum) + " response collected: " + survey.getERResponse(questionNum));
					questionNum++;
					rating.setRating(0);
					askER();
				}

			});

		}
	}
private void askMotiv() {
		
		answered = false;
		Log.i("answered", "false");

		lowScale.setText(survey.getMotivLowScale());
		medScale.setText(survey.getMotivMedScale());
		highScale.setText(survey.getMotivHighScale());
		rating.setNumStars(10);
		if (questionNum > 5) {
			Log.w("survey", "all Motiv questions have been asked, questionNum="
					+ questionNum);
			return;
		} else {
			questionTextView.setText(survey.getMotivQuestion(questionNum));

			rating.setOnTouchListener(new OnTouchListener() {
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					answered = true;
					Log.i("answered", "true");
					return false;
				}

			});
			nextButton.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					if (!answered) {
						Toast.makeText(getBaseContext(),
								"Please select an answer", Toast.LENGTH_SHORT).show();
						return;
					}

					survey.setMotivResponse(questionNum, rating.getRating());
					Log.i("survey",
							survey.getMotivCode(questionNum) + " response collected: " + survey.getMotivResponse(questionNum));
					questionNum++;
					rating.setRating(0);
					Log.i("survey", "done survey items");
					return;
				
				}

			});

		}
	}
}
