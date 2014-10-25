package ca.stevenlyall.ersurvey;

// has:
// momentary affect items
// 
public class Survey {

	// three survey sections, each has array for questions, coding to save and user response
	
	// momentary affect
	private String[] maQuestions = { "At the moment I feel happy",
								   "At the moment I feel relaxed",
								   "At the moment I feel angry",
								   "At the moment I feel stressed",
								   "At the moment I feel anxious",
								   "At the moment I feel depressed"
								    };
	private String maLowScale = "Not at all";
	private String maHighScale = "Very much";
	private String[] maCodes = {"happy", "relaxed", "angry", "stressed", "anxious", "depressed"};
	private float[] maResponses = new float[6];
	
	//emotion regulation
	private String[] erQuestions  = { "Since the previous signal I have allowed or accepted my feelings",
									  "Since the previous signal I have changed the way I think about what caused my feelings",
									  "Since the previous signal I have criticised myself for my feelings",
									  "Since the previous signal I couldn’t stop thinking about my feelings",
									  "Since the previous signal I have talked about my feelings with others",
									  "Since the previous signal I have avoided expressing my emotions",
									  "Since the previous signal I have engaged in activities to distract myself from my feelings",
									  "Since the previous signal I have come up with ideas to fix or change the problem that caused my feelings",
									  "Since the previous signal I have pushed my feelings down or put them out of my mind"				
									};
	private String erLowScale = "Not at all";
	private String erHighScale = "A lot";
	private String[] erCodes = {"acceptance", "reappraisal", "self-criticism", "rumination", "social sharing", "expression suppression", "distraction", "problem-solving", "feeling suppression"}; 
	private float[] erResponses = new float[9];
	
	// motivation
	private String[] motivQuestions = {"Since the last signal, I have wanted my pleasant feelings to…",
									   "Since the last signal, I have wanted my unpleasant feelings to…",
									   "Since the last signal, I have wanted to control my feelings so that I can achieve some other goal"
	 								  };
	private String motivLowScale = "Decrease a lot";
	private String motivMedScale = "Stay the same";
	private String motivHighScale = "Increase a lot";
	
	private String[] motivCodes = {"pleasant", "unpleasant", "control"};
	public String getMaLowScale() {
		return maLowScale;
	}

	public String getMaHighScale() {
		return maHighScale;
	}

	public String getErLowScale() {
		return erLowScale;
	}

	public String getErHighScale() {
		return erHighScale;
	}

	public String getMotivLowScale() {
		return motivLowScale;
	}

	public String getMotivMedScale() {
		return motivMedScale;
	}

	public String getMotivHighScale() {
		return motivHighScale;
	}
	private float[] motivResponses = new float[3];
	
	
	// no arg constructor
	public Survey() {
			
	}
	
	// getters and setters for momentary affect
	public String getMAQuestion(int i) {
		return maQuestions[i];
	}
	
	public String getMACode(int i) {
		return maCodes[i];
	}
	
	public float getMAResponse(int i) {
		return maResponses[i];
	}
	
	public void setMAResponse(int i, float value) {
		maResponses[i] = value;
	}
	
	//getters and setters for emotion regulation
	public String getERQuestion(int i) {
		return erQuestions[i];
	}
	
	public String getERCode(int i) {
		return erCodes[i];
	}
	
	public float getERResponse(int i) {
		return erResponses[i];
	}
	public void setERResponse(int i, float value) {
		erResponses[i] = value;
	}
	
	//getters and setters for motivation
	public String getMotivQuestion(int i) {
		return motivQuestions[i];
	}
	
	public String getMotivCode(int i) {
		return motivCodes[i];
	}
	
	public float getMotivResponse(int i) {
		return motivResponses[i];
	}
		public void setMotivResponse(int i, float value) {
		motivResponses[i] = value;
	}
	
	
}
