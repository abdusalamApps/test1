import java.util.List;

import models.AllInfoRow;

/*
 * This class provides forms to be displayed to the user
 */
public class FormGenerator {

	private String formElement(String par) {
		return '"' + par + '"';
	}

	/*
	 * Form for requesting user name
	 */
	public String nameRequestForm() {
		String html = "Please enter your name";
		html += "<p> <form name=" + formElement("input");
		// html += " action=" + formElement(myURL);
		html += " method=" + formElement("get");
		html += "<p> Name: <input type=" + formElement("text") + " name=" + formElement("user") + '>';
		html += "<p> <input type=" + formElement("submit") + "value=" + formElement("Submit") + '>';
		return html;
	}

	public String projectCharRequestForm() {
		String[][] variables = { { "How many people are involved in the project?", "s0" },
				{ "How many bosses are there", "s1" } };

		String html = "<p>Please assess the importance of the following factors (1-10, where 1 is least important)";
		html += "<p> <form name=" + formElement("input2");
		html += " method=" + formElement("get");
		// html += " action=" + formElement(myURL);
		for (int i = 0; i < 2; i++) {
			html += "<p> " + variables[i][0];
			html += ": <input type=" + formElement("text") + "name =" + variables[i][1] + '>';
		}
		html += "<p> <input type=" + formElement("submit") + "value=" + formElement("Submit") + '>';
		//ksdk
		return html;
	}

	/*
	 * Form for requesting success with respect to four factors
	 */
	public String projectDataRequestForm() {
		String[][] variables = { { "Met operational performance", "s11" }, { "Met technical performance", "s12" },
				{ "Met project schedule", "s13" }, { "Stayed on budget", "s14" } };
		String html = "<p>Please assess the importance of the following factors (1-10, where 1 is least important)";
		html += "<p> <form name=" + formElement("input2");
		html += " method=" + formElement("get");
		// html += " action=" + formElement(myURL);
		for (int i = 0; i < 4; i++) {
			html += "<p> " + variables[i][0];
			html += ": <input type=" + formElement("text") + "name =" + variables[i][1] + '>';
		}
		html += "<p> <input type=" + formElement("submit") + "value=" + formElement("Submit") + '>';
		return html;
	}

	/*
	 * Generates html for AllInformation servlet
	 */
	public String generateTable(String[] columns, List<AllInfoRow> allInforList, String cssTag) {
		StringBuilder htmlBuilder = new StringBuilder();

		htmlBuilder.append("<html><head>" + cssTag);
		htmlBuilder.append("<title>All Info</title>");
		htmlBuilder.append("</head><body>");

		htmlBuilder.append("<h1>All Info in the Database</h1>");

		// Generate table's heads
		htmlBuilder.append("<table><tr>");
		for (String column : columns) {
			htmlBuilder.append("<th>" + column + "</th>");
		}
		htmlBuilder.append("</tr>");
		
		for (AllInfoRow row : allInforList) {
			htmlBuilder.append("<tr>");
			htmlBuilder.append("<td>" + row.name + "</td>");
			htmlBuilder.append("<td>" + row.answer00 + "</td>");
			htmlBuilder.append("<td>" + row.answer01 + "</td>");
			htmlBuilder.append("<td>" + row.answer11 + "</td>");
			htmlBuilder.append("<td>" + row.answer12 + "</td>");
			htmlBuilder.append("<td>" + row.answer13 + "</td>");
			htmlBuilder.append("<td>" + row.answer14 + "</td>");
			htmlBuilder.append("</tr>");
		}

		htmlBuilder.append("</Body>");
		htmlBuilder.append("</html>");

		return htmlBuilder.toString();
	}

}