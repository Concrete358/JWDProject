package utils;

import javax.servlet.http.HttpServletRequest;

public class CurrentPageExtractor {

    private static final String CONTROLLER_PART = "/FrontController?";


    public static String extract(HttpServletRequest request) {
        String commandPart = request.getQueryString();
        String currentPage = CONTROLLER_PART + commandPart;
        return currentPage;
    }
}    