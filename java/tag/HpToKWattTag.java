package tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

@SuppressWarnings("serial")
public class HpToKWattTag extends TagSupport {
	
	private int powerHP;
	
	public void setPowerHP(int powerHP) {
		this.powerHP = powerHP;
	}
	
	@Override
	public int doStartTag() throws JspException {
		try {
			double powerKW = powerHP/1.36;
			pageContext.getOut().write(String.format("%.1f", powerKW));
		} catch (IOException e) {
			throw new JspException(e.getMessage());
		}
		return SKIP_BODY;
	}
}
