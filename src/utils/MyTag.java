package utils;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.io.Serializable;
import java.time.Year;

public class MyTag extends TagSupport implements Serializable {
    @Override
    public int doStartTag() throws JspException {
        try {
            JspWriter out = pageContext.getOut();
            out.write("created by Stas Osipov - " + Year.now().getValue());
        } catch (IOException ex) {
            throw new JspTagException(ex.getMessage());
        }
        return SKIP_BODY;
    }

}
