package com.revature.util;

import java.io.PrintWriter;

public class HtmlUtil {
	public static void writerHtmlFooter(PrintWriter writer) {
		writer.print("</div>" + "    </body>" + "</html>");
	}

	public static void writerHtmlHeader(PrintWriter writer) {
		writer.print("<!DOCTYPE html>" + "<html>" + "<head>" + "<meta charset=\"UTF-8\">"
				+ " <link rel=\"stylesheet\" href=\"style.css\"/>" + "<title>Welcome to Reva</title>" + "</head>"
				+ "</head>" + "    <body>" + "        <div>" + "");
	}
}
