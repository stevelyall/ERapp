package ca.stevenlyall.ersurvey;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
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
								"Please select an answer", Toast.LENGTH_SHORT)
								.show();
						return;
					}

					survey.setERResponse(questionNum, rating.getRating());
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
		highScale.setText(survey.getErHighScale());
		rating.setNumStars(10);
		rating.setStepSize(1.0f);
		if (questionNum == 2) {
			nextButton.setText(R.string.finish);

		}
		if (questionNum > 2) {
			Log.w("survey", "all Motiv questions have been asked, questionNum="
					+ questionNum);
			questionNum = 0;
			finishSurvey();

			nextButton.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					finishSurvey();
					Intent quit = new Intent(getBaseContext(),
							QuitActivity.class);
					startActivity(quit);
					finish();
				}
			});
			return;
		} else {
			questionTextView.setText(survey.getMotivQuestion(questionNum));
			Log.i("qnum", "question" + questionNum);
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

					survey.setMotivResponse(questionNum, rating.getRating());
					questionNum++;
					rating.setRating(0);
					askMotiv();
				}

			});

		}
	}

	private void finishSurvey() {
		try {
			File filePath = Environment
					.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);

			File f = new File(filePath, "file.txt");
			FileOutputStream fos;
			// file exists already, append to it
			if (f.exists()) {
				fos = new FileOutputStream(f, true);
				Log.i("File", "File exists, appending " + fos.toString());

			} else {
				fos = new FileOutputStream(f);
				Log.i("File", "File exists, appending " + fos.toString());
			}

			fos.write("=============\n".getBytes());
			
			//timestamp
			Date date = new Date();
			Timestamp timestamp = new Timestamp(date.getTime());
			fos.write((timestamp.toString() + "\n").getBytes());
			
			// write MA responses to file
			fos.write("\nMomentary affect items:\n".getBytes());
			String[] codes = survey.getMACodes();
			float[] responses = survey.getMAResponses();
			for (int i = 0; i < codes.length; i++) {
				fos.write((codes[i] + ": " + responses[i] + "\n").getBytes());
			}
			
			// write ER responses to file
			fos.write("\nEmotion regulation items:\n".getBytes());
			codes = survey.getERCodes();
			responses = survey.getERResponses();
			for (int i = 0; i < codes.length; i++) {
				fos.write((codes[i] + ": " + responses[i] + "\n").getBytes());
			}
			// write motivation responses to file
			fos.write("\nMotivation items:\n".getBytes());
			codes = survey.getMotivCodes();
			responses = survey.getMotivResponses();
			for (int i = 0; i < codes.length; i++) {
				fos.write((codes[i] + ": " + responses[i] + "\n").getBytes());
			}
			
			fos.write("=============\n".getBytes());
			Log.i("File", "Finished writing to file");
			fos.close();
			Log.i("File", "File closed");
		} catch (IOException e) {
			Toast.makeText(this, "Problem writing data file",
					Toast.LENGTH_SHORT).show();
			Log.e("file", "couldn't open file for writing", e);
		}

	}
}
