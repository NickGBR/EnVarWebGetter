package org.nickgbr;

import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;

@Stateless
public class VarKeyGetter {
    String scriptBack = "<a href=\"javascript:history.back()\">back\n</a>";

    public String getInfo(HttpServletRequest req) throws IOException {
        String result = "";
        Map<String, String> env = System.getenv();
        Enumeration<String> parameterNames = req.getParameterNames();
        String[] values = null;
        boolean isRightParam = false;
        String param = "";

        while (parameterNames.hasMoreElements()) {

            param = parameterNames.nextElement();

            if (param.trim().equals("name") || param.trim().equals("getAll")) {
                isRightParam = true;
                values = req.getParameterValues(param);
                break;
            }
        }


        if (!isRightParam) {
            return scriptBack +
                    "use param \"name\" to get environment variable or \"getAll\" for getting all environment variables";
        }  else if (param.trim().equals("name")) {
            String html  = "</html><head><title>variables table</title></head><body><table border=1>";

            for (String key : values) {
                String envVar = env.get(key);

                if (envVar == null) {
                    result = result + "<tr><td> " + key + " </td><td> hasn't been found</td></tr>";
                } else result = result + "<tr><td>" + key + "</td><td>" + envVar + "</td></tr>";
            }

            return scriptBack + html + result + "</table></body></html>";
        } else {

            String html  = "</html><head><title>variables table</title></head><body><table border=1>";
            for (Map.Entry<String, String> pair : env.entrySet()) {
                html = html + "<tr><td>"+pair.getKey()+"</td><td>" +
                        pair.getValue()+"</td></tr>";
            }
            return scriptBack +
                    html + "</td></tr></table></body></html>";
        }
    }
}
