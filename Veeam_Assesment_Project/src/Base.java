import java.io.IOException;

import pageObjectModel.czCareers_Automation;

public class Base {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		czCareers_Automation testMethods = new czCareers_Automation();
		testMethods.setup();
		testMethods.testJobCount();
		testMethods.tearDown();
	}

}
